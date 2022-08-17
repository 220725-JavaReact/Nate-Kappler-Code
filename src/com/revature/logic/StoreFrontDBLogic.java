package com.revature.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.logging.Logger;
import com.revature.logging.Logger.LogLevel;
import com.revature.models.StoreFront;
import com.revature.ui.ConnectionFactory;

public class StoreFrontDBLogic implements DAO<StoreFront>{

	@Override
	public void Add(StoreFront newCust) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StoreFront[] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	public static void SearchOrder() {
		String infoInput;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter in the store id: ");
		infoInput = s.nextLine();
		
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from orders where storeid = "+infoInput;
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println("                Order                 ");
				System.out.println("--------------------------------------");
				System.out.println("Order Number: "+rs.getInt("ordernumber"));
				System.out.println("Movie Line Number: "+rs.getInt("lineid"));
				System.out.println("Amount ordered: "+rs.getInt("amount"));
				System.out.println("Overall Price: $"+rs.getDouble("overallprice"));
				System.out.println("--------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
