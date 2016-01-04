package controller;

import model.FeedBackSet;
import view.FeedBackView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class FeedBackController implements PanelController{
    private FeedBackSet feedBackSet;
    private FeedBackView feedBackView;


    @Override
    public void setupGUI() {
        feedBackView.setupGUI();
    }

    @Override
    public JPanel getView() {
        return null;
    }

    public FeedBackSet getFeedBackSet() {
        return feedBackSet;
    }
}
