package app.controllers;

import app.events.UserEvent;
import entities.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class DashboardController implements Initializable {

    @FXML
    private GridPane dashboardRoot;
    @FXML
    private FlowPane itemsPane;
    @FXML
    private Button logOutButton;

    private Vector<Item> items = new Vector<>();

    private Vector<Item> cart = new Vector<>();
    @FXML
    private Label cartLabel;
    @FXML
    private Button orderButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int c = 0; c < 5; c++) {
            String imagePath = "/assets/images/items/" + (c + 1) + ".jpg";
            items.add(new Item(c, "small", 200.00, "boy summer shirt", imagePath));
        }

        populateItemsPane();
    }

    // creating a method for populating the itemsPane with items
    public void populateItemsPane() {
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
            itemLabel.setText("id: " + item.getItemId());
            addButton.setId(String.valueOf(item.getItemId()));
            removeButton.setId(String.valueOf(item.getItemId()));


            // Event Handlers
            addButton.setOnAction(e -> {
                // find the item (will usually query the database)
                for (Item i :
                        items) {
                    if (i.getItemId() == Integer.parseInt(addButton.getId())) {

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
                        if (i.getItemId() == Integer.parseInt(removeButton.getId())) {

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

    // todo Action Handler for order button
    public void placeOrder() {

    }

    // Handling when the user logs out
    public void onLogOut() {

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

        // creating a new scen  e for the stage
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
