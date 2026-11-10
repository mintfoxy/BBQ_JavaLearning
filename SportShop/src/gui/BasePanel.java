package gui;

import javax.swing.JPanel;

public abstract class BasePanel extends JPanel { // bildet die gemeinsame Grundlage für eigene Panels

    public BasePanel() { // führt die gemeinsamen Schritte beim Erstellen eines Panels aus
        super(); // ruft den Konstruktor von JPanel auf
        setFont(GuiConstants.FONT); // setzt die Schrift der Panel
        initLayout(); // ruft die Layout Einstellungen der Unterklasse auf
        initComponents(); // ruft die Erstellung der Komponenten in der Unterklasse auf
    }

    protected abstract void initLayout(); // verpflichtet die Unterklasse, das Layout festzulegen
    protected abstract void initComponents(); // verpflichtet die Unterklasse, ihre Komponenten zu erstellen
}