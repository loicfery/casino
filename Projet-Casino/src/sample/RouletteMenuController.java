package sample;

import games.User;
import javafx.animation.PathTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private final List<Case> listOfCase = new ArrayList<>();
    private final List<InformationTokenBet> listOfTokenUsed = new ArrayList<>();
    private final List<Integer> listPositionXCaseRoulette = new ArrayList<>();
    private final List<Integer> listPositionYCaseRoulette = new ArrayList<>();

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;

    private final List<Rectangle> listOfRectangleGameBoard = new ArrayList<>();
    private final List<Label> listOfLabelGameBoard = new ArrayList<>();
    private double soundVolume;
    private boolean backgroundAnimation;

    private MediaPlayer tokenSound;

    private final ImageView roulette = new ImageView();

    private final Label labelProfit = new Label();
    private final Label labelTokenUser = new Label();
    private final Label labelPseudo = new Label();
    private final Label labelInformationBetToken = new Label();
    private final Label labelError = new Label();
    private final Label labelRule = new Label();
    private final Label labelLogParty = new Label();

    private final Button modifyBetTokenButton = new Button();
    private final Button validBetTokenButton = new Button();
    private final Button startingGameButton = new Button();
    private final Button returnMainMenuButton = new Button();

    private final TextField textBetToken = new TextField();

    private final Circle circleBallRoulette = new Circle();
    private final Circle circleRule = new Circle();
    private  Circle circleSetting = new Circle();

    private final Rectangle rectangleLog = new Rectangle();

    private final TextArea textRule = new TextArea();
    private final TextArea textLog = new TextArea();


    public RouletteMenuController(User user, Stage stage, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
    }

    /** Méthode qui initialise l'interface de la roulette **/
    public void setting(){
        stage.setTitle("Roulette");
        Scene scene = new Scene(root, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("rouletteMenu.css").toExternalForm());
        stage.setScene(scene);

        setupGameBoard();
        setCasePosition();
        setCasePositionRoulette();
        setRule();

        setupScene.setImageView(roulette,638.0,350.0,425.0,471.0, new Image(getClass().getResource("image/roulette.jpg").toExternalForm()),true,anchorPane);
        roulette.setPickOnBounds(true);
        roulette.setPreserveRatio(true);

        setupScene.setLabel(labelProfit,"Gain : 0",Pos.CENTER_LEFT,14.0,718.0,68.0,607.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelTokenUser,"Jetons : "+user.getNumberOfToken(),Pos.CENTER_LEFT,14.0,640.0,68.0,613.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPseudo,"Joueur : "+user.getPseudo(),Pos.CENTER_LEFT,14.0,563.0,68.0,613.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelInformationBetToken,"Information",Pos.CENTER,212.0,368.0,202.0,387.0,new Font(18),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setButton(modifyBetTokenButton,"Miser",Pos.CENTER,295.0,696.0,68.0,216.0,new Font(20.0),false,anchorPane);
        setupScene.setButton(validBetTokenButton,"Miser",Pos.CENTER,295.0,696.0,68.0,216.0,new Font(20.0),false,anchorPane);
        setupScene.setTextField(textBetToken,"Mise",Pos.CENTER,234.0,597.0,80.0,338.0,new Font(20.0),false,anchorPane);
        setupScene.setButton(startingGameButton,"Starting",Pos.CENTER,25.0,416.0,102.0,251.0,new Font(20.0),true,anchorPane);
        setupScene.setLabel(labelError,"Erreur",Pos.CENTER,197.0,600.0,60.0,387.0,new Font(20.0),Paint.valueOf("RED"),false,anchorPane);
        setupScene.setCircle(circleBallRoulette,7.0,851.0,563.0,Paint.valueOf("BLACK"),Paint.valueOf("BLACK"),StrokeType.INSIDE,0.0,false,anchorPane);
        setupScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);

        setupScene.setCircle(circleSetting,18,970,30,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())),Paint.valueOf("GREEN"),StrokeType.INSIDE,1.0,true,anchorPane);

        setupScene.setRectangle(rectangleLog,1000.0,15.0,30.0,50.0,10.0,10.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),1.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setLabel(labelLogParty,"Log",Pos.CENTER,1000.0,15.0,23.0,50.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextArea(textLog,500,46.0,500.0,500.0,false,false,anchorPane);

        setupScene.setCircle(circleRule,16.0,1070.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setupScene.setLabel(labelRule,"?",Pos.CENTER,1055.0,15.0,23,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextArea(textRule,360,46.0,600.0,700.0,false,false,anchorPane);

        tokenSound = createSoundToken();
        tokenSound.setVolume(soundVolume);

        anchorPane.setOnMouseClicked((event)-> choosePositionToken(event));
        validBetTokenButton.setOnMouseClicked((event)-> validBetToken());
        modifyBetTokenButton.setOnMouseClicked((event)-> modifyBetToken());
        startingGameButton.setOnMouseClicked((event)-> startingGame());
        returnMainMenuButton.setOnMouseClicked((event)-> returnMainMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /**
     * Méthode pour écrire les règles du jeux dans une zone de texte
     **/
    private void setRule() {
        try {
            File fileRuleBlackJack = new File("Regles-Roulette.txt");
            BufferedReader buffer = new BufferedReader(new FileReader(fileRuleBlackJack));
            String line;

            while ((line = buffer.readLine()) != null) {
                textRule.setText(textRule.getText() + "\n" + line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("fichier règle non trouvé");
        } catch (Exception e) {
            e.printStackTrace();
        }

        labelRule.setOnMouseEntered((event)->{
            showRule();
        });
        labelRule.setOnMouseExited((event)->{
            hideRule();
        });
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
        listOfCase.add(new Case(320,78,384,223,"green","0"));
        listOfCase.add(new Case(383,174,434,222,"red","1"));
        listOfCase.add(new Case(383,126,434,174,"black","2"));
        listOfCase.add(new Case(383,78,434,126,"red","3"));
        listOfCase.add(new Case(434,174,485,222,"black","4"));
        listOfCase.add(new Case(434,126,485,174,"red","5"));
        listOfCase.add(new Case(434,78,485,126,"black","6"));
        listOfCase.add(new Case(485,174,536,222,"red","7"));
        listOfCase.add(new Case(485,126,536,174,"black","8"));
        listOfCase.add(new Case(485,78,536,126,"red","9"));
        listOfCase.add(new Case(536,174,587,222,"black","10"));
        listOfCase.add(new Case(536,126,587,174,"black","11"));
        listOfCase.add(new Case(536,78,587,126,"red","12"));
        listOfCase.add(new Case(587,174,638,222,"black","13"));
        listOfCase.add(new Case(587,126,638,174,"red","14"));
        listOfCase.add(new Case(587,78,638,126,"black","15"));
        listOfCase.add(new Case(638,174,689,222,"red","16"));
        listOfCase.add(new Case(638,126,689,174,"black","17"));
        listOfCase.add(new Case(638,78,689,126,"red","18"));
        listOfCase.add(new Case(689,174,740,222,"red","19"));
        listOfCase.add(new Case(689,126,740,174,"black","20"));
        listOfCase.add(new Case(689,78,740,126,"red","21"));
        listOfCase.add(new Case(740,174,791,222,"black","22"));
        listOfCase.add(new Case(740,126,791,174,"red","23"));
        listOfCase.add(new Case(740,78,791,126,"black","24"));
        listOfCase.add(new Case(791,174,842,222,"red","25"));
        listOfCase.add(new Case(791,126,842,174,"black","26"));
        listOfCase.add(new Case(791,78,842,126,"red","27"));
        listOfCase.add(new Case(842,174,893,222,"black","28"));
        listOfCase.add(new Case(842,126,893,174,"black","29"));
        listOfCase.add(new Case(842,78,893,126,"red","30"));
        listOfCase.add(new Case(893,174,944,222,"black","31"));
        listOfCase.add(new Case(893,126,944,174,"red","32"));
        listOfCase.add(new Case(893,78,944,126,"black","33"));
        listOfCase.add(new Case(944,174,995,222,"red","34"));
        listOfCase.add(new Case(944,126,995,174,"black","35"));
        listOfCase.add(new Case(944,78,995,126,"red","36"));
        listOfCase.add(new Case(995,174,1046,222,"green","1st"));
        listOfCase.add(new Case(995,126,1046,174,"green","2nd"));
        listOfCase.add(new Case(995,78,1046,126,"green","3rd"));
        listOfCase.add(new Case(383,222,587,270,"green","1-12"));
        listOfCase.add(new Case(383,270,483,318,"green","1-18"));
        listOfCase.add(new Case(484,270,584,318,"green","even"));
        listOfCase.add(new Case(587,222,791,270,"green","13-24"));
        listOfCase.add(new Case(587,270,687,718,"green","red"));
        listOfCase.add(new Case(688,270,788,718,"green","black"));
        listOfCase.add(new Case(791,222,995,270,"green","25-36"));
        listOfCase.add(new Case(791,270,891,318,"green","odd"));
        listOfCase.add(new Case(892,270,992,318,"green","19-36"));
    }

    /** Méthode de création de la liste des position des cases de la roulette **/
    private void setCasePositionRoulette(){
        listPositionXCaseRoulette.add(984);
        listPositionYCaseRoulette.add(475);
        listPositionXCaseRoulette.add(963);
        listPositionYCaseRoulette.add(493);
        listPositionXCaseRoulette.add(972);
        listPositionYCaseRoulette.add(513);
        listPositionXCaseRoulette.add(979);
        listPositionYCaseRoulette.add(535);
        listPositionXCaseRoulette.add(982);
        listPositionYCaseRoulette.add(557);
        listPositionXCaseRoulette.add(982);
        listPositionYCaseRoulette.add(579);
        listPositionXCaseRoulette.add(976);
        listPositionYCaseRoulette.add(600);
        listPositionXCaseRoulette.add(967);
        listPositionYCaseRoulette.add(620);
        listPositionXCaseRoulette.add(957);
        listPositionYCaseRoulette.add(641);
        listPositionXCaseRoulette.add(942);
        listPositionYCaseRoulette.add(658);
        listPositionXCaseRoulette.add(925);
        listPositionYCaseRoulette.add(670);
        listPositionXCaseRoulette.add(906);
        listPositionYCaseRoulette.add(680);
        listPositionXCaseRoulette.add(885);
        listPositionYCaseRoulette.add(691);
        listPositionXCaseRoulette.add(861);
        listPositionYCaseRoulette.add(694);
        listPositionXCaseRoulette.add(841);
        listPositionYCaseRoulette.add(693);
        listPositionXCaseRoulette.add(816);
        listPositionYCaseRoulette.add(694);
        listPositionXCaseRoulette.add(794);
        listPositionYCaseRoulette.add(686);
        listPositionXCaseRoulette.add(775);
        listPositionYCaseRoulette.add(674);
        listPositionXCaseRoulette.add(757);
        listPositionYCaseRoulette.add(660);
        listPositionXCaseRoulette.add(742);
        listPositionYCaseRoulette.add(645);
        listPositionXCaseRoulette.add(729);
        listPositionYCaseRoulette.add(624);
        listPositionXCaseRoulette.add(718);
        listPositionYCaseRoulette.add(603);
        listPositionXCaseRoulette.add(714);
        listPositionYCaseRoulette.add(580);
        listPositionXCaseRoulette.add(716);
        listPositionYCaseRoulette.add(556);
        listPositionXCaseRoulette.add(715);
        listPositionYCaseRoulette.add(533);
        listPositionXCaseRoulette.add(722);
        listPositionYCaseRoulette.add(512);
        listPositionXCaseRoulette.add(734);
        listPositionYCaseRoulette.add(492);
        listPositionXCaseRoulette.add(745);
        listPositionYCaseRoulette.add(469);
        listPositionXCaseRoulette.add(765);
        listPositionYCaseRoulette.add(454);
        listPositionXCaseRoulette.add(786);
        listPositionYCaseRoulette.add(443);
        listPositionXCaseRoulette.add(807);
        listPositionYCaseRoulette.add(433);
        listPositionXCaseRoulette.add(829);
        listPositionYCaseRoulette.add(426);
        listPositionXCaseRoulette.add(850);
        listPositionYCaseRoulette.add(425);
        listPositionXCaseRoulette.add(875);
        listPositionYCaseRoulette.add(428);
        listPositionXCaseRoulette.add(898);
        listPositionYCaseRoulette.add(433);
        listPositionXCaseRoulette.add(920);
        listPositionYCaseRoulette.add(441);
        listPositionXCaseRoulette.add(938);
        listPositionYCaseRoulette.add(455);
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
                        labelError.setVisible(false);
                        setPositionToken(mousePositionX, mousePositionY, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), true);
                        betToken(mousePositionX, mousePositionY, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed));
                    }
                    else {
                        labelInformationBetToken.setVisible(false);
                        textBetToken.setVisible(false);
                        labelError.setText("Vous ne pouvez pas placer de jeton ici");
                        labelError.setVisible(true);
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
        for(int index = 0; index < listOfCase.size(); index ++){
            if(tokenInTheCase(listOfCase.get(index),positionXToken,positionYToken)){
                listOfCaseToken.add(listOfCase.get(index));
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
        labelInformationBetToken.setVisible(false);
        startingGameButton.setVisible(true);
    }

    /** Méthode pour valider un nouveau jeton posé  **/
    private void validBetToken(){
        if(!textBetToken.getText().isEmpty()) {
            try {
                int valueOfBet = Integer.parseInt(textBetToken.getText());
                if (valueOfBet <= 0) {
                    setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), false);
                    if (tokenUsed >= 0 && listOfTokenUsed.size() > 0) {
                        listOfTokenUsed.remove(tokenUsed);
                        listLabelToken.get(tokenUsed).setText("0");
                    }
                } else {
                    listOfTokenUsed.get(tokenUsed).setValueOfBet(textBetToken.getText());
                    listLabelToken.get(tokenUsed).setText(textBetToken.getText());
                    tokenUsed++;
                    tokenSound.play();
                    tokenSound = createSoundToken();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
            validBetTokenButton.setVisible(false);
            textBetToken.setVisible(false);
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
        for (Case aCase : listOfCase) {
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
            labelError.setText("Il faut placer un jeton au minimum");
            labelError.setVisible(true);
        }
        else{
            startingGame = true;
            labelError.setVisible(false);
            startingGameButton.setVisible(false);

            Circle circle = new Circle(140);
            PathTransition transition = new PathTransition();
            transition.setNode(circleBallRoulette);
            transition.setDuration(Duration.seconds(2));
            transition.setPath(circle);
            transition.setCycleCount(PathTransition.INDEFINITE);
            circleBallRoulette.setVisible(true);
            transition.play();
        }
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
        MainMenuController mainMenuController = new MainMenuController(stage,user,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    /**
     * Méthode qui montre la zone de texte contenant les règles
     **/
    private void showRule() {
        textRule.setVisible(true);
    }

    /**
     * Méthode qui cacher la zone de texte contenant les règles
     **/
    private void hideRule() {
        textRule.setVisible(false);
    }

    private void showLog(){

    }

    private void hideLog(){

    }

    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume <= 1.0 && newSoundVolume >= 0){
            soundVolume = newSoundVolume;
            tokenSound.setVolume(soundVolume);
        }
    }

    private void goToMenuSetting(){
        InterfaceMenuSetting interfaceMenuSetting = new InterfaceMenuSetting(this, soundVolume,backgroundAnimation);
        interfaceMenuSetting.setting();
    }

    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    private MediaPlayer createSoundToken(){
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("sound/tokenSound.mp3").toExternalForm()));
        mediaPlayer.setVolume(soundVolume);
        return mediaPlayer;
    }
}
