package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.objekte.Article;
import data.objekte.Designation;
import data.objekte.Manufacturer;
import data.objekte.SportType;

public class EditorPanel extends BasePanel {

    private final Delegate delegate; // Zugriff auf Interface für die Zusammenarbeit zwischen Panellen

    private Article selectedArticle; // merkt sich den Artikel, dessen Daten bearbeitet werden

    private final JTextField articleNumberField = new JTextField(); // nimmt die Artikelnummer auf
    private final JComboBox<SportType> sportTypeBox = new JComboBox<>(SportType.values()); // alle Sportarten
    private final JComboBox<Manufacturer> manufacturerBox = new JComboBox<>(); // alle Hersteller
    private final JTextField designationField = new JTextField(); // nimmt die Artikelbezeichnung auf
    private final JLabel statusLabel = new JLabel(); // zeigt Rückmeldungen unter den Schaltflächen an

    /**
     * Konstruktor
     */
    public EditorPanel(Delegate delegate) {
        this.delegate = delegate; // speichert den Empfänger der Schaltflächenereignisse
        initLayout(); // richtet das Layout der Editorpanel ein
        initComponents(); // erstellt die Eingabefelder und Schaltflächen
        clearForm(); // entfernt die automatische Vorauswahl aus dem Formular
    }

    /**
     * richtet das Layout der Editorpanel ein
     */
    @Override
    protected void initLayout() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Artikel")); // Beschriftung der Panel
    }

    /**
     * Diese Methode erstellt die Komponenten der Panel
     */
    @Override
    protected void initComponents() {
        addEditorFields(); // fügt die vier Eingabefelder in den Zeilen 0 bis 3 hinzu

        GridBagConstraints space = gridCell(0, 4); // legt den Zwischenraum in Zeile 4 an
        space.gridwidth = 2; // erstreckt den Zwischenraum über beide Spalten
        space.weighty = 1.0; // weist dem Zwischenraum die zusätzliche Höhe zu
        space.fill = GridBagConstraints.BOTH;
        add(Box.createVerticalGlue(), space); // hält die Schaltflächen am unteren Rand

        JButton saveButton = new JButton("Speichern"); // erstellt die Schaltfläche zum Speichern
        GridBagConstraints saveCell = gridCell(0, 5); // platziert Speichern oberhalb der beiden anderen Schaltflächen
        saveCell.gridwidth = 2; // erstreckt die Schaltfläche über beide Spalten
        add(saveButton, saveCell);
        saveButton.addActionListener(event -> saveArticle()); // prüft die Eingaben und meldet den Speicherwunsch

        addEditorButton("Reset", 0, 6,
                event -> delegate.resetRequested()); // Schaltfläche zum Zurücksetzen beider Panelle an

        addEditorButton("Artikel löschen", 1, 6,
                event -> deleteSelectedArticle()); // prüft die Auswahl und meldet den Löschwunsch

        GridBagConstraints status = gridCell(0, 7); // legt die Meldung in die Zeile unter den Schaltflächen
        status.gridwidth = 2; // erstreckt die Meldung über beide Spalten
        add(statusLabel, status);
    }

    /**
     * Diese Methode erstellt Eingabefelder mit ihren Beschriftungen für editorPanel
     */
    private void addEditorFields() {
        addEditorRow("Artikelnummer", articleNumberField, 0);
        addEditorRow("Sportart", sportTypeBox, 1);
        addEditorRow("Hersteller", manufacturerBox, 2);
        addEditorRow("Bezeichnung", designationField, 3);
    }

    /**
     * füllt die Herstellerauswahl mit den übergebenen Herstellern
     */
    public void setManufacturers(List<Manufacturer> manufacturers) {
        manufacturerBox.removeAllItems();

        for (Manufacturer manufacturer : manufacturers) {
            manufacturerBox.addItem(manufacturer); // bietet den Hersteller im Formular an
        }

        showSelectedArticle(selectedArticle); // stellt die passende Herstellerauswahl wieder her
    }

    /**
     * Diese Methode zeigt die Daten des ausgewählten Artikels im Formular rechts
     */
    public void showSelectedArticle(Article article) {
        selectedArticle = article; // merkt sich den übergebenen Artikel für Speichern und Löschen

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

            if (manufacturer.getId() == article.getManufacturer().getId()) {
                manufacturerBox.setSelectedIndex(index); // wählt den gefundenen Hersteller im Formular aus
                break; // beendet die Suche nach dem ersten Treffer
            }
        }
    }

    /**
     * Diese Methode fügt eine Zeile der Form hinzu, mit Beschriftung links und
     * Eingabefeld rechts
     */
    private void addEditorRow(String label, Component field, int row) {
        add(new JLabel(label), gridCell(0, row)); // setzt die Beschriftung in die linke Spalte

        GridBagConstraints fieldCell = gridCell(1, row); // setzt das Feld in dieselbe Zeile rechts
        fieldCell.weightx = 1.0; // weist dem Eingabefeld den zusätzlichen Platz in der Breite zu
        add(field, fieldCell);
    }

    /**
     * Diese Methode erstellt einen Button, platziert ihn im Panel und registriert
     * seinen ActionListener
     */
    private void addEditorButton(String text, int column, int row, ActionListener listener) {
        JButton button = new JButton(text); // erstellt einen Button mit dem angegebenen Text
        add(button, gridCell(column, row)); // platziert die Schaltfläche in der gewünschten Zelle
        button.addActionListener(listener); // verbindet die Schaltfläche mit ihrer Aktion
    }

    /**
     * prüft die Eingaben und übergibt einen neuen oder geänderten Artikel an den
     * Delegate
     */
    private void saveArticle() {

        String articleNumber = articleNumberField.getText(); // liest die Artikelnummer

        String designationName = designationField.getText(); // liest die Bezeichnung

        SportType sportType = (SportType) sportTypeBox.getSelectedItem(); // liest die für den Artikel gewählte Sportart

        Manufacturer manufacturer = (Manufacturer) manufacturerBox.getSelectedItem(); // liest den für den Artikel gewählten Hersteller

        if (articleNumber.isEmpty() || designationName.isEmpty() // falls etwas nicht bestimmt wurde
                || sportType == null || manufacturer == null) {
            statusLabel.setText("Bitte alle Felder ausfüllen.");
            return; // bricht das Speichern ab
        }

        Article article = new Article(articleNumber, sportType, manufacturer,
                new Designation(designationName)); // erstellt ein Artikelobjekt mit den aktuellen Eingaben

        if (selectedArticle != null) { // prüfen, ob ein vorhanderner Artikel bearbeitet wird
            article.setId(selectedArticle.getId()); // übernimmt die ID für die Aktualisierung
            // beim vorhandenen Artikel wird INSERT ausgeführt und die ID wird von der Datenbank gesetzt
        }

        delegate.saveRequested(article); // übergibt Artikel an den gespeicherten Empfänger bzw. an ArticleFormPanel
    }

    /**
     * prüft die Auswahl und übergibt den Artikel zum Löschen an den Delegate
     */
    private void deleteSelectedArticle() {

        if (selectedArticle == null) { // falls kein Artikel ausgewählt wurde
            statusLabel.setText("Bitte zuerst einen Artikel auswählen.");
            return; // beendet den Vorgang ohne Datenbankzugriff
        }

        delegate.deleteRequested(selectedArticle); // meldet den Löschwunsch an die ArticleFormPanel
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
     * zeigt eine Meldung an, die von der ArticleFormPanel übergeben wurde
     */
    public void showStatus(String message) {
        statusLabel.setText(message);
    }
}