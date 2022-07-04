package entities;

import java.sql.Date;
import java.util.Vector;

public class Stock {

    // Attributes
    private final int ST_ID;
    private int quantity;
    private Date purchaseDate;
    private double purchasePrice;

    // Constructors

    public Stock(int ST_ID, int quantity, Date purchaseDate, double purchasePrice) {
        this.ST_ID = ST_ID;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }

    public int getST_ID() {
        return ST_ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Stock{\n" +
                "\tST_ID=" + ST_ID +
                ",\n\tquantity=" + quantity +
                ",\n\tpurchaseDate=" + purchaseDate +
                ",\n\tpurchasePrice=" + purchasePrice +
                "\n}";
    }
}
