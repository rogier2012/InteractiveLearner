package controller;

import model.TestImportedDataSet;
import model.TestedSet;
import view.TesterView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class TesterController implements PanelController{
    private TestedSet testedSet;
    private TesterView testerView;

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return testerView;
    }

    public void test(TestImportedDataSet dataSet){

    }
}
