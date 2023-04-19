package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.QualityUserMapper;
import com.zjut.Dicom.pojo.QualityUser;
import com.zjut.Dicom.service.QualityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QualityUserServiceImpl implements QualityUserService {
    @Autowired
    private QualityUserMapper qualityUserMapper;
    @Override
    public List<QualityUser> getAll() {
        return qualityUserMapper.getAll();
    }

    @Override
    public QualityUser getById(Integer id) {
        return qualityUserMapper.getById(id);
    }

    @Override
    public boolean updatePwd(Map<String, Object> info) {
        String oldPassword = (String) info.get("oldPassword");
        String newPassword = (String) info.get("newPassword");
        QualityUser qualityUser = qualityUserMapper.getById((Integer) info.get("id"));
        if (qualityUser.getPassword().equals(oldPassword)){
            qualityUser.setPassword(newPassword);
            qualityUserMapper.update(qualityUser);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateRecord(QualityUser record) {
        return qualityUserMapper.updateRecord(record) > 0;
    }
}
