package data.objekte;

import java.util.HashMap;

public class ObjectFactory { // erstellt Artikelobjekte aus gespeicherten Daten

    public static Article articleFromData(String[] data) { // erstellt einen Artikel aus fünf Textfeldern

        if (data == null || data.length != 5) { // prüft, ob fünf Felder vorhanden sind
            return null; // meldet unvollständige Daten
        }

        for (String value : data) { // prüft die übergebenen Felder
            if (value == null || value.isBlank()) { // erkennt fehlende oder leere Werte
                return null; // meldet ungültige Daten
            }
        }

        try { // behandelt eine ungültige Nummer oder Sportart

            int id = Integer.parseInt(data[0]); // wandelt die gespeicherte Nummer in eine Zahl um

            if (id < 1 || id == Integer.MAX_VALUE) { // prüft, ob die Nummer positiv ist und noch erhöht werden kann
                return null; // meldet eine ungültige Nummer
            }

            String articleNumber = data[1]; // übernimmt die Artikelnummer
            SportType sportType = SportType.valueOf(data[2]); // ermittelt die gespeicherte Sportart
            Manufacturer manufacturer = new Manufacturer(data[3]); // erstellt den Hersteller
            Designation designation = new Designation(data[4]); // erstellt die Bezeichnung

            return new Article(id, articleNumber, sportType,
                    manufacturer, designation); // erstellt den vollständigen Artikel

        } catch (IllegalArgumentException exception) { // erkennt eine ungültige Zahl oder unbekannte Sportart
            return null; // meldet dem CSVDataManager fehlerhafte Daten
        }
    }

    public static HashMap<String, Object> toHashMap(Article article) { // überträgt die Artikeldaten in eine Map

        HashMap<String, Object> map = new HashMap<>(); // verwendet die Object-Klasse von Java

        map.put("id", article.getId()); // speichert die interne Nummer
        map.put("articleNumber", article.getArticleNumber()); // speichert die Artikelnummer
        map.put("sportType", article.getSportType().name()); // speichert den festen enum-Namen
        map.put("manufacturer", article.getManufacturer().getName()); // speichert den Herstellernamen
        map.put("designation", article.getDesignation().getName()); // speichert die Artikelbezeichnung

        return map; // gibt die gefüllte Map zurück
    }

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

        return articleFromData(data); // verwendet dieselbe Objekterstellung wie beim Laden der CSV-Datei
    }
}