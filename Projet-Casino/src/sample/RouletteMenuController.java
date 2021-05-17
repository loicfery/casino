package sample;

import games.Database;
import games.Roulette;
import games.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class RouletteMenuController implements InterfaceMenu{

    private final int ORIGIN_Y_1 = 78;
    private final int END_Y_1 = 222;
    private final int ORIGIN_X_TOKEN = 336;
    private final int ORIGIN_Y_TOKEN = 262;

    private int tokenUsed = 0;
    private int indexTokenRemove = 0;
    private boolean startingGame = false;
    private final List<Circle> listOfCircleToken = new ArrayList<>();
    private final List<Label> listLabelToken = new ArrayList<>();
    private final List<Case> listOfCaseBet = new ArrayList<>();
    private final List<InformationTokenBet> listOfTokenUsed = new ArrayList<>();
    private final List<Case> listOfCaseRoulette = new ArrayList<>();

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;
    private final LogMenuController logMenuController;
    private final RuleMenuController ruleMenuController;
    private final Database database;
    private final MessageInterface messageInterface = new MessageInterface();

    private final Roulette roulette;

    private final List<Rectangle> listOfRectangleGameBoard = new ArrayList<>();
    private final List<Label> listOfLabelGameBoard = new ArrayList<>();
    private double soundVolume;
    private boolean backgroundAnimation;

    private MediaPlayer tokenSound;
    private MediaPlayer rouletteSound;

    private final ImageView pictureRoulette = new ImageView();

    private final Label labelProfit = new Label();
    private final Label labelTokenUser = new Label();
    private final Label labelPseudo = new Label();
    private final Label labelInformationBetToken = new Label();
    private final Label labelError = new Label();
    private final Label labelRule = new Label();
    private final Label labelLog = new Label();

    private final Button modifyBetTokenButton = new Button();
    private final Button validBetTokenButton = new Button();
    private final Button startingGameButton = new Button();
    private final Button returnMainMenuButton = new Button();

    private final TextField textBetToken = new TextField();

    private final Circle circleBallRoulette = new Circle();
    private final Circle circleRule = new Circle();
    private final Circle circleSetting = new Circle();

    private final Rectangle rectangleLog = new Rectangle();
    private final Rectangle rectangleInformationBet = new Rectangle();


    public RouletteMenuController(User user, Stage stage, Database database, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        logMenuController = new LogMenuController();
        settingMenuController = new SettingMenuController(this,soundVolume,backgroundAnimation);
        ruleMenuController = new RuleMenuController(this);
        this.database = database;
        roulette = new Roulette();
    }

    /** Méthode qui initialise l'interface de la roulette **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("rouletteMenu.css").toExternalForm());
        stage.setScene(scene);

        setupGameBoard();
        setCasePosition();
        setCasePositionRoulette();

        setupScene.setImageView(pictureRoulette,638.0,350.0,425.0,471.0, new Image(getClass().getResource("image/roulette.jpg").toExternalForm()),true,anchorPane);
        pictureRoulette.setPickOnBounds(true);
        pictureRoulette.setPreserveRatio(true);

        setupScene.setLabel(labelProfit,"Gain : 0",Pos.CENTER_LEFT,14.0,718.0,68.0,607.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelTokenUser,"Jetons : "+user.getToken(),Pos.CENTER_LEFT,14.0,640.0,68.0,613.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPseudo,"Joueur : "+user.getPseudo(),Pos.CENTER_LEFT,14.0,563.0,68.0,613.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setRectangle(rectangleInformationBet,212,368,202,387,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        setupScene.setLabel(labelInformationBetToken,"Information",Pos.CENTER,212.0,368.0,202.0,387.0,new Font(18),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setButton(modifyBetTokenButton,"Miser",Pos.CENTER,295.0,696.0,68.0,216.0,new Font(20.0),false,anchorPane);
        setupScene.setButton(validBetTokenButton,"Miser",Pos.CENTER,295.0,696.0,68.0,216.0,new Font(20.0),false,anchorPane);
        setupScene.setTextField(textBetToken,"Mise",Pos.CENTER,234.0,597.0,80.0,338.0,new Font(20.0),false,anchorPane);
        setupScene.setButton(startingGameButton,"Starting",Pos.CENTER,25.0,416.0,102.0,251.0,new Font(20.0),true,anchorPane);
        setupScene.setLabel(labelError,"Erreur",Pos.CENTER,197.0,600.0,60.0,387.0,new Font(20.0),Paint.valueOf("RED"),false,anchorPane);
        setupScene.setCircle(circleBallRoulette,7.0,950,475,Paint.valueOf("BLACK"),Paint.valueOf("BLACK"),StrokeType.INSIDE,0.0,false,anchorPane);
        setupScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);

        setupScene.setCircle(circleSetting,18,970,30,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())),Paint.valueOf("GREEN"),StrokeType.INSIDE,1.0,true,anchorPane);

        setupScene.setRectangle(rectangleLog,1000.0,15.0,30.0,50.0,10.0,10.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),1.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setLabel(labelLog,"Log",Pos.CENTER,1000.0,15.0,23.0,50.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setCircle(circleRule,16.0,1070.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setupScene.setLabel(labelRule,"?",Pos.CENTER,1055.0,15.0,23,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);

        createSoundToken();
        createSoundRoulette();

        anchorPane.setOnMouseClicked((event)-> choosePositionToken(event));
        validBetTokenButton.setOnMouseClicked((event)-> validBetToken());
        modifyBetTokenButton.setOnMouseClicked((event)-> modifyBetToken());
        startingGameButton.setOnMouseClicked((event)-> startingGame());
        returnMainMenuButton.setOnMouseClicked((event)-> returnMainMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        labelLog.setOnMouseClicked((event) -> goToLogMenu());
        labelRule.setOnMouseClicked((event)-> goToRuleMenu());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui crée la grille de mise **/
    private void setupGameBoard(){
        Rectangle rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,434.0,78.0,48.0,51.0,5.0,5.0, Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0, StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,485.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,536.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,587.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,638.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,689.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,740.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,791.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,842.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,893.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,944.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,995.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,383.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,434.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,485.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 536.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,587.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,638.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,689.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 740.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,791.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,842.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,893.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,944.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 995.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 383.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,434.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,536.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,485.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,587.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,638.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,689.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,740.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,791.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 842.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,893.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,944.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,995.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,383.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,383.0,222.0,48.0,204.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,587.0,222.0,48.0,204.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,791.0,222.0,48.0,204.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,383.0,270.0,48.0,100.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,481.0,270.0,48.0,106.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,587.0,270.0,48.0,100.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,685.0,270.0,48.0,106.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,791.0,270.0,48.0,100.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 889.0,270.0,48.0,106.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,319.0,78.0,145.0,64.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);


        Label label = new Label();
        setupScene.setLabel(label,"3", Pos.CENTER,383.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"2", Pos.CENTER,383.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"1", Pos.CENTER,383.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"4", Pos.CENTER,434.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"5", Pos.CENTER,434.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"6", Pos.CENTER,434.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"7", Pos.CENTER,485.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"8", Pos.CENTER,485.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"9", Pos.CENTER,485.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"10", Pos.CENTER,536.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"11", Pos.CENTER,536.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"12", Pos.CENTER,536.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"13", Pos.CENTER,587.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"14", Pos.CENTER,587.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"15", Pos.CENTER,587.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"16", Pos.CENTER,638.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"17", Pos.CENTER,638.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"18", Pos.CENTER,638.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"19", Pos.CENTER,689.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"20", Pos.CENTER,689.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"21", Pos.CENTER,689.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"22", Pos.CENTER,740.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"23", Pos.CENTER,740.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"24", Pos.CENTER,740.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"25", Pos.CENTER,791.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"26", Pos.CENTER,791.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"27", Pos.CENTER,791.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"28", Pos.CENTER,842.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"29", Pos.CENTER,842.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"30", Pos.CENTER,842.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"31", Pos.CENTER,893.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"32", Pos.CENTER,893.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"33", Pos.CENTER,893.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"34", Pos.CENTER,944.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"35", Pos.CENTER,944.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"36", Pos.CENTER,944.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"3rd", Pos.CENTER,995.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"2nd", Pos.CENTER,995.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"1st", Pos.CENTER,995.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"1 - 12", Pos.CENTER,383.0,222.0,48.0,204.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"13 - 24", Pos.CENTER,587.0,222.0,48.0,204.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"25 - 36", Pos.CENTER,791.0,222.0,48.0,204.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"1 - 18", Pos.CENTER,383.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"PAIRE", Pos.CENTER,484.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"ROUGE", Pos.CENTER,587.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"NOIR", Pos.CENTER,688.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"IMPAIRE", Pos.CENTER,791.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"19 - 36", Pos.CENTER,892.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setupScene.setLabel(label,"0", Pos.CENTER,320.0,78.0,145.0,64.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);
    }

    /** Méthode de création de la liste des cases pour parier **/
    private void setCasePosition(){
        listOfCaseBet.add(new Case(320,78,384,223,"green","0"));
        listOfCaseBet.add(new Case(383,174,434,222,"red","1"));
        listOfCaseBet.add(new Case(383,126,434,174,"black","2"));
        listOfCaseBet.add(new Case(383,78,434,126,"red","3"));
        listOfCaseBet.add(new Case(434,174,485,222,"black","4"));
        listOfCaseBet.add(new Case(434,126,485,174,"red","5"));
        listOfCaseBet.add(new Case(434,78,485,126,"black","6"));
        listOfCaseBet.add(new Case(485,174,536,222,"red","7"));
        listOfCaseBet.add(new Case(485,126,536,174,"black","8"));
        listOfCaseBet.add(new Case(485,78,536,126,"red","9"));
        listOfCaseBet.add(new Case(536,174,587,222,"black","10"));
        listOfCaseBet.add(new Case(536,126,587,174,"black","11"));
        listOfCaseBet.add(new Case(536,78,587,126,"red","12"));
        listOfCaseBet.add(new Case(587,174,638,222,"black","13"));
        listOfCaseBet.add(new Case(587,126,638,174,"red","14"));
        listOfCaseBet.add(new Case(587,78,638,126,"black","15"));
        listOfCaseBet.add(new Case(638,174,689,222,"red","16"));
        listOfCaseBet.add(new Case(638,126,689,174,"black","17"));
        listOfCaseBet.add(new Case(638,78,689,126,"red","18"));
        listOfCaseBet.add(new Case(689,174,740,222,"red","19"));
        listOfCaseBet.add(new Case(689,126,740,174,"black","20"));
        listOfCaseBet.add(new Case(689,78,740,126,"red","21"));
        listOfCaseBet.add(new Case(740,174,791,222,"black","22"));
        listOfCaseBet.add(new Case(740,126,791,174,"red","23"));
        listOfCaseBet.add(new Case(740,78,791,126,"black","24"));
        listOfCaseBet.add(new Case(791,174,842,222,"red","25"));
        listOfCaseBet.add(new Case(791,126,842,174,"black","26"));
        listOfCaseBet.add(new Case(791,78,842,126,"red","27"));
        listOfCaseBet.add(new Case(842,174,893,222,"black","28"));
        listOfCaseBet.add(new Case(842,126,893,174,"black","29"));
        listOfCaseBet.add(new Case(842,78,893,126,"red","30"));
        listOfCaseBet.add(new Case(893,174,944,222,"black","31"));
        listOfCaseBet.add(new Case(893,126,944,174,"red","32"));
        listOfCaseBet.add(new Case(893,78,944,126,"black","33"));
        listOfCaseBet.add(new Case(944,174,995,222,"red","34"));
        listOfCaseBet.add(new Case(944,126,995,174,"black","35"));
        listOfCaseBet.add(new Case(944,78,995,126,"red","36"));
        listOfCaseBet.add(new Case(995,174,1046,222,"green","1st"));
        listOfCaseBet.add(new Case(995,126,1046,174,"green","2nd"));
        listOfCaseBet.add(new Case(995,78,1046,126,"green","3rd"));
        listOfCaseBet.add(new Case(383,222,587,270,"green","1-12"));
        listOfCaseBet.add(new Case(383,270,483,318,"green","1-18"));
        listOfCaseBet.add(new Case(484,270,584,318,"green","even"));
        listOfCaseBet.add(new Case(587,222,791,270,"green","13-24"));
        listOfCaseBet.add(new Case(587,270,687,718,"green","red"));
        listOfCaseBet.add(new Case(688,270,788,718,"green","black"));
        listOfCaseBet.add(new Case(791,222,995,270,"green","25-36"));
        listOfCaseBet.add(new Case(791,270,891,318,"green","odd"));
        listOfCaseBet.add(new Case(892,270,992,318,"green","19-36"));
    }

    /** Méthode de création de la liste des position des cases de la roulette **/
    private void setCasePositionRoulette(){
        listOfCaseRoulette.add(new Case(950,475,0,0,"red","21"));
        listOfCaseRoulette.add(new Case(963,493,0,0,"black","2"));
        listOfCaseRoulette.add(new Case(972,513,0,0,"red","25"));
        listOfCaseRoulette.add(new Case(979,535,0,0,"black","17"));
        listOfCaseRoulette.add(new Case(982,557,0,0,"red","34"));
        listOfCaseRoulette.add(new Case(982,579,0,0,"black","6"));
        listOfCaseRoulette.add(new Case(976,600,0,0,"red","27"));
        listOfCaseRoulette.add(new Case(967,620,0,0,"black","13"));
        listOfCaseRoulette.add(new Case(957,641,0,0,"red","36"));
        listOfCaseRoulette.add(new Case(942,658,0,0,"black","11"));
        listOfCaseRoulette.add(new Case(925,670,0,0,"red","30"));
        listOfCaseRoulette.add(new Case(906,680,0,0,"black","8"));
        listOfCaseRoulette.add(new Case(885,691,0,0,"red","23"));
        listOfCaseRoulette.add(new Case(861,694,0,0,"black","10"));
        listOfCaseRoulette.add(new Case(841,693,0,0,"red","5"));
        listOfCaseRoulette.add(new Case(816,694,0,0,"black","24"));
        listOfCaseRoulette.add(new Case(794,686,0,0,"red","16"));
        listOfCaseRoulette.add(new Case(775,674,0,0,"black","33"));
        listOfCaseRoulette.add(new Case(757,660,0,0,"red","1"));
        listOfCaseRoulette.add(new Case(742,645,0,0,"black","20"));
        listOfCaseRoulette.add(new Case(729,624,0,0,"red","14"));
        listOfCaseRoulette.add(new Case(718,603,0,0,"black","31"));
        listOfCaseRoulette.add(new Case(714,580,0,0,"red","9"));
        listOfCaseRoulette.add(new Case(716,556,0,0,"black","22"));
        listOfCaseRoulette.add(new Case(715,533,0,0,"red","18"));
        listOfCaseRoulette.add(new Case(722,512,0,0,"black","29"));
        listOfCaseRoulette.add(new Case(734,492,0,0,"red","7"));
        listOfCaseRoulette.add(new Case(745,469,0,0,"black","28"));
        listOfCaseRoulette.add(new Case(765,454,0,0,"red","12"));
        listOfCaseRoulette.add(new Case(786,443,0,0,"black","35"));
        listOfCaseRoulette.add(new Case(807,433,0,0,"red","3"));
        listOfCaseRoulette.add(new Case(829,426,0,0,"black","26"));
        listOfCaseRoulette.add(new Case(850,425,0,0,"green","0"));
        listOfCaseRoulette.add(new Case(845,428,0,0,"red","32"));
        listOfCaseRoulette.add(new Case(898,433,0,0,"black","15"));
        listOfCaseRoulette.add(new Case(920,441,0,0,"red","19"));
        listOfCaseRoulette.add(new Case(938,455,0,0,"black","4"));
    }

    /** Méthode d'action pour poser un jeton ou modifier sa position ou sa valeur **/
    private void choosePositionToken(MouseEvent mouseEvent) {
        if(!startingGame) {
            labelError.setVisible(false);
            int mousePositionX = (int) (mouseEvent.getX());
            int mousePositionY = (int) (mouseEvent.getY());

            if (mouseEvent.getButton() == MouseButton.PRIMARY) { //clique gauche
                if (isPositionMouseGood(mousePositionX, mousePositionY)) {
                    createToken();
                    startingGameButton.setVisible(false);
                    if(getTokenToRemove(mousePositionX,mousePositionY) == -1) {
                        setPositionToken(mousePositionX, mousePositionY, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), true);
                        betToken(mousePositionX, mousePositionY, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed));
                    }
                    else {
                        labelInformationBetToken.setVisible(false);
                        rectangleInformationBet.setVisible(false);
                        textBetToken.setVisible(false);
                        messageInterface.setMessage(labelError,"Vous ne pouvez pas placer de jeton ici", Color.RED);
                    }
                }
            }
            if (mouseEvent.getButton() == MouseButton.SECONDARY) { //clique droit
                startingGameButton.setVisible(false);
                indexTokenRemove = getTokenToRemove(mousePositionX, mousePositionY);
                if (indexTokenRemove > -1) {
                    if (tokenUsed > 0) {
                        tokenUsed--;
                    }

                    labelInformationBetToken.setText("Mise d'un jeton  \n" + "Cases sélectionnées : \n" + listOfTokenUsed.get(indexTokenRemove).getCases() + "\n Cases misées : \n" + getCasesBet(listOfTokenUsed.get(indexTokenRemove).getListOfCaseToken(), listOfTokenUsed.get(indexTokenRemove).getCircleToken())); //recup combinaison avec methode
                    textBetToken.setText(listOfTokenUsed.get(indexTokenRemove).getValueOfBet());
                    textBetToken.setVisible(true);
                    rectangleInformationBet.setVisible(false);
                    labelInformationBetToken.setVisible(true);
                    modifyBetTokenButton.setVisible(true);
                }
            }
        }
    }

    /** Méthode qui returne les cases de la combinaison à partir de la position du jeton **/
    private String getCasesBet(List<Case> listOfCaseToken, Circle circleToken){
        if(listOfCaseToken.size() == 0){
            return "aucune case sélectionnée";
        }
        if(listOfCaseToken.size() == 1){
            switch(listOfCaseToken.get(0).getValueCase()){
                case "1st" : return "1;4;7;10;13;16;19;22;25;28;31;34";
                case "2nd" : return "2;5;8;11;14;17;20;23;26;29;32;35";
                case "3rd" : return "3;6;9;12;15;18;21;24;27;30;33;36";
                case "red" : return "1;3;5;7;9;12;14;16;18;21;23;25;27;30;32;34;36";
                case "black" : return "2;4;6;8;10;11;13;15;17;20;22;24;26;28;29;31;33;35";
                case "1-12" : return "de 1 à 12";
                case "13-24" : return "de 13 à 24";
                case "25-36" : return "de 25 à 36";
                case "even" : return "2;4;6;8;10;12;14;16;19;20;22;24;26;28;30;32;34;36";
                case "odd" : return "1;3;5;7;9;11;13;15;17;19;21;23;25;27;29;31;33;35";
                case "1-18" : return "de 1 à 18";
                case "19-36" : return "de 19 à 36";
                default:
                    if(( circleToken.getLayoutY() - circleToken.getRadius()) <= ORIGIN_Y_1){
                        String listCase = "";
                        for(Case cases : getCombinationCase(listOfCaseToken.get(0))){
                            listCase = listCase + cases.getValueCase() + ";";
                        }
                        return listCase.substring(0,listCase.length() - 1);
                    }
                    return listOfCaseToken.get(0).getValueCase();
            }
        }
        if(listOfCaseToken.size() == 2){
            switch (listOfCaseToken.get(0).getValueCase()){
                case "1" :
                case "4" :
                case "7" :
                case "10" :
                case "13" :
                case "16" :
                case "19" :
                case "22" :
                case "25" :
                case "28" :
                case "31" :
                case "34" :
                    if((circleToken.getLayoutY() + circleToken.getRadius()) >= END_Y_1){
                        String listCase = "";
                        for(Case cases : getCombinationCase(listOfCaseToken.get(0))){
                            listCase = listCase + cases.getValueCase() + ";";
                        }
                        return listCase.substring(0,listCase.length() - 1);
                    }
                    else {
                        if(listOfCaseToken.get(1).getValueCase().equals("1st")){
                            return "1;4;7;10;13;16;19;22;25;28;31;34";
                        }
                        break;
                    }
                case "1-12" :
                    switch (listOfCaseToken.get(1).getValueCase()){
                        case "1-18" :
                        case "even" :
                            validBetTokenButton.setVisible(false);
                            modifyBetTokenButton.setVisible(false);
                            textBetToken.setVisible(false);
                            return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
                        case "13-24" :
                            return "de 1 à 24";
                    }
                case "13-24" :
                    switch (listOfCaseToken.get(1).getValueCase()){
                        case "red" :
                        case "black" :
                            validBetTokenButton.setVisible(false);
                            modifyBetTokenButton.setVisible(false);
                            textBetToken.setVisible(false);
                            return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
                        case "25-36" :
                            return "de 13 à 36";
                    }
                case "25-35" :
                    switch (listOfCaseToken.get(1).getValueCase()){
                        case "odd" :
                        case "19-36" :
                            validBetTokenButton.setVisible(false);
                            modifyBetTokenButton.setVisible(false);
                            textBetToken.setVisible(false);
                            return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
                    }
                case "1st" :
                    if(listOfCaseToken.get(1).getValueCase().equals("2nd")){
                        return "1;4;7;10;13;16;19;22;25;28;31;34 \n" + "2;5;8;11;14;17;20;23;26;29;32;35";
                    }
                    break;
                case "2nd" :
                    if(listOfCaseToken.get(1).getValueCase().equals("3rd")){
                        return "2;5;8;11;14;17;20;23;26;29;32;35 \n" + "3;6;9;12;15;18;21;24;27;30;33;36";
                    }
                    break;
                case "36" :
                    if(listOfCaseToken.get(1).getValueCase().equals("3rd")) {
                        return "3;6;9;12;15;18;21;24;27;30;33;36";
                    }
                    break;
                case "35" :
                    if(listOfCaseToken.get(1).getValueCase().equals("2nd")) {
                        return "2;5;8;11;14;17;20;23;26;29;32;35";
                    }
                    break;
            }
            return listOfCaseToken.get(0).getValueCase() + ";" + listOfCaseToken.get(1).getValueCase();
        }
        if(listOfCaseToken.size() == 3){
            validBetTokenButton.setVisible(false);
            modifyBetTokenButton.setVisible(false);
            textBetToken.setVisible(false);
            return "Cette combinaison de case est impossible";
        }

        if(listOfCaseToken.size() == 4){
            if(listOfCaseToken.get(2).getValueCase().equals("2nd") && listOfCaseToken.get(3).getValueCase().equals("3rd")){
                return "2;5;8;11;14;17;20;23;26;29;32;35 \n" + "3;6;9;12;15;18;21;24;27;30;33;36";
            }
            if(listOfCaseToken.get(2).getValueCase().equals("1st") && listOfCaseToken.get(3).getValueCase().equals("2nd")){
                return "1;4;7;10;13;16;19;22;25;28;31;34 \n" + "2;5;8;11;14;17;20;23;26;29;32;35";
            }
            return listOfCaseToken.get(0).getValueCase()+";"+listOfCaseToken.get(1).getValueCase()+";"+listOfCaseToken.get(2).getValueCase()+";"+listOfCaseToken.get(3).getValueCase();
        }
        return "aucune case sélectionnée";
    }

    /** Méthode qui retourne l'indice du jeton à modifier **/
    private int getTokenToRemove(int positionX, int positionY){
        for(int index = 0; index < tokenUsed; index ++){
            int distance = (int) Math.sqrt(Math.pow(Math.abs((positionX - listOfTokenUsed.get(index).getCircleToken().getLayoutX())) ,2) + Math.pow(Math.abs((positionY - listOfTokenUsed.get(index).getCircleToken().getLayoutY())) ,2));
            if(distance <= listOfTokenUsed.get(index).getCircleToken().getRadius()){
                return index;
            }
        }
        return -1;
    }

    /** Méthode qui modifie la position d'un jeton **/
    private void setPositionToken(int positionX, int positionY, Circle circleToken, Label labelToken, boolean visible){
        circleToken.setLayoutX(positionX);
        circleToken.setLayoutY(positionY);
        circleToken.setVisible(visible);
        labelToken.setLayoutX(positionX - 10);
        labelToken.setLayoutY(positionY - 10);
        labelToken.setVisible(visible);
    }

    /** Vérifie la zone pour poser un jeton **/
    private boolean isPositionMouseGood(int positionX, int positionY){
        int ORIGIN_X_1 = 320;
        int END_X_1 = 1046;
        if((positionX >= ORIGIN_X_1 && positionX <= END_X_1) && (positionY >= ORIGIN_Y_1 && positionY <= END_Y_1)){
            return true;
        }
        else{
            int ORIGIN_X_2 = 383;
            int ORIGIN_Y_2 = 222;
            int END_X_2 = 992;
            int END_Y_2 = 318;

            return (positionX >= ORIGIN_X_2 && positionX <= END_X_2) && (positionY >= ORIGIN_Y_2 && positionY <= END_Y_2);
        }
    }

    /** Méthode qui récupère toutes les informations du jeton posé **/
    private void betToken(int positionXToken, int positionYToken, Circle circleToken, Label labelToken){
        List<Case> listOfCaseToken = new ArrayList<>();
        for(int index = 0; index < listOfCaseBet.size(); index ++){
            if(tokenInTheCase(listOfCaseBet.get(index),positionXToken,positionYToken)){
                listOfCaseToken.add(listOfCaseBet.get(index));
            }
        }
        InformationTokenBet informationTokenBet = new InformationTokenBet(circleToken,labelToken,listOfCaseToken);
        if(listOfTokenUsed.size() >= (tokenUsed + 1)){
            if(listOfTokenUsed.size() == 0){
                listOfTokenUsed.add(informationTokenBet);
            }
            else {
                listOfTokenUsed.set(tokenUsed, informationTokenBet);
            }
        }
        else {
            listOfTokenUsed.add(informationTokenBet);
        }
        rectangleInformationBet.setVisible(true);
        labelInformationBetToken.setVisible(true);
        validBetTokenButton.setVisible(true);
        textBetToken.setText("0");
        textBetToken.setVisible(true);

        labelInformationBetToken.setText("Mise d'un jeton  \n"+ "Cases sélectionnées : \n" + informationTokenBet.getCases() + "\n Cases misées : \n"+getCasesBet(informationTokenBet.getListOfCaseToken(),circleToken)); //recup combinaison avec methode
    }

    /** Méthode qui vérifie si un jeton est passé sur une case **/
    private boolean tokenInTheCase(Case cases, int positionXToken, int positionYToken){
        int RADIUS_TOKEN = 16;
        for(int x = (positionXToken - RADIUS_TOKEN); x <= (positionXToken + RADIUS_TOKEN); x++){
            for(int y = (positionYToken - RADIUS_TOKEN); y <= (positionYToken + RADIUS_TOKEN); y++){
                if((Math.pow(Math.abs(x - positionXToken),2) + Math.pow(Math.abs(y - positionYToken),2))  == Math.pow(RADIUS_TOKEN,2)){
                    if(x >= cases.getOriginX() && x <= cases.getEndX() && y >= cases.getOriginY() && y <= cases.getEndY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Méthode pour valider la modification d'un jeton **/
    private void modifyBetToken() {
        if(!textBetToken.getText().isEmpty()) {
            try {
                int valueOfBet = Integer.parseInt(textBetToken.getText());
                if (valueOfBet <= 0) {
                    if (indexTokenRemove >= 0) {
                        setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(indexTokenRemove), listLabelToken.get(indexTokenRemove), false);
                        listOfTokenUsed.remove(indexTokenRemove);
                        listLabelToken.get(indexTokenRemove).setText("0");
                        Label labelToken = listLabelToken.get(indexTokenRemove);
                        Circle circleToken = listOfCircleToken.get(indexTokenRemove);
                        listLabelToken.remove(indexTokenRemove);
                        listOfCircleToken.remove(indexTokenRemove);
                        listLabelToken.add(labelToken);
                        listOfCircleToken.add(circleToken);
                    }
                } else {
                    listOfTokenUsed.get(indexTokenRemove).setValueOfBet(textBetToken.getText());
                    listLabelToken.get(indexTokenRemove).setText(textBetToken.getText());
                    tokenUsed++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            tokenUsed++;
        }

        modifyBetTokenButton.setVisible(false);
        textBetToken.setVisible(false);
        rectangleInformationBet.setVisible(false);
        labelInformationBetToken.setVisible(false);
        startingGameButton.setVisible(true);
    }

    /** Méthode pour valider un nouveau jeton posé  **/
    private void validBetToken(){
        if(!textBetToken.getText().isEmpty()) {
            int valueOfBet = 0;

            try {
                valueOfBet = Integer.parseInt(textBetToken.getText());
            }
            catch (Exception e) {
                rectangleInformationBet.setVisible(false);
                labelInformationBetToken.setVisible(false);
                validBetTokenButton.setVisible(false);
                textBetToken.setVisible(false);
                startingGameButton.setVisible(true);
                messageInterface.setMessage(labelError,"Il faut miser une valeur entière",Color.RED);
                return;
            }

            if (valueOfBet <= 0) {
                setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), false);
                if (tokenUsed >= 0 && listOfTokenUsed.size() > 0) {
                    listOfTokenUsed.remove(tokenUsed);
                    listLabelToken.get(tokenUsed).setText("0");
                }
            } else {
                listOfTokenUsed.get(tokenUsed).setValueOfBet(valueOfBet+"");
                listLabelToken.get(tokenUsed).setText(valueOfBet+"");
                tokenUsed++;
                tokenSound.play();
                createSoundToken();
            }

            System.out.println();
            validBetTokenButton.setVisible(false);
            textBetToken.setVisible(false);
            rectangleInformationBet.setVisible(false);
            labelInformationBetToken.setVisible(false);
            startingGameButton.setVisible(true);
        }
        else {
            textBetToken.setText("0");
            validBetToken();
        }
    }

    /** Méthode qui retourne la case qui possède une certaine valeur **/
    private Case getCase(String valueOfCase){
        for (Case aCase : listOfCaseBet) {
            if (aCase.getValueCase().equals(valueOfCase)) {
                return aCase;
            }
        }
        return null;
    }

    /** Méthode qui retourne les autres cases d'une combinaisons d'après une case **/
    private List<Case> getCombinationCase(Case cases){
        List<Case> listOfCase = new ArrayList<>();

        switch (cases.getValueCase()){
            case "1" :
                listOfCase.add(cases);
                listOfCase.add(getCase("2"));
                listOfCase.add(getCase("3"));
                break;
            case "3" :
                listOfCase.add(getCase("1"));
                listOfCase.add(getCase("2"));
                listOfCase.add(cases);
                break;
            case "4" :
                listOfCase.add(cases);
                listOfCase.add(getCase("5"));
                listOfCase.add(getCase("6"));
                break;
            case "6" :
                listOfCase.add(getCase("4"));
                listOfCase.add(getCase("5"));
                listOfCase.add(cases);
                break;
            case "7" :
                listOfCase.add(cases);
                listOfCase.add(getCase("8"));
                listOfCase.add(getCase("9"));
                break;
            case "9" :
                listOfCase.add(getCase("7"));
                listOfCase.add(getCase("8"));
                listOfCase.add(cases);
                break;
            case "10" :
                listOfCase.add(cases);
                listOfCase.add(getCase("11"));
                listOfCase.add(getCase("12"));
                break;
            case "12" :
                listOfCase.add(getCase("10"));
                listOfCase.add(getCase("11"));
                listOfCase.add(cases);
                break;
            case "13" :
                listOfCase.add(cases);
                listOfCase.add(getCase("14"));
                listOfCase.add(getCase("15"));
                break;
            case "15" :
                listOfCase.add(getCase("13"));
                listOfCase.add(getCase("14"));
                listOfCase.add(cases);
                break;
            case "16" :
                listOfCase.add(cases);
                listOfCase.add(getCase("17"));
                listOfCase.add(getCase("18"));
                break;
            case "18" :
                listOfCase.add(getCase("16"));
                listOfCase.add(getCase("17"));
                listOfCase.add(cases);
                break;
            case "19" :
                listOfCase.add(cases);
                listOfCase.add(getCase("20"));
                listOfCase.add(getCase("21"));
                break;
            case "21" :
                listOfCase.add(getCase("19"));
                listOfCase.add(getCase("20"));
                listOfCase.add(cases);
                break;
            case "22" :
                listOfCase.add(cases);
                listOfCase.add(getCase("23"));
                listOfCase.add(getCase("24"));
                break;
            case "24" :
                listOfCase.add(getCase("22"));
                listOfCase.add(getCase("23"));
                listOfCase.add(cases);
                break;
            case "25" :
                listOfCase.add(cases);
                listOfCase.add(getCase("26"));
                listOfCase.add(getCase("27"));
                break;
            case "27" :
                listOfCase.add(getCase("25"));
                listOfCase.add(getCase("26"));
                listOfCase.add(cases);
                break;
            case "28" :
                listOfCase.add(cases);
                listOfCase.add(getCase("29"));
                listOfCase.add(getCase("30"));
                break;
            case "30" :
                listOfCase.add(getCase("28"));
                listOfCase.add(getCase("29"));
                listOfCase.add(cases);
                break;
            case "31" :
                listOfCase.add(cases);
                listOfCase.add(getCase("32"));
                listOfCase.add(getCase("33"));
                break;
            case "33" :
                listOfCase.add(getCase("31"));
                listOfCase.add(getCase("32"));
                listOfCase.add(cases);
                break;
            case "34" :
                listOfCase.add(cases);
                listOfCase.add(getCase("35"));
                listOfCase.add(getCase("36"));
                break;
            case "36" :
                listOfCase.add(getCase("34"));
                listOfCase.add(getCase("35"));
                listOfCase.add(cases);
                break;
        }
        return listOfCase;
    }

    /** Méthode pour lancer la roulette et mettre fin aux mises **/
    private void startingGame() {
        if(tokenUsed == 0){
            messageInterface.setMessage(labelError,"Il faut placer un jeton au minimum",Color.RED);
        }
        else{
            startingGame = true;
            startingGameButton.setVisible(false);

            Timeline timeline = new Timeline();
            Duration timePoint = Duration.ZERO;
            int indexList = 0;
            int caseChoose = 0;

            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> circleBallRoulette.setVisible(true)));

            for(int index = 0; index < 111; index ++){ //3 tours
                int finalIndexList = indexList;
                timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setPositionBallRoulette(listOfCaseRoulette.get(finalIndexList).getOriginX(),listOfCaseRoulette.get(finalIndexList).getOriginY())));
                timePoint = timePoint.add(Duration.seconds(0.05));
                indexList = (indexList + 1) % listOfCaseRoulette.size();
            }

            for(int index = 0; index < listOfCaseRoulette.size(); index++){
                int finalIndexList = index;
                timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setPositionBallRoulette(listOfCaseRoulette.get(finalIndexList).getOriginX(),listOfCaseRoulette.get(finalIndexList).getOriginY())));
                timePoint = timePoint.add(Duration.seconds(0.05));
                if(Integer.parseInt(listOfCaseRoulette.get(index).getValueCase()) == caseChoose){
                    break;
                }
            }

            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> createSoundRoulette()));

            rouletteSound.play();
            timeline.play();
        }
    }

    private void setPositionBallRoulette(double layoutX, double layoutY){
        circleBallRoulette.setLayoutY(layoutY);
        circleBallRoulette.setLayoutX(layoutX);
    }

    /** Méthode qui crée un jeton **/
    private void createToken(){
        Circle circle = new Circle();
        setupScene.setCircle(circle,16.0,336.0,262.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),StrokeType.INSIDE,2.0,false,anchorPane);
        listOfCircleToken.add(circle);

        Label label = new Label();
        setupScene.setLabel(label,"",Pos.CENTER,325.0,252.0,21.0,23.0,new Font(10),Paint.valueOf("WHITE"),false,anchorPane);
        listLabelToken.add(label);
    }

    /**
     * Méthode pour quitter le jeu et retourner dans le menu principale
     **/
    private void returnMainMenu(){
        settingMenuController.exitSettingMenu();
        logMenuController.exitLogMenu();
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }


    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume <= 1.0 && newSoundVolume >= 0){
            soundVolume = newSoundVolume;
            tokenSound.setVolume(soundVolume);
            rouletteSound.setVolume(soundVolume);
        }
    }

    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    private void createSoundToken(){
        tokenSound = new MediaPlayer(new Media(getClass().getResource("sound/tokenSound.mp3").toExternalForm()));
        tokenSound.setVolume(soundVolume);
    }

    private void createSoundRoulette(){
        rouletteSound = new MediaPlayer(new Media(getClass().getResource("sound/rouletteSound.mp3").toExternalForm()));
        rouletteSound.setVolume(soundVolume);
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
