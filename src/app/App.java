package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();

        Node login = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("screens/login.fxml")));

        root.getChildren().add(0,login);

        Scene scene = new Scene(root);

        // Image Icon
        Image icon = new Image("/assets/images/ClothingStore.png");

        root.autosize();
        stage.setScene(scene);
        stage.setTitle("Clothing Store");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
