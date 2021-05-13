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


public class HistoryShoppingMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;
    private final Database database;

    private final Circle circleSetting = new Circle();

    private final Label labelTitle = new Label();

    private final TextArea textInformation = new TextArea();

    private final Button returnShopMenuButton = new Button();
    private final Button leftInformationButton = new Button();
    private final Button rightInformationButton = new Button();
    private final Button exchangeTokenButton = new Button();
    private final Button exchangeMoneyButton = new Button();

    private double soundVolume;
    private boolean backgroundAnimation;
    private List<String> listOfInformationToken = new ArrayList<>();
    private List<String> listOfInformationMoney = new ArrayList<>();
    private int indexInformation = 0;
    private boolean switchExchange = false;

    /** Base de données **/
    private final String columnUserMail = "MailUser";
    private final String tableUser = "utilisateur";
    private final String tableHistoryExchangeMoney = "historiqueachat";
    private final String tableHistoryExchangeToken = "historiqueechange";

    public HistoryShoppingMenuController(User user, Stage stage, Database database, double soundVolume, boolean backgroundAnimation){
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
        Scene scene = new Scene(root, 400, 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(labelTitle,"Historique des échanges : Jeton", Pos.CENTER,0,20,20,400,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextArea(textInformation,0,100,700,400,false,true,anchorPane);

        setupScene.setButton(returnShopMenuButton,"Quitter",Pos.CENTER,25,700,60,123.0,new Font(20.0),true,anchorPane);
        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,100,600,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,250,600,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(exchangeTokenButton,"Echange de jeton",Pos.CENTER,175,700,60,200,new Font(20),false,anchorPane);
        setupScene.setButton(exchangeMoneyButton,"Echange d'argent",Pos.CENTER,175,700,60,200,new Font(20),true,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("BLACK"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event) -> goToMenuSetting());
        leftInformationButton.setOnMouseClicked((event) -> leftInformation());
        rightInformationButton.setOnMouseClicked((event) -> rightInformation());
        returnShopMenuButton.setOnMouseClicked((event)-> returnShopMenu());
        exchangeTokenButton.setOnMouseClicked((event) -> switchExchange());
        exchangeMoneyButton.setOnMouseClicked((event) -> switchExchange());

        getAllInformation();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void getAllInformation(){
        try {
            ResultSet resultSet = database.select(tableHistoryExchangeToken, columnUserMail + " = \"" + user.getEmail() + "\"");
            while (resultSet.next()){
                listOfInformationToken.add("Data : "+resultSet.getInt(2)+" jetons ---> "+resultSet.getInt(3)+" $");
            }
        }
        catch (Exception e){ System.out.println("Erreur 1 : getAllInformation dans HistoryShoppingMenuController"); }

        try{
            ResultSet resultSet = database.select(tableHistoryExchangeMoney,columnUserMail+" = \""+user.getEmail()+"\"");
            while(resultSet.next()){
                listOfInformationMoney.add("Date : "+resultSet.getInt(2)+" $ ---> "+resultSet.getInt(3)+" jetons");
            }
        }
        catch (Exception e){ System.out.println("Erreur 2 : getAllInformation dans HistoryShoppingMenuController");}

        printInformation();
    }


    private void printInformation(){
        List<String> listOfHistory;
        int indexPrint = indexInformation + 15;

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
        indexInformation += 15;
    }

    private void leftInformation(){
        textInformation.setText("");
        indexInformation -= 15;
        printInformation();

        if(indexInformation <= 15){
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

    private void rightInformation(){
        textInformation.setText("");
        indexInformation += 15;
        printInformation();

        if(indexInformation <= 15){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexInformation + 15) >= listOfInformationToken.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

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
    }

    private void returnShopMenu(){
        settingMenuController.exitSettingMenu();
        ShopMenuController shopMenuController = new ShopMenuController(stage,user,database,soundVolume,backgroundAnimation);
        shopMenuController.setting();
    }

    private void setTextInformation(String information){
        textInformation.setText(textInformation.getText() + information + "\n");
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
