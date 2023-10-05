package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "userdb";
        String userName = "root";
        String password = "Crehfnjd1311";
        return getConnection(hostName, dbName, userName, password);
    }

    public static Connection getConnection(String hostName, String dbName,
                                           String userName, String password) throws SQLException {
        String URL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connect = DriverManager.getConnection(URL, userName, password);
        return connect;
    }
}
