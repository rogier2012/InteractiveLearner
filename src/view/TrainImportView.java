package view;

import javax.swing.*;
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


    public TrainImportView() {
        setupGUI();
    }

    public void addButtonActionListener(ActionListener actionListener){
        fileButton1.addActionListener(actionListener);
        fileButton2.addActionListener(actionListener);
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
        fileButton1 = new JButton();
        fileButton2 = new JButton();
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
