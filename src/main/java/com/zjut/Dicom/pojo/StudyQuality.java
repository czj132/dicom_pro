package com.zjut.Dicom.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyQuality {
    private Integer id;
    private Integer qualityUserId;
    private Integer projectId;
    private Integer studyId;
    private Byte status;
    private Byte isPass;
    private String description;
    @JsonFormat(locale = "zjut",timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
