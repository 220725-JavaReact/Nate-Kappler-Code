package com.revature.storage;

import com.revature.models.Order;

public class OrderInfo {
	private Order[] backarray;
	private int lastind;
	
	public OrderInfo() {
		backarray = new Order[2];
		lastind = 0;
	}
	
	public void add(Order newOrd) {
		if (lastind == backarray.length) {
			int newSize = (int) (lastind * 1.5);
			Order[] newBacking = new Order[newSize];
			System.arraycopy(backarray, 0, newBacking, 0, backarray.length);
			backarray=newBacking;
		}
		backarray[lastind] = newOrd;
		lastind++;
	}
	
	public Order[] getAllElements() {
		return backarray;
	}
	
}
