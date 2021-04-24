package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.imageio.IIOException;

public class InformationMenuController {

    public Button returnButton;
    public Label pseudo;
    public Label email;
    public Label token;
    public Label money;
    public Button historicalPurchaseButton;
    public Button historicalPartyButton;

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

    public void clickReturn(ActionEvent actionEvent) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("mainMenuSample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 500.0D, 500.0D);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var6) {
            var6.printStackTrace();
        }
    }

    public void getUserInformation(String email){
        //récupère dans la base de donnée les informations de l'utilisateur
    }

    public void clickHistoricalPurchase(ActionEvent actionEvent) {

    }

    public void clickHistoricalParty(ActionEvent actionEvent) {

    }
}
