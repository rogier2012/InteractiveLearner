package view;

import model.TestImportedDataSet;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by Rogier on 30-11-15
 */
public class ResultView extends JPanel{
    private JTextArea textArea;


    public ResultView() {
        super(new GridBagLayout());

    }

    public void setupGUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        textArea = new JTextArea();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 1;
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, gbc);
    }

    public void displayDocuments(Map<String, String> testedSet, TestImportedDataSet importedDataSet) {
        for (String document : testedSet.keySet()){
            textArea.append("Class: " + testedSet.get(document) + " Document: " + importedDataSet.getFileNames().get(importedDataSet.getData().indexOf(document)) + "\n");

        }
    }
}
