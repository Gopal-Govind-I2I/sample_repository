package com.ideas2it.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection is a singleton class equipped with methods for providing database connectivity
 *
 * @version 1.1   09-04-2021
 *
 * @author Gopal G
 */

public class DatabaseConnection {
    public static DatabaseConnection databaseConnection = null;
    public static Connection connection = null;

   /**
    * Constructor that renders singleton characteristics to DatabaseConnection
    */
    private DatabaseConnection() {
    }
	
   /**
    * Returns an instance of DatabaseConnection
    *
    * @return databaseConnection an instance of the DatabaseConnection class
    */
    public static DatabaseConnection getInstance() {
       if (databaseConnection == null) {
           databaseConnection = new DatabaseConnection();
       }
       return databaseConnection;
    }
	
   /**
    * Creates a Connection object using database credentials and returns it on request
    *
    * @return connection a Connection object that helps to communicate with the database
    */
    public static Connection getConnection() {
        String db_url = "jdbc:mysql://localhost/i2i_empprojcrud_modif";
        String user = "root";
        String password = "root@123";
        try {
            if(null == connection || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(db_url, user, password);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }
}