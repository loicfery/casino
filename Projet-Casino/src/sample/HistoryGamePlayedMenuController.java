package sample;

import games.Database;
import games.DatabaseName;
import games.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Scene scene;

    private final Button returnShopMenuButton = new Button();
    private final Button gameBlackJackButton = new Button();
    private final Button gameSlotMachineButton = new Button();
    private final Button gameRouletteButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button leftInformationButton = new Button();
    private final Button searchUserButton = new Button();
    private final Button searchByDateButton = new Button();

    private final Circle circleSetting = new Circle();

    private final Label blackJackTitleLabel = new Label();
    private final Label slotMachineTitleLabel = new Label();
    private final Label rouletteTitleLabel = new Label();

    private final TextArea textHistory = new TextArea();

    private final TextField textSearchUser = new TextField();
    private final TextField textSearchDateYear = new TextField();
    private final TextField textSearchDateMonth = new TextField();
    private final TextField textSearchDateDay = new TextField();

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

    private List<String> listOfGameBlackJack;
    private List<String> listOfGameSlotMachine;
    private List<String> listOfGameRoulette;
    private List<String> currentList = new ArrayList<>();
    private int indexList = 0;
    private boolean ADMIN = false;
    private String currentGame;
    private boolean inputControlNumber = true;

    public HistoryGamePlayedMenuController(User user, Stage stage, Database database, Language language, double soundVolume, boolean backgroundAnimation, double sizeX, double sizeY){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        this.database = database;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation,sizeX,sizeY);
        this.language = language;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

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
        scene = new Scene(root, sizeX * 600, sizeY * 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(blackJackTitleLabel,language.getLine("historyGamePlayedBlackJackTitleLabel"),Pos.CENTER,0,sizeY * 20,sizeY * 20,sizeX * 600,new Font(sizeX * 30),Paint.valueOf("BLACK"),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setLabel(slotMachineTitleLabel,language.getLine("historyGamePlayedSlotMachineTitleLabel"),Pos.CENTER,0,sizeY * 20,sizeY * 20,sizeX * 600,new Font(sizeX * 30),Paint.valueOf("BLACK"),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,false,anchorPane);
        setupScene.setLabel(rouletteTitleLabel,language.getLine("historyGamePlayedRouletteTitleLabel"),Pos.CENTER,0,sizeY * 20,sizeY * 20,sizeX * 600,new Font(sizeX * 30),Paint.valueOf("BLACK"),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,false,anchorPane);

        setupScene.setTextArea(textHistory,sizeX * 200,sizeY * 150,sizeY * 530,sizeX * 380,false,sizeX * 20,listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);

        setupScene.setTextField(textSearchUser,"",Pos.CENTER,sizeX * 20,sizeY * 600,sizeY * 20,sizeX * 150,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,false,anchorPane);
        setupScene.setTextField(textSearchDateYear,"",Pos.CENTER,sizeX * 20,sizeY * 500,sizeY * 20,sizeX * 60,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setTextField(textSearchDateMonth,"",Pos.CENTER,sizeX * 90,sizeY * 500,sizeY * 20,sizeX * 35,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setTextField(textSearchDateDay,"",Pos.CENTER,sizeX * 135,sizeY * 500,sizeY * 20,sizeX * 35,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);

        setupScene.setButton(gameBlackJackButton,language.getLine("gameBlackJackButton"),Pos.CENTER,sizeX * 20,sizeY * 150,sizeY * 20,sizeX * 150,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setButton(gameSlotMachineButton,language.getLine("gameSlotMachineButton"),Pos.CENTER,sizeX * 20,sizeY * 250,sizeY * 20,sizeX * 150,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setButton(gameRouletteButton,language.getLine("gameRouletteButton"),Pos.CENTER,sizeX *  20,sizeY *  350,sizeY * 20,sizeX * 150,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setButton(returnShopMenuButton,language.getLine("quitButton"), Pos.CENTER,sizeX * 25,sizeY * 720,sizeY * 60,sizeX * 123.0,new Font(sizeX * 20.0),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,sizeX * 300,sizeY * 720,sizeY * 20,sizeX * 50,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,sizeX * 450,sizeY * 720,sizeY * 20,sizeX * 50,new Font(sizeX * 15),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,false,anchorPane);
        setupScene.setButton(searchUserButton,language.getLine("historySearchUserByEmailButton"),Pos.CENTER,sizeX * 20,sizeY * 650,sizeY * 20,sizeX * 150,new Font( sizeX * 10),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,false,anchorPane);
        setupScene.setButton(searchByDateButton,language.getLine("historySearchByDateButton"),Pos.CENTER,sizeX * 20,sizeY * 550,sizeY * 20,sizeX * 150,new Font(sizeX * 10),listOfLayoutX,listOfLayoutY,listOfPrefHeight,listOfPrefWidth,listOfFontSize,true,anchorPane);

        setupScene.setCircle(circleSetting,Math.max(sizeX,sizeY) * 18,sizeX * 570,sizeY * 30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,listOfLayoutX,listOfLayoutY,listOfRadius,true,anchorPane);

        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        returnShopMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        leftInformationButton.setOnMouseClicked((event)-> leftInformation());
        rightInformationButton.setOnMouseClicked((event)-> rightInformation());
        gameRouletteButton.setOnMouseClicked((event)-> setGameRoulette());
        gameBlackJackButton.setOnMouseClicked((event)-> setGameBlackJack());
        gameSlotMachineButton.setOnMouseClicked((event)-> setGameSlotMachine());
        searchUserButton.setOnMouseClicked((event)-> searchUser());
        searchByDateButton.setOnMouseClicked((event)-> searchByDate());

        textSearchDateYear.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                InputControl.inputControlNumber(oldValue,newValue,textSearchDateYear);
            }
        });

        textSearchDateMonth.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                InputControl.inputControlNumber(oldValue,newValue,textSearchDateMonth);
            }
        });

        textSearchDateDay.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                InputControl.inputControlNumber(oldValue,newValue,textSearchDateDay);
            }
        });

        if(ADMIN){
            textSearchUser.setVisible(true);
            searchUserButton.setVisible(true);
            getAllInformation("");
        }
        else {
            getAllInformation(databaseName.getTableUserColumnMailUser() + " = '" + user.getEmail() + "'");
        }

        currentList = listOfGameBlackJack;
        currentGame = "blackJack";
        printInformation(currentList);

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode pour rechercher des historiques avec un email précis
     * Utilisable seulement par un ADMIN
     * **/
    private void searchUser(){
        if(!textSearchUser.getText().isEmpty()) {
            String conditionDate = getConditionDate();
            String conditionEmail = databaseName.getTableHistoryPartyGamedColumnMailUser() + " like '%" + textSearchUser.getText() + "%'";
            getAllInformation(conditionEmail + " && " + conditionDate);
        }
        else {
            getAllInformation("");
        }

        setCurrentList();
        printInformation(currentList);
    }

    private void setCurrentList(){
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
    }

    private void searchByDate(){
        String conditionDate = getConditionDate();

        if(conditionDate.equals("")){
            return;
        }

        if(ADMIN) {
            String conditionEmail = databaseName.getTableHistoryPartyGamedColumnMailUser() + " like '%" + textSearchUser.getText() + "%'";
            getAllInformation(conditionEmail + " && " + conditionDate);
        }
        else {
            getAllInformation(conditionDate);
        }
        setCurrentList();
        printInformation(currentList);
    }

    private String getConditionDate(){
        int year = 0,month = 0,day = 0;
        String condition = "";

        if(!textSearchDateYear.getText().isEmpty()){
            try{
                year = Integer.parseInt(textSearchDateYear.getText());
                if(year < 2000 || year > Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()))){
                    return "";
                }
            }
            catch (Exception exception){
                return "";
            }
        }

        if(!textSearchDateMonth.getText().isEmpty()){
            try{
                month = Integer.parseInt(textSearchDateMonth.getText());
                if(month < 1 || month > 12){
                    return "";
                }
            }
            catch (Exception exception){
                return "";
            }
        }

        if(!textSearchDateDay.getText().isEmpty()){
            try{
                day = Integer.parseInt(textSearchDateDay.getText());
                if(day < 1){
                    return "";
                }
                else{
                    switch(month){
                        case 1 :
                        case 3 :
                        case 5 :
                        case 7 :
                        case 8 :
                        case 10 :
                        case 12 :
                            if(day > 31){
                                return "";
                            }
                            break;
                        default:
                            if(day > 30){
                                return "";
                            }
                    }
                }
            }
            catch (Exception exception){
                return "";
            }
        }

        if(year != 0){
            condition = condition + textSearchDateYear.getText() + "-";
        }
        else{
            condition = condition + "%-";
        }

        if(month != 0){
            condition = condition + textSearchDateMonth.getText() + "-";
        }
        else{
            condition = condition + "%-";
        }

        if(day != 0){
            condition = condition + textSearchDateDay.getText();
        }
        else{
            condition = condition + "%";
        }

        return databaseName.getTableHistoryPartyGamedColumnDate() + " like '" + condition + "'";
    }

    /**
     * Méthode qui récupère dans la base de données les historiques des parties jouées
     **/
    private void getAllInformation(String condition){
        listOfGameBlackJack = new ArrayList<>();
        listOfGameSlotMachine = new ArrayList<>();
        listOfGameRoulette = new ArrayList<>();
        indexList = 0;

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

        if(indexList < 16){
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

        if(indexList < 16){
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
        slotMachineTitleLabel.setVisible(false);
        rouletteTitleLabel.setVisible(false);
        blackJackTitleLabel.setVisible(true);
        currentList = listOfGameBlackJack;
        indexList = 0;
        currentGame = "blackJack";
        printInformation(currentList);
    }

    /**
     * Méthode qui affiche l'historique pour la machine à sous
     **/
    private void setGameSlotMachine(){
        blackJackTitleLabel.setVisible(false);
        rouletteTitleLabel.setVisible(false);
        slotMachineTitleLabel.setVisible(true);
        currentList = listOfGameSlotMachine;
        indexList = 0;
        currentGame = "slotMachine";
        printInformation(currentList);
    }

    /**
     * Méthode qui affiche l'historique pour la roulette
     **/
    private void setGameRoulette(){
        slotMachineTitleLabel.setVisible(false);
        blackJackTitleLabel.setVisible(false);
        rouletteTitleLabel.setVisible(true);
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
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
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

    public void setSizeX(double sizeX){ this.sizeX = sizeX; }

    public void setSizeY(double sizeY){ this.sizeY = sizeY; }

    /** Méthode qui rafraichit ce menu **/
    public void refresh(){
        blackJackTitleLabel.setText(language.getLine("historyGamePlayedBlackJackTitleLabel"));
        slotMachineTitleLabel.setText(language.getLine("historyGamePlayedSlotMachineTitleLabel"));
        rouletteTitleLabel.setText(language.getLine("historyGamePlayedRouletteTitleLabel"));
        gameBlackJackButton.setText(language.getLine("gameBlackJackButton"));
        gameSlotMachineButton.setText(language.getLine("gameSlotMachineButton"));
        gameRouletteButton.setText(language.getLine("gameRouletteButton"));
        returnShopMenuButton.setText(language.getLine("quitButton"));
        searchUserButton.setText(language.getLine("historySearchUserButton"));

        if(ADMIN){
            getAllInformation("");
        }
        else {
            getAllInformation(databaseName.getTableUserColumnMailUser() + " = '" + user.getEmail() + "'");
        }

        switch(currentGame){
            case "blackJack" :
                currentList = listOfGameBlackJack;
                break;
            case "roulette":
                currentList = listOfGameRoulette;
                break;
            case "slotMachine":
                currentList = listOfGameSlotMachine;
        }

        printInformation(currentList);
        refreshPosition();
    }

    private void refreshPosition(){
        scene.setRoot(new BorderPane());
        scene = new Scene(root, sizeX * 600, sizeY * 800);
        stage.setScene(scene);

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
            if(node instanceof TextArea){
                SetupScene.refreshPositionTextArea(node,listOfLayoutX.get(indexLayoutX),listOfLayoutY.get(indexLayoutY),listOfPrefHeight.get(indexPrefHeight),listOfPrefWidth.get(indexPrefWidth),listOfFontSize.get(indexFontSize),sizeX,sizeY);
                indexLayoutX ++;
                indexLayoutY ++;
                indexPrefHeight ++;
                indexPrefWidth ++;
                indexFontSize ++;
            }
        }
    }
}
