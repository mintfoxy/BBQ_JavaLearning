package gui;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import data.dataHandling.DataManager;
import data.objekte.Article;
import data.objekte.Manufacturer;

/**
 * Grundpanel
 * Erzeugt Panelle, lädt die Daten und koordiniert die Arbeit zwischen den
 * Panellen
 */
public class ArticleFormPanel extends BasePanel implements Delegate {

    private final DataManager manager; // Zugriff auf Interface

    private SelectionPanel selectionPanel; // speichert die linke Panel
    private EditorPanel editorPanel; // speichert die rechte Panel

    /**
     * Konstruktor
     */
    public ArticleFormPanel(DataManager manager) {

        this.manager = manager; // speichert den übergebenen Datenmanager
        initLayout(); // legt die Aufteilung in zwei Bereiche fest
        initComponents(); // erstellt die Komponenten und registriert ihre Listener
        loadManufacturers(); // lädt die Hersteller für den Filter und das Formular
        loadArticles(); // lädt die gespeicherten Artikel in die Liste
    }

    /**
     * Diese Methode initialisiert das Layout der Panel
     */
    @Override
    protected void initLayout() {

        setLayout(new GridLayout(1, 2, 16, 0)); // teilt das Panel in zwei gleich breite Bereiche
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // schafft rundum zehn Pixel Abstand
    }

    /**
     * Diese Methode initialisiert die Komponenten
     */
    @Override
    protected void initComponents() {

        editorPanel = new EditorPanel(this); // erstellt Panel rechts (Editor); 
                                             // Speichern und Löschen der Editor übergibt (delegate) an ArticleFormPanel (this)

        selectionPanel = new SelectionPanel(this); // übergibt denselben Empfänger an die linke Panel

        add(selectionPanel); // fügt den Auswahlbereich links ein
        add(editorPanel); // fügt das Eingabeformular rechts ein
        
    }

    /**
     * Diese Methode lädt alle gespeichrten Artikel aus der Datenbank
     */
    private void loadArticles() {

        List<Article> articles = manager.readAllArticles(); // fordert die Artikel beim Datenmanager an

        if (articles == null) { // erkennt einen Lesefehler
            editorPanel.showStatus("Artikel konnten nicht geladen werden.");
            return; // behält bei einem Lesefehler die bisherige Liste
        }

        selectionPanel.setArticles(articles); // übergibt die Artikel an die linke Panel
    }

    /**
     * Diese Methode füllt die Herstellerliste für die Suche (Filter) und das
     * Formular
     */
    private void loadManufacturers() {

        List<Manufacturer> manufacturers = manager.readAllManufacturers(); // fordert die Hersteller beim Datenmanager
                                                                           // an

        if (manufacturers == null) { // erkennt einen Lesefehler
            editorPanel.showStatus("Hersteller konnten nicht geladen werden.");
            return; // beendet das Laden bei einem Fehler
        }

        selectionPanel.setManufacturers(manufacturers); // füllt den Herstellerfilter
        editorPanel.setManufacturers(manufacturers); // füllt die Herstellerauswahl im Formular

        if (manufacturers.isEmpty()) {
            editorPanel.showStatus("Keine Hersteller vorhanden.");
        }
    }

    /**
     * übergibt den links ausgewählten Artikel an die rechte Panel
     */
    @Override
    public void articleSelected(Article article) {

        editorPanel.showSelectedArticle(article); // zeigt den Artikel oder leert das Formular
    }

    /**
     * Diese Methode speichert den Artikel und aktualisiert anschließend beide Panelle
     */
    @Override
    public void saveRequested(Article article) {

        try {
            manager.saveArticle(article); // übergibt den Artikel zum Speichern an den Datenmanager
        } catch (SQLException exception) { // fängt einen Datenbankfehler beim Speichern ab
            editorPanel.showStatus("Fehler beim Speichern: " + exception.getMessage());
            return; // lässt die Eingaben zur Korrektur im Formular stehen
        }

        selectionPanel.articleSaved(article); // aktualisiert die Liste und meldet die neue Auswahl
        editorPanel.showStatus(""); // entfernt die bisherige Meldung

        JOptionPane.showMessageDialog(
                this, "Gespeichert!", "Speichern",
                JOptionPane.INFORMATION_MESSAGE); // zeigt die Speicherbestätigung mit einer OK-Schaltfläche an
    }

    /**
     * Diese Methode löscht den Artikel und aktualisiert anschließend beide Panelle
     */
    @Override
    public void deleteRequested(Article article) {
        try {
            manager.deleteArticle(article); // lässt den Datenmanager den Artikel anhand seiner ID löschen
        } catch (SQLException exception) { // fängt einen Datenbankfehler beim Löschen ab
            editorPanel.showStatus("Fehler beim Löschen: " + exception.getMessage());
            return; // behält den Artikel bei einem Fehler in der Liste
        }

        selectionPanel.articleDeleted(article); // entfernt den Artikel aus der Liste
        editorPanel.showStatus(""); // entfernt die bisherige Meldung

        JOptionPane.showMessageDialog(
                this, "Gelöscht!", "Artikel löschen",
                JOptionPane.INFORMATION_MESSAGE); // zeigt nach dem Löschen eine Bestätigung
    }

    /**
     * Diese Methode setzt alles zurück
     */
    @Override
    public void resetRequested() {

        selectionPanel.resetSelection(); // setzt Filter, Sortierung und Artikelauswahl zurück
        editorPanel.showSelectedArticle(null); // leert die Eingabefelder
        editorPanel.showStatus(""); // entfernt die bisherige Meldung
    }
}