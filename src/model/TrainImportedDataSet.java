package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportedDataSet {
    private Map<String, List<String>> data;

    public TrainImportedDataSet(Map<String, List<String>> data) {
        this.data = data;
    }

    public TrainImportedDataSet() {
        data = new HashMap<>();
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void addDocument(String category, String document) {

        if (!data.containsKey(category)) {
            List<String> list = new ArrayList<>();
            list.add(document);
            data.put(category, list);
        } else {

            for (String string : data.keySet()){
                if (string.equals(category)) {
                    data.get(category).add(document);

                }
            }

        }


    }


}
