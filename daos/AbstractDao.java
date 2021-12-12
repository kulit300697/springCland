package edu.vinaenter.daos;

import java.util.List;

public abstract class AbstractDao<T> {
	public abstract List<T> selectAll();
	
	public abstract T selectByID(int id);
	
	public abstract int add(T t);
	
	public abstract int del(int id);
	
	public abstract int edit(T t);
	
	public abstract List<T> search(String search);
	
	public abstract int countAll();
	
}
