package com.danielrc91.sortable.core;

import com.danielrc91.sortable.model.Product;

public class ProductMatcher {

	
	private static final int RELEVANT_TITLE_WORDS = 4;
	private ProductHelper pH;
	private long positiveMatches;
	private long numberTries;
	
	public ProductMatcher(ProductHelper ph){
		this.pH = ph;
		
	}
	
	public Product tryMatch(String title, String manufacturer){
		this.numberTries++;
		String manufacturerReal = this.pH.manufacturerOnList(manufacturer);
		if(manufacturerReal == null){
			String splitTitle[] = title.split(" ");
			for(int i=0; i < splitTitle.length; i++){
				if(i > RELEVANT_TITLE_WORDS)
					break;
				manufacturerReal = this.pH.manufacturerOnList(splitTitle[i]);
				if(manufacturerReal != null)break;
			}
		}
		
		if(manufacturerReal == null) return null;
		Product x = this.pH.getProduct(title, manufacturerReal);
		if(x != null) this.positiveMatches++;
		return x;
		
	}

	public long getPositiveMatches() {
		return positiveMatches;
	}

	public long getNumberTries() {
		return numberTries;
	}

	
	
	
}
