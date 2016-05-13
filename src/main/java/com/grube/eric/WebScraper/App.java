package com.grube.eric.WebScraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	App app = new App();
        app.connect("http://www.google.com");
    }
    
    public void connect(String URL){
    	try{
        	Document doc = Jsoup.connect(URL).get();
        	String title = doc.title();
        	System.out.println("Connected to " + title + " at " + doc.location());
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
}
