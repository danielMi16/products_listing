package com.danielrc91.sortable.parser;


import com.google.gson.Gson;

public abstract class Parser<T> {
	
	protected static Gson gson = new Gson();
	
	public abstract T read(String line);
	
}
