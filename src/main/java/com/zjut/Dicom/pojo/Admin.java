package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Admin {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Byte logicDeletion;
}
