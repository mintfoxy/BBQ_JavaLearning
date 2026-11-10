package gui;

import java.sql.SQLException;
import java.util.List;

import data.dataHandling.DataManager;
import data.dataHandling.MySQLDataManager;
import data.io.FileIO;
import data.objekte.Article;
import data.objekte.Designation;
import data.objekte.Manufacturer;
import data.objekte.SportType;

public class Main {

    private final DataManager manager = MySQLDataManager.getInstance(); // verwendet MySQL zum Laden und Speichern der
                                                                        // Artikel
    private List<Article> articles; // speichert die geladenen und neu eingegebenen Artikel

    public static void main(String[] args) {
        Main application = new Main(); // erstellt die Konsolenanwendung
        application.start(); // startet die Auswahl
    }

    /**
     * lädt die Daten und fragt nach der gewünschten Aktion
     */
    private void start() {

        articles = manager.readAllArticles(); // lädt die gespeicherten Artikel und aktualisiert den id Zähler

        if (articles == null) { // prüft, ob das Laden fehlgeschlagen ist
            IO.println("Artikel konnten nicht geladen werden.");
            return;
        }

        String choice = IO.readln(
                "1 - Artikel eingeben\n2 - Artikel anzeigen\n3 - Artikel löschen\n0 - Beenden\nAuswahl: "); // liest die
                                                                                                            // gewünschte
                                                                                                            // Aktion

        if (choice.equals("1")) {
            inputArticles();
        } else if (choice.equals("2")) {
            showArticles();
        } else if (choice.equals("3")) {
            deleteArticles();
        } else if (!choice.equals("0")) {
            IO.println("Ungültige Auswahl");
        }

        IO.println("Ende.");
    }

    /**
     * Diese Methode ermöglicht die Eingabe mehrerer Artikel
     */

    private void inputArticles() {

        boolean more = true; // erlaubt zunächst die Eingabe eines Artikels

        while (more) { // wiederholt die Eingabe auf Wunsch des Benutzers

            Article article = inputArticle(); // erstellt einen Artikel aus den Tastatureingaben

            try {
                manager.saveArticle(article); // speichert den Artikel in der Datenbank

                articles.add(article); // ergänzt den erfolgreich gespeicherten Artikel in der Liste
                IO.println("Artikel gespeichert:"); // bestätigt die erfolgreiche Speicherung
                IO.println(article.toString()); // zeigt den gespeicherten Artikel

            } catch (SQLException exception) { // fängt einen Fehler beim Speichern ab
                IO.println("Fehler beim Speichern: " + exception.getMessage()); // zeigt die Fehlerbeschreibung
                return; // beendet die Eingabe nach dem Fehler
            }

            String answer = IO.readln(
                    "\nWeiteren Artikel eingeben? (j = ja, alles andere = beenden): "); // fragt nach einem weiteren
                                                                                        // Artikel

            more = answer.trim().equalsIgnoreCase("j"); // wiederholt die Eingabe nur bei j oder J
        }
    }

    /**
     * Diese Methode liest die Daten eines einzelnen Artikels über die
     * Tastatureingabe
     * 
     * @return
     */

    private Article inputArticle() { // liest die Daten eines einzelnen Artikels

        String articleNumber = inputText("\nArtikelnummer eingeben: "); // liest die Artikelnummer
        SportType sportType = inputSportType(); // bietet die Auswahl der Sportart an
        Manufacturer manufacturer = inputManufacturer(); // liest den Hersteller
        Designation designation = inputDesignation(); // liest die Bezeichnung

        return new Article(articleNumber, sportType,
                manufacturer, designation); // erstellt den Artikel mit automatischer id
    }

    private SportType inputSportType() { // liest die Sportart durch eine nummerierte Auswahl

        SportType[] sportTypes = SportType.values(); // übernimmt alle Sportarten aus dem enum

        while (true) { // wiederholt die Auswahl bis zu einer gültigen Eingabe

            IO.println("\nSportart auswählen:");

            for (int i = 0; i < sportTypes.length; i++) { // geht alle Sportarten durch
                IO.println((i + 1) + " - " + sportTypes[i]); // zeigt die Nummer und den Namen
            }

            String choice = IO.readln("Auswahl: ").trim(); // liest die gewünschte Nummer als Text

            for (int i = 0; i < sportTypes.length; i++) { // prüft die verfügbaren Auswahlnummern

                if (choice.equals(String.valueOf(i + 1))) { // erkennt die ausgewählte Nummer
                    return sportTypes[i]; // gibt die passende Sportart zurück
                }
            }

            IO.println("Ungültige Auswahl. Bitte erneut versuchen.");
        }
    }

    private Manufacturer inputManufacturer() { // liest den Namen des Herstellers
        String name = inputText("Hersteller eingeben: ");
        return new Manufacturer(name); // erstellt das Herstellerobjekt
    }

    private Designation inputDesignation() { // liest die Bezeichnung des Artikels
        String name = inputText("Bezeichnung eingeben: ");
        return new Designation(name); // erstellt das Bezeichnungsobjekt
    }

    private String inputText(String prompt) { // liest einen ausgefüllten Text ohne Semikolon

        String text = IO.readln(prompt).trim(); // liest den Text und entfernt äußere Leerzeichen

        while (text.isBlank() || text.contains(FileIO.DELIMITER)) { // erkennt leere Eingaben oder das csv Trennzeichen
            IO.println("Bitte einen Text ohne Semikolon eingeben.");
            text = IO.readln(prompt).trim(); // liest neuen Text
        }

        return text;
    }

    /**
     * Diese Methode zeigt die gespeicherten Artikelobjekte
     */

    private void showArticles() {

        if (articles.isEmpty()) { // prüft, ob Artikel vorhanden sind
            IO.println("Keine Artikel vorhanden");
            return;
        }

        IO.println("ID | Artikelnummer | Sportart | Hersteller | Bezeichnung");

        for (Article article : articles) { // geht die Artikelobjekte einzeln durch
            IO.println(article.toString()); // gibt jeden Artikel als lesbaren Text aus
        }
    }

    /**
     * Diese Methode löscht einen Artikel
     */

    private void deleteArticles() {

        if (articles.isEmpty()) { // prüft, ob Artikel in der Liste vorhanden sind
            IO.println("Keine Artikel vorhanden");
            return;
        }

        showArticles(); // Aufruf der Methode zur Anzeige

        String choice = IO.readln("Geben Sie die id ein, die gelöscht werden soll: "); // liest die eingegebene id

        int choiceId; // Variable für die Umwandlung der Auswahl in ein Integer

        try {
            choiceId = Integer.parseInt(choice); // wandelt die Eingabe in eine Zahl um
        } catch (NumberFormatException exception) { // falls etwas anderes eingegeben wurde
            IO.println("Ungültige ID");
            return;
        }

        for (int i = 0; i < articles.size(); i++) {

            Article article = articles.get(i); // liest den Artikel an dem konkreten Index

            if (article.getId() == choiceId) { // vergleicht die Artikel-ID mit der eingegebenen ID

                try {
                    manager.deleteArticle(article); // löscht den Artikel aus der Datenbank
                    articles.remove(i); // löscht den Artikel aus der Liste
                    IO.println("Artikel wurde gelöscht.");

                } catch (SQLException exception) {
                    IO.println("Fehler beim Löschen: " + exception.getLocalizedMessage());
                }
                return;
            }
        }
        IO.println("Artikel mit der ID " + choiceId + " nicht gefunden.");
    }
}
