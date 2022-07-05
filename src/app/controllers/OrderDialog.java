package app.controllers;

import app.events.UserEvent;
import database.OracleConnectionDistributer;
import entities.CreditCard;
import entities.Customer;
import entities.EasyPaisa;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderDialog implements Initializable, OracleConnectionDistributer {

    private final ToggleGroup toggleGroup = new ToggleGroup();
    public TextField IBAN;
    public TextField CVV;
    public TextField cnicField;
    public TextField contactNumberField;
    public TextField addressField;
    public RadioButton easyPaisaRadioButton;
    public TextField easyPaisaField;
    public RadioButton creditCardRadioButton;
    public DatePicker ccExpDate;
    public Button placeOrderButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // setting a toggle group for payment method
        easyPaisaRadioButton.setToggleGroup(toggleGroup);
        creditCardRadioButton.setToggleGroup(toggleGroup);

        toggleGroup.selectToggle(easyPaisaRadioButton);
        disableFields(creditCardRadioButton);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {

            if (easyPaisaRadioButton.isSelected()) {
                disableFields(creditCardRadioButton);
                enableFields(easyPaisaRadioButton);
            } else {
                disableFields(easyPaisaRadioButton);
                enableFields(creditCardRadioButton);
            }
        });


        /*------------------ PLACE ORDER -------------------*/
        placeOrderButton.setOnAction(e -> {
            try {
                /*Update Customer*/
                OracleConnectionDistributer.updateCustomerOnOrder(cnicField.getText(), contactNumberField.getText(), addressField.getText());

                /*Add Payment Data*/
                int P_ID = OracleConnectionDistributer.getPaymentCount();
                int C_ID = ((Customer) UserEvent.getUser()).getC_ID();

                if (easyPaisaRadioButton.isSelected()) {
                    EasyPaisa ep = new EasyPaisa(C_ID, P_ID, "EasyPaisa", easyPaisaField.getText());
                    OracleConnectionDistributer.insertPaymentMode(ep);
                } else {
                    CreditCard cc = new CreditCard(C_ID, P_ID, "Credit_Card", IBAN.getText(), Integer.parseInt(CVV.getText()), Date.valueOf(ccExpDate.getValue()));
                    OracleConnectionDistributer.insertPaymentMode(cc);
                }

                placeOrderButton.fireEvent(new UserEvent(UserEvent.PLACE_ORDER));

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void disableFields(Object o) {
        if (!o.equals(easyPaisaRadioButton)) {
            ccExpDate.setDisable(true);
            CVV.setDisable(true);
            IBAN.setDisable(true);
        } else {
            easyPaisaField.setDisable(true);
        }
    }

    private void enableFields(Object o) {
        if (!o.equals(easyPaisaRadioButton)) {
            ccExpDate.setDisable(false);
            CVV.setDisable(false);
            IBAN.setDisable(false);
        } else {
            easyPaisaField.setDisable(false);
        }
    }
}
