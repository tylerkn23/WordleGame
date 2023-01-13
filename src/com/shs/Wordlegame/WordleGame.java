package com.shs.Wordlegame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import java.io.*;
import java.nio.file.*;

public class WordleGame {

    JFrame frame;
    JLabel title;
    JPanel layerPanel, contentPane, titlePanel;
    JTextField[] input = new JTextField[36];
    Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    GridBagConstraints c = new GridBagConstraints();

    int guesses = 0;
    int outerBound = 5;
    Color yellow = new Color(200,182,83);
    Color green = new Color(108,169,101);
    String word = getWord();
    String[] guess = new String[6];

    public WordleGame() {
        frame = new JFrame("Wordle");

        layerPanel = new JPanel();
        contentPane = new JPanel();
        titlePanel = new JPanel();

        addTitle();

        layerPanel.setLayout(new GridBagLayout());
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

        createTextFields();
        addTextFields();
        input[0].requestFocusInWindow();
        changeEditableTextFieldsFalse();
        checkForInput();
    }
    public String getWord(){
        int random_int = (int)Math.floor(Math.random() * (450+1));
        try {
            return Files.readAllLines(Paths.get("RandomWords")).get(random_int);
        }
        catch(IOException e){
            System.out.println("Problem reading file.");
            System.err.println("IOException: " + e.getMessage());
        }
        return "";
    }
    public boolean checkWord(String[] g){
        File file = new File("6letters.txt");
        FileReader in;
        BufferedReader readFile;
        String word;

        StringBuilder strBuilder = new StringBuilder();
        for (String s : g) {
            strBuilder.append(s);
        }
        String guess = strBuilder.toString();

            try {
            in = new FileReader(file);
            readFile = new BufferedReader(in);

            while ((word = readFile.readLine()) != null) {
               if (word.equals(guess)) {
                   return true;
               }
            }
            readFile.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem reading file.");
            System.err.println("IOException: " + e.getMessage());
        }
        return false;
    }

    public void addTitle() {
        title = new JLabel("WORDLE");
        title.setFont(new Font("Serif", Font.BOLD, sz.width/20));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
    }

    public void createTextFields() {
        for (int i = 0; i < 36; i++) {
            input[i] = new JTextField();
            input[i].setFont(new Font("Verdana", Font.BOLD, sz.height/20));
            input[i].setHorizontalAlignment(JTextField.CENTER);
        }
    }

    public void addTextFields() {
        for (int i = 0; i < 36; i++) {
            contentPane.add(input[i]);
        }
    }

    public void setEditableTextFields(int guesses) {
        switch (guesses) {
            case 1: changeEditableTextFieldsTrue(6); return;
            case 2: changeEditableTextFieldsTrue(12); return;
            case 3: changeEditableTextFieldsTrue(18); return;
            case 4: changeEditableTextFieldsTrue(24); return;
            case 5: changeEditableTextFieldsTrue(30); return;
            case 6: setWrongText(36);
        }
    }

    public void changeEditableTextFieldsFalse() {
        for (int i = 6; i < 36; i++) {
            input[i].setEditable(false);
        }
    }

    public void changeEditableTextFieldsTrue(int num) {
        for (int i = num; i < num + 6; i++) {
            input[i].setEditable(true);
            manager.focusNextComponent();
        }
        setWrongText(num);
    }

    public void setWrongText(int num) {
        for (int i = num - 6; i < num; i++) {
            input[i].setEditable(false);
            input[i].setBackground(Color.darkGray);
            input[i].setForeground(Color.white);
        }
    }

    public void compareActualWord(int guesses) {
        int match = 0;
        char[] phrase = word.toCharArray();
        for (int i = 0; i < 6; i++) {
            if ((guess[i]).equals(String.valueOf(phrase[i]))) {
                input[i+6*guesses].setBackground(green);
                phrase[i] = Character.MIN_VALUE;
                match++;
            } else {
                for(int j = 1; j < 6; j++) {
                    if ((guess[i]).equals(String.valueOf(phrase[j]))) {
                        input[i+6*guesses].setBackground(yellow);
                        phrase[j] = Character.MIN_VALUE;
                    }
                }
            }
        }
        if (match == 6) {
            winScreen();
        } else {
            if (guesses == 5) {
                loseScreen();
            }
        }
    }

    public void addLetterToArray(int guesses, int index, Character letter) {
        switch (guesses) {
            case 0: guess[index] = letter.toString(); return;
            case 1: guess[index-6] = letter.toString(); return;
            case 2: guess[index-12] = letter.toString(); return;
            case 3: guess[index-18] = letter.toString(); return;
            case 4: guess[index-24] = letter.toString(); return;
            case 5: guess[index-30] = letter.toString();
        }
    }

    public void removeLetterFromArray(int guesses, int index) {
        switch (guesses) {
            case 0: guess[index] = null; return;
            case 1: guess[index-6] = null; return;
            case 2: guess[index-12] = null; return;
            case 3: guess[index-18] = null; return;
            case 4: guess[index-24] = null; return;
            case 5: guess[index-30] = null;
        }
    }

    public int checkElementInArray() {
        int counter = 0;
        for (String s : guess)
            if (s != null)
                counter++;
        return counter;
    }

    public void winScreen() {
        createDialog("You Win!");
    }

    public void loseScreen() {
        createDialog("You Lose!");
    }

    public void createDialog(String s) {
        JPanel bottomPane = new JPanel();

        JFrame f = new JFrame();
        JLabel l = new JLabel(s);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setFont(new Font("Serif", Font.BOLD, sz.width/20));

        JDialog d = new JDialog(f , s, true);
        d.setLayout(new GridLayout(2, 1, 100, 100));

        JButton b = new JButton ("OK");
        bottomPane.add(b);
        bottomPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        b.addActionListener(e -> {
            d.setVisible(false);
            frame.dispose();
        });
        b.setPreferredSize(new Dimension(400, 400));
        b.setFont(new Font("Serif", Font.BOLD, sz.width/20));

        d.setSize(sz.width/2, (sz.height/4)*3);
        d.add(l);
        d.add(bottomPane);
        d.setVisible(true);
    }

    public void checkForInput() {
        for (int i = 0; i < 36; i++) {
            int finalI = i;
            input[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char keyChar = e.getKeyChar();
                    if(!(Character.isLetter(keyChar))){
                        e.consume(); //only allows letters to be typed
                    } else {
                        if (input[finalI].getText().length() > 0) {
                            e.consume(); //prevents more than 1 letter being typed
                        }
                        if (Character.isLowerCase(keyChar)) {
                            e.setKeyChar(Character.toUpperCase(keyChar)); //makes text uppercase
                        }
                        if (checkElementInArray() < 6) {
                            addLetterToArray(guesses, finalI, keyChar);
                        }
                        if(input[outerBound].isFocusOwner()) {
                        } else {
                            manager.focusNextComponent(); //automatically moves to the next box
                        }
                    }
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(input[guesses*6].isFocusOwner()) {
                        } else {
                            if (input[finalI].getText().isEmpty()) {
                                manager.focusPreviousComponent();
                            }
                            if (checkElementInArray() == 0) {
                            } else {
                                removeLetterFromArray(guesses, finalI);
                            }
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (checkElementInArray() == 6) {
                            if (checkWord(guess)) {
                                setEditableTextFields(++guesses);
                                outerBound+=6;
                                compareActualWord(guesses-1);
                                Arrays.fill(guess, null);
                            }
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
}
