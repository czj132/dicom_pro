package com.zjut.Dicom;

import com.zjut.Dicom.pojo.VO.PatientExpVO;
import com.zjut.Dicom.service.ExpertEvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ExpertEvaluationTest {

    @Autowired
    private ExpertEvaluationService expertEvaluation;

    @Test
    public void testExpertEvaluation(){
        List<PatientExpVO> voList = expertEvaluation.getByExpertIdAndStatus(1,(byte)0);
        System.out.println(voList);
    }
}
