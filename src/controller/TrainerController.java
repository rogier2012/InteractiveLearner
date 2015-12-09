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
      for(String clss : data.keySet()){
        for (String document : data.get(clss)){
            Set<String> stringSet = new HashSet<>(Arrays.asList(document.split("\\s+")));
            for (String string1: stringSet){
                String result = string1.toLowerCase();
                result = result.replaceAll("[^a-zA-Z]+", "");
                if (!result.equals("")){
                    trainedSet.insert(clss, result);
                }

            }
        }
      }

    }

    public TrainedSet getTrainedSet() {
        return trainedSet;
    }
}
