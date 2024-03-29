package sample;

import games.Database;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage stage){
        String bd = "projet";
        String userName = "root";
        String password = "Poussin13!";
        Database database = new Database(bd,userName,password);

        try {
            database.importSqlFile("bdcasino.sql");
            database.verifyGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stage.setResizable(false);

        ConnexionMenuController controller = new ConnexionMenuController(stage,database,new Language("language_french"),1,1);
        controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
