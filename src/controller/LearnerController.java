package controller;

import view.HomeView;

import javax.swing.*;

/**
 * Created by Rogier on 25-11-15
 */
public class LearnerController {
    private TesterController testerController = null;
    private TrainerController trainerControllerl;
    private TrainImportController trainImportController = null;
    private TestImportController testImportController = null;
    private FeedBackController feedBackController = null;
    private HomeView homeView;
    private ResultController resultController;
    private JFrame frame;

    public LearnerController() {
        this.setupGUI();
        trainImportController = new TrainImportController();
    }

    public void setupGUI(){
        frame = new JFrame();
        homeView = new HomeView();
        frame.setContentPane(homeView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void nextPanel(PanelController controller){
        frame.setContentPane(controller.getView());
        frame.setVisible(true);
    }
}
