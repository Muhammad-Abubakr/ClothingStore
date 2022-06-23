package entities;

public class EasyPaisa extends PaymentMode{
    private String mobileNumber;
    private String IBAN;

    // Constructor
    public EasyPaisa(int paymentModeId, String mobileNumber, String IBAN) {
        super(paymentModeId);
        this.mobileNumber = mobileNumber;
        this.IBAN = IBAN;
    }

    // getter and setter
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
