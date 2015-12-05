package controller;

import model.TestImportedDataSet;
import view.TestImportView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class TestImportController implements PanelController{
    private TestImportedDataSet testImportedDataSet = null;
    private TestImportView testImportView;

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return null;
    }
}
