package gui.mittwoch;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Bschreibt die Eigenschaften eines Buttons
 * Knopka
 */

public class Knopka extends JButton {

        public Knopka (String text){

        super(text);
        setBackground(new Color(0, 255, 255)); // setzt Türkis als Hintergrundfarbe
        setOpaque(true); // markiert die Schaltfläche als deckend
        setBounds(100, 200, 400, 40);
        setFont(new Font("Arial", Font.ROMAN_BASELINE, 32));
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        }  

}
