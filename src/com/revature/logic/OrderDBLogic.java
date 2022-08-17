package com.revature.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.logging.Logger;
import com.revature.logging.Logger.LogLevel;
import com.revature.models.Order;
import com.revature.storage.OrderInfo;
import com.revature.ui.ConnectionFactory;

public class OrderDBLogic implements DAO<Order>{

	@Override
	public void Add(Order newOrder) {
		// TODO Auto-generated method stub
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into orders (ordernumber,lineid,custid,overallprice,amount,storeid) values (?,?,?,?,?,?)";
			PreparedStatement pstmt = connie.prepareStatement(query);
			pstmt.setInt(1, newOrder.getOrderId());
			pstmt.setInt(2, newOrder.getLineItemID());
			pstmt.setInt(3, newOrder.getCustOrderingID());
			pstmt.setDouble(4, newOrder.getOverallPrice());
			pstmt.setInt(5, newOrder.getAmount());
			pstmt.setInt(6, newOrder.getStoreid());
			pstmt.execute();
			pstmt.close();
			Logger.getLogger().log(LogLevel.info,"Inserted Order");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Order[] getAll() {
		OrderInfo order = new OrderInfo();
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from orders";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				order.add(new Order(rs.getInt("lineid"), rs.getInt("custid"), rs.getDouble("overallprice"), rs.getInt("ordernumber"), rs.getInt("amount"), rs.getInt("storeid") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order.getAllElements();
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		String infoInput, StoreInput;
		Order ord = new Order();
		Scanner s = new Scanner(System.in);
		int result = -1;
		double overallprice = 0;
		
		System.out.println("What store are you ordering from?");
		do{
			System.out.println("Store Number: ");
			StoreInput = s.nextLine();
			if(StoreInput.equals("exit"))
				return;
		}while(StoreInput.equals("-1") || StoreInput.equals(""));
		
		ord.setStoreid(Integer.valueOf(StoreInput));
		ord.setOrderId((getOrderNumber()+ 1));
		
		System.out.println("What is your customer id?");
		do{
			System.out.println("Customer ID: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));
		
		ord.setCustOrderingID(Integer.valueOf(infoInput));
				
		System.out.println("So what movie would you like to purchase?");
		do{
			System.out.println("Movie name: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
			result = ProductDBLogic.SearchMovieGetLineId(infoInput,StoreInput);
		}while(result == -1 || infoInput.equals(""));
		//Set name after validation rule has passed
		ord.setLineItemID(result);
		
		System.out.println("How many would you like to purchase?");
		do{
			System.out.println("Quantity: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
			
			if(LineItemDBLogic.isTooMuch(Integer.valueOf(infoInput), ord.getLineItemID())){
				System.out.println("You chose too many!");
				infoInput = "bad";
			}
		}while(infoInput.equals("") || infoInput.equals("bad"));
		
		ord.setAmount(Integer.valueOf(infoInput));
		
		ord.setOverallPrice((double)ProductDBLogic.CalculatePrice(ord.getLineItemID()) * Double.valueOf(infoInput));
		
		LineItemDBLogic.updateQuantity(Integer.valueOf(infoInput), ord.getLineItemID());
		
		Add(ord);
		
		//Additional logic to order more movies
		do {
			System.out.println("Would you like to add another movie to this order?");
			infoInput = s.nextLine();
			if(infoInput.equals("no") || infoInput.equals("exit"))
				return;
			
			System.out.println("So what movie would you like to purchase?");
			
			do{
				System.out.println("Movie name: ");
				infoInput = s.nextLine();
				if(infoInput.equals("exit"))
					return;
				result = ProductDBLogic.SearchMovieGetLineId(infoInput,StoreInput);
			}while(result == -1);
			//Set name after validation rule has passed
			ord.setLineItemID(result);
			
			System.out.println("How many would you like to purchase?");
			
			do{
				System.out.println("Quantity: ");
				infoInput = s.nextLine();
				if(infoInput.equals("exit"))
					return;
				
				if(LineItemDBLogic.isTooMuch(Integer.valueOf(infoInput), ord.getLineItemID())){
					System.out.println("You chose too many!");
					infoInput = "bad";
				}
			}while(infoInput.equals("") || infoInput.equals("bad"));
			
			ord.setAmount(Integer.valueOf(infoInput));
				
			ord.setOverallPrice((double)ProductDBLogic.CalculatePrice(ord.getLineItemID()) * Double.valueOf(infoInput));
			
			LineItemDBLogic.updateQuantity(Integer.valueOf(infoInput), ord.getLineItemID());
			
			Add(ord);
		}while(true);
		
	}
	
	public static void SearchOrder() {
		String infoInput;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter in the customer id: ");
		
		infoInput = s.nextLine();
		
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from orders where custid = "+infoInput;
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println("                Order                 ");
				System.out.println("--------------------------------------");
				System.out.println("Order Number: "+rs.getInt("ordernumber"));
				System.out.println("Movie Line Number: "+rs.getInt("lineid"));
				System.out.println("Amount ordered: "+rs.getInt("amount"));
				System.out.println("From store number : "+rs.getInt("storeid"));
				System.out.println("Overall Price: $"+rs.getDouble("overallprice"));
				System.out.println("--------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public static int getOrderNumber() {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "SELECT MAX(ordernumber) as mx FROM orders";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println(rs.getInt("mx"));
				return rs.getInt("mx");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
}





