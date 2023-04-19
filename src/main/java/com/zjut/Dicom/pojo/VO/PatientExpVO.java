package com.zjut.Dicom.pojo.VO;

import com.zjut.Dicom.pojo.Study;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PatientExpVO {
    //病人表
    private Integer patientId;
    private String clinicalHistory;
    private String part;
    private String examinationType;
    private Date createTime;
    private String hospitalSource;
    private Integer age;
    private Integer sex;

    //项目表
    private String medicine;
    private String disease;

    //阶段表
    List<Study> studyList;

}
