package application;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class TextAnalyzer {
	
	public Map<String, Integer> usableWords(File file) throws IOException {
		Map<String, Integer> wordFrequency = new HashMap<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)) ;
			String line = br.readLine();
			while(line != null) {
				if(!line.trim().isEmpty()) {
					String[] words = line.toLowerCase().split(" ");
					
					for(String word : words) {
						if(word != null && !line.trim().isEmpty()) {
							String str = word.replaceAll("[^a-z]", "");
							wordFrequency.put(str, wordFrequency.getOrDefault(str, 0)+1);
						}
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordFrequency;
	}
	
	public List<Entry<String, Integer>> topFrequentWords(Map<String, Integer> wordFrequency) {
		List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequency.entrySet());
		sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		
		return sortedList;
	}
}
