package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class StudentRecruitment implements Serializable {
    private Integer id;

    private Integer studentId;

    private Integer recruitmentId;

    /**
     * 1审批通过，2审批未通过，3待审批
     */
    private Integer passing;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(Integer recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public Integer getPassing() {
        return passing;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
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
        StudentRecruitment other = (StudentRecruitment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getRecruitmentId() == null ? other.getRecruitmentId() == null : this.getRecruitmentId().equals(other.getRecruitmentId()))
            && (this.getPassing() == null ? other.getPassing() == null : this.getPassing().equals(other.getPassing()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getRecruitmentId() == null) ? 0 : getRecruitmentId().hashCode());
        result = prime * result + ((getPassing() == null) ? 0 : getPassing().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", recruitmentId=").append(recruitmentId);
        sb.append(", passing=").append(passing);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}