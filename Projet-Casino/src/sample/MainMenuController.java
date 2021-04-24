package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.imageio.IIOException;

public class MainMenuController {
    public Button logoutButton;
    public Button informationButton;
    public Button boutiqueButton;
    public MenuItem blackJackMenu;
    public MenuItem slotMachineMenu;
    public MenuItem rouletteMenu;

    public MainMenuController() {
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
        switchInformationMenu(actionEvent);
    }

    public void clickBoutique(ActionEvent actionEvent) {
    }

    public void clickBlackJack(ActionEvent actionEvent) {
        System.out.println("test");
    }

    public void switchInformationMenu(ActionEvent actionEvent) throws Exception {
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

    public void clickSlotMachine(ActionEvent actionEvent) {

    }

    public void clickRoulette(ActionEvent actionEvent) {

    }
}
