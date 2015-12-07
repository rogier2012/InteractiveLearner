package controller;

import model.FeedbackSet;
import view.FeedBackView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class FeedBackController implements PanelController{
    private FeedbackSet feedBackSet;
    private FeedBackView feedBackView;


    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return null;
    }
}
