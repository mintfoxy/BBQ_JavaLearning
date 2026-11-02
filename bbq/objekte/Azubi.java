package bbq.objekte;

public class Azubi extends BBQObject {

    private String name; // Speichert den Nachnamen; direkter Zugriff ist nur innerhalb dieser Klasse möglich.
    private String vorname; // Speichert den Vornamen.
    private Spezialisierung spezialisierung; // Verweist auf die Spezialisierung des Azubis; anfangs null.
    private Gruppe gruppe; // Verweist auf die Gruppe des Azubis; anfangs null.

    public Azubi(int id, String name, String vorname) { // Konstruktor zum Erzeugen eines Azubi-Objekts.
        super(id); // Ruft den Konstruktor von BBQObject auf, der die ID speichert.
        this.name = name; // Speichert den übergebenen Nachnamen.
        this.vorname = vorname; // Speichert den übergebenen Vornamen.
    }

    @Override // Zeigt an, dass die folgende Methode eine Methode der Elternklasse implementiert.
    protected String getFileName() { // Liefert den Dateinamen für Azubi-Objekte.
        return "azubis.csv"; // Gibt den Dateinamen als String zurück.
    }

    @Override // Implementiert die abstrakte Methode getData aus BBQObject.
    protected String getData() { // Erstellt eine Textzeile mit den Daten dieses Azubis.
        String spezialisierungId = spezialisierung == null // Prüft, ob dem Azubi noch keine Spezialisierung zugeordnet ist.
                ? "" // Falls die Bedingung wahr ist: Verwendet einen leeren String.
                : String.valueOf(spezialisierung.getId()); // Andernfalls: Liest die ID aus und wandelt sie in einen String um.

        String gruppeId = gruppe == null // Prüft, ob dem Azubi noch keine Gruppe zugeordnet ist.
                ? "" // Ohne Gruppe bleibt das entsprechende Feld leer.
                : String.valueOf(gruppe.getId()); // Bei vorhandener Gruppe wird deren ID als String verwendet.

        return id + ";" + name + ";" + vorname // Beginnt die Datenzeile mit ID, Nachname und Vorname.
                + ";" + spezialisierungId + ";" + gruppeId; // Ergänzt die IDs der Spezialisierung und der Gruppe.
    }

    public String getName() { // Getter für den Nachnamen.
        return name; // Gibt den gespeicherten Nachnamen zurück.
    }

    public void setName(String name) { // Setter für den Nachnamen.
        this.name = name; // Ersetzt den bisherigen Nachnamen durch den neuen Wert.
    }

    public String getVorname() { // Getter für den Vornamen.
        return vorname; // Gibt den gespeicherten Vornamen zurück.
    } // Ende der Methode getVorname.

    public void setVorname(String vorname) { // Setter für den Vornamen.
        this.vorname = vorname; // Speichert den neuen Vornamen.
    }

    public Spezialisierung getSpezialisierung() { // Getter für die zugeordnete Spezialisierung.
        return spezialisierung; // Gibt die Objektreferenz zurück; diese kann auch null sein.
    }

    public void setSpezialisierung(Spezialisierung spezialisierung) { // Setter für die Spezialisierung.
        this.spezialisierung = spezialisierung; // Speichert die Referenz auf das übergebene Objekt.
    }

    public Gruppe getGruppe() { // Getter für die zugeordnete Gruppe.
        return gruppe; // Gibt die Gruppenreferenz zurück; diese kann auch null sein.
    }

    public void setGruppe(Gruppe gruppe) { // Setter für die Gruppe.
        this.gruppe = gruppe; // Speichert die Referenz auf die übergebene Gruppe.
    }

    @Override // Überschreibt die geerbte Methode toString aus der Klasse Object.
    public String toString() { // Erstellt eine lesbare Textdarstellung dieses Azubis.
        return name + " " + vorname + " id " + id; // Gibt Nachname, Vorname und ID als gemeinsamen String zurück.
    }
}