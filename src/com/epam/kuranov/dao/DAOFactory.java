package com.epam.kuranov.dao;

import javax.sql.DataSource;
import com.epam.kuranov.dao.daofamily.BidDAO;
import com.epam.kuranov.dao.daofamily.ComplexDAO;
import com.epam.kuranov.dao.daofamily.ItemDAO;
import com.epam.kuranov.dao.daofamily.UserDAO;
import com.epam.kuranov.dao.oracle.OracleDAOFactory;

public abstract class DAOFactory {
	

	protected DataSource dataSource = null;

    public static final int ORACLE = 1;

    public abstract UserDAO getUserDAO();
    public abstract BidDAO getBidDAO();
    public abstract ItemDAO getItemDAO();
    public abstract ComplexDAO getComplexDAO();
    
    
    public abstract DataSource getDataSource();
    
    public static DAOFactory getDAOFactory(
            int whichFactory
            ) {

        switch (whichFactory) {
            case ORACLE:
                return new OracleDAOFactory();
            default:
                return null;
        }
        
    }

}
