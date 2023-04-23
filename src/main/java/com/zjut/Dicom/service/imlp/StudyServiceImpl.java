package com.zjut.Dicom.service.imlp;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.config.PathConfig;
import com.zjut.Dicom.mapper.*;
import com.zjut.Dicom.pojo.*;
import com.zjut.Dicom.pojo.VO.StudyQualityVO;
import com.zjut.Dicom.service.StudyService;
import com.zjut.Dicom.utils.DicomFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudyServiceImpl implements StudyService {
    @Autowired
    private StudyMapper studyMapper;

    @Autowired
    private StudyQualityMapper studyQualityMapper;

    @Autowired
    private SeriesMapper seriesMapper;

    @Autowired
    private ProjectExpertMapper projectExpertMapper;

    @Autowired
    private ExpertEvaluationMapper expertEvaluationMapper;

    @Override
    public List<StudyQualityVO> getStudyQualityVOByStatus(Byte status) {
        List<StudyQuality> studyQualities = studyQualityMapper.getByStatus(status);
        List<StudyQualityVO> finalStudyQualityList = new ArrayList<>();
        ArrayList<StudyQuality> collect = studyQualities.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                Comparator.comparing(StudyQuality::getStudyId)
        )), ArrayList::new));
        for(StudyQuality studyQuality: collect){
            List<StudyQualityVO> studyQualityVO = studyMapper.getStudyQualityVO(studyQuality.getStudyId());
            finalStudyQualityList.addAll(studyQualityVO);
        }
        return finalStudyQualityList;
    }

    @Override
    public boolean Quality(Integer patientId, List<Integer> viewpointList) {
        List<Study> studyList = studyMapper.getByPatientId(patientId);
        StudyQuality studyQuality = new StudyQuality();
        int ret = 0;
        for (Study study: studyList){
            for (Integer viewpoint : viewpointList) {
                if(study.getViewpoint() == viewpoint){
                    study.setStatus((byte)2);
                    studyMapper.update(study);
                    studyQuality.setStudyId(study.getId());
                    studyQuality.setProjectId(study.getProjectId());
                    studyQuality.setStatus((byte)0);
                    studyQuality.setId(study.getId());
                    ret = studyQualityMapper.save(studyQuality);
                }
            }
            List<Series> seriesList = seriesMapper.getByStudyId(study.getId());
            for (Series series : seriesList) {
                try{
                    DicomFileUtils.convertToNii(new File(PathConfig.DCM_IMAGES_ROOT_DIR, series.getId().toString()),
                            new File(PathConfig.DCM_IMAGES_ROOT_DIR, series.getId()+".nii.gz"));
                }catch (Exception e){
                    System.out.println(series.getId()+" 转换失败");
                    e.printStackTrace();
                }
            }
        }
        return ret > 0;
    }

    @Override
    public boolean ReQuality(Integer patientId, Integer viewpoint) {
        List<Study> studyList = studyMapper.getByPatientId(patientId);
        int ret = 0;
        for (Study study: studyList){
            if(study.getViewpoint() == viewpoint){
                study.setStatus((byte)2);
                studyMapper.update(study);
                StudyQuality studyQuality = studyQualityMapper.getByStudyId(study.getId());
                studyQuality.setStatus((byte)0);
                studyQuality.setStudyId(study.getId());
                ret = studyQualityMapper.update(studyQuality);
                List<Series> seriesList = seriesMapper.getByStudyId(study.getId());
                for (Series series : seriesList) {
                    try{
                        DicomFileUtils.convertToNii(new File(PathConfig.DCM_IMAGES_ROOT_DIR, series.getId().toString()),
                                new File(PathConfig.DCM_IMAGES_ROOT_DIR, series.getId()+".nii.gz"));
                    }catch (Exception e){
                        System.out.println(series.getId()+" 转换失败");
                        e.printStackTrace();
                    }
                }
            }
        }

        return ret > 0;
    }


    @Override
    public List<Study> getByPatientId(Integer patientId) {
        return studyMapper.getByPatientId(patientId);
    }

    @Override
    public boolean QualityResult(StudyQuality studyQuality) {
        int result = 0;
        studyQuality.setStatus((byte)1);
        Study study = studyMapper.getById(studyQuality.getStudyId());
        if(studyQuality.getIsPass() == 0){
            studyQuality.setStatus((byte)2);
            study.setStatus((byte)1);
            studyMapper.update(study);
        }
        studyQuality.setUpdateTime(new Date());
        result =  studyQualityMapper.update(studyQuality);

        //质控合格后分配普通专家评估记录
        if(studyQuality.getIsPass() == 1){
            //合格则进入下一环节，初评专家评估
            Integer studyId = studyQuality.getStudyId();
            Study tempStudy = studyMapper.getById(studyId);
            Integer projectId = tempStudy.getProjectId();
            Integer patientId = tempStudy.getPatientId();
            List<ProjectExpert> projectExpertList = projectExpertMapper.queryFirstSecondExperts(projectId);
            for(ProjectExpert projectExpert : projectExpertList){
                ExpertEvaluation ee = new ExpertEvaluation(projectId, patientId, studyId, projectExpert.getExpertId(), (byte)1, (byte)0);
                result = expertEvaluationMapper.save(ee);
            }
        }

        return  result > 0;
    }

    /**
     * 分页查询
     * @param status 质控状态
     * @param begin  起始页
     * @param size  每页显示数目
     * @return
     */
    @Override
    public PageInfo<StudyQualityVO> findAllByPages(Byte status, int begin, int size) {
        List<StudyQuality> studyQualities = studyQualityMapper.getByStatus(status);
        List<StudyQualityVO> finalStudyQualityList = new ArrayList<>();
        ArrayList<StudyQuality> collect = studyQualities.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                Comparator.comparing(StudyQuality::getStudyId)
        )), ArrayList::new));
        for(StudyQuality studyQuality: collect){
            List<StudyQualityVO> studyQualityVO = studyMapper.getStudyQualityVO(studyQuality.getStudyId());
            finalStudyQualityList.addAll(studyQualityVO);
        }
        PageInfo<StudyQualityVO> pageInfo = new PageInfo<>(finalStudyQualityList);
        return pageInfo;
    }
}
