package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignInController {

    @FXML
    private VBox signInRoot;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button signInSubmit;

    // On Submit
    public void onSubmit(ActionEvent e) throws IOException {
        if (username.getText().equals("") || password.getText().equals("")) {
            System.out.println("Username or password are null");

        } else {
            System.out.printf("Username: %s \nPassword: %s\n", username.getText(), password.getText());
            username.clear();
            password.clear();


            // Login Successful - Next Stage
            // get the login window will close it later
            Stage stage = (Stage) signInSubmit.getScene().getWindow();

            // get the new window to initialize after successful authentication
            Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/dashboard.fxml")));
            stage.close();

            // Initializing new Stage
            Stage secondStage = new Stage();
            Scene scene = new Scene(dashboard);

            secondStage.setScene(scene);
            secondStage.setTitle("Clothing Store");
            secondStage.setResizable(false);

            secondStage.show();
        }
    }

}
