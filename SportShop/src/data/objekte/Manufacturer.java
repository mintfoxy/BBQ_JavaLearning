package data.objekte;

public class Manufacturer {

    private int id; // speichert die id des Herstellers
    private String name; // speichert den Namen des Herstellers


    public int getId() {
        return id;
    }

    public void setId(int id) { 
        this.id = id;
    }

    public Manufacturer(String name) { // erstellt ein Herstellerobjekt
        this.name = name; // übernimmt den übergebenen Namen
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { // ermöglicht das Lesen des Namens
        return name; // gibt den Herstellernamen zurück
    }

    public void setName(String name) { // ermöglicht das Ändern des Namens
        this.name = name; // speichert den neuen Namen
    }

    @Override // ersetzt die Standarddarstellung eines Objekts
    public String toString() { // liefert einen lesbaren Text für die Konsole
        return name; // gibt den Herstellernamen zurück
    }
}
