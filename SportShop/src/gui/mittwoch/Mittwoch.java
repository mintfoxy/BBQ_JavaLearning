package gui.mittwoch;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mittwoch extends JFrame {

    public static void main(String[] args) {

        new Mittwoch();
    }

    public Mittwoch() {


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(3000, 100, 600, 400);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        JPanel panellli = new Panelli();
        add(panellli);
    }
}