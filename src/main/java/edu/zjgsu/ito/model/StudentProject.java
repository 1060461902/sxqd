package edu.zjgsu.ito.model;

import java.io.Serializable;

/**
 * @author 
 */
public class StudentProject implements Serializable {
    private Integer id;

    private String projectName;

    private String identity;

    private String startTime;

    private String endTime;

    private String instruction;

    private Integer studentId;

    private Boolean deleteTag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Boolean getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(Boolean deleteTag) {
        this.deleteTag = deleteTag;
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
        StudentProject other = (StudentProject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getIdentity() == null ? other.getIdentity() == null : this.getIdentity().equals(other.getIdentity()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getInstruction() == null ? other.getInstruction() == null : this.getInstruction().equals(other.getInstruction()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getDeleteTag() == null ? other.getDeleteTag() == null : this.getDeleteTag().equals(other.getDeleteTag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getIdentity() == null) ? 0 : getIdentity().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getInstruction() == null) ? 0 : getInstruction().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getDeleteTag() == null) ? 0 : getDeleteTag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectName=").append(projectName);
        sb.append(", identity=").append(identity);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", instruction=").append(instruction);
        sb.append(", studentId=").append(studentId);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}