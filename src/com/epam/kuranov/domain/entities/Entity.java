package com.epam.kuranov.domain.entities;

public abstract class Entity {
	protected int id; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public abstract String getTableName();
	
}
