package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private ScrollPane rightTabbedPane;
    @FXML
    private Button analyticsButton;

    @FXML
    private Button employeesButton;

    @FXML
    private Button itemButton;

    @FXML
    private Button recordButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void showEmployeeTab() throws IOException {
        // disabling employees button to avoid loading employee tab pane again
        employeesButton.setDisable(true);

        // loading and adding employee tab pane
        Node employeeTabPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/employeeTab.fxml")));

        rightTabbedPane.setContent(employeeTabPane);
    }
}
