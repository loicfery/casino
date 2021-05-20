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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class HistoryShoppingMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();

    private final Label labelTitle = new Label();

    private final TextArea textInformation = new TextArea();

    private final TextField textSearchUser = new TextField();

    private final Button returnShopMenuButton = new Button();
    private final Button leftInformationButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button exchangeTokenButton = new Button();
    private final Button exchangeMoneyButton = new Button();
    private final Button searchUserButton = new Button();

    private double soundVolume;
    private boolean backgroundAnimation;
    private List<String> listOfInformationToken = new ArrayList<>();
    private List<String> listOfInformationMoney = new ArrayList<>();
    private int indexInformation = 0;
    private boolean switchExchange = false;
    private boolean ADMIN = false;

    public HistoryShoppingMenuController(User user, Stage stage, Database database, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        this.database = database;
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);

        if(user.getRank().equals("ADMIN")){
            ADMIN = true;
        }
    }

    /**
     * Méthode qui initialise l'interface
     **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 400, 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(labelTitle,"Historique des échanges : Jeton", Pos.CENTER,0,20,20,400,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setTextArea(textInformation,0,100,700,400,false,true,anchorPane);

        setupScene.setTextField(textSearchUser,"",Pos.CENTER,175,650,20,200,new Font(15),false,anchorPane);

        setupScene.setButton(returnShopMenuButton,"Quitter",Pos.CENTER,25,720,60,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,100,580,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,250,580,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(exchangeTokenButton,"Echange de jeton",Pos.CENTER,175,720,60,200,new Font(20),false,anchorPane);
        setupScene.setButton(exchangeMoneyButton,"Echange d'argent",Pos.CENTER,175,720,60,200,new Font(20),true,anchorPane);
        setupScene.setButton(searchUserButton,"Rechercher",Pos.CENTER,25,650,20,123,new Font(15),false,anchorPane);

        leftInformationButton.setOnMouseClicked((event) -> leftInformation());
        rightInformationButton.setOnMouseClicked((event) -> rightInformation());
        returnShopMenuButton.setOnMouseClicked((event)-> goToShopMenu());
        exchangeTokenButton.setOnMouseClicked((event) -> switchExchange());
        exchangeMoneyButton.setOnMouseClicked((event) -> switchExchange());
        searchUserButton.setOnMouseClicked((event)-> searchUser());

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
        printInformation();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void searchUser(){
        textInformation.setText("");
        listOfInformationToken = new ArrayList<>();
        listOfInformationMoney = new ArrayList<>();

        if(!textSearchUser.getText().isEmpty()) {
            String conditionToken = databaseName.getTableHistoryExchangeTokenColumnMailUser() + " = \"%" + textSearchUser.getText() + "%\"";
            String conditionMoney = databaseName.getTableHistoryExchangeMoneyColumnMailUser() + " = \"%" + textSearchUser.getText() + "%\"";
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
        try {
            ResultSet resultSetToken = database.select(databaseName.getTableHistoryExchangeToken(), conditionToken);
            ResultSet resultSetMoney = database.select(databaseName.getTableHistoryExchangeMoney(), conditionMoney);

            while (resultSetToken.next()){
                if(ADMIN){
                    listOfInformationToken.add(resultSetToken.getString(2)+" : ");
                }
                listOfInformationToken.add("Data : "+resultSetToken.getInt(3)+" jetons ---> "+resultSetToken.getInt(4)+" $");
            }
            while(resultSetMoney.next()){
                if(ADMIN){
                    listOfInformationMoney.add(resultSetMoney.getString(2)+" : ");
                }
                listOfInformationMoney.add("Date : "+resultSetMoney.getInt(3)+" $ ---> "+resultSetMoney.getInt(4)+" jetons");
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
            labelTitle.setText("Historique des échanges : Jeton");
        }
        else {
            exchangeMoneyButton.setVisible(false);
            exchangeTokenButton.setVisible(true);
            switchExchange = true;
            labelTitle.setText("Historique des échanges : Argent");

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
        ShopMenuController shopMenuController = new ShopMenuController(stage,user,database,soundVolume,backgroundAnimation);
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
}
