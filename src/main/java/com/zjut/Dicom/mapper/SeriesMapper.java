package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Series;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface SeriesMapper {

    @Update({
            "update series set patient_id = #{patientId},",
            "viewpoint = #{viewpoint},",
            "study_id = #{studyId},",
            "upload_time = #{uploadTime} ",
            "where id = #{id}"
    })
    int update(Series series);


    @Select("select * from series where study_id = #{studyId}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="patient_id", property="patientId"),
            @Result(column="viewpoint", property="viewpoint"),
            @Result(column="study_id", property="studyId"),
            @Result(column="upload_time", property="uploadTime")
    })
    List<Series> getByStudyId(Integer studyId);

    @Delete("delete from series where study_id = #{studyId}")
    int deleteByStudyId(Integer studyId);


    @Insert("insert into series(id, patient_id,study_id,viewpoint,upload_time) " +
            "values (#{id},#{patientId},#{studyId},#{viewpoint}," +
            "#{uploadTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="patient_id", property="patientId"),
            @Result(column="viewpoint", property="viewpoint"),
            @Result(column="study_id", property="studyId"),
            @Result(column="upload_time", property="uploadTime")
    })
    int save(Series series);


    @Select("select * from series where id = #{id}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="patient_id", property="patientId"),
            @Result(column="viewpoint", property="viewpoint"),
            @Result(column="study_id", property="studyId"),
            @Result(column="upload_time", property="uploadTime")
    })
    Series getById(Integer id);
}
