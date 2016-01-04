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
        for (String category : data.keySet()) {
            for (String document : data.get(category)) {
                Set<String> stringSet = new HashSet<>(Arrays.asList(document.split("\\s+")));
                for (String word : stringSet) {
                    String result = word.toLowerCase();
                    result = result.replaceAll("[^a-zA-Z]+", "");
                    if (!result.equals("")) {
                        trainedSet.insert(category, result);
                    }

                }
                trainedSet.insertDocument(category);
            }
        }
        this.featureSelection();
    }

    public TrainedSet getTrainedSet() {
        return trainedSet;
    }

    public void featureSelection() {
        Map<String, Map<String, Integer>> chiSquares = new HashMap<>();
        Map<String, Map<String, Integer>> wordCount = trainedSet.getWordCount();
        for (String category : wordCount.keySet()) {
            chiSquares.put(category, new HashMap<>());
            for (String word : wordCount.get(category).keySet()) {
                int n11;
                int n10;
                int n01;
                int n00;
                double e11;
                double e10;
                double e01;
                double e00;
                int result;

                chiSquares.get(category).put(word, result);
            }
        }


    }
}
