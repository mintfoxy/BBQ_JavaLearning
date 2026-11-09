package Aufgabe8.data.manager;

import java.util.ArrayList;
import java.util.List;

import Aufgabe8.data.io.FileIO;
import Aufgabe8.data.objekte.Key;
import Aufgabe8.data.objekte.Menu;
import Aufgabe8.data.objekte.Scope;

public class FileDataManager { // wandelt gespeicherte Textzeilen in Objekte um

    public List<Scope> ladeAlleEintraege() { // lädt die gespeicherten Menüeinträge

        List<String> data = FileIO.readFromFile(Menu.MENU_FILE); // Liest menu.csv zunächst als Liste von Textzeilen
        List<Scope> eintraege = new ArrayList<>(); // Speichert anschließend die erzeugten Scope-Objekte

        for (String zeile : data) { // Verarbeitet jede gelesene Zeile

            if (zeile.isBlank()) { // erkennt leere Zeilen und Zeilen nur mit Leerzeichen
                continue; // überspringt diese Zeile
            }

            String[] werte = zeile.split(Menu.SEPARATOR, 4); // Teilt die Zeile in höchstens vier Felder

            if (werte.length != 4) { // Prüft, ob alle vier benötigten Felder vorhanden sind
                throw new IllegalArgumentException("Ungültige CSV-Zeile: " + zeile); // Meldet eine fehlerhafte Zeile
            }

            int eintragId = Integer.parseInt(werte[0].trim()); // Wandelt das erste Feld in die ID des Eintrags um
            int kategorieId = Integer.parseInt(werte[1].trim()); // Wandelt das zweite Feld in die ID der Kategorie um

            if (eintragId < 1 || kategorieId < 1) { // Prüft, ob beide IDs positiv sind
                throw new IllegalArgumentException("IDs müssen positiv sein: " + zeile); // Verhindert ungültige IDs
            }

            Key kategorie = new Key(kategorieId, werte[2]); // Erstellt ein Kategorieobjekt mit gespeicherter ID und
                                                            // gespeichertem Namen
            Scope eintrag = new Scope(eintragId, kategorie, werte[3]); // Erstellt ein Eintragsobjekt mit gespeicherter
                                                                       // ID, Kategorie und Beschreibung

            eintraege.add(eintrag); // Speichert das fertige Objekt in der Liste
        }

        return eintraege; // Gibt die Liste der Scope-Objekte zurück
    }
}