package entities;

public class User {
    // Attributes
    private final int u_id;
    private String type;
    private String cnic;
    private String email;
    private String password;

    public User(int userId, String type, String cnic, String password, String email) {
        this.u_id = userId;
        this.cnic = cnic;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    // Getters and Setters
    public int getU_id() {
        return u_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "\n\tUser{\n" +
                "\t\tu_id=" + u_id +
                ", \n\t\ttype=" + type +
                ", \n\t\tcnic='" + cnic + '\'' +
                ", \n\t\temail='" + email + '\'' +
                ", \n\t\tpassword='" + password + '\'' +
                "\n\t}";
    }


}
