package com.soulsight.redlightgreenlightadmin.Modle;

import java.io.Serializable;

public class DepositModle implements Serializable {
    public DepositModle() {
    }

    private String id,userID,ss,time,status;

    public DepositModle(String id, String userID, String ss, String time, String status) {
        this.id = id;
        this.userID = userID;
        this.ss = ss;
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
