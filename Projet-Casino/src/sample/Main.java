package sample;

import games.Database;
import games.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage stage) throws  Exception{
        String bd = "casino";
        String userName = "root";
        String password = "Bananaslayer2!";
        Database database = new Database(bd,userName,password); //si vous voulez tester avec la votre changer les valeurs

        /*try {
            database.importSqlFile("bdcasino.sql");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        stage.setResizable(false);

        ConnexionMenuController controller = new ConnexionMenuController(stage,database);
        controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
