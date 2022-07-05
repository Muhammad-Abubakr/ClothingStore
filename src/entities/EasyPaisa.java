package entities;

public class EasyPaisa extends PaymentMode{
    private final String mobileNumber;

    public EasyPaisa(int c_ID, int p_ID, String mode, String mobileNumber) {
        super(c_ID, p_ID, mode);
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "EasyPaisa{\n\t" + super.toString() +
                "\n\tmobileNumber=" + mobileNumber +
                "\n}";
    }
}
