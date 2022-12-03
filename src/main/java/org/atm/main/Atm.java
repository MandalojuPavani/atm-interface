package org.atm.main;
import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.atm.main.dao.AtmOperations;
import org.atm.main.exception.InvalidAmountException;



public class Atm 
{
	static BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void main(String args[]) throws NullPointerException,NumberFormatException, IOException, ClassNotFoundException, SQLException,InvalidAmountException
	{
		
		
		System.out.println("============================= WELCOME TO ATM MACHINE !!! ===========================");
		System.out.println("====================================================================================");
		
		boolean status=false;
		do
		{
		
			
			System.out.print("\t\t  Accountnumber : ");
			long accnumber=Long.parseLong( bufferedReader.readLine());
			System.out.println();
			System.out.print("\t\t  Enter Pin : ");
			String pin=bufferedReader.readLine();
			System.out.println("====================================================================================");
			status=AtmOperations.enter(accnumber, pin);
		
		
				if(status)
				{
				do
				{
					
					System.out.println("========================= CHOOSE THE GIVEN OPTION BELOW ============================");
					System.out.println("====================================================================================");
					System.out.println("\t\t  Enter 1-> Balance Enquiry");
					System.out.println("\t\t  Enter 2-> Withdraw");
					System.out.println("\t\t  Enter 3-> Deposit");
					System.out.println("\t\t  Enter 4-> Check Account Info");
					System.out.println("\t\t  Enter 5-> Exit");
					System.out.println("====================================================================================");
					System.out.println("\t\t  Enter a valid input between 1 to 5:");
					int choice=Integer.parseInt(bufferedReader.readLine());
					
					switch(choice)
					{
					case 1: System.out.println("Enter valid pin number:");
							String pinNumber=bufferedReader.readLine();
							System.out.println( "Current available balance is :"+AtmOperations.balanceCheck(pinNumber));
							break;
							
					case 2: System.out.println("Enter valid pin number:");
					        pinNumber= bufferedReader.readLine();
							System.out.println("Enter withdraw amount:");
							double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());
							try
							{
								double result=AtmOperations.withdraw(pinNumber, withdrawalAmount);						
								System.out.println("Transaction successfull!!");
								System.out.println("Remaining balance is:"+result);
							}
							catch(InvalidAmountException e)
							{
								System.out.println("Invalid Withdrawal amount!!");
							}
							break;
					
					case 3: System.out.println("Enter valid pin number:");
							pinNumber=bufferedReader.readLine();
				           	System.out.println("Enter deposit amount:");
				          	double depositAmount=Double.parseDouble(bufferedReader.readLine());
					        double result=AtmOperations.deposit(pinNumber, depositAmount);
					
							if(result==0)
							{
								
								System.out.println("Transaction is unsuccessfull!!");
							}
							else
							{
								System.out.println("Transaction successfull!!");
								System.out.println("New balance is:"+result);
							}
							break;	
							
					case 4: System.out.println("Enter valid pin number:");
							pinNumber=bufferedReader.readLine();
							System.out.println("====================================================================================");
							System.out.println("=============================== ACCOUNT DETAIL's ===================================");		
							System.out.println("====================================================================================");
		
							ResultSet accountInfo=AtmOperations.checkAccountInfo(pinNumber); 
			        		System.out.println("\t\t  Account Number    :"+accountInfo.getLong("ACCOUNT_NUMBER"));
			        		System.out.println("\t\t  Account Name      :"+accountInfo.getString("ACCOUNT_NAME"));
			        		System.out.println("\t\t  Branch        :"+accountInfo.getString("BRANCH"));
			        		System.out.println("\t\t  Available Balance :"+accountInfo.getDouble("BALANCE"));
							System.out.println("====================================================================================");
							break;
							
					case 5: status=false;
					        System.out.println("====================================================================================");
							System.out.println("Thank you for using ATM Machine!!!");
							System.out.println("====================================================================================");
							break;
							
					default: System.out.println("====================================================================================");
							 System.out.println("Wrong Choice!!");		
							 System.out.println("====================================================================================");
						
					}
				
			}
			while(status);
			}
			
			else
			{
				System.out.println("====================================================================================");
				System.out.println("Incorrect account number or pin!!");
				System.out.println("====================================================================================");
			}
	}
	while(status);
	}
	
	}
