package com.epam.kuranov.domain.entities.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.kuranov.domain.dictionary.Columns;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.Entity;

public class User extends Entity{
	private String fullName;
	private String billingAdress;
	private String login;
	private String password;
	//private String role;
	
    public User(int userId, String fullName, String billingAdress, String login, String password, String role) {
        this.id = userId;
        this.fullName = fullName;
        this.billingAdress = billingAdress;
        this.login = login;
        this.password = password;
        //this.role = role;
    }

    public User(String fullName, String billingAdress, String login, String password, String role) {
        this.fullName = fullName;
        this.billingAdress = billingAdress;
        this.login = login;
        this.password = password;
        //this.role = role;
    }
    
    public User(ResultSet resultSet) throws SQLException{
    	this.id = resultSet.getInt(Columns.Users.USER_ID.name());
        this.fullName = resultSet.getString(Columns.Users.FULL_NAME.name());
        this.billingAdress = resultSet.getString(Columns.Users.BILLING_ADRESS.name());
        this.login = resultSet.getString(Columns.Users.LOGIN.name());
        this.password = resultSet.getString(Columns.Users.PASSWORD.name());
        //this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBillingAdress() {
        return billingAdress;
    }

    public void setBillingAdress(String billingAdress) {
        this.billingAdress = billingAdress;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	@Override
	public String getTableName() {
		return Tables.USERS.name();
	}
}
