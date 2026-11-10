package gui.sandbox;

import javax.swing.JButton;
import javax.swing.JFrame; // x steht für Grafik
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Der erste Versuch mit Swing
 * Main
 */
public class Main {

    public static void main(String[] args) {
        
        JFrame myFrame = new JFrame("First");

        myFrame.setBounds(600, 100, 400, 300);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // was passiert wenn man auf X klickt

        myFrame.setLayout(new GridLayout(0, 2));

        JPanel panel = new JPanel();
        panel.setSize(100, 100);
        panel.setBackground(Color.BLACK);
        myFrame.add(panel);

        JLabel label = new JLabel("Hallo");
        panel.add(label);
        label.setFont(new Font("Helvetica", Font.PLAIN, 30));
        label.setForeground(Color.WHITE);

        panel = new JPanel();
        panel.setSize(200, 100);
        panel.setBackground(Color.BLUE);
        myFrame.add(panel);

        JButton button = new JButton("Start");
        button.setFont(new Font("Helvetica", Font.PLAIN, 30));
        button.setFocusable(false);
        panel.add(button);

        myFrame.setVisible(true);
    }

}
