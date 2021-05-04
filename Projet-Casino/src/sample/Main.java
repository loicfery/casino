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

    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        User user = new User("loic","@","user");
        user.addToken(50);

        //BlackJackMenuController blackJackMenuController = new BlackJackMenuController(stage,user);
        //blackJackMenuController.setting();

        //SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage);
        //slotMachineMenuController.setting();

        //RouletteMenuController rouletteMenuController = new RouletteMenuController(user,stage);
        //rouletteMenuController.setting();

        ConnexionMenuController controller = new ConnexionMenuController(stage);
        controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
