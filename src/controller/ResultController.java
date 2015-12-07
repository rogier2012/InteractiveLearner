package controller;

import model.ResultSet;
import model.TestedSet;
import view.ResultView;

import javax.swing.*;

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

    public void displayResults(TestedSet testedSet){
        resultView.displayDocuments(testedSet.getResult());
    }
}
