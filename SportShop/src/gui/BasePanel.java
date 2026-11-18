package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

public abstract class BasePanel extends JPanel {

    /**
     * Konstruktor
     */
    protected BasePanel() {
        super();
        setFont(GuiConstants.FONT); // setzt die Schrift der Panel
    }

    protected abstract void initLayout();

    protected abstract void initComponents();

    /**
     * Diese Methode erstellt pro Aufruf ein GridBagConstraints Objekt für eine
     * Komponente (ein Objekt mit Einstellungen)
     */
    protected GridBagConstraints gridCell(int column, int row) {

        GridBagConstraints cell = new GridBagConstraints(); // Variable cell für ein neues Objekt mit Eigenschaften
        cell.gridx = column; // legt die Spalte fest
        cell.gridy = row; // legt die Zeile fest
        cell.fill = GridBagConstraints.HORIZONTAL; // nutzt die verfügbare Breite der Zelle
        cell.insets = new Insets(2, 4, 2, 4); // schafft außen Abstand zum nächsten Element: oben, links, unten, rechts
        return cell; // GridBagLayout nutzt das von hier erhaltene Objekt mit Einstellungen um ein Komponent zu platzieren
    }
}