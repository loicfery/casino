package sample;

import games.Database;
import games.DatabaseName;
import games.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


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
    private final TextField textSearchDateYear = new TextField();
    private final TextField textSearchDateMonth = new TextField();
    private final TextField textSearchDateDay = new TextField();

    private final Button returnShopMenuButton = new Button();
    private final Button leftInformationButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button exchangeTokenButton = new Button();
    private final Button exchangeMoneyButton = new Button();
    private final Button searchUserButton = new Button();
    private final Button searchByDateButton = new Button();

    private final Circle circleSetting = new Circle();

    private final Pattern wholeNumberPattern = Pattern.compile("\\d*");

    private double soundVolume;
    private boolean backgroundAnimation;
    private List<String> listOfInformationToken;
    private List<String> listOfInformationMoney;
    private int indexInformation;
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
        Scene scene = new Scene(root, 400, 900);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(labelTitle,language.getLine("historyShoppingTitleLabel1"), Pos.CENTER,0,40,20,360,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setTextArea(textInformation,0,100,450,400,false,true,anchorPane);

        setupScene.setTextField(textSearchUser,"",Pos.CENTER,175,650,20,200,new Font(15),false,anchorPane);
        setupScene.setTextField(textSearchDateYear,"",Pos.CENTER,175,750,20,60,new Font(15),true,anchorPane);
        setupScene.setTextField(textSearchDateMonth,"",Pos.CENTER,245,750,20,35,new Font(15),true,anchorPane);
        setupScene.setTextField(textSearchDateDay,"",Pos.CENTER,290,750,20,35,new Font(15),true,anchorPane);

        setupScene.setButton(returnShopMenuButton,language.getLine("quitButton"),Pos.CENTER,25,820,60,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,100,580,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,250,580,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(exchangeTokenButton,language.getLine("shopTokenTitleLabel"),Pos.CENTER,175,820,60,200,new Font(20),false,anchorPane);
        setupScene.setButton(exchangeMoneyButton,language.getLine("shopMoneyTitleLabel"),Pos.CENTER,175,820,60,200,new Font(20),true,anchorPane);
        setupScene.setButton(searchUserButton,language.getLine("historySearchUserByEmailButton"),Pos.CENTER,25,650,20,123,new Font(10),false,anchorPane);
        setupScene.setButton(searchByDateButton,language.getLine("historySearchByDateButton"),Pos.CENTER,25,750,20,123,new Font(10),true,anchorPane);

        setupScene.setCircle(circleSetting,18,372,28,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        leftInformationButton.setOnMouseClicked((event) -> leftInformation());
        rightInformationButton.setOnMouseClicked((event) -> rightInformation());
        returnShopMenuButton.setOnMouseClicked((event)-> goToShopMenu());
        exchangeTokenButton.setOnMouseClicked((event) -> switchExchange());
        exchangeMoneyButton.setOnMouseClicked((event) -> switchExchange());
        searchUserButton.setOnMouseClicked((event)-> searchUser());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        searchByDateButton.setOnMouseClicked((event) ->searchByDate());

        textSearchDateYear.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                                final String newValue) {
                if (!wholeNumberPattern.matcher(newValue).matches())
                    textSearchDateYear.setText(oldValue);
            }
        });

        textSearchDateMonth.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                                final String newValue) {
                if (!wholeNumberPattern.matcher(newValue).matches())
                    textSearchDateMonth.setText(oldValue);
            }
        });

        textSearchDateDay.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                                final String newValue) {
                if (!wholeNumberPattern.matcher(newValue).matches())
                    textSearchDateDay.setText(oldValue);
            }
        });

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
            String conditionDateExchangeToken = getConditionDate(databaseName.getTableHistoryExchangeTokenColumnDate());
            String conditionDateExchangeMoney = getConditionDate(databaseName.getTableHistoryExchangeMoneyColumnDate());

            if(conditionDateExchangeToken.equals("") || conditionDateExchangeMoney.equals("")){
                return;
            }

            String conditionExchangeToken = databaseName.getTableHistoryExchangeTokenColumnMailUser() + " like \"%" + textSearchUser.getText() + "%\" && " + conditionDateExchangeToken;
            String conditionExchangeMoney = databaseName.getTableHistoryExchangeMoneyColumnMailUser() + " like \"%" + textSearchUser.getText() + "%\" && " + conditionDateExchangeMoney;
            getAllInformation(conditionExchangeToken, conditionExchangeMoney);
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

    private String getConditionDate(String columnName){
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
        System.out.println(columnName + " like '" + condition + "'");
        return columnName + " like '" + condition + "'";
    }

    private void searchByDate(){
        String conditionDateExchangeToken = getConditionDate(databaseName.getTableHistoryExchangeTokenColumnDate());
        String conditionDateExchangeMoney = getConditionDate(databaseName.getTableHistoryExchangeMoneyColumnDate());

        if(conditionDateExchangeToken.equals("") || conditionDateExchangeMoney.equals("")){
            return;
        }

        if(ADMIN) {
            String conditionEmailExchangeToken = databaseName.getTableHistoryExchangeTokenColumnMailUser() + " like '%" + textSearchUser.getText() + "%'";
            String conditionEmailExchangeMoney = databaseName.getTableHistoryExchangeMoneyColumnMailUser() + " like '%" + textSearchUser.getText() + "%'";
            getAllInformation(conditionEmailExchangeToken + " && " + conditionDateExchangeToken,conditionEmailExchangeMoney + " && " + conditionDateExchangeMoney);
        }
        else {
            getAllInformation(conditionDateExchangeToken,conditionDateExchangeMoney);
        }

        printInformation();
    }

    /**
     * Méthode qui récupère de la base de données tous les historiques d'échanges
     **/
    private void getAllInformation(String conditionToken, String conditionMoney){
        listOfInformationToken = new ArrayList<>();
        listOfInformationMoney = new ArrayList<>();
        textInformation.setText("");
        indexInformation = 0;

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

            leftInformationButton.setVisible(false);
            if(indexInformation >= listOfInformationToken.size()){
                rightInformationButton.setVisible(false);
            }
            else {
                rightInformationButton.setVisible(true);
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
