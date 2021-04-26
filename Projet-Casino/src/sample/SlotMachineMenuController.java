package sample;

import games.Bet;
import games.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.imageio.IIOException;

public class SlotMachineMenuController {

    private User user;
    private Bet bet = new Bet();

    @FXML
    private ImageView pictureSlot1Seven;
    @FXML
    private ImageView pictureSlot2Seven;
    @FXML
    private ImageView pictureSlot3Seven;

    @FXML
    private ImageView pictureSlot1Cherry;
    @FXML
    private ImageView pictureSlot2Cherry;
    @FXML
    private ImageView pictureSlot3Cherry;

    @FXML
    private ImageView pictureSlot1Lemon;
    @FXML
    private ImageView pictureSlot2Lemon;
    @FXML
    private ImageView pictureSlot3Lemon;

    @FXML
    private ImageView pictureSlot1Watermelon;
    @FXML
    private ImageView pictureSlot2Watermelon;
    @FXML
    private ImageView pictureSlot3Watermelon;

    @FXML
    private Label labelBet;
    @FXML
    private Label labelProfit;

    public Label labelToken;
    @FXML
    private Label labelError;

    @FXML
    private Button startingGameButton;
    @FXML
    private Button addOneToken;
    @FXML
    private Button removeOneToken;
    @FXML
    private Button addTenToken;
    @FXML
    private Button removeTenToken;
    @FXML
    private Button returnMainMenu;

    private ImageView currentPictureSlot1;
    private ImageView currentPictureSlot2;
    private ImageView currentPictureSlot3;

    public SlotMachineMenuController(User user){
        this.user = user;
    }

    public void initialize(){
        currentPictureSlot1 = pictureSlot1Seven;
        currentPictureSlot2 = pictureSlot2Seven;
        currentPictureSlot3 = pictureSlot3Seven;

        labelToken.setText("Vos jetons : "+user.getNumberOfToken());
    }

    /** Change l'image d'un des trois slots **/
    public void switchPictureSlot(int element, int slot){
        currentPictureSlot1.setVisible(false);
        currentPictureSlot2.setVisible(false);
        currentPictureSlot3.setVisible(false);

        switch (element) {
            case 1:
            case 7:
            case 10:
                if(slot == 1) {
                    currentPictureSlot1 = pictureSlot1Lemon;
                }
                if(slot == 2) {
                    currentPictureSlot2 = pictureSlot2Lemon;
                }
                if(slot == 3) {
                    currentPictureSlot3 = pictureSlot3Lemon;
                }
                break;
            case 2:
            case 4:
            case 6:
            case 8:
                if(slot == 1) {
                    currentPictureSlot1 = pictureSlot1Watermelon;
                }
                if(slot == 2) {
                    currentPictureSlot2 = pictureSlot2Watermelon;
                }
                if(slot == 3) {
                    currentPictureSlot3 = pictureSlot3Watermelon;
                }
                break;
            case 3:
            case 9:
                if(slot == 1) {
                    currentPictureSlot1 = pictureSlot1Cherry;
                }
                if(slot == 2) {
                    currentPictureSlot2 = pictureSlot2Cherry;
                }
                if(slot == 3) {
                    currentPictureSlot3 = pictureSlot3Cherry;
                }
                break;
            case 5:
                if(slot == 1) {
                    currentPictureSlot1 = pictureSlot1Seven;
                }
                if(slot == 2) {
                    currentPictureSlot2 = pictureSlot2Seven;
                }
                if(slot == 3) {
                    currentPictureSlot3 = pictureSlot3Seven;
                }
                break;
        }
        currentPictureSlot1.setVisible(true);
        currentPictureSlot2.setVisible(true);
        currentPictureSlot3.setVisible(true);
    }

    /** Lance la machine à sous **/
    public void clickStartingGame(ActionEvent actionEvent){
        //méthode à finir
        switchPictureSlot(1,1);
        switchPictureSlot(4,2);
        switchPictureSlot(2,3);

    }

    /** Ajoute un jeton à la mise **/
    public void clickAddOneToken(ActionEvent actionEvent) {
        if(user.getNumberOfToken() <= 0){
            labelError.setText("Vous n'avez pas assez de jeton");
            labelError.setVisible(true);
        }
        else{
            labelError.setVisible(false);
            user.removeToken(1);
            bet.addBet(1,user);
            labelBet.setText("Mise : "+bet.getValueOfBetTotal());
            labelToken.setText("Jetons : "+user.getNumberOfToken());
        }
    }

    /** Enlève un jeton à la mise **/
    public void clickRemoveOneToken(ActionEvent actionEvent) {
        if(bet.getValueOfBetTotal() <= 0){
            labelError.setText("La mise n'a pas assez de jeton");
            labelError.setVisible(true);
        }
        else{
            labelError.setVisible(false);
            user.addToken(1);
            bet.removeBet(1,user);
            labelBet.setText("Mise : "+bet.getValueOfBetTotal());
            labelToken.setText("Jetons : "+user.getNumberOfToken());
        }
    }

    /** Ajoute dix jeton à la mise **/
    public void clickAddTenToken(ActionEvent actionEvent) {
        if(user.getNumberOfToken() <= 9){
            labelError.setText("Vous n'avez pas assez de jeton");
            labelError.setVisible(true);
        }
        else{
            labelError.setVisible(false);
            user.removeToken(10);
            bet.addBet(10,user);
            labelBet.setText("Mise : "+bet.getValueOfBetTotal());
            labelToken.setText("Jetons : "+user.getNumberOfToken());
        }
    }

    /** Enlève dix jeton à la mise **/
    public void clickRemoveTenToken(ActionEvent actionEvent) {
        if(bet.getValueOfBetTotal() <= 9){
            labelError.setText("La mise n'a pas assez de jeton");
            labelError.setVisible(true);
        }
        else{
            labelError.setVisible(false);
            user.addToken(10);
            bet.removeBet(10,user);
            labelBet.setText("Mise : "+bet.getValueOfBetTotal());
            labelToken.setText("Jetons : "+user.getNumberOfToken());
        }
    }

    public void clickReturnMainMenu(ActionEvent actionEvent) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("mainMenuSample.fxml"));
            loader.setControllerFactory(c -> new MainMenuController(user));
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
}
