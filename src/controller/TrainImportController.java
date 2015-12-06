package controller;

import helper.FileUtils;
import model.TrainImportedDataSet;
import view.TrainImportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportController implements ActionListener,PanelController{
    private TrainImportedDataSet trainImportedDataSet = null;
    private TrainImportView trainImportView;
    public static final int ZIPFILE = 0x504b0304;

    public TrainImportController() {
        trainImportView = new TrainImportView();
        trainImportedDataSet = new TrainImportedDataSet();
        trainImportView.addButtonActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            String clss =  null;
            if (e.getSource() == trainImportView.getFileButton1()){
                clss = trainImportView.getTextField1().getText();
            } else if (e.getSource() == trainImportView.getFileButton2()){
                clss = trainImportView.getTextField2().getText();
            }
            File file = trainImportView.getFile();
            boolean zip;
            try {
                 zip = isZipFile(file);
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

                while (zipFile.entries().hasMoreElements()){
                    ZipEntry zipEntry = zipFile.entries().nextElement();
                    trainImportedDataSet.addDocument(clss, FileUtils.fileToString(zipEntry));
                }
            }
        }
    }

    public static boolean isZipFile(File file) throws IOException {
        if(file.isDirectory()) {
            return false;
        }
        if(!file.canRead()) {
            throw new IOException("Cannot read file "+file.getAbsolutePath());
        }
        if(file.length() < 4) {
            return false;
        }
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        int test = in.readInt();
        in.close();
        return test == ZIPFILE;
    }

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return trainImportView;
    }
}
