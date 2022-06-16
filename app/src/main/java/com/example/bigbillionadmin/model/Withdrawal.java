package com.example.bigbillionadmin.model;

public class Withdrawal {
    String id,name,mobile,points,status,date_created;
    public Withdrawal(){

    }

    public Withdrawal(String id, String name, String mobile, String points, String status, String date_created) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.points = points;
        this.status = status;
        this.date_created = date_created;
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

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
