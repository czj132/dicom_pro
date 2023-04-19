package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private Integer id;
    private Integer sex;
    private Integer age;
    private String clinicalHistory;
    private String part;
    private String examinationType;
    private Date createTime;
    private Integer projectId;
    private String hospitalSource;
    private String comment;
    private List<Study> studyList;

}
