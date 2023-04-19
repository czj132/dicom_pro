package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;

import java.util.List;

public interface PatientService {
    public List<Patient> getByProId(Integer projectId);

    public boolean save(Patient patient);

    public Patient getByIdAndProId(Integer id, Integer projectId);

    public boolean deleteById(Integer id);

    public boolean update(Patient patient);

    public Patient getByPatientId(Integer patientId);

}
