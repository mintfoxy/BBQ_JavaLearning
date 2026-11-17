package gui;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Box;

import data.dataHandling.DataManager;

import data.objekte.Article;
import data.objekte.Designation;
import data.objekte.Manufacturer;
import data.objekte.SportType;

public class ArticleFormPanel extends BasePanel {

    private final DataManager manager; // Zugriff auf Interface

    // LINKE SEITE, PANEL FILTER
    private final List<Article> loadedArticles = new ArrayList<>(); // hält alle geladenen Artikel für die Filter bereit

    private final JComboBox<SportType> sportTypeFilter = new JComboBox<>(); // Filter Sportart

    private final JComboBox<Manufacturer> manufacturerFilter = new JComboBox<>(); // Filter Hersteller

    private final JComboBox<String> sortBox = new JComboBox<>(new String[] {
            "Standard", "Artikelnummer A–Z", "Artikelnummer Z–A"}); // auf- und absteigende Sortierung

    private final JList<Article> articleList = new JList<>(); // Liste mit gefilterten Artikeln

    private boolean updatingArticleList; // kennzeichnet den laufenden Austausch des Listeninhalts

    // RECHTE SEITE, PANEL ARTIKEL
    private final JTextField articleNumberField = new JTextField(); // nimmt die Artikelnummer auf

    private final JComboBox<SportType> sportTypeBox = new JComboBox<>(SportType.values()); // alle Sportarten

    private final JComboBox<Manufacturer> manufacturerBox = new JComboBox<>(); // alle Hersteller

    private final JTextField designationField = new JTextField(); // nimmt die Artikelbezeichnung auf

    private final JLabel statusLabel = new JLabel(); // zeigt Rückmeldungen unter den Schaltflächen an

    /**
     * Konstruktor
     * 
     */
    public ArticleFormPanel(DataManager manager) {
        this.manager = manager; // speichert den übergebenen Datenmanager
        initLayout(); // legt die Aufteilung in zwei Bereiche fest
        initComponents(); // erstellt die Komponenten und registriert ihre Listener
        loadManufacturers(); // lädt die Hersteller für den Filter und das Formular
        clearForm(); // entfernt die automatische Vorauswahl aus dem Formular
        loadArticles(); // lädt die gespeicherten Artikel in die Liste
    }

    /**
     * Diese Methode initialisiert das Layout der Panel
     * 
     */
    @Override
    protected void initLayout() {
        setLayout(new GridLayout(1, 2, 16, 0)); // teilt das Panel in zwei gleich breite Bereiche
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // schafft rundum zehn Pixel Abstand
    }

    /**
     * Diese Methode erstellt die Komponenten der Panel
     * 
     */
    @Override
    protected void initComponents() {

        sportTypeFilter.addItem(null); // leerer Eintrag im Filter für alle Sportarten

        for (SportType sportType : SportType.values()) {
            sportTypeFilter.addItem(sportType); // fügt jede verfügbare Sportart zum Filter hinzu
        }

        manufacturerFilter.addItem(null); // leerer Eintrag im Filter für alle Hersteller 

        JPanel selectionPanel = new JPanel(new GridBagLayout()); // erstellt den linken Bereich
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Auswahl")); // Beschriftung der Panel
        addSelectionFields(selectionPanel); // fügt Filter, Sortierung und Artikelliste hinzu

        JPanel editorPanel = new JPanel(new GridBagLayout()); // erstellt den rechten Bereich
        editorPanel.setBorder(BorderFactory.createTitledBorder("Artikel")); // Beschriftung der Panel
        addEditorFields(editorPanel); // fügt die vier Eingabefelder in den Zeilen 0 bis 3 hinzu

        GridBagConstraints space = gridCell(0, 4); // legt den Zwischenraum in Zeile 4 an
        space.gridwidth = 2; // erstreckt den Zwischenraum über beide Spalten
        space.weighty = 1.0; // weist dem Zwischenraum die zusätzliche Höhe zu
        space.fill = GridBagConstraints.BOTH;
        editorPanel.add(Box.createVerticalGlue(), space); // hält die Schaltflächen am unteren Rand

        addEditorButton(editorPanel, "Neuer Artikel", 0, 5, event -> {
            articleList.clearSelection(); // hebt die Auswahl des vorhandenen Artikels auf
            clearForm(); // leert die Felder für einen neuen Artikel
            statusLabel.setText(""); // entfernt die bisherige Meldung
        });

        addEditorButton(editorPanel, "Speichern", 1, 5,
                event -> saveArticle()); // speichert die Eingaben des Formulars
        addEditorButton(editorPanel, "Reset", 0, 6,
                event -> resetForm()); // setzt die Oberfläche zurück
        addEditorButton(editorPanel, "Artikel löschen", 1, 6,
                event -> deleteSelectedArticle()); // löscht den ausgewählten Artikel

        GridBagConstraints status = gridCell(0, 7); // legt die Meldung in die Zeile unter den Schaltflächen
        status.gridwidth = 2; // erstreckt die Meldung über beide Spalten
        editorPanel.add(statusLabel, status);

        add(selectionPanel); // fügt den Auswahlbereich links ein
        add(editorPanel); // fügt das Eingabeformular rechts ein

        articleList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && !updatingArticleList) {
                showSelectedArticle(); // übernimmt die Daten des ausgewählten Artikels
            }
        });

        sportTypeFilter.addActionListener(event -> refreshArticleSelection()); // filtert die Liste nach Sportart
        manufacturerFilter.addActionListener(event -> refreshArticleSelection()); // filtert die Liste nach Hersteller
        sortBox.addActionListener(event -> refreshArticleSelection()); // sortiert die sichtbaren Artikel
    }

    /**
     * Diese Methode erstellt Filter und Sortierung für selectionPanel
     * 
     */
    private void addSelectionFields(JPanel panel) {
        GridBagConstraints cell = gridCell(0, 0); // beginnt links oben in der ersten Spalte
        cell.weightx = 1.0; // gibt der Spalte den zusätzlichen Platz in der Breite

        panel.add(new JLabel("Nach Sportart filtern"), cell);
        cell.gridy++; // wechselt in die nächste Zeile
        panel.add(sportTypeFilter, cell);

        cell.gridy++;
        panel.add(new JLabel("Nach Hersteller filtern"), cell);
        cell.gridy++;
        panel.add(manufacturerFilter, cell);

        cell.gridy++;
        panel.add(new JLabel("Sortieren nach"), cell);
        cell.gridy++;
        panel.add(sortBox, cell);

        cell.gridy++;
        cell.weighty = 1.0; // gibt der Artikelliste den freien Platz in der Höhe
        cell.fill = GridBagConstraints.BOTH; // lässt die Liste in beide Richtungen wachsen
        articleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // erlaubt die Auswahl eines Artikels
        panel.add(new JScrollPane(articleList), cell); // zeigt die Liste direkt unter der Sortierung an
    }

    /**
     * Diese Methode erstellt Eingabefelder mit ihren Beschriftungen für editorPanel
     * 
     */
    private void addEditorFields(JPanel panel) {
        addEditorRow(panel, "Artikelnummer", articleNumberField, 0);
        addEditorRow(panel, "Sportart", sportTypeBox, 1);
        addEditorRow(panel, "Hersteller", manufacturerBox, 2);
        addEditorRow(panel, "Bezeichnung", designationField, 3);
    }

    /**
     * Diese Methode füllt die Herstellerliste für die Suche (Filter) und das
     * Formular
     */
    private void loadManufacturers() {
        List<Manufacturer> manufacturers = manager.readAllManufacturers(); // fordert die Hersteller beim Datenmanager
                                                                           // an

        if (manufacturers == null) { // erkennt einen Lesefehler
            statusLabel.setText("Hersteller konnten nicht geladen werden.");
            return; // beendet das Laden bei einem Fehler
        }

        if (manufacturers.isEmpty()) { // erkennt eine erfolgreich geladene, aber leere Liste
            statusLabel.setText("Keine Hersteller vorhanden.");
            return; // beendet das Laden, weil es keine Einträge gibt
        }

        for (Manufacturer manufacturer : manufacturers) {
            manufacturerBox.addItem(manufacturer); // bietet den Hersteller im Formular an
            manufacturerFilter.addItem(manufacturer); // bietet denselben Hersteller als Filter an
        }
    }

    /**
     * Diese Methode lädt alle gespeichrten Artikel aus der Datenbank
     */
    private void loadArticles() { // lädt alle gespeicherten Artikel aus der Datenbank
        List<Article> articles = manager.readAllArticles(); // fordert die Artikel beim Datenmanager an

        if (articles == null) { // erkennt einen Lesefehler
            statusLabel.setText("Artikel konnten nicht geladen werden.");
            return; // behält bei einem Lesefehler die bisherige Liste
        }

        loadedArticles.clear(); // entfernt die bisher geladenen Artikel
        loadedArticles.addAll(articles); // übernimmt die neu gelesenen Artikel
        refreshArticleSelection(); // zeigt die geladenen Artikel in der Liste an
    }

    /**
     * Diese Methode baut die Liste links anhand der filter und der Sortierung auf
     */
    private void refreshArticleSelection() {
        Article selectedArticle = articleList.getSelectedValue(); // merkt sich die aktuelle Auswahl
        SportType selectedSportType = (SportType) sportTypeFilter.getSelectedItem(); // liest den gewählten
                                                                                     // Sportartfilter
        Manufacturer selectedManufacturer = (Manufacturer) manufacturerFilter.getSelectedItem(); // liest den gewählten
                                                                                                 // Herstellerfilter
        List<Article> matchingArticles = new ArrayList<>(); // sammelt die Artikel, die zu beiden Filtern passen

        for (Article article : loadedArticles) {
            boolean matchesSportType = selectedSportType == null
                    || article.getSportType() == selectedSportType; // lässt bei leerem Filter jede Sportart zu
            boolean matchesManufacturer = selectedManufacturer == null
                    || article.getManufacturer().getId() == selectedManufacturer.getId(); // vergleicht bei gesetztem
                                                                                          // Filter die Hersteller-IDs

            if (matchesSportType && matchesManufacturer) {
                matchingArticles.add(article); // übernimmt den Artikel, wenn beide Filterbedingungen erfüllt sind
            }
        }

        Comparator<Article> byArticleNumber = Comparator.comparing(
                article -> article.getArticleNumber(), // liest die Artikelnummer als Sortierschlüssel
                String.CASE_INSENSITIVE_ORDER); // vergleicht die Nummern ohne Beachtung der Groß- und Kleinschreibung

        if (sortBox.getSelectedIndex() == 1) { // erkennt die Auswahl „Artikelnummer A–Z“
            matchingArticles.sort(byArticleNumber); // sortiert aufsteigend
        } else if (sortBox.getSelectedIndex() == 2) { // erkennt die Auswahl „Artikelnummer Z–A“
            matchingArticles.sort(byArticleNumber.reversed()); // sortiert absteigend
        }

        DefaultListModel<Article> model = new DefaultListModel<>(); // speichert die Einträge, die JList anzeigen soll

        for (Article article : matchingArticles) {
            model.addElement(article); // übernimmt jeden Artikel in der angezeigten Reihenfolge
        }

        boolean selectionRemains = selectedArticle != null && matchingArticles.contains(selectedArticle); // prüft, ob
                                                                                                          // der
                                                                                                          // gewählte
                                                                                                          // Artikel
                                                                                                          // sichtbar
                                                                                                          // bleibt

        updatingArticleList = true; // überspringt Auswahlereignisse während des Austauschs
        try {
            articleList.setModel(model); // zeigt die gefilterte und sortierte Liste an

            if (selectionRemains) {
                articleList.setSelectedValue(selectedArticle, true); // stellt die Auswahl wieder her und scrollt zum
                                                                     // Artikel
            }
        } finally {
            updatingArticleList = false; // gibt die Verarbeitung von Auswahlereignissen auch im Fehlerfall wieder frei
        }

        if (selectedArticle != null && !selectionRemains) {
            clearForm(); // leert das Formular, wenn der gewählte Artikel aus der Liste fällt
        }
    }

    /**
     * Diese Methode zeigt die Daten des ausgewählten Artikels im Formular rechts
     */
    private void showSelectedArticle() {
        Article article = articleList.getSelectedValue(); // liest den markierten Artikel aus der Liste

        if (article == null) { // erkennt eine aufgehobene Auswahl
            clearForm(); // leert das Formular bei fehlender Auswahl
            return; // beendet das Laden der Artikeldaten
        }

        articleNumberField.setText(article.getArticleNumber()); // zeigt die gespeicherte Artikelnummer an
        sportTypeBox.setSelectedItem(article.getSportType()); // wählt die gespeicherte Sportart aus
        designationField.setText(article.getDesignation().getName()); // zeigt die gespeicherte Bezeichnung an
        manufacturerBox.setSelectedIndex(-1); // entfernt die bisherige Herstellerauswahl vor der Suche

        for (int index = 0; index < manufacturerBox.getItemCount(); index++) {
            Manufacturer manufacturer = manufacturerBox.getItemAt(index); // liest den Hersteller an der aktuellen
                                                                          // Position

            if (manufacturer.getId() == article.getManufacturer().getId()) { // prüft, ob beide Hersteller dieselbe ID
                                                                             // haben
                manufacturerBox.setSelectedIndex(index); // wählt den gefundenen Hersteller im Formular aus
                break; // beendet die Suche nach dem ersten Treffer
            }
        }
    }

    /**
     * Diese Methode löscht alles aus den Eingabefeldern
     */
    private void clearForm() {
        articleNumberField.setText(""); // entfernt die Artikelnummer
        sportTypeBox.setSelectedIndex(-1); // hebt die Auswahl der Sportart auf
        manufacturerBox.setSelectedIndex(-1); // hebt die Auswahl des Herstellers auf
        designationField.setText(""); // entfernt die Artikelbezeichnung
    }

    /**
     * Diese Methode setzt alles zurück
     */
    private void resetForm() {
        sportTypeFilter.setSelectedIndex(-1); // lässt wieder alle Sportarten zu
        manufacturerFilter.setSelectedIndex(-1); // lässt wieder alle Hersteller zu
        sortBox.setSelectedIndex(0); // stellt die ursprüngliche Reihenfolge wieder her
        articleList.clearSelection(); // hebt die Artikelauswahl auf
        clearForm(); // leert die Eingabefelder
        statusLabel.setText(""); // entfernt die bisherige Meldung
    }

    /**
     * Diese Methode speichert einen neuen Artikel
     */
    private void saveArticle() { // speichert einen neuen oder geänderten Artikel
        String articleNumber = articleNumberField.getText().trim(); // liest die Artikelnummer ohne äußere Leerzeichen
        String designationName = designationField.getText().trim(); // liest die Bezeichnung ohne äußere Leerzeichen
        SportType sportType = (SportType) sportTypeBox.getSelectedItem(); // liest die für den Artikel gewählte Sportart
        Manufacturer manufacturer = (Manufacturer) manufacturerBox.getSelectedItem(); // liest den für den Artikel
                                                                                      // gewählten Hersteller

        if (articleNumber.isEmpty() || designationName.isEmpty()
                || sportType == null || manufacturer == null) { // prüft, ob alle Pflichtfelder ausgefüllt sind
            statusLabel.setText("Bitte alle Felder ausfüllen.");
            return; // bricht das Speichern bei fehlenden Eingaben ab
        }

        Article selectedArticle = articleList.getSelectedValue(); // prüft anhand der Auswahl, ob ein vorhandener
                                                                  // Artikel bearbeitet wird
        Article article = new Article(articleNumber, sportType, manufacturer,
                new Designation(designationName)); // erstellt ein Artikelobjekt mit den aktuellen Eingaben

        if (selectedArticle != null) {
            article.setId(selectedArticle.getId()); // übernimmt die ID für die Aktualisierung
        }

        try {
            manager.saveArticle(article); // übergibt den Artikel zum Speichern an den Datenmanager
        } catch (SQLException exception) { // fängt einen Datenbankfehler beim Speichern ab
            statusLabel.setText("Fehler beim Speichern: " + exception.getMessage());
            return; // lässt die Eingaben zur Korrektur im Formular stehen
        }

        if (selectedArticle == null) {
            loadedArticles.add(article); // ergänzt den neu gespeicherten Artikel
        } else {
            int index = loadedArticles.indexOf(selectedArticle); // findet die Position des bisherigen Artikels
            loadedArticles.set(index, article); // ersetzt den bisherigen Artikel durch die gespeicherte Fassung
        }

        refreshArticleSelection(); // wendet die aktuellen Filter und die Sortierung auf die aktualisierte Liste an
        clearForm(); // leert die Felder vor der erneuten Auswahl
        articleList.setSelectedValue(article, true); // wählt den gespeicherten Artikel aus, wenn er sichtbar ist
        JOptionPane.showMessageDialog(this, "Gespeichert!", "Speichern", JOptionPane.INFORMATION_MESSAGE); // zeigt die
                                                                                                           // Speicherbestätigung
                                                                                                           // mit einer
                                                                                                           // OK
                                                                                                           // Schaltfläche
                                                                                                           // an
        // mit this nutzt Swing die Koordinaten der Form (BaseFrame) als Orientierung
        // für die Anzeige des Popups
    }

    /**
     * Diese Methode löscht ein Artikel
     */
    private void deleteSelectedArticle() { // löscht den ausgewählten Artikel aus der Datenbank
        Article article = articleList.getSelectedValue(); // liest den markierten Artikel aus der Liste

        if (article == null) { // prüft, ob ein Artikel zum Löschen ausgewählt wurde
            statusLabel.setText("Bitte zuerst einen Artikel auswählen.");
            return; // beendet den Vorgang ohne Datenbankzugriff
        }

        try {
            manager.deleteArticle(article); // lässt den Datenmanager den Artikel anhand seiner ID löschen
        } catch (SQLException exception) { // fängt einen Datenbankfehler beim Löschen ab
            statusLabel.setText("Fehler beim Löschen: " + exception.getMessage());
            return; // behält den Artikel bei einem Fehler in der Liste
        }

        loadedArticles.remove(article); // entfernt den Artikel nach erfolgreichem Löschen aus der geladenen Liste
        refreshArticleSelection(); // aktualisiert die sichtbare Liste und leert das Formular
        JOptionPane.showMessageDialog(this, "Artikel wurde gelöscht.", "Artikel löschen",
                JOptionPane.INFORMATION_MESSAGE); // zeigt nach dem Löschen eine Bestätigung
    }

    /**
     * Diesel Methode fügt eine Zeile der Form hinzu: Bezeichnung links und
     * Eingabefeld rechts
     * 
     */
    private void addEditorRow(JPanel panel, String label, Component field, int row) {
        panel.add(new JLabel(label), gridCell(0, row)); // setzt die Beschriftung in die linke Spalte

        GridBagConstraints fieldCell = gridCell(1, row); // setzt das Feld in dieselbe Zeile rechts
        fieldCell.weightx = 1.0; // weist dem Eingabefeld den zusätzlichen Platz in der Breite zu
        panel.add(field, fieldCell);
    }

    /**
     * Diese Methode erstellt pro Aufruf ein GridBagConstraints Objekt für eine
     * Komponente
     * 
     */
    private GridBagConstraints gridCell(int column, int row) {
        GridBagConstraints cell = new GridBagConstraints();
        cell.gridx = column; // legt die Spalte fest
        cell.gridy = row; // legt die Zeile fest
        cell.fill = GridBagConstraints.HORIZONTAL; // nutzt die verfügbare Breite der Zelle
        cell.insets = new Insets(2, 4, 2, 4); // schafft außen Abstand zum nächsten Element
        return cell;
    }

    /**
     * Diese Method erstellt ein Button, platziert sie im Panel und registriert ihren Actionlistener
     * 
     */
    private void addEditorButton(JPanel panel, String text, int column, int row,
            ActionListener listener) {
        JButton button = new JButton(text); // erstellt ein Button mit dem angegebenen Text
        panel.add(button, gridCell(column, row)); // platziert die Schaltfläche in der gewünschten Zelle
        button.addActionListener(listener); // verbindet die Schaltfläche mit ihrer Aktion
    }

}