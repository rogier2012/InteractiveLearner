package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 25-11-15
 */
public class TestImportedDataSet {
    private List<String> data;

    public void addUnclassifiedDocument(String document){
        if(data == null){
            data = new ArrayList<>();
        }
        data.add(document);
    }

    public List<String> getData(){
        return data;
    }

}
