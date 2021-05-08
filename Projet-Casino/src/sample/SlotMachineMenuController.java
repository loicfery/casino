package sample;

import games.SlotMachine;
import games.User;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.applet.AudioClip;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SlotMachineMenuController {

    private BorderPane root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setUpScene = new SetupScene();
    private User user;

    private List<Rectangle> listOfRectangleAnimate = new ArrayList<>();
    private List<FillTransition> listOfFillTransition = new ArrayList<>();

    private AudioClip soundPayout;
    private MediaPlayer soundSlot;

    private Rectangle cadreSlotMachine = new Rectangle();

    private Circle circleRule = new Circle();

    private Polygon star1;
    private Polygon star2;
    private Polygon star3;

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

    private final SlotMachine slotMachine;


    public SlotMachineMenuController(User user, Stage stage){
        this.user = user;
        this.stage = stage;

        slotMachine = new SlotMachine(user);
    }

    /** Méthode qui initialise le'interface de la machine à sous **/
    public void setting(){
        stage.setTitle("Machine à sous");
        scene = new Scene(root,800,800);
        scene.getStylesheets().add(getClass().getResource("slotMachineMenu.css").toExternalForm());
        stage.setScene(scene);

        setUpScene.setRectangle(cadreSlotMachine,105.0,275.0,199.0,601.0,5.0,5.0, Paint.valueOf("RED"),Paint.valueOf("RED"),1.0, StrokeType.INSIDE,true,anchorPane);

        animation();

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


        soundPayout = java.applet.Applet.newAudioClip(getClass().getResource("sound/slotMachinePayoutSound.wav"));
        soundSlot = new MediaPlayer(new Media(getClass().getResource("sound/slotMachineSlotsSound.mp3").toExternalForm()));
        soundSlot.setVolume(0.1);
        soundSlot.setCycleCount(Transition.INDEFINITE);

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

    /** Méthode qui change l'image d'un slot avec l'image en paramètre **/
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

    /** Méthode qui lance la machine à sous **/
    private void startingGame(){
        labelToken.setText("Jetons : "+user.getNumberOfToken());
        if(user.getNumberOfToken() > 0) {
            labelError.setVisible(false);
            slotMachine.useSlotMachine();

            startingGameButton.setDisable(true);
            soundSlot.play();
            animationSlot(slotMachine.getNbImage().get(0) + 1, slotMachine.getNbImage().get(1) + 1, slotMachine.getNbImage().get(2) + 1);

            int gain = slotMachine.verifySlot();
            labelProfit.setText("Gain : " +gain);
            if(gain > 0){
                soundPayout.play();
            }
            slotMachine.reset();
            labelToken.setText("Jetons : "+user.getNumberOfToken());
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

    private void animation(){
        int indexColor = 0;
        Color[] colors = {Color.YELLOW,Color.GREEN};
        star1 = createStarAnimate(500,40,Paint.valueOf("YELLOW"));
        star2 = createStarAnimate(300,40,Paint.valueOf("BLUE"));
        star3 = createStarAnimate(100,40,Paint.valueOf("YELLOW"));

        listOfFillTransition.add(animate(star1,Color.BLUE,Color.YELLOW));
        listOfFillTransition.add(animate(star2,Color.YELLOW,Color.BLUE));
        listOfFillTransition.add(animate(star3,Color.BLUE,Color.YELLOW));

        int layoutX = 105;
        for(int index = 0; index < 12; index ++){
            listOfRectangleAnimate.add(createRectangleAnimate(layoutX,268,50,8));
            layoutX += 50;
        }

        int layoutY = 269;
        for(int index = 0; index < 4; index ++){
            listOfRectangleAnimate.add(createRectangleAnimate(105,layoutY,8,53));
            layoutY += 53;
        }

        layoutX = 113;
        for(int index = 0; index < 12; index ++){
            listOfRectangleAnimate.add(createRectangleAnimate(layoutX,473,50,8));
            layoutX += 50;
        }

        layoutY = 268;
        for(int index = 0; index < 4; index ++){
            listOfRectangleAnimate.add(createRectangleAnimate(705,layoutY,8,52));
            layoutY += 52;
        }

        for(int index = 0; index < listOfRectangleAnimate.size(); index ++){
            listOfFillTransition.add(animate(listOfRectangleAnimate.get(index),colors[indexColor],colors[(indexColor + 1)%2]));
            indexColor = (indexColor + 1) % 2;
        }
    }

    private FillTransition animate(Shape shape, Color firstColor, Color secondColor){
        FillTransition animation = new FillTransition(Duration.millis(500),shape, firstColor,secondColor);
        animation.setCycleCount(Transition.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
        return animation;
    }

    private Rectangle createRectangleAnimate(double layoutX, double layoutY, double width, double height){
        Rectangle rectangle = new Rectangle(layoutX,layoutY,width,height);
        anchorPane.getChildren().add(rectangle);
        return rectangle;
    }

    private Polygon createStarAnimate(double layoutX, double layoutY, Paint color){
        Polygon star = new Polygon();
        star.setLayoutX(layoutX);
        star.setLayoutY(layoutY);
        star.setFill(color);
        double[] points = new double[]{10,85,85,75,110,10,135,75,210,85,160,125,170,190,110,150,50,190,60,125};
        for(int index = 0; index < points.length; index ++) {
            star.getPoints().add(points[index]);
        }
        anchorPane.getChildren().add(star);
        return star;
    }

    private void animationSlot(int symbolOne, int symbolTwo, int symbolThree){
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;
        Duration pause = Duration.seconds(0.3);
        int symbol = 1;

        for(int index = 0; index < 11; index ++) {
            int finalSymbol = symbol;
            KeyFrame keyFrame = new KeyFrame(timePoint, e -> switchPicture(finalSymbol,1));
            timeline.getKeyFrames().add(keyFrame);

            timePoint = timePoint.add(pause);
            keyFrame = new KeyFrame(timePoint, e -> switchPicture((finalSymbol + 1)%10, 2));
            timeline.getKeyFrames().add(keyFrame);

            timePoint = timePoint.add(pause);
            keyFrame = new KeyFrame(timePoint, e -> switchPicture((finalSymbol + 2)%10, 3));
            timeline.getKeyFrames().add(keyFrame);

            symbol = (symbol + 1) % 10;
        }

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> soundSlot.pause()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolOne,1)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolTwo,2)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolThree,3)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  startingGameButton.setDisable(false)));

        timeline.play();
    }
}


