package com.danielrc91.sortable.io;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class JSONReader {

	private File file;
	private StringBuilder jsonBuffer;
	private Reader reader, buffer;
	private InputStream in;
		
	//States of the reader
	private boolean escaped;
	private boolean insideJSON;
	private boolean insideString;
	
	public JSONReader(File file) throws FileNotFoundException{
		this.file = file;
		this.jsonBuffer =  new StringBuilder();
		this.in = new FileInputStream(this.file);
		this.reader = new InputStreamReader(this.in);
		this.buffer = new BufferedReader(this.reader);
		
		this.insideJSON=false;
		this.insideString=false;
		this.escaped = false;
		
	}
	
	public String getJSONLine() throws IOException{
		
		this.jsonBuffer.setLength(0); //Cleaning the buffer
		int r;
		//While not a valid JSON Expression
		while ((r = this.buffer.read()) != -1) {
            char ch =  (char) r;
            if( ch == '\n' || ch == '\r' || ch == '\t')continue;
            this.jsonBuffer.append(ch);
            switch(ch){
            case '{':
            	this.insideJSON=true;
            	break;
            case '\\':
            	this.escaped=true;
            case '"':
            	if(this.escaped) this.escaped=false;
            	this.insideString = !this.insideString;
            	break;
            case '}':
            	if(this.insideString)continue;
            	if(this.insideJSON == false) throw new Error("Invalid JSON Format");
            	this.insideJSON=false;
            	this.insideString=false;
            	return this.jsonBuffer.toString();
            default:
            	this.escaped=false;
            }
            
        }
		return null;
	}
	
	public void close() throws IOException{
		this.buffer.close();
		this.reader.close();
		this.in.close();
	}
	
	
}
