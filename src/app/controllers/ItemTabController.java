package app.controllers;

import database.ConnectionDistributor;
import entities.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemTabController implements ConnectionDistributor, Initializable {

    public TextField itemSize;
    public TextField itemPrice;
    public TextField imagePath;
    public RadioButton maleRadioButton;
    public RadioButton femaleRadioButton;
    private ToggleGroup toggleGroup = new ToggleGroup();
    public TextField itemQuantity;
    public DatePicker purchaseDate;
    public TextField brandName;
    public Button save;
    public Button clear;

    // Add item to the database
    @FXML
    private void addItem() throws SQLException{

        if (itemSize.getText().isBlank() || itemPrice.getText().isBlank() || imagePath.getText().isBlank() || itemQuantity.getText().isBlank() || brandName.getText().isBlank()) emptyFieldAlert();
        else {

            final int ITM_ID = ConnectionDistributor.getItemCount();


            Item item = new Item(ITM_ID, Integer.parseInt(itemSize.getText()), Double.parseDouble(itemPrice.getText()), toggleGroup.getSelectedToggle().selectedProperty().getName(), imagePath.getText());

//            ConnectionDistributor.insertItem(item);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupGenderTypes();
    }

    private void groupGenderTypes() {
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
    }
}
