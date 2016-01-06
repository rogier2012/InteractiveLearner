package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Created by Rogier on 25-11-15
 */
public class HomeView extends JPanel{
    private JLabel title;
    private JButton nextButton;
    private JLabel categoryAmountLabel;
    private JSpinner categoryAmount;
    private JCheckBox checkBox;


    public HomeView() {
        super(new GridBagLayout());
    }

    public void setupGUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        title = new JLabel("Interactive Learner");
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        this.add(title, gbc);
        gbc = new GridBagConstraints();
        categoryAmountLabel = new JLabel("Number of classes");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        this.add(categoryAmountLabel, gbc);
        gbc = new GridBagConstraints();

        categoryAmount = new JSpinner();
        SpinnerNumberModel model = new SpinnerNumberModel(2, 2, 10, 1);
        categoryAmount.setModel(model);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.ipadx = 30;
        this.add(categoryAmount, gbc);
        gbc = new GridBagConstraints();
        JLabel chiSquare = new JLabel("Chi Square FS");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        this.add(chiSquare, gbc);
        gbc = new GridBagConstraints();
        checkBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.ipadx = 30;
        this.add(checkBox, gbc);

        gbc = new GridBagConstraints();
        nextButton = new JButton("Next");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;

        this.add(nextButton, gbc);
    }

    public void addNextButtonActionListener(ActionListener actionListener) {
        nextButton.addActionListener(actionListener);
    }


    public int getCategoryAmount() {
        int result = 0;
        result = (Integer) categoryAmount.getValue();


        return result;
    }

    public void addCheckboxListener(ItemListener listener) {
        checkBox.addItemListener(listener);
    }


}
