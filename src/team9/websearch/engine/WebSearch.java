package team9.websearch.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class WebSearch {
	private static final String DICTIONARY_FILE = "dictionary.txt";
	
	public static void doSpellCheck(String pat) throws IOException {
		try {
			File input_file = new File(DICTIONARY_FILE);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(input_file));
			ArrayList<String> word_dict = new ArrayList<>();
			String search_str = null;
			while ((search_str = bufferedReader.readLine()) != null) {
				word_dict.add(search_str);
			}
			int edit_dist = 10, edit_dist_first_word = 10, edit_dist_second_word = 10;
			int suggestion1 = 0;
			int suggestion2 = 0;
			for (int index = 0; index < word_dict.size(); index++) {
				String d_word = word_dict.get(index);
				edit_dist = EditDistanceAlgorithm.findMinimumDistance(d_word, pat);
				if (edit_dist < edit_dist_first_word) {
					edit_dist_first_word = edit_dist;
					suggestion1 = index;
				}
			}
			for (int index = 0; index < word_dict.size(); index++) {
				String d_word = word_dict.get(index);
				edit_dist = EditDistanceAlgorithm.findMinimumDistance(d_word, pat);
				if (edit_dist < edit_dist_second_word) {
					edit_dist_second_word = edit_dist;
					if (index != suggestion1)
						suggestion2 = index;
				}
			}
			bufferedReader.close();
			System.out.println("Please try searching for: " + word_dict.get(suggestion1) + " or the word "
					+ word_dict.get(suggestion2)+".");
		} catch (Exception exception) {
			System.out.println("Exception has been found." + exception.getLocalizedMessage());
		}
	}

	public int wordSearch(File filePath, String searchingWord) throws IOException {
		String data = "";
		try {
			String s = null;
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((s = br.readLine()) != null) {
				data = data + s;
			}
			br.close();
		} catch (NullPointerException npe) {
			System.out.println("Exception has been found." + npe.getLocalizedMessage());
		}
		char text[] = data.toCharArray();
		char pattern[] = searchingWord.toCharArray();
		int value = BoyerMooreAlgorithm.find(text, pattern);
		if (value != 0) {
			System.out.println("\nThe file that contains all of the listed words" + filePath.getName());
			System.out.println("--------------------------------------\n");
		}
		return value;
	}

	public static void searchWordsinFiles() throws Exception {
		WebSearch ws = new WebSearch();
		Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
		Scanner sc = new Scanner(System.in);
		String selection = "y";
		while (selection.equals("y")) {
			System.out.println("Please enter the search word: ");
			String sword = sc.nextLine();
			int appearance = 0;
			int num = 0;
			try {
				File my_dir = new File("src/text-files");
				File[] fileArray = my_dir.listFiles();
				int a = 0;
				while (a < fileArray.length) {
					appearance = ws.wordSearch(fileArray[a], sword);
					hash.put(fileArray[a].getName(), appearance);
					if (appearance != 0) {
						num++;
					}
					a++;
				}
				System.out.println("\nTotal Number of Files for search input '" + sword + "' is : " + num);
				if (appearance == 0) {
					System.out.println("\nPlease wait while system provides you suggestions regarding search words...\n");
					Dictionaryfile.create_Dictionary_File();
					ws.suggestions(sword);
				}
				SortingFile.ranking(hash, num);
				System.out.println("\n\nDo you want to continue the search?");
				System.out.println("Please enter y if you want to continue");
				System.out.println("Please enter q to exit the search");
				Scanner sc2 = new Scanner(System.in);
				selection = sc2.nextLine();
				if (selection.equals("q")) {
					sc.close();
					sc2.close();
					System.out.println("Thank you for using the Team9's Web Search Engine service... :)");
				}
			} catch (Exception exception) {
				System.out.println("Exception has been found." + exception.getLocalizedMessage());
			}
		}
	}
	
	public void suggestions(String patterns) {
		try {
			doSpellCheck(patterns);
		} catch (Exception exception) {
			System.out.println("Exception has been found." + exception.getLocalizedMessage());
		}
	}
}
