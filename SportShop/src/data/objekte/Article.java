package data.objekte;

/**
 * 
 * Diese Methode verbindet alle Daten eines Sportartikels.
 * Die id auf der Seite der MySQL Datenbank erzeugt,
 * von Java übernommen und als id gespeichert.
 * @see data.dataHandling.MySQLDataManager
 *
 */
public class Article extends PersistentEntity {

    private String articleNumber; // speichert die vom Benutzer eingegebene Artikelnummer
    private SportType sportType; // speichert die ausgewählte Sportart
    private Manufacturer manufacturer; // verbindet den Artikel mit einem Herstellerobjekt
    private Designation designation; // verbindet den Artikel mit einem Bezeichnungsobjekt

    public Article(String articleNumber, SportType sportType,
            Manufacturer manufacturer, Designation designation) { // erstellt einen noch nicht gespeicherten Artikel

        this(-1, articleNumber, sportType, manufacturer, designation); // kennzeichnet den Artikel als neu
    }

    public Article(int id, String articleNumber, SportType sportType,
            Manufacturer manufacturer, Designation designation) { // erstellt einen Artikel mit einer vorgegebenen ID

        super(id); // übergibt die ID an die Mutterklasse
        this.articleNumber = articleNumber; // speichert die Artikelnummer
        this.sportType = sportType; // speichert die Sportart
        this.manufacturer = manufacturer; // speichert den Hersteller
        this.designation = designation; // speichert die Bezeichnung
    }

    public String getArticleNumber() { // ermöglicht das Lesen der Artikelnummer
        return articleNumber; // gibt die Artikelnummer zurück
    }

    public void setArticleNumber(String articleNumber) { // ermöglicht das Ändern der Artikelnummer
        this.articleNumber = articleNumber; // speichert die neue Artikelnummer
    }

    public SportType getSportType() { // ermöglicht das Lesen der Sportart
        return sportType; // gibt die ausgewählte Sportart zurück
    }

    public void setSportType(SportType sportType) { // ermöglicht das Ändern der Sportart
        this.sportType = sportType; // speichert die neue Sportart
    }

    public Manufacturer getManufacturer() { // ermöglicht den Zugriff auf das Herstellerobjekt
        return manufacturer; // gibt das verbundene Herstellerobjekt zurück
    }

    public void setManufacturer(Manufacturer manufacturer) { // ermöglicht die Zuweisung eines anderen Herstellers
        this.manufacturer = manufacturer; // speichert die neue Verbindung
    }

    public Designation getDesignation() { // ermöglicht den Zugriff auf das Bezeichnungsobjekt
        return designation; // gibt das verbundene Bezeichnungsobjekt zurück
    }

    public void setDesignation(Designation designation) { // ermöglicht die Zuweisung einer anderen Bezeichnung
        this.designation = designation; // speichert die neue Verbindung
    }

    @Override // erfüllt die Vorgabe aus PersistentEntity
    protected String getFileName() { // bestimmt die Zieldatei für Artikel
        return ARTICLES_FILE; // verwendet articles.csv im Datenordner
    }

    /**
     * 
     * Verpackt die Artikeldaten als Textarray
     * @see data.objekte.PersistentEntity#getObjectData()
     */

    @Override
    public String[] getObjectData() { // verpackt die Artikeldaten als Textarray

        String[] data = new String[5]; // erstellt Platz für fünf Felder

        data[0] = String.valueOf(getId()); // übernimmt die interne ID als Text
        data[1] = articleNumber; // übernimmt die Artikelnummer
        data[2] = sportType.name(); // übernimmt den festen enum-Namen
        data[3] = manufacturer.getName(); // übernimmt den Herstellernamen
        data[4] = designation.getName(); // übernimmt die Bezeichnung

        return data; // gibt die Daten zurück
    }

    /**
     * 
     * Erstellt einen lesbaren Text für die Konsole
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() { // erstellt einen lesbaren Text für die Konsole
        return getId() + " | " + articleNumber + " | " + sportType
                + " | " + manufacturer + " | " + designation; // verwendet auch die toString()-Methoden der verbundenen
                                                              // Objekte
    }
}
