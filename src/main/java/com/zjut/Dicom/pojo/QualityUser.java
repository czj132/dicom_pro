package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QualityUser {
    private Integer id;

    private String image;

    private String account;

    private String password;

    private String name;

    private Integer sex;

    private Integer age;

    private String phone;

    private String email;

    private String unit;

    private String department;

    private String description;

    private Byte logicDeletion;
}
