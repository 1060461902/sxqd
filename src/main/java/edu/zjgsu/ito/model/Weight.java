package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Weight implements Serializable {
    private Integer id;

    /**
     * 老师周报权重
     */
    private Float tWeekReport;

    /**
     * 实习小结权重
     */
    private Float tSummary;

    /**
     * 老师实习报告权重
     */
    private Float tFinalReport;

    /**
     * 公司周报权重
     */
    private Float cWeekReport;

    /**
     * 公司考勤权重
     */
    private Float cAttendance;

    /**
     * 老师权重
     */
    private Float teacherWeight;

    /**
     * 公司权重
     */
    private Float companyWeight;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float gettWeekReport() {
        return tWeekReport;
    }

    public void settWeekReport(Float tWeekReport) {
        this.tWeekReport = tWeekReport;
    }

    public Float gettSummary() {
        return tSummary;
    }

    public void settSummary(Float tSummary) {
        this.tSummary = tSummary;
    }

    public Float gettFinalReport() {
        return tFinalReport;
    }

    public void settFinalReport(Float tFinalReport) {
        this.tFinalReport = tFinalReport;
    }

    public Float getcWeekReport() {
        return cWeekReport;
    }

    public void setcWeekReport(Float cWeekReport) {
        this.cWeekReport = cWeekReport;
    }

    public Float getcAttendance() {
        return cAttendance;
    }

    public void setcAttendance(Float cAttendance) {
        this.cAttendance = cAttendance;
    }

    public Float getTeacherWeight() {
        return teacherWeight;
    }

    public void setTeacherWeight(Float teacherWeight) {
        this.teacherWeight = teacherWeight;
    }

    public Float getCompanyWeight() {
        return companyWeight;
    }

    public void setCompanyWeight(Float companyWeight) {
        this.companyWeight = companyWeight;
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
        Weight other = (Weight) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.gettWeekReport() == null ? other.gettWeekReport() == null : this.gettWeekReport().equals(other.gettWeekReport()))
            && (this.gettSummary() == null ? other.gettSummary() == null : this.gettSummary().equals(other.gettSummary()))
            && (this.gettFinalReport() == null ? other.gettFinalReport() == null : this.gettFinalReport().equals(other.gettFinalReport()))
            && (this.getcWeekReport() == null ? other.getcWeekReport() == null : this.getcWeekReport().equals(other.getcWeekReport()))
            && (this.getcAttendance() == null ? other.getcAttendance() == null : this.getcAttendance().equals(other.getcAttendance()))
            && (this.getTeacherWeight() == null ? other.getTeacherWeight() == null : this.getTeacherWeight().equals(other.getTeacherWeight()))
            && (this.getCompanyWeight() == null ? other.getCompanyWeight() == null : this.getCompanyWeight().equals(other.getCompanyWeight()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((gettWeekReport() == null) ? 0 : gettWeekReport().hashCode());
        result = prime * result + ((gettSummary() == null) ? 0 : gettSummary().hashCode());
        result = prime * result + ((gettFinalReport() == null) ? 0 : gettFinalReport().hashCode());
        result = prime * result + ((getcWeekReport() == null) ? 0 : getcWeekReport().hashCode());
        result = prime * result + ((getcAttendance() == null) ? 0 : getcAttendance().hashCode());
        result = prime * result + ((getTeacherWeight() == null) ? 0 : getTeacherWeight().hashCode());
        result = prime * result + ((getCompanyWeight() == null) ? 0 : getCompanyWeight().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tWeekReport=").append(tWeekReport);
        sb.append(", tSummary=").append(tSummary);
        sb.append(", tFinalReport=").append(tFinalReport);
        sb.append(", cWeekReport=").append(cWeekReport);
        sb.append(", cAttendance=").append(cAttendance);
        sb.append(", teacherWeight=").append(teacherWeight);
        sb.append(", companyWeight=").append(companyWeight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}