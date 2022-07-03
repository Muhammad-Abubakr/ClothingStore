package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private BorderPane itemRoot;
    @FXML
    private ImageView itemImage;
    @FXML
    private HBox buttonBar;
    @FXML
    private Label itemIDLabel;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;


    public BorderPane getItemRoot() {
        return itemRoot;
    }

    public void setItemRoot(BorderPane itemRoot) {
        this.itemRoot = itemRoot;
    }

    public ImageView getItemImage() {
        return itemImage;
    }

    public void setItemImage(ImageView itemImage) {
        this.itemImage = itemImage;
    }

    public HBox getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(HBox buttonBar) {
        this.buttonBar = buttonBar;
    }

    public Label getItemIDLabel() {
        return itemIDLabel;
    }

    public void setItemIDLabel(Label itemIDLabel) {
        this.itemIDLabel = itemIDLabel;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // setting id label colors dynamically on startup
        setIdLabelDynamicColors();

    }

    private void setIdLabelDynamicColors() {
        // dynamic itemModel background color
        Random random = new Random();

        double red = random.nextDouble(0, 1);
        double green = random.nextDouble(0, 1);
        double blue = random.nextDouble(0, 1);

        Paint color = Color.color(red, green, blue, 1);

        itemIDLabel.setBackground(Background.fill(Paint.valueOf(color.toString())));

    }
}
