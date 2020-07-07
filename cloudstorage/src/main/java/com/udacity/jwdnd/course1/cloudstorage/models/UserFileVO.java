package com.udacity.jwdnd.course1.cloudstorage.models;

public class UserFileVO {

    private Integer fileId;
    private Integer userId;

    public UserFileVO(Integer fileId, Integer userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
