package com.epam.kuranov.domain.dictionary;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.kuranov.domain.entities.Entity;
import com.epam.kuranov.domain.entities.impl.Bid;
import com.epam.kuranov.domain.entities.impl.Item;
import com.epam.kuranov.domain.entities.impl.User;

public enum Tables {
	
	ITEMS{
		
	    @Override
		public String getIdColumn(){
			return Columns.Items.ITEM_ID.name();
		}

		@Override
		public Entity getNewEntity(ResultSet resultSet) {
			try {
				return new Item(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	},
	BIDS{
		
	    @Override
		public String getIdColumn(){
			return Columns.Bid.BID_ID.name();
		}

		@Override
		public Entity getNewEntity(ResultSet resultSet) {
			try {
				return new Bid(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	},
	USERS{
		
	    @Override
		public String getIdColumn(){
			return Columns.Users.USER_ID.name();
		}

		@Override
		public Entity getNewEntity(ResultSet resultSet) {
			try {
				return new User(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	};
	
	public abstract String getIdColumn(); 
	
	public abstract Entity getNewEntity(ResultSet resultSet); 
}
