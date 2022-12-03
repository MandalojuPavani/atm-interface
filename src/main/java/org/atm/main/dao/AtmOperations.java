package org.atm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.atm.main.exception.InvalidAmountException;



public class AtmOperations {
	
	public static boolean enter(long accnumber, String pin) throws ClassNotFoundException, SQLException
	{
		try
		{
		Connection connection= MySqlDBConnection.dbconnection();
		PreparedStatement statement=connection.prepareStatement("select * from atm where ACCOUNT_NUMBER=?");
		statement.setLong(1, accnumber);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
		
			if(result.getString("pin").equals(pin))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		}
		catch(SQLException e)
		{
			System.out.println("Pin is incorrect!! "); 
		}
		return false;
		
		
	}
	
	public static double balanceCheck(String pin) throws ClassNotFoundException, SQLException
	{  
		double balance;
		try
		{
		Connection connection= MySqlDBConnection.dbconnection();
		PreparedStatement statement=connection.prepareStatement("select * from atm where pin=?");		
		statement.setString(1, pin);
		ResultSet result=statement.executeQuery();
		result.next();
		balance=result.getDouble("balance");
		return balance;
		
		}
		catch(SQLException e)
		{
			System.out.println("Wrong password!!");
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong!!");
		}
		return 0;
	}

	
	public static double withdraw(String pinNumber, double withdrawalAmount) throws ClassNotFoundException, SQLException, InvalidAmountException
	{
		Connection connection= MySqlDBConnection.dbconnection();
		PreparedStatement statement=connection.prepareStatement("select * from atm where pin=?");
		statement.setString(1, pinNumber);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double availableBalance=result.getDouble("balance");
		
		if(withdrawalAmount<availableBalance)
		{
		   double remainingBalance=availableBalance-withdrawalAmount;
		   statement=connection.prepareStatement("update atm set balance=? where pin=?");
		   statement.setDouble(1, new Double(remainingBalance));
		   statement.setString(2, pinNumber);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return remainingBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		}
		else
		{
			throw new InvalidAmountException("Invalid Withdrawal amount!!");
		}
	}



	public static double deposit(String pinNumber, double depositAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MySqlDBConnection.dbconnection();
		PreparedStatement statement=connection.prepareStatement("select * from atm where pin=?");
		statement.setString(1, pinNumber);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		double newBalance=avilableBalance+depositAmount;
	
		statement=connection.prepareStatement("update atm set balance=? where pin=?");
		statement.setDouble(1, new Double(newBalance));
		statement.setString(2, pinNumber);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return newBalance;  
		   }
		   else
		   {
			   return 0;
	       }
	
   }
	
	public static ResultSet checkAccountInfo (String pinNumber) throws ClassNotFoundException, SQLException
	{
		Connection connection= MySqlDBConnection.dbconnection();
		PreparedStatement statement=connection.prepareStatement("select * from atm where pin=?");
		statement.setString(1, pinNumber);
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			return result;
		}
		else
		{
			return null;
		}
	}

	
	
	

}