package data.dataHandling;

import java.util.ArrayList;
import java.util.List;

public class CSVDataManager implements DataManager {

    	/**
	 * Lädt alle gespeicherten Aufgaben.<br>
	 * Jede gelesene Zeile ist ein String - ein Eintrag, d.h. eine Aufgabe.<br>
	 * Die String-Zeilen werden gesplitet zu String[], aus dem<br>
	 * anschließend eine Instanz der Klasse Aufgabe erzeugt un zur<br>
	 * Liste hinzugefügt wird.
	 * 
	 * @return Liste der Aufgabe-Instanzen
	 */
    @Override 
	public ArrayList<Aufgabe> leseAlleAufgaben() {
		ArrayList<String> data = FileIO.readFromFile(AObject.KLASSENBUCH);

		if (data == null)
			return null;
		List<Aufgabe> aufgaben = new ArrayList<Aufgabe>();
		String[] werte;

		for (String str : data) {
			if (str.isEmpty())
				continue;
			werte = str.split(FileIO.DELIMETER);
			aufgaben.add(AObjectFactory.aufgabeFromData(werte));
		}

		return data;
	}

}
