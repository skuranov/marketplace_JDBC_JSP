package com.epam.kuranov.dao.daofamily;


import java.sql.Connection;
import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.impl.Item;

public class ItemDAO extends AbstractDAO {
		
    public ItemDAO(Connection dbConnection) {
		super(dbConnection);
	}

    public boolean addItem(Item item){
    	StringBuilder builder = new StringBuilder();
    	builder.append("INSERT INTO ").
    		append(Tables.ITEMS.name()).
    		append("(").
    		append(Columns.Items.SELLER_ID.name()).
    		append(", ").
    		append(Columns.Items.TITLE.name()).
    		append(", ").
    		append(Columns.Items.DESCRIPTION.name()).
    		append(", ").
    		append(Columns.Items.START_PRICE.name()).
    		append(", ").
    		append(Columns.Items.TIME_LEFT.name()).
    		append(", ").
    		append(Columns.Items.START_BIDDING_DATE.name()).
    		append(", ").
    		append(Columns.Items.BUY_IT_NOW.name()).
    		append(", ").
    		append(Columns.Items.BID_INCREMENT.name()).
    		append(")VALUES(?,?,?,?,?,?,?,?)");
    	String updateQuery = builder.toString(); 
        
    	try {
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
    
    public boolean changeItem(Item item,
            int id){
    	StringBuilder builder = new StringBuilder();
    	builder.append("UPDATE ").
			append(Tables.ITEMS.name()).
			append(" SET ").
			append(Columns.Items.TITLE.name()).
			append(" = '").
			append(item.getTitle()).
			append("', ").
			append(Columns.Items.DESCRIPTION.name()).
			append(" = '").
			append(item.getDescription()).
			append("', ").
			append(Columns.Items.START_PRICE.name()).
			append(" = '").
			append(item.getStartPrice()).
			append("', ").
			append(Columns.Items.TIME_LEFT).
			append(" = '").
			append(item.getTimeLeft()).
			append("', ").
			append(Columns.Items.BUY_IT_NOW).
			append(" = '").
			append(item.getBuyItNow()).
			append("', ").
			append(Columns.Items.BID_INCREMENT).
			append(" = '").
			append(item.getBidIncremment()).
			append("' WHERE ").
			append(Columns.Items.ITEM_ID.name()).
			append(" = ").
			append(id);
		String updateQuery = builder.toString(); 
 
		try{
			runQuery(updateQuery);
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

