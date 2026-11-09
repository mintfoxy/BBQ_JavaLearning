package data.dataHandling;

import java.sql.SQLException;

public class TestManager { // testet die Verbindung zur MySQL-Datenbank

    public static void main(String[] args) { // startet den Verbindungstest

        MySQLDataManager manager = MySQLDataManager.getInstance(); // holt die gemeinsame Instanz des Managers

        try {
            String databaseName = manager.testConnection(); // prüft die Verbindung und liest den Datenbanknamen
            IO.println("Verbindung erfolgreich: " + databaseName); // zeigt den Namen der Datenbank an
        } catch (SQLException exception) { // fängt einen Fehler beim Verbindungstest ab
            IO.println("Verbindung fehlgeschlagen: " + exception.getMessage()); // zeigt die Fehlermeldung an
        }
    }
}