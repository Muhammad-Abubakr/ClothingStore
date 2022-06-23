package entities;

import java.sql.Date;

public class Employee extends User {
    private int employeeId;
    private Date joiningDate;
    private Date leaveDate;
    private double salary;


    public Employee(int userId, User type, String cnic, String email, String password, String contactNo) {
        super(userId, type, cnic, email, password, contactNo);
    }

    // getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
