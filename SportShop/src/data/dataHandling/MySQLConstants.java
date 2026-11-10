package data.dataHandling;

final class MySQLConstants {

    /**
     * SQL Statements
     */

    static final String INSERT_ARTICLE = "INSERT INTO articles (article_number, sport_type, manufacturer_id, designation) "
            + "VALUES (?, ?, ?, ?)"; // fügt einen Artikel mit der ID seines Herstellers ein

    static final String UPDATE_ARTICLE = "UPDATE articles SET article_number = ?, sport_type = ?, "
            + "manufacturer_id = ?, designation = ? WHERE id = ?"; // aktualisiert den Artikel mit der angegebenen ID

    static final String SELECT_ARTICLE = "SELECT articles.id, articles.article_number, articles.sport_type, "
            + "articles.manufacturer_id, articles.designation, "
            + "manufacturer.name AS manufacturer_name "
            + "FROM articles "
            + "JOIN manufacturer ON articles.manufacturer_id = manufacturer.id "
            + "ORDER BY articles.id"; // verbindet die Artikel mit ihren Herstellern

    static final String SELECT_MANUFACTURER = "SELECT id, name FROM manufacturer ORDER BY id"; // liest alle
                                                                                                       // Hersteller
                                                                                                       // nach ihrer ID

    static final String DELETE_ARTICLE = "DELETE FROM articles WHERE id = ?"; // löscht den Artikel mit der angegebenen
                                                                              // ID

    static final String SELECT_ARTICLE_WITH_MANUFACTURER = "SELECT articles.id, articles.article_number, articles.sport_type, "
            + "articles.manufacturer_id, articles.designation, "
            + "manufacturer.name AS manufacturer_name "
            + "FROM articles "
            + "JOIN manufacturer ON articles.manufacturer_id = manufacturer.id "
            + "WHERE manufacturer_id = ?" // alle Artikel auswählen, die zu einem bestimmten Hersteller gehören
            + "ORDER BY articles.id";

}
