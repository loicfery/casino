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

        SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage,0.5);
        slotMachineMenuController.setting();

        //ConnexionMenuController controller = new ConnexionMenuController(stage);
        //controller.setting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
