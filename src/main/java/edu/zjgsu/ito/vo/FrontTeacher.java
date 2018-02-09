package edu.zjgsu.ito.vo;


import edu.zjgsu.ito.model.Student;

import java.util.List;

public class FrontTeacher {
    private Integer id;
    private String nickName;
    private String userName;

    private String major;
    private String phone;
    private Boolean forbidden;
   private  long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Boolean getForbidden() {
        return forbidden;
    }

    public void setForbidden(Boolean forbidden) {
        this.forbidden = forbidden;
    }

    @Override
    public String toString() {
        return "FrontTeacher{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", forbidden=" + forbidden +
                '}';
    }
}
