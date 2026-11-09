package Aufgabe8.data.objekte;

import Aufgabe8.data.io.FileIO;

public abstract class Menu { // gemeinsame Mutterklasse für Kategorien und Einträge

    protected int id; // speichert die id; Unterklassen können direkt auf dieses Feld zugreifen

    public static final String SEPARATOR = ";"; // dieses Zeichen wird als Trennzeichen verwendet

    protected static final String FOLDER = "./data/"; // in diesem Verzeichnis wird die Datei gespeichert

    protected static final String FILE_EXTENSION = ".csv"; // Dateiformat

    public static final String MENU_FILE = FOLDER + "menu" + FILE_EXTENSION; // Gemeinsamer Pfad zur Menüdatei

    protected abstract String getFileName(); // liefert den Namen der Zieldatei

    protected abstract String getData(); // liefert die Daten eines Eintrags im csv-Format (s. FILE_EXTENSION)

    /**
     * Methode zum Speichern von Daten
     */
    public void save() { // speichert die Daten dieses Objekts
        String msg = FileIO.writeIntoFile(getFileName(), getData()); // übergibt Dateipfad und CSV-Zeile
        IO.println(msg); // zeigt das Ergebnis der Speicherung
    }

    /**
     * Konstruktor für ID für alle Unterklassen
     */
    public Menu(int id) { // Üübernimmt die ID aus der Unterklasse
        this.id = id; // speichert den übergebenen Parameter id im Feld id dieses Objekts
    }

    /**
     * Getter ermöglicht das Auslesen der id in der Unterklasse
     *
     * @return gibt die id dieses Objektes zurück
     */
    public int getId() { // ermöglicht den Zugriff auf die ID
        return id; // gibt die gespeicherte ID zurück
    }

    /**
     * Setter ermöglicht das Ändern der id in der Unterklasse
     *
     * @param id weist dem Feld id den neuen Parameterwert zu
     */
    public void setId(int id) { // wird für die automatische Nummerierung nicht benötigt
        this.id = id; // speichert den übergebenen Parameter id im Feld id dieses Objekts
    }
}