import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlTest {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/azubisbbq";
        String user = "root";
        String password = "n?.r=k0O*Gg1";

        try (Connection connection =
                DriverManager.getConnection(url, user, password)) {

            System.out.println("Подключение установлено!");
            System.out.println("База: " + connection.getCatalog());

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}