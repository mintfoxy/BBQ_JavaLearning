package bbq.objekte;

import java.util.ArrayList;

public class Spezialisierung extends BBQObject {

    private String bezeichnung; // Speichert die Bezeichnung der Spezialisierung.

    private Fach fach; // Verweist auf ein Fach; mehrere Spezialisierungen können demselben Fach zugeordnet sein.

    private ArrayList<Modul> module = new ArrayList<>(); // Erzeugt eine leere Liste für die zugehörigen Module.
    private ArrayList<Azubi> azubis = new ArrayList<>(); // Erzeugt eine leere Liste für die zugehörigen Azubis.

    public Spezialisierung(int id, String bezeichnung) { // Konstruktor mit ID und Bezeichnung.
        super(id); // Ruft den Elternkonstruktor auf, der die ID speichert.
        this.bezeichnung = bezeichnung; // Speichert die Bezeichnung.
    }

    @Override // Implementiert die abstrakte Methode getFileName.
    protected String getFileName() { // Liefert den Dateinamen für Spezialisierungen.
        return "spezialisierungen.csv"; // Gibt den Dateinamen zurück.
    }

    @Override // Implementiert die abstrakte Methode getData.
    protected String getData() { // Erstellt die Datenzeile dieser Spezialisierung.
        String fachId = fach == null // Prüft, ob noch kein Fach zugeordnet ist.
                ? "" // Ohne Fach bleibt das entsprechende Datenfeld leer.
                : String.valueOf(fach.getId()); // Andernfalls wird die ID des Faches in einen String umgewandelt.

        return id + ";" + bezeichnung + ";" + fachId; // Gibt ID, Bezeichnung und Fach-ID mit Semikolon als Trennzeichen zurück.
    }

    public String addModul(Modul m) { // Fügt dieser Spezialisierung ein Modul hinzu.
        if (m == null) { // Prüft, ob statt eines Modul-Objekts null übergeben wurde.
            return "Das Modul darf nicht null sein"; // Beendet die Methode mit einer Fehlermeldung.
        } // Ende der null-Prüfung.

        if (module.contains(m)) { // Prüft, ob das Modul bereits in der Liste steht.
            return "Das Objekt ist bereits in der Liste"; // Verhindert einen erneuten Eintrag desselben Moduls.
        } // Ende der Prüfung auf einen vorhandenen Eintrag.

        m.setSpezialisierung(this); // Ordnet dem Modul diese Spezialisierung zu.
        module.add(m); // Fügt das Modul zur Liste dieser Spezialisierung hinzu.

        return null; // Bedeutet hier: erfolgreich, keine Fehlermeldung.
    }

    public String addAzubi(Azubi azb) { // Fügt dieser Spezialisierung einen Azubi hinzu.
        if (azb == null) { // Prüft, ob eine gültige Azubi-Referenz übergeben wurde.
            return "Der Azubi darf nicht null sein"; // Beendet die Methode bei null mit einer Fehlermeldung.
        } // Ende der null-Prüfung.

        if (azubis.contains(azb)) { // Prüft, ob der Azubi bereits in der Liste steht.
            return "Das Objekt ist bereits in der Liste"; // Verhindert einen erneuten Eintrag desselben Azubis.
        } // Ende der Prüfung auf einen vorhandenen Eintrag.

        azb.setSpezialisierung(this); // Ordnet dem Azubi diese Spezialisierung zu.
        azubis.add(azb); // Fügt den Azubi zur Liste dieser Spezialisierung hinzu.

        return null; // Bedeutet hier: erfolgreich, keine Fehlermeldung.
    }
    public String getBezeichnung() { // Getter für die Bezeichnung.
        return bezeichnung; // Gibt die gespeicherte Bezeichnung zurück.
    }

    public void setBezeichnung(String bezeichnung) { // Setter für die Bezeichnung.
        this.bezeichnung = bezeichnung; // Speichert die neue Bezeichnung.
    }

    public Fach getFach() { // Getter für das zugeordnete Fach.
        return fach; // Gibt die Referenz auf das Fach zurück; diese kann auch null sein.
    }

    public void setFach(Fach fach) { // Setter für das zugeordnete Fach.
        this.fach = fach; // Speichert die übergebene Fachreferenz.
    }

    public ArrayList<Modul> getModule() { // Getter für die Modulliste.
        return module; // Gibt die tatsächliche Liste der Module zurück.
    }

    public ArrayList<Azubi> getAzubis() { // Getter für die Azubi-Liste.
        return azubis; // Gibt die tatsächliche Liste der Azubis zurück.
    }

    @Override // Überschreibt die Methode toString aus Object.
    public String toString() { // Erstellt eine lesbare Darstellung der Spezialisierung.
        String fachId = fach == null // Prüft, ob ein Fach zugeordnet ist.
                ? "nicht zugeordnet" // Verwendet diesen Text, wenn kein Fach vorhanden ist.
                : String.valueOf(fach.getId()); // Verwendet andernfalls die ID des Faches als String.

        return bezeichnung // Beginnt die Darstellung mit der Bezeichnung.
                + " id " + id // Ergänzt die ID dieser Spezialisierung.
                + " Fach: " + fachId // Ergänzt die Fach-ID oder den Ersatztext.
                + " Module: " + module; // Ergänzt die Module; für jedes Modul wird dessen toString verwendet.
    }
}