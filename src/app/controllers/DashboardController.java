package app.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private GridPane dashboardRoot;
    @FXML
    private FlowPane itemsPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int c = 0; c < 30; c++) {

            Pane newPane = new Pane();

            Random random = new Random();
            double red = random.nextDouble(0, 1);
            double green = random.nextDouble(0, 1);
            double blue = random.nextDouble(0, 1);
            Paint color = new Color(red, green, blue, 1);

            newPane.setPrefSize(210, 160);
            newPane.setBackground(Background.fill(color));

            itemsPane.getChildren().add(newPane);
        }
    }
}
