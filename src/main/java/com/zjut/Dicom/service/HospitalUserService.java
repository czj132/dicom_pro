package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.HospitalUser;

import java.util.List;
import java.util.Map;

public interface HospitalUserService {

    public boolean save(HospitalUser record);

    public boolean deleteById(Integer id);

    public boolean updateRecord(HospitalUser record);

    public boolean updateSignature(Integer id, String signature);

    /**
     * 修改密码
     * @param info:hospId,oldPassword,newPassword
     * @return
     */
    public boolean updatePassword(Map<String,Object> info);

    public HospitalUser getById(Integer id);

    public List<HospitalUser> getAll();

    public HospitalUser getByAccount(String account);

    public boolean update(HospitalUser record);
}
