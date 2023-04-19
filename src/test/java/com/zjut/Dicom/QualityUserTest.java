package com.zjut.Dicom;

import com.zjut.Dicom.pojo.QualityUser;
import com.zjut.Dicom.service.QualityUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QualityUserTest {
    @Autowired
    private QualityUserService qualityUserService;
    @Test
    public void testUpdate(){
        QualityUser record = new QualityUser();
        record.setId(5);
        record.setName("赵六");
        record.setSex(1);
        record.setAge(40);
        record.setDepartment("生产部");
        record.setDescription("sdarew");
        boolean ret = qualityUserService.updateRecord(record);
        System.out.println(ret);
    }
}
