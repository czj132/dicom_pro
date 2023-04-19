package com.zjut.Dicom.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 阶段表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Study {
    private Integer id;
    private Integer projectId;
    private Integer patientId;
    private Integer viewpoint;
    private Byte status;
    private Date uploadTime;
    @JsonFormat(locale = "zjut",timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;
    private Date viewTime;

}
