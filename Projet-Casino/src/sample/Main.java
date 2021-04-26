package sample;

import games.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        User user = new User("Loic","loic-fery@orange.fr","ADMIN"); //pour tester
        user.addToken(100); // pour tester

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("slotMachineMenuSample.fxml"));//"connexionMenuSample.fxml"
        loader.setControllerFactory(c -> new SlotMachineMenuController(user));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(this.getClass().getResource("slotMachineMenuSample.fxml"));  // Autre façon de faire //

        primaryStage.setTitle("Projet Casino");
        primaryStage.setScene(new Scene(root, 800.0D, 800.0D)); //500.0D, 500.0D --> taille à utiliser à la fin
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
