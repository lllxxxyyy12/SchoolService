package com.weixin.pojo;

import java.io.Serializable;
import java.util.Date;

public class InContent implements Serializable{
    private Long uid;

    private Long categoryId;

    private String title;

    private String titleDes;

    private String pic;

    private Date created;

    private Date updated;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleDes() {
        return titleDes;
    }

    public void setTitleDes(String titleDes) {
        this.titleDes = titleDes == null ? null : titleDes.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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