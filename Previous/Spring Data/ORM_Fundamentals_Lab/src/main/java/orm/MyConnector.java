package orm;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnector {
    private static Connection connection;
    private static final String connectionToString = "jdbc:mysql::/localhost3306/";

    public static void createConnection(String username, String password, String dbName) throws SQLException {
        connection = DriverManager.getConnection(connectionToString+dbName,username,password);
    }

    public static Connection getConnection() {
        return connection;
    }
}
