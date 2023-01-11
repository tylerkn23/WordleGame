package com.shs.Wordlegame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import java.io.*;
import java.nio.file.*;

import static java.awt.Color.cyan;

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

        //titlePanel.setBackground(Color.BLUE);
        title = new JLabel("WORDLE");
        title.setFont(new Font("Verdana", Font.PLAIN, sz.width/20));
        //title.setForeground(Color.green);
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
        frame.setSize(sz.width/2, (sz.height/4)*3);
        frame.setResizable(false);

        contentPane.setLayout(new GridLayout(6, 6, sz.width/200, sz.height/200));
        contentPane.setBorder(BorderFactory.createEmptyBorder(sz.height/50, sz.width/20, sz.height/20, sz.width/20));

        for (int i = 0; i < 36; i++) {
            input[i] = new JTextField();
            contentPane.add(input[i]);
            input[i].setFont(new Font("Verdana", Font.BOLD, sz.height/20));
            input[i].setHorizontalAlignment(JTextField.CENTER);

            input[i].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char keyChar = e.getKeyChar();
                    if (Character.isLowerCase(keyChar)) {
                        e.setKeyChar(Character.toUpperCase(keyChar)); //makes letter uppercase
                    }
                }

            });
            int finalI = i;
            input[i].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (input[finalI].getText().length() > 0 ) // limit textfield to one letter
                        e.consume();
                }
            });
        }

    }
    public String GetWord(){
        String a;
        Random rand = new Random();
        int random_int = (int)Math.floor(Math.random() * (7637+1));
        File file = new File("6letters.txt");
        try {
            String line = Files.readAllLines(Paths.get("6letters.txt")).get(random_int);
            return line;
        }
        catch(IOException e){
            a = e.getMessage();
        }
        return "";
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
