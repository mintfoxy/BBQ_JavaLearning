package Aufgabe8.objekte;

public class Key extends Menu {

    private String kategorie;

    private static int nextId = 1; // Nächste ID für Kategorien

    public Key(String kategorie) {
        super(nextId++);
        this.kategorie = kategorie;
    }

    /**
     * (non-Javadoc)
     * Zieldatei für das SPeichern einer Kategorie
     * 
     * @see Aufgabe8.objekte.Menu#getFileName()
     */
    @Override
    protected String getFileName() {
        return FOLDER + "key" + FILE_EXTENSION;
    }

    /**
     * (non-Javadoc)
     * verbindet id und Namen einer Kategorie
     * 
     * @see Aufgabe8.objekte.Menu#getData()
     */
    @Override
    protected String getData() {
        return id + SEPARATOR + kategorie;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
}