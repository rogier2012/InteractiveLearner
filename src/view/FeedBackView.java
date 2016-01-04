package view;

import model.TestImportedDataSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogier on 25-11-15
 */
public class FeedBackView extends JPanel{
    private List<String> documentList;
    private List<ButtonGroup> radioButtonList;
    private JScrollPane scrollPane;
    private JButton nextButton;
    private JPanel panel;

    public FeedBackView() {
        super(new GridBagLayout());
        documentList = new ArrayList<>();
        radioButtonList = new ArrayList<>();
    }

    public void setupGUI(){
        GridBagConstraints gbc = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        System.out.println(panel.getWidth());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 1;
        scrollPane = new JScrollPane(panel);
        this.add(scrollPane, gbc);
        gbc = new GridBagConstraints();
        nextButton = new JButton("Next");
        gbc.gridx = 1;
        gbc.gridy = 1;

        this.add(nextButton, gbc);
    }


    public void displayResults(Map<String, String> results, List<String> categories, TestImportedDataSet importedDataSet) {
        int iterator = 0;
        GridBagConstraints gbc;
        for (String document : results.keySet()) {
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = iterator;
//            gbc.fill = GridBagConstraints.HORIZONTAL;

            documentList.add(document);
            JTextArea area = new JTextArea();
            area.append(importedDataSet.getFileNames().get(importedDataSet.getData().indexOf(document)));

            panel.add(area, gbc);
            iterator++;
            List<JRadioButton> radioButtons = new ArrayList<>();
            for (String category : categories) {
                JRadioButton radioButton = new JRadioButton(category);
                radioButton.setActionCommand(category);
                if (category.equals(results.get(document))) {
                    radioButton.setSelected(true);
                }
                radioButtons.add(radioButton);
//                radioButton.addActionListener(actionListener);
            }
            ButtonGroup buttonGroup = new ButtonGroup();
            JPanel radioPanel = new JPanel(new GridBagLayout());
            int radio = 0;
            for (JRadioButton button : radioButtons) {
                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = radio;
                gridBagConstraints.gridy = 0;
                buttonGroup.add(button);
                radioPanel.add(button, gridBagConstraints);
                radio++;
            }
            radioButtonList.add(buttonGroup);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = iterator;
//            gbc.fill = GridBagConstraints.HORIZONTAL;


            panel.add(radioPanel, gbc);
            iterator++;
        }

        revalidate();
        repaint();
    }

    public List<String> getDocumentList() {
        return documentList;
    }

    public List<ButtonGroup> getRadioButtonList() {
        return radioButtonList;
    }

    public void addNextButtonListener(ActionListener actionListener) {
        nextButton.addActionListener(actionListener);
    }
}
