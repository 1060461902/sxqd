package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Score implements Serializable {
    private Integer id;

    private Integer studentId;

    private Float tWeekReport;

    private Float tSummary;

    private Float tFinalReport;

    private Float additionalScore;

    private Float cWeekReport;

    private Float cAttendance;

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

    public Float getAdditionalScore() {
        return additionalScore;
    }

    public void setAdditionalScore(Float additionalScore) {
        this.additionalScore = additionalScore;
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
        Score other = (Score) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.gettWeekReport() == null ? other.gettWeekReport() == null : this.gettWeekReport().equals(other.gettWeekReport()))
            && (this.gettSummary() == null ? other.gettSummary() == null : this.gettSummary().equals(other.gettSummary()))
            && (this.gettFinalReport() == null ? other.gettFinalReport() == null : this.gettFinalReport().equals(other.gettFinalReport()))
            && (this.getAdditionalScore() == null ? other.getAdditionalScore() == null : this.getAdditionalScore().equals(other.getAdditionalScore()))
            && (this.getcWeekReport() == null ? other.getcWeekReport() == null : this.getcWeekReport().equals(other.getcWeekReport()))
            && (this.getcAttendance() == null ? other.getcAttendance() == null : this.getcAttendance().equals(other.getcAttendance()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((gettWeekReport() == null) ? 0 : gettWeekReport().hashCode());
        result = prime * result + ((gettSummary() == null) ? 0 : gettSummary().hashCode());
        result = prime * result + ((gettFinalReport() == null) ? 0 : gettFinalReport().hashCode());
        result = prime * result + ((getAdditionalScore() == null) ? 0 : getAdditionalScore().hashCode());
        result = prime * result + ((getcWeekReport() == null) ? 0 : getcWeekReport().hashCode());
        result = prime * result + ((getcAttendance() == null) ? 0 : getcAttendance().hashCode());
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
        sb.append(", tWeekReport=").append(tWeekReport);
        sb.append(", tSummary=").append(tSummary);
        sb.append(", tFinalReport=").append(tFinalReport);
        sb.append(", additionalScore=").append(additionalScore);
        sb.append(", cWeekReport=").append(cWeekReport);
        sb.append(", cAttendance=").append(cAttendance);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}