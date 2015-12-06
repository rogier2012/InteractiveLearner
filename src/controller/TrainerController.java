package controller;

import model.TrainImportedDataSet;
import model.TrainedSet;
import view.TrainerView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainerController implements PanelController{
    private TrainedSet trainedSet;
    private TrainerView trainerView;

    public TrainerController() {
//        trainerView = new TrainerView();
        trainedSet = new TrainedSet();
    }

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return trainerView;
    }

    public void train(TrainImportedDataSet dataSet){
        //TODO Train
    }

    public TrainedSet getTrainedSet() {
        return trainedSet;
    }
}
