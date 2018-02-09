package edu.zjgsu.ito.vo;

public class ApprovalVo {
    Integer[] id;
    Boolean passFlag;
    String meg;

    public String getMeg() {
        return meg;
    }

    public void setMeg(String meg) {
        this.meg = meg;
    }

    public Integer[] getId() {
        return id;
    }

    public void setId(Integer[] id) {
        this.id = id;
    }

    public Boolean getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(Boolean passFlag) {
        this.passFlag = passFlag;
    }
}
