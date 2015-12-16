package com.epam.kuranov.dao.daofamily;

import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.entities.impl.Bid;

import java.sql.*;

public class BidDAO extends AbstractDAO{

    
    public BidDAO(Connection dbConnection) {
		super(dbConnection);
	}
   

    public boolean addBid(Bid bid){
        PreparedStatement preparedStatement = null;
        try {
            String insertTableSQL = "INSERT INTO BIDS(" +
            		Columns.Bid.BIDDER_ID.name() +", " +
            		Columns.Bid.ITEM_ID_BID.name() +", " +
            		Columns.Bid.BID_VALUE.name() +") VALUES (?,?,?)";
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, bid.getBidderId());
            preparedStatement.setInt(2, bid.getItemIdBid());
            preparedStatement.setDouble(3, bid.getBidValue());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return false;
    }

}
