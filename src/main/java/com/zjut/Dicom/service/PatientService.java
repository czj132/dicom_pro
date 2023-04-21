package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;

import java.util.List;

public interface PatientService {
    List<Patient> getByProId(Integer projectId);

    boolean save(Patient patient);

    //public Patient getByIdAndProId(Integer id, Integer projectId);

    boolean deleteById(Integer id);

    boolean update(Patient patient);

    Patient getByPatientId(Integer patientId);

}
