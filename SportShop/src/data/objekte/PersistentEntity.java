package data.objekte;

import data.io.FileIO;

/**
 * Abstrakte Basisklasse für Objekte mir einer id,
 * die in einer csv Datei gespeichert werden können.
 * Die Unterklassen legen den Dateinamen und die zu speichernden Felder fest.
 */

public abstract class PersistentEntity {
    
    public static final String ARTICLES_FILE = "articles.csv"; // Dateiname für alle gespeicherten Artikel

    private int id; // speichert die interne Nummer des Objekts

    protected PersistentEntity(int id) { // übernimmt die Nummer aus der Unterklasse
        this.id = id; // speichert die übergebene Nummer
    }

    public int getId() { // ermöglicht das Lesen der internen Nummer
        return id; // gibt die gespeicherte Nummer zurück
    }

    public void setId(int id) { // ermöglicht die Übernahme einer beim Speichern vergebenen id
    this.id = id; // speichert die neue id
}

    protected abstract String getFileName(); // liefert den Namen der Zieldatei

    public abstract String[] getObjectData(); // liefert die Daten des Objekts als Textarray

    /**
    * Hängt die Daten dieses Objekts als neue Zeile an die Zieldatei an.
    *
    * @return {@code null} bei Erfolg, sonst eine Fehlermeldung
    */

    public String save() {
        return FileIO.writeIntoFile(getFileName(), getObjectData()); // übergibt Dateiname und Daten an FileIO
    }
}