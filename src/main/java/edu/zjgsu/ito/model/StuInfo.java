package edu.zjgsu.ito.model;

import java.util.Date;

public class StuInfo {
    private String id;

    private String userid;

    private String companyid;

    private String teacherid;

    private String status;

    private Boolean zipfile;

    private String post;

    private Boolean deletetag;

    private Boolean sex;

    private String nation;

    private Boolean english;

    private Date fallyear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getZipfile() {
        return zipfile;
    }

    public void setZipfile(Boolean zipfile) {
        this.zipfile = zipfile;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Boolean getDeletetag() {
        return deletetag;
    }

    public void setDeletetag(Boolean deletetag) {
        this.deletetag = deletetag;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Boolean getEnglish() {
        return english;
    }

    public void setEnglish(Boolean english) {
        this.english = english;
    }

    public Date getFallyear() {
        return fallyear;
    }

    public void setFallyear(Date fallyear) {
        this.fallyear = fallyear;
    }
}