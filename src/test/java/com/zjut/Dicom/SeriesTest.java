package com.zjut.Dicom;

import com.zjut.Dicom.mapper.SeriesMapper;
import com.zjut.Dicom.pojo.Series;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SeriesTest {

    @Autowired
    private SeriesMapper seriesMapper;
    @Test
    public void testSave(){
        Series series = new Series();
        series.setPatientId(2);
        series.setStudyId(1);
        series.setUploadTime(new Date());
        seriesMapper.save(series);
        System.out.println(series.getId());
    }
}
