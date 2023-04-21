package com.zjut.Dicom.service.imlp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.mapper.PatientMapper;
import com.zjut.Dicom.mapper.ProjectMapper;
import com.zjut.Dicom.mapper.StudyMapper;
import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;
import com.zjut.Dicom.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public int save(Project project) {
        project.setCreateTime(new Date());
        project.setLogicDeletion((byte)0);
        project.setStatus((byte)0);
        return projectMapper.save(project);
    }

    @Override
    public boolean deleteById(Integer id) {
        return projectMapper.deleteById(id) > 0;
    }

    @Override
    public int update(Project project) {
        project.setUpdateTime(new Date());
        return projectMapper.update(project);
    }

    @Override
    public Project getById(Integer id) {
        return projectMapper.getById(id);
    }

    @Override
    public List<Project> getAll() {
        List<Project> projects = projectMapper.getAll();
        for (Project project: projects) {
            List<Patient> patients = patientMapper.getByProId(project.getId());
            for (Patient patient : patients) {
                List<Study> studyList = studyMapper.getByPatientId(patient.getId());
                patient.setStudyList(studyList);
            }
            project.setPatientList(patients);
        }
        return projects;
    }

    @Override
    public PatientStudyVO getVoByStuId(Integer studyId) {
        return projectMapper.getVoByStuId(studyId);
    }

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Override
    public List<Project> selectByPage(int begin, int size) {
        PageHelper.startPage(begin,size);
        List<Project> projects = projectMapper.getAll();
        return projects;
    }

    @Override
    public PageInfo<Project> findAllByPages(int begin, int size) {
        PageHelper.startPage(begin,size);
        List<Project> projects = projectMapper.getAll();
        PageInfo<Project> pageInfo = new PageInfo<>(projects);
        return pageInfo;
    }
}
