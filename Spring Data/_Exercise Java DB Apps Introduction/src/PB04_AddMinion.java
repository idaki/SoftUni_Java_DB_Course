import java.sql.*;
import java.util.Scanner;

public class PB04_AddMinion {
    public static void main(String[] args) throws SQLException {
//read user input
        Scanner sc = new Scanner(System.in);
        String[] minionInputArr = sc.nextLine().split("\\s+");
        String minion = minionInputArr[1];
        int age = Integer.parseInt(minionInputArr[2]);
        String town = minionInputArr[3];
        String villain = sc.nextLine().split("\\s+")[1];

        //create connection

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        //add  town  in db if missing

        addTownToDBIfMissing(town, connection);
//check if vilan is in db
        addVilliantoDBIfMising(villain, connection);




        //add minion


    }

    private static void addVilliantoDBIfMising(String villain, Connection connection) throws SQLException {
        String query = "SELECT name FROM villains v WHERE v.name = ?;";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, villain);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            String insertQuery = "INSERT INTO villains (name, evilness_factor) VALUES (?,?);";
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setString(1, villain);
            insertStmt.setString(2, "evil");
            insertStmt.executeUpdate();
        }
    }

    private static void addTownToDBIfMissing(String town, Connection connection) throws SQLException {
        String query = "SELECT name FROM towns t WHERE t.name = ?;";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, town);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            String insertQuery = "INSERT INTO towns (name) VALUES (?);";
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setString(1, town);
            insertStmt.executeUpdate();
        }

    }

    private static void createConnection() throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

    }
}
