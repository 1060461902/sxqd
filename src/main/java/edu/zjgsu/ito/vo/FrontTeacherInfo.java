package edu.zjgsu.ito.vo;

import java.util.List;

public class FrontTeacherInfo {
    private String id;
    private String nickname;
    private String username;
    private Boolean status;
//    private List<String> studentList;
    private String phone;
    private Boolean forbidden;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

//    public List<String> getStudentList() {
//        return studentList;
//    }

//    public void setStudentList(List<String> studentList) {
//        this.studentList = studentList;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public String getForbidden() {
//        return forbidden;
//    }

//    public void setForbidden(String forbidden) {
//        this.forbidden = forbidden;
//    }


    public Boolean getForbidden() {
        return forbidden;
    }

    public void setForbidden(Boolean forbidden) {
        this.forbidden = forbidden;
    }

    @Override
    public String toString() {
        return "FrontTeacherInfo{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", forbidden='" + forbidden + '\'' +
                '}';
    }
}
