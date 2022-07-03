// importing the required Classes and Interface for establishing connection with OracleDB

import database.ConnectionDistributor;

import java.sql.SQLException;

public class Main implements ConnectionDistributor {
    public static void main(String[] args) throws SQLException {

        // Initializing Database
        assert con != null;

        // if the connecting has been established
        System.out.println(con.createStatement().executeQuery("SELECT * FROM \"User\""));

    }
}