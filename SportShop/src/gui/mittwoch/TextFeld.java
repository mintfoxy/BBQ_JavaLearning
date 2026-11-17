package gui.mittwoch;

import javax.swing.JTextField;

/**
 * Beschreibt die Eigenschaften eines Textfeldes
 * TextFeld
 */

public class TextFeld extends JTextField {

    public TextFeld(String text){

        super(text);
        setBounds(100, 120, 400, 40);
    }

}
