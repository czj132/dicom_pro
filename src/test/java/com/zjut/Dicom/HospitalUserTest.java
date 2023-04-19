package com.zjut.Dicom;

import com.zjut.Dicom.pojo.HospitalUser;
import com.zjut.Dicom.service.HospitalUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HospitalUserTest {

    @Autowired
    private HospitalUserService hospitalUserService;

    @Test
    public void testGetId(){
        HospitalUser hos = hospitalUserService.getById(1);
        System.out.println(hos);
    }

    @Test
    public void testGetAll(){
        List<HospitalUser> hospitalUsers = hospitalUserService.getAll();
        System.out.println(hospitalUsers);
    }

    @Test
    public void testGetAccount(){
        HospitalUser account = hospitalUserService.getByAccount("zhaoliu");
        System.out.println(account);
    }

    @Test
    public void testDelete(){
        boolean flag = hospitalUserService.deleteById(4);
        System.out.println(flag);
    }

    @Test
    public void testSave(){
        HospitalUser hospitalUser = new HospitalUser();
        hospitalUser.setAccount("zhaoliu");
        hospitalUser.setPassword("12036");
        hospitalUser.setName("赵六");
        hospitalUser.setSex(1);
        hospitalUser.setAge(20);
        hospitalUser.setPhone("13505830926");
        hospitalUser.setHospital("浙大二院");
        hospitalUser.setDepartment("肿瘤科");
        hospitalUser.setDescription("情况非常不好");
        hospitalUser.setEsignature("12365");
        hospitalUser.setLogicDeletion((byte)1);
        boolean ret = hospitalUserService.save(hospitalUser);
        System.out.println(ret);
    }

    @Test
    public void testUpdatePwd(){
        Map<String, Object> pwd = new HashMap<>();
        pwd.put("id",1);
        pwd.put("oldPassword","1234");
        pwd.put("newPassword","zhangsan");
        boolean ret = hospitalUserService.updatePassword(pwd);
        System.out.println(ret);
    }


}
