package com.danielrc91.sortable.parser;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danielrc91.sortable.model.Product;

public class ProductParserTest {
	
	private static ProductParser parser;
	
	@BeforeClass
	public static void setUp() throws Exception{
		parser = new ProductParser();
	}
	
	@Test
	public void testSimpleLine(){
		String line = "{\"product_name\":\"Sony_Cyber-shot_DSC-W310\"," +
				"\"manufacturer\":\"Sony\"," +
				"\"model\":\"DSC-W310\",\"family\":\"Cyber-shot\"," +
						"\"announced-date\":\"2010-01-06T19:00:00.000-05:00\"}";
		
		
		Product product = parser.read(line);
		
		Assert.assertEquals("Sony_Cyber-shot_DSC-W310",	product.getProduct_name());
		Assert.assertEquals("Sony", product.getManufacturer());
		Assert.assertEquals("DSC-W310", product.getModel());
		
		
	}
}
