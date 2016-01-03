package com.danielrc91.sortable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.Test;

import com.danielrc91.sortable.core.ProductHelper;
import com.danielrc91.sortable.core.ProductHelperHashImpl;
import com.danielrc91.sortable.core.ProductMatcher;
import com.danielrc91.sortable.io.JSONReader;
import com.danielrc91.sortable.model.Listing;
import com.danielrc91.sortable.model.Product;
import com.danielrc91.sortable.parser.ListingParser;
import com.danielrc91.sortable.parser.ProductParser;

public class IntegrationTest {

	private static final String PATH_TO_TEST_FILES = ClassLoader.getSystemResource("").getPath();

	@Test
	public void smallTest() throws IOException{
		
		JSONReader listingReader = new JSONReader(new File(PATH_TO_TEST_FILES+"smallListing.txt"));
		JSONReader productReader = new JSONReader(new File(PATH_TO_TEST_FILES+"products.txt"));
		
		ProductHelper productHelper = new ProductHelperHashImpl();
		ProductMatcher productMatcher = new ProductMatcher(productHelper);
		
		ProductParser productParser = new ProductParser();
		ListingParser listingParser = new ListingParser();
		
		
		String productLine = productReader.getJSONLine();
		while(productLine != null){
			Product product = productParser.read(productLine);
			productHelper.addProduct(product);
			productLine = productReader.getJSONLine();
		}
		
		String listingLine = listingReader.getJSONLine();
		while(listingLine != null){
			Listing listing = listingParser.read(listingLine);
			Product result = productMatcher.tryMatch(listing.getTitle(), listing.getManufacturer());
			
			if(result != null){
				//Add to result List
			}
			listingLine = listingReader.getJSONLine();
		}
		listingReader.close();
		productReader.close();
		
		System.out.println("Tries: "+productMatcher.getNumberTries());
		System.out.println("Possitives: "+productMatcher.getPositiveMatches());
	}
	
	@Test
	public void bigTest() throws IOException{
    	Hashtable<Product, List<Listing>> results = new Hashtable<>();

		JSONReader listingReader = new JSONReader(new File(PATH_TO_TEST_FILES+"bigfile.txt"));
		JSONReader productReader = new JSONReader(new File(PATH_TO_TEST_FILES+"products.txt"));
		
		ProductHelper productHelper = new ProductHelperHashImpl();
		ProductMatcher productMatcher = new ProductMatcher(productHelper);
		
		ProductParser productParser = new ProductParser();
		ListingParser listingParser = new ListingParser();
		
		
		String productLine = productReader.getJSONLine();
		while(productLine != null){
			Product product = productParser.read(productLine);
			productHelper.addProduct(product);
			productLine = productReader.getJSONLine();
		}
		String listingLine = listingReader.getJSONLine();
		while(listingLine != null){
			Listing listing = listingParser.read(listingLine);
			Product result = productMatcher.tryMatch(listing.getTitle(), listing.getManufacturer());
			
			if(result != null){
				if(results.containsKey(result)){
					List<Listing> r =results.get(result);
					r.add(listing);
				}
				else{
					ArrayList<Listing> l = new ArrayList<>();
					l.add(listing);
					results.put(result, l);
				}
			}
			listingLine = listingReader.getJSONLine();
		}
		listingReader.close();
		productReader.close();
		
		System.out.println("Tries: "+productMatcher.getNumberTries());
		System.out.println("Possitives: "+productMatcher.getPositiveMatches());

	}
	
}
