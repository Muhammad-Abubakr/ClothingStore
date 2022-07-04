package database;

import entities.Customer;
import entities.Employee;
import entities.User;

import java.sql.*;
import java.util.Vector;

// An Interface to distribute the shared Connection with the oracle database
public interface ConnectionDistributor {
    // Connection Established
    Connection con = Oracle.initDb();

    // Fields for DATASETS
    // * USERS
    Vector<User> users = new Vector<>();

    // * EMPLOYEES
    Vector<Employee> employees = new Vector<>();



    /*---------------------- User -----------------------*/
    // Parsing the user data obtained from oracle server
    static void parseUserSet(ResultSet userSet) throws SQLException {
        while (userSet.next()) {
            users.add(new User(userSet.getInt(0), userSet.getString(1), userSet.getString(2), userSet.getString(3), userSet.getString(4)));
        }
    }

    static User parseUser(ResultSet userSet) throws SQLException {
        return userSet.next() ? new User(userSet.getInt(1), userSet.getString(2), userSet.getString(3), userSet.getString(4), userSet.getString(5)) : null;
    }



    /*---------------------- Employee -----------------------*/

    static Employee getAndParseEmployee(String email) throws SQLException {
        assert con != null;

        // join the two tables to get the employees' user data as well as employees' table data
        PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLOYEE E JOIN USERS U on U.U_ID = E.U_ID WHERE EMAIL=?");
        ps.setString(1, email);

        ResultSet result = ps.executeQuery();

        if (result.next()) {
            return new Employee(result.getInt(1), result.getInt(2), result.getDate(3), result.getDate(4), result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getDouble(5));
        }

        return null;
    }

    static int updateEmployee(int user_id, String field, Object newValue) throws SQLException {
        assert con != null;
        PreparedStatement ps;

        if (field.equals("JOINING_DATE") || field.equals("LEAVE_DATE") || field.equals("SALARY")) {

            String query = "Update EMPLOYEE SET " + field + " = ? WHERE U_ID = " + user_id;
            ps = con.prepareStatement(query);

            if (field.equals("JOINING_DATE") || field.equals("LEAVE_DATE")) {
                Date newDateValue = Date.valueOf(String.valueOf(newValue));
                ps.setDate(1, newDateValue);

            } else {
                double newSalaryValue = Double.parseDouble(newValue.toString());
                ps.setDouble(1, newSalaryValue);

            }
        } else {
            String query = "Update USERS SET " + field + " = ? WHERE U_ID = " + user_id;
            ps = con.prepareStatement(query);

            String newStringData = String.valueOf(newValue);
            ps.setString(1, newStringData);
        }

        return ps.executeUpdate();
    }

    static int removeEmployee(int e_id, Date leaveDate) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("UPDATE EMPLOYEE SET LEAVE_DATE=? WHERE E_ID=?");
        ps.setDate(1, leaveDate);
        ps.setInt(2, e_id);

        return ps.executeUpdate();
    }


    /*-------------------- Aggregation -------------------*/
    static int getUsersCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM USERS");

        return result.next() ? result.getInt(1) : 0;
    }

    static int getEmployeesCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM EMPLOYEE");

        return result.next() ? result.getInt(1) : 0;
    }


    static int getCustomerCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM CUSTOMER");

        return result.next() ? result.getInt(1) : 0;
    }


    static int getItemCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM ITEM");

        return result.next() ? result.getInt(1) : 0;
    }


    /*----------------------------------------------------------------*/

    /*ADD EMPLOYEE*/
    static int insertEmployee(Employee employee) throws SQLException {
        assert con != null;

        PreparedStatement userInsert = con.prepareStatement("INSERT INTO USERS VALUES (?,?,?,?,?)");
        userInsert.setInt(1, employee.getU_id());
        userInsert.setString(2, employee.getType());
        userInsert.setString(3, employee.getCnic());
        userInsert.setString(4, employee.getPassword());
        userInsert.setString(5, employee.getEmail());

        PreparedStatement empInsert = con.prepareStatement("INSERT INTO EMPLOYEE VALUES (?,?,?,?,?)");
        empInsert.setInt(1, employee.getU_id());
        empInsert.setInt(2, employee.getE_ID());
        empInsert.setDate(3, employee.getJoiningDate());
        empInsert.setDate(4, employee.getLeaveDate());
        empInsert.setDouble(5, employee.getSalary());


        userInsert.executeUpdate();
        return empInsert.executeUpdate();
    }


    /*---------------------- CUSTOMER -----------------------*/
    static int insertCustomer(Customer customer) throws SQLException {
        assert con != null;

        PreparedStatement userInsert = con.prepareStatement("INSERT INTO USERS VALUES (?,?,?,?,?)");
        userInsert.setInt(1, customer.getU_id());
        userInsert.setString(2, customer.getType());
        userInsert.setString(3, customer.getCnic());
        userInsert.setString(4, customer.getPassword());
        userInsert.setString(5, customer.getEmail());


        PreparedStatement custInsert = con.prepareStatement("INSERT INTO CUSTOMER VALUES (?,?,?)");
        custInsert.setInt(1, customer.getU_id());
        custInsert.setInt(2, customer.getC_ID());
        custInsert.setString(3, customer.getAddress());

        userInsert.executeUpdate();
        return custInsert.executeUpdate();
    }

    /*----------------------- ITEM ------------------------*/
//    static void insertItem(Item item) throws SQLException {
//        assert con != null;
//
//        PreparedStatement userInsert = con.prepareStatement("INSERT INTO ITEM VALUES (?,?,?,?,?,?,?)");
//        userInsert.setInt(1, customer.getU_id());
//        userInsert.setString(2, customer.getType());
//        userInsert.setString(3, customer.getCnic());
//        userInsert.setString(4, customer.getPassword());
//        userInsert.setString(5, customer.getEmail());
//
//    }
}
