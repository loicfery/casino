package sample;

import games.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class SlotMachineMenuController {

    private User user;

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
    private Label bet;
    @FXML
    private Label profit;
    @FXML
    private Label token;
    @FXML
    private Label error;

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

        token.setText("Vos jetons : "+user.getNumberOfToken());
    }

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

    public void clickStartingGame(ActionEvent actionEvent){
        switchPictureSlot(1,1);
        switchPictureSlot(4,2);
        switchPictureSlot(2,3);

    }

    public void clickAddOneToken(ActionEvent actionEvent) {
        if(user.getNumberOfToken() <= 0){
            error.setText("Vous n'avez pas assez de jeton");
            error.setVisible(true);
        }
        else{
            error.setVisible(false);
            user.removeToken(1);

        }
    }
}
