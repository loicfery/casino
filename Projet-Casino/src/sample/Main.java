package sample;

import games.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        User user = new User("Loic","loic-fery@orange.fr","ADMIN"); //pour tester
        user.addToken(50); // pour tester

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("rouletteMenuSample.fxml")); //"connexionMenuSample.fxml"
        loader.setControllerFactory(c -> new RouletteMenuController(user,primaryStage));
        Parent root = loader.load();

        //Parent root = FXMLLoader.load(this.getClass().getResource("slotMachineMenuSample.fxml"));  // Autre façon de faire //

        primaryStage.setTitle("Projet Casino");
        Scene scene = new Scene(root, 1100, 800.0D); //500.0D, 500.0D --> taille à utiliser à la fin

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
