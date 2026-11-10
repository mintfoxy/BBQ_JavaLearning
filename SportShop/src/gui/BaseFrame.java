package gui;

import java.awt.GridLayout;
import javax.swing.JFrame;

import gui.sandbox.LabelPanel;

public class BaseFrame extends JFrame { // erweitert JFrame um die Einstellungen und Inhalte der Anwendung

    public static void main(String[] args) { // startet die Anwendung

        new BaseFrame(); // erstellt das Fenster und führt seinen Konstruktor aus
    }

    public BaseFrame() { // erstellt das Fenster und richtet es ein

        super(GuiConstants.APP_NAME); // übergibt den Fenstertitel an den Konstruktor von JFrame
        setBounds(GuiConstants.FRAME_BOUNDS); // setzt Position und Größe des Fensters
        setDefaultCloseOperation(EXIT_ON_CLOSE); // beendet die Anwendung beim Schließen des Fensters
        setLayout(new GridLayout(0, 2)); // ordnet die Komponenten in zwei Spalten an

        initComponents(); // erstellt die Inhalte und fügt sie zum Fenster hinzu

        setVisible(true); // zeigt das konfigurierte Fenster an
    }

    private void initComponents() { // erstellt die Inhalte des Fensters

        add(new LabelPanel()); // erstellt eine LabelPanel und fügt sie zum Fenster hinzu
    }

}