package data.objekte.tests;

import data.objekte.Article;
import data.objekte.Designation;
import data.objekte.Manufacturer;
import data.objekte.SportType;

public class TestArticle { // prüft Objekte und ihre Verbindungen ohne Daten zu speichern

    public static void main(String[] args) { // startet den Objekttest

        Manufacturer manufacturer = new Manufacturer("Wilson"); // erstellt einen gemeinsam verwendeten Hersteller
        Article firstArticle = new Article("TEN-001", SportType.TENNIS,
                manufacturer, new Designation("Tennisschläger")); // erstellt den ersten Artikel
        Article secondArticle = new Article("TEN-002", SportType.TENNIS,
                manufacturer, new Designation("Tennisball")); // erstellt den zweiten Artikel mit demselben Herstellerobjekt

        IO.println(firstArticle.toString()); // zeigt den ersten Artikel mit id 1
        IO.println(secondArticle.toString()); // zeigt den zweiten Artikel mit id 2
        IO.println(firstArticle.getManufacturer() == secondArticle.getManufacturer()); // prüft, ob beide Artikel auf denselben Hersteller zeigen
    }
}
