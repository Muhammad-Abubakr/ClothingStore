package app.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private BorderPane rightContainer;
    @FXML
    private Label loginSwitcherText;
    @FXML
    private Button loginSwitcherButton;
    @FXML
    private BorderPane root;
    @FXML
    private ImageView branding;

    Node signIn;
    Node signUp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // After initialization
        // the first node on login screen should be for sign in

        try {
            signIn = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("../screens/signIn.fxml")));
            signUp = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("../screens/signUp.fxml")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert signIn != null;
        rightContainer.setCenter(signIn);

        // Animation
        translateAnimationBranding();
    }

    @FXML
    private void sceneSwitcher(ActionEvent e) {
        if (loginSwitcherButton.getText().equalsIgnoreCase("register here")) {

            // switch to sign up
            rightContainer.getChildren().remove(signIn);
            rightContainer.setCenter(signUp);

            // change text of switchLabel and switchButton
            loginSwitcherButton.setText("Sign In");
            loginSwitcherText.setText("Already have an Account?");

        } else {

            // switch to sign in
            rightContainer.getChildren().remove(signUp);
            rightContainer.setCenter(signIn);

            // change text of switchLabel and switchButton
            loginSwitcherButton.setText("Register here");
            loginSwitcherText.setText("Don't have an Account?");

        }
    }

    private void translateAnimationBranding() {
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setNode(branding);
        translateTransition.setByY(-10);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }
}
