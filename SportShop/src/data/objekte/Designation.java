package data.objekte;

public class Designation { // beschreibt die Bezeichnung eines Artikels

    private String name; // speichert die Artikelbezeichnung

    public Designation(String name) { // erstellt ein Bezeichnungsobjekt
        this.name = name; // übernimmt die übergebene Bezeichnung
    }

    public String getName() { // ermöglicht das Lesen der Bezeichnung
        return name; // gibt die Bezeichnung zurück
    }

    public void setName(String name) { // ermöglicht das Ändern der Bezeichnung
        this.name = name; // speichert die neue Bezeichnung
    }

    @Override // ersetzt die Standarddarstellung eines Objekts
    public String toString() { // liefert einen lesbaren Text für die Konsole
        return name; // gibt die Artikelbezeichnung zurück
    }
}
