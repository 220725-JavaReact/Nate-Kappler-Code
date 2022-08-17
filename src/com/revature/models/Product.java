package com.revature.models;

public class Product {
	private String Name;
	private double Price;
	private String Desc;
	private String Category;
	private int pid;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	
	public int getPid() {
		return pid;
	}
	
	public Product(){
		
	}
	
	public Product(String n, double p, String d, String c, int pi){
		Name = n;
		Price = p;
		Desc = d;
		Category = c;
		pid = pi;
	}		
}
