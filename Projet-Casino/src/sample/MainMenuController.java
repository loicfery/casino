package sample;

import games.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.IIOException;

public class MainMenuController {

    private User user;
    private Stage stage;

    @FXML
    private Button logoutButton;
    @FXML
    private Button informationButton;
    @FXML
    private Button boutiqueButton;
    @FXML
    private Button blackJackMenuButton;
    @FXML
    private Button slotMachineMenuButton;
    @FXML
    private Button rouletteMenuButton;

    public MainMenuController(User user,Stage stage) {
        this.user = user;
        this.stage = stage;
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        loadingInterface("connexionMenuSample.fxml","",500,500,new ConnexionMenuController(stage),mouseEvent);
    }

    public void informationUser(MouseEvent mouseEvent) throws Exception{
        loadingInterface("informationMenuSample.fxml","",500,500,new InformationMenuController(user,stage),mouseEvent);
    }

    public void boutique(MouseEvent mouseEvent) {
    }

    public void blackJack(MouseEvent mouseEvent) throws Exception{
        loadingInterface("blackJackMenuSample.fxml","",800,800,new BlackJackMenuController(user,stage),mouseEvent);
    }

    public void slotMachine(MouseEvent mouseEvent) throws Exception{
        loadingInterface("slotMachineMenuSample.fxml","",800,800,new SlotMachineMenuController(user,stage),mouseEvent);
    }

    public void roulette(MouseEvent mouseEvent) throws Exception{
        loadingInterface("rouletteMenuSample.fxml","",1100,800,new RouletteMenuController(user,stage),mouseEvent);
    }

    public void loadingInterface(String nameSample, String nameCSS, int sizeX, int sizeY, Object interfaceController, MouseEvent mouseEvent) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(nameSample));
            loader.setControllerFactory(c -> interfaceController);
            Parent root = loader.load();
            Scene scene = new Scene(root, sizeX, sizeY);
            //scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }
    }
}
