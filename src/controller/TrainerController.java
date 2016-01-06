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

    public void train(TrainImportedDataSet dataSet, boolean chi) {
        List<String> stopwords = Arrays.asList(FileUtils.stopwordList);
        Map<String, List<String>> data = dataSet.getData();
        for (String category : data.keySet()) {
            for (String document : data.get(category)) {
                Set<String> stringSet = new HashSet<>(Arrays.asList(document.split("\\s+")));
                Set<String> strings = new HashSet<>();
                for (String word : stringSet) {
                    String result = word.toLowerCase();
                    result = result.replaceAll("[^a-zA-Z]+", "");
                    if (!result.equals("") && !stopwords.contains(result)) {
                        strings.add(result);
                    }

                }
                for (String word : strings) {
                    trainedSet.insert(category, word);
                }
                trainedSet.insertDocument(category);
            }
        }
        if (chi) {
            trainedSet.newFilterSet();
            this.featureSelection();
        }

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
//                Calculate Chi Square value per word
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
//      Filter words that occur in < 3 documents
        for (String category : chiSquares.keySet()) {
            Set<String> badWords = new HashSet<>();
            for (String word : chiSquares.get(category).keySet()) {
                if (wordCount.get(category).get(word) < 3) {
                    badWords.add(word);
                }
            }
            for (String bad : badWords) {
                chiSquares.remove(bad);
            }
        }
//      Filter words that have a chi-square value < 4
        for (String category : chiSquares.keySet()) {
            Set<String> badWords = new HashSet<>();
            for (String word : chiSquares.get(category).keySet()) {
                if (chiSquares.get(category).get(word) < 4) {
                    badWords.add(word);
                }
            }
            for (String bad : badWords) {
                chiSquares.get(category).remove(bad);
            }
        }
//      Put best chi square words in their right category
        for (String category : chiSquares.keySet()) {
            for (String category1 : chiSquares.keySet()) {
                if (!category.equals(category1)) {
                    for (String word : chiSquares.get(category).keySet()) {
                        if (chiSquares.get(category1).containsKey(word)) {
                            if (wordCount.get(category).get(word) > wordCount.get(category1).get(word)) {
                                chiSquares.get(category1).remove(word);
                            }
                        }
                    }
                }
            }
        }
//      Publish the chisquare data to a txt file
        this.publishChiSquareData(chiSquares);
//      Fill the filtered set
        for (String category : chiSquares.keySet()) {
            for (String word : chiSquares.get(category).keySet()) {
                    trainedSet.filteredInsert(category, word, wordCount.get(category).get(word));
            }
        }
    }

    public void publishChiSquareData(Map<String, Map<String, Integer>> chiSquareData) {
        List<String> file = new ArrayList<>();
        for (String category : chiSquareData.keySet()) {
            file.add(category);
            for (String word : chiSquareData.get(category).keySet()) {
                file.add(word + ": " + chiSquareData.get(category).get(word));
            }
        }
        FileUtils.makeTxtFile(file);
    }
}
