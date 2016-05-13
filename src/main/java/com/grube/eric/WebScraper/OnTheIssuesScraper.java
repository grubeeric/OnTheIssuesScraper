package com.grube.eric.WebScraper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OnTheIssuesScraper {

	public static void main(String[] args) {
		OnTheIssuesScraper app = new OnTheIssuesScraper();
		Document doc = app.connectToCandidatePage("Bernie Sanders");
		Elements links = app.getCandidatesViews(doc);
		for (Element link : links) {
			System.out.println(link);
		}
		try {
			BufferedImage bernieImage = app.getCandImage("Bernie Sanders");
			ImageIO.write(bernieImage, "jpg", new File("C:\\Users\\egrube\\Pictures\\testIMG\\bernie.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// returns a document containing the html to the candidate
	public Document connectToCandidatePage(String candName) {
		candName = candName.replace(' ', '_');
		String candURL = "http://www.ontheissues.org/" + candName + ".htm";

		try {
			Document doc = Jsoup.connect(candURL).userAgent("Mozilla/5.0").timeout(30000).get();;
			return doc;

		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("Problem with Candidate name: " + candName);
			
		}

		return null;
	}

	//returns all "ul" links containing the candidates views
	public Elements getCandidatesViews(Document doc) {
		Elements links = doc.select("ul");
		return links;
	}

	//returns the candidate portrait image
	public BufferedImage getCandImage(String candName) throws IOException {
		BufferedImage image = null;
		candName = candName.replace(' ', '_');
		String candImage = "http://www.ontheissues.org/" + candName + ".jpg";
		
		try{
			URL url = new URL(candImage);
			image = ImageIO.read(url);
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Problem with image url: " + candImage);
		}
		
		return image;
	}
}
