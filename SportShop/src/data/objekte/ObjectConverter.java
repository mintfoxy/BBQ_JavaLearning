package data.objekte;

import java.util.HashMap;

/**
 * HashMap kann man sich wie eine Tabelle vorstellen: Spaltenname und das,
 * was in jeder Zelle steht. Eine Zeile mit allen Daten ist ein Map,
 * bzw. ein Datensatz ist ein Map
 * ObjectConverter
 */

public class ObjectConverter {

    public static HashMap<String, Object> toHashMap(Article article) { // konvertiert die Artikeldaten in eine Map

        HashMap<String, Object> map = new HashMap<>(); // verwendet die Object Klasse von Java

        map.put("id", article.getId()); // speichert die interne Nummer
        map.put("articleNumber", article.getArticleNumber()); // speichert die Artikelnummer
        map.put("sportType", article.getSportType().name()); // speichert den festen enum-Namen
        map.put("manufacturer", article.getManufacturer().getName()); // speichert den Herstellernamen
        map.put("designation", article.getDesignation().getName()); // speichert die Artikelbezeichnung

        return map; // gibt die gefüllte Map zurück
    }

    /**
     * Alternative Mathode mit eienr Schleife, ohne jedes Feld aufzuschreiben.
     * Feldernamen (=Spalten) müssen in diesem Fall den Spaltennamen in der DB entsprechen.
     * So werden Framworks geschrieben.
     * 
     * public static HashMap<String, Object> toHashMap(Article article) { //
     * überträgt die Artikelfelder in eine Map
     * 
     * HashMap<String, Object> map = new HashMap<>(); // sammelt Feldnamen und Feldwerte
     * map.put("id", article.getId()); // übernimmt die id aus der Basisklasse
     * 
     * Field[] fields = article.getClass().getDeclaredFields(); // ermittelt die direkt in der Laufzeitklasse deklarierten Felder
     * 
     * for (Field field : fields) { // geht die gefundenen Felder durch
     * 
     * try {
     * field.setAccessible(true); // erlaubt den Zugriff auf private Felder
     * map.put(field.getName(), field.get(article)); // speichert den Feldwert unter dem Feldnamen
     * 
     * } catch (IllegalArgumentException | IllegalAccessException exception) { // fängt Fehler beim Lesen des Feldes ab
     * exception.printStackTrace(); // gibt die Fehlerdetails aus
     * }
     * }
     * 
     * return map; // gibt die gefüllte Map zurück
     * }
     */

    public static Article createFromMap(HashMap<String, Object> map) { // erstellt einen Artikel aus einer Map

        if (map == null) { // prüft, ob eine Map übergeben wurde
            return null; // meldet fehlende Daten
        }

        String[] data = new String[5]; // erstellt Platz für die fünf Artikelfelder

        data[0] = String.valueOf(map.get("id")); // übernimmt die interne Nummer als Text
        data[1] = (String) map.get("articleNumber"); // übernimmt die Artikelnummer als String
        data[2] = (String) map.get("sportType"); // übernimmt den gespeicherten enum-Namen
        data[3] = (String) map.get("manufacturer"); // übernimmt den Herstellernamen
        data[4] = (String) map.get("designation"); // übernimmt die Artikelbezeichnung

        return ObjectFactory.articleFromData(data); // verwendet dieselbe Objekterstellung wie beim Laden der CSV-Datei
    }

    /**
     * Liefert die SQL-Anweisung zum Einfügen eines Artikels
     *
     * @return INSERT Anweisung mit vier Platzhaltern
     */

    public static String insertSql() {
        // überlässt die Vergabe der id der Datenbank (Auto Increment)
        // legt die Reihenfolge der Parameter über die Spaltenliste fest
        String sql = "INSERT INTO articles (article_number, sport_type, manufacturer, designation) " // legt die Spalten
                                                                                                     // für den neuen
                                                                                                     // Artikel fest
                + "VALUES (?, ?, ?, ?)"; // setzt einen Platzhalter für jede Spalte

        return sql; // gibt die SQL Anweisung als String zurück
    }
}
