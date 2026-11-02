package Aufgabe8.objekte;

public class Scope extends Menu {

    private String beschreibung;
    private Key kategorie;

    private static int nextId = 1; // nächste id für Menüeinträge

    /**
     * Konstruktor für einen Eintrag
     *
     * @param kategorie    zugewiesene Kategorie
     * @param beschreibung Beschreibung
     */
    public Scope(Key kategorie, String beschreibung) {
        super(nextId++);
        this.kategorie = kategorie;
        this.beschreibung = beschreibung;
    }

    public Key getKategorie() {
        return kategorie;
    }

    public void setKategorie(Key kategorie) {
        this.kategorie = kategorie;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * (non-Javadoc)
     *
     * erstellt den Pfad zu der Datei mit dem festgelegten Format
     *
     * @see Aufgabe8.objekte.Menu#getFileName()
     */
    @Override
    protected String getFileName() {
        return FOLDER + "menu" + FILE_EXTENSION;
    }

    /**
     * (non-Javadoc)
     *
     * verbindet alle Felder mit dem festgelegten Trennzeichen,
     * fürs Speichern in einer Datei
     *
     * @see Aufgabe8.objekte.Menu#getData()
     */
    @Override
    protected String getData() {
        return id + SEPARATOR + kategorie.getData()
                + SEPARATOR + beschreibung;
    }
}