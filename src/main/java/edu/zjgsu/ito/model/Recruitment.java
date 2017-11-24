package edu.zjgsu.ito.model;

import java.util.Date;

public class Recruitment {
    private String id;

    private String companyId;

    private String post;

    private Integer currentNumber;

    private Integer totalNumber;

    private String postInfo;

    private Boolean checked;

    private Boolean pass;

    private Boolean deleteTag;

    private String address;

    private String skillRequire;

    private Date postTime;

    private Boolean remove;

    private Boolean forbidden;

    private String salary;

    private String contact;

    private String phone;

    private String email;

    private Boolean recruitment;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo == null ? null : postInfo.trim();
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public Boolean getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(Boolean deleteTag) {
        this.deleteTag = deleteTag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSkillRequire() {
        return skillRequire;
    }

    public void setSkillRequire(String skillRequire) {
        this.skillRequire = skillRequire == null ? null : skillRequire.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public Boolean getForbidden() {
        return forbidden;
    }

    public void setForbidden(Boolean forbidden) {
        this.forbidden = forbidden;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Boolean recruitment) {
        this.recruitment = recruitment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}