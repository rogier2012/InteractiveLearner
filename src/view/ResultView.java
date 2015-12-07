package view;

import model.TestedSet;

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, gbc);
    }

    public void displayDocuments(Map<String, String> testedSet){
        for (String category : testedSet.keySet()){
            textArea.append("Class: " + category + " Docuement: " + testedSet.get(category) + "\n");
        }
    }
}
