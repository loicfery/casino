package sample;

import games.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.imageio.IIOException;

public class MainMenuController {

    private User user;

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

    @FXML
    private MenuItem blackJackMenu;
    @FXML
    private MenuItem slotMachineMenu;

    public MainMenuController(User user) {
        this.user = user;
    }

    public void clickLogout(ActionEvent actionEvent) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("connexionMenuSample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 500.0D, 500.0D);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var6) {
            var6.printStackTrace();
        }

    }

    public void clickInformation(ActionEvent actionEvent) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("informationMenuSample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 500);
            //scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }
    }

    public void clickBoutique(ActionEvent actionEvent) {
    }

    public void clickBlackJack(ActionEvent actionEvent) {

    }

    public void clickSlotMachine(ActionEvent actionEvent) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("slotMachineMenuSample.fxml"));
            loader.setControllerFactory(c -> new SlotMachineMenuController(user));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 800);
            //scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }
    }

    public void clickRoulette(ActionEvent actionEvent) {

    }
}
