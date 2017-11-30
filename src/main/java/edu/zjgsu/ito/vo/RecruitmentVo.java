package edu.zjgsu.ito.vo;

public class RecruitmentVo {
        private String Post;
        private String companyName;
        private String Address;
        private String PostTime;
        private int CurrentNumber;
        private int TotalNumber;

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
