package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("slotMachineMenuSample.fxml")); //"connexionMenuSample.fxml"
        primaryStage.setTitle("Projet Casino");
        primaryStage.setScene(new Scene(root, 800.0D, 800.0D)); //500.0D, 500.0D
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
