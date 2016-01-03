package com.danielrc91.sortable.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

import com.danielrc91.sortable.model.Product;

public class ProductHelperHashImpl extends ProductHelper {
	
	//param1:Manufacturer Name
	private HashSet<String> manufacturers;
	
	//param1:Manufacturer
	//param2:Model
	private Hashtable<String, Hashtable<String, Product>> models;
	private int numberProducts;

	public ProductHelperHashImpl(){
		this.manufacturers = new HashSet<>();
		this.models = new Hashtable<>();
		this.numberProducts=0;
		
	}
	
	public int getNumberProducts(){
		return this.numberProducts;
	}
	
	public void addProduct(Product p){
		String manufacturer = p.getManufacturer().toUpperCase();

		String model = cleanModel(p.getModel().toUpperCase());
		if(this.manufacturers.add(manufacturer)){
			this.models.put(manufacturer, new Hashtable<String,Product>());
		}
		this.models.get(manufacturer).put(model, p);
		this.numberProducts++;
	}
	
	public String manufacturerOnList(String manufacturer){
		String[] manufacturerArray = manufacturer.toUpperCase().split(" ");
		Queue<String> concatenated = ProductHelper.concatenateWords(new LinkedList<>(Arrays.asList(manufacturerArray)));
		while(!concatenated.isEmpty()){
			if(this.manufacturers.contains(concatenated.peek())) return concatenated.poll();
			concatenated.poll();
		}
		return null;
	}

	public Product getProduct(String title, String manufacturer){
		String[] words = title.toUpperCase().split(" ");
		if(!validTitle(words)) return null;
		LinkedList<String> wordsOfInterest = cleanTitle(words);
		Queue<String> concatenatedWords = concatenateWords(wordsOfInterest);
		Hashtable<String, Product> modelsFromManufacturer = this.models.get(manufacturer);	
		
		Product result = null;
		while(!concatenatedWords.isEmpty()){
			if(modelsFromManufacturer.get(concatenatedWords.peek()) != null){
				result = modelsFromManufacturer.get(cleanModel(concatenatedWords.poll()));
				
			}
			concatenatedWords.poll();
		}
		return result;	
	}
	

	

	

	
}
