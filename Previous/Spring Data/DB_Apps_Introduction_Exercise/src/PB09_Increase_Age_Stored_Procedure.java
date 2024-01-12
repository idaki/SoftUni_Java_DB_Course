import Utils.Utils;

import java.sql.*;
import java.util.Scanner;

public class PB09_Increase_Age_Stored_Procedure {

    public static final String CREATE_PROCEDURE_TO_INCREASE_AGE_BY_ID= "call usp_get_older(?)";
    public static final String  GET_MINIONS_DATA_FROM_MINIONS_TABLE= "select id, name, age from minions where id =?";
    public static void main(String[] args) throws SQLException {
        int id = getIdFromConsole();
        Connection connection = Utils.getSQLConnection();
        CallableStatement callableStatement = connection.prepareCall(CREATE_PROCEDURE_TO_INCREASE_AGE_BY_ID);

        callableStatement.setInt(1,id);
        callableStatement.execute();

       PreparedStatement preparedStatement = connection.prepareStatement
               (GET_MINIONS_DATA_FROM_MINIONS_TABLE);
       preparedStatement.setInt(1,id);

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            System.out.printf
                    ("%s %d%n", result.getString("name"),
                            result.getInt("age"));
        }

    }

    private static int getIdFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }
}
