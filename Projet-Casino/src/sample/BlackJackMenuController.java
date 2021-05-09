package sample;

import games.BlackJack;
import games.Card;
import games.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    private final BlackJack blackJack;

    private MediaPlayer cardSound;
    private MediaPlayer tokenSound;

    private double soundVolume;
    private boolean backgroundAnimation;

    private boolean split = false;
    private boolean stand = false;
    private int indexCurrentHand = 1;
    private final int valueTokenUserBegin;

    private final List<Shape> token1 = new ArrayList<>();
    private final List<Shape> token2 = new ArrayList<>();
    private final List<ImageView> croupierHand = new ArrayList<>();
    private final List<ImageView> userFirstHand = new ArrayList<>();
    private final List<ImageView> userSecondHand = new ArrayList<>();

    private final TextArea textRule = new TextArea();
    private final TextArea textLog = new TextArea();

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
    private final Label labelLogParty = new Label();

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

    public BlackJackMenuController(User user,Stage stage, double soundVolume, boolean backgroundAnimation){
        this.stage = stage;
        this.user = user;
        this.soundVolume = soundVolume;
        this.valueTokenUserBegin = user.getNumberOfToken();
        this.backgroundAnimation = backgroundAnimation;

        blackJack = new BlackJack(user);
    }

    /** Méthode qui initialise l'interface du black jack **/
    public void setting(){
        stage.setTitle("Menu Black Jack");
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("blackJackMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setRectangle(zoneBetUser1,327.0,600.0,174.0,147.0,5.0,5.0,Paint.valueOf("#158000"),Paint.valueOf("BLACK"),5.0,StrokeType.INSIDE,true,anchorPane);
        setupScene.setRectangle(zoneBetUser2,550,600.0,174.0,147.0,5.0,5.0,Paint.valueOf("#158000"),Paint.valueOf("BLACK"),5.0,StrokeType.INSIDE,false,anchorPane);
        setupScene.setLabel(labelToken,"Jetons : "+user.getNumberOfToken(), Pos.CENTER_LEFT,30.0,660.0,55.0,163.0,new Font(30.0), Paint.valueOf("BLACK"),true,anchorPane);
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
        setupScene.setLabel(labelLogParty,"Log",Pos.CENTER,700.0,15.0,23.0,50.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextArea(textLog,200,46.0,500.0,500.0,false,false,anchorPane);

        setupScene.setCircle(circleRule,16.0,770.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setupScene.setLabel(labelRule,"?",Pos.CENTER,754.0,15.0,23,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextArea(textRule,50,46.0,600.0,700.0,false,false,anchorPane);

        setRule();
        setLog("Log de la partie\n");
        setLog("Le joueur "+user.getPseudo()+" démarre une nouvelle partie.");

        cardSound = new MediaPlayer(new Media(getClass().getResource("sound/dealingThreeCardsSound.mp3").toExternalForm()));
        cardSound.setCycleCount(Transition.INDEFINITE);
        cardSound.setVolume(soundVolume);

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
        labelLogParty.setOnMouseEntered((event)-> showLog());
        labelLogParty.setOnMouseExited((event)->  hideLog());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        blackJack.addUserBet(user);

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
     * Méthode pour écrire les règles du jeux dans une zone de texte
     **/
    private void setRule() {
        try {
            File fileRuleBlackJack = new File("Regles-BlackJack.txt");
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
                    int newTokenUser = user.getNumberOfToken() - valueOfBet;
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
                        labelToken.setText("Jetons : " + (user.getNumberOfToken() - valueOfBet));

                        setLog("Le joueur "+user.getPseudo()+" mise "+valueOfBet+" jetons");

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

        showAction();
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
        MainMenuController mainMenuController = new MainMenuController(stage,user,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    /**
     * Méthode qui montre la zone de texte contenant les règles
     **/
    private void showRule() {
       setVisibleCards(false);
       textRule.setVisible(true);
    }

    /**
     * Méthode qui cache la zone de texte contenant les règles
     **/
    private void hideRule() {
        textRule.setVisible(false);
        setVisibleCards(true);
    }

    /**
     * Méthode qui affiche les log de la partie
     */
    private void showLog(){
        setVisibleCards(false);
        textLog.setVisible(true);
    }

    /**
     * Méthode qui cache les log de la partie
     */
    private void hideLog(){
        textLog.setVisible(false);
        setVisibleCards(true);
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
        setLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Partager\".");
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
    }

    /** Méthode pour l'action tirer une carte **/
    private void actionHit(){
        setLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Tirer une carte\".");
        blackJack.actionHit();

        drawCard();

        verification();

    }

    /** Méthode pour l'action prendre une assurance **/
    private void actionInsurance(){
        setLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Assurance\".");
        blackJack.actionInsurance();
        actionInsuranceButton.setVisible(false);
    }

    /** Méthode pour l'action doubler la mise **/
    private void actionDouble(){
        hideAction();

        stand = true;

        blackJack.actionDouble();

        setLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Doubler\".");
        setLog("La mise est maintenant de "+blackJack.getBet().getBet(user));

        drawCard();

        labelToken1.setText(blackJack.getBet().getBet(user)+"");

       verification();

    }

    /** Méthode pour l'action rester **/
    private void actionStand(){
        setLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Rester\".");
        if(!split) {
            stand = true;
        }
        blackJack.actionStand();

        checkSplit();
    }

    /** Méthode pour l'action abandonner **/
    private void actionSurrender(){
        if(!split) {
            stand = true;
        }
        setLog("Le joueur "+user.getPseudo()+" a choisit l'action \"Abandonner\".");
        blackJack.actionSurrender();

        if(!split) {
            hideAction();

            labelProfit.setText("Gain : " + blackJack.getBet().getBet(user));
            labelToken.setText("Jetons : " + user.getNumberOfToken());

            newPartyButton.setVisible(true);
        }
        else {
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
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> cardSound.setStartTime(Duration.ZERO)));

        cardSound.play();
        timeline.play();

        setLog("Le joueur "+user.getPseudo()+" pioche la carte "+newCard.getNumber()+" de "+newCard.getRank()+" dans sa main n°"+indexCurrentHand);
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
            if(!split) {
                if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)) <= 21){
                    croupierTurn();
                    distributeGain();
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
                split = false;
                indexCurrentHand = 2;
            }
        }

        if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)) == 21){
            checkSplit();
        }
        if(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(indexCurrentHand)) < 21){
            if(stand) {
                hideAction();
                croupierTurn();

                distributeGain();

                newPartyButton.setVisible(true);
            }
        }
    }

    private void distributeGain(){
        blackJack.betDistribute();
        int gain = user.getNumberOfToken() - valueTokenUserBegin;

        labelProfit.setText("Gain : " + gain);
        labelToken.setText("Jetons : " + user.getNumberOfToken());
    }

    private void checkSplit() {
        if(!split) {
            hideAction();
            croupierTurn();

            distributeGain();

            newPartyButton.setVisible(true);
        }
        else{
            split = false;
            indexCurrentHand = 2;
        }
    }

    /** Méthode qui correspond au tour du croupier **/
    private void croupierTurn(){
        setLog("\nC'est au tour du croupier de jouer");
        blackJack.turnCroupier();

        for(int index = 1; index < blackJack.getListOfUserHand().get(0).getHand().size(); index ++) {
            ImageView card = chooseCard(blackJack.getListOfUserHand().get(0).getHand().get(index).getNumber(), blackJack.getListOfUserHand().get(0).getHand().get(index).getRank());
            setLog("Le croupier pioche la carte "+blackJack.getListOfUserHand().get(0).getHand().get(index).getNumber()+" de "+blackJack.getListOfUserHand().get(0).getHand().get(index).getRank());
            croupierHand.add(card);
            setupCard(card, currentPositionXCroupier, ORIGIN_Y_CROUPIER);
            labelValueCroupierHand.setText("valeur de la main : " + blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0)));
            labelValueCroupierHand.setVisible(true);
            currentPositionXCroupier += 50;
        }
    }

    /** Méthode pour une nouvelle partie de black jack **/
    private void newGame(){
        blackJack.reset();
       BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage,soundVolume,backgroundAnimation);
       blackJackMenuController.setting();
    }

    private void setLog(String newLog){
        textLog.setText(textLog.getText() + "\n" + newLog);
    }

    private void animationCardBegin(){
        currentPositionXUserFirstHand = ORIGIN_X_FIRST_HAND;
        currentPositionXCroupier = ORIGIN_X_Croupier;
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;
        Duration pause = Duration.seconds(0.4);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  cardSound.play()));

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setLog("Le joueur "+user.getPseudo()+" pioche la carte "+blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber()+" de "+blackJack.getListOfUserHand().get(1).getHand().get(0).getRank())));
        timeline.getKeyFrames().add(new KeyFrame(timePoint,e -> userFirstHand.add(chooseCard(blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber(), blackJack.getListOfUserHand().get(1).getHand().get(0).getRank()))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(chooseCard(blackJack.getListOfUserHand().get(1).getHand().get(0).getNumber(), blackJack.getListOfUserHand().get(1).getHand().get(0).getRank()), currentPositionXUserFirstHand, ORIGIN_Y_USER)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueUserFirstHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueUserFirstHand.setVisible(true)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> currentPositionXUserFirstHand += 25));
        timePoint = timePoint.add(pause);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setLog("Le croupier pioche la carte "+blackJack.getListOfUserHand().get(0).getHand().get(0).getNumber()+" de "+blackJack.getListOfUserHand().get(0).getHand().get(0).getRank())));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> croupierHand.add(chooseCard(blackJack.getListOfUserHand().get(0).getHand().get(0).getNumber(), blackJack.getListOfUserHand().get(0).getHand().get(0).getRank()))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(chooseCard(blackJack.getListOfUserHand().get(0).getHand().get(0).getNumber(), blackJack.getListOfUserHand().get(0).getHand().get(0).getRank()), currentPositionXCroupier, ORIGIN_Y_CROUPIER)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueCroupierHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0)))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueCroupierHand.setVisible(true)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e ->  currentPositionXCroupier += 25));
        timePoint = timePoint.add(pause);

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setLog("Le joueur "+user.getPseudo()+" pioche la carte "+blackJack.getListOfUserHand().get(1).getHand().get(1).getNumber()+" de "+blackJack.getListOfUserHand().get(1).getHand().get(1).getRank()+"\n")));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> userFirstHand.add(chooseCard(blackJack.getListOfUserHand().get(1).getHand().get(1).getNumber(), blackJack.getListOfUserHand().get(1).getHand().get(1).getRank()))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> setupCard(chooseCard(blackJack.getListOfUserHand().get(1).getHand().get(1).getNumber(), blackJack.getListOfUserHand().get(1).getHand().get(1).getRank()), currentPositionXUserFirstHand, ORIGIN_Y_USER)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelValueUserFirstHand.setText("valeur de la main : "+blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(1)))));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> currentPositionXUserFirstHand += 25));

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> cardSound.pause()));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> cardSound.setStartTime(Duration.ZERO)));

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
        InterfaceMenuSetting interfaceMenuSetting = new InterfaceMenuSetting(this, soundVolume,backgroundAnimation);
        interfaceMenuSetting.setting();
    }
}
