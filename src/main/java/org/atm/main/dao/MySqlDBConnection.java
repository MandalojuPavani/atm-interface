package org.atm.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlDBConnection {
	static final String DB_URL="jdbc:mysql://localhost:3306/";
	static final String DB_USER="root";
	static final String DB_PASSWORD="root";
	static final String DB_NAME="atminterfaceddb";
	public static Connection dbconnection()
	{
		Connection connection=null;
		try
		{
			connection = DriverManager.getConnection(DB_URL+DB_NAME, DB_USER, DB_PASSWORD);
		}
		catch(SQLException e)
		{
			System.out.println("Connection Error!!");
			
		}
		return connection;
	}
	

}
