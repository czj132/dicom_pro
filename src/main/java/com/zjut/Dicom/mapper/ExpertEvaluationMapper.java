package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.ExpertEvaluation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpertEvaluationMapper {


    @Select("select * from expert_evaluation where expert_id = #{expertId} and status = #{status}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="project_id", property = "projectId"),
            @Result(column="patient_id", property = "patientId"),
            @Result(column="study_id", property = "studyId" ),
            @Result(column="expert_id", property = "expertId"),
            @Result(column="expert_type", property = "expertType"),
            @Result(column="evaluate_time", property = "evaluateTime"),
            @Result(column="status", property = "status" ),
            @Result(column="valid_status", property = "validStatus"),
            @Result(column="target_focus", property = "targetFocus"),
            @Result(column = "non_target_focus", property = "nonTargetFocus"),
            @Result(column = "new_focus", property = "newFocus"),
            @Result(column="supplementary", property="supplementary"),
            @Result(column="report",property = "report")
    })
    List<ExpertEvaluation> getByExpertIdAndStatus(Integer expertId, Byte status);

    @Select("select * from expert_evaluation where expert_id = #{expertId} and study_id = #{studyId}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="project_id", property = "projectId"),
            @Result(column="patient_id", property = "patientId"),
            @Result(column="study_id", property = "studyId" ),
            @Result(column="expert_id", property = "expertId"),
            @Result(column="expert_type", property = "expertType"),
            @Result(column="evaluate_time", property = "evaluateTime"),
            @Result(column="status", property = "status" ),
            @Result(column="valid_status", property = "validStatus"),
            @Result(column="target_focus", property = "targetFocus"),
            @Result(column = "non_target_focus", property = "nonTargetFocus"),
            @Result(column = "new_focus", property = "newFocus"),
            @Result(column="supplementary", property="supplementary"),
            @Result(column="report",property = "report")
    })
    ExpertEvaluation getByExpertIdAndStudyId(Integer expertId, Integer studyId);


    @Select("select * from expert_evaluation where study_id = #{studyId} and expert_type = #{expertType}")
    List<ExpertEvaluation> getByStudyIdAndExpertType(Integer studyId, Byte expertType);


    @Insert("insert into expert_evaluation(project_id, patient_id, study_id, expert_id, expert_type," +
            "evaluate_time, status, valid_status, target_focus, non_target_focus, new_focus, supplementary, report)" +
            " values (#{projectId}, #{patientId}, #{studyId}, #{expertId}, #{expertType}, #{evaluateTime}, #{status}," +
            "#{validStatus}, #{targetFocus}, #{nonTargetFocus}, #{newFocus}, #{supplementary}, #{report})")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="project_id", property = "projectId"),
            @Result(column="patient_id", property = "patientId"),
            @Result(column="study_id", property = "studyId" ),
            @Result(column="expert_id", property = "expertId"),
            @Result(column="expert_type", property = "expertType"),
            @Result(column="evaluate_time", property = "evaluateTime"),
            @Result(column="status", property = "status" ),
            @Result(column="valid_status", property = "validStatus"),
            @Result(column="target_focus", property = "targetFocus"),
            @Result(column = "non_target_focus", property = "nonTargetFocus"),
            @Result(column = "new_focus", property = "newFocus"),
            @Result(column="supplementary", property="supplementary"),
            @Result(column="report",property = "report")
    })
    int save(ExpertEvaluation expertEvaluation);

    @Update("<script>" +
            "update expert_evaluation" +
            "<set>" +
            "   <if test = 'projectId != null'>" +
            "       project_id = #{projectId}," +
            "   </if>" +
            "   <if test='patientId != null'>" +
            "       patient_id = #{patientId}," +
            "   </if>" +
            "   <if test='studyId != null'>" +
            "       study_id = #{studyId}," +
            "   </if>" +
            "   <if test='expertId != null'>" +
            "       expert_id = #{expertId}," +
            "   </if>" +
            "   <if test='expertType != null'>" +
            "       expert_type = #{expertType}," +
            "   </if>" +
            "   <if test='evaluateTime != null'>" +
            "       evaluate_time = #{evaluateTime}," +
            "   </if>" +
            "   <if test='status != null'>" +
            "       status = #{status}," +
            "   </if>" +
            "   <if test='validStatus != null'>" +
            "       valid_status = #{validStatus}," +
            "   </if>" +
            "   <if test='targetFocus != null'>" +
            "       target_focus = #{targetFocus}," +
            "   </if>" +
            "   <if test='nonTargetFocus != null'>" +
            "       non_target_focus = #{nonTargetFocus}," +
            "   </if>" +
            "   <if test='newFocus != null'>" +
            "       new_focus = #{newFocus}," +
            "   </if>" +
            "   <if test= 'supplementary != null'>" +
            "       supplementary = #{supplementary}," +
            "   </if>" +
            "   <if test= 'report != null'>" +
            "       report = #{report}," +
            "   </if>" +
            "</set>" +
            " where id = #{id}" +
            "</script>")
    int update(ExpertEvaluation expertEvaluation);

}
