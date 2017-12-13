package edu.zjgsu.ito.vo;

public class CompanyVo {
    Integer id;
    String companyName;
    long nowIntership;
    long allIntership;
    long studentNumber;
    String contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getNowIntership() {
        return nowIntership;
    }

    public void setNowIntership(long nowIntership) {
        this.nowIntership = nowIntership;
    }

    public long getAllIntership() {
        return allIntership;
    }

    public void setAllIntership(long allIntership) {
        this.allIntership = allIntership;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
