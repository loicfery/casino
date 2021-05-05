package sample;

import games.BlackJack;
import games.User;
import games.UserHand;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BlackJackMenuController {

    private final int BET_MIN = 2;
    private final int BET_MAX = 100;
    private final int ORIGIN_X_Croupier = 300;
    private final int ORIGIN_X_FIRST_HAND = 300;
    private final int ORIGIN_X_SECOND_HAND = 523;
    private final int ORIGIN_Y_USER = 400;
    private final int ORIGIN_Y_CROUPIER = 100;

    private BorderPane  root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setUpScene = new SetupScene();
    private User user;

    private BlackJack blackJack;

    private List<Shape> token1 = new ArrayList<>();
    private List<Shape> token2 = new ArrayList<>();
    private List<ImageView> croupierHand = new ArrayList<>();
    private List<ImageView> userFirstHand = new ArrayList<>();
    private List<ImageView> userSecondHand = new ArrayList<>();

    private TextArea textRule = new TextArea();

    private Rectangle zoneBetUser1 = new Rectangle();
    private Rectangle zoneBetUser2 = new Rectangle();

    private Label labelToken = new Label();
    private Label labelProfit = new Label();
    private Label labelPseudo = new Label();
    private Label labelError = new Label();
    private Label labelToken1 = new Label();
    private Label labelToken2 = new Label();
    private Label labelRule = new Label();

    private TextField textBetUser = new TextField();

    private Button validBetButton = new Button();
    private Button returnMainMenuButton = new Button();
    private Button actionSplitButton = new Button();

    private Circle circleRule = new Circle();

    public BlackJackMenuController(User user,Stage stage){
        this.stage = stage;
        this.user = user;

        UserHand userHand = new UserHand(user);
        blackJack = new BlackJack();
    }

    /** Méthode qui initialise l'interface du black jack **/
    public void setting(){
        stage.setTitle("Menu Black Jack");
        scene = new Scene(root,800,800);
        scene.getStylesheets().add(getClass().getResource("blackJackMenu.css").toExternalForm());
        stage.setScene(scene);

        setUpScene.setRectangle(zoneBetUser1,327.0,600.0,174.0,147.0,5.0,5.0,Paint.valueOf("#158000"),Paint.valueOf("BLACK"),5.0,StrokeType.INSIDE,true,anchorPane);
        setUpScene.setRectangle(zoneBetUser2,550,600.0,174.0,147.0,5.0,5.0,Paint.valueOf("#158000"),Paint.valueOf("BLACK"),5.0,StrokeType.INSIDE,false,anchorPane);
        setUpScene.setLabel(labelToken,"Jeton : "+user.getNumberOfToken(), Pos.CENTER_LEFT,30.0,660.0,55.0,163.0,new Font(30.0), Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelProfit,"Gain : 0", Pos.CENTER_LEFT, 30.0,720.0,55.0,163.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelPseudo,"Joueur : "+user.getPseudo(), Pos.CENTER_LEFT,30.0,600.0,55.0,308.0,new Font(30.0),Color.BLACK,true,anchorPane);
        setUpScene.setLabel(labelError,"Erreur :", Pos.CENTER,150.0,460.0,36.0,500.0,new Font(30.0),Color.RED,false,anchorPane);
        setUpScene.setTextField(textBetUser,"Votre mise", Pos.CENTER,280.0,380.0,79.0,252.0,new Font(30.0),true,anchorPane);
        setUpScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);
        setUpScene.setButton(actionSplitButton,"Split",Pos.CENTER,300.0,10.0,72.0,163.0,new Font(30.0),false,anchorPane);
        setUpScene.setButton(validBetButton,"Miser",Pos.CENTER,320.0,500.0,72.0,163.0,new Font(30.0),true,anchorPane);
        setUpScene.setCircle(circleRule,16.0,770.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setUpScene.setLabel(labelRule,"?",Pos.CENTER,754.0,15.0,23,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setTextArea(textRule,50,46.0,600.0,700.0,false,false,anchorPane);

        initToken1();
        initToken2();
        setRule();

        returnMainMenuButton.setOnMouseClicked((event) -> {
            returnMainMenu();
        });

        actionSplitButton.setOnMouseClicked((event)->{
            actionSplit(event);
        });

        validBetButton.setOnMouseClicked((event)->{
            validBet();
        });

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui crée le premier jeton **/
    private void initToken1(){
        Circle circle = new Circle();
        setUpScene.setCircle(circle,41.0,400.0,685.0, Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE,2.0,false,anchorPane);
        token1.add(circle);
        circle = new Circle();
        setUpScene.setCircle(circle,41.0,400.0,675.0,Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE, 2.0,false,anchorPane);
        token1.add(circle);

        Rectangle rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 393.0, 637.0,22.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);
        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 393.0, 687.0,37.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);
        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 362.0, 670.0,14.0,25.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);
        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 415.0, 669.0,14.0,24.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token1.add(rectangle);

        Polyline polyline = new Polyline();
        setUpScene.setPolyline(polyline,379.0,699.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{-13.75,4.25,0.75,-12.0,10.5,-3.0,-4.75,17.0},false,anchorPane);
        token1.add(polyline);
        polyline = new Polyline();
        setUpScene.setPolyline(polyline,424.0,702.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{4.75, 8.5, -12.0, -8.75, -4.75, -17.75, 12.5, -2.75},false,anchorPane);
        token1.add(polyline);
        polyline = new Polyline();
        setUpScene.setPolyline(polyline,371.0,639.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{7.5, 4.25, 19.25, 15.75, 9.5, 27.75, -2.0, 13.25},false,anchorPane);
        token1.add(polyline);
        polyline = new Polyline();
        setUpScene.setPolyline(polyline,425.0,651.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE, new double[]{-13.75, 4.25, -2.0, -7.0, 6.75, 2.5, -4.75, 17.0},false,anchorPane);
        token1.add(polyline);

        circle = new Circle();
        setUpScene.setCircle(circle,23.0,400.0,675.0,Paint.valueOf("DODGERBLUE"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,false,anchorPane);
        token1.add(circle);

        Line line = new Line();
        setUpScene.setLine(line,493.0,714.0,-100.0,-85.25, 0, 0,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token1.add(line);
        line = new Line();
        setUpScene.setLine(line,468.0,700.0,-99.5,-87.75, -1.0, 9.5,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token1.add(line);
        line = new Line();
        setUpScene.setLine(line,522.0,702.0,-97.5,-88.0, 5.25, -5.75,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token1.add(line);

        setUpScene.setLabel(labelToken1,"0",Pos.CENTER,341.0,600.0,148.0,119.0,new Font(30.0),Color.BLACK,false,anchorPane);
    }

    /** Méthode qui crée le deuxième jeton en cas de split **/
    private void initToken2(){
        Circle circle = new Circle();
        setUpScene.setCircle(circle,41.0,625.0,685.0, Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE,2.0,false,anchorPane);
        token2.add(circle);
        circle = new Circle();
        setUpScene.setCircle(circle,41.0,625,675.0,Paint.valueOf("#1d2fd7"),Paint.valueOf("BLACK"), StrokeType.INSIDE, 2.0,false,anchorPane);
        token2.add(circle);

        Rectangle rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,618.0, 637.0,22.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);
        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 618.0, 687.0,37.0,15.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);
        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 587.0, 670.0,14.0,25.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);
        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 640.0, 669.0,14.0,24.0,5.0,5.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),1.0,StrokeType.INSIDE,false,anchorPane);
        token2.add(rectangle);

        Polyline polyline = new Polyline();
        setUpScene.setPolyline(polyline,604.0,699.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{-13.75, 4.25, 0.75, -12.0, 10.5, -3.0, -4.75, 17.0},false,anchorPane);
        token2.add(polyline);
        polyline = new Polyline();
        setUpScene.setPolyline(polyline,649,702.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{4.75, 8.5, -12.0, -8.75, -4.75, -17.75, 12.5, -2.75},false,anchorPane);
        token2.add(polyline);
        polyline = new Polyline();
        setUpScene.setPolyline(polyline,596,639.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE,new double[]{7.5, 4.25, 19.25, 15.75, 9.5, 27.75, -2.0, 13.25},false,anchorPane);
        token2.add(polyline);
        polyline = new Polyline();
        setUpScene.setPolyline(polyline,652,651.0,Paint.valueOf("WHITE"),Paint.valueOf("WHITE"),StrokeType.INSIDE, new double[]{-13.75, 4.25, -2.0, -7.0, 6.75, 2.5, -4.75, 17.0},false,anchorPane);
        token2.add(polyline);

        circle = new Circle();
        setUpScene.setCircle(circle,23.0,625,675.0,Paint.valueOf("DODGERBLUE"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,false,anchorPane);
        token2.add(circle);

        Line line = new Line();
        setUpScene.setLine(line,718.0,714.0,-100.0,-85.25,0, 0,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token2.add(line);
        line = new Line();
        setUpScene.setLine(line,693,700.0,-99.5,-87.75, -1.0, 9.5,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token2.add(line);
        line = new Line();
        setUpScene.setLine(line,747,702.0,-97.5,-88.0, 5.25, -5.75,Paint.valueOf("BLACK"),2.0,false,anchorPane);
        token2.add(line);

        setUpScene.setLabel(labelToken2,"0",Pos.CENTER,566,600.0,148.0,119.0,new Font(30.0),Color.BLACK,false,anchorPane);
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
    public void setTokenVisible(List<Shape> token, boolean visible) {
        for(int index = 0; index < token.size(); index ++){
            token.get(index).setVisible(visible);
        }
    }

    /**
     * Méthode pour valider la mise
     **/
    public void validBet() {
        if (textBetUser.getText().isEmpty()) {
            labelError.setText("Il faut entrer une mise");
            labelError.setVisible(true);
        } else {
            try {
                int valueOfBet = Integer.parseInt(textBetUser.getText());

                if (valueOfBet < BET_MIN || valueOfBet > BET_MAX) {
                    labelError.setText("Vous ne pouvez miser qu'entre 2 et 100 jetons");
                    labelError.setVisible(true);
                } else {
                    int newTokenUser = user.getNumberOfToken() - valueOfBet;
                    if (newTokenUser < 0) {
                        labelError.setText("Vous n'avez pas assez de jeton");
                        labelError.setVisible(true);
                    } else {
                        labelError.setVisible(false);
                        // ajouter valueOfBet à bet dans la classe BlackJack
                        textBetUser.setVisible(false);
                        validBetButton.setVisible(false);

                        user.removeToken(valueOfBet);
                        labelToken1.setText("1"); // recup valeur de bet
                        labelToken.setText("Jetons : " + user.getNumberOfToken());

                        setTokenVisible(token1,true);
                        startingGame();
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
    public void startingGame() {
        int positionXUser = ORIGIN_X_FIRST_HAND;
        int positionXCroupier = ORIGIN_X_Croupier;
        ImageView card;

        // test de fonctionnement
        card = chooseCard(1, "HEART");
        userFirstHand.add(card);
        setUpCard(card, positionXUser, ORIGIN_Y_USER);
        positionXUser += 50;

        setUpCard(chooseCard(8, "SPADE"), positionXCroupier, ORIGIN_Y_CROUPIER);
        positionXCroupier += 50;

        card = chooseCard(12, "HEART");
        userFirstHand.add(card);
        setUpCard(card, positionXUser, ORIGIN_Y_USER);
        positionXUser += 50;
    }

    /**
     * Méthode pour l'action split (2 carte de même numéro)
     **/
    public void actionSplit(MouseEvent mouseEvent) {
        zoneBetUser2.setVisible(true);
        setTokenVisible(token2,true);
        labelToken2.setText(labelToken1.getText());



        userSecondHand.add(userFirstHand.get(userFirstHand.size() - 1));
        userFirstHand.remove(userFirstHand.size() - 1);
        setUpCard(userSecondHand.get(0), ORIGIN_X_SECOND_HAND, ORIGIN_Y_USER);
    }

    /**
     * Méthode pour retourner une carte précise parmis les 52 cartes
     **/
    public ImageView chooseCard(int cardNumber, String cardRank) {
        switch (cardNumber) {
            case 1:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/asOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/asOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/asOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/asOfSquare.jpg").toExternalForm()));
            case 2:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/twoOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/twoOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/twoOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/twoOfSquare.jpg").toExternalForm()));
            case 3:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/threeOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/threeOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/threeOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/threeOfSquare.jpg").toExternalForm()));
            case 4:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/fourOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fourOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fourOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fourOfSquare.jpg").toExternalForm()));
            case 5:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/fiveOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fiveOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fiveOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/fiveOfSquare.jpg").toExternalForm()));
            case 6:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/sixOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sixOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sixOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sixOfSquare.jpg").toExternalForm()));
            case 7:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/sevenOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sevenOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sevenofClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/sevenOfSquare.jpg").toExternalForm()));
            case 8:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/eightOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/eightOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/eightOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/eightOfSquare.jpg").toExternalForm()));
            case 9:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/nineOfheart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/nineOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/nineOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/nineOfSquare.jpg").toExternalForm()));
            case 10:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/tenOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/tenOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/tenOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/tenOfSquare.jpg").toExternalForm()));
            case 11:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/jackOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/jackOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/jackOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/jackOfSquare.jpg").toExternalForm()));
            case 12:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/queenOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/queenOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/queenOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/queenOfSquare.jpg").toExternalForm()));
            case 13:
                return getCardByRank(cardRank, new Image(getClass().getResource("image/cartes/kingOfHeart.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/kingOfSpade.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/kingOfClover.jpg").toExternalForm()), new Image(getClass().getResource("image/cartes/kingOfClover.jpg").toExternalForm()));
            default:
                return null;
        }
    }

    /**
     * Méthode pour retourner une carte parmis 4 cartes et un symbole
     **/
    private ImageView getCardByRank(String cardRank, Image cardOfHeart, Image cardOfSpade, Image cardOfClover, Image cardOfSquare) {
        switch (cardRank) {
            case "HEART":
                return new ImageView(cardOfHeart);
            case "SPADE":
                return new ImageView(cardOfSpade);
            case "CLOVER":
                return new ImageView(cardOfClover);
            case "SQUARE":
                return new ImageView(cardOfSquare);
            default:
                return null;
        }
    }

    /**
     * Méthode pour modifier la position d'une carte
     **/
    private void setUpCard(ImageView card, int positionX, int positionY) {
        anchorPane.getChildren().add(card);
        card.setLayoutX(positionX);
        card.setLayoutY(positionY);
        card.setVisible(true);
    }

    /**
     * Méthode pour quitter le jeu et retourner dans le menu principale
     **/
    public void returnMainMenu(){
        MainMenuController mainMenuController = new MainMenuController(stage,user);
        mainMenuController.setting();
    }

    /**
     * Méthode qui montre la zone de texte contenant les règles
     **/
    public void showRule() {
        textRule.setVisible(true);
    }

    /**
     * Méthode qui cacher la zone de texte contenant les règles
     **/
    public void hideRule() {
        textRule.setVisible(false);
    }
}
