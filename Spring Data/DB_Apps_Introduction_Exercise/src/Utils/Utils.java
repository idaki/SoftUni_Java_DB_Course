package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Utils {
    ;
   public static  Connection getSQLConnection () throws SQLException {
        return DriverManager.getConnection(Constants.JDBC_MYSQL_URL,
                Constants.USER_VALUE,
                Constants.PASSWORD_VALUE);
    }
}
