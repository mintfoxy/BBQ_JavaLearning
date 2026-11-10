package gui.sandbox;

import java.awt.GridLayout;
import javax.swing.JLabel;

public class LabelPanel extends MyPanel {

    @Override 
    protected void initLayout(){
        setLayout(new GridLayout(0, 1));
    }

    @Override 
    protected void initComponents() {
        add(new JLabel("Hallo"));
    }

}
