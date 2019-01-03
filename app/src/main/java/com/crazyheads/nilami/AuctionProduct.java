package com.crazyheads.nilami;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Calendar;

@IgnoreExtraProperties
public class AuctionProduct {

    private String productName;
    private String productDesc;
    private int minBid;
    private String ownwer;
    private String ownerEmail;
    private String highestBidder;
    private String highestBidderEmail;
    private int currentBid;
    private Calendar calendar;

    public AuctionProduct(String productName, String productDesc, int minBid, String ownwer, String ownerEmail, String highestBidder, String highestBidderEmail, int currentBid) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.minBid = minBid;
        this.ownwer = ownwer;
        this.ownerEmail = ownerEmail;
        this.highestBidder = highestBidder;
        this.highestBidderEmail = highestBidderEmail;
        this.currentBid = currentBid;
    }

    public AuctionProduct(){}

    public void UpdateBid(String highestBidder, String highestBidderEmail, int currentBid){
        this.highestBidder = highestBidder;
        this.highestBidderEmail = highestBidderEmail;
        this.currentBid = currentBid;
    }


    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public int getMinBid() {
        return minBid;
    }

    public String getOwnwer() {
        return ownwer;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public String getHighestBidderEmail() {
        return highestBidderEmail;
    }

    public int getCurrentBid() {
        return currentBid;
    }
}
