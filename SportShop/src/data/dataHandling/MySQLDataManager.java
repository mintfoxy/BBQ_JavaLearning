package data.dataHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import data.objekte.Article;
import data.objekte.Designation;
import data.objekte.Manufacturer;
import data.objekte.SportType;

public class MySQLDataManager implements DataManager {

    private final String url; // enthält die Adresse der Datenbank
    private final String user; // enthält den Benutzernamen
    private final String password; // enthält das Passwort

    /**
     * Singleton
     */
    private static final MySQLDataManager instance = new MySQLDataManager(); // erstellt die gemeinsame Instanz des
                                                                             // Managers

    public static MySQLDataManager getInstance() { // gibt die gemeinsame Instanz zurück
        return instance; // liefert den bereits erstellten Manager
    }

    private MySQLDataManager() { // lädt die Einstellungen beim Erstellen des Managers

        Properties properties = new Properties(); // Objekt sammelt die Einstellungen aus der Datei

        try (FileReader reader = new FileReader("database.properties")) { // öffnet die Datei mit Werten

            properties.load(reader); // liest die Werte aus der Datei

        } catch (IOException exception) { // behandelt Fehler beim Öffnen oder Lesen
            throw new IllegalStateException("Datenbankkonfiguration konnte nicht geladen werden", exception); // beendet
                                                                                                              // die
                                                                                                              // Erstellung
                                                                                                              // des
                                                                                                              // Managers
        }

        url = properties.getProperty("db.url"); // Adresse
        user = properties.getProperty("db.user"); // Login
        password = properties.getProperty("db.password"); // Passwort

        if (url == null || url.isBlank()
                || user == null || user.isBlank()
                || password == null) { // prüft die erforderlichen Einstellungen
            throw new IllegalStateException("Datenbankkonfiguration ist unvollständig"); // meldet fehlende
                                                                                         // Einstellungen
        }
    }

    /**
     * prüft die Verbindung zur MySQL-Datenbank
     *
     * @return Name der verbundenen Datenbank
     * @throws SQLException wenn die Verbindung fehlschlägt
     */
    public String testConnection() throws SQLException {

        try (Connection connection = DriverManager.getConnection(url, user, password)) { // öffnet die Verbindung
            return connection.getCatalog(); // gibt den Namen der verbundenen Datenbank zurück
        }
    }

    /**
     * lädt alle Artikel einschließlich ihrer Hersteller aus der Datenbank
     */
    @Override
    public List<Article> readAllArticles() {

        List<Article> articles = new ArrayList<>(); // sammelt die geladenen Artikel

        try (Connection connection = DriverManager.getConnection(url, user, password); // öffnet die Verbindung
                PreparedStatement statement = connection.prepareStatement(MySQLConstants.SELECT_ARTICLE); // bereitet
                                                                                                          // die Abfrage
                                                                                                          // vor
                ResultSet result = statement.executeQuery()) { // führt die Abfrage aus

            while (result.next()) { // geht die gefundenen Zeilen durch

                int id = result.getInt("id"); // liest die ID des Artikels
                String articleNumber = result.getString("article_number"); // liest die Artikelnummer

                String sportTypeName = result.getString("sport_type"); // liest den gespeicherten Enum-Namen

                if (sportTypeName == null) { // erkennt eine fehlende Sportart
                    throw new SQLException("Sportart fehlt beim Artikel mit ID: " + id);
                }

                SportType sportType = SportType.valueOf(sportTypeName); // wandelt den gespeicherten Namen in eine
                                                                        // Sportart um

                int manufacturerId = result.getInt("manufacturer_id"); // liest die ID des Herstellers
                String manufacturerName = result.getString("manufacturer_name"); // liest den Namen aus der
                                                                                 // Herstellertabelle

                Manufacturer manufacturer = new Manufacturer(manufacturerId, manufacturerName); // erstellt den
                                                                                                // Hersteller mit ID und
                                                                                                // Name

                String designationName = result.getString("designation"); // liest die Artikelbezeichnung
                Designation designation = new Designation(designationName); // erstellt das Bezeichnungsobjekt

                Article article = new Article(id, articleNumber, sportType, manufacturer, designation); // erstellt den
                                                                                                        // vollständigen
                                                                                                        // Artikel

                articles.add(article); // fügt den Artikel zur Liste hinzu
            }

            return articles; // liefert die geladenen Artikel oder eine leere Liste

        } catch (SQLException exception) { // fängt Fehler beim Zugriff auf die Datenbank ab
            IO.println("Datenbankfehler: " + exception.getMessage());
            return null;

        } catch (IllegalArgumentException exception) { // fängt ungültige Enum-Namen ab
            IO.println("Ungültige Artikeldaten: " + exception.getMessage());
            return null;
        }
    }

    /**
     * speichert neue Artikel und aktualisiert vorhandene Artikel
     *
     * @param article der zu speichernde Artikel
     * @throws SQLException wenn das Speichern fehlschlägt
     */
    @Override
    public void saveArticle(Article article) throws SQLException {

        if (article == null) { // erkennt einen fehlenden Artikel
            throw new IllegalArgumentException("Kann kein null Objekt speichern");
        }

        if (article.getManufacturer() == null || article.getManufacturer().getId() < 1) { // prüft die Auswahl eines
                                                                                          // gespeicherten Herstellers
            throw new IllegalArgumentException("Bitte einen vorhandenen Hersteller auswählen");
        }

        if (article.getId() > 0) { // erkennt einen bereits gespeicherten Artikel

            try (Connection connection = DriverManager.getConnection(url, user, password); // öffnet die Verbindung
                    PreparedStatement statement = connection.prepareStatement(MySQLConstants.UPDATE_ARTICLE)) { // bereitet
                                                                                                                // den
                // UPDATE-Befehl vor

                statement.setString(1, article.getArticleNumber()); // setzt die Artikelnummer
                statement.setString(2, article.getSportType().name()); // setzt den Namen der Enum Konstante
                statement.setInt(3, article.getManufacturer().getId()); // setzt die ID des ausgewählten Herstellers
                statement.setString(4, article.getDesignation().getName()); // setzt die Artikelbezeichnung
                statement.setInt(5, article.getId()); // bestimmt den Artikel für die WHERE edingung

                int updatedRows = statement.executeUpdate(); // führt den UPDATE Befehl aus

                if (updatedRows != 1) { // prüft die vom Treiber gemeldete Zeilenanzahl
                    throw new SQLException("Der Artikel konnte nicht aktualisiert werden. ID: " + article.getId());
                }
            }

        } else if (article.getId() == -1) { // erkennt einen neuen Artikel

            try (Connection connection = DriverManager.getConnection(url, user, password); // öffnet die Verbindung
                    PreparedStatement statement = connection.prepareStatement(
                            MySQLConstants.INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS)) { // fordert die von MySQL
                                                                                               // erzeugte ID an

                statement.setString(1, article.getArticleNumber()); // setzt die Artikelnummer
                statement.setString(2, article.getSportType().name()); // setzt den Namen der Enum-Konstante
                statement.setInt(3, article.getManufacturer().getId()); // setzt die ID des ausgewählten Herstellers
                statement.setString(4, article.getDesignation().getName()); // setzt die Artikelbezeichnung

                int insertedRows = statement.executeUpdate(); // führt den INSERT Befehl aus

                if (insertedRows != 1) { // prüft, ob genau eine Zeile eingefügt wurde
                    throw new SQLException("Der Artikel konnte nicht eingefügt werden");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) { // liest die von MySQL erzeugte ID
                    if (generatedKeys.next()) { // prüft, ob eine ID vorhanden ist
                        article.setId(generatedKeys.getInt(1)); // übernimmt die ID in das Artikelobjekt
                    }
                }
            }

        } else { // erkennt eine ungültige interne ID
            throw new IllegalArgumentException("Ungültige Artikel-ID: " + article.getId());
        }
    }

    /**
     * löscht einen Artikel aus der Datenbank
     */
    @Override
    public void deleteArticle(Article article) throws SQLException {

        if (article == null) { // erkennt einen fehlenden Artikel
            throw new IllegalArgumentException("Artikel existiert nicht");
        }

        if (article.getId() < 1) { // erkennt eine ungültige Artikel-ID
            throw new IllegalArgumentException("Ungültige Artikel-ID: " + article.getId());
        }

        try (Connection connection = DriverManager.getConnection(url, user, password); // öffnet die Verbindung
                PreparedStatement statement = connection.prepareStatement(MySQLConstants.DELETE_ARTICLE)) { // bereitet
                                                                                                            // den
                                                                                                            // DELETE-Befehl
                                                                                                            // vor

            statement.setInt(1, article.getId()); // bestimmt den Artikel für die WHERE-Bedingung

            int deletedRows = statement.executeUpdate(); // führt den DELETE-Befehl aus

            if (deletedRows != 1) { // prüft, ob genau ein Artikel gelöscht wurde
                throw new SQLException("Der Artikel konnte nicht gelöscht werden");
            }
        }
    }

    /**
     * lädt alle Einträge aus der Tabelle manufacturer
     */
    @Override
    public List<Manufacturer> readAllManufacturers() {

        List<Manufacturer> manufacturers = new ArrayList<>(); // sammelt die geladenen Hersteller

        try (Connection connection = DriverManager.getConnection(url, user, password); // öffnet die Verbindung
                PreparedStatement statement = connection.prepareStatement(MySQLConstants.SELECT_MANUFACTURER); // bereitet
                                                                                                               // die
                                                                                                               // Abfrage
                // vor
                ResultSet result = statement.executeQuery()) { // führt die Abfrage aus

            while (result.next()) { // geht die gefundenen Zeilen durch

                int id = result.getInt("id"); // liest die Hersteller ID
                String name = result.getString("name"); // liest den Herstellernamen

                Manufacturer manufacturer = new Manufacturer(id, name); // erstellt den Hersteller aus ID und Name
                manufacturers.add(manufacturer); // fügt den Hersteller zur Liste hinzu
            }

            return manufacturers; // liefert die geladenen Hersteller oder eine leere Liste

        } catch (SQLException exception) { // fängt Fehler beim Verbinden oder Lesen ab
            IO.println("Fehler beim Lesen der Herstellerliste: " + exception.getMessage()); // zeigt die
                                                                                            // Fehlerbeschreibung
            return null; // meldet einen Lesefehler an Main
        }
    }

    /**
     * lädt alle Artikel eines bestimmten Herstellers aus der Tabelle in der
     * Datenbank
     * 
     */
    @Override
    public List<Article> readManufacturerArticles(Manufacturer manufacturer) throws SQLException {

        List<Article> articles = new ArrayList<>();

        if (manufacturer == null) {
            throw new SQLException("Keine Herstellerliste vorhanden");
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection
                        .prepareStatement(MySQLConstants.SELECT_ARTICLE_WITH_MANUFACTURER)) {

            statement.setInt(1, manufacturer.getId());

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) { // geht die gefundenen Zeilen durch

                    int id = result.getInt("id"); // liest die ID des Artikels

                    String articleNumber = result.getString("article_number"); // liest die Artikelnummer

                    String sportTypeName = result.getString("sport_type"); // liest den gespeicherten Enum-Namen

                    if (sportTypeName == null) { // erkennt eine fehlende Sportart
                        throw new SQLException("Sportart fehlt beim Artikel mit ID: " + id);
                    }

                    SportType sportType = SportType.valueOf(sportTypeName); // wandelt den gespeicherten Namen in eine
                                                                            // Sportart um

                    String designationName = result.getString("designation"); // liest die Artikelbezeichnung

                    Designation designation = new Designation(designationName); // erstellt das Bezeichnungsobjekt

                    Article article = new Article(id, articleNumber, sportType, manufacturer, designation); // erstellt
                                                                                                            // den
                                                                                                            // vollständigen
                                                                                                            // Artikel

                    articles.add(article); // fügt den Artikel zur Liste hinzu
                }
            }
            return articles;

        } catch (SQLException exception) {
            IO.println("Fehler beim Lesen der Artikelliste: " + exception.getLocalizedMessage());
            return null;
        }

    }
}