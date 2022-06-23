package entities;

public class Admin extends User {
    private int adminId;

    public Admin(int userId, User type, String cnic, String email, String password, String contactNo) {
        super(userId, cnic, email, password, contactNo);
    }


    // getters and setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    // Override Method
    @Override
    public String toString() {
        return "Admin{" + super.toString() +
                "adminId=" + adminId +
                '}';
    }
}

