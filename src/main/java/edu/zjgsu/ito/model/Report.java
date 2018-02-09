package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Report implements Serializable {
    private Integer id;

    private Integer studentId;

    private Integer studentRecruitmentId;

    private String title;

    /**
     * 发布时间
     */
    private String publishedDate;

    private String startTime;

    private String endTime;

    private String content;

    /**
     * 存放地址
     */
    private String url;

    private String readoverTime;

    private Integer score;

    private String comment;

    private String cReadoverTime;

    private Integer cScore;

    private String cComment;

    private String week;

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

    public Integer getStudentRecruitmentId() {
        return studentRecruitmentId;
    }

    public void setStudentRecruitmentId(Integer studentRecruitmentId) {
        this.studentRecruitmentId = studentRecruitmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReadoverTime() {
        return readoverTime;
    }

    public void setReadoverTime(String readoverTime) {
        this.readoverTime = readoverTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getcReadoverTime() {
        return cReadoverTime;
    }

    public void setcReadoverTime(String cReadoverTime) {
        this.cReadoverTime = cReadoverTime;
    }

    public Integer getcScore() {
        return cScore;
    }

    public void setcScore(Integer cScore) {
        this.cScore = cScore;
    }

    public String getcComment() {
        return cComment;
    }

    public void setcComment(String cComment) {
        this.cComment = cComment;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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
        Report other = (Report) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getStudentRecruitmentId() == null ? other.getStudentRecruitmentId() == null : this.getStudentRecruitmentId().equals(other.getStudentRecruitmentId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getPublishedDate() == null ? other.getPublishedDate() == null : this.getPublishedDate().equals(other.getPublishedDate()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getReadoverTime() == null ? other.getReadoverTime() == null : this.getReadoverTime().equals(other.getReadoverTime()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getcReadoverTime() == null ? other.getcReadoverTime() == null : this.getcReadoverTime().equals(other.getcReadoverTime()))
            && (this.getcScore() == null ? other.getcScore() == null : this.getcScore().equals(other.getcScore()))
            && (this.getcComment() == null ? other.getcComment() == null : this.getcComment().equals(other.getcComment()))
            && (this.getWeek() == null ? other.getWeek() == null : this.getWeek().equals(other.getWeek()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getStudentRecruitmentId() == null) ? 0 : getStudentRecruitmentId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPublishedDate() == null) ? 0 : getPublishedDate().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getReadoverTime() == null) ? 0 : getReadoverTime().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getcReadoverTime() == null) ? 0 : getcReadoverTime().hashCode());
        result = prime * result + ((getcScore() == null) ? 0 : getcScore().hashCode());
        result = prime * result + ((getcComment() == null) ? 0 : getcComment().hashCode());
        result = prime * result + ((getWeek() == null) ? 0 : getWeek().hashCode());
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
        sb.append(", studentRecruitmentId=").append(studentRecruitmentId);
        sb.append(", title=").append(title);
        sb.append(", publishedDate=").append(publishedDate);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", content=").append(content);
        sb.append(", url=").append(url);
        sb.append(", readoverTime=").append(readoverTime);
        sb.append(", score=").append(score);
        sb.append(", comment=").append(comment);
        sb.append(", cReadoverTime=").append(cReadoverTime);
        sb.append(", cScore=").append(cScore);
        sb.append(", cComment=").append(cComment);
        sb.append(", week=").append(week);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}