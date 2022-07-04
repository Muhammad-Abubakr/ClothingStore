package entities;

import java.sql.Date;

public class Employee extends User {
    private final int E_ID;
    private Date joiningDate;
    private Date leaveDate;
    private double salary;


    public Employee(int U_ID, int e_ID, Date joiningDate, Date leaveDate, String type, String cnic, String password, String email, double salary) {
        super(U_ID, type, cnic, password, email);
        E_ID = e_ID;
        this.joiningDate = joiningDate;
        this.leaveDate = leaveDate;
        this.salary = salary;
    }

    // getters and setters
    public int getE_ID() {
        return E_ID;
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


    // Override Methods
    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                "\n\temployeeId=" + E_ID + ",\n" +
                "\tjoiningDate=" + joiningDate + ",\n" +
                "\tleaveDate=" + leaveDate + ",\n" +
                "\tsalary=" + salary + ",\n" +
                "}";
    }
}
