package database;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Pinger {
    public static String ping(String url, String username, String password) throws SQLException {
        Properties connProps = new Properties();
        connProps.put("user", username);
        connProps.put("password", password);

        Connection conn = null;
        conn = DriverManager.getConnection(url, connProps);

        PreparedStatement statement = conn.prepareStatement("SELECT column1 FROM table1");
        ResultSet resultSet = statement.executeQuery();

        resultSet.first();
        String ret = resultSet.getString(1);

        return ret;
    }

    public static void main(String[] args) throws IOException, SQLException {
        final String PROPERTIES_FILE = "environment.properties";

        Properties properties = new Properties();
        properties.load(Pinger.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));

        String url = properties.getProperty("db_url");
        String username = properties.getProperty("db_username");
        String password = properties.getProperty("db_password");

        String response = ping(url, username, password);
        System.out.println(response);
    }
}