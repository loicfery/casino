package sample;

import games.Bet;
import games.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SlotMachineMenuController {

    private Stage stage;

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
    private Label labelProfit;

    public Label labelToken;
    @FXML
    private Label labelError;
    @FXML
    private Label labelUserPseudo;

    @FXML
    private Button startingGameButton;
    @FXML
    private Button returnMainMenu;

    @FXML
    private TextArea textRule;

    private ImageView currentPictureSlot1;
    private ImageView currentPictureSlot2;
    private ImageView currentPictureSlot3;

    public SlotMachineMenuController(User user,Stage stage){
        this.user = user;
        this.stage = stage;
    }

    public void initialize(){
        textRule.setEditable(false);
        setRule();

        currentPictureSlot1 = pictureSlot1Seven;
        currentPictureSlot2 = pictureSlot2Seven;
        currentPictureSlot3 = pictureSlot3Seven;

        labelUserPseudo.setText("Joueur : "+user.getPseudo());
        labelToken.setText("Vos jetons : "+user.getNumberOfToken());
    }

    private void setRule(){
        try {
            File fileRuleSlotMachine = new File("Regles-SlotMachine.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRuleSlotMachine));
            String line;

            while((line = bufferedReader.readLine()) != null){
                textRule.setText(textRule.getText()+"\n"+line);
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichier règle non trouvé");
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
    public void startingGame(MouseEvent mouseEvent){
        if(user.getNumberOfToken() > 0) {
            labelError.setVisible(false);
            user.removeToken(1);
            labelToken.setText("Jetons : "+user.getNumberOfToken());
            //méthode à finir
            switchPictureSlot(1, 1);
            switchPictureSlot(4, 2);
            switchPictureSlot(2, 3);
        }
        else{
            labelError.setText("Vous n'avez pas assez de jeton");
            labelError.setVisible(true);
        }

    }

    public void returnMainMenu(ActionEvent actionEvent) throws Exception{
        try {
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
        }
    }

    public void showRule(MouseEvent mouseEvent) {
        textRule.setVisible(true);
    }

    public void hideRule(MouseEvent mouseEvent) {
        textRule.setVisible(false);
    }
}
