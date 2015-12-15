package com.epam.kuranov.dao.daofamily;

import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.domain.entities.impl.Bid;

import java.sql.*;
import java.util.ArrayList;

public class BidDAO extends AbstractDAO{

	final static private int GREATER_THEN = 1;
    final static private int LESS_THEN = 2;
    
    public BidDAO(Connection dbConnection) {
		super(dbConnection);
	}
   
    public ArrayList<Bid> getByValue(Double value, int mode){
        ArrayList<Bid> listBids = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM BIDS";
            if (mode == GREATER_THEN){
                selectQuery = selectQuery + " WHERE BID_VALUE >= " + value;
            }
            else if (mode == LESS_THEN){
                selectQuery = selectQuery + " WHERE BID_VALUE <= " + value;
            }
            preparedStatement =  dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	listBids.add(new Bid(resultSet));
            }
            return listBids;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return listBids;
    }

  
    public ArrayList<Bid> getByItemId(int itemId){
        PreparedStatement preparedStatement = null;
        ArrayList<Bid> listBids = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM BIDS WHERE ITEM_ID_BID = " + itemId;
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	listBids.add(new Bid(resultSet));
            }
            return listBids;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return listBids;
    }

 
    public ArrayList<Bid> getByUserId(int bidderId){
        PreparedStatement preparedStatement = null;
        ArrayList<Bid> listBids = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM BIDS WHERE BIDDER_ID = " + bidderId;
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	listBids.add(new Bid(resultSet));
            }
            return listBids;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return listBids;
    }


    public boolean addBid(Bid bid){
        PreparedStatement preparedStatement = null;
        try {
            String insertTableSQL = "INSERT INTO BIDS"
                    + "(BIDDER_ID, ITEM_ID_BID, BID_VALUE) VALUES"
                    + "(?,?,?)";
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
