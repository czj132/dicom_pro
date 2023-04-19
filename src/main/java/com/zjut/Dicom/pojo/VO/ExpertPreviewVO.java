package com.zjut.Dicom.pojo.VO;

import lombok.Data;

/**
 * 管理员端：专家管理中的专家预览视图
 * 专家端：去除密码，作为专家个人信息返回
 */
@Data
public class ExpertPreviewVO {
    private Integer id;

    private String account;

    private String name;

    private Byte sex;

    private Byte age;

    private String phone;

    private String email;

    private String hospital;

    private String department;

    private Byte level;

    private String title;

    private String specialty; //专业

    private String subSpecialty; //亚专业

    private String eSignature;

    private String description;
}
