package com.epam.kuranov.domain.entities.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.Entity;

public class Item extends Entity{
    private int sellerId;
    private String title;
    private String description;
    private double startPrice;
    private double timeLeft;
    private Timestamp startBiddingDate;
    private String buyItNow;
    private double bidIncremment;

    public Item(int itemId, int sellerId, String title, String description, double startPrice,
                double timeLeft, Timestamp startBiddingDate, String buyItNow, double bidIncremment) {
        this.id = itemId;
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.timeLeft = timeLeft;
        this.startBiddingDate = startBiddingDate;
        this.buyItNow = buyItNow;
        this.bidIncremment = bidIncremment;
    }

    public Item(int sellerId, String title, String description, double startPrice,
                double timeLeft, Timestamp startBiddingDate, String buyItNow, double bidIncremment) {
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.timeLeft = timeLeft;
        this.startBiddingDate = startBiddingDate;
        this.buyItNow = buyItNow;
        this.bidIncremment = bidIncremment;
    }
    
    public Item(int sellerId, String title, String description, double startPrice,
            double timeLeft, String buyItNow, double bidIncremment) {
	    this.sellerId = sellerId;
	    this.title = title;
	    this.description = description;
	    this.startPrice = startPrice;
	    this.timeLeft = timeLeft;
	    this.startBiddingDate = new Timestamp(System.currentTimeMillis());
	    this.buyItNow = buyItNow;
	    this.bidIncremment = bidIncremment;
	}
    
    public Item(ResultSet resultSet) throws SQLException{
    	this.id = resultSet.getInt(Columns.Items.ITEM_ID.name());
        this.sellerId = resultSet.getInt(Columns.Items.SELLER_ID.name());
        this.title = resultSet.getString(Columns.Items.TITLE.name());
        this.description = resultSet.getString(Columns.Items.DESCRIPTION.name());
        this.startPrice = resultSet.getDouble(Columns.Items.START_PRICE.name());
        this.timeLeft =resultSet.getDouble(Columns.Items.TIME_LEFT.name());
        this.startBiddingDate = resultSet.getTimestamp(Columns.Items.START_BIDDING_DATE.name());
        this.buyItNow = resultSet.getString(Columns.Items.BUY_IT_NOW.name());
        this.bidIncremment = resultSet.getInt(Columns.Items.BID_INCREMENT.name());
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Timestamp getStartBiddingDate() {
        return startBiddingDate;
    }

    public void setStart_bidding_date(Timestamp startBiddingDate) {
        this.startBiddingDate = startBiddingDate;
    }

    public String getBuyItNow() {
        return buyItNow;
    }

    public void setBuyItNow(String buyItNow) {
        this.buyItNow = buyItNow;
    }

    public double getBidIncremment() {
        return bidIncremment;
    }

    public void setBidIncremment(double bidIncremment) {
        this.bidIncremment = bidIncremment;
    }
    
	@Override
	public String getTableName() {
		return Tables.ITEMS.name();
	}
}
