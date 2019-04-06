package com.weixin.pojo;

import java.io.Serializable;
import java.util.Date;

public class SugTable implements Serializable{
    private String uid;
    // 上传人的name
    private String name;
    // 意见类型
    private String sortId;
    // 意见内容
    private String content;
    // 创建时间
    private Date created;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId == null ? null : sortId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}