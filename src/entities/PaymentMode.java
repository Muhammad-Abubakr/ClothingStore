package entities;

public class PaymentMode {
    // attributes
    private final int C_ID;
    private final int P_ID;
    private String mode;

    // Constructor
    public PaymentMode(int c_ID, int p_ID, String mode) {
        C_ID = c_ID;
        P_ID = p_ID;
        this.mode = mode;
    }

    // getter and setter
    public int getC_ID() {
        return C_ID;
    }

    public int getP_ID() {
        return P_ID;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "PaymentMode{\n" +
                "\t\tC_ID=" + C_ID +
                ",\n\t\tP_ID=" + P_ID +
                ",\n\t\tmode='" + mode + '\'' +
                "\t\n}";
    }
}
