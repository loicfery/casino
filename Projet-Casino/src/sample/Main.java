package sample;

import games.Database;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage stage){
        String bd = "casino";
        String userName = "root";
        String password = "";
        Database database = new Database(bd,userName,password); //si vous voulez tester avec la votre changer les valeurs

        stage.setResizable(false);

        ConnexionMenuController controller = new ConnexionMenuController(stage,database);
        controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
