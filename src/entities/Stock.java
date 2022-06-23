package entities;

import java.sql.Date;
import java.util.Vector;

public class Stock {

    // Attributes
    private int stockId;
    private int quantity;
    private Date purchaseDate;
    private double purchasePrice;
    private Vector<Item> items;

    // Constructors
    public Stock(int stockId, int quantity, Date purchaseDate, double purchasePrice, Vector<Item> items) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.items = items;
    }

    // getters and setters
    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
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

    public Vector<Item> getItems() {
        return items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    // Override Methods
    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                ", purchasePrice=" + purchasePrice +
                ", items=" + items +
                '}';
    }
}
