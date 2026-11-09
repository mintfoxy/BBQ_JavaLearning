package data.dataHandling;

import java.util.ArrayList;
import java.util.List;

import data.io.FileIO;
import data.objekte.Article;
import data.objekte.ObjectFactory;

public class CSVDataManager implements DataManager { // verwaltet Artikel in einer CSV Datei

    /**
     * Singleton
     */

    private static CSVDataManager instance = new CSVDataManager(); // erstellt einer Instanz des Managers

    public static CSVDataManager getInstance() { // so wird der Manager in anderen Klassen aufgerufen
        return instance; // die Instanz des Managers, die nur einmalig zum einem Zeitpunkt existieren
                         // kann
    }

    private CSVDataManager() { // Konstruktor des Managers; andere Klassen können keine neue Instanz des
                               // Managers
                               // mti new erstellen, weil private
    }

    /**
     * Lädt alle gespeicherten Artikeln
     * Jede gelesene Zeile ist ein String - ein Eintrag, d.h. einA Artikel.
     * Die String-Zeilen werden gesplittet zu String[], aus dem
     * anschließend eine Instanz der Klasse Article erzeugt und zur
     * Liste hinzugefügt wird.
     *
     * @return Liste der Aufgabe-Instanzen
     */

    private static final String FILE_NAME = "articles.csv"; // Dateiname innerhalb des Datenordners

    @Override
    public List<Article> readAllArticles() { // lädt alle gespeicherten Artikel als Objekte

        List<String> lines = FileIO.readFromFile(FILE_NAME); // liest die Datei zunächst als Textzeilen

        if (lines == null) { // prüft, ob die Datei nicht gelesen werden konnte
            IO.println("Artikeldatei konnte nicht gelesen werden."); // zeigt eine Fehlermeldung
            return null; // gibt den Lesefehler weiter
        }

        List<Article> articles = new ArrayList<>(); // sammelt die erzeugten Artikelobjekte

        for (String line : lines) { // verarbeitet jede gespeicherte Zeile

            if (line.isBlank()) { // erkennt leere Zeilen
                continue; // überspringt diese Zeile
            }

            String[] data = line.split(FileIO.DELIMITER, -1); // trennt die Felder und behält leere Felder
            Article article = ObjectFactory.articleFromData(data); // erstellt das Artikelobjekt aus den Feldern

            if (article == null) { // erkennt einen fehlerhaften Datensatz
                IO.println("Ungültige csv Zeile: " + line); // zeigt die betroffene Zeile
                return null;
            }

            for (Article existingArticle : articles) { // prüft die bereits geladenen Artikel

                if (existingArticle.getId() == article.getId()) { // erkennt eine doppelte interne Nummer
                    IO.println("Doppelte Artikel id: " + article.getId()); // zeigt die doppelte Nummer
                    return null; // bricht das Laden ab
                }
            }

            articles.add(article); // fügt das fertige Objekt zur Liste hinzu
        }

        return articles; // gibt die geladenen Artikelobjekte zurück
    }

    @Override
    public void saveArticle(Article article) { // fügt einen neuen Artikel hinzu oder ersetzt einen vorhandenen
                                               // Artikel

        List<Article> articles = readAllArticles(); // lädt die bisher gespeicherten Artikel

        if (articles == null) { // prüft, ob das Laden fehlgeschlagen ist
            IO.println("Vorhandene Artikel konnten nicht geladen werden"); // verhindert das Überschreiben bei einem
            // Lesefehler
        }

        int previousId = article.getId(); // merkt sich die ursprüngliche ID

        if (previousId == -1) { // erkennt einen neuen Artikel

            int maxId = 0; // beginnt ohne bereits vergebene Nummer

            for (Article existingArticle : articles) { // geht die gespeicherten Artikel durch

                if (existingArticle.getId() > maxId) { // erkennt eine größere ID
                    maxId = existingArticle.getId(); // merkt sich die bisher größte ID
                }
            }

            if (maxId >= Integer.MAX_VALUE - 1) { // verhindert einen Überlauf der Nummerierung
                IO.println("Keine weitere Artikel-ID verfügbar"); // meldet das Ende des verfügbaren Zahlenbereichs
            }

            article.setId(maxId + 1); // vergibt die nächste freie ID
            articles.add(article); // ergänzt den neuen Artikel

        } else if (previousId > 0) { // erkennt einen bereits gespeicherten Artikel

            boolean found = false; // merkt sich, ob die ID gefunden wurde

            for (int index = 0; index < articles.size(); index++) { // durchsucht die gespeicherten Artikel

                if (articles.get(index).getId() == previousId) { // findet den Artikel mit derselben ID

                    articles.set(index, article); // ersetzt den bisherigen Artikel durch die geänderte Version
                    found = true; // merkt sich die erfolgreiche Suche
                    break; // beendet die Suche
                }
            }

            if (!found) { // erkennt eine id ohne gespeicherten Artikel
                IO.println("Artikel mit dieser ID wurde nicht gefunden"); // meldet die fehlende Zuordnung
            }

        } else { // erkennt eine ID, die weder neu noch gültig ist
            IO.println("Ungültige Artikel ID"); // meldet eine ungültige Nummer
        }

        List<String[]> rows = new ArrayList<>(); // sammelt die Datensätze für die Datei

        for (Article existingArticle : articles) { // geht die vollständige Artikelliste durch
            rows.add(existingArticle.getObjectData()); // übernimmt die Daten jedes Artikels
        }

        String error = FileIO.writeAllToFile(FILE_NAME, rows); // speichert die vollständige aktualisierte Liste

        if (error != null && previousId == -1) { // erkennt eine fehlgeschlagene Speicherung eines neuen Artikels
            article.setId(-1); // kennzeichnet den Artikel wieder als noch nicht gespeichert
        }
    }
}