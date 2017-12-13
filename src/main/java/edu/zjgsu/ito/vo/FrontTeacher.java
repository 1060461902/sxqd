package edu.zjgsu.ito.vo;

import java.util.List;

public class FrontTeacher {
    private Integer id;
    private String nickName;
    private String userName;
    private String phone;
    private Boolean forbidden;

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
