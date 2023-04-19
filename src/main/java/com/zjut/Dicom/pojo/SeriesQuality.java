package com.zjut.Dicom.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SeriesQuality {
    private Integer id;
    private Integer seriesId;
    private Byte status;
    private Byte isPass;
    private String description;
    private Date updateTime;
    private List<Series> seriesList;
}
