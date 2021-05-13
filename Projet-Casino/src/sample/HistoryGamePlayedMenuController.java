package sample;

import games.Database;
import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryGamePlayedMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;
    private final Database database;

    private final Button returnShopMenuButton = new Button();
    private final Button gameBlackJackButton = new Button();
    private final Button gameSlotMachineButton = new Button();
    private final Button gameRouletteButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button leftInformationButton = new Button();

    private final Circle circleSetting = new Circle();

    private final Label titleLabel = new Label();

    private final TextArea textHistory = new TextArea();

    private double soundVolume;
    private boolean backgroundAnimation;

    private final List<String> listOfGameBlackJack = new ArrayList<>();
    private final List<String> listOfGameSlotMachine = new ArrayList<>();
    private final List<String> listOfGameRoulette = new ArrayList<>();
    private List<String> currentList = new ArrayList<>();
    private int indexList = 0;

    /** base de données **/
    private final String tableHistoryGamePlayed = "historiquepartiesjouees";
    private final String columnGameBlackJack = "Black Jack";
    private final String columnGameSlotMachine = "slot machine";
    private final String columnGameRoulette = "Roulette";

    public HistoryGamePlayedMenuController(User user, Stage stage, Database database, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        this.database = database;
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);
    }

    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 600, 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(titleLabel,"Historique de jeux : Black Jack",Pos.CENTER,0,20,20,600,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setTextArea(textHistory,200,150,530,380,false,true,anchorPane);

        setupScene.setButton(gameBlackJackButton,"Black Jack",Pos.CENTER,20,150,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(gameSlotMachineButton,"Machine à sous",Pos.CENTER,20,250,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(gameRouletteButton,"Roulette",Pos.CENTER,20,350,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(returnShopMenuButton,"Quitter", Pos.CENTER,25,720,60,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,300,720,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,450,720,20,50,new Font(15),false,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        returnShopMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        leftInformationButton.setOnMouseClicked((event)-> leftInformation());
        rightInformationButton.setOnMouseClicked((event)-> rightInformation());
        gameRouletteButton.setOnMouseClicked((event)-> setGameRoulette());
        gameBlackJackButton.setOnMouseClicked((event)-> setGameBlackJack());
        gameSlotMachineButton.setOnMouseClicked((event)-> setGameSlotMachine());

        getAllInformation();
        currentList = listOfGameBlackJack;
        printInformation(currentList);

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void getAllInformation(){
        completeList(listOfGameBlackJack,columnGameBlackJack,"Erreur black jack de getAllInformation dans HistoryGamePlayedMenuController");
        completeList(listOfGameSlotMachine,columnGameSlotMachine,"Erreur machine à sous de getAllInformation dans HistoryGamePlayedMenuController");
        completeList(listOfGameRoulette,columnGameRoulette,"\"Erreur roulette de getAllInformation dans HistoryGamePlayedMenuController\"");
    }

    private void completeList(List<String> list, String game, String errorMessage){
        try {
            ResultSet resultSet = database.select(tableHistoryGamePlayed, "NomJeux = \"" + game + "\" && MailUser = \""+user.getEmail()+"\"");
            while (resultSet.next()){
                list.add("Date : "+resultSet.getString(3)+" --> "+resultSet.getInt(2)+" jetons");
            }

            if(list.size() == 0){
                list.add("Il n'y a aucune partie enregistrée");
            }
        }
        catch (Exception e){ System.out.println(errorMessage); }
    }

    private void printInformation(List<String> list){
        int indexMax = (indexList + 15);

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

    private void leftInformation(){
        indexList -= 15;
        printInformation(currentList);

        if(indexList <= 15){
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

    private void rightInformation(){
        indexList += 15;
        printInformation(currentList);

        if(indexList <= 15){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexList + 15) >= currentList.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    private void setGameBlackJack(){
        titleLabel.setText("Historique de jeux : Black Jack");
        currentList = listOfGameBlackJack;
        indexList = 0;
        printInformation(currentList);
    }

    private void setGameSlotMachine(){
        titleLabel.setText("Historique de jeux : Machine à sous");
        currentList = listOfGameSlotMachine;
        indexList = 0;
        printInformation(currentList);
    }

    private void setGameRoulette(){
        titleLabel.setText("Historique de jeux : Roulette");
        currentList = listOfGameRoulette;
        indexList = 0;
        printInformation(currentList);
    }

    /**
     * Méthode pour quitter le jeu et retourner dans le menu principale
     **/
    private void goToMainMenu(){
        settingMenuController.exitSettingMenu();
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
        }
    }

    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }
}
