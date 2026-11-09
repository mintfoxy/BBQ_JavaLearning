package Aufgabe8.data.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * FileIO wird verwendet um Daten bzw. Datensätze in einer Datei zu speichern
 */
public class FileIO { // Enthält Methoden für den Zugriff auf Textdateien

  private static final String NL = "\n"; // Konstante für eine neue Zeile;
  // static: gehört zur Klasse, es muss kein neues Objekt erzeugt werden
  // final: der Wert kann nicht geändert werden
  private static final String SUCCESS_MSG = "gespeichert"; // konstante für eine erfolgreiche Speicherung

  /**
   * diese Methode fügt den Text der Datei am Ende der Datei hinzu
   *
   * @param fileName ist Pfad zur Datei
   * @param data     der Text, der hinzugefügt wird
   * @return "gespeichert" als Rückgabe bei Erfolg oder Beschreibung des Fehlers
   *
   *         Java schließt nach try die Ressource (hier: FileWriter) automatisch
   */
  public static String writeIntoFile(String fileName, String data) { // Fügt einen Datensatz zur Datei hinzu

    File file = new File(fileName); // Stellt die Zieldatei dar
    File folder = file.getParentFile(); // Ermittelt den Ordner der Zieldatei

    if (folder != null && !folder.exists() && !folder.mkdirs()) { // Versucht einen fehlenden Ordner anzulegen
      return "Ordner konnte nicht erstellt werden"; // Meldet einen Fehler beim Anlegen
    }

    try (FileWriter fileWriter = new FileWriter(file, true)) { // Öffnet die Datei zum Anhängen und schließt sie
                                                               // automatisch

      fileWriter.write(data + NL); // Schreibt den Datensatz mit einem Zeilenumbruch
      return SUCCESS_MSG; // Meldet die erfolgreiche Speicherung

    } catch (IOException e) { // Fängt Fehler beim Schreiben oder Schließen ab
      return e.getLocalizedMessage(); // Gibt die Fehlerbeschreibung zurück
    }
  }

  public static List<String> readFromFile(String fileName) { // Liest alle Zeilen einer Datei

    List<String> data = new ArrayList<>(); // Speichert die gelesenen Textzeilen
    File file = new File(fileName); // Stellt die Datei dar

    if (!file.exists()) { // Prüft, ob die Datei noch fehlt
      return data; // Gibt eine leere Liste zurück
    }

    try (Scanner scanner = new Scanner(file)) { // Öffnet die Datei und schließt sie danach automatisch

      while (scanner.hasNextLine()) { // Prüft, ob eine weitere Zeile vorhanden ist
        String zeile = scanner.nextLine(); // Liest die nächste Zeile
        data.add(zeile); // Fügt die Zeile zur Liste hinzu
      }

    } catch (FileNotFoundException e) { // Behandelt einen Fehler beim Öffnen der Datei
      IO.println("Datei konnte nicht geöffnet werden."); // Zeigt eine Fehlermeldung
    }

    return data; // Gibt die Liste mit den gelesenen Zeilen zurück
  }

  /**
   * Zählt die Anzahl der Zeilen in einem File
   *
   * @param fileName
   * @return Anzahl der Zeilen
   */
  public static int getLineCount(String fileName) { // Kann weiterhin zum Zählen verwendet werden
    return readFromFile(fileName).size(); // Gibt die Anzahl der gelesenen Zeilen zurück
  }

  /**
   * liest eine Datei
   * 
   * @param fileName
   */
  public static void showFile(String fileName) { // Zeigt bei Bedarf die unveränderten CSV-Zeilen an
    for (String zeile : readFromFile(fileName)) { // Geht alle gelesenen Textzeilen durch
      IO.println(zeile); // Gibt die aktuelle Textzeile aus
    }
  }
}