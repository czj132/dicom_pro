package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.HospitalUser;

import java.util.List;
import java.util.Map;

public interface HospitalUserService {

    boolean save(HospitalUser record);

    boolean deleteById(Integer id);

    boolean updateRecord(HospitalUser record);

    boolean updateSignature(Integer id, String signature);

    /**
     * 修改密码
     * @param info:hospId,oldPassword,newPassword
     * @return
     */
    boolean updatePassword(Map<String,Object> info);

    HospitalUser getById(Integer id);

    List<HospitalUser> getAll();

    HospitalUser getByAccount(String account);

    boolean update(HospitalUser record);
}
