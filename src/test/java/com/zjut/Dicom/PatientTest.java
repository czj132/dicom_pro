package com.zjut.Dicom;

import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;
import com.zjut.Dicom.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientService patientService;

    @Test
    public void testGetByProId(){
        List<Patient> patients = patientService.getByProId(1);
        System.out.println(patients);
    }

    @Test
    public void testSave(){
        Patient patient = new Patient();
        patient.setSex(1);
        patient.setAge(60);
        patient.setClinicalHistory("测试病理1235");
        patient.setPart("肺部");
        patient.setExaminationType("MR");
        patient.setProjectId(1);
        patient.setHospitalSource("浙一");

    }

    @Test
    public void testGetById(){
        Patient patient = patientService.getByPatientId(1);
        System.out.println(patient);
    }
}
