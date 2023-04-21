package com.zjut.Dicom.service;

import com.github.pagehelper.PageInfo;
import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;


import java.util.List;


public interface ProjectService {

    int save(Project project);

    boolean deleteById(Integer id);

    int update(Project project);

    Project getById(Integer id);

    List<Project> getAll();

    PatientStudyVO getVoByStuId(Integer studyId);

    //分页查询
    List<Project> selectByPage(int begin, int size);

    PageInfo<Project> findAllByPages(int begin, int size);
}
