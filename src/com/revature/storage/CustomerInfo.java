package com.revature.storage;

import com.revature.models.Customer;

public class CustomerInfo {
	private Customer[] backarray;
	private int lastind;
	
	public CustomerInfo() {
		backarray = new Customer[2];
		lastind = 0;
	}
	
	public void add(Customer newCust) {
		if (lastind == backarray.length) {
			int newSize = (int) (lastind * 1.5);
			Customer[] newBacking = new Customer[newSize];
			System.arraycopy(backarray, 0, newBacking, 0, backarray.length);
			backarray=newBacking;
		}
		backarray[lastind] = newCust;
		lastind++;
	}
	
	public Customer[] getAllElements() {
		return backarray;
	}
	
}
