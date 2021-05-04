package sample;

import games.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.imageio.IIOException;

public class InformationMenuController {

    private User user;
    private Stage stage;

    @FXML
    private Label labelPseudo;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelToken;
    @FXML
    private Label labelMoney;

    @FXML
    private Button buttonHistoricalPurchase;
    @FXML
    private Button buttonHistoricalParty;
    @FXML
    private Button returnButton;

    public InformationMenuController(User user, Stage stage){
        this.user = user;
        this.stage = stage;

        //scene.getStylesheets().add(getClass().getResource("informationMenu.css").toExternalForm());
    }

    public void initialize(){
        labelEmail.setText("Email : "+user.getEmail());
        labelPseudo.setText("Pseudo : "+user.getPseudo());
        labelToken.setText("Jetons : "+user.getNumberOfToken());
        labelMoney.setText("Argent : "+user.getAmountOfMoney());
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

    public void clickReturn(ActionEvent actionEvent) throws Exception{
       /* try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("mainMenuSample.fxml"));
            loader.setControllerFactory(c -> new MainMenuController(user,stage));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 800);
            //scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }*/
    }

    public void getUserInformation(String email){
        //récupère dans la base de donnée les informations de l'utilisateur
    }

    public void clickHistoricalPurchase(ActionEvent actionEvent) {

    }

    public void clickHistoricalParty(ActionEvent actionEvent) {

    }
}
