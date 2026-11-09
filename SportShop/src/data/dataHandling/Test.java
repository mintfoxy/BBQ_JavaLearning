package data.dataHandling;

import java.util.HashMap;

import data.objekte.Article;
import data.objekte.Designation;
import data.objekte.Manufacturer;
import data.objekte.SportType;
import data.objekte.ObjectConverter;

public class Test { // prüft die Umwandlung zwischen Artikelobjekt und HashMap

    public static void main(String[] args) { // startet den Test

        Manufacturer manufacturer = new Manufacturer("Wilson"); // erstellt einen Hersteller
        Designation designation = new Designation("Tennisschläger"); // erstellt eine Bezeichnung

        Article article = new Article(
                1, "TEN-001", SportType.TENNIS,
                manufacturer, designation); // erstellt einen Beispielartikel mit fester Nummer

        HashMap<String, Object> map = ObjectConverter.toHashMap(article); // überträgt die Artikeldaten in eine Map

        IO.println(map); // zeigt die Schlüssel und Werte

        Article restoredArticle = ObjectConverter.createFromMap(map); // erstellt ein neues Objekt aus der Map

        if (restoredArticle == null) { // prüft, ob die Erstellung fehlgeschlagen ist
            IO.println("Artikel konnte nicht erstellt werden."); // zeigt eine Fehlermeldung
            return; // beendet den Test
        }

        IO.println("Wiederhergestellter Artikel:"); // kündigt die Ausgabe an
        IO.println(restoredArticle.toString()); // zeigt den wiederhergestellten Artikel

        String sql = ObjectConverter.insertSql();
        IO.println(sql);
    }
}