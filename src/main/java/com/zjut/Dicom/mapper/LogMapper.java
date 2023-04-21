package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
@Mapper
public interface LogMapper {

    @Insert("insert into log(id,user_id, user_type, operation_type, mission_id, " +
            "slide_id, operation_time, `describe`) values (#{id}, #{userId}, #{userType}, #{operationType}," +
            "#{missionId}, #{slideId}, #{operationTime}, #{describe})")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="user_id", property="userId"),
            @Result(column="user_type", property="userType"),
            @Result(column="operation_type", property="operationType"),
            @Result(column="mission_id", property="missionId"),
            @Result(column="slide_id", property="slideId"),
            @Result(column="operation_time", property="operationTime"),
            @Result(column="describe", property="describe")
    })
    int save(Log log);
}
