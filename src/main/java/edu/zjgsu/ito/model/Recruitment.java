package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Recruitment implements Serializable {
    private String id;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 招聘岗位
     */
    private String post;

    /**
     * 现在招收人数
     */
    private Integer currentNumber;

    /**
     * 总招募人数
     */
    private Integer totalNumber;

    /**
     * 职位信息
     */
    private String postInfo;

    /**
     * 0代表未审批，1代表已审批
     */
    private Boolean checked;

    /**
     * 0代表审批未通过，1代表审批通过
     */
    private Boolean pass;

    /**
     * 标记删除，0-false，1-true
     */
    private Boolean deleteTag;

    /**
     * 地址
     */
    private String address;

    /**
     * 技能要求
     */
    private String skillRequire;

    /**
     * 招聘时间
     */
    private String postTime;

    /**
     * 是否被撤下0-false,1-true
     */
    private Boolean remove;

    /**
     * 0-没有被禁用，1-禁用
     */
    private Boolean forbidden;

    /**
     * 薪资待遇
     */
    private String salary;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 招聘状态，1-招聘中，0-招聘结束
     */
    private Boolean recruitment;

    private String userId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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
        this.postInfo = postInfo;
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
        this.address = address;
    }

    public String getSkillRequire() {
        return skillRequire;
    }

    public void setSkillRequire(String skillRequire) {
        this.skillRequire = skillRequire;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
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
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.userId = userId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Recruitment other = (Recruitment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getPost() == null ? other.getPost() == null : this.getPost().equals(other.getPost()))
            && (this.getCurrentNumber() == null ? other.getCurrentNumber() == null : this.getCurrentNumber().equals(other.getCurrentNumber()))
            && (this.getTotalNumber() == null ? other.getTotalNumber() == null : this.getTotalNumber().equals(other.getTotalNumber()))
            && (this.getPostInfo() == null ? other.getPostInfo() == null : this.getPostInfo().equals(other.getPostInfo()))
            && (this.getChecked() == null ? other.getChecked() == null : this.getChecked().equals(other.getChecked()))
            && (this.getPass() == null ? other.getPass() == null : this.getPass().equals(other.getPass()))
            && (this.getDeleteTag() == null ? other.getDeleteTag() == null : this.getDeleteTag().equals(other.getDeleteTag()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getSkillRequire() == null ? other.getSkillRequire() == null : this.getSkillRequire().equals(other.getSkillRequire()))
            && (this.getPostTime() == null ? other.getPostTime() == null : this.getPostTime().equals(other.getPostTime()))
            && (this.getRemove() == null ? other.getRemove() == null : this.getRemove().equals(other.getRemove()))
            && (this.getForbidden() == null ? other.getForbidden() == null : this.getForbidden().equals(other.getForbidden()))
            && (this.getSalary() == null ? other.getSalary() == null : this.getSalary().equals(other.getSalary()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getRecruitment() == null ? other.getRecruitment() == null : this.getRecruitment().equals(other.getRecruitment()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getPost() == null) ? 0 : getPost().hashCode());
        result = prime * result + ((getCurrentNumber() == null) ? 0 : getCurrentNumber().hashCode());
        result = prime * result + ((getTotalNumber() == null) ? 0 : getTotalNumber().hashCode());
        result = prime * result + ((getPostInfo() == null) ? 0 : getPostInfo().hashCode());
        result = prime * result + ((getChecked() == null) ? 0 : getChecked().hashCode());
        result = prime * result + ((getPass() == null) ? 0 : getPass().hashCode());
        result = prime * result + ((getDeleteTag() == null) ? 0 : getDeleteTag().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getSkillRequire() == null) ? 0 : getSkillRequire().hashCode());
        result = prime * result + ((getPostTime() == null) ? 0 : getPostTime().hashCode());
        result = prime * result + ((getRemove() == null) ? 0 : getRemove().hashCode());
        result = prime * result + ((getForbidden() == null) ? 0 : getForbidden().hashCode());
        result = prime * result + ((getSalary() == null) ? 0 : getSalary().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getRecruitment() == null) ? 0 : getRecruitment().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", post=").append(post);
        sb.append(", currentNumber=").append(currentNumber);
        sb.append(", totalNumber=").append(totalNumber);
        sb.append(", postInfo=").append(postInfo);
        sb.append(", checked=").append(checked);
        sb.append(", pass=").append(pass);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", address=").append(address);
        sb.append(", skillRequire=").append(skillRequire);
        sb.append(", postTime=").append(postTime);
        sb.append(", remove=").append(remove);
        sb.append(", forbidden=").append(forbidden);
        sb.append(", salary=").append(salary);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", recruitment=").append(recruitment);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}