package gui;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import data.dataHandling.MySQLDataManager;

public class BaseFrame extends JFrame {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new BaseFrame()); // plant die Erstellung des Fensters im Event Dispatch Thread (Swing Ereignisthread) ein
    }

    public BaseFrame() { // erstellt das Fenster und richtet es ein

        super(GuiConstants.APP_NAME); // übergibt den Fenstertitel an den Konstruktor von JFrame
        setBounds(GuiConstants.FRAME_BOUNDS); // setzt Position und Größe des Fensters
        setDefaultCloseOperation(EXIT_ON_CLOSE); // beendet die Anwendung beim Schließen des Fensters
        // setLayout(new GridLayout(0, 2)); // ordnet die Komponenten in zwei Spalten an

        initComponents(); // erstellt die Inhalte und fügt sie zum Fenster hinzu

        setVisible(true); // zeigt das konfigurierte Fenster an
    }

    private void initComponents() { // erstellt die Inhalte des Fensters

        setLayout(new BorderLayout()); // teilt den Fensterinhalt in Bereiche ein
        add(new HeaderPanel(), BorderLayout.NORTH); // zeigt die Überschrift oben an
        add(new ArticleFormPanel(MySQLDataManager.getInstance()), BorderLayout.CENTER); // platziert das Formular in der
                                                                                        // Mitte
    }

}