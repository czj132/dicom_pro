package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.QualityUser;

import java.util.List;
import java.util.Map;

public interface QualityUserService {
    public List<QualityUser> getAll();

    public QualityUser getById(Integer id);

    public boolean updatePwd(Map<String, Object> info);

    public boolean updateRecord(QualityUser record);
}
