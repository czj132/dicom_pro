package com.zjut.Dicom.pojo;

public class HospitalUser {
    private Integer id;
    private String image;
    //登录名
    private String account;
    private String password;
    //真实名字
    private String name;
    private Integer sex;
    private Integer age;
    private String phone;
    private String email;
    private String hospital;
    private String department;
    private String description;
    private String esignature;
    private Byte logicDeletion;

    @Override
    public String toString() {
        return "HospitalUser{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", hospital='" + hospital + '\'' +
                ", department='" + department + '\'' +
                ", description='" + description + '\'' +
                ", esignature='" + esignature + '\'' +
                ", logicDeletion=" + logicDeletion +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEsignature() {
        return esignature;
    }

    public void setEsignature(String esignature) {
        this.esignature = esignature;
    }

    public Byte getLogicDeletion() {
        return logicDeletion;
    }

    public void setLogicDeletion(Byte logicDeletion) {
        this.logicDeletion = logicDeletion;
    }

}
