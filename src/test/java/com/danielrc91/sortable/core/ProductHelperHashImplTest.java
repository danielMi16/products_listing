package com.danielrc91.sortable.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.danielrc91.sortable.model.Product;

public class ProductHelperHashImplTest {

	private ProductHelperHashImpl productHelper;
	
	@Before
	public  void setUp(){
		productHelper = new ProductHelperHashImpl();
	}
	
	
	@Test
	public void addProductTest(){
		Product p = new Product();
		p.setAnnounced_date("Tomorrow");
		p.setManufacturer("Sony");
		p.setProduct_name("Sony_Cyber-shot_DSC-W310");
		p.setModel("DSC-W310");
		
		Product p1 = new Product();
		p1.setAnnounced_date("Tomorrow");
		p1.setManufacturer("Sony");
		p1.setProduct_name("Sony_Cyber-shot_DSC-W311");
		p1.setModel("DSC-W311");
		
		productHelper.addProduct(p);
		productHelper.addProduct(p1);
		Assert.assertEquals(2, productHelper.getNumberProducts());
		Assert.assertNotNull( productHelper.manufacturerOnList("Sony"));
		Assert.assertNull(productHelper.manufacturerOnList("Casio"));
	}
	
	@Test
	public void getProductTest(){
		Product p = new Product();
		p.setAnnounced_date("Tomorrow");
		p.setManufacturer("Sony");
		p.setProduct_name("Sony_Cyber-shot_DSC-W310");
		p.setModel("DSC-W310");
		
		Product p1 = new Product();
		p1.setAnnounced_date("Tomorrow");
		p1.setManufacturer("Sony");
		p1.setProduct_name("Sony_Cyber-shot_DSC-W311");
		p1.setModel("DSC-W311");
		
		productHelper.addProduct(p);
		productHelper.addProduct(p1);
		
		
		String realTitle = "Sony DSCW310 Cyber-Shot Digital Camera (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Black";
		String notExisting = "Sony DSC-W310S Digitalkamera (12 Megapixel, 28mm Weitwinkelobjektiv mit 4fach optischem Zoom, 6,9 cm (2,7 Zoll) LC-Display) silber";
		String trickyOne = "Fototasche, Kameratasche Modell Hardcase, hellblau, im Set mit 4 GB SD-Card für Sony DSC-310, W530, W510, J10, T110, TX100V, TX10, T99, WX5, TX5 W310, 320, 350, 380";
		Assert.assertNotNull(productHelper.getProduct(realTitle, "SONY"));
		Assert.assertNull(productHelper.getProduct(notExisting, "SONY"));
		Assert.assertNull(productHelper.getProduct(trickyOne, "SONY"));
	}

}	
