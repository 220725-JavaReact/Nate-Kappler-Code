package com.revature.models;

public class LineItem {
	private int productId;
	private int Quantity;
	private int Lid;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public int getLid() {
		return Lid;
	}
	public void setLid(int lid) {
		Lid = lid;
	}
	
	LineItem(){
		
	}
	
	public LineItem(int p, int q, int l){
		productId = p;
		Quantity = q;
		Lid = l;
	}
	
}
