package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportView extends JPanel {
    private JButton fileButton1;
    private JButton fileButton2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton nextButton;


    public TrainImportView() {
        super(new GridBagLayout());
    }

    public void addButtonActionListener(ActionListener actionListener){
        fileButton1.addActionListener(actionListener);
        fileButton2.addActionListener(actionListener);
    }

    public void addNextButtonListener(ActionListener actionListener){
        nextButton.addActionListener(actionListener);
    }

    public void setupGUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        textField1 = new JTextField();
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(textField1, gbc);
        fileButton1 = new JButton("Class 1");
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(fileButton1, gbc);
        textField2 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(textField2, gbc);
        fileButton2 = new JButton("Class 2");
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(fileButton2, gbc);
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

    public JButton getFileButton2(){
        return fileButton2;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }
}
