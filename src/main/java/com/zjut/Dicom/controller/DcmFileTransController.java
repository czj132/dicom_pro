package com.zjut.Dicom.controller;

import com.zjut.Dicom.pojo.DcmFileReTrans;
import com.zjut.Dicom.pojo.DcmFileTrans;
import com.zjut.Dicom.pojo.VO.StudyDcmImageVO;
import com.zjut.Dicom.service.DcmFileService;
import com.zjut.Dicom.utils.ContentVariable;
import com.zjut.Dicom.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


@RestController
public class DcmFileTransController {

    @Autowired
    private DcmFileService dcmFileService;

    /**
     * 初次上传功能（高级版）
     * @param dcmFileReTrans 包含两级目录的传输类，两级目录是以一个study作为起始
     * @return
     */
    @PostMapping("/studyUploading")
    public boolean studyUploading(DcmFileReTrans dcmFileReTrans){
        return dcmFileService.studyUploading(dcmFileReTrans);
    }

    /**
     * 重新上传质控不通过的图像
     * @param dcmFileReTrans
     * @return
     */
    @PostMapping("/reTransDcmFile")
    public boolean reTransDcmFile(DcmFileReTrans dcmFileReTrans){
        return dcmFileService.reTransDcmFile(dcmFileReTrans);
    }

    /**
     * 读取一个dcm图像
     * @param seriesId
     * @param dcmName 图像名称
     */
    @GetMapping("/showImg/{seriesId}/{dcmName}")
    public void downloadFold(@PathVariable("seriesId") Integer seriesId,@PathVariable("dcmName") String dcmName){
        File dcmPath = new File(ContentVariable.rootDir, seriesId+"/"+dcmName);
        if(dcmPath.exists() && dcmPath.isFile()){
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            OutputStream outputStream = null;
            try {
                outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                response.setHeader("content-disposition", "inline");
                response.setCharacterEncoding("utf-8");
                byte[] bytes = dcmFileService.getSingleFile(dcmPath);
                assert bytes != null;
                outputStream.write(bytes);
                outputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取指定studyId对应的所有seriesId以及对应的文件名
     * @param studyId
     * @return
     */
    @GetMapping("/studyImgInfo/{studyId}")
    public StudyDcmImageVO getTotalFileName(@PathVariable("studyId") Integer studyId){
        return dcmFileService.getDcmFilesByStudyId(studyId);
    }
}
