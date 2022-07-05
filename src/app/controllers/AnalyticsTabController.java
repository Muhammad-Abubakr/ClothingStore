package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalyticsTabController implements Initializable {

    /*------------------ Sales ------------------*/
    @FXML
    private Label totalSales;
    @FXML
    private Label averageSalesPrice;
    @FXML
    private Label mostSoldItem;
    @FXML
    private Label leastSoldItem;


    /*------------------ Purchases ------------------*/
    @FXML
    private Label totalPurchased;
    @FXML
    private Label averagePurchasePrice;
    @FXML
    private Label mostPurchasedItem;
    @FXML
    private Label leastPurchasedItem;

    /*------------------ Employees ------------------*/
    @FXML
    private Label totalEmployees;
    @FXML
    private Label employeesPresent;
    @FXML
    private Label employeesLeft;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
