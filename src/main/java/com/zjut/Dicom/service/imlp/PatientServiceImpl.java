package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.PatientMapper;
import com.zjut.Dicom.mapper.StudyMapper;
import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private StudyMapper studyMapper;
    @Override
    public List<Patient> getByProId(Integer projectId) {
        return patientMapper.getByProId(projectId);
    }

    @Override
    public boolean save(Patient patient) {
        //获取创建的StudyList
        List<Study> studyList = patient.getStudyList();
        //新建患者
        patient.setCreateTime(new Date());
        int ret1 = patientMapper.save(patient);
        //获取患者id
        Integer patientId = patient.getId();
        //新建study记录
        int ret2 = 0;
        for (Study study: studyList){
            study.setProjectId(patient.getProjectId());
            study.setPatientId(patientId);
            study.setStatus((byte)0);
            ret2 = studyMapper.save(study);
        }
        return (ret1 + ret2) > 0 ;
    }

    @Override
    public boolean deleteById(Integer id) {
        return patientMapper.deleteById(id) > 0;
    }

    @Override
    public boolean update(Patient patient) {
        return patientMapper.update(patient) > 0;
    }

    @Override
    public Patient getByPatientId(Integer patientId) {
        Patient patient = patientMapper.getById(patientId);
        List<Study> studies = studyMapper.getByPatientId(patientId);
        patient.setStudyList(studies);
        return patient;
    }
}
