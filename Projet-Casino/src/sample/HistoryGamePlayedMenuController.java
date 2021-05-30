package sample;

import games.Database;
import games.DatabaseName;
import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import javafx.stage.WindowEvent;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryGamePlayedMenuController implements InterfaceMenu{

    private BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private SettingMenuController settingMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private Language language;

    private final Button returnShopMenuButton = new Button();
    private final Button gameBlackJackButton = new Button();
    private final Button gameSlotMachineButton = new Button();
    private final Button gameRouletteButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button leftInformationButton = new Button();
    private final Button searchUserButton = new Button();

    private final Circle circleSetting = new Circle();

    private final Label titleLabel = new Label();

    private final TextArea textHistory = new TextArea();

    private final TextField textSearchUser = new TextField();

    private double soundVolume;
    private boolean backgroundAnimation;

    private List<String> listOfGameBlackJack;
    private List<String> listOfGameSlotMachine;
    private List<String> listOfGameRoulette;
    private List<String> currentList = new ArrayList<>();
    private int indexList = 0;
    private boolean ADMIN = false;
    private String currentGame;

    public HistoryGamePlayedMenuController(User user, Stage stage, Database database, Language language, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        this.database = database;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        this.language = language;

        if(user.getRank().equals("ADMIN")){
            ADMIN = true;
        }
    }

    /**
     * Méthode qui initialise l'interface du menu de l'historique des parties jouées
     **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        Scene scene = new Scene(root, 600, 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(titleLabel,language.getLine("historyGamePlayedBlackJackTitleLabel"),Pos.CENTER,0,20,20,600,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setTextArea(textHistory,200,150,530,380,false,true,anchorPane);

        setupScene.setTextField(textSearchUser,"",Pos.CENTER,20,500,20,150,new Font(15),false,anchorPane);

        setupScene.setButton(gameBlackJackButton,language.getLine("gameBlackJackButton"),Pos.CENTER,20,150,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(gameSlotMachineButton,language.getLine("gameSlotMachineButton"),Pos.CENTER,20,250,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(gameRouletteButton,language.getLine("gameRouletteButton"),Pos.CENTER,20,350,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(returnShopMenuButton,language.getLine("quitButton"), Pos.CENTER,25,720,60,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,300,720,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,450,720,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(searchUserButton,language.getLine("historySearchUserButton"),Pos.CENTER,20,550,20,150,new Font(15),false,anchorPane);

        setupScene.setCircle(circleSetting,18,570,30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        returnShopMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        leftInformationButton.setOnMouseClicked((event)-> leftInformation());
        rightInformationButton.setOnMouseClicked((event)-> rightInformation());
        gameRouletteButton.setOnMouseClicked((event)-> setGameRoulette());
        gameBlackJackButton.setOnMouseClicked((event)-> setGameBlackJack());
        gameSlotMachineButton.setOnMouseClicked((event)-> setGameSlotMachine());
        searchUserButton.setOnMouseClicked((event)-> searchUser());

        getAllInformation("");
        currentList = listOfGameBlackJack;
        currentGame = "blackJack";
        printInformation(currentList);

        if(ADMIN){
            textSearchUser.setVisible(true);
            searchUserButton.setVisible(true);
        }

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode pour rechercher des historiques avec un email précis
     * Utilisable seulement par un ADMIN
     * **/
    private void searchUser(){
        if(!textSearchUser.getText().isEmpty()) {
            listOfGameBlackJack = new ArrayList<>();
            listOfGameRoulette = new ArrayList<>();
            listOfGameSlotMachine = new ArrayList<>();
            indexList = 0;
            getAllInformation(databaseName.getTableHistoryPartyGamedColumnMailUser() + " like \"%" + textSearchUser.getText() + "%\"");
        }
        else {
            getAllInformation("");
        }
        switch(currentGame){
            case "blackJack" :
                currentList = listOfGameBlackJack;
                break;
            case "slotMachine" :
                currentList = listOfGameSlotMachine;
                break;
            case "roulette" :
                currentList = listOfGameRoulette;
        }
        printInformation(currentList);
    }

    /**
     * Méthode qui récupère dans la base de données les historiques des parties jouées
     **/
    private void getAllInformation(String condition){
        listOfGameBlackJack = new ArrayList<>();
        listOfGameSlotMachine = new ArrayList<>();
        listOfGameRoulette = new ArrayList<>();

        completeList(listOfGameBlackJack,databaseName.getGameBlackJack(),"Erreur black jack de getAllInformation dans HistoryGamePlayedMenuController",condition);
        completeList(listOfGameSlotMachine,databaseName.getGameSlotMachine(),"Erreur machine à sous de getAllInformation dans HistoryGamePlayedMenuController",condition);
        completeList(listOfGameRoulette,databaseName.getGameRoulette(),"\"Erreur roulette de getAllInformation dans HistoryGamePlayedMenuController\"",condition);
    }

    /**
     * Méthode qui prépare l'affichage des historiques des parties jouées
     **/
    private void completeList(List<String> list, String game, String errorMessage, String condition){
        try {
            ResultSet resultSet;

            if(ADMIN){
                if(condition.length() == 0) {
                    resultSet = database.select(databaseName.getTableHistoryPartyGamed(), databaseName.getTableHistoryPartyGamedColumnGameName() + " = \"" + game + "\"");
                }
                else {
                    resultSet = database.select(databaseName.getTableHistoryPartyGamed(), databaseName.getTableHistoryPartyGamedColumnGameName() + " = \"" + game + "\" && "+condition);
                }
            }
            else {
                resultSet = database.select(databaseName.getTableHistoryPartyGamed(), databaseName.getTableHistoryPartyGamedColumnGameName()+" = \"" + game + "\" && "+ databaseName.getTableHistoryPartyGamedColumnGameName() + " = \"" + user.getEmail() + "\"");
            }

            while (resultSet.next()){
                list.add(resultSet.getString(2) + " : ");
                list.add(resultSet.getString(5) + " : " + resultSet.getString(3) + " --> " + resultSet.getInt(4) + " " + language.getLine("tokenLabel"));
            }

            if(list.size() == 0){
                list.add(language.getLine("historyGamePlayedNoGameRegister"));
            }
        }
        catch (Exception e){ System.out.println(errorMessage); }
    }

    /**
     * Méthode qui affiche l'historique du jeu choisit
     **/
    private void printInformation(List<String> list){
        int indexMax = (indexList + 16);

        if(indexMax > list.size()){
            indexMax = list.size();
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }

        textHistory.setText("");
        for(int index = indexList; index < indexMax; index ++){
            textHistory.setText(textHistory.getText() + list.get(index) + "\n");
        }
    }

    /**
     * Méthode qui affiche les 15 informations suivantes
     **/
    private void leftInformation(){
        indexList -= 16;
        printInformation(currentList);

        if(indexList <= 16){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexList + 1) >= currentList.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    /**
     * Méthode qui affiche les 15 informations précédentes
     **/
    private void rightInformation(){
        indexList += 16;
        printInformation(currentList);

        if(indexList <= 16){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexList + 16) >= currentList.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    /**
     * Méthode qui affiche l'historique pour le black jack
     **/
    private void setGameBlackJack(){
        titleLabel.setText(language.getLine("historyGamePlayedBlackJackTitleLabel"));
        currentList = listOfGameBlackJack;
        indexList = 0;
        currentGame = "blackJack";
        printInformation(currentList);
    }

    /**
     * Méthode qui affiche l'historique pour la machine à sous
     **/
    private void setGameSlotMachine(){
        titleLabel.setText(language.getLine("historyGamePlayedSlotMachineTitleLabel"));
        currentList = listOfGameSlotMachine;
        indexList = 0;
        currentGame = "slotMachine";
        printInformation(currentList);
    }

    /**
     * Méthode qui affiche l'historique pour la roulette
     **/
    private void setGameRoulette(){
        titleLabel.setText(language.getLine("historyGamePlayedRouletteTitleLabel"));
        currentList = listOfGameRoulette;
        indexList = 0;
        currentGame = "roulette";
        printInformation(currentList);
    }

    /**
     * Méthode pour redirigé vers le menu principale
     **/
    private void goToMainMenu(){
        settingMenuController.exitSettingMenu();
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,language,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    /**
     * Méthode qui modifier le volume sonore
     **/
    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
        }
    }

    /**
     * Méthode qui affiche ou non les animations en arrière plan
     **/
    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    /**
     * Méthode qui affiche le menu des paramètres
     **/
    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    /** Méthode qui modifie la langue **/
    public void setLanguage(Language language){ this.language = language; }

    /** Méthode qui rafraichit ce menu **/
    public void refresh(){
        setting();
        settingMenuController.exitSettingMenu();
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        settingMenuController.setting();
    }
}
