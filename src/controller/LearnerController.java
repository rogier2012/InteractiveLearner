package controller;

import view.HomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        testImportController = new TestImportController();
        testImportController.addNextButtonListener(this);
        testerController = new TesterController();
        resultController = new ResultController();
//        this.nextPanel(null);
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
            this.nextPanel(testImportController);
            status = 3;
        } else if (status == 3){
//            this.nextPanel(testerController);
            testerController.test(trainerControllerl.getTrainedSet(),testImportController.getDataSet());
            this.nextPanel(resultController);
            resultController.displayResults(testerController.getTestedSet(), testImportController.getDataSet());
            status = 5;
        }
    }
}
