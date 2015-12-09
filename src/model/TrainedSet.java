package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainedSet {


    // Map of Category name + Map of word and number of documents its in.
    private Map<String,Map<String, Integer>> wordCount;

    public Map<String, Map<String, Integer>> getWordCount() {
        return wordCount;
    }

    public void insert(String clss, String word){
        if (wordCount == null){
            wordCount = new HashMap<>();
        }

        if (!wordCount.containsKey(clss)){
            Map<String, Integer> word1 = new HashMap<>();
            word1.put(word, 1);
            wordCount.put(clss, word1);
        } else {
            if (!wordCount.get(clss).containsKey(word)){
                wordCount.get(clss).put(word,1);
            } else {
                int value = wordCount.get(clss).get(word);
                wordCount.get(clss).replace(word,value + 1);
            }
        }
    }
}
