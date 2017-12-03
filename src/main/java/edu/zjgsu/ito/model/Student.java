package edu.zjgsu.ito.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Student implements Serializable {
    private Integer id;

    private Integer userId;

    /**
     * 公司联系人代表公司id学生根据状态表示想去的公司
     */
    private Integer companyId;

    private Integer teacherId;

    /**
     * 实习状态0未实习1申请中2待实习3实习中4已结业
     */
    private String status;

    /**
     * 0默认未归档1归档
     */
    private Boolean zipFile;

    /**
     * 实习岗位
     */
    private String post;

    /**
     * 1存在 0删除 默认1
     */
    private Boolean deleteTag;

    /**
     * 0男1女
     */
    private Boolean sex;

    /**
     * 民族
     */
    private String nation;

    /**
     * 1熟练2一般
     */
    private Boolean english;

    /**
     * 毕业年份
     */
    private Date graduateYear;

    private String major;

    private String clss;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        this.post = post;
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
        this.nation = nation;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss;
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
        Student other = (Student) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getZipFile() == null ? other.getZipFile() == null : this.getZipFile().equals(other.getZipFile()))
            && (this.getPost() == null ? other.getPost() == null : this.getPost().equals(other.getPost()))
            && (this.getDeleteTag() == null ? other.getDeleteTag() == null : this.getDeleteTag().equals(other.getDeleteTag()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getNation() == null ? other.getNation() == null : this.getNation().equals(other.getNation()))
            && (this.getEnglish() == null ? other.getEnglish() == null : this.getEnglish().equals(other.getEnglish()))
            && (this.getGraduateYear() == null ? other.getGraduateYear() == null : this.getGraduateYear().equals(other.getGraduateYear()))
            && (this.getMajor() == null ? other.getMajor() == null : this.getMajor().equals(other.getMajor()))
            && (this.getClss() == null ? other.getClss() == null : this.getClss().equals(other.getClss()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getZipFile() == null) ? 0 : getZipFile().hashCode());
        result = prime * result + ((getPost() == null) ? 0 : getPost().hashCode());
        result = prime * result + ((getDeleteTag() == null) ? 0 : getDeleteTag().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getNation() == null) ? 0 : getNation().hashCode());
        result = prime * result + ((getEnglish() == null) ? 0 : getEnglish().hashCode());
        result = prime * result + ((getGraduateYear() == null) ? 0 : getGraduateYear().hashCode());
        result = prime * result + ((getMajor() == null) ? 0 : getMajor().hashCode());
        result = prime * result + ((getClss() == null) ? 0 : getClss().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", status=").append(status);
        sb.append(", zipFile=").append(zipFile);
        sb.append(", post=").append(post);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", sex=").append(sex);
        sb.append(", nation=").append(nation);
        sb.append(", english=").append(english);
        sb.append(", graduateYear=").append(graduateYear);
        sb.append(", major=").append(major);
        sb.append(", clss=").append(clss);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}