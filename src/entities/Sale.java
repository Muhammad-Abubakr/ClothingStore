package entities;

public class Sale {
    private int item_id;
    private int brand_id;
    private int order_id;
    private int quantity;
    private double discount;

    public Sale(int item_id, int brand_id, int order_id, int quantity, double discount) {
        this.item_id = item_id;
        this.brand_id = brand_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
