package controller;

import model.TrainedSet;
import view.TrainerView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainerController implements PanelController{
    private TrainedSet trainedSet;
    private TrainerView trainerView;

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return trainerView;
    }
}
