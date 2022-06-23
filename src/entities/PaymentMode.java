package entities;

public class PaymentMode {
    // attributes
    private int paymentModeId;

    // Constructor
    public PaymentMode(int paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    // getter and setter
    public int getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(int paymentModeId) {
        this.paymentModeId = paymentModeId;
    }
}
