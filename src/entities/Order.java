package entities;


import java.sql.Date;

public class Order {
    private static int orderId;
    // Attributes
    private final int O_ID;
    private final int C_ID;
    private final int P_ID;

    private java.sql.Date dateTime;

    // Constructor
    public Order(int O_ID, int c_id, int p_id, Date dateTime) {
        C_ID = c_id;
        P_ID = p_id;
        this.O_ID = O_ID;
        this.dateTime = dateTime;
    }

    public static int getOrderId() {
        return orderId;
    }

    public static void setOrderId(int orderId) {
        Order.orderId = orderId;
    }

    public int getO_ID() {
        return O_ID;
    }

    public int getC_ID() {
        return C_ID;
    }

    public int getP_ID() {
        return P_ID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Order{\n" +
                "\tO_ID=" + O_ID +
                ",\n\tC_ID=" + C_ID +
                ",\n\tP_ID=" + P_ID +
                ",\n\tdateTime=" + dateTime +
                "\n}";
    }
}
