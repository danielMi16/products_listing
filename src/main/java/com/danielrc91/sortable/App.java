package com.danielrc91.sortable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.danielrc91.sortable.core.ProductHelper;
import com.danielrc91.sortable.core.ProductHelperHashImpl;
import com.danielrc91.sortable.core.ProductMatcher;
import com.danielrc91.sortable.io.JSONReader;
import com.danielrc91.sortable.model.Listing;
import com.danielrc91.sortable.model.Product;
import com.danielrc91.sortable.model.Result;
import com.danielrc91.sortable.parser.ListingParser;
import com.danielrc91.sortable.parser.ProductParser;


public class App 
{
    private static final String OUTPUT_FILE = "output.txt";
	public static void main( String[] args ) throws IOException
    {

    	
    	if(args.length != 2){
    		printUsage();
    		return;
    	}
    	System.out.println("---------------------------------------------------");
    	System.out.println("Starting at: "+new Date().toString());
    	long intTime = System.nanoTime();
    	
		JSONReader productReader = new JSONReader(new File(args[0]));
		JSONReader listingReader = new JSONReader(new File(args[1]));
    	Hashtable<String, List<Listing>> results = new Hashtable<>();
		
		ProductHelper productHelper = new ProductHelperHashImpl();
		ProductMatcher productMatcher = new ProductMatcher(productHelper);
		
		ProductParser productParser = new ProductParser();
		ListingParser listingParser = new ListingParser();
		
		PrintWriter out = new PrintWriter(new File(OUTPUT_FILE));

		
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
				if(results.containsKey(result.getProduct_name())){
					List<Listing> r =results.get(result.getProduct_name());
					r.add(listing);
				}
				else{
					ArrayList<Listing> l = new ArrayList<>();
					l.add(listing);
					results.put(result.getProduct_name(), l);
				}
			}
			listingLine = listingReader.getJSONLine();
		}
		listingReader.close();
		productReader.close();
		
		System.out.println("Tries: "+productMatcher.getNumberTries());
		System.out.println("Possitives: "+productMatcher.getPositiveMatches());
		
		
		Enumeration<String> enumKey = results.keys();
		while(enumKey.hasMoreElements()) {
		    String key = enumKey.nextElement();
		    List<Listing> val = results.get(key);
		    Result r = new Result();
		    r.setListing(val);
		    r.setProductName(key);
		    out.println(r.toString());
		}
		out.close();
		long endTime = System.nanoTime();
		int difference = (int) ((endTime - intTime)/1e6);

    	System.out.println("Finished at: "+new Date().toString());
    	System.out.println("---------------------------------------------------");
		System.out.println("Results written to: "+OUTPUT_FILE);
		System.out.println("Tries: "+productMatcher.getNumberTries());
		System.out.println("Possitives: "+productMatcher.getPositiveMatches());
		System.out.println("Running time: "+difference+" milliseconds");
		
		
       
    }
    private static void printUsage(){
		System.out.println("Usage: mvn exec:java <products_file> <listings_file>");
    }
    
}
