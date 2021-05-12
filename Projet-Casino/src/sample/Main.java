package sample;

import games.Database;
import games.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage stage) throws  Exception{
        Database database = new Database("casino","root","Poussin13!"); //base de données loic (si vous voulez tester avec la votre changer les valeurs)

        stage.setResizable(false);

        //MainMenuController mainMenuController = new MainMenuController(stage,user,0.1,true);
        //mainMenuController.setting();

        ConnexionMenuController controller = new ConnexionMenuController(stage,database);
        controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
