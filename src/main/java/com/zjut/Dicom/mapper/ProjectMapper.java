package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Project;
import com.zjut.Dicom.pojo.VO.PatientStudyVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("insert into project(name,status,create_time," +
            "update_time,description,pharmaceutical,medicine, disease,view_total, logic_deletion) values" +
            " (#{name}, #{status},#{createTime},#{updateTime},#{description},#{pharmaceutical}," +
            "#{medicine}, #{disease},#{viewTotal},#{logicDeletion})")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column="status", property="status"),
            @Result(column="create_time", property="createTime"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="description", property="description"),
            @Result(column="pharmaceutical", property="pharmaceutical"),
            @Result(column="medicine", property="medicine"),
            @Result(column="disease", property="disease"),
            @Result(column="view_total", property="viewTotal"),
            @Result(column="logic_deletion", property="logicDeletion")
    })
    int save(Project project);

    @Delete("delete from project where id = #{id}")
    int deleteById(Integer id);

    /**
     * 只编辑项目名称，药厂，药物，病种，项目描述
     * @param project
     * @return
     */
    @Update("<script>" +
            "update project" +
            "<set>" +
            "   <if test = 'name != null'>" +
            "       name = #{name}," +
            "   </if>" +
            "   <if test='pharmaceutical != null'>" +
            "       pharmaceutical = #{pharmaceutical}," +
            "   </if>" +
            "   <if test='medicine != null'>" +
            "       medicine = #{medicine}," +
            "   </if>" +
            "   <if test='disease != null'>" +
            "       disease = #{disease}," +
            "   </if>" +
            "   <if test='description != null'>" +
            "       description = #{description}," +
            "   </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    int update(Project project);


    @Select("select * from project where id = #{id}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column="status", property="status"),
            @Result(column="create_time", property="createTime"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="description", property="description"),
            @Result(column="pharmaceutical", property="pharmaceutical"),
            @Result(column="medicine", property="medicine"),
            @Result(column="disease", property="disease"),
            @Result(column="view_total", property="viewTotal"),
            @Result(column="logic_deletion", property="logicDeletion")
    })
    Project getById(Integer id);

    @Select("select * from project")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column="status", property="status"),
            @Result(column="create_time", property="createTime"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="description", property="description"),
            @Result(column="pharmaceutical", property="pharmaceutical"),
            @Result(column="medicine", property="medicine"),
            @Result(column="disease", property="disease"),
            @Result(column="view_total", property="viewTotal"),
            @Result(column="logic_deletion", property="logicDeletion")
    })
    List<Project> getAll();

    @Select("select study.id as studyId, study.upload_time as uploadTime, study.plan_time as planTime," +
            "project.name as proName, patient.id as patientId, " +
            "patient.hospital_source as hospitalSource, study_quality.status as status, " +
            "study_quality.description as description from study INNER JOIN project ON study.project_id = project.id" +
            " Inner join patient on study.patient_id = patient.id inner join study_quality on study.id = study_quality.study_id" +
            " where study.id = #{studyId}")
    PatientStudyVO getVoByStuId(Integer studyId);

}
