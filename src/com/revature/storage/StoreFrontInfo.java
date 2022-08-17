package com.revature.storage;

import com.revature.models.StoreFront;

public class StoreFrontInfo {
	private StoreFront[] backarray;
	private int lastind;
	
	public StoreFrontInfo() {
		backarray = new StoreFront[2];
		lastind = 0;
	}
	
	public void add(StoreFront newProd) {
		if (lastind == backarray.length) {
			int newSize = (int) (lastind * 1.5);
			StoreFront[] newBacking = new StoreFront[newSize];
			System.arraycopy(backarray, 0, newBacking, 0, backarray.length);
			backarray=newBacking;
		}
		backarray[lastind] = newProd;
		lastind++;
	}
	
	public StoreFront[] getAllElements() {
		return backarray;
	}
}
