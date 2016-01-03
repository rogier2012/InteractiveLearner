package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 25-11-15
 */
public class TestImportedDataSet {
    private List<String> data;
    private List<String> fileNames;

    public void addUnclassifiedDocument(String document){
        if(data == null){
            data = new ArrayList<>();
        }
        data.add(document);
    }

    public List<String> getData(){
        return data;
    }


    public void addFileName(String filename) {
        if (fileNames == null) {
            fileNames = new ArrayList<>();
        }
        fileNames.add(filename);
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
