package data.dataHandling;

import java.sql.SQLException;
import java.util.List;
import data.objekte.Article;
import data.objekte.Manufacturer;

/**
 * diese Klasse legt die gemeinsamen Methoden einer Datenverwaltung fest
 * DataManager.
 * Interface beschreib, was eine Klasse können muss.
 */

public interface DataManager {

    List<Article> readAllArticles(); // liefert die Einträge aus der Tabelle in der Datenbank

    void saveArticle(Article article) throws SQLException; // erstellt oder ändert die Einträge

    void deleteArticle(Article article) throws SQLException; // löscht die Eunträge

    List<Manufacturer> readAllManufacturers(); // liefert die Hersteller aus der Tabelle in der Datenbank

    List <Article> readManufacturerArticles(Manufacturer manufacturer) throws SQLException; // liefert alle Artikel eines bestimmten Herstelelrs

}
