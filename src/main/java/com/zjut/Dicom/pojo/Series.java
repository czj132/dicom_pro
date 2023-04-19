package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    private Integer id;
    private Integer patientId;
    private Integer studyId;
    private Integer viewpoint;
    private Date uploadTime;
}
