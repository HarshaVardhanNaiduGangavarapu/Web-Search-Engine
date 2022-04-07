package team9.websearch.engine;

public class EditDistanceAlgorithm {
	public static int findMinimumDistance(String first_word, String second_word) {
		int word1Length = first_word.length();
		int word2Length = second_word.length();

		int[][] cost = new int[word1Length + 1][word2Length + 1];
		for (int index = 0; index <= word1Length; index++) {
			cost[index][0] = index;
		}
		for (int index = 1; index <= word2Length; index++) {
			cost[0][index] = index;
		}
		for (int index = 0; index < word1Length; index++) {
			for (int k = 0; k < word2Length; k++) {
				if (first_word.charAt(index) == second_word.charAt(k)) {
					cost[index + 1][k + 1] = cost[index][k];
				}
				else {
					int u = cost[index][k];
					int v = cost[index][k + 1];
					int w = cost[index + 1][k];
					cost[index + 1][k + 1] = u < v ? (u < w ? u : w) : (v < w ? v : w);
					cost[index + 1][k + 1]++;
				}
			}
		}
		return cost[word1Length][word2Length];
	}
}
