package com.epam.kuranov.dao.daofamily;

import com.epam.kuranov.dao.AbstractDAO;
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
        try {
            String insertTableSQL = "INSERT INTO USERS"
                    + "(FULL_NAME, BILLING_ADRESS, LOGIN, PASSWORD, ROLE) VALUES"
                    + "(?,?,?,?,?)";
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


    public boolean deleteUser(int user_id){
        try {
            String selectQuery = "DELETE FROM Users WHERE User_Id=?";
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	cleanObjects();
        }
		return false;
    }


    public boolean modifyUser(User user){
        try {
            String updateQuery = "UPDATE Users SET Full_Name=?, Billing_Adress=?, " +
                    "Login=?, Password=? WHERE User_Id=?";
            preparedStatement = dbConnection.prepareStatement(updateQuery);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getBillingAdress());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getId());
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


    public User getUserByLogin(String login){
        try {
            String selectQuery = "SELECT * FROM USERS WHERE LOWER(Login) = '" +
                    login.toLowerCase() + "'";
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getInt("User_Id"), resultSet.getString("Full_Name"),
                        resultSet.getString("Billing_Adress"), resultSet.getString("Login"), resultSet.getString("Password"));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	cleanObjects();
        }
        return null;
    }

    public User getUserById(int userId){
        try {
            String selectQuery = "SELECT * FROM USERS WHERE User_Id = '" + userId + "'";
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
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
    
    public User authentificate(String login, String password){
        try {
            String selectQuery = "SELECT * FROM USERS WHERE LOGIN = '" + login + "' AND PASSWORD = '" + password +"'";
            preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
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
