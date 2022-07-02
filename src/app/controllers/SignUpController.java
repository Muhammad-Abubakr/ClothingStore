package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
