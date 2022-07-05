package app.controllers;

import app.events.UserEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private Button logOutButton;
    @FXML
    private ScrollPane rightTabbedPane;
    @FXML
    private Button analyticsButton;

    @FXML
    private Button employeesButton;

    @FXML
    private Button itemButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analyticsButton.fire();
    }

    @FXML
    private void showEmployeeTab() throws IOException {
        // disabling employees button to avoid loading employee tab pane again
        employeesButton.setDisable(true);

        // check if any other tabs are disabled  if yes enable them
        if (itemButton.isDisabled()) itemButton.setDisable(false);
        if (analyticsButton.isDisabled()) analyticsButton.setDisable(false);

        // loading and adding employee tab pane
        Node employeeTabPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/employeeTab.fxml")));

        rightTabbedPane.setContent(employeeTabPane);
    }

    @FXML
    private void showItemTab() throws IOException {
        // disabling employees button to avoid loading employee tab pane again
        itemButton.setDisable(true);

        // check if any other tabs are disabled  if yes enable them
        if (employeesButton.isDisabled()) employeesButton.setDisable(false);
        if (analyticsButton.isDisabled()) analyticsButton.setDisable(false);

        // loading and adding employee tab pane
        Node itemsTab = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/itemTab.fxml")));

        rightTabbedPane.setContent(itemsTab);
    }

    @FXML
    private void showAnalyticsTab() throws IOException {
        // disabling employees button to avoid loading employee tab pane again
        analyticsButton.setDisable(true);

        // check if any other tabs are disabled  if yes enable them
        if (employeesButton.isDisabled()) employeesButton.setDisable(false);
        if (itemButton.isDisabled()) itemButton.setDisable(false);

        // loading and adding employee tab pane
        Node itemsTab = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/analyticsTab.fxml")));

        rightTabbedPane.setContent(itemsTab);
    }

    @FXML
    private void logOut() {
        // Setting the logged-in user to null to indicate no user is logged in
        logOutButton.fireEvent(new UserEvent(UserEvent.LOG_OUT, null));

        // Closing the current Stage
        Stage stage = (Stage) logOutButton.getScene().getWindow();

        // Going back to signIn stage
        Stage rollBackStage = new Stage();
        Parent loginController;

        try {
            loginController = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/login.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // creating a new scene for the stage
        assert loginController != null;

        Scene scene = new Scene(loginController);

        // Image Icon
        Image icon = new Image("/assets/images/ClothingStore.png");

        rollBackStage.setScene(scene);
        rollBackStage.setTitle("Clothing Store");
        rollBackStage.getIcons().add(icon);
        rollBackStage.setResizable(false);
        rollBackStage.setAlwaysOnTop(true);

        // Closing the dashboard stage and showing the login stage
        stage.close();
        rollBackStage.show();
    }
}
