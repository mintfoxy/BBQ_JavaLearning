package data.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * diese Klasse dient zum Schreiben und Lesen von Dateien
 */

public class FileIO {

    public static final String NL = "\n"; // Zeichen für eine neue Zeile
    public static final String DELIMITER = ";"; // Trennzeichen zwischen den Feldern
    public static final String FOLDER = "./data/"; // Ordner für die gespeicherten Dateien

    public static List<String> readFromFile(String fileName) { // liest alle Zeilen einer Datei

        File file = new File(toPath(fileName)); // erstellt ein File Objekt für den vollständigen Dateipfad,
                                                // in dem die Datei sich befindet

        List<String> lines = new ArrayList<>(); // speichert die gelesenen Zeilen in einer ArrayList

        if (!file.exists()) { // prüft, ob die Datei noch fehlt
            return lines; // gibt beim ersten Programmstart eine leere Liste zurück
        }

        try (Scanner scanner = new Scanner(file)) { // öffnet die Datei und schließt sie danach automatisch

            while (scanner.hasNextLine()) { // prüft, ob eine weitere Zeile vorhanden ist
                lines.add(scanner.nextLine()); // liest die nächste Zeile und fügt sie zur Liste hinzu
            }

        } catch (FileNotFoundException exception) { // behandelt einen Fehler beim Öffnen
            return null; // meldet dem aufrufenden Code einen Lesefehler
        }

        return lines; // gibt die gelesenen Textzeilen zurück dem CSVDataManager zurück,
                    // der sie in Article Objekte umwandelt
    }

    /**
     * 
     * Diese Methode erhält den Dateinamen und ein Array mit Daten eines Datensatzes,
     * verbindet die Felder in eine csv Zeile,
     * und fügt die Zeile am Dateinende hinzu
     * @param fileName
     * @param data
     * @return
     */
    public static String writeIntoFile(String fileName, String[] data) { // schreibt einen Datensatz in eine Datei

        if (!checkFolder()) { // prüft den Datenordner und erstellt ihn bei Bedarf
            return "Datenordner konnte nicht erstellt werden"; // meldet einen Fehler beim Anlegen
                                                            // die Methode wird nicht weiterausgeführt,
                                                            // falls der Ordner weder existiert,
                                                            // noch erstellt werden kann
        }

        try (FileWriter writer = new FileWriter(toPath(fileName), true)) { // öffnet die Datei
                                    // verwandelt articles.csv in ./data/articles.csv
                                    // erstellt ein Objekt zum Speichern und versucht die Datei zu öffnen
                                    // true bedeutet hier, dass der Datensatz am Dateiende hinzugefügt wird

            String preparedData = prepareData(data); // verbindet die Felder zu einer csv Zeile
            writer.write(preparedData + NL); // schreibt die Daten und danach einen Zeilenumbruch

        } catch (IOException exception) { // behandelt einen Fehler beim Schreiben oder Schließen
            return exception.getLocalizedMessage(); // gibt die Fehlerbeschreibung zurück
        }

        return null; // bedeutet, dass das Speichern erfolgreich war
    }

    /**
     * diese Methode verbindet die Array Felder zu einem Text
     * @param data
     * @return
     */

    private static String prepareData(String[] data) {

        String line = ""; // beginnt mit einem leeren Text

        for (int index = 0; index < data.length; index++) { // geht alle Felder durch

            if (index > 0) { // erkennt die Felder nach dem ersten Feld
                line += DELIMITER; // ergänzt ein Trennzeichen
            }

            line += data[index]; // ergänzt den Inhalt des aktuellen Feldes
        }

        return line; // gibt die fertige csv Zeile zurück
    }

    private static boolean checkFolder() { // prüft den Ordner für die Dateien

        File folder = new File(FOLDER); // stellt den Datenordner dar

        if (!folder.exists()) { // prüft, ob der Ordner fehlt
            return folder.mkdirs(); // erstellt den Ordner und gibt das Ergebnis zurück
        }

        return folder.isDirectory(); // prüft, ob der vorhandene Pfad ein Ordner ist
    }

    private static String toPath(String fileName) { // ergänzt den Ordner vor dem Dateinamen
        return FOLDER + fileName; // ergibt zum Beispiel ./data/articles.csv
    }

    public static String writeAllToFile(String fileName, List<String[]> rows) { // schreibt alle Datensätze neu in die
                                                                                // Datei

        if (!checkFolder()) { // prüft den Datenordner und erstellt ihn bei Bedarf
            return "Datenordner konnte nicht erstellt werden"; // meldet einen Fehler beim Anlegen
        }

        try (FileWriter writer = new FileWriter(toPath(fileName), false)) { // ersetzt den bisherigen Dateiinhalt

            for (String[] row : rows) { // geht alle Datensätze durch
                writer.write(prepareData(row) + NL); // schreibt den Datensatz und einen Zeilenumbruch
            }

        } catch (IOException exception) { // behandelt Fehler beim Schreiben oder Schließen
            return exception.getLocalizedMessage(); // gibt die Fehlerbeschreibung zurück
        }

        return null; // kennzeichnet eine erfolgreiche Speicherung
    }
}