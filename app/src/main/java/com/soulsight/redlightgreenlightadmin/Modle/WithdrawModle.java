package com.soulsight.redlightgreenlightadmin.Modle;

import java.io.Serializable;

public class WithdrawModle implements Serializable {
    public WithdrawModle() {
    }

    private String id,userID,amount,time,status,accountInfo;

    public WithdrawModle(String id, String userID, String amount, String time, String status, String accountInfo) {
        this.id = id;
        this.userID = userID;
        this.amount = amount;
        this.time = time;
        this.status = status;
        this.accountInfo = accountInfo;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }
}
