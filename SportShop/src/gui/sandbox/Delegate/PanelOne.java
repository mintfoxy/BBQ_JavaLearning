package gui.sandbox.Delegate;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * PanelOne
 */
public class PanelOne extends JPanel{

    JButton button;
    AufButtonReagieren reagierender; // Interface, um eine andere Panels zu steuern

    public PanelOne(){

        super();
        button = new JButton("Button of PanelOne");
        add(button);

        button.addActionListener(_-> derButtonWurdeGeklickt());
    }

    /**
     * Diese Methode ändert die Farbe des Buttons beim Klicken auf Button
     */
    private void derButtonWurdeGeklickt() {
        button.setBackground(Color.BLACK);
        reagierender.reagiere(); // ruft die Methode reagiere aus dem Interface
    }

}
