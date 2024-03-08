import java.sql.*;

public class PB02_GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "1234");

        String query = "SELECT v.name, COUNT(mv.minion_id) AS minion_count\n" +
                "FROM minions_villains mv\n" +
                "         JOIN villains v ON mv.villain_id = v.id\n" +
                "GROUP BY v.name\n" +
                "ORDER BY minion_count DESC;";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            String villainName = resultSet.getString("name");
            int minionCount = resultSet.getInt("minion_count");
            System.out.println(villainName + " " + minionCount);
        }
    }
}