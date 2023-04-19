package com.zjut.Dicom.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.StudyQuality;
import com.zjut.Dicom.pojo.VO.StudyQualityVO;
import com.zjut.Dicom.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudyController {
    @Autowired
    private StudyService studyService;

    @GetMapping("/study/{status}")
    public List<StudyQualityVO> getStudyQualityVOByStatus(@PathVariable Byte status){
        return studyService.getStudyQualityVOByStatus(status);
    }

    @PostMapping("/quality/{patientId}")
    public boolean Quality(@PathVariable Integer patientId, @RequestBody List<Integer> viewpointList){
        return studyService.Quality(patientId, viewpointList);
    }

    @PostMapping("/ReQuality/{patientId}/{viewpoint}")
    public boolean ReQuality(@PathVariable("patientId") Integer patientId, @PathVariable("viewpoint") Integer viewpoint){
        return studyService.ReQuality(patientId, viewpoint);
    }

    @GetMapping("/getByPatientId/{patientId}")
    public List<Study> getByPatientId(@PathVariable Integer patientId){
        return studyService.getByPatientId(patientId);
    }

    /**
     * 质控结果
     * @param studyQuality
     * @return
     */
    @PostMapping("/qualityResult")
    public boolean QualityResult(@RequestBody StudyQuality studyQuality){
        return studyService.QualityResult(studyQuality);
    }

    @GetMapping("/{status}/{begin}/{size}")
    public PageInfo<StudyQualityVO> findAllByPages(@PathVariable("status") Byte status, @PathVariable("begin") int begin, @PathVariable("size") int size){
        return studyService.findAllByPages(status,begin,size);
    }
}
