package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainedSet {


    // Map of Category name + Map of word and number of documents its in.
    private Map<String,Map<String, Integer>> wordCount;

    private Map<String, Map<String, Integer>> filteredWordCount;

    // Map of category name + number of documents in that category.
    private Map<String, Integer> totalDocuments;

    private Map<String, Integer> filteredTotalDocuments;

    public Map<String, Map<String, Integer>> getWordCount() {
        return wordCount;
    }

    public Map<String, Map<String, Integer>> getConcurrentWordCount() {
        Map<String, Map<String, Integer>> concurrentWordCount = new ConcurrentHashMap<String, Map<String, Integer>>();
        concurrentWordCount.putAll(wordCount);
        return concurrentWordCount;
    }

    public Map<String, Integer> getTotalDocuments(){
        return totalDocuments;
    }

    public double getDocumentCount(String category){
        double result = 0;
        if (totalDocuments.containsKey(category)) {
            result = totalDocuments.get(category);
        }
        return result;
    }



    public void insert(String category, String word){
        if (wordCount == null){
            wordCount = new HashMap<>();
        }
        if (totalDocuments == null){
            totalDocuments = new HashMap<>();
        }

        if (!wordCount.containsKey(category)){
            Map<String, Integer> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put(word, 1);
            wordCount.put(category, stringIntegerHashMap);

        } else {
            if (!wordCount.get(category).containsKey(word)){
                wordCount.get(category).put(word,1);
            } else {
                int value = wordCount.get(category).get(word);
                wordCount.get(category).replace(word,value + 1);
            }

        }
    }

    public void insertDocument(String category){
        if (totalDocuments == null){
            totalDocuments = new HashMap<>();
        }

        if (!totalDocuments.containsKey(category)) {
            totalDocuments.put(category, 1);
        } else {
            totalDocuments.replace(category,totalDocuments.get(category)+1);
        }
    }


    public void filteredInsert(String category, String word) {
        if (wordCount == null) {
            filteredWordCount = new HashMap<>();
        }


        if (!filteredWordCount.containsKey(category)) {
            Map<String, Integer> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put(word, 1);
            filteredWordCount.put(category, stringIntegerHashMap);

        } else {
            if (!filteredWordCount.get(category).containsKey(word)) {
                filteredWordCount.get(category).put(word, 1);
            } else {
                int value = wordCount.get(category).get(word);
                filteredWordCount.get(category).replace(word, value + 1);
            }

        }
    }

    public void filteredInsertDocument(String category) {
        if (filteredTotalDocuments == null) {
            filteredTotalDocuments = new HashMap<>();
        }

        if (!filteredTotalDocuments.containsKey(category)) {
            filteredTotalDocuments.put(category, 1);
        } else {
            filteredTotalDocuments.replace(category, filteredTotalDocuments.get(category) + 1);
        }
    }
}
