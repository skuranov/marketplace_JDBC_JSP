package com.epam.kuranov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.epam.kuranov.dao.daofamily.UserDAO;
import com.epam.kuranov.dao.oracle.OracleDAOFactory;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.Entity;
import com.epam.kuranov.domain.entities.impl.User;

public abstract class AbstractDAO {
	protected Connection dbConnection;
	protected DAOFactory daoFactory;
    protected PreparedStatement preparedStatement;
    protected Statement statement;
    protected ResultSet resultSet;
	
    public AbstractDAO(Connection dbConnection){
    	this.dbConnection = dbConnection;
    }
    
    protected void cleanObjects(){
    	
    	if (resultSet != null) {
			try {
				resultSet.close();}
			catch (SQLException e) {e.printStackTrace();}
			resultSet = null;
        }
    	
		if (preparedStatement != null) {
			try {
				preparedStatement.close();}
			catch (SQLException e) {e.printStackTrace();}
			preparedStatement = null;
        }
		
        if (statement != null) {
        	try {
        		statement.close();}
			catch (SQLException e) {e.printStackTrace();}
        	statement = null;
        }
        
        if (dbConnection != null) {
        	try {
        		dbConnection.close();}
			catch (SQLException e) {e.printStackTrace();}
        	dbConnection = null;
        }
    }
    
    public boolean deleteById(String tableName, String idColumnName, String id){
    	try {
            String selectQuery = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = " + id;
            runQuery(selectQuery);
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
    
    public ArrayList<Entity> getAllTable(Tables tableName){
    	ArrayList<Entity> entityList = new ArrayList<>();
    	String selectQuery = "SELECT * FROM " + tableName;
    	try {
            runQuery(selectQuery);
            while (resultSet.next()) {
            	entityList.add(tableName.getNewEntity(resultSet));
            }
            cleanObjects();
            return entityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return null;
    }
    
   
    public Entity getById(Tables entityType,
            int id){
    	Entity entity = null;
    	String selectQuery = "SELECT * FROM " + entityType.name() + " WHERE " +
        entityType.getIdColumn() + " = " + id; 
		try{
	    	runQuery(selectQuery);
	    	while (resultSet.next()) {
				 entity = entityType.getNewEntity(resultSet);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cleanObjects();
		}
		return entity;
		}
    
    protected void runQuery(String query) throws SQLException{
    	preparedStatement = dbConnection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
    }
}
