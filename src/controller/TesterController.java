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

import static java.lang.Math.*;

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
            for(String clss : trainedMap.keySet()){
                int totalwordcount = 0;
                for(String key : trainedMap.get(clss).keySet()) {
                    totalwordcount += trainedMap.get(clss).get(key);
                }
                resultProb.put(clss, 0.0);
                for(String word : stringList){
                    if(trainedMap.get(clss).get(word) != null){
                        double calculatedval = trainedMap.get(clss).get(word)/((double)totalwordcount);
                        double newval = resultProb.get(clss) + log(calculatedval);
                        resultProb.replace(clss, newval);

                    }

                }

                double newval = resultProb.get(clss) + log(propClass);
//                System.out.println(newval);
                resultProb.replace(clss, newval);
//                System.out.println(clss);
                resultPropabiltyOfDocument.put(clss, resultProb.get(clss));

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
        double result = -Integer.MIN_VALUE;
        String resultclass = "ERROR IN GETHIGHESTPROP";
        System.out.println(resultPropabiltyOfDocument.keySet().size());
        for(String clss : resultPropabiltyOfDocument.keySet()){

            result =  Math.max(result, resultPropabiltyOfDocument.get(clss));
            if(result == resultPropabiltyOfDocument.get(clss)){
                resultclass = clss;
            }

        }
        return resultclass;

    }
}

