package controller;

import model.TrainImportedDataSet;
import model.TrainedSet;
import view.TrainerView;

import javax.swing.*;
import java.util.*;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainerController implements PanelController{
    private TrainedSet trainedSet;
    private TrainerView trainerView;
    public TrainerController() {
//        trainerView = new TrainerView();
        trainedSet = new TrainedSet();
    }

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return trainerView;
    }

    public void train(TrainImportedDataSet dataSet){
      Map<String, List<String>> data = dataSet.getData();
      for(String category : data.keySet()){
        for (String document : data.get(category)){
            Set<String> stringSet = new HashSet<>(Arrays.asList(document.split("\\s+")));
            for (String word : stringSet) {
                String result = word.toLowerCase();
                result = result.replaceAll("[^a-zA-Z]+", "");
                if (!result.equals("")){
                    trainedSet.insert(category, result);
                }

            }
            trainedSet.insertDocument(category);
        }
      }

    }

    public TrainedSet getTrainedSet() {
        return trainedSet;
    }
}
