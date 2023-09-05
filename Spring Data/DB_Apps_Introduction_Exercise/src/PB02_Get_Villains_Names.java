import Utils.Utils;

import java.sql.*;
import java.util.Scanner;

public class PB02_Get_Villains_Names {

    public static final String GET_VILLIANS_NAMES = "select name, count(distinct mv.minion_id) as minions_count " +
            "from villains as v " +
            "         join minions_villains as mv on v.id = mv.villain_id " +
            "group by v.name " +
            "having minions_count > ? " +
            "order by minions_count desc;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        int limit = Integer.parseInt(scanner.nextLine());
        PreparedStatement statement = connection.prepareStatement(GET_VILLIANS_NAMES);
        statement.setInt(1, limit);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            System.out.printf(result.getString("name") + " " + result.getInt("minions_count") + System.lineSeparator());
        }


    }

}
