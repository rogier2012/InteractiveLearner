package controller;

import model.FeedBackSet;
import model.TestImportedDataSet;
import view.FeedBackView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogier on 25-11-15
 */
public class FeedBackController implements PanelController, ActionListener {
    private FeedBackSet feedBackSet;
    private FeedBackView feedBackView;


    public FeedBackController() {
        feedBackSet = new FeedBackSet();
        feedBackView = new FeedBackView();
        this.setupGUI();
    }

    @Override
    public void setupGUI() {
        feedBackView.setupGUI();
    }

    @Override
    public JPanel getView() {
        return feedBackView;
    }

    public FeedBackSet getFeedBackSet() {
        List<String> documentList = feedBackView.getDocumentList();
        List<ButtonGroup> buttonGroupList = feedBackView.getRadioButtonList();
        for (String document : documentList) {
            feedBackSet.insert(document, buttonGroupList.get(documentList.indexOf(document)).getSelection().getActionCommand());
        }

        return feedBackSet;
    }

    public void feedback(Map<String, String> results, List<String> categories, TestImportedDataSet importedDataSet) {
        feedBackView.displayResults(results, categories, importedDataSet);
    }

    public void addNextButtonListener(ActionListener actionListener) {
        feedBackView.addNextButtonListener(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
