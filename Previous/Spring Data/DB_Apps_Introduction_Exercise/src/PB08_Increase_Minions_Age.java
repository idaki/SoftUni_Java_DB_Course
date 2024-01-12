import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class PB08_Increase_Minions_Age {
    public static final String GET_MINIONS_DATA_FROM_MINIONS_TABLE= "select id, name, age from minions";
    public static final String PRINT_CONSOLE_FORMAT = "%s %d%n";
    public static final String COLUMN_NAME_FROM_MINION_TABLE = "name";
    public static final String COLUMN_AGE_FROM_MINION_TABLE = "age";
    public static final int AGE_INCREMENT_FACTOR = 1;
    public static void main(String[] args) throws SQLException {

        int[] ids = getIdsFromConsole();

        Connection connection = Utils.getSQLConnection();

        ResultSet result = getStatement(connection);

        while (result.next()){
            int id = result.getInt("id");
            print(ids, id,result);

        }
    }

    public static void print(int[] ids, int id, ResultSet result ) throws SQLException {
        System.out.printf(PRINT_CONSOLE_FORMAT
                , isEligibleIDForLowercase(ids,id)
                        ? result.getString(COLUMN_NAME_FROM_MINION_TABLE).toLowerCase()
                        :  result.getString(COLUMN_NAME_FROM_MINION_TABLE)
                ,result.getInt(COLUMN_AGE_FROM_MINION_TABLE) + AGE_INCREMENT_FACTOR);
    }

    private static boolean isEligibleIDForLowercase(int[] ids,int id ){
        return Arrays.stream(ids).anyMatch(i -> i == id);
    }
    private static ResultSet getStatement(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_MINIONS_DATA_FROM_MINIONS_TABLE);
        return statement.executeQuery();
    }

    private static int[] getIdsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
