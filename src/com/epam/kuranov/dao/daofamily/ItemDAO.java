package com.epam.kuranov.dao.daofamily;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.impl.Item;

public class ItemDAO extends AbstractDAO {

	final protected int NOORDER = 0;
	final protected int UP = 1;
	final protected int DOWN = 2;
	final protected int NOSTATUS = 0;
	final protected int ACTIVE = 1;
	final protected int ENDED = 2;
	
		
    public ItemDAO(Connection dbConnection) {
		super(dbConnection);
	}


	public ArrayList<Item> getByDescr(String description,
                              int orderType,
                              int status) {
        ArrayList<Item> listItems = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM ITEMS WHERE LOWER(Description) LIKE '%" +
                    description.toLowerCase()+"%'";
            if (status == ACTIVE){
                selectQuery = selectQuery + " AND (START_BIDDING_DATE + TIME_LEFT) > sysDate";
            }
            else if (status == ENDED){
                selectQuery = selectQuery + " AND (START_BIDDING_DATE + TIME_LEFT) < sysDate";
            }
            else if(status == NOSTATUS){}

            if (orderType == UP){
                selectQuery = selectQuery + " ORDER BY BID_VALUE";
            }
            else if(orderType == DOWN){
                selectQuery = selectQuery + " ORDER BY BID_VALUE";
            }
            else if(orderType == NOORDER){}

            preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
            	listItems.add(new Item(resultSet));
            }
            
            return listItems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return listItems;
    }


    public boolean addItem(Item item){
        try {
            String updateQuery = "Insert Into Items(Seller_Id," +
                    "Title,Description,Start_Price,Time_Left,Start_Bidding_Date," +
                    "Buy_It_Now, Bid_Increment)VALUES(?,?,?,?,?,?,?,?)";
            preparedStatement = dbConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, item.getSellerId());
            preparedStatement.setString(2, item.getTitle());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setDouble(4, item.getStartPrice());
            preparedStatement.setDouble(5, item.getTimeLeft());
            preparedStatement.setTimestamp(6, item.getStartBiddingDate());
            preparedStatement.setString(7, item.getBuyItNow());
            preparedStatement.setDouble(8, item.getBidIncremment());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            else {return false;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return false;
    }

    
    public boolean modifyItem(Item item){
        try {
            String updateQuery = "UPDATE Items SET seller_id=?, title=?, description=?, start_price=?," +
                    "time_left=?, start_bidding_date=?, buy_it_now=?, bid_increment=? WHERE Item_Id=?";
            preparedStatement = dbConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, item.getSellerId());
            preparedStatement.setString(2, item.getTitle());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setDouble(4, item.getStartPrice());
            preparedStatement.setDouble(5, item.getTimeLeft());
            preparedStatement.setTimestamp(6, item.getStartBiddingDate());
            preparedStatement.setString(7, item.getBuyItNow());
            preparedStatement.setDouble(8, item.getBidIncremment());
            preparedStatement.setInt(9, item.getId());
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


    public boolean changeItem(Item item,
            int id){
    	String selectQuery = "UPDATE " + Tables.ITEMS.name() + 
    			" SET " + Columns.Items.TITLE.name() + " = '" + item.getTitle() + "', " +
    			Columns.Items.DESCRIPTION.name() + " = '" + item.getDescription() + "', " +
    			Columns.Items.START_PRICE.name() + " = '" + item.getStartPrice() + "', " +
    			Columns.Items.TIME_LEFT + " = '" + item.getTimeLeft() + "', " +
    			Columns.Items.BUY_IT_NOW + " = '" + item.getBuyItNow() + "', " +
    			Columns.Items.BID_INCREMENT + " = '" + item.getBidIncremment() + "'" +
    			" WHERE " + Columns.Items.ITEM_ID.name() + " = " + item.getId();
    	
		try{
	    	preparedStatement = dbConnection.prepareStatement(selectQuery);
            preparedStatement.executeUpdate();
            cleanObjects();
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

