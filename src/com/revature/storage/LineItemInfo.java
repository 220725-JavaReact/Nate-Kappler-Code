package com.revature.storage;

import com.revature.models.LineItem;

public class LineItemInfo {
	private LineItem[] backarray;
	private int lastind;
	
	public LineItemInfo() {
		backarray = new LineItem[2];
		lastind = 0;
	}
	
	public void add(LineItem newLine) {
		if (lastind == backarray.length) {
			int newSize = (int) (lastind * 1.5);
			LineItem[] newBacking = new LineItem[newSize];
			System.arraycopy(backarray, 0, newBacking, 0, backarray.length);
			backarray=newBacking;
		}
		backarray[lastind] = newLine;
		lastind++;
	}
	
	public LineItem[] getAllElements() {
		return backarray;
	}
}
