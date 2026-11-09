package bbq;

/**
 * Mutterklasse für alle Objekte, die in der Datenbank gespeichert werden.
 */

public class BBQObjekt extends Object {
    
    public int id; // jede Klasse bzw. jede Tabelle in der Datenbank hat id

    public BBQObjekt(int id) {
        this.id = id;   // Konstruktor der Klasse BBQObjekt, der die id des Objekts setzt
    }

    // wir können die Methode toString() überschreiben, ja
    // um eine benutzerfreundliche Darstellung des Objekts zu erhalten

    @Override

    public String toString() {  // Überschreiben der Methode toString() der Mutterklasse Object
        
        String info = super.toString() + "\n"; // Aufruf der toString() Methode der Mutterklasse Object

        info = info +  "Eine Instanz der Klasse BBQObject\n"; // Beschreibung der Klasse BBQObjekt

        info = info + "id: " + id + "\n"; // Beschreibung der id des Objekts

        return info;    // Rückgabe der Beschreibung des Objekts als String

    }

}