package com.zjut.Dicom.service;

import com.zjut.Dicom.pojo.SeriesQuality;

public interface SeriesQualityService {

    /**
     * 保存质控结果
     * @return
     */
    boolean save(SeriesQuality seriesQuality);
}
