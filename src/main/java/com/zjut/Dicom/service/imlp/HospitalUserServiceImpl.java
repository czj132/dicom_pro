package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.HospitalUserMapper;
import com.zjut.Dicom.pojo.HospitalUser;
import com.zjut.Dicom.service.HospitalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HospitalUserServiceImpl implements HospitalUserService {

    @Autowired
    private HospitalUserMapper hospitalUserMapper;

    public boolean save(HospitalUser record) {
        return hospitalUserMapper.save(record) > 0;
    }

    public boolean deleteById(Integer id) {
        return hospitalUserMapper.deleteById(id) > 0;
    }

    public boolean updateRecord(HospitalUser record) {
        return hospitalUserMapper.updateRecord(record) > 0;
    }

    @Override
    public boolean updateSignature(Integer id, String signature) {
        return hospitalUserMapper.updateSignature(id,signature) > 0;
    }

    @Override
    public boolean updatePassword(Map<String, Object> info) {
        String oldPassword = (String) info.get("oldPassword");
        String newPassword = (String) info.get("newPassword");
        HospitalUser hospitalUser = hospitalUserMapper.getById((Integer) info.get("id"));
        //判断用户密码是否一致
        if(hospitalUser.getPassword().equals(oldPassword)){
            hospitalUser.setPassword(newPassword);
            hospitalUserMapper.update(hospitalUser);
            return true;
        }else {
            return false;
        }
    }

    public HospitalUser getById(Integer id) {
        return hospitalUserMapper.getById(id);
    }

    public List<HospitalUser> getAll() {
        return hospitalUserMapper.getAll();
    }

    public HospitalUser getByAccount(String account) {
        return hospitalUserMapper.getByAccount(account);
    }

    @Override
    public boolean update(HospitalUser record) {
        return hospitalUserMapper.update(record) > 0;
    }
}
