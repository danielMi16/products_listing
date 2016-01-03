package com.danielrc91.sortable.parser;


import com.danielrc91.sortable.model.Product;

public class ProductParser extends Parser<Product> {

	@Override
	public Product read(String line) {
		
		Product product = gson.fromJson(line, Product.class);
		
		return product;
		
	}

}
