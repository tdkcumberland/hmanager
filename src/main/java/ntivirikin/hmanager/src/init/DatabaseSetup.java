package ntivirikin.hmanager.src.init;

import java.sql.*;

public class DatabaseSetup {
    private static Connection connection;
    private static Statement state;
    private static PreparedStatement pstate;

    // Initializes connection to database and creates database if it doesn't exist
    public static void createDatabase() throws Exception {
        connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/hdbase.db");
        connection.close();
    }

    // Checks to see if tables already exist
    public static boolean tableExists(String tableName) throws Exception {
        connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/hdbase.db");
        boolean exists = false;
        ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null);
        while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(tableName)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public static void createAdmin() throws Exception {
        connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/hdbase.db");
        state = connection.createStatement();

        //String dropAdmin = "DROP TABLE Admin";

        //state.executeUpdate(dropAdmin);

        String createAdminT = "CREATE TABLE Admin " +
                              "(ID            INT PRIMARY KEY      NOT NULL," +
                              " USERNAME      CHARACTER(50)        NOT NULL," +
                              " PASSWORD      CHARACTER(50)        NOT NULL," +
                              " STATUS        BOOLEAN              NOT NULL)";

        state.executeUpdate(createAdminT);
        state.close();
        connection.close();
    }

    public static void createRoom() throws Exception {
        connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/hdbase.db");
        state = connection.createStatement();

        String createRoomT = "CREATE TABLE Room " +
                             "(ID         INT PRIMARY KEY NOT NULL," +
                             " NUMBER     INT             NOT NULL," +
                             " FLOOR      INT             NOT NULL," +
                             " CAPACITY   INT             NOT NULL," +
                             " PRICE      REAL            NOT NULL," +
                             " ACTIVE     BOOLEAN         NOT NULL)";

        state.executeUpdate(createRoomT);
        state.close();
        connection.close();
    }

    public static void createGuest() throws Exception {
        connection = null;
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/databases/hdbase.db");
        state = connection.createStatement();

        String createGuestT = "CREATE TABLE Guest " +
                              "(ID          INT PRIMARY KEY     NOT NULL," +
                              " FIRSTNAME   TEXT                NOT NULL," +
                              " LASTNAME    TEXT                NOT NULL," +
                              " SIGNIN      TEXT                NOT NULL," +
                              " SIGNOUT     TEXT)";

        state.executeUpdate(createGuestT);
        state.close();
        connection.close();
    }
}
