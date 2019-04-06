package com.weixin.pojo;

import java.io.Serializable;
import java.util.Date;

public class MaintenanceTable implements Serializable {
    private String uid;

    private String name;

    private String status;

    private Long card;

    private String locationTitle;

    private Long locationRoomId;

    private String content;

    private String pic;

    private Date created;

    private Date updated;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle == null ? null : locationTitle.trim();
    }

    public Long getLocationRoomId() {
        return locationRoomId;
    }

    public void setLocationRoomId(Long locationRoomId) {
        this.locationRoomId = locationRoomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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