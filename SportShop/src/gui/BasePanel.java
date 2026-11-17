package gui;

import javax.swing.JPanel;

public abstract class BasePanel extends JPanel { // bildet die gemeinsame Grundlage für eigene Panels

    /**
     * Konstruktor
     */
    protected BasePanel() {
        super(); // ruft den Konstruktor von JPanel auf
        setFont(GuiConstants.FONT); // setzt die Schrift für das Panel
    }

    protected abstract void initLayout(); // legt die Anordnung im jeweiligen Panel fest

    protected abstract void initComponents(); // fügt die Komponenten des jeweiligen Panels hinzu
}