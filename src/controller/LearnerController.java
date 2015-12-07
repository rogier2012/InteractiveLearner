package controller;

import view.HomeView;
import view.TestImportView;
import view.TrainImportView;

import javax.swing.*;
import java.awt.*;
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

    public LearnerController() {
        this.setupGUI();
        trainImportController = new TrainImportController();
        trainImportController.addNextButtonListener(this);
        //for now
        this.nextPanel(trainImportController);
        //
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof TrainImportView){
//            this.nextPanel(trainerControllerl);
            trainerControllerl.train(trainImportController.getData());
        } else if (e.getSource() instanceof TestImportView){
//            this.nextPanel(testerController);
            testerController.test(testImportController.getDataSet());
        }
    }
}
