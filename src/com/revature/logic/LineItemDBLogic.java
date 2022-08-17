package com.revature.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.logging.Logger;
import com.revature.logging.Logger.LogLevel;
import com.revature.models.LineItem;
import com.revature.storage.LineItemInfo;
import com.revature.ui.ConnectionFactory;

public class LineItemDBLogic implements DAO<LineItem>{

	@Override
	public void Add(LineItem newLine) {
		// TODO Auto-generated method stub
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "Insert into lineitem (productid, quantity, lineid) values (?,?,?)";
			PreparedStatement pstmt = connie.prepareStatement(query);
			pstmt.setInt(1, newLine.getProductId());
			pstmt.setInt(2, newLine.getQuantity());
			pstmt.setInt(3, newLine.getLid());
			pstmt.execute();
			Logger.getLogger().log(LogLevel.info,"Inserted Line Item");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public LineItem[] getAll() {
		// TODO Auto-generated method stub
		LineItemInfo LineItem = new LineItemInfo();
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from lineitem";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				LineItem.add(new LineItem(rs.getInt("productid"), rs.getInt("quantity"), rs.getInt("lineid") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LineItem.getAllElements();
	}
	
	
	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}
	
	public static boolean isTooMuch(int amount, int lid) {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select quantity from lineitem where lineid = "+lid;
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				if(rs.getInt("quantity") < amount)
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static void updateQuantity(int amount, int lid) {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "update lineitem set quantity=quantity-"+amount+" where lineid = "+lid;
			Statement stmt = connie.createStatement();
			stmt.executeUpdate(query);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public static void restock() {
		String ramount;
		String lid;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter in the movie line number: ");
		lid = s.nextLine();
		
		System.out.println("Enter the amount you would like to add: ");
		ramount = s.nextLine();
		
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "update lineitem set quantity=quantity+"+ramount+" where lineid = "+lid;
			Statement stmt = connie.createStatement();
			stmt.executeUpdate(query);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	
	
}
