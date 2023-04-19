package com.zjut.Dicom.controller;

import com.zjut.Dicom.pojo.QualityUser;
import com.zjut.Dicom.service.QualityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/qualityUsers")
public class QualityUserController {

    @Autowired
    private QualityUserService qualityUserService;

    @PutMapping
    public boolean updateRecord(@RequestBody QualityUser record){
        return qualityUserService.updateRecord(record);
    }

    @PutMapping("/updatePwd")
    public boolean updatePwd(@RequestBody Map<String, Object> info){
        return qualityUserService.updatePwd(info);
    }
}
