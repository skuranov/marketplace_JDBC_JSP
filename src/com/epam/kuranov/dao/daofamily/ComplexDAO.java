package com.epam.kuranov.dao.daofamily;


import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.domain.entities.impl.ComplexEntity;

public class ComplexDAO extends AbstractDAO{
	
	
	
    public ComplexDAO(Connection dbConnection) {
		super(dbConnection);
	}

	public ArrayList<ComplexEntity> getRSBySearchSequence(String addSequence, DataSource dataSource) {
		ArrayList<ComplexEntity> complexResultSet = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM BIDS RIGHT JOIN ITEMS ON ITEM_ID_BID = "
            		+ "ITEM_ID, USERS WHERE SELLER_ID = USER_ID" +
            		addSequence;
            
            daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
            dataSource = daoFactory.getDataSource();
            dbConnection = dataSource.getConnection();
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
