package sample;

import games.BlackJack;
import games.Card;
import games.Database;
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

public class BlackJackMenuController implements InterfaceMenu{

    private final int ORIGIN_X_Croupier = 300;
    private final int ORIGIN_X_FIRST_HAND = 300;
    private final int ORIGIN_Y_USER = 380;
    private final int ORIGIN_Y_CROUPIER = 25;

    private int currentPositionXUserFirstHand;
    private int currentPositionXUserSecondHand;
    private int currentPositionXCroupier;

    private final BorderPane  root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final LogMenuController logMenuController;
    private final SettingMenuController settingMenuController;
    private final RuleMenuController ruleMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();

    private final BlackJack blackJack;

    private MediaPlayer cardSound;
    private MediaPlayer tokenSound;

    private double soundVolume;
    private boolean backgroundAnimation;

    private boolean split = false;
    private boolean stand = false;
    private boolean surrender = false;
    private int indexCurrentHand = 1;
    private final int valueTokenUserBegin;

    private final List<Shape> token1 = new ArrayList<>();
    private final List<Shape> token2 = new ArrayList<>();
    private final List<ImageView> croupierHand = new ArrayList<>();
    private final List<ImageView> userFirstHand = new ArrayList<>();
    private final List<ImageView> userSecondHand = new ArrayList<>();

    private final Rectangle zoneBetUser1 = new Rectangle();
    private final Rectangle zoneBetUser2 = new Rectangle();
    private final Rectangle rectangleLog = new Rectangle();

    private final Label labelToken = new Label();
    private final Label labelProfit = new Label();
    private final Label labelPseudo = new Label();
    private final Label labelError = new Label();
    private final Label labelToken1 = new Label();
    private final Label labelToken2 = new Label();
    private final Label labelRule = new Label();
    private final Label labelValueUserFirstHand = new Label();
    private final Label labelValueUserSecondHand = new Label();
    private final Label labelValueCroupierHand = new Label();
    private final Label labelLog = new Label();

    private final TextField textBetUser = new TextField();

    private final Button validBetButton = new Button();
    private final Button returnMainMenuButton = new Button();
    private final Button actionSplitButton = new Button();
    private final Button actionHitButton = new Button();
    private final Button actionStandButton = new Button();
    private final Button actionDoubleButton = new Button();
    private final Button actionSurrenderButton = new Button();
    private final Button actionInsuranceButton = new Button();
    private final Button newPartyButton = new Button();

    private final Circle circleRule = new Circle();
    private final Circle circleSetting = new Circle();

    public BlackJackMenuController(User user,Stage stage, Database database, double soundVolume, boolean backgroundAnimation){
        this.stage = stage;
        this.user = user;
        this.soundVolume = soundVolume;
        this.valueTokenUserBegin = user.getToken();
        this.backgroundAnimation = backgroundAnimation;
        logMenuController = new LogMenuController();
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);
        ruleMenuController = new RuleMenuController(this);
        blackJack = new BlackJack(user);
        this.database = database;
    }

    /** Méthode qui initialise l'interface du black jack **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("blackJackMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setRectangle(zoneBetUser1,327.0,600.0,174.0,147.0,5.0,5.0,Paint.valueOf("#158000"),Paint.valueOf("BLACK"),5.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setRectangle(zoneBetUser2,550,600.0,174.0,147.0,5.0,5.0,Paint.valueOf("#158000"),Paint.valueOf("BLACK"),5.0,StrokeType.INSIDE,false,anchorPane);
        setupScene.setLabel(labelToken,"Jetons : "+user.getToken(), Pos.CENTER_LEFT,30.0,660.0,55.0,163.0,new Font(30.0), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelProfit,"Gain : 0", Pos.CENTER_LEFT, 30.0,720.0,55.0,163.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPseudo,"Joueur : "+user.getPseudo(), Pos.CENTER_LEFT,30.0,600.0,55.0,308.0,new Font(30.0),Color.BLACK,true,anchorPane);
        setupScene.setLabel(labelError,"Erreur :", Pos.CENTER,100.0,455.0,36.0,700.0,new Font(25.0),Color.RED,false,anchorPane);
        setupScene.setTextField(textBetUser,"Votre mise", Pos.CENTER,280.0,380.0,79.0,252.0,new Font(30.0),true,anchorPane);
        setupScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(newPartyButton,"Nouvelle partie",Pos.CENTER,14.0,80.0,57.0,200.0,new Font(20.0),false,anchorPane);
        setupScene.setButton(validBetButton,"Miser",Pos.CENTER,320.0,500.0,72.0,163.0,new Font(30.0),true,anchorPane);
        setupScene.setLabel(labelValueUserFirstHand,"",Pos.CENTER,250.0,330.0,20.0,300.0,new Font(20.0),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelValueUserSecondHand,"",Pos.CENTER,473.0,330.0,20.0,300.0,new Font(20.0),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelValueCroupierHand,"",Pos.CENTER,250.0,260,20.0,300.0,new Font(20.0),Paint.valueOf("BLACK"),false,anchorPane);

        setupScene.setButton(actionSurrenderButton,"Abandonner",Pos.CENTER,30.0,500.0,55.0,250,new Font(20.0),false,anchorPane);
        setupScene.setButton(actionHitButton,"Tirer une carte",Pos.CENTER,30.0,430.0,55.0,250,new Font(20.0),false,anchorPane);
        setupScene.setButton(actionDoubleButton,"Doubler la mise",Pos.CENTER,30.0,360.0,55.0,250,new Font(20.0),false,anchorPane);
        setupScene.setButton(actionStandButton,"Rester",Pos.CENTER,30.0,290.0,55.0,250,new Font(20.0),false,anchorPane);
        setupScene.setButton(actionInsuranceButton,"Assurance",Pos.CENTER,30.0,220.0,55.0,250,new Font(20.0),false,anchorPane);
        setupScene.setButton(actionSplitButton,"Partager",Pos.CENTER,30.0,150.0,55.0,250,new Font(20.0),false,anchorPane);

        initToken1();
        initToken2();

        setupScene.setCircle(circleSetting,18,670,30,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())),Paint.valueOf("GREEN"),StrokeType.INSIDE,1.0,true,anchorPane);

        setupScene.setRectangle(rectangleLog,700.0,15.0,30.0,50.0,10.0,10.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),1.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setLabel(labelLog,"Log",Pos.CENTER,700,15,30,50,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setCircle(circleRule,16.0,770.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setupScene.setLabel(labelRule,"?",Pos.CENTER,754.0,15.0,23,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);

        logMenuController.getLog("Log de la partie\n");
        logMenuController.getLog("Le joueur "+user.getPseudo()+" démarre une nouvelle partie.");

        createCardSound();

        tokenSound = new MediaPlayer(new Media(getClass().getResource("sound/tokenSound.mp3").toExternalForm()));
        tokenSound.setVolume(soundVolume);

        returnMainMenuButton.setOnMouseClicked((event) -> returnMainMenu());
        validBetButton.setOnMouseClicked((event)->  validBet());
        actionSplitButton.setOnMouseClicked((event)-> actionSplit());
        actionStandButton.setOnMouseClicked((event)-> actionStand());
        actionHitButton.setOnMouseClicked((event)-> actionHit());
        actionDoubleButton.setOnMouseClicked((event)-> actionDouble());
        actionInsuranceButton.setOnMouseClicked((event)-> actionInsurance());
        actionSurrenderButton.setOnMouseClicked((event)-> actionSurrender());
        newPartyButton.setOnMouseClicked((event)->  newGame());
        labelLog.setOnMouseClicked((event)-> goToLogMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        blackJack.addUserBet(user);
        labelRule.setOnMouseClicked((event)-> goToRuleMenu());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui crée le premier jeton **/
    private void initToken1(){
        Circle circle = new Circle();
        setupScene.setCircle(circle,41.0,400.0,685.0, Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE,2.0,false,anchorPane);
        token1.add(circle);
        circle = new Circle();
        setupScene.setCircle(circle,41.0,400.0,675.0,Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE, 2.0,false,anchorPane);
        token1.add(circle);

        Rectangle rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 393.0, 637.0,22.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);
        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 393.0, 687.0,37.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);
        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 362.0, 670.0,14.0,25.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);
        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 415.0, 669.0,14.0,24.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);

        Polyline polyline = new Polyline();
        setupScene.setPolyline(polyline,379.0,699.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{-13.75,4.25,0.75,-12.0,10.5,-3.0,-4.75,17.0},false,anchorPane);
        token1.add(polyline);
        polyline = new Polyline();
        setupScene.setPolyline(polyline,424.0,702.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{4.75, 8.5, -12.0, -8.75, -4.75, -17.75, 12.5, -2.75},false,anchorPane);
        token1.add(polyline);
        polyline = new Polyline();
        setupScene.setPolyline(polyline,371.0,639.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{7.5, 4.25, 19.25, 15.75, 9.5, 27.75, -2.0, 13.25},false,anchorPane);
        token1.add(polyline);
        polyline = new Polyline();
        setupScene.setPolyline(polyline,425.0,651.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE, new double[]{-13.75, 4.25, -2.0, -7.0, 6.75, 2.5, -4.75, 17.0},false,anchorPane);
        token1.add(polyline);

        circle = new Circle();
        setupScene.setCircle(circle,23.0,400.0,675.0,Paint.valueOf("DODGERBLUE"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,false,anchorPane);
        token1.add(circle);

        Line line = new Line();
        setupScene.setLine(line,493.0,714.0,-100.0,-85.25, 0, 0,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token1.add(line);
        line = new Line();
        setupScene.setLine(line,468.0,700.0,-99.5,-87.75, -1.0, 9.5,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token1.add(line);
        line = new Line();
        setupScene.setLine(line,522.0,702.0,-97.5,-88.0, 5.25, -5.75,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token1.add(line);

        setupScene.setLabel(labelToken1,"0",Pos.CENTER,341.0,600.0,148.0,119.0,new Font(30.0),Color.BLACK,false,anchorPane);
    }

    /** Méthode qui crée le deuxième jeton en cas de split **/
    private void initToken2(){
        Circle circle = new Circle();
        setupScene.setCircle(circle,41.0,625.0,685.0, Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE,2.0,false,anchorPane);
        token2.add(circle);
        circle = new Circle();
        setupScene.setCircle(circle,41.0,625,675.0,Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE, 2.0,false,anchorPane);
        token2.add(circle);

        Rectangle rectangle = new Rectangle();
        setupScene.setRectangle(rectangle,618.0, 637.0,22.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);
        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 618.0, 687.0,37.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);
        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 587.0, 670.0,14.0,25.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);
        rectangle = new Rectangle();
        setupScene.setRectangle(rectangle, 640.0, 669.0,14.0,24.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);

        Polyline polyline = new Polyline();
        setupScene.setPolyline(polyline,604.0,699.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{-13.75, 4.25, 0.75, -12.0, 10.5, -3.0, -4.75, 17.0},false,anchorPane);
        token2.add(polyline);
        polyline = new Polyline();
        setupScene.setPolyline(polyline,649,702.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{4.75, 8.5, -12.0, -8.75, -4.75, -17.75, 12.5, -2.75},false,anchorPane);
        token2.add(polyline);
        polyline = new Polyline();
        setupScene.setPolyline(polyline,596,639.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{7.5, 4.25, 19.25, 15.75, 9.5, 27.75, -2.0, 13.25},false,anchorPane);
        token2.add(polyline);
        polyline = new Polyline();
        setupScene.setPolyline(polyline,652,651.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE, new double[]{-13.75, 4.25, -2.0, -7.0, 6.75, 2.5, -4.75, 17.0},false,anchorPane);
        token2.add(polyline);

        circle = new Circle();
        setupScene.setCircle(circle,23.0,625,675.0,Paint.valueOf("DODGERBLUE"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,false,anchorPane);
        token2.add(circle);

        Line line = new Line();
        setupScene.setLine(line,718.0,714.0,-100.0,-85.25,0, 0,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token2.add(line);
        line = new Line();
        setupScene.setLine(line,693,700.0,-99.5,-87.75, -1.0, 9.5,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token2.add(line);
        line = new Line();
        setupScene.setLine(line,747,702.0,-97.5,-88.0, 5.25, -5.75,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token2.add(line);

        setupScene.setLabel(labelToken2,"0",Pos.CENTER,566,600.0,148.0,119.0,new Font(30.0),Color.BLACK,false,anchorPane);
    }

    /**
     * Méthode pour modifier la visibilité du second jetons (en cas de split)
     **/
    private void setTokenVisible(List<Shape> token) {
        for (Shape shape : token) {
            shape.setVisible(true);
        }
    }

    /**
     * Méthode pour valider la mise
     **/
    private void validBet() {
        if (textBetUser.getText().isEmpty()) {
            labelError.setText("Il faut entrer une mise");
            labelError.setVisible(true);
        } else {
            try {
                int valueOfBet = Integer.parseInt(textBetUser.getText());

                int BET_MIN = 2;
                int BET_MAX = 100;
                if (valueOfBet < BET_MIN || valueOfBet > BET_MAX) {
                    labelError.setText("Vous ne pouvez miser qu'entre 2 et 100 jetons");
                    labelError.setVisible(true);
                } else {
                    int newTokenUser = user.getToken() - valueOfBet;
                    if (newTokenUser < 0) {
                        labelError.setText("Vous n'avez pas assez de jeton");
                        labelError.setVisible(true);
                    }
                    else {
                        tokenSound.play();

                        labelError.setVisible(false);

                        blackJack.addUserBet(user);
                        blackJack.userBet(valueOfBet,user);

                        textBetUser.setVisible(false);
                        validBetButton.setVisible(false);

                        labelToken1.setVisible(true);
                        labelToken1.setText(blackJack.getBet().getBet(user)+"");
                        labelToken.setText("Jetons : " + (user.getToken() - valueOfBet));

                        logMenuController.getLog("Le joueur "+user.getPseudo()+" mise "+valueOfBet+" jetons");

                        setTokenVisible(token1);

                        Timeline timeline = new Timeline();
                        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),e -> startingGame()));
                        timeline.play();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Méthode pour commencer une partie de black jack
     **/
    private void startingGame() {
        currentPositionXUserFirstHand = ORIGIN_X_FIRST_HAND;
        currentPositionXCroupier = ORIGIN_X_Croupier;

        blackJack.gameBegin();
        animationCardBegin();
    }

    /**
     * Méthode pour retourner une carte précise parmis les 52 cartes
     **/
    private ImageView chooseCard(int cardNumber, String cardRank) {
        switch (cardNumber) {
            case 1 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/asOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/asOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/asOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/asOfSquare.jpg").toExternalForm()));
            case 2 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/twoOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/twoOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/twoOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/twoOfSquare.jpg").toExternalForm()));
            case 3 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/threeOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/threeOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/threeOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/threeOfSquare.jpg").toExternalForm()));
            case 4 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/fourOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fourOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fourOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fourOfSquare.jpg").toExternalForm()));
            case 5 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/fiveOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fiveOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fiveOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fiveOfSquare.jpg").toExternalForm()));
            case 6 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/sixOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sixOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sixOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sixOfSquare.jpg").toExternalForm()));
            case 7 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/sevenOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sevenOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sevenofClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sevenOfSquare.jpg").toExternalForm()));
            case 8 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/eightOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/eightOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/eightOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/eightOfSquare.jpg").toExternalForm()));
            case 9 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/nineOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/nineOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/nineOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/nineOfSquare.jpg").toExternalForm()));
            case 10 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/tenOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/tenOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/tenOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/tenOfSquare.jpg").toExternalForm()));
            case 11 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/jackOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/jackOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/jackOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/jackOfSquare.jpg").toExternalForm()));
            case 12 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/queenOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/queenOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/queenOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/queenOfSquare.jpg").toExternalForm()));
            case 13 : return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/kingOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/kingOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/kingOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/kingOfClover.jpg").toExternalForm()));
            default : return null;
        }
    }

    /**
     * Méthode pour retourner une carte parmis 4 cartes et un symbole
     **/
    private ImageView getCardByRank(String cardRank, Image cardOfHeart, Image cardOfSpade, Image cardOfClover, Image cardOfSquare) {
        switch (cardRank) {
            case "HEART" : return new ImageView(cardOfHeart);
            case "SPADE" : return new ImageView(cardOfSpade);
            case "CLOVER" : return new ImageView(cardOfClover);
            case "SQUARE" : return new ImageView(cardOfSquare);
            default : return null;
        }
    }

    /**
     * Méthode pour modifier la position d'une carte
     **/
    private void setupCard(ImageView card, int positionX, int positionY) {
        if(!anchorPane.getChildren().contains(card)){
            anchorPane.getChildren().add(card);
        }
        card.setLayoutX(positionX);
        card.setLayoutY(positionY);
        card.setVisible(true);
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

    /** Méthode qui modifie la visibilité des cartes tirés **/
    private void setVisibleCards(boolean visible){
        for (ImageView imageView : userFirstHand) {
            imageView.setVisible(visible);
        }
        for (ImageView imageView : userSecondHand) {
            imageView.setVisible(visible);
        }
        for (ImageView imageView : croupierHand) {
            imageView.setVisible(visible);
        }
    }

    /** Méthode qui affiche les boutons d'actions **/
    private void showAction(){
        actionHitButton.setVisible(true);
        actionSurrenderButton.setVisible(true);
        actionInsuranceButton.setVisible(true);
        actionDoubleButton.setVisible(true);
        actionStandButton.setVisible(true);
        actionSplitButton.setVisible(true);
        if(blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber() == blackJack.getListOfUserHand().get(1).getHand().get(1).getNumber()){
            actionSplitButton.setVisible(true);
        }
    }

    /** Méthode qui cache les boutons d'actions **/
    private void hideAction(){
        actionHitButton.setVisible(false);
        actionSurrenderButton.setVisible(false);
        actionInsuranceButton.setVisible(false);
        actionDoubleButton.setVisible(false);
        actionStandButton.setVisible(false);
        actionSplitButton.setVisible(false);
    }

    /** Méthode pour l'action split (2 carte de même numéro) **/
    private void actionSplit() {
        int valueToken = blackJack.getBet().getBet(user);
        logMenuController.getLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Partager\".");
        actionDoubleButton.setVisible(false);
        actionSplitButton.setVisible(false);
        split = true;

        blackJack.actionSplit();

        zoneBetUser2.setVisible(true);
        setTokenVisible(token2);
        if(valueToken % 2 != 0 ){
            labelToken2.setText(valueToken/2+"");
            labelToken1.setText((valueToken/2) + 1+"");
        }
        else {
            labelToken2.setText(valueToken/2+"");
            labelToken1.setText(valueToken/2+"");
        }
        labelToken2.setVisible(true);

        userSecondHand.add(userFirstHand.get(1));
        userFirstHand.remove(1);
        currentPositionXUserSecondHand = 523;
        setupCard(userSecondHand.get(0), currentPositionXUserSecondHand, ORIGIN_Y_USER);
        currentPositionXUserSecondHand += 25;
        currentPositionXUserFirstHand -= 25;

        labelValueUserFirstHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)));
        labelValueUserSecondHand.setText("Valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(2)));
        labelValueUserSecondHand.setVisible(true);

        logMenuController.getLog("La main n°1 contient la carte "+blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber()+" de "+blackJack.getListOfUserHand().get(1).getHand().get(0).getRank()+".");
        logMenuController.getLog("La mise de la main n°1 est de "+blackJack.getBet().getBet(blackJack.getListOfUserHand().get(1).getUser())+" jetons.");
        logMenuController.getLog("La main n°2 contient la carte "+blackJack.getListOfUserHand().get(2).getHand().get(0).getNumber()+" de "+blackJack.getListOfUserHand().get(2).getHand().get(0).getRank()+".");
        logMenuController.getLog("La mise de la main n°2 est de "+blackJack.getBet().getBet(blackJack.getListOfUserHand().get(2).getUser())+" jetons.");
        logMenuController.getLog("C'est au tour de la main n°1 du joueur "+user.getPseudo());
    }

    /** Méthode pour l'action tirer une carte **/
    private void actionHit(){
        logMenuController.getLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Tirer une carte\".");

        blackJack.actionHit();
        drawCard();
        verification();

    }

    /** Méthode pour l'action prendre une assurance **/
    private void actionInsurance(){
        logMenuController.getLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Assurance\".");
        blackJack.actionInsurance();
        logMenuController.getLog("Le prix de l'assurance est de "+blackJack.getInsuranceUser()+" jetons.");
        actionInsuranceButton.setVisible(false);
    }

    /** Méthode pour l'action doubler la mise **/
    private void actionDouble(){
        hideAction();

        stand = true;
        blackJack.actionDouble();
        logMenuController.getLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Doubler\".");
        logMenuController.getLog("La mise est maintenant de "+blackJack.getBet().getBet(user));
        drawCard();
        labelToken1.setText(blackJack.getBet().getBet(user)+"");
       verification();

    }

    /** Méthode pour l'action rester **/
    private void actionStand(){
        logMenuController.getLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Rester\".");

        if(!split) {
            stand = true;
        }
        else {
            logMenuController.getLog("C'est au tour de la main n°2 du joueur "+user.getPseudo());
        }
        blackJack.actionStand();
        checkSplit();
    }

    /** Méthode pour l'action abandonner **/
    private void actionSurrender(){
        logMenuController.getLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Abandonner\".");
        blackJack.actionSurrender();

        if(!split) {
            stand = true;
            hideAction();
            int tokenLose = (blackJack.getBet().getBet(user)) / 2;

            logMenuController.getLog("Le joueur "+user.getPseudo()+" a perdu "+tokenLose+" jetons.");
            labelProfit.setText("Gain : -" + tokenLose);
            labelToken.setText("Jetons : " + user.getToken());
            newPartyButton.setVisible(true);
            database.insert(databaseName.getTableHistoryPartyGamed(),"\""+user.getEmail()+"\",\""+databaseName.getGameBlackJack()+"\","+tokenLose);
        }
        else {
            surrender = true;
            logMenuController.getLog("C'est au tour de la main n°2 du joueur "+user.getPseudo());
            split = false;
            indexCurrentHand = 2;
        }
    }

    private void drawCard() {
        int positionCardX;
        Card newCard = blackJack.getListOfUserHand().get(indexCurrentHand).getHand().get(blackJack.getListOfUserHand().get(indexCurrentHand).getHand().size() - 1);
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.seconds(0.5);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> cardSound.pause()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> createCardSound()));

        cardSound.play();
        timeline.play();

        if(userSecondHand.size() > 0) {
            logMenuController.getLog("Le joueur " + user.getPseudo() + " pioche la carte " + newCard.getNumber() + " de " + newCard.getRank() + " dans sa main n°" + indexCurrentHand);
        }
        else {
            logMenuController.getLog("Le joueur "+user.getPseudo()+" pioche la carte "+newCard.getNumber()+" de "+newCard.getRank());
        }

        if(indexCurrentHand == 1){
            positionCardX = currentPositionXUserFirstHand;
            labelValueUserFirstHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)));
            currentPositionXUserFirstHand += 25;
        }
        else {
            positionCardX = currentPositionXUserSecondHand;
            labelValueUserSecondHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)));
            currentPositionXUserSecondHand += 25;
        }
        ImageView card = chooseCard(newCard.getNumber(),newCard.getRank());
        userFirstHand.add(card);
        setupCard(card, positionCardX, ORIGIN_Y_USER);
    }

    /** Méthode de vérifcation si le joueur a atteind 21 ou l'a dépassé **/
    private void verification(){
        if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)) > 21){
            if(userSecondHand.size() > 0) {
                logMenuController.getLog("La main n°" + indexCurrentHand + " du joueur " + user.getPseudo() + " a dépassé 21.");
            }
            else {
                logMenuController.getLog("La main du joueur "+user.getPseudo()+" a dépassé 21.");
            }
            if(!split && !surrender) {
                if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)) <= 21){
                    croupierTurn();
                    hideAction();
                    newPartyButton.setVisible(true);
                }
                else {
                    hideAction();
                   distributeGain();
                    newPartyButton.setVisible(true);
                }
            }
            else {
                distributeGain();
            }
        }

        if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)) == 21){
            if(!split) {
                logMenuController.getLog("Le joueur "+user.getPseudo()+" a fait un Black Jack");
            }
            checkSplit();
        }
        if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)) < 21){
            if(stand) {
                hideAction();
                croupierTurn();
                newPartyButton.setVisible(true);
            }
        }
    }

    private void distributeGain(){
        blackJack.betDistribute();
        int gain = user.getToken() - valueTokenUserBegin;

        if(gain < 0){
            logMenuController.getLog("Le croupier a gagné.");
            logMenuController.getLog("Le joueur "+user.getPseudo()+" a perdu "+(gain+"").substring(1)+" jetons.");
        }
        if(gain > 0){
            logMenuController.getLog("Le joueur "+user.getPseudo()+" a gagné.");
            logMenuController.getLog("Le joueur "+user.getPseudo()+" a gagné "+gain+" jetons.");
        }
        if(gain == 0){
            logMenuController.getLog("Egalité, personne ne gagne.");
            logMenuController.getLog("Le joueur "+user.getPseudo()+" ne perd et ne gagne aucun jeton.");
        }

        System.out.println("token gain : "+gain);
        database.insert(databaseName.getTableHistoryPartyGamed(),"\""+user.getEmail()+"\","+gain+",\""+databaseName.getGameBlackJack()+"\"");
        labelProfit.setText("Gain : " + gain);
        labelToken.setText("Jetons : " + user.getToken());
    }

    private void checkSplit() {
        if(!split) {
            hideAction();
            croupierTurn();
            newPartyButton.setVisible(true);
        }
        else{
            split = false;
            indexCurrentHand = 2;
        }
    }

    /** Méthode qui correspond au tour du croupier **/
    private void croupierTurn(){
        logMenuController.getLog("\nC'est au tour du croupier de jouer");
        blackJack.turnCroupier();
        labelValueCroupierHand.setVisible(true);

        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;

        for(int index = 1; index < blackJack.getListOfUserHand().get(0).getHand().size(); index ++) {
            int indexTimeline = index;
            ImageView card = chooseCard(blackJack.getListOfUserHand().get(0).getHand().get(index).getNumber(), blackJack.getListOfUserHand().get(0).getHand().get(index).getRank());
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> cardSound.play()));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  logMenuController.getLog("Le croupier pioche la carte "+blackJack.getListOfUserHand().get(0).getHand().get(indexTimeline).getNumber()+" de "+blackJack.getListOfUserHand().get(0).getHand().get(indexTimeline).getRank())));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> croupierHand.add(card)));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(card, currentPositionXCroupier, ORIGIN_Y_CROUPIER)));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueCroupierHand.setText("valeur de la main : " + blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0)))));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> currentPositionXCroupier += 50));
            timePoint = timePoint.add(Duration.seconds(0.5));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> cardSound.pause()));
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> createCardSound()));
            timePoint = timePoint.add(Duration.seconds(1));
        }

        int valueHandCroupier = blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0));
        if(valueHandCroupier == 21){
           timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  logMenuController.getLog("Le croupier a fait un Black Jack.")));
        }
        if(valueHandCroupier > 21){
            timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le croupier a dépassé 21.")));
        }

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> distributeGain()));

        timeline.play();
    }

    /** Méthode pour une nouvelle partie de black jack **/
    private void newGame(){
        blackJack.reset();
        logMenuController.exitLogMenu();
       BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage, database,soundVolume,backgroundAnimation);
       blackJackMenuController.setting();

    }

    private void animationCardBegin(){
        currentPositionXUserFirstHand = ORIGIN_X_FIRST_HAND;
        currentPositionXCroupier = ORIGIN_X_Croupier;
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;
        Duration pause = Duration.seconds(0.5);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  cardSound.play()));

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le joueur "+user.getPseudo()+" pioche la carte "+blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber()+" de "+blackJack.getListOfUserHand().get(1).getHand().get(0).getRank())));
        timeline.getKeyFrames().add(new KeyFrame(timePoint,e -> userFirstHand.add(chooseCard(blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber(), blackJack.getListOfUserHand().get(1).getHand().get(0).getRank()))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(userFirstHand.get(0), currentPositionXUserFirstHand, ORIGIN_Y_USER)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueUserFirstHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueUserFirstHand.setVisible(true)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> currentPositionXUserFirstHand += 25));
        timePoint = timePoint.add(pause);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le croupier pioche la carte "+blackJack.getListOfUserHand().get(0).getHand().get(0).getNumber()+" de "+blackJack.getListOfUserHand().get(0).getHand().get(0).getRank())));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> croupierHand.add(chooseCard(blackJack.getListOfUserHand().get(0).getHand().get(0).getNumber(), blackJack.getListOfUserHand().get(0).getHand().get(0).getRank()))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(croupierHand.get(0), currentPositionXCroupier, ORIGIN_Y_CROUPIER)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueCroupierHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0)))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueCroupierHand.setVisible(true)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  currentPositionXCroupier += 25));
        timePoint = timePoint.add(pause);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> logMenuController.getLog("Le joueur "+user.getPseudo()+" pioche la carte "+blackJack.getListOfUserHand().get(1).getHand().get(1).getNumber()+" de "+blackJack.getListOfUserHand().get(1).getHand().get(1).getRank()+"\n")));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> userFirstHand.add(chooseCard(blackJack.getListOfUserHand().get(1).getHand().get(1).getNumber(), blackJack.getListOfUserHand().get(1).getHand().get(1).getRank()))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(userFirstHand.get(1), currentPositionXUserFirstHand, ORIGIN_Y_USER)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueUserFirstHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> currentPositionXUserFirstHand += 25));

        timePoint = timePoint.add(pause);
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> showAction()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> createCardSound()));

        timeline.play();
    }

    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
            tokenSound.setVolume(soundVolume);
            cardSound.setVolume(soundVolume);
        }
    }

    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    private void goToLogMenu(){
        logMenuController.exitLogMenu();
        logMenuController.setting();
    }

    private void createCardSound(){
        cardSound = new MediaPlayer(new Media(getClass().getResource("sound/dealingThreeCardsSound.mp3").toExternalForm()));
        cardSound.setVolume(soundVolume);
    }

    private void goToRuleMenu(){
        ruleMenuController.exitRuleMenu();
        ruleMenuController.setting();
    }
}
