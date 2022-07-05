package entities;

import java.sql.Date;

public class CreditCard extends PaymentMode {
    // attributes
    private final String IBAN;
    private final int CVV;
    private final Date expirationDate;

    public CreditCard(int c_ID, int p_ID, String mode, String IBAN, int CVV, Date expirationDate) {
        super(c_ID, p_ID, mode);
        this.IBAN = IBAN;
        this.CVV = CVV;
        this.expirationDate = expirationDate;
    }

    public String getIBAN() {
        return IBAN;
    }

    public int getCVV() {
        return CVV;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "CreditCard{\n\t" + super.toString() +
                "\tIBAN=" + IBAN +
                ",\n\tCVV=" + CVV +
                ",\n\texpirationDate=" + expirationDate +
                "\n}";
    }
}
