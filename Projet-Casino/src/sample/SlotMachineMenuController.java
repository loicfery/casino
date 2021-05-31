package sample;

import games.Database;
import games.DatabaseName;
import games.SlotMachine;
import games.User;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlotMachineMenuController implements InterfaceMenu{

    private  BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private SettingMenuController settingMenuController;
    private final LogMenuController logMenuController;
    private RuleMenuController ruleMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private final MessageInterface messageInterface = new MessageInterface();
    private Language language;

    private final List<Rectangle> listOfRectangleAnimate = new ArrayList<>();
    private final List<FillTransition> listOfFillTransition = new ArrayList<>();
    private boolean backgroundAnimation;

    private MediaPlayer soundPayout;
    private MediaPlayer soundSlot;
    private double soundVolume;

    private final Rectangle cadreSlotMachine = new Rectangle();
    private final Rectangle rectangleLog = new Rectangle();

    private final Circle circleRule = new Circle();
    private final Circle circleSetting = new Circle();

    private final Button startingGameButton = new Button();
    private final Button returnMainMenuButton = new Button();

    private final ImageView pictureSlot1 = new ImageView();
    private final ImageView pictureSlot2 = new ImageView();
    private final ImageView pictureSlot3 = new ImageView();

    private final Label labelToken = new Label();
    private final Label labelGain = new Label();
    private final Label labelUserName = new Label();
    private final Label labelRule = new Label();
    private final Label labelError = new Label();
    private final Label labelLog = new Label();

    private final SlotMachine slotMachine;

    public SlotMachineMenuController(User user, Stage stage, Database database, Language language, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        slotMachine = new SlotMachine(user);
        logMenuController = new LogMenuController();
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        ruleMenuController = new RuleMenuController(this,language);
        this.database = database;
        this.language = language;
    }

    /** Méthode qui initialise l'interface de la machine à sous **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("slotMachineMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setRectangle(cadreSlotMachine,105.0,275.0,199.0,601.0,5.0,5.0, Paint.valueOf("RED"),Paint.valueOf("RED"),1.0, StrokeType.INSIDE,true,anchorPane);

        animation();
        if(!backgroundAnimation){
            setAnimationState(false);
        }

        setupScene.setButton(startingGameButton,language.getLine("startingGameButton"), Pos.CENTER,504.0,615.0,134.0,256.0,new Font(25),true,anchorPane);
        setupScene.setImageView(pictureSlot1,130.0,300.0,150.0,151.0,new Image(new File("Projet-Casino/image/slot_machine_seven.jpg").toURI().toString()),true,anchorPane);
        setupScene.setImageView(pictureSlot2,330,300.0,150.0,151.0,new Image(new File("Projet-Casino/image/slot_machine_seven.jpg").toURI().toString()),true,anchorPane);
        setupScene.setImageView(pictureSlot3,530.0,300.0,150.0,151.0,new Image(new File("Projet-Casino/image/slot_machine_seven.jpg").toURI().toString()),true,anchorPane);
        setupScene.setLabel(labelGain,language.getLine("gainLabel") + " 0",Pos.CENTER_LEFT,39.0,686.0,63.0,455.0,new Font(33.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelToken,language.getLine("tokenLabel") + ": " + user.getToken(),Pos.CENTER_LEFT,39.0,615.0,70.0,455.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelUserName,language.getLine("playerLabel") + " " + user.getUserName(),Pos.CENTER_LEFT,39.0,565.0,50.0,463.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setButton(returnMainMenuButton,language.getLine("quitButton"),Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);

        setupScene.setCircle(circleSetting,18,670,30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("BLUE"),StrokeType.INSIDE,1.0,true,anchorPane);

        setupScene.setRectangle(rectangleLog,700.0,15.0,30.0,50.0,10.0,10.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),1.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setLabel(labelLog,language.getLine("logLabel"),Pos.CENTER,700.0,15.0,23.0,50.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setCircle(circleRule,16.0,770.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setupScene.setLabel(labelRule,"?",Pos.CENTER,754.0,15.0,23.0,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelError,"",Pos.CENTER,280.0,488.0,50.0,401.0,new Font(20.0),Paint.valueOf("RED"),false,anchorPane);

        createSoundPayout();
        createSoundSlot();

        startingGameButton.setOnMouseClicked((event)-> startingGame());
        returnMainMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        labelLog.setOnMouseClicked((event) -> goToLogMenu());
        labelRule.setOnMouseClicked((event)-> goToRuleMenu());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui change l'image d'un slot d'après le symbole tiré **/
    private void switchPicture(int symbol, int slot){
        switch (symbol) {
            case 1:
            case 7:
            case 10:
                switchPictureSlot(slot, new Image(new File("Projet-Casino/image/slot_machine_lemon.jpg").toURI().toString()));
                break;
            case 2:
            case 4:
            case 6:
            case 8:
                switchPictureSlot(slot,new Image(new File("Projet-Casino/image/slot_machine_watermelon.jpg").toURI().toString()));
                break;
            case 3:
            case 9:
                switchPictureSlot(slot,new Image(new File("Projet-Casino/image/slot_machine_cherry.jpg").toURI().toString()));
                break;
            case 5:
                switchPictureSlot(slot,new Image(new File("Projet-Casino/image/slot_machine_seven.jpg").toURI().toString()));
                break;
        }
    }

    private String getPicture(int symbol){
        switch(symbol){
            case 1:
            case 7:
            case 10:
                return language.getLine("slotMachineLemonSymbol");
            case 2:
            case 4:
            case 6:
            case 8:
               return language.getLine("slotMachineWatermelonSymbol");
            case 3:
            case 9:
               return language.getLine("slotMachineCherrySymbol");
            case 5:
                return language.getLine("slotMachineSevenSymbol");
            default: return "";
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
        if(user.getToken() > 0) {
            logMenuController.resetLog();
            logMenuController.getLog("Le joueur " + user.getUserName()+" démarre une nouvelle partie.");
            slotMachine.useSlotMachine();
            labelToken.setText(language.getLine("tokenLabel") + ": " + user.getToken());

            startingGameButton.setDisable(true);
            logMenuController.getLog("Le joueur " + user.getUserName()+" a lancé la machine à sous");
            animationSlot(slotMachine.getNbImage().get(0) + 1, slotMachine.getNbImage().get(1) + 1, slotMachine.getNbImage().get(2) + 1);
        }
        else{
            messageInterface.setMessage(labelError,language.getLine("errorLabelNotEnoughToken"),Color.RED);
        }

    }

    /** Méthode pour retourner dans le menu principal **/
    private void goToMainMenu(){
        settingMenuController.exitSettingMenu();
        logMenuController.exitLogMenu();
        ruleMenuController.exitRuleMenu();
        soundSlot.stop();
        soundPayout.stop();
        MainMenuController mainMenuController = new MainMenuController(stage,user,database,language,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    /** Méthode qui lance les animations en arrière plan **/
    private void animation(){
        int indexColor = 0;
        Color[] colors = {Color.YELLOW,Color.GREEN};
        Polygon star1 = createStarAnimate(500, Paint.valueOf("YELLOW"));
        Polygon star2 = createStarAnimate(300, Paint.valueOf("BLUE"));
        Polygon star3 = createStarAnimate(100, Paint.valueOf("YELLOW"));

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

        for (Rectangle rectangle : listOfRectangleAnimate) {
            listOfFillTransition.add(animate(rectangle, colors[indexColor], colors[(indexColor + 1) % 2]));
            indexColor = (indexColor + 1) % 2;
        }
    }

    /** Méthode qui crée une animation de changement de couleur pour une forme géometrique **/
    private FillTransition animate(Shape shape, Color firstColor, Color secondColor){
        FillTransition animation = new FillTransition(Duration.millis(500),shape, firstColor,secondColor);
        animation.setCycleCount(Transition.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
        return animation;
    }

    /** Méthode qui crée un rectangle animé **/
    private Rectangle createRectangleAnimate(double layoutX, double layoutY, double width, double height){
        Rectangle rectangle = new Rectangle(layoutX,layoutY,width,height);
        anchorPane.getChildren().add(rectangle);
        return rectangle;
    }

    /** Méthode qui crée une étoile animé **/
    private Polygon createStarAnimate(double layoutX, Paint color){
        Polygon star = new Polygon();
        star.setLayoutX(layoutX);
        star.setLayoutY(40);
        star.setFill(color);
        double[] points = new double[]{10,85,85,75,110,10,135,75,210,85,160,125,170,190,110,150,50,190,60,125};
        for (double point : points) {
            star.getPoints().add(point);
        }
        anchorPane.getChildren().add(star);
        return star;
    }

    /** Méthode qui lance l'animation de la machine à sous **/
    private void animationSlot(int symbolOne, int symbolTwo, int symbolThree){
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;
        Duration pause = Duration.seconds(0.3);
        int symbol = 1;

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> soundSlot.play()));

        for(int index = 0; index < 10; index ++) {
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

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolOne,1)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Premier symbole : " + getPicture(symbolOne))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolTwo,2)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Deuxième symbole : " + getPicture(symbolTwo))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolThree,3)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Troisième symbole : " + getPicture(symbolThree))));

        timePoint = timePoint.add(Duration.seconds(1));

        int gain = slotMachine.verifySlot();
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelGain.setText(language.getLine("gainLabel") + " " + gain)));
        if(gain > 0){
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> soundPayout.play()));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le joueur "+user.getUserName() + " a gagné " + gain + " jetons.")));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> database.insert(databaseName.getTableHistoryPartyGamed(),"\""+user.getEmail()+"\",\""+databaseName.getGameSlotMachine() + "\","+gain+",\"" + getCurrentDate()+"\"",databaseName.getTableHistoryPartyGamedColumnMailUser() + ","+databaseName.getTableHistoryPartyGamedColumnGameName() + ","+databaseName.getTableHistoryPartyGamedColumnTokenGain() + ","+databaseName.getTableHistoryPartyGamedColumnDate())));

        }
        else {
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le joueur " + user.getUserName() + " a gagné aucun jeton.")));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> database.insert(databaseName.getTableHistoryPartyGamed(),"\""+user.getEmail() + "\",\""+databaseName.getGameSlotMachine() + "\",-1,\""+getCurrentDate()+"\"",databaseName.getTableHistoryPartyGamedColumnMailUser() + ","+databaseName.getTableHistoryPartyGamedColumnGameName() + ","+databaseName.getTableHistoryPartyGamedColumnTokenGain() + ","+databaseName.getTableHistoryPartyGamedColumnDate())));

        }

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> slotMachine.reset()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelToken.setText(language.getLine("tokenLabel") + ": " + user.getToken())));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> createSoundSlot()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  startingGameButton.setDisable(false)));

        timeline.play();

    }

    /** Méthode pour modifier le volume sonore des effets sonores **/
    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
            soundSlot.setVolume(soundVolume);
            soundPayout.setVolume(soundVolume);
        }
    }

    /** Méthode pour ouvrir le menu des paramètres **/
    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    /** Méthode pour active/désactiver les animations en arrière plan **/
    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
        if(backgroundAnimation){
            setAnimationState(true);
        }
        else {
            setAnimationState(false);
        }
    }

    /** Méthode pour créer le son de la machine à sous en fonctionnement **/
    private void createSoundSlot(){
        soundSlot = new MediaPlayer(new Media(new File("Projet-Casino/sound/slotMachineSlotsSound.mp3").toURI().toString()));
        soundSlot.setVolume(soundVolume);
    }

    /** Méthode pour créer le son du paiement de la machine à sous en cas de victoire **/
    private void createSoundPayout(){
        soundPayout = new MediaPlayer(new Media(new File("Projet-Casino/sound/slotMachinePayoutSound.wav").toURI().toString()));
        soundPayout.setVolume(soundVolume);
    }

    /** Méthode pour modifier la visibilité des animations en arrière plan **/
    private void setAnimationState(boolean visible){
        if(!visible) {
            for (FillTransition fillTransition : listOfFillTransition) {
                fillTransition.pause();
            }
        }
        else {
            for (FillTransition fillTransition : listOfFillTransition) {
                fillTransition.play();
            }
        }
    }

    /** Méthode pour se dirriger l'interface du menu principal **/
    private void goToLogMenu(){
        logMenuController.exitLogMenu();
        logMenuController.setting();
    }

    /** Méthode pour ouvrir l'interface des règles de la machine à sous **/
    private void goToRuleMenu(){
        ruleMenuController.exitRuleMenu();
        ruleMenuController.setting();
    }

    /** Méthode qui retourne la date courrente sous le format yyyy-MM-dd **/
    private String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /** Méthode pour modifier le language **/
    public void setLanguage(Language language){ this.language = language; }

    /** Méthode pour rafraichir ce menu **/
    public void refresh(){
        startingGameButton.setText(language.getLine("startingGameButton"));
        labelGain.setText(language.getLine("gainLabel") + " 0");
        labelToken.setText(language.getLine("tokenLabel") + ": " + user.getToken());
        labelUserName.setText(language.getLine("playerLabel") + " " + user.getUserName());
        returnMainMenuButton.setText(language.getLine("quitButton"));
        labelLog.setText(language.getLine("logLabel"));

        ruleMenuController.setLanguage(language);
        ruleMenuController.setRule();
    }
}


