package database;

import app.events.UserEvent;
import entities.*;

import java.sql.*;
import java.util.Vector;

// An Interface to distribute the shared Connection with the oracle database
public interface OracleConnectionDistributer {
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

    static int removeEmployee(int e_id) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE E_ID=?");
        ps.setInt(1, e_id);

        ResultSet result = ps.executeQuery();
        result.next();
        int uid = result.getInt(1);

        PreparedStatement employeeDel = con.prepareStatement("DELETE FROM EMPLOYEE WHERE E_ID=?");
        employeeDel.setInt(1, e_id);

        PreparedStatement userDel = con.prepareStatement("DELETE FROM USERS WHERE U_ID=?");
        userDel.setInt(1, uid);

        employeeDel.executeUpdate();
        return userDel.executeUpdate();
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


    static int getStockCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM STOCK");

        return result.next() ? result.getInt(1) : 0;
    }

    static int getBrandCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM BRAND");

        return result.next() ? result.getInt(1) : 0;
    }


    static int getPaymentCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM PAYMENT_MODE");

        return result.next() ? result.getInt(1) : 0;
    }

    static int getOrderCount() throws SQLException {
        assert con != null;

        ResultSet result = con.createStatement().executeQuery("SELECT COUNT(*) FROM ORDERS");

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
    static boolean insertItem(Item item, Stock stock, Brand brand) throws SQLException {
        assert con != null;

        // At the index 4,5 we need brandID and stockID for that we must
        // have stock and brand data

//        if(!isStockPresent(stock.getPurchaseDate(), stock.getPurchasePrice())) {
        // todo write the logic to add the stock to the table
        addStockToDb(stock);
        System.out.println("stock added");
//        }
        // same is the case for brand
        if (!isBrandPresent(brand.getBrandName())) {
            // todo write the logic to add the stock to the table
            addBrandToDb(brand);
            System.out.println("Brand Added");
        } else {
            // change the brandID to already present one
            brand.setBrandID(getBrandID(brand.getBrandName()));
        }

        // --------------------------------------------------------- //
        // Before inserting a brand or stock we must check if there is already one
        // present with the same name and stock with the same purchase date and price


        PreparedStatement itemInsert = con.prepareStatement("INSERT INTO ITEM VALUES (?,?,?,?,?,?,?)");
        itemInsert.setInt(1, item.getITM_ID());
        itemInsert.setInt(2, item.getSize());
        itemInsert.setDouble(3, item.getPrice());
        itemInsert.setString(4, item.getType());
        itemInsert.setInt(5, brand.getBrandID()); // todo Brand ID
        itemInsert.setInt(6, stock.getST_ID()); // todo Stock ID
        itemInsert.setString(7, item.getImagePath());

        int result = itemInsert.executeUpdate();

        if (result == 1) {
            System.out.println("ITEM ADDED");
            return true;
        }
        ;

        return false;
    }


    static boolean isStockPresent(Date purchaseDate, Double purchasePrice) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("SELECT * FROM STOCK WHERE PURCHASE_DATE_TIME=? AND PURCHASE_PRICE=?");
        ps.setDate(1, purchaseDate);
        ps.setDouble(2, purchasePrice);

        ResultSet result = ps.executeQuery();

        return result.next();
    }

    private static void addStockToDb(Stock stock) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("INSERT INTO STOCK VALUES (?,?,?,?)");
        ps.setInt(1, stock.getST_ID());
        ps.setInt(2, stock.getQuantity());
        ps.setDate(3, stock.getPurchaseDate());
        ps.setDouble(4, stock.getPurchasePrice());

        ps.executeUpdate();
    }

    static boolean isBrandPresent(String brandName) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("SELECT * FROM BRAND WHERE BRAND_NAME=?");
        ps.setString(1, brandName);

        ResultSet result = ps.executeQuery();

        return result.next();
    }

    private static int getBrandID(String brandName) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("SELECT * FROM BRAND WHERE BRAND_NAME=?");
        ps.setString(1, brandName);

        ResultSet result = ps.executeQuery();

        result.next();
        return result.getInt(1);
    }

    private static void addBrandToDb(Brand brand) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("INSERT INTO BRAND VALUES (?,?)");
        ps.setInt(1, brand.getBrandID());
        ps.setString(2, brand.getBrandName());

        ps.executeUpdate();
    }

    // --------------------- Analytics Tab Aggregations ----------------- //
    static int getTotalSales() throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM SALES");

        ResultSet result = ps.executeQuery();
        result.next();

        return result.getInt(1);
    }

    static int getAverageSalesPrice() throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("");

        ResultSet result = ps.executeQuery();
        result.next();

        return result.getInt(1);
    }

    /*-----------------------------------------------------------*/

    /*-------------------- ORDER PLACEMENT ----------------------*/
    static void insertPaymentMode(PaymentMode paymentMode) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("INSERT INTO PAYMENT_MODE VALUES (?,?,?)");
        ps.setInt(1, paymentMode.getC_ID());
        ps.setInt(2, paymentMode.getP_ID());
        ps.setString(3, paymentMode.getMode());

        if (paymentMode.getMode().equals("Credit_Card")) {
            addCreditCard((CreditCard) paymentMode);
        } else {
            addEasyPaisa((EasyPaisa) paymentMode);
        }
    }

    private static void addCreditCard(CreditCard cc) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("INSERT INTO CREDIT_CARD VALUES  (?,?,?,?)");
        ps.setString(1, cc.getIBAN());
        ps.setInt(2, cc.getP_ID());
        ps.setInt(3, cc.getCVV());
        ps.setDate(4, cc.getExpirationDate());

        ps.executeUpdate();
    }

    private static void addEasyPaisa(EasyPaisa ep) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("INSERT INTO EASYPAISA VALUES (?,?)");
        ps.setString(1, ep.getMobileNumber());
        ps.setInt(2, ep.getP_ID());
    }

    /*----------------------- Update Customer -----------------------------*/
    static void updateCustomerOnOrder(String cnic, String mobileNumber, String address) throws SQLException {
        assert con != null;

        int uid = UserEvent.getUser().getU_id();
        int cid = ((Customer) UserEvent.getUser()).getC_ID();

        PreparedStatement userTableUpdate = con.prepareStatement("UPDATE USERS SET USERS.CNIC=? WHERE USERS.U_ID=?");
        userTableUpdate.setString(1, cnic);
        userTableUpdate.setInt(2, uid);

        userTableUpdate.executeUpdate();

        PreparedStatement customerTableUpdate = con.prepareStatement("UPDATE CUSTOMER SET CUSTOMER.ADDRESS=? AND CUSTOMER.MOBILE_NUMBER=? WHERE CUSTOMER.C_ID=?");
        customerTableUpdate.setString(1, address);
        customerTableUpdate.setString(2, mobileNumber);
        customerTableUpdate.setInt(3, cid);

        customerTableUpdate.executeUpdate();
    }

    static void insertOrder(Order order) throws SQLException {
        assert con != null;

        PreparedStatement ps = con.prepareStatement("INSERT INTO ORDERS VALUES (?,?,?,?)");
        ps.setInt(1, order.getO_ID());
        ps.setDate(2, order.getDateTime());
        ps.setInt(3, order.getC_ID());
        ps.setInt(4, order.getP_ID());

        ps.executeUpdate();
    }

    static void insertSales() throws SQLException {

    }
}
