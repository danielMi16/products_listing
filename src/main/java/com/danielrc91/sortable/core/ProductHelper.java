package com.danielrc91.sortable.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.danielrc91.sortable.model.Product;

public abstract class ProductHelper {
	private static final int MINIMUM_MODEL_LENGTH_ACCEPTED = 2;
	private static final String IS_A_NUMBER = "-?\\d+(\\.\\d+)?";
	private static final int INTERESTING_WORDS = 6;
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
	
	protected static Queue<String> concatenateWords(LinkedList<String> words, HashSet<String> manufacturers){
		if(words.isEmpty()) return words;
		Queue<String> result = new LinkedList<>(words);
                if(manufacturers.contains(words.peek())) words.poll();
                int i =0;
		while(i < INTERESTING_WORDS && i < words.size()){
                    
                    StringBuilder concat = new StringBuilder(words.get(i));

                    for(int j = i+1; j< INTERESTING_WORDS && j < words.size(); j++){
                        
                            concat.append(" ");
                            concat.append(words.get(j));
                            result.offer(concat.toString());   
                        
                    }
                    i++;
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
//			if(x.matches(IS_A_NUMBER)) continue;
                        if(x.contains("-")){
                            String[] wordExploted = x.split("-");
                            for(String t: wordExploted){
                                if(x.length() < MINIMUM_MODEL_LENGTH_ACCEPTED)continue;
                                s.offer(t);
                            }
                        }
			s.offer(x);
			
		}
		return s;
	}
	

	
	public abstract int getNumberProducts();
	public abstract void addProduct(Product p);
	public abstract String manufacturerOnList(String manufacturer);
	public abstract Product getProduct(String title, String manufacturer);
	
}
