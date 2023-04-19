package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.SeriesQuality;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface SeriesQualityMapper {

    @Insert("insert into series_quality(series_id, status, is_pass, update_time, description) values" +
            "(#{seriesId}, #{status}, #{isPass}, #{updateTime}, #{description}")
    @Results({
            @Result(column="series_id", property="seriesId"),
            @Result(column="status", property = "status" ),
            @Result(column="is_pass", property = "isPass"),
            @Result(column="description", property = "description"),
            @Result(column="update_time",property = "updateTime")
    })
    int save(SeriesQuality seriesQuality);
}
