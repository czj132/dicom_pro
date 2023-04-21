package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.QualityUser;

import java.util.List;
import java.util.Map;

public interface QualityUserService {
    List<QualityUser> getAll();

    QualityUser getById(Integer id);

    boolean updatePwd(Map<String, Object> info);

    boolean updateRecord(QualityUser record);
}
