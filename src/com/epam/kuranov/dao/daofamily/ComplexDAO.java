package com.epam.kuranov.dao.daofamily;


import java.sql.Connection;
import java.util.ArrayList;

import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.impl.ComplexEntity;

public class ComplexDAO extends AbstractDAO{
	
	
	
    public ComplexDAO(Connection dbConnection) {
		super(dbConnection);
	}

	public ArrayList<ComplexEntity> getRSBySearchSequence(String addSequence) {
		ArrayList<ComplexEntity> complexResultSet = new ArrayList<>();
        try {
        	StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ").
            	append(Tables.BIDS.name()).
            	append(" RIGHT JOIN ").
            	append(Tables.ITEMS.name()).
            	append(" ON ").
            	append(Columns.Bid.ITEM_ID_BID.name()).
            	append(" = ").
            	append(Columns.Items.ITEM_ID).
            	append(" , ").
            	append(Tables.USERS.name()).
            	append(" WHERE ").
            	append(Columns.Items.SELLER_ID.name()).
            	append(" = ").
            	append(Columns.Users.USER_ID.name()).
            	append(addSequence);
            String selectQuery = stringBuilder.toString();
            
            daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            dbConnection = daoFactory.getDataSource().getConnection();
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                complexResultSet.add(new ComplexEntity(resultSet));
            }
            cleanObjects();
            return complexResultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return complexResultSet;
    }
}
