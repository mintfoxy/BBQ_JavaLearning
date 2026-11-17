package gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class HeaderPanel extends BasePanel { // beschreibt eine Panel mit einer Überschrift

    /**
     * Konstruktor
     */
    public HeaderPanel() { // richtet das Panel mit der Überschrift ein
    initLayout(); // legt die Anordnung fest
    initComponents(); // fügt die Überschrift hinzu
    }

    @Override
    protected void initLayout() { // legt die Anordnung der Komponenten fest
        setLayout(new GridLayout(0, 1)); // ordnet die Komponenten in einer Spalte und fünf Zeilen an
    }

    @Override
    protected void initComponents() { // erstellt den Inhalt der Panel
        JLabel titleLabel = new JLabel("SportShop"); // erstellt eine Beschriftung
        titleLabel.setFont(GuiConstants.FONT); // setzt die Schrift der Beschriftung
        titleLabel.setForeground(Color.WHITE); // Farbe der Beschriftung
        this.setBackground(new Color(13, 13, 13)); // Farbe des Backgrounds
        add(titleLabel); // fügt die Beschriftung zur Panel hinzu
    }
}