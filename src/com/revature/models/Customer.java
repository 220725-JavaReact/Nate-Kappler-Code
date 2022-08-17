package com.revature.models;

import java.util.ArrayList;

public class Customer {
	private String Name;
	private String Address;
	private String Email;
	private String PhoneNum;
	private int c_id;
	private ArrayList<Order> Orders;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	public ArrayList<Order> getOrders() {
		return Orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		Orders = orders;
	}
	
	public Customer() {}
	
	
	public Customer(String Name, String Address, String Email, String PhoneNum, int c_id){
		this.Name = Name;
		this.Address = Address;
		this.Email = Email;
		this.PhoneNum = PhoneNum;
		this.c_id = c_id;
	}
	public int getC_id() {
		return c_id;
	}
	
}