package com.danielrc91.sortable.parser;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danielrc91.sortable.model.Listing;

import junit.framework.Assert;

public class ListingParserTest{

	private static ListingParser parser;
	
	@BeforeClass
	public static void setUp() throws Exception{
		parser = new ListingParser();
	}
	
	@Test
	public void testSimpleLine(){
		String line = "{\"title\":\"LED Flash Macro Ring Light (48 X LED) with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses\"," +
				"\"manufacturer\":\"Neewer Electronics Accessories\"" +
				",\"currency\":\"CAD\",\"price\":\"35.99\"}";
		
		
		Listing listing = parser.read(line);
		
		Assert.assertEquals("CAD",	listing.getCurrency());
		Assert.assertEquals("Neewer Electronics Accessories", listing.getManufacturer());
		Assert.assertEquals("35.99", listing.getPrice());
		
		
	}
	
}
