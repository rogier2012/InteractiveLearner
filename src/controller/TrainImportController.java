package controller;

import model.TrainImportedDataSet;
import view.TrainImportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportController implements ActionListener,PanelController{
    private TrainImportedDataSet trainImportedDataSet = null;
    private TrainImportView trainImportView;
    public static final int ZIPFILE = 0x504b0304;

    public TrainImportController() {
        trainImportView = new TrainImportView();
        trainImportView.addButtonActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            File file = trainImportView.getFile();
            try {
                boolean zip = isZipFile(file);
            } catch (IOException e1) {
                boolean zip = false;
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
        return test == 0x504b0304;
    }

    @Override
    public void setupGUI() {

    }

    @Override
    public JPanel getView() {
        return null;
    }
}
