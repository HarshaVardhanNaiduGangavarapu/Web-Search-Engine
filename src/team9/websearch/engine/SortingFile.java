package team9.websearch.engine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("rawtypes")
class KeyComparator implements Comparator{
	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object wordObject1, Object wordObject2) {
		Map.Entry<?, Integer> value1 = (Map.Entry<String, Integer>) wordObject1;
		Map.Entry<?, Integer> value2 = (Map.Entry<String, Integer>) wordObject2;
		if (value1.getValue() < value2.getValue()) {
			return -1;
		}
		if (value1.getValue() == value2.getValue()) {
			return 0;
		} else {
			return 1;
		}
	}
}

public class SortingFile {
	@SuppressWarnings("unchecked")
	public static void ranking(Hashtable<String, Integer> file, Integer occurances) {
		ArrayList<Map.Entry<String, Integer>> listofwords = new ArrayList<>(file.entrySet());
		Collections.sort(listofwords, new KeyComparator());
		Collections.reverse(listofwords);
		if (occurances != 0) {
			System.out.println("The first five search results for the given search word are shown below:");
			for(int index = 0; index < 5; index++) {
				Map.Entry<String, Integer> map = (Entry<String, Integer>) listofwords.get(index);
				System.out.println("The given search word has appeared '" + map.getValue() + "' times in the following file: '" + map.getKey()+"'");
			}
		} 
		
	}

}