package com.example.bigbillionadmin.model;

public class Users {
    String id,name,mobile,points,earn;
    public Users(){

    }

    public Users(String id, String name, String mobile, String points, String earn) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.points = points;
        this.earn = earn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getEarn() {
        return earn;
    }

    public void setEarn(String earn) {
        this.earn = earn;
    }
}
