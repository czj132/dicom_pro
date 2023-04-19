package com.zjut.Dicom.mapper;


import com.zjut.Dicom.pojo.HospitalUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HospitalUserMapper {

    @Insert("insert into hospital_user" +
            "(id,image,account,password,name,sex,age,phone,email,hospital,department,description,e_signature,logic_deletion) " +
            "values(#{id}, #{image},#{account},#{password},#{name},#{sex},#{age},#{phone},#{email},#{hospital},#{department}," +
            "#{description},#{esignature},#{logicDeletion}) ")
    @Results(id="hospitalUserMap", value = {
            @Result(column="id", property="id"),
            @Result(column="image", property="image"),
            @Result(column="account", property="account"),
            @Result(column="password", property="password"),
            @Result(column="name", property="name"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="phone", property="phone"),
            @Result(column="email", property="email"),
            @Result(column="hospital", property="hospital"),
            @Result(column="department", property="department"),
            @Result(column="description", property="description"),
            @Result(column="logic_deletion", property="logicDeletion"),
            @Result(column="e_signature", property="eSignature")
    })
   int save(HospitalUser record);

    @Delete("delete from hospital_user where id = #{id}")
    int deleteById(Integer id);


    @Select("select * from hospital_user where id = #{id}")
    HospitalUser getById(Integer id);

    @Select("select * from hospital_user where account = #{account}")
    HospitalUser getByAccount(String account);

    @Select("select * from hospital_user;")
    List<HospitalUser> getAll();

    @Update("<script>" +
            "update hospital_user" +
            "<set>" +
            "   <if test = 'name != null'>" +
            "       name = #{name}," +
            "   </if>" +
            "   <if test='sex != null'>" +
            "       sex = #{sex}," +
            "   </if>" +
            "   <if test='age != null'>" +
            "       age = #{age}," +
            "   </if>" +
            "   <if test='phone != null'>" +
            "       phone = #{phone}," +
            "   </if>" +
            "   <if test='email != null'>" +
            "       email = #{email}," +
            "   </if>" +
            "   <if test='hospital != null'>" +
            "       hospital = #{hospital}," +
            "   </if>" +
            "   <if test='department != null'>" +
            "       department = #{department}," +
            "   </if>" +
            "   <if test='description != null'>" +
            "       description = #{description}," +
            "   </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    int updateRecord(HospitalUser record);


    @Update("update hospital_user set image = #{image},account = #{account},password = #{password}," +
            "name = #{name},sex = #{sex},age = #{age},phone = #{phone},email = #{email}," +
            "hospital = #{hospital},department = #{department},description = #{description}," +
            "e_signature = #{esignature} where id = #{id}")
    int update(HospitalUser record);

    @Update("update hospital_user set e_signature = #{signature} where id = #{id}")
    int updateSignature(@Param("id") Integer id, @Param("signature") String signature);

}
