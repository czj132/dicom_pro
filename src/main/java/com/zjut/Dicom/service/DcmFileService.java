package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.DcmFileReTrans;
import com.zjut.Dicom.pojo.DcmFileTrans;
import com.zjut.Dicom.pojo.VO.StudyDcmImageVO;

import java.io.File;

public interface DcmFileService {
    /**
     * 上传一组图像
     * @param dcmFileTrans
     * @return
     */
    boolean uploadDcmFile(DcmFileTrans dcmFileTrans);

    /**
     * 根据studyId获取这个阶段采集的所有图像文件名，以StudyDcmImageVO返回
     * @param studyId
     * @return
     */
    StudyDcmImageVO getDcmFilesByStudyId(Integer studyId);
    byte[] getSingleFile(File dcmPath);
    boolean reTransDcmFile(DcmFileReTrans dcmFileReTrans);

    /**
     * 初次上传功能（高级版）
     * @param dcmFileReTrans 包含两级目录的传输类，两级目录是以一个study作为起始
     * @return
     */
    boolean studyUploading(DcmFileReTrans dcmFileReTrans);
}
