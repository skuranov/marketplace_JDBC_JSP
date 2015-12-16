package com.epam.kuranov.dao.daofamily;

import com.epam.kuranov.dao.AbstractDAO;
import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.impl.User;

import java.sql.*;
/**
 * Created by Sergei_Kuranov on 11/5/2015.
 */
public class UserDAO extends AbstractDAO {

	
	
    public UserDAO(Connection dbConnection) {
		super(dbConnection);
	}

	public boolean addUser(User user) {
		StringBuilder builder = new StringBuilder();
    	builder.append("INSERT INTO").
    		append(Tables.USERS.name()).
    		append("(").
    		append(Columns.Users.FULL_NAME.name()).
    		append(", ").
    		append(Columns.Users.BILLING_ADRESS.name()).
    		append(", ").
    		append(Columns.Users.LOGIN.name()).
    		append(", ").
    		append(Columns.Users.PASSWORD.name()).
    		append(", ").
    		append(Columns.Users.ROLE.name()).
    		append(") VALUES(?,?,?,?,?)");
    	String insertTableSQL = builder.toString();	    		
        try {
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getBillingAdress());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, "simpleUser");
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


   
    public User authentificate(String login, String password){
		StringBuilder builder = new StringBuilder();
    	builder.append("SELECT * FROM ").
    		append(Tables.USERS.name()).
    		append(" WHERE ").
    		append(Columns.Users.LOGIN.name()).
    		append(" = '").
    		append(login).
    		append("' AND ").
    		append(Columns.Users.PASSWORD.name()).
    		append(" = '").
    		append(password).
    		append("'");
    	String selectQuery = builder.toString();	    
        try {
            runQuery(selectQuery);
            while (resultSet.next()) {
            	return new User(resultSet);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	cleanObjects();
        }
        return null;
    }
}
