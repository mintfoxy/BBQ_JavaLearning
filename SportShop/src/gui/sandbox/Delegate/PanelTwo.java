package gui.sandbox.Delegate;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

/**
 * PanelTwo
 */
public class PanelTwo extends JPanel implements AufButtonReagieren{

    JLabel label;

    public PanelTwo(){

        super();
        label = new JLabel("Label of PanelTwo");
        add(label);
    }

    @Override 
    public void reagiere(){

        setBackground(Color.GREEN);
    }

}
