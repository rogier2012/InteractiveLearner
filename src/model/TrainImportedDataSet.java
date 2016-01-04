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

    public void addDocument(String clss, String document){

        if (!data.containsKey(clss)){
            List<String> list = new ArrayList<>();
            list.add(document);
            data.put(clss, list);
        } else {

            for (String string : data.keySet()){
                if (string.equals(clss)){
                    data.get(clss).add(document);

                }
            }

        }


    }


}
