package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Rogier on 25-11-15
 */
public class TrainImportView extends JPanel {
    private JButton fileButton;


    public TrainImportView() {
        setupGUI();
    }

    public void addButtonActionListener(ActionListener actionListener){
        fileButton.addActionListener(actionListener);
    }

    public File getFile(){
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

        }
        return selectedFile;
    }

    public void setupGUI(){
        fileButton = new JButton();

    }
}
