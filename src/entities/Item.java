package entities;

public class Item {

    // Attributes
    private int itemId;
    private String size;
    private double price;
    private String type;
    private String imagePath;


    // Constructors
    public Item(int itemId, String size, double price, String type, String imagePath) {
        this.itemId = itemId;
        this.size = size;
        this.price = price;
        this.type = type;
        this.imagePath = imagePath;
    }

    // getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Override Methods
    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }

}
