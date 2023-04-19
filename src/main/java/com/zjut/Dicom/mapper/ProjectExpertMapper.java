package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.ProjectExpert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectExpertMapper {

    @Select("select * from project_expert where project_id = #{projectId} and expert_type = 1")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="project_id", property="projectId"),
            @Result(column="expert_id", property="expertId"),
            @Result(column="expert_type", property="expertType")
    })
    List<ProjectExpert> queryFirstSecondExperts(Integer projectId);


    @Select("select * from project_expert where project_id = #{projectId} and expert_type = 2")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="project_id", property="projectId"),
            @Result(column="expert_id", property="expertId"),
            @Result(column="expert_type", property="expertType")
    })
    ProjectExpert selectFinalExpertByProjectId(Integer projectId);
}
