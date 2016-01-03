package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportView extends JPanel {
    private JButton fileButton;
    private JTextField textField;
    private List<JButton> fileButtonList;
    private List<JTextField> textFieldList;
    private JComboBox<String> categories;

    private JButton nextButton;
    private int categoryAmount;

    public TrainImportView() {
        super(new GridBagLayout());
        fileButtonList = new ArrayList<>();
        textFieldList = new ArrayList<>();
    }

    public void addButtonActionListener(ActionListener actionListener){
//        fileButton1.addActionListener(actionListener);
        if (fileButtonList != null) {
            for (JButton button : fileButtonList) {
                button.addActionListener(actionListener);
            }
        }
    }

    public void addNextButtonListener(ActionListener actionListener){
        nextButton.addActionListener(actionListener);
    }

    public void setupGUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        categories = new JComboBox<>();
        for (int i = 1; i <= categoryAmount; i++) {
            categories.addItem("Class " + i);
        }
        categories.setSelectedIndex(0);
        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrainImportView.this.setButtonAndTextField();

            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(categories, gbc);
        for (int i = 0; i < categoryAmount; i++) {
            fileButtonList.add(new JButton("Import " + (i + 1)));
            textFieldList.add(new JTextField());
        }
        gbc = new GridBagConstraints();
        textField = textFieldList.get(categories.getSelectedIndex());
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(textField, gbc);
        fileButton = fileButtonList.get(categories.getSelectedIndex());
        fileButton.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(fileButton, gbc);
        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(nextButton, gbc);

    }


    public void setButtonAndTextField() {
        this.remove(textField);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        textField = textFieldList.get(categories.getSelectedIndex());
        this.add(textField, gbc);

        this.remove(fileButton);
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;

        fileButton = fileButtonList.get(categories.getSelectedIndex());
        if (textField.getText().length() == 0) {
            fileButton.setEnabled(false);
        }
        this.add(fileButton, gbc);


        revalidate();
        repaint();
    }

    public List<JButton> getFileButtonList() {
        return fileButtonList;
    }


    public String getText(int index) {
        return textFieldList.get(index).getText();
    }

    public void setNextButtonEnabled(boolean active){
        nextButton.setEnabled(active);
    }

    public void setCategoryAmount(int categoryAmount) {
        this.categoryAmount = categoryAmount;
    }

    public void addDocumentListener(){
        if (textFieldList != null && fileButtonList != null) {
            for (JTextField jTextField : textFieldList) {
                JButton fileButton = fileButtonList.get(textFieldList.indexOf(jTextField));
                jTextField.getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        fileButton.setEnabled(true);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        if (e.getDocument().getLength() == 0) {
                            fileButton.setEnabled(false);
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                    }
                });
            }
        }
    }


}
