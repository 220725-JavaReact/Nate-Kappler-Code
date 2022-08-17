package com.revature.ui;

import java.util.Scanner;

import com.revature.logic.DAO;
import com.revature.logic.LineItemDBLogic;
import com.revature.logic.OrderDBLogic;
import com.revature.logic.ProductDBLogic;
import com.revature.logic.StoreFrontDBLogic;
import com.revature.logging.Logger;
import com.revature.logging.Logger.LogLevel;
import com.revature.logic.CustomerDBLogic;
import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.models.Product;

public class Driver {

	public static void main(String[] args) {
		DAO<Customer> custDao = new CustomerDBLogic();
		DAO<Order> ordDao = new OrderDBLogic();
		DAO<Product> prodDao = new ProductDBLogic();
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Welcome to BuckBloster, the #1 place to buy movies! -");
		System.out.println("-           Below are your menu options               -");
		System.out.println("- [Customer]: Perform customer operations             -");
		System.out.println("- [Order]: Work on/view orders                        -");
		System.out.println("- [Inventory]: View availible movies                  -");
		System.out.println("- [Admin]: Perform administration tasks               -");
		System.out.println("- [Exit]: Exit                                        -");
		System.out.println("-------------------------------------------------------");
		
		Scanner s = new Scanner(System.in);;
		String input = "";
		do {
			System.out.println("Input:");
			input = "";
			input = s.nextLine();
			
			if(input.equalsIgnoreCase("customer")){
				do {
					System.out.println("Ok what do you want to do?");
					System.out.println("[Register] Register a new customer in our database");
					System.out.println("[Search] Search for a customer profile in our database");
					System.out.println("[Menu] Go back to the menu");
					System.out.println("[Exit] Exit the program");
					
					input = s.nextLine();
					
					if(input.equalsIgnoreCase("register")) {
						custDao.register();
						break;
					}
					else if(input.equalsIgnoreCase("search")) {
						CustomerDBLogic.SearchCustomer();
						break;
					}
					else if(input.equalsIgnoreCase("exit")) {
						System.out.println("Exiting the program");
						return;
					}
				}while(!(input.equalsIgnoreCase("menu")));
			}
			else if(input.equalsIgnoreCase("order")){
				do {
					System.out.println("Ok what do you want to do?");
					System.out.println("[Buy] Order in a movie");
					System.out.println("[Search] Find the orders of a customer");
					System.out.println("[Menu] Go back to the menu");
					System.out.println("[Exit] Exit the program");
					
					input = s.nextLine();
					
					if(input.equalsIgnoreCase("buy")) {
						ordDao.register();
						break;
					}
					else if(input.equalsIgnoreCase("search")) {
						OrderDBLogic.SearchOrder();
						break;
					}
					else if(input.equalsIgnoreCase("exit")) {
						System.out.println("Exiting the program");
						return;
					}
				}while(!(input.equalsIgnoreCase("menu")));
			}
			else if(input.equalsIgnoreCase("inventory")){
				do {
					System.out.println("Ok what do you want to do?");
					System.out.println("[List] Show our movie lineup");
					System.out.println("[Store] Show a stores stock");
					System.out.println("[Search] Find a movie");
					System.out.println("[Menu] Go back to the menu");
					System.out.println("[Exit] Exit the program");
					
					input = s.nextLine();
					
					if(input.equalsIgnoreCase("list")) {
						System.out.println("           Buckbloster product list           ");
						System.out.println("----------------------------------------------");
						for(Product p : prodDao.getAll()) {
							if(p==null) break;
							System.out.println("Title: "+p.getName());
							System.out.println("Product Id: "+p.getPid());
							System.out.println("Category: "+p.getCategory());
							System.out.println("Description: "+p.getDesc());
							System.out.println("Price: $"+p.getPrice());
							System.out.println("----------------------------------------------");
						}
						break;
					}
					else if(input.equalsIgnoreCase("search")) {
						ProductDBLogic.SearchMovie();
						break;
					}
					else if(input.equalsIgnoreCase("store")) {
						ProductDBLogic.ShowStoreStock();
						break;
					}
					else if(input.equalsIgnoreCase("exit")) {
						System.out.println("Exiting the program");
						return;
					}
				}while(!(input.equalsIgnoreCase("menu")));
			}
			else if(input.equalsIgnoreCase("admin")){
				do {
					System.out.println("Ok what do you want to do?");
					System.out.println("[History] Output the order history of a store");
					System.out.println("[Restock] Replenish the inventory of a store");
					//System.out.println("[Product] Add a new product to the database");
					System.out.println("[Menu] Go back to the menu");
					System.out.println("[Exit] Exit the program");
					
					input = s.nextLine();
					
					if(input.equalsIgnoreCase("history")) {
						StoreFrontDBLogic.SearchOrder();
					}
					else if(input.equalsIgnoreCase("restock")) {
						LineItemDBLogic.restock();
					}
					else if(input.equalsIgnoreCase("product")) {
						//prodDao.register();
					}
					else if(input.equalsIgnoreCase("exit")) {
						System.out.println("Exiting the program");
						return;
					}
				}while(!(input.equalsIgnoreCase("menu")));		
			}
			else if(input.equalsIgnoreCase("exit")){
				System.out.println("Sorry to see you go :(");
				break;
			}
			else {
				System.out.println("Invalid input, please try again");
			}
			
			//Reprint Menu options
			System.out.println("-------------------------------------------------------");
			System.out.println("- Welcome to BuckBloster, the #1 place to buy movies! -");
			System.out.println("-           Below are your menu options               -");
			System.out.println("- [Customer]: Perform customer operations             -");
			System.out.println("- [Order]: Work on/view orders                        -");
			System.out.println("- [Inventory]: View availible movies                  -");
			System.out.println("- [Admin]: Perform administration tasks               -");
			System.out.println("- [Exit]: Exit                                        -");
			System.out.println("-------------------------------------------------------");
		}while(!(input.equalsIgnoreCase("Exit")));
		//Close the scanner
		s.close();
	}
	
}
