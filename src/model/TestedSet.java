package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Rogier on 25-11-15
 */
public class TestedSet {
    private Map<String, String> result;

    public void addClassifiedDocument(String clss, String document){
        if (result == null){
            result = new HashMap<>();
        }
        result.put(clss,document);
    }

    public Map<String, String> getResult() {
        return result;
    }
}
