package bbq.objekte;

import java.util.ArrayList;

public class Gruppe extends BBQObject {

    private String gruppe_name; // Speichert den Namen der Gruppe.

    private ArrayList<Azubi> teilnehmer = new ArrayList<>(); // Erzeugt eine leere Liste für die Azubis dieser Gruppe.

    public Gruppe(int id, String gruppe_name) { // Konstruktor mit ID und Gruppenname.
        super(id); // Lässt die Elternklasse die ID speichern.
        this.gruppe_name = gruppe_name; // Speichert den Gruppennamen.
    }

    @Override // Implementiert die abstrakte Methode getFileName.
    protected String getFileName() { // Liefert den Dateinamen für Gruppen.
        return "gruppen.csv"; // Gibt den Dateinamen zurück.
    }

    @Override // Implementiert die abstrakte Methode getData.
    protected String getData() { // Erstellt die Datenzeile dieser Gruppe.
        return id + ";" + gruppe_name; // Verbindet ID und Gruppenname durch ein Semikolon.
    }

    public String addTeilnehmer(Azubi tn) { // Fügt einen Azubi zur Gruppe hinzu.
        if (tn == null) { // Prüft, ob eine gültige Objektreferenz übergeben wurde.
            return "Der Teilnehmer darf nicht null sein"; // Gibt bei null eine Fehlermeldung zurück und beendet die Methode.
        }

        if (teilnehmer.contains(tn)) { // Prüft, ob dieser Teilnehmer bereits in der Liste steht.
            return "Das Objekt ist bereits in der Liste"; // Verhindert, dass derselbe Teilnehmer erneut hinzugefügt wird.
        }

        tn.setGruppe(this); // Speichert dieses Gruppe-Objekt im Feld gruppe des Azubis.
        teilnehmer.add(tn); // Fügt den Azubi zur Teilnehmerliste dieser Gruppe hinzu.

        return null; // Signalisiert in diesem Programm: erfolgreich, keine Fehlermeldung.
    }

    public String getGruppe_name() { // Getter für den Gruppennamen.
        return gruppe_name; // Gibt den Gruppennamen zurück.
    }

    public void setGruppe_Name(String gruppe_name) { // Setter für den Gruppennamen.
        this.gruppe_name = gruppe_name; // Speichert den neuen Gruppennamen.
    }

    public ArrayList<Azubi> getTeilnehmer() { // Getter für die Teilnehmerliste.
        return teilnehmer; // Gibt die tatsächliche Liste der Teilnehmer zurück.
    }

    @Override // Überschreibt die Methode toString aus Object.
    public String toString() { // Erstellt eine lesbare Darstellung der Gruppe.
        return "Gruppe " + gruppe_name + " id " + id; // Gibt Gruppenname und ID als String zurück.
    }
}