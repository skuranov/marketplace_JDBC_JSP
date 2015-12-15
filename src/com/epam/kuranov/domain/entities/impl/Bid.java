package com.epam.kuranov.domain.entities.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.Entity;

public class Bid extends Entity {

    private int bidderId;
    private int itemIdBid;
    private double bidValue;

    public Bid(int bidderId, int itemIdBid, double bidValue) {
        this.id = 1;
        this.bidderId = bidderId;
        this.itemIdBid = itemIdBid;
        this.bidValue = bidValue;
    }

    public Bid(int bidId, int bidderId, int itemIdBid, double bidValue) {
        this.id = bidId;
        this.bidderId = bidderId;
        this.itemIdBid = itemIdBid;
        this.bidValue = bidValue;
    }
    
    public Bid(ResultSet resultSet) throws SQLException{
    	this.id = resultSet.getInt(Columns.Bid.BID_ID.name());
        this.bidderId = resultSet.getInt(Columns.Bid.BIDDER_ID.name());
        this.itemIdBid = resultSet.getInt(Columns.Bid.ITEM_ID_BID.name());
        this.bidValue = resultSet.getDouble(Columns.Bid.BID_VALUE.name());
    }

    public double getBidValue() {
        return bidValue;
    }

    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public int getItemIdBid() {
        return itemIdBid;
    }

    public void setItemIdBid(int itemIdBid) {
        this.itemIdBid = itemIdBid;
    }

	@Override
	public String getTableName() {
		return Tables.BIDS.name();
	}
	
}
