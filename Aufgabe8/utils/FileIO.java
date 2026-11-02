package Aufgabe8.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * FileIO wird verwendet um Daten bzw. Datensätze in einer Datei zu speichern
 */

public class FileIO {

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
    public static String writeIntoFile(String fileName, String data) {
		
		try (FileWriter fileWriter = new FileWriter(fileName, true)){
					
			fileWriter.write(data + NL);
			return SUCCESS_MSG;
			
		} catch (IOException e) {			
			return e.getLocalizedMessage();
		}
		
	}

}