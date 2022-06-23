package entities;

public class User {
    // Attributes
    private int userId;
    private String cnic;
    private String email;
    private String password;
    private String contactNo;

    public User(int userId, String cnic, String email, String password, String contactNo) {
        this.userId = userId;
        this.cnic = cnic;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
    }


    // getter and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    // Override Methods
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", cnic='" + cnic + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
