package app.controllers;

import database.OracleConnectionDistributer;
import entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class SignUpController implements OracleConnectionDistributer {

    // Root of SignUp
    @FXML
    private VBox signUpRoot;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private Button signUpSubmit;


    @FXML
    private void addCustomer() throws SQLException {

        if (username.getText().isBlank() || email.getText().isBlank() || password.getText().isBlank() || confirmPassword.getText().isBlank()) emptyFieldAlert();
        else if (!password.getText().equals(confirmPassword.getText())) passwordsMismatchAlert();
        else {

            final int uid = OracleConnectionDistributer.getUsersCount();
            final int cid = OracleConnectionDistributer.getCustomerCount();

            Customer cust = new Customer(uid, null, email.getText(), password.getText(), null, cid, null);

            OracleConnectionDistributer.insertCustomer(cust);
        }
    }


    // Empty field alert
    private void emptyFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Please fill all the fields");
        alert.setTitle("Empty Required Field");
        alert.setContentText("All fields are required and cannot be EMPTY!");
        alert.show();
    }

    private void passwordsMismatchAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Your passwords don't match");
        alert.setTitle("Passwords Mismatch");
        alert.setContentText("The passwords must match!");
        alert.show();
    }
}
