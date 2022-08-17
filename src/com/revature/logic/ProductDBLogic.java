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
import com.revature.models.Product;
import com.revature.storage.ProductInfo;
import com.revature.ui.ConnectionFactory;

public class ProductDBLogic implements DAO<Product>{

	@Override
	public void Add(Product newProd) {
		// TODO Auto-generated method stub
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "Insert into product (name, price, description, category) values (?,?,?,?)";
			PreparedStatement pstmt = connie.prepareStatement(query);
			pstmt.setString(1, newProd.getName());
			pstmt.setDouble(2, newProd.getPrice());
			pstmt.setString(3, newProd.getDesc());
			pstmt.setString(4, newProd.getCategory());
			pstmt.execute();
			Logger.getLogger().log(LogLevel.info,"Inserted Product");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Product[] getAll() {
		ProductInfo product = new ProductInfo();
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from product";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				product.add(new Product(rs.getString("name"), rs.getDouble("price"), rs.getString("description"), rs.getString("category"), rs.getInt("pid")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product.getAllElements();
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		String infoInput;
		Product prod = new Product();
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter 'Exit' at any time to return to the menu");
		//Get the users name
		do{
			System.out.println("Movie title: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));
		//Set name after validation rule has passed
		prod.setName(infoInput);
		
		//Set the price of the product
		do{
			System.out.println("Movie price: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));
		//Set name after validation rule has passed
		prod.setPrice(Double.valueOf(infoInput));
		
		//Set the description of the product
		do{
			System.out.println("Movie description: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));		
		//Set name after validation rule has passed
		prod.setDesc(infoInput);		
		
		//Set the category of the product
		do{
			System.out.println("Movie category: ");
			infoInput = s.nextLine();
			if(infoInput.equals("exit"))
				return;
		}while(infoInput.equals(""));				
		//Set name after validation rule has passed
		prod.setCategory(infoInput);	
		
		//Add the product to the database
		Add(prod);
	}
	
	public static int SearchMovieGetLineId(String term, String SID) {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select pid from product where name = '"+term+"'";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				query = "select lineid from lineitem where productid = "+rs.getInt("pid")+" and storeid = "+SID;
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					return (rs.getInt("lineid"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static double CalculatePrice(int lineid) {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select productid from lineitem where lineid = "+lineid;
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				query = "select price from product where pid = "+rs.getInt("productid");
				ResultSet rs2 = stmt.executeQuery(query);
				if(rs2.next()) {
					return (rs2.getDouble("price"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void SearchMovie() {
		String infoInput;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter in the movie title: ");
		infoInput = s.nextLine();
		
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from product where name = '"+infoInput+"'";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				System.out.println("                Film                  ");
				System.out.println("--------------------------------------");
				System.out.println("Title: "+rs.getString("name"));
				System.out.println("Product Id: "+rs.getString("pid"));
				System.out.println("Category: "+rs.getString("category"));
				System.out.println("Description: "+rs.getString("description"));
				System.out.println("Price: $"+rs.getDouble("price"));
				System.out.println("--------------------------------------");
			}else {
				System.out.println("Movie not found :(");
				Logger.getLogger().log(LogLevel.error,"Film not found by user search");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public static void ShowStoreStock() {
		String infoInput;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter in the store number: ");
		infoInput = s.nextLine();
		
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select productid from lineitem where storeid = "+infoInput;
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				String query2 = "select * from product where pid = "+rs.getInt("productid");
				Statement stmt2 = connie.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query2);
				if(rs2.next()) {
					System.out.println("                Film                  ");
					System.out.println("--------------------------------------");
					System.out.println("Title: "+rs2.getString("name"));
					System.out.println("Product Id: "+rs2.getString("pid"));
					System.out.println("Category: "+rs2.getString("category"));
					System.out.println("Description: "+rs2.getString("description"));
					System.out.println("Price: $"+rs2.getDouble("price"));
					System.out.println("--------------------------------------");
				}
			}
			else{
				System.out.println("Store not found :(");
				Logger.getLogger().log(LogLevel.error,"Store not found by user search");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
}
