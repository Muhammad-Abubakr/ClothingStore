package entities;

import java.util.Vector;

public class Customer extends User {

    private final int C_ID;
    private String address;

    // Constructor
    public Customer(int userId, String cnic, String email, String password, String contactNo, int C_ID, String address) {
        super(userId, cnic, email, password, contactNo);
        this.C_ID = C_ID;
        this.address = address;
    }

    // getters and setters
    public int getC_ID() {
        return C_ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{\n" + super.toString() +
                "\n\tC_ID=" + C_ID +
                ",\n\taddress='" + address + '\'' +
                "\n}";
    }
}