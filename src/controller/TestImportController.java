package controller;

import helper.FileUtils;
import model.TestImportedDataSet;
import view.TestImportView;

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
public class TestImportController implements PanelController,ActionListener {
    private TestImportedDataSet testImportedDataSet = null;
    private TestImportView testImportView;

    public TestImportController() {
        testImportView = new TestImportView();
        testImportedDataSet = new TestImportedDataSet();
        this.setupGUI();
        testImportView.addButtonActionListener(this);
    }

    @Override
    public void setupGUI() {
        testImportView.setupGUI();
    }

    @Override
    public JPanel getView() {
        return testImportView;
    }

    public TestImportedDataSet getDataSet() {
        return testImportedDataSet;
    }

    public void addNextButtonListener(ActionListener actionListener){
        testImportView.addNextButtonActionListener(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
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
                    testImportedDataSet.addUnclassifiedDocument(FileUtils.fileToString(zipFile,zipEntry));
                }

            }
        }
    }
}
