package controller;

import helper.FileUtils;
import model.TrainImportedDataSet;
import view.TrainImportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportController implements ActionListener, PanelController {
    private TrainImportedDataSet trainImportedDataSet = null;
    private TrainImportView trainImportView;
    private int categoryAmount;

    public TrainImportController() {
        trainImportView = new TrainImportView();
        trainImportedDataSet = new TrainImportedDataSet();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton ){
            String category = null;
            File file = FileUtils.getFile("Open a train file");
            JButton button = (JButton) e.getSource();
            if (trainImportView.getFileButtonList().contains(button)) {
                category = trainImportView.getText(trainImportView.getFileButtonList().indexOf(button));
            }
            boolean zip;
            try {
                 zip = FileUtils.isZipFile(file);
            } catch (IOException e1) {
                 zip = false;
            }
            if (zip){
                ZipFile zipFile = null;
                try {
                    zipFile = new ZipFile(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()){
                    ZipEntry zipEntry = entries.nextElement();
                    trainImportedDataSet.addDocument(category, FileUtils.fileToString(zipFile, zipEntry));
                }
                if (trainImportedDataSet.getData().size() == categoryAmount) {
                    trainImportView.setNextButtonEnabled(true);
                }
            }

        }
    }

    public TrainImportedDataSet getData(){
        return trainImportedDataSet;
    }

    public void addNextButtonListener(ActionListener actionListener){
        trainImportView.addNextButtonListener(actionListener);
    }

    @Override
    public void setupGUI() {
        trainImportView.setupGUI();
    }

    @Override
    public JPanel getView() {
        return trainImportView;
    }

    public void setCategoryAmount(int categoryAmount) {
        this.categoryAmount = categoryAmount;
        trainImportView.setCategoryAmount(categoryAmount);
        this.setupGUI();
        trainImportView.addButtonActionListener(this);
        trainImportView.addDocumentListener();
    }
}
