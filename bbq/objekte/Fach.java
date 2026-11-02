package bbq.objekte;

import java.util.ArrayList;

public class Fach extends BBQObject {

    private String fach_name; // Speichert den Namen des Faches.

    private ArrayList<Spezialisierung> spezialisierungen = // Deklariert eine Liste, die Spezialisierung-Objekte aufnehmen kann.
            new ArrayList<>(); // Erzeugt sofort eine leere Liste; das Feld ist dadurch nicht null.

    public Fach(int id, String bezeichnung) { // Konstruktor mit ID und Fachbezeichnung.
        super(id); // Übergibt die ID an den Konstruktor der Elternklasse.
        this.fach_name = bezeichnung; // Speichert die Bezeichnung als Fachnamen.
    }

    @Override // Implementiert die abstrakte Methode aus BBQObject.
    protected String getFileName() { // Liefert den Dateinamen für Fächer.
        return "faecher.csv"; // Gibt den Namen der Datei zurück.
    }

    @Override // Implementiert die abstrakte Methode getData.
    protected String getData() { // Erstellt die zu speichernde Datenzeile.
        return id + ";" + fach_name; // Verbindet ID und Fachnamen mit einem Semikolon.
    }

    public String addSpezialisierung(Spezialisierung sp) { // Fügt eine Spezialisierung hinzu und gibt bei Problemen einen Meldungstext zurück.
        if (sp == null) { // Prüft, ob statt eines Objekts null übergeben wurde.
            return "Die Spezialisierung darf nicht null sein"; // Beendet die Methode sofort mit einer Fehlermeldung.
        }

        if (spezialisierungen.contains(sp)) { // Prüft, ob die Liste diese Spezialisierung bereits enthält.
            return "Das Objekt ist bereits in der Liste"; // Beendet die Methode, ohne das Objekt erneut hinzuzufügen.
        }

        sp.setFach(this); // Ordnet der Spezialisierung dieses Fach-Objekt zu.
        spezialisierungen.add(sp); // Speichert die Referenz auf die Spezialisierung in der Liste.

        return null; // Bedeutet hier: Das Hinzufügen war erfolgreich, es gibt keine Fehlermeldung.
    }

    public String getFach_Name() { // Getter für den Fachnamen.
        return fach_name; // Gibt den gespeicherten Fachnamen zurück.
    }

    public void setFach_Name(String fach_name) { // Setter für den Fachnamen.
        this.fach_name = fach_name; // Speichert den neuen Fachnamen.
    }

    public ArrayList<Spezialisierung> getSpezialisierungen() { // Getter für die Liste der Spezialisierungen.
        return spezialisierungen; // Gibt die tatsächliche Liste zurück, keine Kopie.
    }

    @Override // Überschreibt die Methode toString aus Object.
    public String toString() { // Erstellt eine lesbare Darstellung des Faches.
        return "Fach " + fach_name // Beginnt den String mit dem Fachnamen.
                + " id " + id // Ergänzt die ID.
                + " Spezialisierungen: " + spezialisierungen; // Ergänzt die Liste; dabei werden die Elemente über toString dargestellt.
    }
}