import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user","root");
        props.setProperty("password", "root");

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni",props);


        String query = "SELECT first_name, last_name FROM employees WHERE salary > ?";
        PreparedStatement statement = connection.prepareStatement(query);
        Scanner scanner = new Scanner(System.in);

        String salary = scanner.nextLine();
        statement.setDouble(1, Double.parseDouble(salary));

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("first_name") + " " + result.getString("last_name"));
        }
    }
}
