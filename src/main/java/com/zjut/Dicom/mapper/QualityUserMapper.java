package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.QualityUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QualityUserMapper {

    @Insert("insert into quality_user(image,account,password,name,sex,age,phone,email,unit,department," +
            "description,logic_deletion) values(" +
            "#{image}, #{account}, #{password}, #{name}, #{sex}, #{age}, #{phone}, " +
            "#{email}, #{unit}, #{department}, #{description}, #{logicDeletion) ")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="image", property="image"),
            @Result(column="account", property="account"),
            @Result(column="password", property="password"),
            @Result(column="name", property="name"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="phone", property="phone"),
            @Result(column="email", property="email"),
            @Result(column="unit", property="unit"),
            @Result(column="department", property="department"),
            @Result(column="description", property="description"),
            @Result(column="logic_deletion", property="logicDeletion")
    })
    int save(QualityUser report);

    @Select("select * from quality_user")
    List<QualityUser> getAll();

    @Select("select * from quality_user where id = #{id}")
    QualityUser getById(Integer id);

    @Delete("delete from quality_user where id = #{id}")
    int deleteById(Integer id);

    @Update("<script>" +
            "update quality_user" +
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
            "   <if test='unit != null'>" +
            "       unit = #{unit}," +
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
    int updateRecord(QualityUser record);

    @Update("update quality_user set account = #{account},password = #{password}, name = #{name}, sex = #{sex}," +
            "age = #{age}, phone = #{phone}, email = #{email}, unit = #{unit}, department = #{department}," +
            "description = #{description} where id = #{id}")
    int update(QualityUser record);
}
