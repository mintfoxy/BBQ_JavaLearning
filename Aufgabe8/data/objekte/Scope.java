package Aufgabe8.data.objekte;

public class Scope extends Menu { // Ein Menüeintrag übernimmt die gemeinsamen Eigenschaften von Menu

    private String beschreibung; // speichert die Beschreibung des Eintrags
    private Key kategorie; // verbindet den Eintrag mit einem Kategorieobjekt
    private static int nextId = 1; // nächste ID für Menüeinträge

    /**
     * Konstruktor für einen Eintrag
     *
     * @param kategorie    zugewiesene Kategorie
     * @param beschreibung Beschreibung
     */
    public Scope(Key kategorie, String beschreibung) { // Erstellt einen neuen Eintrag mit automatischer ID
        this(nextId, kategorie, beschreibung); // Ruft den zweiten Konstruktor mit der nächsten ID auf
    }

    public Scope(int id, Key kategorie, String beschreibung) { // Erstellt einen Eintrag mit seiner gespeicherten ID
        super(id); // Übergibt die ID an Menu
        this.kategorie = kategorie; // Speichert die Verbindung zur Kategorie
        this.beschreibung = beschreibung; // Speichert die Beschreibung

        if (id >= nextId) { // Prüft, ob die übernommene ID den bisherigen Zähler erreicht oder übersteigt
            nextId = id + 1; // Reserviert die nächste Nummer für einen neuen Eintrag
        }
    }

    public Key getKategorie() { // Ermöglicht den Zugriff auf das Kategorieobjekt
        return kategorie; // Gibt das verbundene Objekt zurück
    }

    public void setKategorie(Key kategorie) { // Ermöglicht die Zuweisung einer anderen Kategorie
        this.kategorie = kategorie; // Speichert die neue Verbindung
    }

    public String getBeschreibung() { // Ermöglicht das Lesen der Beschreibung
        return beschreibung; // Gibt die Beschreibung zurück
    }

    public void setBeschreibung(String beschreibung) { // Ermöglicht das Ändern der Beschreibung
        this.beschreibung = beschreibung; // Speichert die neue Beschreibung
    }

    /**
     * (non-Javadoc)
     *
     * erstellt den Pfad zu der Datei mit dem festgelegten Format
     *
     * @see Aufgabe8.data.objekte.Menu#getFileName()
     */
    @Override // Erfüllt die Vorgabe aus Menu
    protected String getFileName() { // Bestimmt die Datei für vollständige Menüeinträge
        return MENU_FILE; // Verwendet ./data/menu.csv
    }

    /**
     * (non-Javadoc)
     *
     * verbindet alle Felder mit dem festgelegten Trennzeichen,
     * fürs Speichern in einer Datei
     *
     * @see Aufgabe8.data.objekte.Menu#getData()
     */
    @Override // Erfüllt die Vorgabe aus Menu
    protected String getData() { // Erstellt eine vollständige CSV-Zeile
        return id + SEPARATOR + kategorie.getData() + SEPARATOR + beschreibung; // Verbindet Eintrags-ID, Kategorie-ID,
                                                                                // Kategorienamen und Beschreibung
    }

    @Override // Ersetzt die Standarddarstellung eines Objekts
    public String toString() { // Erstellt einen lesbaren Text für die Konsole
        return id + ": " + kategorie.getKategorie() + " - " + beschreibung; // Gibt zum Beispiel „1: Vorspeise -
                                                                            // Tomatensuppe“ zurück
    }
}