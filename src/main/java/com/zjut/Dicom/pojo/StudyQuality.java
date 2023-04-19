package com.zjut.Dicom.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudyQuality {
    private Integer id;
    private Integer projectId;
    private Integer studyId;
    private Byte status;
    private Byte isPass;
    private String description;
    @JsonFormat(locale = "zjut",timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
