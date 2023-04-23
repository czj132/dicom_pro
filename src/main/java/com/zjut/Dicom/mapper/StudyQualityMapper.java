package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.StudyQuality;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudyQualityMapper {

   @Select("select * from study_quality where study_id = #{studyId}")
   @Results({
           @Result(column="id", property="id"),
           @Result(column = "quality_user_id", property = "qualityUserId"),
           @Result(column="study_id", property = "studyId" ),
           @Result(column="project_id", property = "projectId"),
           @Result(column="status", property = "status" ),
           @Result(column="is_pass", property = "isPass"),
           @Result(column="description", property = "description"),
           @Result(column="update_time",property = "updateTime")
   })
   StudyQuality getByStudyId(Integer studyId);

   @Select("select * from study_quality where status = #{status}")
   @Results({
           @Result(column="id", property="id"),
           @Result(column = "quality_user_id", property = "qualityUserId"),
           @Result(column="study_id", property = "studyId" ),
           @Result(column="project_id", property = "projectId"),
           @Result(column="status", property = "status" ),
           @Result(column="is_pass", property = "isPass"),
           @Result(column="description", property = "description"),
           @Result(column="update_time",property = "updateTime")
   })
   List<StudyQuality> getByStatus(Byte status);


   @Insert("insert into study_quality(id, quality_user_id, study_id, project_id, status, is_pass, description, update_time)" +
           "values (#{id}, #{qualityUserId}, #{studyId}, #{projectId}, #{status}, #{isPass}, #{description}, #{updateTime})")
   @Results({
           @Result(column="id", property="id"),
           @Result(column = "quality_user_id", property = "qualityUserId"),
           @Result(column="study_id", property = "studyId" ),
           @Result(column="project_id", property = "projectId"),
           @Result(column="status", property = "status" ),
           @Result(column="is_pass", property = "isPass"),
           @Result(column="description", property = "description"),
           @Result(column="update_time",property = "updateTime")
   })
   int save(StudyQuality studyQuality);

   @Update("<script>" +
           "update study_quality" +
           "<set>" +
           "   <if test = 'projectId != null'>" +
           "       project_id = #{projectId}," +
           "   </if>" +
           "   <if test='status != null'>" +
           "       status = #{status}," +
           "   </if>" +
           "   <if test='isPass != null'>" +
           "       is_pass = #{isPass}," +
           "   </if>" +
           "   <if test='description != null'>" +
           "      description = #{description}," +
           "   </if>" +
           "   <if test='updateTime != null'>" +
           "       update_time = #{updateTime}," +
           "   </if>" +
           "</set>" +
           " where study_id = #{studyId}" +
           "</script>")
   int update(StudyQuality studyQuality);

}
