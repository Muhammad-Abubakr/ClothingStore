package entities;

public class Item {

    // Attributes
    private final int ITM_ID;
    private int size;
    private double price;
    private String type;
    private String imagePath;


    // Constructors
    public Item(int ITM_ID, int size, double price, String type, String imagePath) {
        this.ITM_ID = ITM_ID;
        this.size = size;
        this.price = price;
        this.type = type;
        this.imagePath = imagePath;
    }

    // getters and setters
    public int getITM_ID() {
        return ITM_ID;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
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
        return "Item{\n" +
                "\titemId=" + ITM_ID +
                ",\n\tsize='" + size + '\'' +
                ",\n\tprice=" + price +
                ",\n\ttype='" + type + '\'' +
                "\n}";
    }

}
