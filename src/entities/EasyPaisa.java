package entities;

public class EasyPaisa extends PaymentMode{
    private final int mobileNumber;
    private final int P_ID;

    public EasyPaisa(int c_ID, int p_ID, String mode, int mobileNumber, int p_ID1) {
        super(c_ID, p_ID, mode);
        this.mobileNumber = mobileNumber;
        P_ID = p_ID1;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public int getP_ID() {
        return P_ID;
    }

    @Override
    public String toString() {
        return "EasyPaisa{\n" +
                "\tmobileNumber=" + mobileNumber +
                ",\n\tP_ID=" + P_ID +
                "\n}";
    }
}
