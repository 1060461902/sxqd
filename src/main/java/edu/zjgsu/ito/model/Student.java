package edu.zjgsu.ito.model;

import java.util.Date;

public class Student {
    private String id;

    private String userId;

    private String companyId;

    private String teacherId;

    private String status;

    private Boolean zipFile;

    private String post;

    private Boolean deleteTag;

    private Boolean sex;

    private String nation;

    private Boolean english;

    private Date graduateYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getZipFile() {
        return zipFile;
    }

    public void setZipFile(Boolean zipFile) {
        this.zipFile = zipFile;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Boolean getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(Boolean deleteTag) {
        this.deleteTag = deleteTag;
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

    public Date getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(Date graduateYear) {
        this.graduateYear = graduateYear;
    }
}