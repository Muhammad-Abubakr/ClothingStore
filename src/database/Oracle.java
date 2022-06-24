package database;

// imports required for the connection to be established with the server
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Oracle {

    // Attributes
    // Establishing a connection
    // con here is the object that stores the established connection details
    private static Connection con;

    /*Constructor*/
    public Oracle() {
        initDb();
    }

    // Connection details
    /* DATABASE INITIALIZER
    * Database: in case of oracle : jdbc:oracle:thin
    * address | hostname: the address to the server, in our case @localhost
    * SID : XE (since we have express edition as the server)
    * username : the username for connection, that will be 'hr'
    * password : the password for connection, that will be 'hr'
    * */
    private void initDb() {
        System.out.println("\nInitializing Oracle Database...");

        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "hr",
                    "hr"
            );

            System.out.println("Connection Established with Oracle Server => hostname: @localhost , PortNumber : 1521, with SID : xe");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
