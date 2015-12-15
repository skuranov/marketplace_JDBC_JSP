package com.epam.kuranov.domain.entities.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.utils.TimeUtils;

public class ComplexEntity {
	private int bidderId;
    private int itemId;
    private double bidValue;
    private int sellerId;
    private String title;
    private String description;
    private double startPrice;
    private double timeLeft;
    private Timestamp startBiddingDate;
    private String buyItNow;
    private double bidIncremment;
	private String fullName;
	private String billingAdress;
	private String login;
	private String password;
	private Date stopDate;
	
	public ComplexEntity(ResultSet resultSet) throws SQLException{
        this.bidderId = resultSet.getInt(Columns.Bid.BIDDER_ID.name());
        this.itemId = resultSet.getInt(Columns.Items.ITEM_ID.name());
        this.bidValue = resultSet.getDouble(Columns.Bid.BID_VALUE.name());
		this.sellerId = resultSet.getInt(Columns.Items.SELLER_ID.name());
        this.title = resultSet.getString(Columns.Items.TITLE.name());
        this.description = resultSet.getString(Columns.Items.DESCRIPTION.name());
        this.startPrice = resultSet.getDouble(Columns.Items.START_PRICE.name());
        this.timeLeft =resultSet.getDouble(Columns.Items.TIME_LEFT.name());
        this.startBiddingDate = resultSet.getTimestamp(Columns.Items.START_BIDDING_DATE.name());
        this.buyItNow = resultSet.getString(Columns.Items.BUY_IT_NOW.name());
        this.bidIncremment = resultSet.getDouble(Columns.Items.BID_INCREMENT.name());
        this.fullName = resultSet.getString(Columns.Users.FULL_NAME.name());
        this.billingAdress = resultSet.getString(Columns.Users.BILLING_ADRESS.name());
        this.login = resultSet.getString(Columns.Users.LOGIN.name());
        this.password = resultSet.getString(Columns.Users.PASSWORD.name());
        this.stopDate = TimeUtils.getSummDate(startBiddingDate,timeLeft);
    }
	
	
	public int getBidderId() {
		return bidderId;
	}

	public int getItemId() {
		return itemId;
	}

	public double getBidValue() {
		return bidValue;
	}

	public int getSellerId() {
		return sellerId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public double getTimeLeft() {
		return timeLeft;
	}

	public Timestamp getStartBiddingDate() {
		return startBiddingDate;
	}

	public String getBuyItNow() {
		return buyItNow;
	}

	public double getBidIncremment() {
		return bidIncremment;
	}

	public String getFullName() {
		return fullName;
	}

	public String getBillingAdress() {
		return billingAdress;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}


	public Date getStopDate() {
		return stopDate;
	}
	
	
}
