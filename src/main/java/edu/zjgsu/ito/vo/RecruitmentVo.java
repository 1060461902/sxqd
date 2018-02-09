package edu.zjgsu.ito.vo;

import java.util.Date;

public class RecruitmentVo {
        private String Post;
        private String companyName;
        private String Address;
        private String PostTime;
        private int CurrentNumber;
        private int TotalNumber;
        private  String contact;
        private int id;
        private int companyId;
        private  boolean forbidden;
        private String ReleaseTime;

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        ReleaseTime = releaseTime;
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostTime() {
        return PostTime;
    }

    public void setPostTime(String postTime) {
        PostTime = postTime;
    }

    public int getCurrentNumber() {
        return CurrentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        CurrentNumber = currentNumber;
    }

    public int getTotalNumber() {
        return TotalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        TotalNumber = totalNumber;
    }
}
