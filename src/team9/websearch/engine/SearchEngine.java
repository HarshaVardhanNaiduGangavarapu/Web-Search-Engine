package team9.websearch.engine;

import java.util.Scanner;

public class SearchEngine {
	public static void main(String[] args) throws Exception {
		System.out.println("****** Welcome to the Team9's Web Search Engine ******");
		System.out.println("------------------------------------------------------");
		System.out.println("--> Press 1 to crawl the web");
		System.out.println("--> Press 2 to search the word");
		Scanner scanner1 = new Scanner(System.in);
		int number = scanner1.nextInt();
		switch (number) {
		case 1:
			Scanner scanner2 = new Scanner(System.in);
			WebCrawler webCrawler = new WebCrawler();
			System.out.println("Kindly enter the web site URL link here:");
			String link = scanner2.nextLine();
			webCrawler.extractPageLinks(link);
			System.out.println("------------------------------------------------------");
			HTMLtoText.createTxtFiles();
			System.out.println("------------------------------------------------------");
			WebSearch.searchWordsinFiles();
			scanner2.close();
			break;
		case 2:
			WebSearch.searchWordsinFiles();
			break;
		}
		scanner1.close();
	}
}
