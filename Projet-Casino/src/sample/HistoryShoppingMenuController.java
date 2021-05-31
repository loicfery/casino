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


public class HistoryShoppingMenuController implements InterfaceMenu{

    private BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private SettingMenuController settingMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private Language language;

    private final Label labelTitle = new Label();

    private final TextArea textInformation = new TextArea();

    private final TextField textSearchUser = new TextField();

    private final Button returnShopMenuButton = new Button();
    private final Button leftInformationButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button exchangeTokenButton = new Button();
    private final Button exchangeMoneyButton = new Button();
    private final Button searchUserButton = new Button();

    private final Circle circleSetting = new Circle();

    private double soundVolume;
    private boolean backgroundAnimation;
    private List<String> listOfInformationToken;
    private List<String> listOfInformationMoney;
    private int indexInformation = 0;
    private boolean switchExchange;
    private boolean ADMIN = false;

    public HistoryShoppingMenuController(User user, Stage stage, Database database,Language language, double soundVolume, boolean backgroundAnimation){
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
     * Méthode qui initialise l'interface du menu de l'historique de la boutique
     **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        Scene scene = new Scene(root, 400, 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(labelTitle,language.getLine("historyShoppingTitleLabel1"), Pos.CENTER,0,40,20,360,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setTextArea(textInformation,0,100,700,400,false,true,anchorPane);

        setupScene.setTextField(textSearchUser,"",Pos.CENTER,175,650,20,200,new Font(15),false,anchorPane);

        setupScene.setButton(returnShopMenuButton,language.getLine("quitButton"),Pos.CENTER,25,720,60,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,100,580,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,250,580,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(exchangeTokenButton,language.getLine("shopTokenTitleLabel"),Pos.CENTER,175,720,60,200,new Font(20),false,anchorPane);
        setupScene.setButton(exchangeMoneyButton,language.getLine("shopMoneyTitleLabel"),Pos.CENTER,175,720,60,200,new Font(20),true,anchorPane);
        setupScene.setButton(searchUserButton,language.getLine("historySearchUserButton"),Pos.CENTER,25,650,20,123,new Font(15),false,anchorPane);

        setupScene.setCircle(circleSetting,18,372,28,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        leftInformationButton.setOnMouseClicked((event) -> leftInformation());
        rightInformationButton.setOnMouseClicked((event) -> rightInformation());
        returnShopMenuButton.setOnMouseClicked((event)-> goToShopMenu());
        exchangeTokenButton.setOnMouseClicked((event) -> switchExchange());
        exchangeMoneyButton.setOnMouseClicked((event) -> switchExchange());
        searchUserButton.setOnMouseClicked((event)-> searchUser());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());

        if(ADMIN){
            textSearchUser.setVisible(true);
            searchUserButton.setVisible(true);
            getAllInformation("","");
        }
        else {
            String conditionToken = databaseName.getTableHistoryExchangeTokenColumnMailUser() + " = \"" + user.getEmail() + "\"";
            String conditionMoney = databaseName.getTableHistoryExchangeMoneyColumnMailUser() + " = \"" + user.getEmail() + "\"";
            getAllInformation(conditionToken,conditionMoney);
        }

        textInformation.setText("");
        switchExchange = false;
        indexInformation = 0;
        printInformation();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode pour rechercher des historiques avec un email précis
     * Utilisable seulement par un ADMIN
     * **/
    private void searchUser(){
        indexInformation = 0;
        textInformation.setText("");
        listOfInformationToken = new ArrayList<>();
        listOfInformationMoney = new ArrayList<>();

        if(!textSearchUser.getText().isEmpty()) {
            String conditionToken = databaseName.getTableHistoryExchangeTokenColumnMailUser() + " like \"%" + textSearchUser.getText() + "%\"";
            String conditionMoney = databaseName.getTableHistoryExchangeMoneyColumnMailUser() + " like \"%" + textSearchUser.getText() + "%\"";
            getAllInformation(conditionToken, conditionMoney);
        }
        else {
            getAllInformation("","");
        }
        printInformation();

        if(indexInformation < 14){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexInformation + 14) >= listOfInformationToken.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    /**
     * Méthode qui récupère de la base de données tous les historiques d'échanges
     **/
    private void getAllInformation(String conditionToken, String conditionMoney){
        listOfInformationToken = new ArrayList<>();
        listOfInformationMoney = new ArrayList<>();
        textInformation.setText("");

        try {
            ResultSet resultSetToken = database.select(databaseName.getTableHistoryExchangeToken(), conditionToken);
            ResultSet resultSetMoney = database.select(databaseName.getTableHistoryExchangeMoney(), conditionMoney);

            while (resultSetToken.next()){
                if(ADMIN){
                    listOfInformationToken.add(resultSetToken.getString(2)+" : ");
                }
                listOfInformationToken.add(resultSetToken.getString(5) + " : "+ resultSetToken.getInt(3) + " " + language.getLine("tokenLabel") +" ---> " + resultSetToken.getInt(4)+" $");
            }
            while(resultSetMoney.next()){
                if(ADMIN){
                    listOfInformationMoney.add(resultSetMoney.getString(2) + " : ");
                }
                listOfInformationMoney.add(resultSetMoney.getString(5) + " : " + resultSetMoney.getInt(3) + " $ ---> " + resultSetMoney.getInt(4) + " " + language.getLine("tokenLabel"));
            }
        }
        catch (Exception e){ System.out.println("Erreur : getAllInformation dans HistoryShoppingMenuController"); }
    }

    /**
     * Méthode qui affiche les historiques d'échanges
     **/
    private void printInformation(){
        List<String> listOfHistory;
        int indexPrint = indexInformation + 14;

        if(switchExchange){
            listOfHistory = listOfInformationMoney;
        }
        else {
            listOfHistory = listOfInformationToken;
        }

        if(indexPrint > listOfHistory.size()){
            indexPrint = listOfHistory.size();
            rightInformationButton.setVisible(false);
        }

        for(int index = indexInformation; index < indexPrint; index ++){
            setTextInformation(listOfHistory.get(index));
        }
    }

    /**
     * Méthode qui affiche les 15 historiques suivantes
     **/
    private void leftInformation(){
        textInformation.setText("");
        indexInformation -= 14;
        printInformation();

        if(indexInformation < 14){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if(indexInformation >= listOfInformationToken.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    /**
     * Méthode qui affiche les 15 historiques précédentes
     **/
    private void rightInformation(){
        textInformation.setText("");
        indexInformation += 14;
        printInformation();

        if(indexInformation < 14){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexInformation + 14) >= listOfInformationToken.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    /**
     * Méthode qui cache des historiques pour afficher les suivant
     **/
    private void switchExchange(){
        if(switchExchange){
            exchangeMoneyButton.setVisible(true);
            exchangeTokenButton.setVisible(false);
            switchExchange = false;
            labelTitle.setText(language.getLine("historyShoppingTitleLabel1"));
        }
        else {
            exchangeMoneyButton.setVisible(false);
            exchangeTokenButton.setVisible(true);
            switchExchange = true;
            labelTitle.setText(language.getLine("historyShoppingTitleLabel2"));

        }
        indexInformation = 0;
        textInformation.setText("");
        printInformation();

        if(indexInformation < 14){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexInformation + 14) >= listOfInformationToken.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    /**
     * Méthode qui redirige dans le menu d'échange
     **/
    private void goToShopMenu(){
        settingMenuController.exitSettingMenu();
        ShopMenuController shopMenuController = new ShopMenuController(stage,user,database,language,soundVolume,backgroundAnimation);
        shopMenuController.setting();
    }

    /**
     * Méthode qui modifie le contenue du texte
     **/
    private void setTextInformation(String information){
        textInformation.setText(textInformation.getText() + information + "\n");
    }

    /**
     * Méthode qui modifie le volume sonore
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

    /** Méthode qui modifie la langue **/
    public void setLanguage(Language language){ this.language = language; }

    /** Méthode qui rafraichit ce menu **/
    public void refresh(){
        labelTitle.setText(language.getLine("historyShoppingTitleLabel1"));
        returnShopMenuButton.setText(language.getLine("quitButton"));
        exchangeTokenButton.setText(language.getLine("shopTokenTitleLabel"));
        exchangeMoneyButton.setText(language.getLine("shopMoneyTitleLabel"));
        searchUserButton.setText(language.getLine("historySearchUserButton"));

        if(ADMIN){

            getAllInformation("","");
        }
        else {
            String conditionToken = databaseName.getTableHistoryExchangeTokenColumnMailUser() + " = \"" + user.getEmail() + "\"";
            String conditionMoney = databaseName.getTableHistoryExchangeMoneyColumnMailUser() + " = \"" + user.getEmail() + "\"";
            getAllInformation(conditionToken,conditionMoney);
        }

        printInformation();
    }


    /**
     * Méthode qui affiche le menu des paramètres
     */
    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }
}
