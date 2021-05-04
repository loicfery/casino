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

public class RouletteMenuController {

    private final int ORIGIN_X_1 = 320;
    private final int ORIGIN_Y_1 = 78;
    private final int END_X_1 = 1046;
    private final int END_Y_1 = 222;
    private final int ORIGIN_X_2 = 383;
    private final int ORIGIN_Y_2 = 222;
    private final int END_X_2 = 992;
    private final int END_Y_2 = 318;

    private final int POSITION_X_CASE_ROULETTE_21 = 984;
    private final int POSITION_Y_CASE_ROULETTE_21 = 475;
    private final int POSITION_X_CASE_ROULETTE_2 = 963;
    private final int POSITION_Y_CASE_ROULETTE_2 = 493;
    private final int POSITION_X_CASE_ROULETTE_25 = 972;
    private final int POSITION_Y_CASE_ROULETTE_25 = 513;
    private final int POSITION_X_CASE_ROULETTE_17 = 979;
    private final int POSITION_Y_CASE_ROULETTE_17 = 535;
    private final int POSITION_X_CASE_ROULETTE_34 = 982;
    private final int POSITION_Y_CASE_ROULETTE_34 = 557;
    private final int POSITION_X_CASE_ROULETTE_6 = 982;
    private final int POSITION_Y_CASE_ROULETTE_6 = 579;
    private final int POSITION_X_CASE_ROULETTE_27 = 976;
    private final int POSITION_Y_CASE_ROULETTE_27 = 600;
    private final int POSITION_X_CASE_ROULETTE_13 = 967;
    private final int POSITION_Y_CASE_ROULETTE_13 = 620;
    private final int POSITION_X_CASE_ROULETTE_36 = 957;
    private final int POSITION_Y_CASE_ROULETTE_36 = 641;
    private final int POSITION_X_CASE_ROULETTE_11 = 942;
    private final int POSITION_Y_CASE_ROULETTE_11 = 658;
    private final int POSITION_X_CASE_ROULETTE_30 = 925;
    private final int POSITION_Y_CASE_ROULETTE_30 = 670;
    private final int POSITION_X_CASE_ROULETTE_8 = 906;
    private final int POSITION_Y_CASE_ROULETTE_8 = 680;
    private final int POSITION_X_CASE_ROULETTE_23 = 885;
    private final int POSITION_Y_CASE_ROULETTE_23 = 691;
    private final int POSITION_X_CASE_ROULETTE_10 = 861;
    private final int POSITION_Y_CASE_ROULETTE_10 = 694;
    private final int POSITION_X_CASE_ROULETTE_5 = 841;
    private final int POSITION_Y_CASE_ROULETTE_5 = 693;
    private final int POSITION_X_CASE_ROULETTE_24 = 816;
    private final int POSITION_Y_CASE_ROULETTE_24 = 694;
    private final int POSITION_X_CASE_ROULETTE_16 = 794;
    private final int POSITION_Y_CASE_ROULETTE_16 = 686;
    private final int POSITION_X_CASE_ROULETTE_33 = 775;
    private final int POSITION_Y_CASE_ROULETTE_33 = 674;
    private final int POSITION_X_CASE_ROULETTE_1 = 757;
    private final int POSITION_Y_CASE_ROULETTE_1 = 660;
    private final int POSITION_X_CASE_ROULETTE_20 = 742;
    private final int POSITION_Y_CASE_ROULETTE_20 = 645;
    private final int POSITION_X_CASE_ROULETTE_14 = 729;
    private final int POSITION_Y_CASE_ROULETTE_14 = 624;
    private final int POSITION_X_CASE_ROULETTE_31 = 718;
    private final int POSITION_Y_CASE_ROULETTE_31 = 603;
    private final int POSITION_X_CASE_ROULETTE_9 = 714;
    private final int POSITION_Y_CASE_ROULETTE_9 = 580;
    private final int POSITION_X_CASE_ROULETTE_22 = 716;
    private final int POSITION_Y_CASE_ROULETTE_22 = 556;
    private final int POSITION_X_CASE_ROULETTE_18 = 715;
    private final int POSITION_Y_CASE_ROULETTE_18 = 533;
    private final int POSITION_X_CASE_ROULETTE_29 = 722;
    private final int POSITION_Y_CASE_ROULETTE_29 = 512;
    private final int POSITION_X_CASE_ROULETTE_7 = 734;
    private final int POSITION_Y_CASE_ROULETTE_7 = 492;
    private final int POSITION_X_CASE_ROULETTE_28 = 745;
    private final int POSITION_Y_CASE_ROULETTE_28 = 469;
    private final int POSITION_X_CASE_ROULETTE_12 = 765;
    private final int POSITION_Y_CASE_ROULETTE_12 = 454;
    private final int POSITION_X_CASE_ROULETTE_35 = 786;
    private final int POSITION_Y_CASE_ROULETTE_35 = 443;
    private final int POSITION_X_CASE_ROULETTE_3 = 807;
    private final int POSITION_Y_CASE_ROULETTE_3 = 433;
    private final int POSITION_X_CASE_ROULETTE_26 = 829;
    private final int POSITION_Y_CASE_ROULETTE_26 = 426;
    private final int POSITION_X_CASE_ROULETTE_0 = 850;
    private final int POSITION_Y_CASE_ROULETTE_0 = 425;
    private final int POSITION_X_CASE_ROULETTE_32 = 875;
    private final int POSITION_Y_CASE_ROULETTE_32 = 428;
    private final int POSITION_X_CASE_ROULETTE_15 = 898;
    private final int POSITION_Y_CASE_ROULETTE_15 = 433;
    private final int POSITION_X_CASE_ROULETTE_19 = 920;
    private final int POSITION_Y_CASE_ROULETTE_19 = 441;
    private final int POSITION_X_CASE_ROULETTE_4 = 938;
    private final int POSITION_Y_CASE_ROULETTE_4 = 455;
    private final int RADIUS_TOKEN = 16;
    private final int ORIGIN_X_TOKEN = 336;
    private final int ORIGIN_Y_TOKEN = 262;

    private int tokenUsed = 0;
    private int indexTokenRemove = 0;
    private boolean startingGame = false;
    private List<Circle> listOfCircleToken = new ArrayList<>();
    private List<Label> listLabelToken = new ArrayList<>();
    private List<Case> listOfCase = new ArrayList<>();
    private List<InformationTokenBet> listOfTokenUsed = new ArrayList<>();
    private List<Integer> listPositionXCaseRoulette = new ArrayList<>();
    private List<Integer> listPositionYCaseRoulette = new ArrayList<>();

    private BorderPane root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setUpScene = new SetupScene();
    private User user;

    private List<Rectangle> listOfRectangleGameBoard = new ArrayList<>();
    private List<Label> listOfLabelGameBoard = new ArrayList<>();

    private ImageView roulette = new ImageView();

    private Label labelProfit = new Label();
    private Label labelTokenUser = new Label();
    private Label labelPseudo = new Label();
    private Label labelInformationBetToken = new Label();
    private Label labelError = new Label();
    private Label labelRule = new Label();

    private Button modifyBetTokenButton = new Button();
    private Button validBetTokenButton = new Button();
    private Button startingGameButton = new Button();
    private Button returnMainMenuButton = new Button();

    private TextField textBetToken = new TextField();

    private Circle circleBallRoulette = new Circle();
    private Circle circleRule = new Circle();

    private TextArea textRule = new TextArea();

    public RouletteMenuController(User user, Stage stage){
        this.user = user;
        this.stage = stage;
    }

    public void setting(){
        stage.setTitle("Roulette");
        scene = new Scene(root,1100,800);
        scene.getStylesheets().add(getClass().getResource("rouletteMenu.css").toExternalForm());
        stage.setScene(scene);

        setupGameBoard();
        setCasePosition();
        setCaseRoulette();
        setRule();

        setUpScene.setImageView(roulette,638.0,350.0,425.0,471.0, new Image(getClass().getResource("image/roulette.jpg").toExternalForm()),true,anchorPane);
        roulette.setPickOnBounds(true);
        roulette.setPreserveRatio(true);

        setUpScene.setLabel(labelProfit,"Gain : 0",Pos.CENTER_LEFT,14.0,718.0,68.0,607.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelTokenUser,"Jetons : "+user.getNumberOfToken(),Pos.CENTER_LEFT,14.0,640.0,68.0,613.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelPseudo,"Joueur : "+user.getPseudo(),Pos.CENTER_LEFT,14.0,563.0,68.0,613.0,new Font(30.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelInformationBetToken,"Information",Pos.CENTER,212.0,368.0,202.0,387.0,new Font(18),Paint.valueOf("BLACK"),false,anchorPane);
        setUpScene.setButton(modifyBetTokenButton,"Miser",Pos.CENTER,295.0,696.0,68.0,216.0,new Font(20.0),false,anchorPane);
        setUpScene.setButton(validBetTokenButton,"Miser",Pos.CENTER,295.0,696.0,68.0,216.0,new Font(20.0),false,anchorPane);
        setUpScene.setTextField(textBetToken,"Mise",Pos.CENTER,234.0,597.0,80.0,338.0,new Font(20.0),false,anchorPane);
        setUpScene.setButton(startingGameButton,"Starting",Pos.CENTER,25.0,416.0,102.0,251.0,new Font(20.0),true,anchorPane);
        setUpScene.setLabel(labelError,"Erreur",Pos.CENTER,197.0,600.0,60.0,387.0,new Font(20.0),Paint.valueOf("RED"),false,anchorPane);
        setUpScene.setCircle(circleBallRoulette,7.0,851.0,563.0,Paint.valueOf("BLACK"),Paint.valueOf("BLACK"),StrokeType.INSIDE,0.0,false,anchorPane);
        setUpScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,57.0,123.0,new Font(20.0),true,anchorPane);
        setUpScene.setCircle(circleRule,16.0,1070.0,30.0,Paint.valueOf("#a1a1a1"),Paint.valueOf("BLACK"),StrokeType.INSIDE,1.0,true,anchorPane);
        setUpScene.setLabel(labelRule,"?",Pos.CENTER,1055.0,15.0,23,32.0,new Font(20.0),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setTextArea(textRule,360,46.0,600.0,700.0,false,false,anchorPane);

        anchorPane.setOnMouseClicked((event)->{
            choosePositionToken(event);
        });

        validBetTokenButton.setOnMouseClicked((event)->{
            validBetToken();
        });

        modifyBetTokenButton.setOnMouseClicked((event)->{
            modifyBetToken();
        });

        startingGameButton.setOnMouseClicked((event)->{
            startingGame();
        });

        returnMainMenuButton.setOnMouseClicked((event)->{
            returnMainMenu();
        });

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

    private void setupGameBoard(){
        Rectangle rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,434.0,78.0,48.0,51.0,5.0,5.0, Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0, StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,485.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,536.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,587.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,638.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,689.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,740.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,791.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,842.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,893.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,944.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,995.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,383.0,78.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,434.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,485.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 536.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,587.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,638.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,689.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 740.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,791.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,842.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,893.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,944.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 995.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 383.0,126.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,434.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,536.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,485.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,587.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,638.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,689.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,740.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,791.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 842.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,893.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,944.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,995.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,383.0,174.0,48.0,51.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,383.0,222.0,48.0,204.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,587.0,222.0,48.0,204.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,791.0,222.0,48.0,204.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,383.0,270.0,48.0,100.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,481.0,270.0,48.0,106.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,587.0,270.0,48.0,100.0,5.0,5.0,Paint.valueOf("RED"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,685.0,270.0,48.0,106.0,5.0,5.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,791.0,270.0,48.0,100.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle, 889.0,270.0,48.0,106.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);

        rectangle = new Rectangle();
        setUpScene.setRectangle(rectangle,319.0,78.0,145.0,64.0,5.0,5.0,Paint.valueOf("GREEN"),Paint.valueOf("WHITE"),2.0,StrokeType.INSIDE,true,anchorPane);
        listOfRectangleGameBoard.add(rectangle);


        Label label = new Label();
        setUpScene.setLabel(label,"3", Pos.CENTER,383.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"2", Pos.CENTER,383.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"1", Pos.CENTER,383.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"4", Pos.CENTER,434.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"5", Pos.CENTER,434.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"6", Pos.CENTER,434.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"7", Pos.CENTER,485.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"8", Pos.CENTER,485.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"9", Pos.CENTER,485.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"10", Pos.CENTER,536.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"11", Pos.CENTER,536.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"12", Pos.CENTER,536.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"13", Pos.CENTER,587.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"14", Pos.CENTER,587.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"15", Pos.CENTER,587.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"16", Pos.CENTER,638.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"17", Pos.CENTER,638.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"18", Pos.CENTER,638.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"19", Pos.CENTER,689.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"20", Pos.CENTER,689.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"21", Pos.CENTER,689.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"22", Pos.CENTER,740.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"23", Pos.CENTER,740.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"24", Pos.CENTER,740.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"25", Pos.CENTER,791.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"26", Pos.CENTER,791.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"27", Pos.CENTER,791.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"28", Pos.CENTER,842.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"29", Pos.CENTER,842.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"30", Pos.CENTER,842.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"31", Pos.CENTER,893.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"32", Pos.CENTER,893.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"33", Pos.CENTER,893.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"34", Pos.CENTER,944.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"35", Pos.CENTER,944.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"36", Pos.CENTER,944.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"3rd", Pos.CENTER,995.0,78.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"2nd", Pos.CENTER,995.0,126.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"1st", Pos.CENTER,995.0,174.0,48.0,51.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"1 - 12", Pos.CENTER,383.0,222.0,48.0,204.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"13 - 24", Pos.CENTER,587.0,222.0,48.0,204.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"25 - 36", Pos.CENTER,791.0,222.0,48.0,204.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"1 - 18", Pos.CENTER,383.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"PAIRE", Pos.CENTER,484.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"ROUGE", Pos.CENTER,587.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"NOIR", Pos.CENTER,688.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"IMPAIRE", Pos.CENTER,791.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"19 - 36", Pos.CENTER,892.0,270.0,48.0,100.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);

        label = new Label();
        setUpScene.setLabel(label,"0", Pos.CENTER,320.0,78.0,145.0,64.0,new Font(20.0),Paint.valueOf("WHITE"),true,anchorPane);
        listOfLabelGameBoard.add(label);
    }

    /** Création de la liste des cases pour parier **/
    public void setCasePosition(){
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

    /** Création de la liste des position des cases de la roulette **/
    public void setCaseRoulette(){
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_21);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_21);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_2);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_2);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_25);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_25);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_17);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_17);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_34);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_34);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_6);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_6);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_27);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_27);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_13);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_13);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_36);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_36);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_11);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_11);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_30);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_30);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_8);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_8);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_23);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_23);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_10);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_10);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_5);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_5);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_24);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_24);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_16);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_16);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_33);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_33);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_1);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_1);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_20);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_20);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_14);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_14);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_31);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_31);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_9);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_9);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_22);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_22);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_18);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_18);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_29);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_29);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_7);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_7);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_28);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_28);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_12);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_12);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_35);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_35);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_3);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_3);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_26);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_26);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_0);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_0);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_32);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_32);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_15);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_15);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_19);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_19);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_4);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_4);

    }

    /** Action pour poser un jeton ou modifier sa position ou sa valeur **/
    public void choosePositionToken(MouseEvent mouseEvent) {
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

    /** Returne les cases de la combinaison à partir de la position du jeton **/
    public String getCasesBet(List<Case> listOfCaseToken, Circle circleToken){
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
                        for(Case cases : getCombinaisonCase(listOfCaseToken.get(0))){
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
                        for(Case cases : getCombinaisonCase(listOfCaseToken.get(0))){
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

    /** Retourne l'indice du jeton à modifier **/
    public int getTokenToRemove(int positionX, int positionY){
        for(int index = 0; index < tokenUsed; index ++){
            int distance = (int) Math.sqrt(Math.pow(Math.abs((positionX - listOfTokenUsed.get(index).getCircleToken().getLayoutX())) ,2) + Math.pow(Math.abs((positionY - listOfTokenUsed.get(index).getCircleToken().getLayoutY())) ,2));
            if(distance <= listOfTokenUsed.get(index).getCircleToken().getRadius()){
                return index;
            }
        }
        return -1;
    }

    /** Modifie la position d'un jeton **/
    public void setPositionToken(int positionX, int positionY, Circle circleToken, Label labelToken, boolean state){
        circleToken.setLayoutX(positionX);
        circleToken.setLayoutY(positionY);
        circleToken.setVisible(state);
        labelToken.setLayoutX(positionX - 10);
        labelToken.setLayoutY(positionY - 10);
        labelToken.setVisible(state);
    }

    /** Vérifie la zone pour poser un jeton **/
    public boolean isPositionMouseGood(int positionX, int positionY){
        if((positionX >= ORIGIN_X_1 && positionX <= END_X_1) && (positionY >= ORIGIN_Y_1 && positionY <= END_Y_1)){
            return true;
        }
        else{
            if((positionX >= ORIGIN_X_2 && positionX <= END_X_2) && (positionY >= ORIGIN_Y_2 && positionY <= END_Y_2)){
                return true;
            }
            else {
                return false;
            }
        }
    }

    /** Récupère toutes les informations du jeton posé **/
    public void betToken(int positionXToken, int positionYToken, Circle circleToken, Label labelToken){
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

    /** Vérifie si un jeton est passé sur une case **/
    public boolean tokenInTheCase(Case cases, int positionXToken, int positionYToken){
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

    /** Bouton pour valider la modification d'un jeton **/
    public void modifyBetToken() {
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

    /** Boutton pour valider un nouveau jeton posé  **/
    public void validBetToken(){
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

    /** Retourne la case qui possède une certaine valeur **/
    public Case getCase(String valueOfCase){
        for(int index = 0; index < listOfCase.size(); index ++){
            if(listOfCase.get(index).getValueCase().equals(valueOfCase)){
                return listOfCase.get(index);
            }
        }
        return null;
    }

    /** Retourne les autres cases d'une combinaisons d'après une case **/
    public List<Case> getCombinaisonCase(Case cases){
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

    /** Boutton pour lancer la roulette et mettre fin aux mises **/
    public void startingGame() {
        if(tokenUsed == 0){
            labelError.setText("Il faut placer un jeton au minimum");
            labelError.setVisible(true);
        }
        else{
            startingGame = true;
            labelError.setVisible(false);
            startingGameButton.setVisible(false);

            //TranslateTransition translateTransition = new TranslateTransition();
            // translateTransition.setDuration(Duration.seconds(1));
            //translateTransition.setNode(circleBallRoulette);

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
    public void createToken(){
        Circle circle = new Circle();
        setUpScene.setCircle(circle,16.0,336.0,262.0,Paint.valueOf("BLACK"),Paint.valueOf("WHITE"),StrokeType.INSIDE,2.0,false,anchorPane);
        listOfCircleToken.add(circle);

        Label label = new Label();
        setUpScene.setLabel(label,"",Pos.CENTER,325.0,252.0,21.0,23.0,new Font(10),Paint.valueOf("WHITE"),false,anchorPane);
        listLabelToken.add(label);
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
