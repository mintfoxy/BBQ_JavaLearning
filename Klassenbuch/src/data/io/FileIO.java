package data.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Die Klasse stellt einfache Mitteln (Methoden)<br>
 * zu Verfügung, um Daten in einen File zu schreiben/lesen
 */
public class FileIO {

	public static final String NL = "\n";
	public static final String DELIMETER = ";";
	public static final String FOLDER = "./data/";
	public static final String NOFOLDER = "Das Verzeichnis zu erstellen war nicht möglich";

	/**
	 * Liest alles aus einem File
	 * 
	 * @param fileName
	 * @return Liste der eingelesenen Zeilen. Jede Zeile ist ein String.<br>
	 *         Leere Liste, wenn der File keine Zeilen hat.<br>
	 *         null, wenn beim Einlesen ein Fehler aufgetreten ist.<br>
	 */
	public static List<String> readFromFile(String fileName) {

		File file = new File(toPath(fileName));
		List<String> zeilen = new ArrayList<String>();

		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				zeilen.add(scan.nextLine());
			}

		} catch (FileNotFoundException e) {

			return null;
		}

		return zeilen;

	}

	/**
	 * Schreibt auf die einfachste Weise in einen File.
	 * 
	 * @param fileName Pfad der Datei
	 * @param strings  Daten, die gespeichert werden
	 * @return Fehlermeldung, wenn das Speichern nicht gelingt, <br>
	 *         sonst null
	 */
	public static String writeIntoFile(String fileName, String[] data) {

		if (!checkFolder())
			return NOFOLDER;

		try (FileWriter fileWriter = new FileWriter(toPath(fileName), true)) {

			String sprepared = prepareData(data);
			fileWriter.write(NL + sprepared);

		} catch (IOException e) {
			return e.getLocalizedMessage();
		}
		return null;
	}

	/**
	 * Erstellt aus den Daten einen String
	 * 
	 * @param data als Array String[]
	 * @return data als String
	 */
	private static String prepareData(String[] data) {
		String str = "";
		int i = 0;
		for (i = 0; i < data.length - 1; i++) {
			str += (data[i] + DELIMETER);
		}
		str += data[i];
		return str;
	}

	/**
	 * Überprüft, ob das Verzeichenis für Daten existiert.<br>
	 * Wenn nicht, erstellt das.
	 * 
	 * @return in das Verzeichnis kann gespeichert werden
	 */
	private static boolean checkFolder() {
		File f = new File(FOLDER);
		if (!f.exists()) {
			Path path = Paths.get(FOLDER);
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Hilfsmethode zum erstelen eines vollständigen Datei-Pfads
	 * 
	 * @param fileName
	 * @return Verzeichnis/Filename
	 */
	private static String toPath(String fileName) {
		return fileName = FOLDER + fileName;
	}

	/**
	 * Zählt die Anzahl der Zeilen in einem File
	 * 
	 * @param fileName
	 * @return Anzahl der Zeilen, 0 auch, wenn Fehler beim lesen aus dem File
	 */
	public static int getLineCount(String fileName) {

		int count = 0;
		try (Scanner scanner = new Scanner(new File(toPath(fileName)))) {
			while (scanner.hasNextLine()) {
				scanner.next();
				count++;
			}
		} catch (FileNotFoundException e) {
		}
		return count;
	}
}