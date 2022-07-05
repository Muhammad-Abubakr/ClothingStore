package app.controllers;

import database.OracleConnectionDistributer;
import entities.Brand;
import entities.Item;
import entities.Stock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemTabController implements OracleConnectionDistributer, Initializable {

    public TextField itemSize;
    public TextField itemPrice;
    public TextField imagePath;
    public RadioButton maleRadioButton;
    public RadioButton femaleRadioButton;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    public TextField stockItemQuantity;
    public TextField stockItemPrice;
    public DatePicker stockPurchaseDate;
    public TextField brandName;
    public Button save;
    public Button clear;

    // Add item to the database
    @FXML
    private void addItem() throws SQLException {

        if (itemSize.getText().isBlank() || itemPrice.getText().isBlank() || imagePath.getText().isBlank() || stockItemQuantity.getText().isBlank() || stockItemPrice.getText().isBlank() || brandName.getText().isBlank())
            emptyFieldAlert();
        else {

            final int ITM_ID = OracleConnectionDistributer.getItemCount();
            final int STOCK_ID = OracleConnectionDistributer.getStockCount();
            final int BRAND_ID = OracleConnectionDistributer.getBrandCount() - 1;

            Item item = new Item(ITM_ID, Integer.parseInt(itemSize.getText()), Double.parseDouble(itemPrice.getText()), ((RadioButton) toggleGroup.getSelectedToggle()).getText(), imagePath.getText());
            Stock stock = new Stock(STOCK_ID, Integer.parseInt(stockItemQuantity.getText()), Date.valueOf(stockPurchaseDate.getValue()), Double.parseDouble(stockItemPrice.getText()));
            Brand brand = new Brand(BRAND_ID, brandName.getText());

            // Passing all three objects to insert item
            // as insertItem API call also inserts the stock and brand table data
            // while inserting an item there's no explicit call to brand and stock insertion
            // yet
            boolean result = OracleConnectionDistributer.insertItem(item, stock, brand);

            if (result) {
                clearItemAddForm();
            }
        }
    }

    private void clearItemAddForm() {
        itemSize.clear();
        itemPrice.clear();
        toggleGroup.selectToggle(null);

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
