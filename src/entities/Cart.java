package entities;

import java.util.Vector;

public class Cart {
    private int cartId;
    private int quantity;
    private Vector<Item> cartItems;

    // Constructor
    public Cart(int cartId, int quantity) {
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public Cart(int cartId, int quantity, Vector<Item> cartItems) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.cartItems = cartItems;
    }

    // getters and setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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


    // Override Methods
    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                ", cartItems=" + cartItems.toString() +
                '}';
    }
}
