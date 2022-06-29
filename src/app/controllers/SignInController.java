package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SignInController {

    @FXML
    private VBox signInRoot;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;




    // On Submit
    public void onSubmit(ActionEvent e) throws IOException {
        if (username.getText().equals("") || password.getText().equals(""))
            System.out.println("Username or password are null");

        else {
            System.out.printf("Username: %s \nPassword: %s\n", username.getText(), password.getText());
            username.clear();
            password.clear();
        }
    }

}
