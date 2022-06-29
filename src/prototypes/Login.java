package prototypes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.ToggleSwitch;
import org.controlsfx.control.textfield.TextFields;

public class Login {

    public static Node create() {

        // Root
        BorderPane root = new BorderPane();

        // Top Node
        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 36; -fx-font-family: 'MV Boli'; -fx-text-fill: #AE683F");
        title.setTextAlignment(TextAlignment.CENTER);

        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        // Left Node
        VBox leftNode = new VBox();
        leftNode.setAlignment(Pos.CENTER);
        leftNode.setPadding(new Insets(0,60,0,60));

        Image storeImage = new Image("/assets/images/ClothingStore.png", true);
        ImageView imageView = new ImageView();
        imageView.setImage(storeImage);
        leftNode.getChildren().add(imageView);

        root.setLeft(leftNode);

        // Right Node
        VBox rightNode = new VBox();
        rightNode.setPadding(new Insets(60));
        rightNode.setSpacing(20);

        TextField usernameField = TextFields.createClearableTextField();
        usernameField.setPromptText("Username");
        TextField passwordField = TextFields.createClearablePasswordField();
        passwordField.setPromptText("Password");


        Button submit = new Button("Sign In");

        rightNode.getChildren().add(0, usernameField);
        rightNode.getChildren().add(1, passwordField);
        rightNode.getChildren().add(2, submit);


        rightNode.setAlignment(Pos.CENTER);

        root.setRight(rightNode);

        // Bottom Node
        HBox bottomNode = new HBox();
        BorderPane.setMargin(bottomNode, new Insets(20, 0, 0, 0));
        bottomNode.setSpacing(20);
        bottomNode.setStyle("");

        Label signIn = new Label("Sign In");
        signIn.setStyle("-fx-font-family: 'Droid Sans'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #AE683F");
        Label signUp = new Label("Sign Up");
        signUp.setStyle("-fx-font-family: 'Droid Sans'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: gray");

        ToggleSwitch sceneSwitcher = new ToggleSwitch();
        sceneSwitcher.setPadding(new Insets(10));
        sceneSwitcher.setSelected(true);
        sceneSwitcher.setOnMouseClicked(e -> {
            if (sceneSwitcher.isSelected()) {
                signIn.setTextFill(Color.valueOf("#AE683F"));
                signUp.setTextFill(Color.GRAY);

            } else {
                signUp.setTextFill(Color.valueOf("#AE683F"));
                signIn.setTextFill(Color.GRAY);
            }
        });

        bottomNode.setAlignment(Pos.CENTER);
        bottomNode.getChildren().add(0, signUp);
        bottomNode.getChildren().add(1, sceneSwitcher);
        bottomNode.getChildren().add(2, signIn);


        bottomNode.setPadding(new Insets(20));
        root.setBottom(bottomNode);


        // Root settings
        root.setPrefSize(500, 450);
        root.setStyle("-fx-background-color: #ffffff");

        return root;
    }
}
