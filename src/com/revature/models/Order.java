package com.revature.models;

public class Order {
	private int LineItemID;
	private int CustOrderingID;
	private double OverallPrice;
	private int OrderId;
	private int amount;
	private int storeid;
	
	public int getLineItemID() {
		return LineItemID;
	}
	public void setLineItemID(int lineItemID) {
		LineItemID = lineItemID;
	}
	public int getCustOrderingID() {
		return CustOrderingID;
	}
	public void setCustOrderingID(int custOrderingID) {
		CustOrderingID = custOrderingID;
	}
	public double getOverallPrice() {
		return OverallPrice;
	}
	public void setOverallPrice(double overallPrice) {
		OverallPrice = overallPrice;
	}
	
	public Order(){}
	
	public Order(int LID, int COID, double OP, int OID, int a, int sid){
		LineItemID = LID;
		CustOrderingID = COID;
		OverallPrice = OP;
		OrderId = OID;
		amount = a;
		storeid = sid;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
}
