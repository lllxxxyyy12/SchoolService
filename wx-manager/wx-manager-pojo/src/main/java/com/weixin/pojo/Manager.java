package com.weixin.pojo;

import java.util.Date;

public class Manager {
    private Long uid;

    private String openid;

    private String name;

    private String sex;

    private String nation;

    private Long phoneNumber;

    private String manLocationId;

    private String manLocation;

    private Date cerated;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getManLocationId() {
        return manLocationId;
    }

    public void setManLocationId(String manLocationId) {
        this.manLocationId = manLocationId == null ? null : manLocationId.trim();
    }

    public String getManLocation() {
        return manLocation;
    }

    public void setManLocation(String manLocation) {
        this.manLocation = manLocation == null ? null : manLocation.trim();
    }

    public Date getCerated() {
        return cerated;
    }

    public void setCerated(Date cerated) {
        this.cerated = cerated;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}