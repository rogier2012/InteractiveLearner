package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rogier on 25-11-15
 */
public class TestedSet {
    private Map<String, String> result;

    public void addClassifiedDocument(String clss, String document){
        if (result == null){
            result = new HashMap<>();
        }
        result.put(document,clss);
    }

    public Map<String, String> getResult() {
        return result;
    }
}
