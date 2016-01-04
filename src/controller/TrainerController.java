package controller;

import helper.FileUtils;
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
        List<String> stopwords = Arrays.asList(FileUtils.stopwordList);
        Map<String, List<String>> data = dataSet.getData();
        for (String category : data.keySet()) {
            for (String document : data.get(category)) {
                Set<String> stringSet = new HashSet<>(Arrays.asList(document.split("\\s+")));
                for (String word : stringSet) {
                    String result = word.toLowerCase();
                    result = result.replaceAll("[^a-zA-Z]+", "");
                    if (!result.equals("") && !stopwords.contains(result)) {
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
                int n11 = wordCount.get(category).get(word);
                int n10 = 0;
                int n00 = 0;
                for (String category1 : wordCount.keySet()) {
                    if (!category1.equals(category)) {
                        int wordInCategory = 0;
                        if (wordCount.get(category1).containsKey(word)) {
                            wordInCategory = wordCount.get(category1).get(word);
                            n10 = n10 + wordInCategory;
                        }
                        n00 = n00 + (trainedSet.getTotalDocuments().get(category1) - wordInCategory);
                    }
                }
                int n01 = trainedSet.getTotalDocuments().get(category) - n11;
                int total = n11 + n10 + n01 + n00;
                double e11 = ((n11 + n10) * (n11 + n01)) / total;
                double e10 = ((n11 + n10) * (n10 + n00)) / total;
                double e01 = ((n01 + n00) * (n11 + n01)) / total;
                double e00 = ((n01 + n00) * (n10 + n00)) / total;

                double eR11 = ((n11 - e11) * (n11 - e11)) / e11;
                double eR10 = ((n10 - e10) * (n10 - e10)) / e10;
                double eR01 = ((n01 - e01) * (n01 - e01)) / e01;
                double eR00 = ((n00 - e00) * (n00 - e00)) / e00;

                int result = (int) Math.floor(eR11 + eR10 + eR01 + eR00);

                chiSquares.get(category).put(word, result);
            }
        }


    }
}
