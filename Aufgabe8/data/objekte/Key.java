package Aufgabe8.data.objekte;

public class Key extends Menu { // eine Kategorie übernimmt die gemeinsamen Eigenschaften von Menu

    private String kategorie; // speichert den Namen der Kategorie

    private static int nextId = 1; // nächste id für Kategorien

    public Key(String kategorie) { // erstellt eine neue Kategorie mit automatischer id
        this(nextId, kategorie); // ruft den zweiten Konstruktor mit der nächsten id auf
    }

    public Key(int id, String kategorie) { // erstellt eine Kategorie mit einer gespeicherten id
        super(id); // übergibt die id an Menu
        this.kategorie = kategorie; // speichert den Namen der Kategorie

        if (id >= nextId) { // prüft, ob die übernommene ID den bisherigen Zähler erreicht oder übersteigt
            nextId = id + 1; // reserviert die nächste Nummer für eine neue Kategorie
        }
    }

    /**
     * (non-Javadoc)
     * Zieldatei für das SPeichern einer Kategorie
     *
     * @see Aufgabe8.data.objekte.Menu#getFileName()
     */
    @Override // erfüllt die Vorgabe aus Menu
    protected String getFileName() { // wird nur beim direkten Speichern einer Kategorie verwendet
        return FOLDER + "key" + FILE_EXTENSION; // gibt den Pfad zur Kategoriedatei zurück
    }

    /**
     * (non-Javadoc)
     * verbindet id und Namen einer Kategorie
     *
     * @see Aufgabe8.data.objekte.Menu#getData()
     */
    @Override // erfüllt die Vorgabe aus Menu
    protected String getData() { // erstellt den CSV-Teil der Kategorie
        return id + SEPARATOR + kategorie; // verbindet ID und Kategorienamen
    }

    public String getKategorie() { // ermöglicht das Lesen des Kategorienamens
        return kategorie; // gibt den Namen zurück
    }

    public void setKategorie(String kategorie) { // ermöglicht das Ändern des Kategorienamens
        this.kategorie = kategorie; // speichert den neuen Namen
    }

    @Override // ersetzt die Standarddarstellung eines Objekts
    public String toString() { // erstellt einen lesbaren Text für die Konsole
        return id + ": " + kategorie; // gibt zum Beispiel „1: Vorspeise“ zurück
    }
}