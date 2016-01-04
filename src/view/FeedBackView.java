package view;

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

    public FeedBackView() {
        super(new GridBagLayout());
        documentList = new ArrayList<>();
        radioButtonList = new ArrayList<>();
    }

    public void setupGUI(){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 1;
        scrollPane = new JScrollPane();
        this.add(scrollPane, gbc);
        gbc = new GridBagConstraints();
        nextButton = new JButton("Next");
        gbc.gridx = 1;
        gbc.gridy = 1;

        this.add(nextButton, gbc);
    }


    public void displayResults(Map<String, String> results, List<String> categories, ActionListener actionListener) {
        for (String document : results.keySet()) {
            documentList.add(document);
            JTextArea area = new JTextArea();
            area.append(document);
            area.setLineWrap(true);
            scrollPane.add(area);
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
            JPanel radioPanel = new JPanel(new GridLayout(0, 1));

            for (JRadioButton button : radioButtons) {
                buttonGroup.add(button);
                radioPanel.add(button);
            }
            radioButtonList.add(buttonGroup);

            scrollPane.add(radioPanel);

        }
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
