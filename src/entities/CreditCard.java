package entities;

public class CreditCard extends PaymentMode {
    // attributes
    private String creditCardNumber;
    private int cardVerificationValue;

    // Constructors
    public CreditCard(int paymentModeId, String creditCardNumber, int cardVerificationValue) {
        super(paymentModeId);
        this.creditCardNumber = creditCardNumber;
        this.cardVerificationValue = cardVerificationValue;
    }

    // getter and setter
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCardVerificationValue() {
        return cardVerificationValue;
    }

    public void setCardVerificationValue(int cardVerificationValue) {
        this.cardVerificationValue = cardVerificationValue;
    }

    // Override Methods

    @Override
    public String toString() {
        return "CreditCard{" + super.toString() +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", cardVerificationValue=" + cardVerificationValue +
                '}';
    }
}
