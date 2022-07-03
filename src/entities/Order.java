package entities;

import java.sql.Date;
import java.util.Vector;

public class Order {
    private static int orderId;
    // Attributes
    private int orderNumber;
    private String description;
    private Date date;
    private Vector<Item> orderedItems;

    // Constructor
    public Order(int orderNumber, String description, Date date, Vector<Item> orderedItems) {
        this.orderNumber = orderNumber;
        this.description = description;
        this.date = date;
        this.orderedItems = orderedItems;
    }

    // getter and setter
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vector<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Vector<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    // Override Methods
    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", orderedItems=" + orderedItems.toString() +
                '}';
    }
}
