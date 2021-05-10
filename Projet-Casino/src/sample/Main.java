package sample;

import games.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        User user = new User("loic","loic@email.com","ADMIN");
        user.addToken(50);

       BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage,0.3,true);
       blackJackMenuController.setting();

        //ConnexionMenuController controller = new ConnexionMenuController(stage);
        //controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
