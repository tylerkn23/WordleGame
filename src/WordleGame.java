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

    public WordleGame() {
        frame = new JFrame("DivisibleBy3");
        contentPane = new JPanel();
        label = new JLabel("Enter an integer: ");
        label1 = new JLabel();
        button = new JButton("Check");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {


    }
}
