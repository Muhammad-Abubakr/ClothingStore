// importing the required Classes for establishing connection with OracleDB
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello, friend! \nSetting up Oracle Database...");
        // Commit : added jar driver and local host as data source

        // Creating a Connection Class Object
        Connection con;

        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            System.out.println("Connection Successfully Established...!");

            System.out.println("\nExecuting Query : \"SELECT * FROM EMPLOYEES\" ");
            Statement query = con.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM EMPLOYEES");

            int row = 1;
            while(result.next()) {
                System.out.println("Row " + row++ + " ==> " +
                        result.getInt(1) + ", " +
                        result.getString(2) + ", " +
                        result.getString(3) + ", " +
                        result.getString(4) + ", " +
                        result.getString(5) + ", " +
                        result.getDate(6) + " "
                        );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }





    }
}