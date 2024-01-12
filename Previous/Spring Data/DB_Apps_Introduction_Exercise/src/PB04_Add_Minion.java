import Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PB04_Add_Minion {

    public static final String GET_VILLAIN_BY_NAME = "Select * from villains as v where v.name = ?";
    public static final String ADD_VILLAIN_TO_DATABASE = "insert into villains(name) values (?)";
    public static final String ADD_VILLAIN_FORMAT = "Villain %s was added to the database.%n";

    public static final String ADD_MINION_TO_VILLAIN_FORMAT = "Successfully added %s to be minion of %s.%n";
    public static final String ADD_MINION_TO_MINIONS_TABLE = "insert into minions (name, age,town_id)" +
            "values (?,?,?)";
    public static final String GET_MINION_BY_NAME = "select * from minions " +
            "where name = ?";
    public static final String ADD_TOWN_FORMAT = "Town %s was added to the database.%n";
    public static final String GET_TOWN_BY_NAME = "select * from towns as t " +
            "where t.name = ?";
    public static final String ADD_TOWN_TO_TOWNS_TABLE = "insert into towns (name) " +
            "values (?) ";

    public static final String ADD_MINION_VILLAIN_IDS_TO_MAP_TABLE= "insert into minions_villains (minion_id,villain_id)\n" +
            "values (?,?) ";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        String[] minionInput = scanner.nextLine().split("\\s+");
        String minionName = minionInput[1];
        int minionAge = Integer.parseInt(minionInput[2]);
        String town = minionInput[3];
        String villain = scanner.nextLine().split("\\s+")[1];

        Connection connection = Utils.getSQLConnection();

        ResultSet result = getTownInfoFromTownTable(connection, town);

        if (!result.next()) {
            addTownToTownsTable(connection, town);
        }
        result = getVillainInfoFromVillainsTable(connection, villain);


        if (!result.next()) {
            insertVillainToVillainsTableIfNotExisting(connection, villain);
        }

        result = getMinionInfoFromMinionsTable(connection, minionName);

        if (!result.next()) {
            ResultSet town_result = getTownInfoFromTownTable(connection, town);
            town_result.next();
            int town_id = town_result.getInt("id");
            ResultSet villain_result = getVillainInfoFromVillainsTable(connection, villain);
            villain_result.next();
            int villain_id = villain_result.getInt("id");
            ResultSet minion_result = getMinionInfoFromMinionsTable(connection, minionName);
            minion_result.next();
            int minion_id = minion_result.getInt("id");

            addMinionToMinionsTable(connection, minionName, minionAge, town_id);
            addMinionAndVillainIDsToMapTable(connection,minion_id,villain_id);

            System.out.printf(ADD_MINION_TO_VILLAIN_FORMAT, minionName, villain);


        }


    }

    private static void addMinionAndVillainIDsToMapTable(Connection connection, int minionId, int villainId) throws SQLException {
   PreparedStatement addMinionAndVillainsIDsToMapTable= connection.prepareStatement(ADD_MINION_VILLAIN_IDS_TO_MAP_TABLE);
   addMinionAndVillainsIDsToMapTable.setInt(1,minionId);
   addMinionAndVillainsIDsToMapTable.setInt(2,villainId);
   addMinionAndVillainsIDsToMapTable.executeUpdate();
    }

    private static void addMinionToMinionsTable(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement addMinionToMinionsTable = connection.prepareStatement(ADD_MINION_TO_MINIONS_TABLE);
        addMinionToMinionsTable.setString(1, minionName);
        addMinionToMinionsTable.setInt(2, minionAge);
        addMinionToMinionsTable.setInt(3, townId);
        addMinionToMinionsTable.executeUpdate();
    }

    private static ResultSet getMinionInfoFromMinionsTable(Connection connection, String minionName) throws SQLException {
        PreparedStatement getMinionStatement = connection.prepareStatement(GET_MINION_BY_NAME);
        getMinionStatement.setString(1, minionName);
        return getMinionStatement.executeQuery();

    }

    private static ResultSet getVillainInfoFromVillainsTable(Connection connection, String villain) throws SQLException {
        PreparedStatement getVillainStatement = connection.prepareStatement(GET_VILLAIN_BY_NAME);
        getVillainStatement.setString(1, villain);
        return getVillainStatement.executeQuery();
    }

    private static ResultSet getTownInfoFromTownTable(Connection connection, String town) throws SQLException {
        PreparedStatement getTownStatement = connection.prepareStatement(GET_TOWN_BY_NAME);
        getTownStatement.setString(1, town);
        return getTownStatement.executeQuery();
    }

    private static void addTownToTownsTable(Connection connection, String town) throws SQLException {
        PreparedStatement insertTownStatement = connection.prepareStatement(ADD_TOWN_TO_TOWNS_TABLE);
        insertTownStatement.setString(1, town);
        int rowsAffected = insertTownStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.printf(ADD_TOWN_FORMAT, town);
        }
    }

    private static void insertVillainToVillainsTableIfNotExisting(Connection connection, String villain) throws SQLException {
        PreparedStatement insertVillainStatement = connection.prepareStatement(ADD_VILLAIN_TO_DATABASE);
        insertVillainStatement.setString(1, villain);
        int rowsAffected = insertVillainStatement.executeUpdate(); // Use executeUpdate() for INSERT queries
        if (rowsAffected > 0) {
            System.out.printf(ADD_VILLAIN_FORMAT, villain);
        }
    }
}
