package team9.websearch.engine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLtoText {

	private static final String PATTERN = "[.][^.]+$";
	public static final String TEXT_FILE_EXT = ".txt";
	private static final String HTML_FILE = "src/html-files";
	private static final String TEXT_FILE = "src/text-files";

	public static void txtFile(String strFileName) throws IOException {
		File file = new File(HTML_FILE + "/" + strFileName);
		Document docs = Jsoup.parse(file, "UTF-8");
		String strTxt = docs.text();
		String fileWithoutExtension = strFileName.replaceFirst(PATTERN, "");
		PrintWriter printWriter = new PrintWriter(TEXT_FILE + "/" + fileWithoutExtension + TEXT_FILE_EXT);
		printWriter.println(strTxt);
		printWriter.close();
	}

	public static void createTxtFiles() throws IOException {
		File files = new File(HTML_FILE);
		File[] filesLst = files.listFiles();
		for (int index = 0; index < filesLst.length; index++) {
			if (filesLst[index].isFile()) {
				txtFile(filesLst[index].getName());
			}
		}
		System.out.println("The file conversion has been completed successfully!!!");
	}

	public static void main(String[] args) throws IOException {
		createTxtFiles();
	}
}
