package controller;

import helper.FileUtils;
import model.TrainImportedDataSet;
import view.TrainImportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportController implements ActionListener,PanelController{
    private TrainImportedDataSet trainImportedDataSet = null;
    private TrainImportView trainImportView;


    public TrainImportController() {
        trainImportView = new TrainImportView();
        trainImportedDataSet = new TrainImportedDataSet();
        this.setupGUI();
        trainImportView.addButtonActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton ){
            String clss =  null;
            if (e.getSource() == trainImportView.getFileButton1()){
                clss = trainImportView.getTextField1().getText();
            } else if (e.getSource() == trainImportView.getFileButton2()){
                clss = trainImportView.getTextField2().getText();
            }
            File file = FileUtils.getFile();
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
                    trainImportedDataSet.addDocument(clss, FileUtils.fileToString(zipFile,zipEntry));
                }

                System.out.println(trainImportedDataSet.getData().get(clss).size());
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
}
