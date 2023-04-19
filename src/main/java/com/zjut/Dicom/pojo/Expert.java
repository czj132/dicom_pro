package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expert {
    private Integer id;
    private String image;
    private String account;
    private String password;
    private String name;
    private Integer sex;
    private Integer age;
    private String phone;
    private String email;
    private String hospital;
    private String department;
    private Integer title; //职称
    private Integer level; //级别
    private String specialty;  //专业
    private String subSpecialty; //亚专业
    private String description;
    private String eSignature;
    private Byte logicDeletion;
}
