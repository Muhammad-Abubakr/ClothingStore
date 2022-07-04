package entities;

import java.sql.Date;

public class CreditCard extends PaymentMode {
    // attributes
    private final int IBAN;
    private final int P_ID;
    private final int CVV;
    private final Date expirationDate;

    public CreditCard(int c_ID, int p_ID, String mode, int IBAN, int p_ID1, int CVV, Date expirationDate) {
        super(c_ID, p_ID, mode);
        this.IBAN = IBAN;
        P_ID = p_ID1;
        this.CVV = CVV;
        this.expirationDate = expirationDate;
    }

    public int getIBAN() {
        return IBAN;
    }

    @Override
    public int getP_ID() {
        return P_ID;
    }

    public int getCVV() {
        return CVV;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "CreditCard{\n" +
                "\tIBAN=" + IBAN +
                ",\n\tP_ID=" + P_ID +
                ",\n\tCVV=" + CVV +
                ",\n\texpirationDate=" + expirationDate +
                "\n}";
    }
}
