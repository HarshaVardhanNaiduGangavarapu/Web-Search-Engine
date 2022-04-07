package team9.websearch.engine;

public class BoyerMooreAlgorithm {
	private final static int charactersCount = 1000000;

	public static int findMaximum(int num1, int num2) {
		if (num1 > num2) {
			return num1;
		}
		return num2;
	}

	public static void findBadCharacters(char[] charArray, int length, int badCharacterArray[]) {
		for (int index = 0; index < charactersCount; index++) {
			badCharacterArray[index] = -1;
		}
		for (int index = 0; index < length; index++) {
			badCharacterArray[(int) charArray[index]] = index;
		}
	}

	public static int find(char textArray[], char pattern[]) {
		int patternSize = pattern.length;
		int textSize = textArray.length;
		int result = 0;
		int badCharArray[] = new int[charactersCount];
		findBadCharacters(pattern, patternSize, badCharArray);
		int length = 0;
		while (length <= (textSize - patternSize)) {
			int size = patternSize - 1;
			while (size >= 0 && pattern[size] == textArray[length + size])
				size--;
			if (size < 0) {
				System.out.println("The search word is found at following position " + length);
				result++;
				length += (length + patternSize < textSize)
						? patternSize - badCharArray[textArray[length + patternSize]]
						: 1;
			} else
				length += findMaximum(1, size - badCharArray[textArray[length + size]]);
		}
		return result;
	}
}