package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class CheckNum implements Serializable {
    private Integer id;

    private Integer clockinnum;

    private Integer clockoutnum;

    private String day;

    private Integer lastdayattendnum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClockinnum() {
        return clockinnum;
    }

    public void setClockinnum(Integer clockinnum) {
        this.clockinnum = clockinnum;
    }

    public Integer getClockoutnum() {
        return clockoutnum;
    }

    public void setClockoutnum(Integer clockoutnum) {
        this.clockoutnum = clockoutnum;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getLastdayattendnum() {
        return lastdayattendnum;
    }

    public void setLastdayattendnum(Integer lastdayattendnum) {
        this.lastdayattendnum = lastdayattendnum;
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
            && (this.getClockinnum() == null ? other.getClockinnum() == null : this.getClockinnum().equals(other.getClockinnum()))
            && (this.getClockoutnum() == null ? other.getClockoutnum() == null : this.getClockoutnum().equals(other.getClockoutnum()))
            && (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getLastdayattendnum() == null ? other.getLastdayattendnum() == null : this.getLastdayattendnum().equals(other.getLastdayattendnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClockinnum() == null) ? 0 : getClockinnum().hashCode());
        result = prime * result + ((getClockoutnum() == null) ? 0 : getClockoutnum().hashCode());
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getLastdayattendnum() == null) ? 0 : getLastdayattendnum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clockinnum=").append(clockinnum);
        sb.append(", clockoutnum=").append(clockoutnum);
        sb.append(", day=").append(day);
        sb.append(", lastdayattendnum=").append(lastdayattendnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}