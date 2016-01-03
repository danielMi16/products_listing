package com.danielrc91.sortable.core;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import junit.framework.Assert;

import org.junit.Test;

public class ProductHelperTest {

	@Test
	public void validTitleTest(){
		String title = "DURAGADGET Utra-fine anti-static Blower brush / lens cleaner for Panasonic Lumix FH27, FH25, FP5, FP7, FH5, FH2, S3, S1";
		String[] titleArray = title.toUpperCase().split(" ");
		
		String title2 = "Nikon D90 12.3MP Digital SLR Camera (Body Only)";
		String[] titleArray2 = title2.toUpperCase().split(" ");
		
		String title3 = "Vivitar ViviCam V3746 3.3MP Digital Camera with 3X Optical Zoom";
		String [] titleArray3 = title3.toUpperCase().split(" ");
		
		Assert.assertFalse( ProductHelper.validTitle(titleArray) );
		Assert.assertTrue(ProductHelper.validTitle(titleArray2));
		Assert.assertTrue(ProductHelper.validTitle(titleArray3));
	}
	
	@Test
	public void cleanTitleTest(){
		String[] title = {"1", "2", "3", "4", "5", "6", "700", "8000", "9000", "1000"};
		Queue<String> result =ProductHelper.cleanTitle(title);
		Assert.assertTrue(result.isEmpty());
		
		String[] title2 = {"one","X" ,"two", "333", "four"};
		result = ProductHelper.cleanTitle(title2);
		Assert.assertEquals("one", result.poll());
		Assert.assertEquals("two", result.poll());
		Assert.assertEquals("four", result.poll());
		
	}
	@Test
	public void concatenateWordsTest(){
		String[] title2 = {"one","two","three"};
		LinkedList<String> list = new LinkedList<>(Arrays.asList(title2));
		
		Queue<String> result = ProductHelper.concatenateWords(list);
		Assert.assertEquals("one", result.poll());
		Assert.assertEquals("two", result.poll());
		Assert.assertEquals("three", result.poll());
		Assert.assertEquals("one two", result.poll());
		Assert.assertEquals("one two three", result.poll());
		
	}
	
}
