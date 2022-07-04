package app.controllers;

import app.events.UserEvent;
import entities.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;

public class StoreController implements Initializable {

    @FXML
    private GridPane dashboardRoot;
    @FXML
    private FlowPane itemsPane;
    @FXML
    private Button logOutButton;

    private final Vector<Item> items = new Vector<>();

    private final Vector<Item> cart = new Vector<>();
    @FXML
    private Label cartLabel;
    @FXML
    private Button orderButton;

    private final Stage dialogStage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int c = 0; c < 5; c++) {
            String imagePath = "/assets/images/items/" + (c + 1) + ".jpg";
            items.add(new Item(c, 12, 200.00, "boy summer shirt", imagePath));
        }

        populateItemsPane();


    }

    // creating a method for populating the itemsPane with items
    private void populateItemsPane() {
        for (Item item : items) {

            BorderPane itemModel;
            ImageView imageContainer;
            Label itemLabel;
            Button addButton;
            Button removeButton;

            try {
                itemModel = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../models/item.fxml")));
                itemLabel = (Label) ((HBox) itemModel.getBottom()).getChildren().get(0);
                addButton = (Button) ((HBox) itemModel.getBottom()).getChildren().get(1);
                removeButton = (Button) ((HBox) itemModel.getBottom()).getChildren().get(2);
                imageContainer = (ImageView) itemModel.getTop();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            // setting item Node data
            imageContainer.setImage(new Image(item.getImagePath()));
            itemLabel.setText("id: " + item.getITM_ID());
            addButton.setId(String.valueOf(item.getITM_ID()));
            removeButton.setId(String.valueOf(item.getITM_ID()));


            // Event Handlers
            addButton.setOnAction(e -> {
                // find the item (will usually query the database)
                for (Item i :
                        items) {
                    if (i.getITM_ID() == Integer.parseInt(addButton.getId())) {

                        // add the item to the cart
                        cart.add(i);
                        cartLabel.setText(cart.size() + " items added to cart");
                        System.out.println(i);
                    }
                }
            });

            removeButton.setOnAction(e -> {
                try {
                    if (!cart.isEmpty()) for (Item i :
                            cart) {
                        if (i.getITM_ID() == Integer.parseInt(removeButton.getId())) {

                            // add the item to the cart
                            cart.remove(i);
                            cartLabel.setText(cart.size() + " items added to cart");
                            System.out.println(i);
                        }
                    }
                } catch (ConcurrentModificationException exception) {
                    System.out.println("Item Removed");
                }
            });

            itemsPane.getChildren().add(itemModel);
        }

    }

    // Action Handler for order button
    @FXML
    private void placeOrder(ActionEvent actionEvent) throws IOException {

        // Loading the Order Dialog
        Parent orderDialog = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../screens/orderDialog.fxml")));

        // Creating a scene
        Scene scene = new Scene(orderDialog);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Order Dialog");
        dialogStage.toFront(); // display at front

        // Disable logout and order buttons when order placement is in progress
        orderButton.setDisable(true);
        logOutButton.setDisable(true);


        // setting some properties and event handlers for dialog stage
        dialogStage.setOnCloseRequest(e -> {

            // Enable back the buttons for logout and order
            orderButton.setDisable(false);
            logOutButton.setDisable(false);
        });

        // Setting an alert when the parent window is being closed by the user
        alertUserOnClosingDashboardWhenOrdering();

        // setting the dialog stage to show and then wait for closing for getting the answer
        dialogStage.showAndWait();

        // when got the answer process it and check whether user wants to close the window or not
        logOutButton.getScene().getWindow().setOnCloseRequest(e -> {
            ((Stage) logOutButton.getScene().getWindow()).close();
        });

    }

    // Setting an alert when the parent window is being closed by the user
    private void alertUserOnClosingDashboardWhenOrdering() {
        logOutButton.getScene().getWindow().setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setContentText("Do you want to close the window? An order is in progress!");
            alert.setTitle("Order placement in progress");
            alert.setResizable(false);
            alert.showAndWait();

            if (!alert.getResult().getButtonData().name().equals("OK_DONE")) {
                e.consume();
            } else {
                dialogStage.close();
            }
        });
    }

    // Handling when the user logs out
    @FXML
    private void onLogOut() {

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
