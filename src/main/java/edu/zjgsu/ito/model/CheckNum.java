package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class CheckNum implements Serializable {
    private Integer id;

    private Integer startCheckNum;

    private Integer endCheckNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartCheckNum() {
        return startCheckNum;
    }

    public void setStartCheckNum(Integer startCheckNum) {
        this.startCheckNum = startCheckNum;
    }

    public Integer getEndCheckNum() {
        return endCheckNum;
    }

    public void setEndCheckNum(Integer endCheckNum) {
        this.endCheckNum = endCheckNum;
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
        CheckNum other = (CheckNum) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStartCheckNum() == null ? other.getStartCheckNum() == null : this.getStartCheckNum().equals(other.getStartCheckNum()))
            && (this.getEndCheckNum() == null ? other.getEndCheckNum() == null : this.getEndCheckNum().equals(other.getEndCheckNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStartCheckNum() == null) ? 0 : getStartCheckNum().hashCode());
        result = prime * result + ((getEndCheckNum() == null) ? 0 : getEndCheckNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", startCheckNum=").append(startCheckNum);
        sb.append(", endCheckNum=").append(endCheckNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}