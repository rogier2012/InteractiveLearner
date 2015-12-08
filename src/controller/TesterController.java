package controller;

import model.TestImportedDataSet;
import model.TestedSet;
import model.TrainedSet;
import view.TesterView;

import javax.swing.*;

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
        for (String document : dataSet.getData()) {
            testedSet.addClassifiedDocument("Male", document);
        }
        System.out.println(testedSet.getResult().size());
    }

    public TestedSet getTestedSet() {
        return testedSet;
    }
}
