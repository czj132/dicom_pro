package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {

    @Insert("insert into log(user_id, user_type, operation_type, mission_id, " +
            "slide_id, operation_time, `describe`) values (#{userId}, #{userType}, #{operationType}," +
            "#{missionId}, #{slideId}, #{operationTime}, #{describe})")
    int save(Log log);
}
