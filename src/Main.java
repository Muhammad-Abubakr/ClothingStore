// importing the required Classes for establishing connection with OracleDB
import database.Oracle;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        // Initializing Database
        try(Connection con = Oracle.initDb()) {
            assert con != null;

            // if the connecting has been established
            System.out.println(con.createStatement().executeQuery("SELECT * FROM ADMIN").next());
        }

    }
}