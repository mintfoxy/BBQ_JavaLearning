package Aufgabe8.objekte;

import Aufgabe8.utils.FileIO;

public abstract class Menu {

    protected int id; // speichert die id; Unterklassen können direkt auf dieses Feld zugreifen

    protected static final String SEPARATOR = ";"; // dieses Zeichen wird als Trennzeichen verwendet

    protected static final String FOLDER = "./data/"; // in diesem Verzeichnis wird die Datei gespeichert

    protected static final String FILE_EXTENSION = ".csv"; // Dateiformat

    protected abstract String getFileName(); // liefert den Namen der Zieldatei

    protected abstract String getData(); // liefert die Daten eines Eintrags im csv-Format (s. FILE_EXTENSION)

    /**
     * Methode zum Speichern von Daten
     */
    public void save() {
        String msg = FileIO.writeIntoFile(getFileName(), getData());
        IO.print(msg);
    }

    /**
     * Konstruktor für ID für alle Unterklassen
     */
    public Menu(int id) {
        this.id = id; // speichert den übergebenen Parameter id im Feld id dieses Objekts
    }

    /**
     * Getter ermöglicht das Auslesen der id in der Unterklasse
     * 
     * @return gibt die id dieses Objektes zurück
     */
    public int getId() {
        return id;
    }

    /**
     * Setter ermöglicht das Ändern der id in der Unterklasse
     * 
     * @param id weist dem Feld id den neuen Parameterwert zu
     */
    public void setId(int id) {
        this.id = id;
    }
}