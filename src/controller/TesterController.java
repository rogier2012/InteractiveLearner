package controller;

import model.TestImportedDataSet;
import model.TestedSet;
import model.TrainedSet;
import view.TesterView;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.log;

/**
 * Created by Rogier on 25-11-15
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
        Map<String, Map<String, Integer>> trainedMap = trainedSet.getWordCount();
        double propClass = 1/((double)(trainedMap.keySet().size()));

        for (String document : dataSet.getData()) {
            Map<String, Double> resultPropabiltyOfDocument = new HashMap<String, Double>();
            List<String> stringList = Arrays.asList(document.split("\\s+"));
            Map<String, Double> resultProb = new HashMap<String, Double>();
            for(String category : trainedMap.keySet()){
                double totalDocumentCount = trainedSet.getDocumentCount(category);
                resultProb.put(category, 1.0);
                for(String word : stringList){
                    if(trainedMap.get(category).get(word) != null){
                        double calculatedval = ((double)trainedMap.get(category).get(word)+ 1.0 )/((double)totalDocumentCount+2.0);
                        System.out.println(log(calculatedval));
                        double newval = resultProb.get(category) + log(calculatedval);
                        resultProb.replace(category, newval);

                    }

                }

                double newval = resultProb.get(category) + log(propClass);
                resultProb.replace(category, newval);
                resultPropabiltyOfDocument.put(category, resultProb.get(category));

            }
            String resultclass = getHighestProp(resultPropabiltyOfDocument);
            testedSet.addClassifiedDocument(resultclass, document);

        }
        System.out.println(testedSet.getResult().size());
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

