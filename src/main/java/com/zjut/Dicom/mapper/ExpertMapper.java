package com.zjut.Dicom.mapper;

import com.zjut.Dicom.pojo.Expert;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpertMapper {

    @Insert("insert into expert(image,account,password,name,sex,age,phone,email,hospital,department,title,'level',specialty," +
            "sub_specialty,description,logic_deletion,e_signature) values (#{image}, #{account},#{password}, #{name},#{sex}, " +
            "#{age}, #{phone}, #{email}, #{hospital}, #{department}, #{title}, #{level}, #{speciality}, #{subSpecialty}, " +
            "#{description}, 0, #{eSignature})")
    int save(Expert expert);

    //查找指定且未逻辑删除的专家
    @Select("select * from expert where id = #{id} and logic_deletion = 0")
    @Results(id="expertMap", value = {
            @Result(column="id", property="id"),
            @Result(column="image", property="image"),
            @Result(column="account", property="account"),
            @Result(column="password", property="password"),
            @Result(column = "name", property = "name"),
            @Result(column="sex", property="sex"),
            @Result(column="age", property="age"),
            @Result(column="phone", property="phone"),
            @Result(column="email", property="email"),
            @Result(column="hospital", property="hospital"),
            @Result(column="department", property="department"),
            @Result(column = "title", property = "title"),
            @Result(column = "level", property = "level"),
            @Result(column="specialty", property="specialty"),
            @Result(column="sub_specialty", property="subSpecialty"),
            @Result(column="description", property="description"),
            @Result(column="logic_deletion", property="logicDeletion"),
            @Result(column="e_signature", property="eSignature")
    })
    Expert getById(Integer id);

    //查找所有未逻辑删除的专家
    @ResultMap(value = "expertMap")
    @Select("select * from expert where logic_deletion = 0")
    List<Expert> getAll();


    @Delete("delete from expert where id = #{id}")
    int deleteById(Integer id);

    /**
     * 逻辑删除专家
     */
    @Update({
            "update expert",
            "set logic_deletion = 1",
            "where id = #{id}"
    })
    int logicDeleteById(Integer id);


    @Update({
            "update `expert`",
            "set image = #{image},",
            "account = #{account},",
            "password = #{password},",
            "name = #{name},",
            "sex = #{sex},",
            "age = #{age},",
            "phone = #{phone},",
            "email = #{email},",
            "hospital = #{hospital},",
            "department = #{department},",
            "title = #{title},",
            "level = #{level},",
            "specialty = #{specialty},",
            "sub_specialty = #{subSpecialty},",
            "description = #{description},",
            "e_signature = #{eSignature}",
            "where id = #{id}"
    })
    int update(Expert expert);

    @ResultMap(value = "expertMap")
    @Select("select * from expert where account = #{account}")
    Expert findByAccount(String account);
}
