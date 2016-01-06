package view;

import model.TestImportedDataSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by Rogier on 30-11-15
 */
public class ResultView extends JPanel{
    private JTextArea textArea;
    private JButton nextButton;
    private JButton stopButton;

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
        gbc = new GridBagConstraints();
        nextButton = new JButton("Next");
        gbc.gridx = 1;
        gbc.gridy = 1;

        this.add(nextButton, gbc);
        gbc = new GridBagConstraints();
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(stopButton, gbc);

    }

    public void displayDocuments(Map<String, String> testedSet, TestImportedDataSet importedDataSet) {
        for (String document : testedSet.keySet()){
            textArea.append("Class: " + testedSet.get(document) + " Document: " + importedDataSet.getFileNames().get(importedDataSet.getData().indexOf(document)) + "\n");

        }
        textArea.append("Number of items: " + importedDataSet.getFileNames().size());
    }

    public void addNextButtonActionListener(ActionListener actionListener) {
        nextButton.addActionListener(actionListener);
    }
}
