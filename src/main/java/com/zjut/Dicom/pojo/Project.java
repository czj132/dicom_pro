package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Integer id;
    private Integer reportUserId;
    private Integer hspUserId;
    private Integer qualityUserId;
    private String name;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private String description;
    private String pharmaceutical;
    private String medicine;
    private String disease;
    private Integer viewTotal;
    private Byte logicDeletion;
    private List<Patient> patientList;

}
