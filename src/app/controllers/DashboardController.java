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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

            VBox itemContainer = new VBox();

            // ! must be replaced with background image
//            Random random = new Random();
//            double red = random.nextDouble(0, 1);
//            double green = random.nextDouble(0, 1);
//            double blue = random.nextDouble(0, 1);
//            Paint color = new Color(red, green, blue, 1);
            // ! end for image replacement code

            // * Image code starts here after initializing the images array
            System.out.println(item.getImagePath());
            ImageView image = new ImageView(new Image(item.getImagePath()));

            image.setFitWidth(210);
            image.setFitHeight(160);

            // should have a button here too for adding the item to cart
            Button button = new Button("Add to Cart");
            button.setId(String.valueOf(item.getItemId()));
            button.setPrefWidth(Double.MAX_VALUE);

            button.setOnAction(e -> {
                // find the item (will usually query the database)
                for (Item i :
                        items) {
                    if (i.getItemId() == Integer.parseInt(button.getId())) {

                        // add the item to the cart
                        cart.add(i);
                        cartLabel.setText(cart.size() + " items added to cart");
                        System.out.println(i);
                    }
                }
            });

            itemContainer.setPrefSize(210, 190);
            itemContainer.getChildren().add(image);
            itemContainer.getChildren().add(button);


            itemsPane.getChildren().add(itemContainer);
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
