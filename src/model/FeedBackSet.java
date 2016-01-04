package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogier on 25-11-15
 */
public class FeedBackSet {
    private Map<String, List<String>> data;

    public FeedBackSet() {
        data = new HashMap<>();
    }

    public void insert(String document, String category) {
        if (!data.containsKey(category)) {
            List<String> documents = new ArrayList<>();
            documents.add(document);
            data.put(category, documents);
        } else {
            data.get(category).add(document);
        }

    }

    public Map<String, List<String>> getData() {
        return data;
    }
}
