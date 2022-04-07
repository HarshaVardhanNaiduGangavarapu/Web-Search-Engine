package team9.websearch.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	Integer totalCount = 0;
	private final Set<String> webLinks;
	private static final int DEPTH_MAXIMUM = 900;
	private static final String HTML_FILE_EXT = ".html";
	List<String> webLinksList = new ArrayList<String>();

	public WebCrawler() {
		webLinks = new HashSet<String>();
	}

	public void extractPageLinks(String webSiteUrl) {
		try {
			if (!webLinks.contains(webSiteUrl)) {
				if (webLinks.add(webSiteUrl)) {
					System.out.println("The entered URL is : " + webSiteUrl);
					webLinksList.add(webSiteUrl);
					Integer index = webLinksList.indexOf(webSiteUrl);
					String fileName = "File-" + index;
					saveFile(fileName, webSiteUrl);
				}
				Document htmlDocument = Jsoup.connect(webSiteUrl).get();
				Elements linkPresentInPage = htmlDocument.select("a[href]");
				for (Element pageElement : linkPresentInPage) {
					if (totalCount != DEPTH_MAXIMUM) {
						totalCount++;
						String link = pageElement.attr("abs:href");
						extractPageLinks(link);
					}
				}
			}
		} catch (Exception exception) {
			System.out.println("Exception has been found : " + exception.getMessage());
		}
	}

	public void saveFile(String fileName, String webUrl) {
		try {
			URL url = new URL(webUrl);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/html-files/" + fileName + HTML_FILE_EXT));
			String dataLine;
			while ((dataLine = bufferedReader.readLine()) != null) {
				bufferedWriter.write(dataLine);
			}
			bufferedReader.close();
			bufferedWriter.close();
			System.out.println("We have successfully downloaded file : " + fileName + HTML_FILE_EXT);
		} catch (MalformedURLException malformedURLException) {
			System.out.println("Malformed URL Exception has been found." + malformedURLException.getLocalizedMessage());
		} catch (IOException ioException) {
			System.out.println("IOException has been found." + ioException.getLocalizedMessage());
		}
	}

	public static void main(String[] args) {
		new WebCrawler().extractPageLinks("https://www.uwindsor.ca");
	}
}