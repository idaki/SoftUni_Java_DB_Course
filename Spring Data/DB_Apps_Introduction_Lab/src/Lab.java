import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Lab {
     public static final String QUERY = "select count(*), first_name, last_name from users " +
            "join diablo.users_games ug on users.id = ug.user_id " +
            "where last_name = ? " +
            "GROUP BY first_name , last_name;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties propes = new Properties();
        propes.setProperty("user","root");
        propes.setProperty("password","root");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo",propes);

//        Connection connection = DriverManager
//                .getConnection("jdbc:mysql://localhost:3306/diablo", "root", "root");

        PreparedStatement statement = connection.prepareStatement(QUERY);
        System.out.println("Get user");

        String user = scanner.nextLine();
        statement.setString(1,user);

        ResultSet result = statement.executeQuery();

        printResult(result);

    }

    private static void printResult(ResultSet result) throws SQLException {
        if (result.next()) {
            System.out.println(result.getString(
                    "first_name")
                    + " "
                    + result.getString("last_name")
                    +" "+ result.getInt(1));
        } else{
            System.out.println("No such user exists");
        }
    }
}
