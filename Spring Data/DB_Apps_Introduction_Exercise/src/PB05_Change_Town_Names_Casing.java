import Utils.Utils;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PB05_Change_Town_Names_Casing {

    public static final String GET_CITY_NAMES_FROM_A_COUNTRY ="select * from towns where country = ? ";
public static final String UPDATE_CITY_NAME_TO_UPPER_CASE_BY_SELECTED_COUNTRY = "update towns " +
        "set name = UPPER(name) " +
        "where country =? ";

public static final String CONSOLE_OUTPUT_MESSAGE_SUCCESSFULLY_CHANGED = "%d town names were affected.\n" +
        "%s";

public  static final String CONSOLE_OUTPUT_MESSAGE_N0_CHANGES = "No town names were affected.";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner scanner =new Scanner(System.in);
        String country = scanner.nextLine();
        List<String> affectedCities = new ArrayList<>();

        ResultSet cityNamesResult = getCityNames(connection, country);

        updateCityNamesToUpperCase(connection, UPDATE_CITY_NAME_TO_UPPER_CASE_BY_SELECTED_COUNTRY, country);

        while (cityNamesResult.next()){
            String city = cityNamesResult.getString("name");
            affectedCities.add(city);
        }

        print(affectedCities);


    }

    private static void print(List<String> cities){
    if (!cities.isEmpty()){
        System.out.printf(CONSOLE_OUTPUT_MESSAGE_SUCCESSFULLY_CHANGED,
                cities.size()
                , Arrays.toString(cities.toArray()));
    }else{
        System.out.println(CONSOLE_OUTPUT_MESSAGE_N0_CHANGES);
    }

    }

    private static void updateCityNamesToUpperCase(Connection connection, String updateCityNameToUpperCaseBySelectedCountry, String country) throws SQLException {
        PreparedStatement updateCityName =
                connection.prepareStatement(updateCityNameToUpperCaseBySelectedCountry);
        updateCityName.setString(1, country);
        int cityNamesUpdate_Result = updateCityName.executeUpdate();

    }

    private static ResultSet getCityNames(Connection connection, String country) throws SQLException {
        PreparedStatement getCityNameStatement = connection.prepareStatement(GET_CITY_NAMES_FROM_A_COUNTRY);
        getCityNameStatement.setString(1, country);
        ResultSet result = getCityNameStatement.executeQuery();
        return result;
    }
}
