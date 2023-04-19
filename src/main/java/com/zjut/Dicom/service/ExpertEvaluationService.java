package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.ExpertEvaluation;
import com.zjut.Dicom.pojo.VO.PatientExpVO;

import java.util.List;

public interface ExpertEvaluationService {

   List<PatientExpVO> getByExpertIdAndStatus(Integer expertId, Byte status);


   /**
    * 专家提交报告
    * @param expertEvaluation
    * @return
    */
   int pushEvaluation(ExpertEvaluation expertEvaluation);

}
