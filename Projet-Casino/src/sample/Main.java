package sample;

import games.Database;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage stage) throws  Exception{
        Database database = new Database("casino","root","Poussin13!"); //base de données loic (si vous voulez tester avec la votre changer les valeurs)

        stage.setResizable(false);

        ConnexionMenuController controller = new ConnexionMenuController(stage,database);
        controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
