import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PB03_Get_Minion_Names {

    public static final String GET_ALL_MINIONS_NAMES = "select v.name, m.name, m.age, mv.villain_id from villains as v " +
            "join minions_db.minions_villains as mv on v.id = mv.villain_id " +
            "join minions as m on mv.minion_id = m.id " +
            "where villain_id = ? ;";

    public static String PRINT_VILLAIN_NAME_FORMAT = "Villain: %s%n";
    public static String PRINT_MINION_DATA_FORMAT = "%d. %s %d%n";
    public static String PRINT_NO_VALID_VILLAIN_FORMAT = "No villain with ID %d exists in the database.%d";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        int villain_id_selector = Integer.parseInt(scanner.nextLine());

        PreparedStatement statement = connection.prepareStatement(GET_ALL_MINIONS_NAMES);
        statement.setInt(1, villain_id_selector);

        ResultSet result = statement.executeQuery();

        print(result, villain_id_selector);

    }

    private static void print(ResultSet result, int id) throws SQLException {
        if (result.next()) {
            System.out.printf(PRINT_VILLAIN_NAME_FORMAT, result.getString("name"));
        } else {
            System.out.printf(PRINT_NO_VALID_VILLAIN_FORMAT, id);
        }
        while (result.next()) {
            System.out.printf(PRINT_MINION_DATA_FORMAT
                    , result.getRow() - 1
                    , result.getString("m.name"),
                    result.getInt("age"));
        }


    }


}
