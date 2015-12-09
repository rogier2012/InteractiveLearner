package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

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
        fileButton1 = new JButton("Import 1");
        fileButton1.setEnabled(false);
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
        fileButton2 = new JButton("Import 2");
        fileButton2.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(fileButton2, gbc);
        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
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

    public void setNextButtonEnabled(boolean active){
        nextButton.setEnabled(active);
    }

    public void addDocumentListener(){
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fileButton1.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getDocument().getLength() == 0){
                    fileButton1.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        textField2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fileButton2.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getDocument().getLength() == 0){
                    fileButton2.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

}
