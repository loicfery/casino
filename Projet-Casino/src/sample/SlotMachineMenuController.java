package sample;

import games.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SlotMachineMenuController {

    private BorderPane root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setUpScene = new SetupScene();
    private User user;

    private Rectangle cadreSlotMachine = new Rectangle();

    private Circle circleRule = new Circle();

    private Button startingGameButton = new Button();
    private Button returnMainMenuButton = new Button();

    private ImageView pictureSlot1 = new ImageView();
    private ImageView pictureSlot2 = new ImageView();
    private ImageView pictureSlot3 = new ImageView();

    private Label labelToken = new Label();
    private Label labelProfit = new Label();
    private Label labelUserPseudo = new Label();
    private Label labelRule = new Label();
    private Label labelError = new Label();

    private TextArea textRule = new TextArea();

    public SlotMachineMenuController(User user, Stage stage){
        this.user = user;
        this.stage = stage;
    }

    public void setting(){
        stage.setTitle("Machine à sous");
        scene = new Scene(root,800,800);
        stage.setScene(scene);

        setUpScene.setRectangle(cadreSlotMachine,105.0,275.0,199.0,601.0,5.0,5.0, Paint.valueOf("RED"),Paint.valueOf("RED"),1.0, StrokeType.INSIDE,true,anchorPane);
        setUpScene.setButton(startingGameButton,"Actionner", Pos.CENTER,504.0,615.0,134.0,256.0,new Font(25),true,anchorPane);
        setUpScene.setImageView(pictureSlot1,130.0,300.0,150.0,151.0,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()),true,anchorPane);
        setUpScene.setImageView(pictureSlot2,330,300.0,150.0,151.0,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()),true,anchorPane);
        setUpScene.setImageView(pictureSlot3,530.0,300.0,150.0,151.0,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()),true,anchorPane);
        setUpScene.setLabel(labelProfit,"Gain : 0",Pos.CENTER_LEFT,39.0,686.0,63.0,455.0,new Font(33.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelToken,"Jetons : "+user.getNumberOfToken(),Pos.CENTER_LEFT,39.0,615.0,70.0,455.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelUserPseudo,"Joueur : "+user.getPseudo(),Pos.CENTER_LEFT,39.0,565.0,50.0,463.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);
        setUpScene.setCircle(circleRule,16.0,770.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setUpScene.setLabel(labelRule,"?",Pos.CENTER,754.0,15.0,23.0,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelError,"Erreur : ",Pos.CENTER,280.0,488.0,50.0,401.0,new Font(20.0),Paint.valueOf("RED"),false,anchorPane);
        setUpScene.setTextArea(textRule,200.0,46.0,376.0,560.0,false,false,anchorPane);

        startingGameButton.setOnMouseClicked((event)->{
            startingGame();
        });

        labelRule.setOnMouseEntered((event)->{
            showRule();
        });

        labelRule.setOnMouseExited((event)->{
            hideRule();
        });

        returnMainMenuButton.setOnMouseClicked((event)->{
            returnMainMenu();
        });

        setRule();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui permet d'écrire le texte pour pouvoir l'afficher **/
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

    /** Méthode qui change l'image d'un slot d'après le symbole tiré **/
    private void switchPicture(int symbol, int slot){
        switch (symbol) {
            case 1:
            case 7:
            case 10:
                switchPictureSlot(slot, new Image(getClass().getResource("image/slot_machine_lemon.jpg").toExternalForm()));
                break;
            case 2:
            case 4:
            case 6:
            case 8:
                switchPictureSlot(slot,new Image(getClass().getResource("image/slot_machine_watermelon.jpg").toExternalForm()));
                break;
            case 3:
            case 9:
                switchPictureSlot(slot,new Image(getClass().getResource("image/slot_machine_cherry.jpg").toExternalForm()));
                break;
            case 5:
                switchPictureSlot(slot,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()));
                break;
        }
    }

    /** Change l'image d'un slot avec l'image en paramètre **/
    private void switchPictureSlot(int slot, Image newPicture){
        switch (slot) {
            case 1 :
                pictureSlot1.setImage(newPicture);
                break;
            case 2 :
                pictureSlot2.setImage(newPicture);
                break;
            case 3 :
                pictureSlot3.setImage(newPicture);
        }
    }

    /** Lance la machine à sous **/
    private void startingGame(){
        if(user.getNumberOfToken() > 0) {
            labelError.setVisible(false);
            user.removeToken(1);
            labelToken.setText("Jetons : "+user.getNumberOfToken());
            //méthode à finir
            switchPicture(1, 1);
            switchPicture(4, 2);
            switchPicture(2, 3);
        }
        else{
            labelError.setText("Vous n'avez pas assez de jeton");
            labelError.setVisible(true);
        }

    }

    /** Méthode pour retourner dans le menu principal **/
    private void returnMainMenu(){
        MainMenuController mainMenuController = new MainMenuController(stage,user);
        mainMenuController.setting();
    }

    /** Méthode qui montre les règles du jeu **/
    private void showRule() {
        textRule.setVisible(true);
    }

    /** Méthode qui cache les règles du jeu **/
    private void hideRule() {
        textRule.setVisible(false);
    }
}
