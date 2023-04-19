package com.zjut.Dicom.controller;

import com.zjut.Dicom.pojo.ExpertEvaluation;
import com.zjut.Dicom.pojo.VO.PatientExpVO;
import com.zjut.Dicom.service.ExpertEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ExpertController {

    @Autowired
    private ExpertEvaluationService expertEvaluationService;

    @GetMapping("/getByExpertIdAndStatus/{expertId}/{status}")
    public List<PatientExpVO> getByExpertIdAndStatus(@PathVariable("expertId") Integer expertId, @PathVariable("status") Byte status){
        return expertEvaluationService.getByExpertIdAndStatus(expertId,status);
    }


    /**
     * 专家提交报告：专家会针对该阶段，提交report和validStatus，专家需要提供expertId和studyId
     * @param expertEvaluation
     * @return
     */
    @PostMapping("/expert/pushEvaluation")
    public int pushEvaluation(@RequestBody ExpertEvaluation expertEvaluation){
        return expertEvaluationService.pushEvaluation(expertEvaluation);
    }
}
