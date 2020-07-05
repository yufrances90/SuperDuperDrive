package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {

    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;

    public Note(Integer noteid, String notetitle, String notedescription) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
    }

    public Note(Integer noteId, String notetitle, String noteDescription, Integer userId) {
        this.noteid = noteId;
        this.notetitle = notetitle;
        this.notedescription = noteDescription;
        this.userid = userId;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
