import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {





        Connection connectionToDB =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni","root","1234");


        String query = "SELECT first_name, last_name FROM employees WHERE salary > ?";
        PreparedStatement statement = connectionToDB.prepareStatement(query);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please entre salary");
        String salary = scanner.nextLine();

        statement.setDouble(1, Double.parseDouble(salary));

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("first_name") + " " + result.getString("last_name"));
        }
    }
}