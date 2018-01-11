package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class TableData implements Serializable {
    private Integer id;

    private String times;

    private Long companyNum;

    private Long currentRecruitNum;

    private Long currentStudentNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public TableData() {

    }

    public TableData(String times, Long companyNum, Long currentRecruitNum, Long currentStudentNum) {
        this.times = times;
        this.companyNum = companyNum;
        this.currentRecruitNum = currentRecruitNum;
        this.currentStudentNum = currentStudentNum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Long getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Long companyNum) {
        this.companyNum = companyNum;
    }

    public Long getCurrentRecruitNum() {
        return currentRecruitNum;
    }

    public void setCurrentRecruitNum(Long currentRecruitNum) {
        this.currentRecruitNum = currentRecruitNum;
    }

    public Long getCurrentStudentNum() {
        return currentStudentNum;
    }

    public void setCurrentStudentNum(Long currentStudentNum) {
        this.currentStudentNum = currentStudentNum;
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
        TableData other = (TableData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()))
            && (this.getCompanyNum() == null ? other.getCompanyNum() == null : this.getCompanyNum().equals(other.getCompanyNum()))
            && (this.getCurrentRecruitNum() == null ? other.getCurrentRecruitNum() == null : this.getCurrentRecruitNum().equals(other.getCurrentRecruitNum()))
            && (this.getCurrentStudentNum() == null ? other.getCurrentStudentNum() == null : this.getCurrentStudentNum().equals(other.getCurrentStudentNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        result = prime * result + ((getCompanyNum() == null) ? 0 : getCompanyNum().hashCode());
        result = prime * result + ((getCurrentRecruitNum() == null) ? 0 : getCurrentRecruitNum().hashCode());
        result = prime * result + ((getCurrentStudentNum() == null) ? 0 : getCurrentStudentNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", times=").append(times);
        sb.append(", companyNum=").append(companyNum);
        sb.append(", currentRecruitNum=").append(currentRecruitNum);
        sb.append(", currentStudentNum=").append(currentStudentNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}