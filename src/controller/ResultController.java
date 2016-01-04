package controller;

import model.ResultSet;
import model.TestImportedDataSet;
import model.TestedSet;
import view.ResultView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Rogier on 30-11-15
 */
public class ResultController implements PanelController{
    private ResultSet resultSet;
    private ResultView resultView;

    public ResultController() {
        resultView = new ResultView();
        resultSet = new ResultSet();
        this.setupGUI();
    }

    @Override
    public void setupGUI() {
        resultView.setupGUI();
    }

    @Override
    public JPanel getView() {
        return resultView;
    }

    public void displayResults(TestedSet testedSet, TestImportedDataSet importedDataSet) {
        resultView.displayDocuments(testedSet.getResult(), importedDataSet);
    }

    public void addNextButtonListener(ActionListener actionListener) {
        resultView.addNextButtonActionListener(actionListener);
    }
}
