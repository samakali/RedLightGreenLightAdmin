package com.soulsight.redlightgreenlightadmin.Modle;

import java.io.Serializable;

public class BetModle implements Serializable {

    public BetModle() {
    }
    private String id,userID;
    private boolean betOnGreen;
    private double betPrice;

    public BetModle(String id, String userID, boolean betOnGreen, double betPrice) {
        this.id = id;
        this.userID = userID;
        this.betOnGreen = betOnGreen;
        this.betPrice = betPrice;
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

    public boolean isBetOnGreen() {
        return betOnGreen;
    }

    public void setBetOnGreen(boolean betOnGreen) {
        this.betOnGreen = betOnGreen;
    }

    public double getBetPrice() {
        return betPrice;
    }

    public void setBetPrice(double betPrice) {
        this.betPrice = betPrice;
    }
}
