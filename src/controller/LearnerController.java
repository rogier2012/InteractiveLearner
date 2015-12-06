package controller;

import view.HomeView;

import javax.swing.*;
import java.awt.*;

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
        this.nextPanel(trainImportController);
        trainerControllerl = new TrainerController();
        trainerControllerl.train(trainImportController.getData());
        testImportController = new TestImportController();
        this.nextPanel(testImportController);
        testerController = new TesterController();
        testerController.test(testImportController.getDataSet());
        resultController = new ResultController();
//        this.nextPanel(null);
    }

    public void setupGUI(){
        frame = new JFrame();
        homeView = new HomeView();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension);
//        frame.setContentPane(homeView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void nextPanel(PanelController controller){
        frame.setContentPane(controller.getView());
        frame.setVisible(true);
    }
}
