package com.weixin.pojo;

import java.io.Serializable;

public class SugSort implements Serializable {
    private Long id;

    private Long parentId;

    private String sugName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSugName() {
        return sugName;
    }

    public void setSugName(String sugName) {
        this.sugName = sugName == null ? null : sugName.trim();
    }
}