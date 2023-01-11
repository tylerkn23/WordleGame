package com.shs.Wordlegame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class WordleGame implements PropertyChangeListener {

    JFrame frame;
    JLabel title;
    JPanel layerPanel, contentPane, titlePanel;

    JTextField[] input = new JTextField[36];

    public WordleGame() {
        frame = new JFrame("Wordle");
        Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();

        layerPanel = new JPanel();
        contentPane = new JPanel();
        titlePanel = new JPanel();

        // titlePanel.setBackground(Color.BLUE);
        title = new JLabel("WORDLE");
        title.setFont(new Font("Verdana", Font.PLAIN, 100));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);

        layerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        layerPanel.add(titlePanel, c);

        c.gridy = 1;
        c.ipady = 40;
        c.weightx = 1;
        layerPanel.add(contentPane, c);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setContentPane(layerPanel);
        frame.setSize(sz.width, sz.height);
        frame.setResizable(false);

        contentPane.setLayout(new GridLayout(6, 6, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        for (int i = 0; i < 36; i++) {
            input[i] = new JTextField();
            contentPane.add(input[i]);
            input[i].setFont(new Font("Verdana", Font.BOLD, 100));
            input[i].setHorizontalAlignment(JTextField.CENTER);
        }

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
