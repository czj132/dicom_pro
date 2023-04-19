package com.zjut.Dicom;

import com.zjut.Dicom.mapper.StudyMapper;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.StudyQuality;
import com.zjut.Dicom.pojo.VO.StudyQualityVO;
import com.zjut.Dicom.service.StudyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudyTest {
    @Autowired
    private StudyService studyService;

    @Test
    public void testGetQualities(){
        byte status = 0;
        List<StudyQualityVO> qualityVOS = studyService.getStudyQualityVOByStatus(status);
        System.out.println(qualityVOS);
    }

    @Test
    public void testQualityResult(){
        StudyQuality studyQuality = new StudyQuality();
        studyQuality.setId(1);
        studyQuality.setStudyId(1);
        studyQuality.setProjectId(3);
        studyQuality.setIsPass((byte)0);
        studyQuality.setDescription("片子质量有问题");
        studyService.QualityResult(studyQuality);
    }
}
