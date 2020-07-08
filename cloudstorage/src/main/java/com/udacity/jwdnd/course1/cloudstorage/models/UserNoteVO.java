package com.udacity.jwdnd.course1.cloudstorage.models;

public class UserNoteVO {

    private Integer userId;
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;

    public UserNoteVO() {
    }

    public UserNoteVO(Integer userId, Integer noteId, String noteTitle, String noteDescription) {
        this.userId = userId;
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    @Override
    public String toString() {
        return "UserNoteVO{" +
                "userId=" + userId +
                ", noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                '}';
    }
}
