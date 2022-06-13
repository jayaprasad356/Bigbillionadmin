package com.example.bigbillionadmin.model;

public class Withdrawal {
    String id,name,mobile,points,status;
    public Withdrawal(){

    }

    public Withdrawal(String id, String name, String mobile, String points, String status) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.points = points;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
