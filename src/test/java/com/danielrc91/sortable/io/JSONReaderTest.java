package com.danielrc91.sortable.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


import junit.framework.Assert;

public class JSONReaderTest {

	private static final String PATH_TO_TEST_FILES = ClassLoader.getSystemResource("").getPath();
	
	@Test
	public void easyFileTest() throws IOException{
		
		JSONReader reader = new JSONReader(new File(PATH_TO_TEST_FILES+"fileWith4Lines.txt"));
		String jsonLine = reader.getJSONLine();
		int lines = 0;
		while ( jsonLine != null ){
			lines++;
			jsonLine = reader.getJSONLine();
			
		}
		reader.close();
		Assert.assertEquals(4, lines);
	}
	
	@Test
	public void tabsFileTest() throws IOException{
		JSONReader reader = new JSONReader(new File(PATH_TO_TEST_FILES+"fileWithTabs.txt"));
		String jsonLine = reader.getJSONLine();
		int lines = 0;
		while ( jsonLine != null ){
			lines++;
			jsonLine = reader.getJSONLine();
			
		}
		reader.close();
		Assert.assertEquals(4, lines);
	}
	
	@Test
	public void mediumFileTest() throws IOException{

		JSONReader reader = new JSONReader(new File(PATH_TO_TEST_FILES+"products.txt"));
		String jsonLine = reader.getJSONLine();
		int lines = 0;
		while ( jsonLine != null ){
			lines++;
			jsonLine = reader.getJSONLine();
			
		}
		reader.close();
		Assert.assertTrue(lines > 700);
	}
	
	@Test
	public void bigFileTest() throws IOException{

		JSONReader reader = new JSONReader(new File(PATH_TO_TEST_FILES+"bigfile.txt"));
		String jsonLine = reader.getJSONLine();
		int lines = 0;
		while ( jsonLine != null ){
			lines++;
			jsonLine = reader.getJSONLine();
			
		}
		reader.close();
		Assert.assertTrue(lines >= 20000);
	}
}
