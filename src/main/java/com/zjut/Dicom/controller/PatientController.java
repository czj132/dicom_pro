package com.zjut.Dicom.controller;

import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * 根据项目编号查询所有患者
     * @param projectId
     * @return
     */
    @GetMapping("/{projectId}")
    public List<Patient> getByProId(@PathVariable Integer projectId){
        return patientService.getByProId(projectId);
    }

    /**
     * 添加患者
     * @param patient 
     * @return
     */
    @PostMapping
    public boolean save(@RequestBody Patient patient){
        return patientService.save(patient);
    }


    @PostMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id") Integer id){
        return patientService.deleteById(id);
    }


    @PutMapping
    public boolean update(@RequestBody Patient patient){
        return patientService.update(patient);
    }

    /**
     * 查看患者详情
     * @param patientId
     * @return
     */
    @GetMapping("/getByPatientId/{patientId}")
    public Patient getByPatientId(@PathVariable Integer patientId){
        return patientService.getByPatientId(patientId);
    }
}
