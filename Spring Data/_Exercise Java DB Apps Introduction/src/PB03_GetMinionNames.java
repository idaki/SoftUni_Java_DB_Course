import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PB03_GetMinionNames {
    public static void main(String[] args) throws SQLException {
        System.out.println("Please enter villain ID");
        Scanner scanner = new Scanner(System.in);
        int villainIDInput = Integer.parseInt(scanner.nextLine());

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        String villainName = getVillainName(villainIDInput, connection);

        if (villainName != null) {
            System.out.printf("Villain: %s%n", villainName);
        } else {
            System.out.printf("No villain with ID %d exists in the database.", villainIDInput);
            return;
        }

        List<String> minions = getMinions(connection, villainIDInput);
        minions.forEach(System.out::println);
        connection.close();
    }

    private static List<String> getMinions(Connection connection, int id) throws SQLException {
        List<String> minions = new ArrayList<>();
        String query = "SELECT name, age FROM minions m JOIN minions_db.minions_villains mv on m.id = mv.minion_id\n" +
                "            WHERE mv.villain_id =?;";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        int row = 1;
        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            minions.add(String.format("%d. %s %d", row++, name, age));
        }
        return minions;
    }

    private static String getVillainName(int id, Connection connection) throws SQLException {
        String query = "SELECT name FROM villains v WHERE v.id =?;";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return rs.getString("name");
        }
        return null;
    }
}
