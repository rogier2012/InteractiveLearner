package controller;

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

    public void test(TrainedSet trainedSet, TestImportedDataSet dataSet) {
        System.out.println(dataSet.getData().size());
        Map<String, Map<String, Integer>> trainedMap = trainedSet.getFilteredWordCount();
        double propClass = 1/((double)(trainedMap.size()));

        for (String document : dataSet.getData()) {
            Map<String, Double> resultPropabiltyOfDocument = new HashMap<String, Double>();
            Set<String> stringSet = new HashSet<>(Arrays.asList(document.split("\\s+")));
            Set<String> stringList = new HashSet<>();
            for (String string1: stringSet){
                String result = string1.toLowerCase();
                result = result.replaceAll("[^a-zA-Z]+", "");
                if (!result.equals("")){
                    stringList.add(result);
                }

            }
            Map<String, Double> resultProb = new HashMap<String, Double>();
            for(String category : trainedMap.keySet()){
                double totalDocumentCount = trainedSet.getDocumentCount(category);
                resultProb.put(category, 1.0);
                for(String word : stringList){
                    int value = 0;
                    if (trainedMap.get(category).get(word) != null){
                         value = trainedMap.get(category).get(word);
                    }


                    double calculatedVal = ((double)value+ 1 )/(totalDocumentCount+2);
                    double newval = resultProb.get(category) + log(calculatedVal);
                    resultProb.replace(category, newval);



                }

                double newval = resultProb.get(category) + log(propClass);
                resultProb.replace(category, newval);
                resultPropabiltyOfDocument.put(category, resultProb.get(category));

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

