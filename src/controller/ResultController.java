package controller;

import model.ResultSet;
import view.ResultView;

import javax.swing.*;

/**
 * Created by Rogier on 30-11-15
 */
public class ResultController implements PanelController{
    private ResultSet resultSet;
    private ResultView resultView;

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return null;
    }
}
