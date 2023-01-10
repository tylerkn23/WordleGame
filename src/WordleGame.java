import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class WordleGame implements PropertyChangeListener {

    JFrame frame;
    JLabel label, label1;
    JPanel contentPane;
    JButton button;
    JTextField input;

    public WordleGame() {
        frame = new JFrame("DivisibleBy3");
        contentPane = new JPanel();
        label = new JLabel("Enter an integer: ");
        label1 = new JLabel();
        button = new JButton("Check");
        input = new JTextField(5);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setContentPane(contentPane);

        contentPane.add(label);
        contentPane.add(input);
        contentPane.add(button);
        contentPane.add(label1);

        contentPane.setLayout(new GridLayout(2, 2, 50, 10));
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {


    }
}
