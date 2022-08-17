package com.revature.logic;

public interface DAO<Type> {
	public void Add(Type newCust);
	public Type[] getAll();
	public void register();
}
