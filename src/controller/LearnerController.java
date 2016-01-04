package controller;

import model.TrainImportedDataSet;
import view.HomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Rogier on 25-11-15
 */
public class LearnerController implements ActionListener{
    private TesterController testerController = null;
    private TrainerController trainerControllerl;
    private TrainImportController trainImportController = null;
    private TestImportController testImportController = null;
    private FeedBackController feedBackController = null;
    private HomeView homeView;
    private ResultController resultController;
    private JFrame frame;
    private int status;

    public LearnerController() {
        this.setupGUI();
        trainImportController = new TrainImportController();
        trainerControllerl = new TrainerController();
    }

    public void setupGUI(){
        frame = new JFrame();
        homeView = new HomeView();
        homeView.setupGUI();
        homeView.addNextButtonActionListener(this);
        frame.setSize(500,500);
        frame.setContentPane(homeView);
        status = 0;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void nextPanel(PanelController controller){
        frame.setContentPane(controller.getView());
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (status == 0) {
            int categoryAmount = homeView.getCategoryAmount();
            trainImportController.setCategoryAmount(categoryAmount);
            trainImportController.addNextButtonListener(this);
            this.nextPanel(trainImportController);
            status = 1;
        } else if (status == 1) {
//            this.nextPanel(trainerControllerl);
            trainerControllerl.train(trainImportController.getData());
            testImportController = new TestImportController();
            testImportController.addNextButtonListener(this);
            this.nextPanel(testImportController);
            status = 3;
        } else if (status == 3){
//            this.nextPanel(testerController);
            testerController = new TesterController();
            testerController.test(trainerControllerl.getTrainedSet(),testImportController.getDataSet());
            resultController = new ResultController();
            resultController.addNextButtonListener(this);
            this.nextPanel(resultController);
            resultController.displayResults(testerController.getTestedSet(), testImportController.getDataSet());
            status = 5;
        } else if (status == 5) {
            feedBackController = new FeedBackController();
            feedBackController.addNextButtonListener(this);
            this.nextPanel(feedBackController);
            feedBackController.feedback(testerController.getTestedSet().getResult(), new ArrayList<>(trainerControllerl.getTrainedSet().getWordCount().keySet()), testImportController.getDataSet());
            status = 6;
        } else if (status == 6) {
            trainerControllerl.train(new TrainImportedDataSet(feedBackController.getFeedBackSet().getData()));
            testImportController = new TestImportController();
            testImportController.addNextButtonListener(this);
            this.nextPanel(testImportController);
            status = 3;
        }
    }
}
