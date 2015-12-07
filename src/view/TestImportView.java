package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Rogier on 25-11-15
 */
public class TestImportView extends JPanel {
    private JButton fileButton1;
    private JTextField textField1;
    private JButton nextButton;


    public TestImportView() {
        super(new GridBagLayout());
    }

    public void addButtonActionListener(ActionListener actionListener){
        fileButton1.addActionListener(actionListener);
    }

    public void addNextButtonActionListener(ActionListener actionListener){
        nextButton.addActionListener(actionListener);
    }

    public void setupGUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        fileButton1 = new JButton("Class 1");
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(fileButton1, gbc);
        nextButton = new JButton("Next");
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(nextButton, gbc);

    }

    public JButton getFileButton1(){
        return fileButton1;
    }


    public JTextField getTextField1() {
        return textField1;
    }


}
