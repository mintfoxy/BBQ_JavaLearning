package gui.mittwoch;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Beschreibt die Eigenschaften eines Labels
 * Schild
 */

public class Schild extends JLabel{

    public Schild(String text){

        super(text);
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 32));
        setForeground(Color.YELLOW);
        
        setBounds(100, 80, 400, 40);

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }

}
