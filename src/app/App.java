package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent loginController = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("screens/login.fxml")));

        loginController.addEventFilter(ActionEvent.ACTION, e -> {
            System.out.println(e.getEventType().getName());
        });

        Scene scene = new Scene(loginController);

        // Image Icon
        Image icon = new Image("/assets/images/ClothingStore.png");

        stage.setScene(scene);
        stage.setTitle("Clothing Store");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.toFront();
        stage.show();
    }
}
