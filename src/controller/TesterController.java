package controller;

import helper.FileUtils;
import model.TestImportedDataSet;
import model.TestedSet;
import model.TrainedSet;
import view.TesterView;

import javax.swing.*;
import java.util.*;

import static java.lang.Math.log;

/**
 * Created by Rotgier Monschouwer on 25-11-15
 */
public class TesterController implements PanelController{
    private TestedSet testedSet;
    private TesterView testerView;

    public TesterController() {
        testerView = new TesterView();
        testedSet = new TestedSet();
    }

    @Override
    public void setupGUI() {
        testerView.setupGUI();
    }

    @Override
    public JPanel getView() {
        return testerView;
    }

    public void test(TrainedSet trainedSet, TestImportedDataSet dataSet, boolean chi) {
        Map<String, Map<String, Integer>> trainedMap;
        if (chi) {
            trainedMap = trainedSet.getFilteredWordCount();
        } else {
            trainedMap = trainedSet.getWordCount();
        }
        for (String category : trainedMap.keySet()) {
            Set<String> badWords = new HashSet<>();
            for (String word : trainedMap.get(category).keySet()) {
                if (trainedMap.get(category).get(word) <= (trainedSet.getTotal() / 200)) {
                    badWords.add(word);
                }
            }
            for (String badWord : badWords) {
                trainedMap.get(category).remove(badWord);
            }
        }


        for (String document : dataSet.getData()) {
            Map<String, Double> resultPropabiltyOfDocument = new HashMap<String, Double>();
            List<String> stringSet = new ArrayList<>(Arrays.asList(document.split("\\s+")));
            Set<String> stringList = new HashSet<>();
            List<String> stopwords = Arrays.asList(FileUtils.stopwordList);
            for (String string1: stringSet){
                String result = string1.toLowerCase();
                result = result.replaceAll("[^a-zA-Z]+", "");
                if (!result.equals("") && !stopwords.contains(result)) {
                    stringList.add(result);
                }

            }

            for(String category : trainedMap.keySet()){
                Set<String> localStringSet = new HashSet<>(stringList);
                List<Double> resultProb = new ArrayList<>();
                double propClass = trainedSet.getDocumentCount(category) / ((double) (trainedSet.getTotal()));
                double totalDocumentCount = trainedSet.getDocumentCount(category);
//                for(String word : stringList){
//                    int value = 0;
//                    if (trainedMap.get(category).get(word) != null){
//                         value = trainedMap.get(category).get(word);
//                    }
//
//
//                    double calculatedVal = ((double)value+ 1 )/(totalDocumentCount+2);
//                    double newval = resultProb.get(category) + log(calculatedVal);
//                    resultProb.replace(category, newval);
//                }
                Set<String> vocabulary = new HashSet<>();
                for (String category1 : trainedMap.keySet()) {
                    for (String word : trainedMap.get(category1).keySet()) {
                        vocabulary.add(word);
                    }
                }

                for (String word : vocabulary) {
                    if (localStringSet.contains(word)) {
                        int value = 0;
                        if (trainedMap.get(category).get(word) != null) {
                            value = trainedMap.get(category).get(word);
                        }
                        double calculatedVal = ((double) value + 1) / (totalDocumentCount + 2);
                        double newval = log(calculatedVal);
                        resultProb.add(newval);
                    } else {
                        int value = 0;
                        if (trainedMap.get(category).get(word) != null) {
                            value = trainedMap.get(category).get(word);
                        }
                        double calculatedVal = 1 - ((double) value + 1) / ((double) (totalDocumentCount + 2));
                        double newval = log(calculatedVal);
                        resultProb.add(newval);
                    }
                }
                localStringSet.removeAll(vocabulary);
                for (String word : localStringSet) {
                    int value = 0;
                    double calculatedVal = ((double) value + 1) / ((double) (totalDocumentCount + 2));
                    double newval = log(calculatedVal);
                    resultProb.add(newval);
                }


                double newval = log(propClass);
                resultProb.add(newval);
                double newVal = 0;
                for (Double chance : resultProb) {
                    newVal = newVal + chance;
                }
                resultPropabiltyOfDocument.put(category, newVal);

            }
            String resultclass = getHighestProp(resultPropabiltyOfDocument);
            testedSet.addClassifiedDocument(resultclass, document);

        }
    }

    public TestedSet getTestedSet() {
        return testedSet;
    }

    public String getHighestProp( Map<String, Double> resultPropabiltyOfDocument){
        double result = -Double.MAX_VALUE;
        String resultClass = "ERROR IN GETHIGHESTPROP";
        for (String category : resultPropabiltyOfDocument.keySet()) {
            if (resultPropabiltyOfDocument.get(category) > result) {
                result = resultPropabiltyOfDocument.get(category);
                resultClass = category;
            }

        }
        return resultClass;

    }
}

