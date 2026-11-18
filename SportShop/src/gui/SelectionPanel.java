package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import data.objekte.Article;
import data.objekte.Manufacturer;
import data.objekte.SportType;

/**
 * Diese Panel enthält Filter, Sortierung, Liste Artikeln, geladene Artikel
 * SelectionPanel
 */
public class SelectionPanel extends BasePanel {

    private final Delegate delegate; // Zugriff auf Interface für die Zusammenarbeit zwischen Panellen

    private final List<Article> loadedArticles = new ArrayList<>(); // hält alle geladenen Artikel für die Filter bereit

    private final JComboBox<SportType> sportTypeFilter = new JComboBox<>(); // Filter Sportart
    private final JComboBox<Manufacturer> manufacturerFilter = new JComboBox<>(); // Filter Hersteller

    private final JComboBox<String> sortBox = new JComboBox<>(new String[] {
            "Standard", "Artikelnummer A–Z", "Artikelnummer Z–A"
    }); // auf- und absteigende Sortierung

    private final JList<Article> articleList = new JList<>(); // Liste mit gefilterten Artikeln
    private boolean updatingArticleList; // kennzeichnet den laufenden Austausch des Listeninhalts

    /**
     * Konstruktor
     */
    public SelectionPanel(Delegate delegate) {
        this.delegate = delegate; // speichert den Empfänger der Auswahlereignisse
        initLayout(); // erstellt Layout
        initComponents(); // erstellt Filter, Sortierung und Artikelliste
    }

    /**
     * richtet das Layout der Auswahlpanel ein
     */
    @Override
    protected void initLayout() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Auswahl")); // Beschriftung der Panel
    }

    /**
     * Diese Methode erstellt die Komponenten der Panel
     */
    @Override
    protected void initComponents() {
        sportTypeFilter.addItem(null); // leerer Eintrag im Filter für alle Sportarten

        for (SportType sportType : SportType.values()) {
            sportTypeFilter.addItem(sportType); // fügt jede verfügbare Sportart zum Filter hinzu
        }

        manufacturerFilter.addItem(null); // leerer Eintrag im Filter für alle Hersteller

        GridBagConstraints cell = gridCell(0, 0); // beginnt links oben in der ersten Spalte
        cell.weightx = 1.0; // gibt der Spalte den zusätzlichen Platz in der Breite

        add(new JLabel("Nach Sportart filtern"), cell);
        cell.gridy++; // wechselt in die nächste Zeile
        add(sportTypeFilter, cell);

        cell.gridy++;
        add(new JLabel("Nach Hersteller filtern"), cell);
        cell.gridy++;
        add(manufacturerFilter, cell);

        cell.gridy++;
        add(new JLabel("Sortieren nach"), cell);
        cell.gridy++;
        add(sortBox, cell);

        cell.gridy++;
        cell.weighty = 1.0; // gibt der Artikelliste den freien Platz in der Höhe
        cell.fill = GridBagConstraints.BOTH; // lässt die Liste in beide Richtungen wachsen
        articleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // erlaubt die Auswahl eines Artikels
        add(new JScrollPane(articleList), cell); // zeigt die Liste direkt unter der Sortierung an

        articleList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && !updatingArticleList) {
                delegate.articleSelected(articleList.getSelectedValue()); // meldet den ausgewählten Artikel
            }
        });

        sportTypeFilter.addActionListener(event -> refreshArticleSelection()); // filtert die Liste nach Sportart
        manufacturerFilter.addActionListener(event -> refreshArticleSelection()); // filtert die Liste nach Hersteller
        sortBox.addActionListener(event -> refreshArticleSelection()); // sortiert die sichtbaren Artikel
    }

    /**
     * übernimmt die geladenen Artikel von der ArticleFormPanel
     */
    public void setArticles(List<Article> articles) {
        loadedArticles.clear(); // entfernt die bisher geladenen Artikel
        loadedArticles.addAll(articles); // übernimmt die neu gelesenen Artikel
        refreshArticleSelection(); // zeigt die geladenen Artikel in der Liste an
        delegate.articleSelected(articleList.getSelectedValue()); // gleicht die rechte Panel mit der Auswahl ab
    }

    /**
     * füllt den Herstellerfilter mit den übergebenen Herstellern
     */
    public void setManufacturers(List<Manufacturer> manufacturers) {
        manufacturerFilter.removeAllItems();
        manufacturerFilter.addItem(null); // leerer Eintrag im Filter für alle Hersteller

        for (Manufacturer manufacturer : manufacturers) {
            manufacturerFilter.addItem(manufacturer);
        }

        manufacturerFilter.setSelectedIndex(-1); // startet ohne ausgewählten Hersteller
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

        if (sortBox.getSelectedIndex() == 1) {
            matchingArticles.sort(byArticleNumber); // sortiert aufsteigend
        } else if (sortBox.getSelectedIndex() == 2) {
            matchingArticles.sort(byArticleNumber.reversed()); // sortiert absteigend
        }

        DefaultListModel<Article> model = new DefaultListModel<>(); // speichert die Einträge, die JList anzeigen soll

        for (Article article : matchingArticles) {
            model.addElement(article); // übernimmt jeden Artikel in der angezeigten Reihenfolge
        }

        boolean selectionRemains = selectedArticle != null
                && matchingArticles.contains(selectedArticle); // prüft, ob der gewählte Artikel sichtbar bleibt

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
            delegate.articleSelected(null); // meldet, dass der ausgewählte Artikel nicht mehr sichtbar ist
        }
    }

    /**
     * ergänzt einen gespeicherten Artikel oder ersetzt seine bisherige Fassung
     */
    public void articleSaved(Article article) {
        int existingIndex = -1; // kennzeichnet zunächst einen neuen Artikel

        for (int index = 0; index < loadedArticles.size(); index++) {
            if (loadedArticles.get(index).getId() == article.getId()) {
                existingIndex = index; // findet den Artikel anhand seiner ID
                break;
            }
        }

        if (existingIndex == -1) {
            loadedArticles.add(article); // ergänzt den neu gespeicherten Artikel
        } else {
            loadedArticles.set(existingIndex, article); // ersetzt die bisherige Fassung
        }

        articleList.clearSelection(); // hebt die bisherige Artikelauswahl auf

        refreshArticleSelection(); // zeigt die aktualisierte Liste mit den aktuellen Filtern an
        
        delegate.articleSelected(null); // leert das Formular und entfernt den bearbeiteten Artikel aus der Editorpanel
    }

    /**
     * entfernt einen erfolgreich gelöschten Artikel aus der Liste
     */
    public void articleDeleted(Article article) {
        loadedArticles.removeIf(
                existingArticle -> existingArticle.getId() == article.getId()); // entfernt den Artikel anhand seiner ID

        refreshArticleSelection(); // aktualisiert die sichtbare Liste
        delegate.articleSelected(articleList.getSelectedValue()); // gleicht das Formular mit der Auswahl ab
    }

    /**
     * hebt die Artikelauswahl auf
     */
    public void clearArticleSelection() {
        articleList.clearSelection();
    }

    /**
     * setzt Filter, Sortierung und Artikelauswahl zurück
     */
    public void resetSelection() {
        sportTypeFilter.setSelectedIndex(-1); // lässt wieder alle Sportarten zu
        manufacturerFilter.setSelectedIndex(-1); // lässt wieder alle Hersteller zu
        sortBox.setSelectedIndex(0); // stellt die ursprüngliche Reihenfolge wieder her
        articleList.clearSelection(); // hebt die Artikelauswahl auf
    }
}