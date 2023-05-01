package com.soulsight.redlightgreenlightadmin.Modle;

import java.io.Serializable;

public class DepositModle implements Serializable {
    public DepositModle() {
    }

    private String id,userID,ss,time,status,amunt,tid;

    public DepositModle(String id, String userID, String ss, String time, String status, String amunt, String tid) {
        this.id = id;
        this.userID = userID;
        this.ss = ss;
        this.time = time;
        this.status = status;
        this.amunt=amunt;
        this.tid=tid;
    }

    public String getAmunt() {
        return amunt;
    }

    public void setAmunt(String amunt) {
        this.amunt = amunt;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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
