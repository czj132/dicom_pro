package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Patient;
import com.zjut.Dicom.pojo.VO.PatientExpVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper {

    @Select("select * from patient where project_id = #{projectId}")
    @Results(id="patientMap", value = {
            @Result(column="id", property="id"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="clinical_history", property="clinicalHistory"),
            @Result(column="part", property="part"),
            @Result(column="examination_type", property="examinationType"),
            @Result(column="create_time", property="createTime"),
            @Result(column="project_id", property="projectId"),
            @Result(column="hospital_source", property="hospitalSource"),
            @Result(column="comment", property="comment"),
    })
    List<Patient> getByProId(Integer projectId);

    @Insert("insert into patient(sex,age,clinical_history,part,examination_type,create_time,project_id," +
            "hospital_source,comment) values (#{sex},#{age},#{clinicalHistory},#{part},#{examinationType}," +
            "#{createTime},#{projectId},#{hospitalSource},#{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="clinical_history", property="clinicalHistory"),
            @Result(column="part", property="part"),
            @Result(column="examination_type", property="examinationType"),
            @Result(column="create_time", property="createTime"),
            @Result(column="project_id", property="projectId"),
            @Result(column="hospital_source", property="hospitalSource"),
            @Result(column="comment", property="comment"),
    })
    int save(Patient patient);


    @Select("<script>" +
            "SELECT * from patient" +
            "<where>" +
            "   and id = #{id}" +
            "   <if test='project_id != null'>" +
            "       and project_id = #{project_id}" +
            "   </if>" +
            "</where>" +
            "</script>")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="clinical_history", property="clinicalHistory"),
            @Result(column="part", property="part"),
            @Result(column="examination_type", property="examinationType"),
            @Result(column="create_time", property="createTime"),
            @Result(column="project_id", property="projectId"),
            @Result(column="hospital_source", property="hospitalSource"),
            @Result(column="comment", property="comment"),
    })
    Patient getByIdAndProId(@Param("id") Integer id, @Param("project_id") Integer projectId);


    @Delete("delete from patient where id = #{id}")
    int deleteById(Integer id);

    @Update("update patient set sex = #{sex}, age = #{age}, clinical_history = #{clinicalHistory}, part = #{part}, examination_type = " +
            "#{examinationType}, project_id = #{projectId}, hospital_source = #{hospitalSource}, comment = #{comment} where id = #{id}")
    int update(Patient patient);

    @Select("select * from patient where id = #{patientId}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="clinical_history", property="clinicalHistory"),
            @Result(column="part", property="part"),
            @Result(column="examination_type", property="examinationType"),
            @Result(column="create_time", property="createTime"),
            @Result(column="project_id", property="projectId"),
            @Result(column="hospital_source", property="hospitalSource"),
            @Result(column="comment", property="comment"),
    })
    Patient getById(Integer patientId);

    @Select("SELECT DISTINCT " +
            "patient.id patientId, patient.part part, project.disease disease, project.medicine medicine," +
            "patient.examination_type examinationType, patient.create_time createTime, patient.sex sex," +
            "patient.clinical_history clinicalHistory, patient.age age, patient.hospital_source hospitalSource " +
            "from patient " +
            "INNER JOIN project on patient.project_id = project.id " +
            "WHERE patient.id = #{patientId}")
    PatientExpVO getByPatientId(Integer patientId);

}
