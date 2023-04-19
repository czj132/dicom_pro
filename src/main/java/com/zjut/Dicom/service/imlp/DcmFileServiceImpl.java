package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.SeriesMapper;
import com.zjut.Dicom.mapper.StudyMapper;
import com.zjut.Dicom.mapper.StudyQualityMapper;
import com.zjut.Dicom.pojo.*;
import com.zjut.Dicom.pojo.VO.StudyDcmImageVO;
import com.zjut.Dicom.service.DcmFileService;
import com.zjut.Dicom.utils.ContentVariable;
import com.zjut.Dicom.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DcmFileServiceImpl implements DcmFileService {

    @Autowired
    private SeriesMapper seriesMapper;

    @Autowired
    private StudyMapper studyMapper;

    @Autowired
    private StudyQualityMapper studyQualityMapper;

    /**
     * 上传一组图像，对单组图像进行插入或者是修改原有的记录
     * @param dcmFileTrans 保存患者id，阶段id，阶段数，dcm图像
     * @return
     */
    @Override
    public boolean uploadDcmFile(DcmFileTrans dcmFileTrans) {
        Series series = new Series();
        // 判断是否为新的记录
        if (dcmFileTrans.getSeriesId() != null){
            // 1. 如果带有seriesId，则表示这次上传是修改，进行覆盖之前的记录
            series.setUploadTime(new Date());
            series.setId(dcmFileTrans.getSeriesId());
            seriesMapper.update(series);
            //删除旧的dcm文件
            FileUtil.deleteDir(String.valueOf(dcmFileTrans.getSeriesId()));
        }else {
            //2.插入序列记录
            series.setPatientId(dcmFileTrans.getPatientId());
            series.setStudyId(dcmFileTrans.getStudyId());
            series.setUploadTime(new Date());
            seriesMapper.save(series);
        }
        //保存图像，默认是以序列id作为一组dcm文件目录名
        String dirName = String.valueOf(series.getId());
        return FileUtil.saveMultiFiles(dirName, dcmFileTrans.getFiles());
    }

    /**
     * 根据studyId获取这个阶段采集的所有图像文件名，以StudyDcmImageVO返回
     * @param studyId
     * @return
     */
    @Override
    public StudyDcmImageVO getDcmFilesByStudyId(Integer studyId) {
        StudyDcmImageVO studyDcmImageVO = new StudyDcmImageVO(studyId);
        // 1. 获取所有关联的seriesId
        List<Integer> seriesIds = seriesMapper.getByStudyId(studyId).stream().map(t -> t.getId()).collect(Collectors.toList());
        for(Integer seriesId:seriesIds){
            // 2. 根据seriesId得到对应的文件名
            File seriesDir = new File(ContentVariable.rootDir, String.valueOf(seriesId));
            if(seriesDir.exists() && seriesDir.list() != null){
                List<String> fileNames = Arrays.asList(seriesDir.list());
                studyDcmImageVO.getSeriesInfo().put(seriesId, fileNames);
            }
        }
        return studyDcmImageVO;
    }

    /**
     * 读取图像并展示
     * @param dcmPath 图像路径
     * @return
     */
    @Override
    public byte[] getSingleFile(File dcmPath) {
        return FileUtil.readFile(dcmPath);
    }

    /**
     * 重新上传质控不通过的图像
     * @param dcmFileReTrans
     * @return
     */
    @Override
    public boolean reTransDcmFile(DcmFileReTrans dcmFileReTrans) {
        //1.更新上传时间
        Study study = studyMapper.getById(dcmFileReTrans.getStudyId());
        study.setUploadTime(new Date());
        study.setStatus((byte)2);
        studyMapper.update(study);
        // 2. 删除旧的记录，根据studyId删除
        List<String> dirNames = seriesMapper.getByStudyId(dcmFileReTrans.getStudyId()).stream().map(t -> String.valueOf(t.getId())).collect(Collectors.toList());
        FileUtil.deleteDirs(dirNames);
        seriesMapper.deleteByStudyId(dcmFileReTrans.getStudyId());

        //更新质控表的状态
        StudyQuality studyQuality = studyQualityMapper.getByStudyId(dcmFileReTrans.getStudyId());
        studyQuality.setStatus((byte)0);
        studyQuality.setUpdateTime(new Date());
        studyQualityMapper.update(studyQuality);

        //3.更新记录 要将文件按目录名分配
        Map<String, List<MultipartFile>> groups =
                Arrays.stream(dcmFileReTrans.getFiles()).collect(Collectors.groupingBy(t->{
                    String fileName = t.getOriginalFilename();
                    fileName.replace("\\\\","/");
                    String regx = "/(.*?)/";
                    Pattern pattern = Pattern.compile(regx);
                    Matcher matcher = pattern.matcher(fileName);
                    boolean result = matcher.find();
                    if(result){
                        return matcher.group(1);
                    }else{
                        return UUID.randomUUID().toString();
                    }
                }));
        System.out.println(groups.keySet());
        //4.创建对应的DcmFileTrans
        DcmFileTrans dcmFileTrans = new DcmFileTrans();
        dcmFileTrans.setStudyId(dcmFileReTrans.getStudyId());
        dcmFileTrans.setPatientId(dcmFileReTrans.getPatientId());
        for(Map.Entry<String, List<MultipartFile>> entry: groups.entrySet()){
            dcmFileTrans.setFiles(entry.getValue().toArray(new MultipartFile[0]));
            uploadDcmFile(dcmFileTrans);
        }
        return true;
    }

    /**
     * 初次上传功能(高级版)
     * @param dcmFileReTrans 包含两级目录的传输类，两级目录是以一个study作为起始
     * @return
     */
    @Override
    public boolean studyUploading(DcmFileReTrans dcmFileReTrans) {
        List<Series> seriesList = seriesMapper.getByStudyId(dcmFileReTrans.getStudyId());
        // 1. 更新上传时间
        Study study = studyMapper.getById(dcmFileReTrans.getStudyId());
        study.setUploadTime(new Date());
        study.setStatus((byte)1);
        studyMapper.update(study);

        //2.删除旧的记录，根据studyId删除
        if(seriesList.size() != 0){
            //seriesList不为空，表示重传
            List<String> dirNames = seriesMapper.getByStudyId(dcmFileReTrans.getStudyId()).stream().map(t -> String.valueOf(t.getId())).collect(Collectors.toList());
            FileUtil.deleteDirs(dirNames);
            seriesMapper.deleteByStudyId(dcmFileReTrans.getStudyId());
        }
        //3.更新记录 要将文件按目录名分配
        Map<String, List<MultipartFile>> groups =
                Arrays.stream(dcmFileReTrans.getFiles()).collect(Collectors.groupingBy(t->{
                    String fileName = t.getOriginalFilename();
                    fileName.replace("\\\\","/");
                    String regx = "/(.*?)/";
                    Pattern pattern = Pattern.compile(regx);
                    Matcher matcher = pattern.matcher(fileName);
                    boolean result = matcher.find();
                    if(result){
                        return matcher.group(1);
                    }else{
                        return UUID.randomUUID().toString();
                    }
                }));
        System.out.println(groups.keySet());
        //4.创建对应的DcmFileTrans
        DcmFileTrans dcmFileTrans = new DcmFileTrans();
        dcmFileTrans.setStudyId(dcmFileReTrans.getStudyId());
        dcmFileTrans.setPatientId(dcmFileReTrans.getPatientId());
        for(Map.Entry<String, List<MultipartFile>> entry: groups.entrySet()){
            dcmFileTrans.setFiles(entry.getValue().toArray(new MultipartFile[0]));
            uploadDcmFile(dcmFileTrans);
        }
        return true;
    }
}
