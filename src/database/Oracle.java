package database;

// imports required for the connection to be established with the server
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Oracle {

    // Attributes
    private static boolean instantiated = false;

    // Connection details
    /* DATABASE INITIALIZER
    * Database: in case of oracle : jdbc:oracle:thin
    * address | hostname: the address to the server, in our case @localhost
    * SID : XE (since we have express edition as the server)
    * username : the username for connection, that will be 'hr'
    * password : the password for connection, that will be 'hr'
    * */
    public static Connection initDb() {

        if (!instantiated) {
            System.out.println("\nInitializing Oracle Database...");
            Connection con;

            try {
                con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "hr",
                        "hr"
                );

                instantiated = true;
                System.out.println("Connection Established with Oracle Server => hostname: @localhost , PortNumber : 1521, with SID : xe");

                return con;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Connection has already been established!");
        }
        return null;
    }

}
