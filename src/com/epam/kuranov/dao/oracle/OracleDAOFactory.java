package com.epam.kuranov.dao.oracle;


import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.BidDAO;
import com.epam.kuranov.dao.daofamily.ComplexDAO;
import com.epam.kuranov.dao.daofamily.ItemDAO;
import com.epam.kuranov.dao.daofamily.UserDAO;

public class OracleDAOFactory extends DAOFactory {
   

	public OracleDAOFactory(){
		
		dataSource = getDataSource();
	
//        try {
//             connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "11111");}
//        catch (SQLException e) {
//        }
    }
	
	@Override
	public DataSource getDataSource(){
		if (dataSource == null){
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/marketplace");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataSource;
	}

	private Connection getConnection(){
		Connection dbConnection = null;
		try {
			dbConnection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbConnection;
	}
	
    @Override
    public UserDAO getUserDAO() {
        return new UserDAO(getConnection());
    }
   
	@Override
    public BidDAO getBidDAO() {
        return new BidDAO(getConnection());
    }
    
    @Override
    public ItemDAO getItemDAO() {
        return new ItemDAO(getConnection());
    }

    @Override
    public ComplexDAO getComplexDAO() {
        return new ComplexDAO(getConnection());
    }
}