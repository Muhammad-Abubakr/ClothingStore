package entities;

public class Brand {
    private final int brandID;
    private String brandName;

    public Brand(int brandID, String brandName) {
        this.brandID = brandID;
        this.brandName = brandName;
    }

    public int getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Brand{\n" +
                "\n\tbrandID=" + brandID +
                ",\n\tbrandName='" + brandName + '\'' +
                "\n}";
    }
}
