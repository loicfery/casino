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
        stage.setResizable(false);
        User user = new User("loic","loic@email.com","ADMIN");
        user.addToken(50);

        //MainMenuController mainMenuController = new MainMenuController(stage,user,0.1,true);
        //mainMenuController.setting();

        //ConnexionMenuController controller = new ConnexionMenuController(stage);
        //controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
