package Aufgabe8.data.tests;

import java.util.List;

import Aufgabe8.data.manager.FileDataManager;
import Aufgabe8.data.objekte.Key;
import Aufgabe8.data.objekte.Menu;
import Aufgabe8.data.objekte.Scope;


public class Aufgabe8Test {

    public static void main(String[] args) {

        try { // fängt Fehler beim Laden und Umwandeln der Daten ab

            FileDataManager manager = new FileDataManager(); // erstellt die Dateiverwaltung
            List<Scope> eintraege = manager.ladeAlleEintraege(); // lädt zuerst die Objekte und aktualisiert dabei die
                                                                 // ID-Zähler

            String auswahl = IO.readln("1 - Eintrag eingeben\n2 - Einträge anzeigen\nAuswahl: "); // liest die Auswahl

            if (auswahl.equals("1")) { // verarbeitet die Eingabe eines neuen Eintrags

                Key kategorie = inputKategorie(); // liest eine neue Kategorie ein
                Scope eintrag = inputEintrag(kategorie); // liest die Beschreibung für diese Kategorie ein

                showEintrag(eintrag); // zeigt den neuen Eintrag über toString()
                saveObjekt(eintrag); // speichert alle Daten gemeinsam in menu.csv

            } else if (auswahl.equals("2")) { // verarbeitet die Anzeige gespeicherter Einträge

                showMenu(eintraege); // zeigt die bereits geladenen Objekte an

            } else {
                IO.println("Ungültige Eingabe");
            }

        } catch (IllegalStateException | IllegalArgumentException e) { // fängt Dateifehler und ungültige CSV-Daten ab
            IO.println("Fehler: " + e.getMessage()); // zeigt die Ursache und beendet diesen Programmlauf
        }
    }

    public static Key inputKategorie() { // liest den Kategorienamen über die Tastatur

        String bezeichnung = IO.readln("Kategorie eingeben: "); // speichert den eingegebenen Text

        while (bezeichnung.isBlank() || bezeichnung.contains(";")) { // verhindert leere Kategorien und das Trennzeichen
                                                                     // im Namen
            bezeichnung = IO.readln("Bitte eine Kategorie ohne Semikolon eingeben: "); // fordert eine passende Eingabe
                                                                                       // an
        }

        return createKategorie(bezeichnung); // Erstellt und liefert das Kategorieobjekt
    }

    public static Scope inputEintrag(Key kategorie) { // Liest die Beschreibung für eine Kategorie
        String beschreibung = IO.readln("Beschreibung eingeben: "); // Liest den Text des Eintrags
        return createEintrag(kategorie, beschreibung); // Erstellt und liefert das Eintragsobjekt
    }

    public static Key createKategorie(String bezeichnung) { // Erstellt eine neue Kategorie
        return new Key(bezeichnung); // Verwendet die nächste freie Kategorie-ID
    }

    public static Scope createEintrag(Key kategorie, String beschreibung) { // erstellt einen neuen Menüeintrag
        return new Scope(kategorie, beschreibung); // verbindet die Kategorie mit der Beschreibung
    }

    public static void showKategorie(Key kategorie) { // zeigt ein Kategorieobjekt an
        IO.println(kategorie.toString()); // verwendet die lesbare Darstellung aus Key
    }

    public static void showEintrag(Scope eintrag) { // zeigt ein Eintragsobjekt an
        IO.println(eintrag.toString()); // verwendet die lesbare Darstellung aus Scope
    }

    public static void showMenu(List<Scope> eintraege) { // zeigt die geladenen Objekte an

        if (eintraege.isEmpty()) { // prüft, ob die Liste keine Einträge enthält
            IO.println("Keine Einträge vorhanden"); // meldet eine leere Menüdatei
            return; // beendet die Anzeige ohne weitere Ausgabe
        }

        for (Scope eintrag : eintraege) { // geht die gespeicherten Objekte einzeln durch
            showEintrag(eintrag); // gibt jedes Objekt über seine toString()-Methode aus
        }
    }

    public static void saveObjekt(Menu objekt) { // nimmt ein Objekt einer Menu-Unterklasse entgegen
        objekt.save(); // ruft die gemeinsame Speicherung aus Menu auf
    }
}