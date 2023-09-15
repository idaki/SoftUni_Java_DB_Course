import Utils.Utils;

import java.sql.*;

public class PB07_Print_All_Minion_Names {
    public static final String GET_MINIONS_NAMES ="select * from minions";
    public static final String GET_NUMBER_OF_VALID_MINIONS = "select count(name) from minions\n" +
            "where name is not null";
    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        int rowCount = getRowCount(connection);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet result = statement.executeQuery(GET_MINIONS_NAMES);

        for (int i = 1; i <rowCount / 2; i++) {
            printEntry(result, i);
            printEntry(result, rowCount+1 - i);
        }
    }

    private static void printEntry(ResultSet result, int i) throws SQLException {
        if (result.absolute(i)) {
            String name = result.getString("name");
            System.out.println(name);
        }
    }

    private static int getRowCount(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_VALID_MINIONS);
        ResultSet result = statement.executeQuery();
        int rowCount = 0;
        if (result.next()) {
            rowCount = result.getInt(1);
        }
        return rowCount;
    }
}
