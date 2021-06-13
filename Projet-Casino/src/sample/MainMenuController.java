package sample;

import games.Database;
import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;;import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainMenuController implements InterfaceMenu{

    private BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private SettingMenuController settingMenuController;
    private final Database database;
    private Language language;
    private Scene scene;

    private final Button logoutButton = new Button();
    private final Button informationMenuButton = new Button();
    private final Button historyGamePlayedButton = new Button();

    private final ImageView pictureBlackJackMenu = new ImageView();
    private final ImageView pictureSlotMachineMenu = new ImageView();
    private final ImageView pictureRouletteMenu = new ImageView();
    private final ImageView pictureShopMenu = new ImageView();

    private final Circle circleSetting = new Circle();

    private double soundVolume;
    private boolean backgroundAnimation;
    private double sizeX;
    private double sizeY;

    private final List<Double> listOfLayoutX = new ArrayList<>();
    private final List<Double> listOfLayoutY = new ArrayList<>();
    private final List<Double> listOfPrefHeight = new ArrayList<>();
    private final List<Double> listOfPrefWidth = new ArrayList<>();
    private final List<Double> listOfFontSize = new ArrayList<>();
    private final List<Double> listOfRadius = new ArrayList<>();

    public MainMenuController(Stage stage,User user, Database database,Language language, double soundVolume, boolean backgroundAnimation, double sizeX, double sizeY){
        this.stage = stage;
        this.user = user;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation,sizeX,sizeY);
        this.database = database;
        this.language = language;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /** Méthode qui intialise l'interface du menu principal **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        scene = new Scene(root, sizeX * 800, sizeY * 800);
        scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setImageView(pictureBlackJackMenu,sizeX * 20.0,sizeY * 170.0,sizeY * 300.0,sizeX * 370.0,new Image(new File("Projet-Casino/image/blackjack.png").toURI().toString()),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,true,anchorPane);
        setupScene.setImageView(pictureSlotMachineMenu,sizeX * 410.0,sizeY * 490.0,sizeY * 290.0,sizeX * 370.0,new Image(new File("Projet-Casino/image/slot_machine.jpg").toURI().toString()),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,true,anchorPane);
        setupScene.setImageView(pictureRouletteMenu,sizeX * 20.0,sizeY * 490.0,sizeY * 290.0,sizeX * 370.0,new Image(new File("Projet-Casino/image/roulette2.jpg").toURI().toString()),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,true,anchorPane);
        setupScene.setImageView(pictureShopMenu,sizeX * 315,sizeY * 20,sizeY * 80,sizeX * 120,new Image(new File("Projet-Casino/image/shop.jpg").toURI().toString()),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,true,anchorPane);

        setupScene.setButton(logoutButton,language.getLine("mainMenuLogoutButton"), Pos.CENTER,sizeX * 20,sizeY * 20,sizeY * 80,sizeX * 120,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setButton(informationMenuButton,language.getLine("informationLabel"),Pos.CENTER,sizeX * 170,sizeY * 20,sizeY * 80,sizeX * 120,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setButton(historyGamePlayedButton,language.getLine("mainMennuHistoryGamePlayedButton"),Pos.CENTER,sizeX * 470,sizeY * 20,sizeY * 80,sizeX * 150,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);

        setupScene.setCircle(circleSetting,Math.max(sizeX,sizeY) * 30,sizeX * 750,sizeY * 40,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())), Paint.valueOf("GREEN"), StrokeType.INSIDE,1.0,listOfLayoutX,listOfLayoutY,listOfRadius,true,anchorPane);

        logoutButton.setOnMouseClicked((event)-> goToConnexionMenu());
        informationMenuButton.setOnMouseClicked((event)-> goToInformationMenu());
        pictureShopMenu.setOnMouseClicked((event)-> goToShopMenu());
        pictureBlackJackMenu.setOnMouseClicked((event)-> goToBlackJackMenu());
        pictureSlotMachineMenu.setOnMouseClicked((event)-> goToSlotMachineMenu());
        pictureRouletteMenu.setOnMouseClicked((event)-> goToRouletteMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        historyGamePlayedButton.setOnMouseClicked((event)-> goToHistoryGamePlayedMenu());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui redirrige vers l'interface du menu de connexion **/
    private void goToConnexionMenu(){
        settingMenuController.exitSettingMenu();
        ConnexionMenuController controller = new ConnexionMenuController(stage,database,language,sizeX,sizeY);
        controller.setting();
    }

    /** Méthode qui redirrige vers l'interface du menu d'information **/
    private void goToInformationMenu(){
        settingMenuController.exitSettingMenu();
        InformationMenuController informationMenuController = new InformationMenuController(user,stage, database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
        informationMenuController.setting();
    }

    /** Méthode qui redirrige vers l'interface du menu de boutique **/
    private void goToShopMenu() {
        settingMenuController.exitSettingMenu();
        ShopMenuController shopMenuController = new ShopMenuController(stage,user,database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
        shopMenuController.setting();
    }

    /** Méthode qui redirrige vers l'interface du menu du jeu black jack **/
    private void goToBlackJackMenu(){
        settingMenuController.exitSettingMenu();
        BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage, database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
        blackJackMenuController.setting();
    }

    /** Méthode qui redirrige vers l'interface du menu du jeu machine à sous **/
    private void goToSlotMachineMenu(){
        settingMenuController.exitSettingMenu();
        SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage,database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
        slotMachineMenuController.setting();
    }

    /** Méthode qui redirrige vers l'interface du menu du jeu roulette **/
    private void goToRouletteMenu(){
        settingMenuController.exitSettingMenu();
        RouletteMenuController rouletteMenuController = new RouletteMenuController(user,stage, database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
        rouletteMenuController.setting();
    }

    /** Méthode qui redirrige vers l'interface du menu de l'historique des parties jouées **/
    private void goToHistoryGamePlayedMenu(){
        settingMenuController.exitSettingMenu();
        HistoryGamePlayedMenuController historyGamePlayedMenuController = new HistoryGamePlayedMenuController(user,stage,database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
        historyGamePlayedMenuController.setting();
    }

    /** Méthode qui modifie le volume sonore des effets sonores **/
    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
        }
    }

    /** Méthode  qui active/désactive les animations en arrière plan **/
    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    /** Méthode qui ouvre l'interface du menu des paramètres **/
    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    /** Méthode qui change la langue **/
    public void setLanguage(Language language){ this.language = language; }

    public void setSizeX(double sizeX){ this.sizeX = sizeX; }

    public void setSizeY(double sizeY){ this.sizeY = sizeY; }

    /** Méthode qui rafraichit ce menu **/
    public void refresh(){
        logoutButton.setText(language.getLine("mainMenuLogoutButton"));
        informationMenuButton.setText(language.getLine("informationLabel"));
        historyGamePlayedButton.setText(language.getLine("mainMennuHistoryGamePlayedButton"));

        refreshPosition();
    }

    private void refreshPosition(){
        scene.setRoot(new BorderPane());
        scene = new Scene(root, sizeX * 800, sizeY * 800);
        stage.setScene(scene);
        root.setStyle("-fx-background-color: green");

        int indexLayoutX = 0, indexLayoutY = 0, indexPrefHeight = 0, indexPrefWidth = 0, indexFontSize = 0, indexRadius = 0;

        for(Node node : anchorPane.getChildren()){
            if(node instanceof Label){
                SetupScene.refreshPositionLabel(node,listOfLayoutX.get(indexLayoutX),listOfLayoutY.get(indexLayoutY),listOfPrefHeight.get(indexPrefHeight),listOfPrefWidth.get(indexPrefWidth),listOfFontSize.get(indexFontSize),sizeX,sizeY);
                indexLayoutX ++;
                indexLayoutY ++;
                indexPrefHeight ++;
                indexPrefWidth ++;
                indexFontSize ++;
            }
            if(node instanceof TextField){
                SetupScene.refreshPositionTextField(node,listOfLayoutX.get(indexLayoutX),listOfLayoutY.get(indexLayoutY),listOfPrefHeight.get(indexPrefHeight),listOfPrefWidth.get(indexPrefWidth),listOfFontSize.get(indexFontSize),sizeX,sizeY);
                indexLayoutX ++;
                indexLayoutY ++;
                indexPrefHeight ++;
                indexPrefWidth ++;
                indexFontSize ++;
            }
            if(node instanceof Button){
                SetupScene.refreshPositionButton(node,listOfLayoutX.get(indexLayoutX),listOfLayoutY.get(indexLayoutY),listOfPrefHeight.get(indexPrefHeight),listOfPrefWidth.get(indexPrefWidth),listOfFontSize.get(indexFontSize),sizeX,sizeY);
                indexLayoutX ++;
                indexLayoutY ++;
                indexPrefHeight ++;
                indexPrefWidth ++;
                indexFontSize ++;
            }
            if(node instanceof Circle){
                SetupScene.refreshPositionCircle(node,listOfLayoutX.get(indexLayoutX),listOfLayoutY.get(indexLayoutY),listOfRadius.get(indexRadius),sizeX,sizeY);
                indexLayoutX ++;
                indexLayoutY ++;
                indexRadius ++;
            }
            if(node instanceof ImageView){
                SetupScene.refreshPositionImageView(node,listOfLayoutX.get(indexLayoutX),listOfLayoutY.get(indexLayoutY),listOfPrefHeight.get(indexPrefHeight), listOfPrefWidth.get(indexPrefWidth),sizeX,sizeY);
                indexLayoutX ++;
                indexLayoutY ++;
                indexPrefHeight ++;
                indexPrefWidth ++;
            }
        }
    }
}