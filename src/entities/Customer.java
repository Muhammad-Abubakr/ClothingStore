package entities;

import java.util.Vector;

public class Customer extends User{

    private int customerId;
    private String address;
    private int frequency;
    private Vector<PaymentMode> paymentModes;

    // Constructor
    public Customer(int userId, User type, String cnic, String email, String password, String contactNo, int customerId, String address, int frequency, Vector<PaymentMode> paymentModes) {
        super(userId, type, cnic, email, password, contactNo);
        this.customerId = customerId;
        this.address = address;
        this.frequency = frequency;
        this.paymentModes = paymentModes;
    }

    // getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Vector<PaymentMode> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(Vector<PaymentMode> paymentModes) {
        this.paymentModes = paymentModes;
    }
}
