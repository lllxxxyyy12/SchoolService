package com.weixin.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private Long uid;

    private String openid;
    // 用户的姓名
    private String name;
    // 用户的性别
    private String sex;
    // 用户头像的绝对路径
    private String pic;
    // 用户的卡号
    private Long card;
    // 用户的电话号码
    private Long phoneNumber;
    // 用户的学院
    private String college;
    // 用户的专业
    private String domain;
    // 用户的班级
    private String classId;
    // 用户的宿舍号
    private String dormitory;
    
    private Date created;

    private Date updated;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory == null ? null : dormitory.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}