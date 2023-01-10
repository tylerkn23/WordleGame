import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }

    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        WordleGame game = new WordleGame();
    }
}