package com.zjut.Dicom.service;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;


import java.util.List;


public interface ProjectService {

    public boolean save(Project project);

    public boolean deleteById(Integer id);

    public boolean update(Project project);

    public Project getById(Integer id);

    public List<Project> getAll();

    public PatientStudyVO getVoByStuId(Integer studyId);

    //分页查询
    public List<Project> selectByPage(int begin, int size);

    public PageInfo<Project> findAllByPages(int begin, int size);
}
