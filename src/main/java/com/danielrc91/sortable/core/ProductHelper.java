package com.danielrc91.sortable.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.danielrc91.sortable.model.Product;

public abstract class ProductHelper {
	private static final int MINIMUM_MODEL_LENGTH_ACCEPTED = 2;
	private static final String IS_A_NUMBER = "-?\\d+(\\.\\d+)?";
	
	private static HashSet<String> INVALID_WORDS;

	
	private static HashSet<String> getInvalidWords(){
		if(INVALID_WORDS == null){
			INVALID_WORDS = new HashSet<>();
			INVALID_WORDS.add("FOR");
			INVALID_WORDS.add("POUR");
			INVALID_WORDS.add("für".toUpperCase());
		}
		return INVALID_WORDS;
	}
	
	protected static boolean validTitle(String[] words){
		for(String x : words){
			if(getInvalidWords().contains(x)) return false;
		}
		return true;
	}
	
	protected static Queue<String> concatenateWords(LinkedList<String> words){
		if(words.isEmpty()) return words;
		Queue<String> result = new LinkedList<>(words);
		StringBuilder last=new StringBuilder(words.poll());

		while(!words.isEmpty()){
			last.append(" ");
			last.append(words.poll());
			result.offer(last.toString());
		}
		return result;
		
	}
	
	protected static String cleanModel(String model){
		return model.replaceAll("[^a-zA-Z0-9]+","");
	}
	
	protected static LinkedList<String> cleanTitle(String[] words){
		LinkedList<String> s = new LinkedList<>();
		for(String x : words){ 
			if(x.length() < MINIMUM_MODEL_LENGTH_ACCEPTED)continue;
			if(x.matches(IS_A_NUMBER)) continue;
			s.offer(x);
			
		}
		return s;
	}
	

	
	public abstract int getNumberProducts();
	public abstract void addProduct(Product p);
	public abstract String manufacturerOnList(String manufacturer);
	public abstract Product getProduct(String title, String manufacturer);
	
}
