package com.revature.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.logging.Logger;
import com.revature.logging.Logger.LogLevel;
import com.revature.models.Customer;
import com.revature.storage.CustomerInfo;
import com.revature.ui.ConnectionFactory;

public class CustomerDBLogic implements DAO<Customer>{

	@Override
	public void Add(Customer newCust) {
		// TODO Auto-generated method stub
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "Insert into customer (CustName,Address,Email,Phonenum) values (?,?,?,?)";
			PreparedStatement pstmt = connie.prepareStatement(query);
			pstmt.setString(1, newCust.getName());
			pstmt.setString(2, newCust.getAddress());
			pstmt.setString(3, newCust.getEmail());
			pstmt.setString(4, newCust.getPhoneNum());
			pstmt.execute();
			Logger.getLogger().log(LogLevel.info,"Inserted Customer");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Customer[] getAll() {
		// TODO Auto-generated method stub
		CustomerInfo customer = new CustomerInfo();
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customer";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				customer.add(new Customer(rs.getString("custname"), rs.getString("address"), rs.getString("email"), rs.getString("phonenum"), rs.getInt("c_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer.getAllElements();
	}
	
	@Override
	public void register() {
		String infoInput;
		Customer cust = new Customer();
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter 'Exit' at any time to return to the menu");
		
		//Get the users name
		do{
			System.out.println("Users Name: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));
		//Set name after validation rule has passed
		cust.setName(infoInput);
		
		//Get the users address
		do{
			System.out.println("Users Address: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));
		//Set address after validation rule has passed
		cust.setAddress(infoInput);
		
		//Get the users email
		do{
			System.out.println("Users Email: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));
		//Set email after validation rule has passed
		cust.setEmail(infoInput);		
		
		//Get the users phone number
		do{
			System.out.println("Users Phone Number: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));	
		//Set number after validation rule has passed
		cust.setPhoneNum(infoInput);
		
		//Add the customer to the database
		Add(cust);
		return;
	}
	
	public static void SearchCustomer() {
		String infoInput;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter in the name of the user you wish to find: ");
		infoInput = s.nextLine();
		
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customer where custname = '"+infoInput+"'";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println("               Customer               ");
				System.out.println("--------------------------------------");
				System.out.println("Customer Id: "+rs.getInt("c_id"));
				System.out.println("Name: "+rs.getString("custname"));
				System.out.println("Address: "+rs.getString("address"));
				System.out.println("Email: "+rs.getString("email"));
				System.out.println("Phone number: "+rs.getString("phonenum"));
				System.out.println("--------------------------------------");
			}
			else {
				System.out.println("Customer not found :(");
				Logger.getLogger().log(LogLevel.error,"Customer not found by user search");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
}
