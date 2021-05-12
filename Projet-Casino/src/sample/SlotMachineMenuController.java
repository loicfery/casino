package sample;

import games.Database;
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

import java.util.ArrayList;
import java.util.List;

public class SlotMachineMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;
    private final LogMenuController logMenuController;
    private final RuleMenuController ruleMenuController;
    private final Database database;

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
    private final Label labelProfit = new Label();
    private final Label labelUserPseudo = new Label();
    private final Label labelRule = new Label();
    private final Label labelError = new Label();
    private final Label labelLog = new Label();

    private final SlotMachine slotMachine;

    /** Base de données **/
    private final String tableHistoryPartyGame  = "historiquepartiesjouees";
    private final String columnGameSlotMachine = "slot machine";

    public SlotMachineMenuController(User user, Stage stage, Database database, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        slotMachine = new SlotMachine(user);
        logMenuController = new LogMenuController();
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);
        ruleMenuController = new RuleMenuController(this);
        this.database = database;
    }

    /** Méthode qui initialise le'interface de la machine à sous **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("slotMachineMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setRectangle(cadreSlotMachine,105.0,275.0,199.0,601.0,5.0,5.0, Paint.valueOf("RED"),Paint.valueOf("RED"),1.0, StrokeType.INSIDE,true,anchorPane);

        animation();
        if(!backgroundAnimation){
            setAnimationState(false);
        }

        setupScene.setButton(startingGameButton,"Actionner", Pos.CENTER,504.0,615.0,134.0,256.0,new Font(25),true,anchorPane);
        setupScene.setImageView(pictureSlot1,130.0,300.0,150.0,151.0,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureSlot2,330,300.0,150.0,151.0,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureSlot3,530.0,300.0,150.0,151.0,new Image(getClass().getResource("image/slot_machine_seven.jpg").toExternalForm()),true,anchorPane);
        setupScene.setLabel(labelProfit,"Gain : 0",Pos.CENTER_LEFT,39.0,686.0,63.0,455.0,new Font(33.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelToken,"Jetons : "+user.getToken(),Pos.CENTER_LEFT,39.0,615.0,70.0,455.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelUserPseudo,"Joueur : "+user.getPseudo(),Pos.CENTER_LEFT,39.0,565.0,50.0,463.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);

        setupScene.setCircle(circleSetting,18,670,30,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())),Paint.valueOf("BLUE"),StrokeType.INSIDE,1.0,true,anchorPane);

        setupScene.setRectangle(rectangleLog,700.0,15.0,30.0,50.0,10.0,10.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),1.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setLabel(labelLog,"Log",Pos.CENTER,700.0,15.0,23.0,50.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setCircle(circleRule,16.0,770.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setupScene.setLabel(labelRule,"?",Pos.CENTER,754.0,15.0,23.0,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelError,"Erreur : ",Pos.CENTER,280.0,488.0,50.0,401.0,new Font(20.0),Paint.valueOf("RED"),false,anchorPane);

        createSoundPayout();
        createSoundSlot();

        startingGameButton.setOnMouseClicked((event)-> startingGame());
        returnMainMenuButton.setOnMouseClicked((event)-> returnMainMenu());
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

    private String getPicture(int symbol){
        switch(symbol){
            case 1:
            case 7:
            case 10:
                return "citron";
            case 2:
            case 4:
            case 6:
            case 8:
               return "pastèque";
            case 3:
            case 9:
               return "cerise";
            case 5:
                return "7";
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
        labelToken.setText("Jetons : "+user.getToken());
        if(user.getToken() > 0) {
            logMenuController.resetLog();
            logMenuController.getLog("Le joueur "+user.getPseudo()+" démarre une nouvelle partie.");
            labelError.setVisible(false);
            slotMachine.useSlotMachine();
            labelToken.setText("Jetons : "+user.getToken());

            startingGameButton.setDisable(true);
            logMenuController.getLog("Le joueur "+user.getPseudo()+" a lancé la machine à sous");
            animationSlot(slotMachine.getNbImage().get(0) + 1, slotMachine.getNbImage().get(1) + 1, slotMachine.getNbImage().get(2) + 1);
        }
        else{
            labelError.setText("Vous n'avez pas assez de jeton");
            labelError.setVisible(true);
        }

    }

    /** Méthode pour retourner dans le menu principal **/
    private void returnMainMenu(){
        settingMenuController.exitSettingMenu();
        logMenuController.exitLogMenu();
        ruleMenuController.exitRuleMenu();
        soundSlot.stop();
        soundPayout.stop();
        MainMenuController mainMenuController = new MainMenuController(stage,user,database,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

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
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Premier symbole : "+getPicture(symbolOne))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolTwo,2)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Deuxième symbole : "+getPicture(symbolTwo))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> switchPicture(symbolThree,3)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Troisième symbole : "+getPicture(symbolThree))));

        timePoint = timePoint.add(Duration.seconds(1));

        int gain = slotMachine.verifySlot();
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelProfit.setText("Gain : " +gain)));
        if(gain > 0){
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le joueur "+user.getPseudo()+" a gagné "+gain+" jetons.")));
        }
        else {
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le joueur "+user.getPseudo()+" a gagné aucun jeton.")));
        }

        if(gain > 0){
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> soundPayout.play()));
        }
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> slotMachine.reset()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> database.insert(tableHistoryPartyGame,"\""+user.getEmail()+"\","+gain+",\""+columnGameSlotMachine+"\"")));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelToken.setText("Jetons : "+user.getToken())));

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> createSoundSlot()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  startingGameButton.setDisable(false)));

        timeline.play();

    }


    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
            soundSlot.setVolume(soundVolume);
            soundPayout.setVolume(soundVolume);
        }
    }

    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
        if(backgroundAnimation){
            setAnimationState(true);
        }
        else {
            setAnimationState(false);
        }
    }

    private void createSoundSlot(){
        soundSlot = new MediaPlayer(new Media(getClass().getResource("sound/slotMachineSlotsSound.mp3").toExternalForm()));
        soundSlot.setVolume(soundVolume);
    }

    private void createSoundPayout(){
        soundPayout = new MediaPlayer(new Media(getClass().getResource("sound/slotMachinePayoutSound.wav").toExternalForm()));
        soundPayout.setVolume(soundVolume);
    }

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

    private void goToLogMenu(){
        logMenuController.exitLogMenu();
        logMenuController.setting();
    }

    private void goToRuleMenu(){
        ruleMenuController.exitRuleMenu();
        ruleMenuController.setting();
    }
}


