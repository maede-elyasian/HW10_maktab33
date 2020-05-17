package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static Connection connection = null;
    private static String URL = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String password = null;

   public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName(URL);
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw10", user, password);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("class not found " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Not able to connect to the Database " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
