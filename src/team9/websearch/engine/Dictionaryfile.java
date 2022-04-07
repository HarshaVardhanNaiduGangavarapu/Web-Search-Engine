package team9.websearch.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeMap;

public class Dictionaryfile {

	private static final String COLON = "-";
	private static final String DOT = ".";
	private static final String ALPHABET_PATTERN = "[^a-zA-Z]";
	private static final String HTML_FILE_LOCATION = "src/html-files/";
	private static final String TEXT_FILE_LOCATION = "src/text-files/";
	private static final String DICTIONARY_FILE = "dictionary.txt";

	public static TreeMap<String, String> dictionary(String file_name, TreeMap<String, String> listofwords) {
		try {
			File filename = new File(file_name);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			String lineData;
			String wordsArray[] = null;
			while ((lineData = bufferedReader.readLine()) != null) {
				String tempString = lineData.replaceAll(ALPHABET_PATTERN, COLON).toLowerCase();
				wordsArray = tempString.split(COLON);
				for (int index = 0; index < wordsArray.length; index++)
					listofwords.put(wordsArray[index], wordsArray[index]);
			}
			bufferedReader.close();
		} catch (Exception exception) {
			System.out.println("Exception has been found : " + exception.getMessage());
		}
		return listofwords;
	}

	public static void create_Dictionary_File() {
		File directoryLocation = new File(HTML_FILE_LOCATION);
		String[] dictionaryWords = directoryLocation.list();
		TreeMap<String, String> listOfWords = new TreeMap<String, String>();
		for (String word : dictionaryWords) {
			String newString = word.substring(word.lastIndexOf(DOT) + 1);
			if (newString.equals("txt")) {
				try {
					dictionary(TEXT_FILE_LOCATION + "/" + word, listOfWords);
					FileWriter fileWriter = new FileWriter(DICTIONARY_FILE);
					for (String str : listOfWords.values()) {
						fileWriter.write(str + System.lineSeparator());
					}
					fileWriter.close();
				} catch (Exception exception) {
					System.out.println("Exception has been found : " + exception.getMessage());
				}
			}
		}
	}

	public static void main(String[] arhs) {
		create_Dictionary_File();
	}
}