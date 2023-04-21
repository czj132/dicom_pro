package com.zjut.Dicom.service;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.StudyQuality;
import com.zjut.Dicom.pojo.VO.StudyQualityVO;

import java.util.List;

public interface StudyService {

   public List<StudyQualityVO> getStudyQualityVOByStatus(Byte status);

   /**
    * 申请质控
    * @param patientId
    * @return
    */
   boolean Quality(Integer patientId, List<Integer> viewpointList);

   List<Study> getByPatientId(Integer patientId);

   /**
    * 质控结果
    * @param studyQuality
    * @return
    */
   boolean QualityResult(StudyQuality studyQuality);

   /**
    * 重新质控不合格文件
    * @param patientId
    * @param viewpoint
    * @return
    */
   boolean ReQuality(Integer patientId, Integer viewpoint);
   /**
    * 分页查询
    * @param status 质控状态
    * @param begin  起始页
    * @param size  每页显示数目
    * @return
    */
   PageInfo<StudyQualityVO> findAllByPages(Byte status, int begin, int size);

}
