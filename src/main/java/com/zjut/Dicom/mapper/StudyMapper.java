package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.VO.StudyQualityVO;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface StudyMapper {

    @Insert("insert into study(patient_id,project_id,viewpoint,status,upload_time, " +
            "plan_time,view_time) values (#{patientId},#{projectId},#{viewpoint}," +
            "#{status},#{uploadTime},#{planTime},#{viewTime})")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="patient_id", property="patientId"),
            @Result(column="project_id", property="projectId"),
            @Result(column="viewpoint", property="viewpoint"),
            @Result(column="status", property="status"),
            @Result(column="upload_time", property="uploadTime"),
            @Result(column="plan_time", property="planTime"),
            @Result(column="view_time", property="viewTime"),
    })
    int save(Study study);

    @Delete("delete from study where id = #{id}")
    int deleteById(Integer id);

    @Update("<script>" +
            "update study" +
            "<set>" +
            "   <if test = 'projectId != null'>" +
            "       project_id = #{projectId}," +
            "   </if>" +
            "   <if test='patientId != null'>" +
            "       patient_id = #{patientId}," +
            "   </if>" +
            "   <if test='viewpoint != null'>" +
            "       viewpoint = #{viewpoint}," +
            "   </if>" +
            "   <if test='status != null'>" +
            "       status = #{status}," +
            "   </if>" +
            "   <if test='uploadTime != null'>" +
            "       upload_time = #{uploadTime}," +
            "   </if>" +
            "   <if test='planTime != null'>" +
            "       plan_time = #{planTime}," +
            "   </if>" +
            "   <if test='viewTime != null'>" +
            "       view_time = #{viewTime}," +
            "   </if>" +
            "</set>" +
            " where id = #{id}" +
            "</script>")
    int update(Study study);

    @Select("select * from study where id = #{id}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="patient_id", property="patientId"),
            @Result(column="project_id", property="projectId"),
            @Result(column="viewpoint", property="viewpoint"),
            @Result(column="status", property="status"),
            @Result(column="upload_time", property="uploadTime"),
            @Result(column="plan_time", property="planTime"),
            @Result(column="view_time", property="viewTime"),
    })
    Study getById(Integer id);

    @Select("select * from study where patient_id = #{patientId}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="patient_id", property="patientId"),
            @Result(column="project_id", property="projectId"),
            @Result(column="viewpoint", property="viewpoint"),
            @Result(column="status", property="status"),
            @Result(column="upload_time", property="uploadTime"),
            @Result(column="plan_time", property="planTime"),
            @Result(column="view_time", property="viewTime"),
    })
    List<Study> getByPatientId(Integer patientId);


    @Select("select  DISTINCT study.id as studyId,project.name as projectName,study.project_id as projectId," +
            "study.patient_id as patientId,project.view_total as viewTotal, patient.hospital_source as hospitalSource," +
            "study.status as status,study.upload_time as uploadTime,patient.part as part," +
            "study.viewpoint as viewpoint,study.plan_time as planTime,patient.examination_type as examinationType " +
            "from study" +
            " INNER JOIN project ON study.project_id = project.id" +
            " INNER JOIN patient ON study.patient_id = patient.id" +
            " INNER JOIN study_quality ON study.id = study_quality.study_id" +
            " where study.id = #{studyId}")
    List<StudyQualityVO> getStudyQualityVO(Integer studyId);
}
