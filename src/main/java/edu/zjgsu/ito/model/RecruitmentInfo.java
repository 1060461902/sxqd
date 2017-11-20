package edu.zjgsu.ito.model;

import java.util.Date;

public class RecruitmentInfo {
    private String id;

    private String companyid;

    private String post;

    private Integer currentnumber;

    private Integer totalnumber;

    private String postinfo;

    private Boolean checked;

    private Boolean pass;

    private Boolean delete;

    private String address;

    private String skillrequirement;

    private Date posttime;

    private Boolean remove;

    private Boolean forbidden;

    private String salary;

    private String contact;

    private String phone;

    private String email;

    private Boolean recruitment;

    private String userid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Integer getCurrentnumber() {
        return currentnumber;
    }

    public void setCurrentnumber(Integer currentnumber) {
        this.currentnumber = currentnumber;
    }

    public Integer getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(Integer totalnumber) {
        this.totalnumber = totalnumber;
    }

    public String getPostinfo() {
        return postinfo;
    }

    public void setPostinfo(String postinfo) {
        this.postinfo = postinfo == null ? null : postinfo.trim();
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

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSkillrequirement() {
        return skillrequirement;
    }

    public void setSkillrequirement(String skillrequirement) {
        this.skillrequirement = skillrequirement == null ? null : skillrequirement.trim();
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}