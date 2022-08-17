package com.revature.storage;

import com.revature.models.Product;

public class ProductInfo {
	private Product[] backarray;
	private int lastind;
	
	public ProductInfo() {
		backarray = new Product[2];
		lastind = 0;
	}
	
	public void add(Product newProd) {
		if (lastind == backarray.length) {
			int newSize = (int) (lastind * 1.5);
			Product[] newBacking = new Product[newSize];
			System.arraycopy(backarray, 0, newBacking, 0, backarray.length);
			backarray=newBacking;
		}
		backarray[lastind] = newProd;
		lastind++;
	}
	
	public Product[] getAllElements() {
		return backarray;
	}
}
