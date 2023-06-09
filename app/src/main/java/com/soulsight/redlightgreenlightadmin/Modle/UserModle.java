package com.soulsight.redlightgreenlightadmin.Modle;

import java.io.Serializable;

public class UserModle implements Serializable {
    public UserModle() {
    }
    private String id,name,email,pass;
    private double balance,totalWithdraw=0;

    public UserModle(String id, String name, String email, String pass, double balance, double totalWithdraw) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.balance = balance;
        this.totalWithdraw=totalWithdraw;
    }

    public double getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(double totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
