package app.controllers;

import app.events.UserEvent;
import database.ConnectionDistributor;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SignInController implements ConnectionDistributor {

    @FXML
    private VBox signInRoot;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private Button signInSubmit;

    // On Submit
    public void onSubmit(ActionEvent e) throws IOException, SQLException {
        if (email.getText().equals("") || password.getText().equals("")) {
            System.out.println("Username or password are null");

        } else {

            if (authenticateUser()) {
                // Login Successful - Next Stage
                // get the login window will close it later
                Stage stage = (Stage) signInSubmit.getScene().getWindow();

                // get the new window to initialize after successful authentication
                String dashboardPath = UserEvent.getUser().getType().equals("Customer") ? "../screens/store.fxml" : "../screens/adminDashboard.fxml";
                Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(dashboardPath)));

                stage.close();

                // Initializing new Stage
                Stage secondStage = new Stage();
                Scene scene = new Scene(dashboard);

                secondStage.setScene(scene);
                secondStage.setTitle("Clothing Store");
                secondStage.setResizable(false);

                secondStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Not a valid user");
                alert.setContentText("No Such User Found!");
                alert.show();
            }
        }
    }

    // Authenticator
    private boolean authenticateUser() throws SQLException {
        assert con != null;

        PreparedStatement prepareStatement = con.prepareStatement("SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=?");
        prepareStatement.setString(1, email.getText());
        prepareStatement.setString(2, password.getText());

        ResultSet result = prepareStatement.executeQuery();
        User authenticatedUser = ConnectionDistributor.parseUser(result);

        System.out.println(authenticatedUser);

        if (authenticatedUser != null) {
            signInSubmit.fireEvent(new UserEvent(UserEvent.LOGIN_SUCCESSFULL, authenticatedUser));
            return true;
        }

        return false;
    }

}
