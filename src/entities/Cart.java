package entities;

import java.util.Vector;

public class Cart {
    private int cartNumber;
    private int quantity;
    private Vector<Item> cartItems;

    // Constructor
    public Cart(int cartNumber, int quantity) {
        this.cartNumber = cartNumber;
        this.quantity = quantity;
    }

    public Cart(int cartNumber, int quantity, Vector<Item> cartItems) {
        this.cartNumber = cartNumber;
        this.quantity = quantity;
        this.cartItems = cartItems;
    }

    // getters and setters
    public int getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(int cartNumber) {
        this.cartNumber = cartNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // methods
    public boolean addItemToCart(Item i) {
        return i != null && cartItems.add(i);
    }

    public boolean removeItemFromCart(Item i) {
        return cartItems.contains(i) && cartItems.remove(i);
    }

}
