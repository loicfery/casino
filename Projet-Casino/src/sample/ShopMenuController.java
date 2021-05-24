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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopMenuController implements InterfaceMenu{

    private BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private SettingMenuController settingMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private final MessageInterface messageInterface = new MessageInterface();
    private Language language;

    private List<String> listOfShopToken;
    private List<String> listOfShopMoney;
    private List<Button> listOfButtonShopToken;
    private List<Button> listOfButtonShopMoney;
    private List<Label> listOfLabelShopToken;
    private List<Label> listOfLabelShopMoney;
    private List<Button> listOfButtonShopTokenDelete;
    private List<Button> listOfButtonShopMoneyDelete;

    private final Circle circleSetting = new Circle();

    private final Button returnMainMenuButton = new Button();
    private final Button historyShoppingButton = new Button();
    private final Button leftShopTokenButton = new Button();
    private final Button rightShopTokenButton = new Button();
    private final Button leftShopMoneyButton = new Button();
    private final Button rightShopMoneyButton = new Button();
    private final Button addMoneyButton = new Button();
    private Button addExchangeTokenButton;
    private Button addExchangeMoneyButton;

    private final Label titleShopTokenLabel = new Label();
    private final Label titleShopMoneyLabel = new Label();
    private final Label errorLabel = new Label();

    private final Line middleLine = new Line();

    private double soundVolume;
    private boolean backgroundAnimation;
    private int positionOriginXToken = 240;
    private int positionOriginXMoney = 640;
    private int positionOriginY = 285;
    private int indexListToken = 0;
    private int indexListMoney = 0;
    private boolean addTokenInformation = false;
    private boolean addMoneyInformation = false;
    private boolean ADMIN = false;
    private int indexToken = 0;
    private int indexMoney = 0;

    public ShopMenuController(Stage stage,User user, Database database, Language language, double soundVolume, boolean backgroundAnimation){
        this.stage = stage;
        this.user = user;
        this.database = database;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        this.language = language;

        if(user.getRank().equals("ADMIN")){
            ADMIN = true;
        }
    }

    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("shopMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(titleShopTokenLabel,language.getTitleShopToken()+user.getToken(),Pos.CENTER,0,200,20,400,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(titleShopMoneyLabel,language.getTitleShopMoney()+user.getMoney(),Pos.CENTER,400,200,20,400,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(errorLabel,"",Pos.CENTER,250,700,20,300,new Font(20),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setButton(leftShopTokenButton,"<-",Pos.CENTER,100,750,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(rightShopTokenButton,"->",Pos.CENTER,250,750,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(leftShopMoneyButton,"<-",Pos.CENTER,500,750,20,50,new Font(15),true,anchorPane);
        setupScene.setButton(rightShopMoneyButton,"->",Pos.CENTER,650,750,20,50,new Font(15),true,anchorPane);

        setupScene.setButton(addMoneyButton,language.getShopMenuControllerAddMoneyButton(),Pos.CENTER,360,14,60,200,new Font(20),true,anchorPane);
        setupScene.setButton(historyShoppingButton,language.getShopMenuControllerHistoryShoppingButton(), Pos.CENTER,150,14,60,200,new Font(20),true,anchorPane);
        setupScene.setButton(returnMainMenuButton,language.getQuitButton(),Pos.CENTER,14.0,14.0,60,123.0,new Font(20.0),true,anchorPane);

        setupScene.setLine(middleLine,400,200,0,0,0,520,Paint.valueOf("BLACK"),1.0,true,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        historyShoppingButton.setOnMouseClicked((event) -> goToHistoryShoppingMenu());
        returnMainMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        leftShopMoneyButton.setOnMouseClicked((event) -> leftShopMoney());
        rightShopMoneyButton.setOnMouseClicked((event) -> rightShopMoney());
        leftShopTokenButton.setOnMouseClicked((event) -> leftShopToken());
        rightShopTokenButton.setOnMouseClicked((event) -> rightShopToken());
        addMoneyButton.setOnMouseClicked((event)-> goToBuyingMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());

        getShopList();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /**
     * Méthode qui récupère dans la base de données les échanges de jetons et d'argents
     **/
    private void getShopList(){
        listOfShopToken = new ArrayList<>();
        listOfShopMoney = new ArrayList<>();
        listOfButtonShopToken = new ArrayList<>();
        listOfButtonShopMoney = new ArrayList<>();
        listOfLabelShopToken = new ArrayList<>();
        listOfLabelShopMoney = new ArrayList<>();
        listOfButtonShopTokenDelete = new ArrayList<>();
        listOfButtonShopMoneyDelete = new ArrayList<>();

        try {
            ResultSet resultSet = database.select(databaseName.getTableExchangeMoney(), "");
            while (resultSet.next()) {
                listOfShopMoney.add(resultSet.getInt(2)+" $ ---> "+resultSet.getInt(3)+" "+language.getToken());
            }

            resultSet = database.select(databaseName.getTableExchangeToken(),"");
            while (resultSet.next()){
                listOfShopToken.add(resultSet.getInt(2)+" "+language.getToken()+" ---> "+resultSet.getInt(3)+" $");
            }

            setupShopList();
            printShopMoneyInformation();
            printShopTokenInformation();
        }
        catch (Exception e){}
    }

    /**
     * Méthode qui prépare l'affichage des échanges d'argents et de jetons
     **/
    private void setupShopList(){
        addExchangeTokenButton = new Button();
        addExchangeMoneyButton = new Button();
        Button button;
        Label label;
        int positionY = positionOriginY;

        for(int index = 0; index < listOfShopToken.size(); index ++){
            int finalIndex = index;
            label = new Label();
            setupScene.setLabel(label,listOfShopToken.get(index),Pos.CENTER_LEFT,14,positionY,20,200,new Font(20),Paint.valueOf("BLACK"),false,anchorPane);
            listOfLabelShopToken.add(label);

            button = new Button();
            setupScene.setButton(button,language.getShopMenuControllerExchangeButton(),Pos.CENTER, positionOriginXToken,positionY,20,100,new Font(15),false,anchorPane);
            button.setOnMouseClicked((event)-> exchange(listOfShopToken.get(finalIndex)));
            listOfButtonShopToken.add(button);
            indexToken ++;

            button = new Button();
            setupScene.setButton(button, "X",Pos.CENTER,positionOriginXToken + 110,positionY,20,20,new Font(15),false,anchorPane);
            button.setOnMouseClicked((event)-> deleteInformation(listOfLabelShopToken,listOfButtonShopToken,listOfButtonShopTokenDelete,listOfShopToken,finalIndex,"token",addExchangeTokenButton));
            listOfButtonShopTokenDelete.add(button);

            if((index % 4) == 0 && index != 0){
                positionY = positionOriginY;
            }
            else {
                positionY += 80;
            }
        }

        setupScene.setButton(addExchangeTokenButton,"+",Pos.CENTER,positionOriginXToken + 110,positionY,20,20,new Font(10),false,anchorPane);
        addExchangeTokenButton.setOnMouseClicked((event)-> addTokenInformation(336,220,270,110, addExchangeTokenButton));

        positionY = positionOriginY;
        for(int index = 0; index < listOfShopMoney.size(); index ++){
            int finalIndex = index;
            label = new Label();
            setupScene.setLabel(label,listOfShopMoney.get(index),Pos.CENTER_LEFT,414,positionY,20,200,new Font(20),Paint.valueOf("BLACK"),false,anchorPane);
            listOfLabelShopMoney.add(label);
            indexMoney ++;

            button = new Button();
            setupScene.setButton(button,language.getShopMenuControllerExchangeButton(),Pos.CENTER, positionOriginXMoney,positionY,20,100,new Font(15),false,anchorPane);
            button.setOnMouseClicked((event) -> exchange(listOfShopMoney.get(finalIndex)));
            listOfButtonShopMoney.add(button);

            button = new Button();
            setupScene.setButton(button,"X",Pos.CENTER,positionOriginXMoney + 110,positionY,20,20,new Font(15),false,anchorPane);
            button.setOnMouseClicked((event)-> deleteInformation(listOfLabelShopMoney,listOfButtonShopMoney,listOfButtonShopMoneyDelete,listOfShopMoney,finalIndex,"money",addExchangeMoneyButton));
            listOfButtonShopMoneyDelete.add(button);

            if((index % 4 ) == 0 && index != 0){
                positionY = positionOriginY;
            }
            else {
                positionY += 80;
            }
        }

        setupScene.setButton(addExchangeMoneyButton,"+",Pos.CENTER,positionOriginXMoney + 110,positionY,20,20,new Font(10),false,anchorPane);
        addExchangeMoneyButton.setOnMouseClicked((event)-> addMoneyInformation(336,220,270,110, addExchangeMoneyButton));
    }

    /**
     * Méthode qui affiche les échanges de jetons
     **/
    private void printShopTokenInformation(){
        int indexMax = (indexListToken + 5);

        if(indexMax > listOfShopToken.size()){
            indexMax = listOfShopToken.size();
            rightShopTokenButton.setVisible(false);
        }
        else {
            rightShopTokenButton.setVisible(true);
        }

        printListExchange(indexMax, indexListToken, listOfLabelShopToken, listOfButtonShopToken, listOfButtonShopTokenDelete, addExchangeTokenButton, leftShopTokenButton);
    }

    private void printListExchange(int indexMax, int indexListToken, List<Label> listOfLabelShopToken, List<Button> listOfButtonShopToken, List<Button> listOfButtonShopTokenDelete, Button addExchangeTokenButton, Button leftShopTokenButton) {
        for(int index = indexListToken; index < indexMax; index ++){
            listOfLabelShopToken.get(index).setVisible(true);
            listOfButtonShopToken.get(index).setVisible(true);
            if(ADMIN) {
                listOfButtonShopTokenDelete.get(index).setVisible(true);
            }
        }

        if(ADMIN){
            addExchangeTokenButton.setVisible(true);
        }

        if(indexListToken <= 0){
            leftShopTokenButton.setVisible(false);
        }
        else {
            leftShopTokenButton.setVisible(true);
        }
    }

    /**
     * Méthode qui affiche les échanges d'argents
     **/
    private void printShopMoneyInformation(){
        int indexMax = (indexListMoney + 5);

        if(indexMax > (listOfShopMoney.size())){
            indexMax = listOfShopMoney.size();
            rightShopMoneyButton.setVisible(false);
        }
        else {
            rightShopMoneyButton.setVisible(true);
        }

        printListExchange(indexMax, indexListMoney, listOfLabelShopMoney, listOfButtonShopMoney, listOfButtonShopMoneyDelete, addExchangeMoneyButton, leftShopMoneyButton);
    }

    private void hideExchange(int indexListToken, List<String> listOfShopToken, Button addExchangeTokenButton, List<Label> listOfLabelShopToken, List<Button> listOfButtonShopToken, List<Button> listOfButtonShopTokenDelete) {
        int indexMax = (indexListToken + 5);

        if(indexMax > listOfShopToken.size()){
            indexMax = listOfShopToken.size();
            addExchangeTokenButton.setVisible(false);
        }

        for(int index = indexListToken; index < indexMax; index ++){
            listOfLabelShopToken.get(index).setVisible(false);
            listOfButtonShopToken.get(index).setVisible(false);
            listOfButtonShopTokenDelete.get(index).setVisible(false);
        }
    }

    /**
     * Méthode qui affiche les 5 échanges de jetons suivants
     **/
    private void rightShopToken(){
        cancelAddInformation();
        hideExchange(indexListToken, listOfShopToken, addExchangeTokenButton, listOfLabelShopToken, listOfButtonShopToken, listOfButtonShopTokenDelete);
        indexListToken += 5;
        printShopTokenInformation();
    }

    /**
     * Méthode qui affiche les 5 échanges de jetons précédents
     **/
    private void leftShopToken(){
        cancelAddInformation();
        hideExchange(indexListToken, listOfShopToken, addExchangeTokenButton, listOfLabelShopToken, listOfButtonShopToken, listOfButtonShopTokenDelete);
        indexListToken -= 5;
        printShopTokenInformation();
    }

    /**
     * Méthode qui affiche les 5 échanges d'argents suivants
     **/
    private void rightShopMoney(){
        cancelAddInformation();
        hideExchange(indexListMoney, listOfShopMoney, addExchangeMoneyButton, listOfLabelShopMoney, listOfButtonShopMoney, listOfButtonShopMoneyDelete);
        indexListMoney += 5;
        printShopMoneyInformation();
    }

    /**
     * Méthode qui affiche les 5 échanges d'argents précédents
     **/
    private void leftShopMoney(){
        cancelAddInformation();
        hideExchange(indexListMoney, listOfShopMoney, addExchangeMoneyButton, listOfLabelShopMoney, listOfButtonShopMoney, listOfButtonShopMoneyDelete);
        indexListMoney -= 5;
        printShopMoneyInformation();
    }

    /**
     * Méthode qui permet de valider l'échange si l'utilisateur possède le nombre suffisant de jeton / d'argent
     **/
    private void exchange(String line){
        cancelAddInformation();
        String[] lines = line.split(" ");
        int money;
        int token;

        if(lines[1].equals("$")){
            try {
                money = Integer.parseInt(lines[0]);
                token = Integer.parseInt(lines[3]);

                if(user.getMoney() >= money){
                    user.removeMoney(money);
                    user.addToken(token);
                    database.insert(databaseName.getTableHistoryExchangeMoney(),"\""+user.getEmail()+"\","+money+","+token+",\""+getCurrentDate()+"\"",databaseName.getTableHistoryExchangeMoneyColumnMailUser()+","+databaseName.getTableHistoryExchangeMoneyColumnPriceMoney()+","+databaseName.getTableHistoryExchangeMoneyColumnMoneyGain()+","+databaseName.getTableHistoryExchangeMoneyColumnDate());
                    messageInterface.setMessage(errorLabel,language.getShopMenuControllerLabelErrorExchangeValid(),Color.GREEN);
                }
                else {
                    messageInterface.setMessage(errorLabel,language.getLabelErrorMoneyNotEnough(),Color.RED);
                }
            }
            catch (Exception e){ System.out.println("Problème dans exchange de shopMenuController : argent"); }
        }

        if(lines[1].equals("jetons")){
            try{
                money = Integer.parseInt(lines[3]);
                token = Integer.parseInt(lines[0]);
                if(user.getToken() >= token){
                    user.addMoney(money);
                    user.removeToken(token);
                    database.insert(databaseName.getTableHistoryExchangeToken(),"\""+user.getEmail()+"\","+token+","+money+",\""+getCurrentDate()+"\"",databaseName.getTableHistoryExchangeTokenColumnMailUser()+","+databaseName.getTableHistoryExchangeTokenColumnPriceToken()+","+databaseName.getTableHistoryExchangeTokenColumnMoneyGain()+","+databaseName.getTableHistoryExchangeTokenColumnDate());
                    messageInterface.setMessage(errorLabel,language.getShopMenuControllerLabelErrorExchangeValid(),Color.GREEN);
                }
                else {
                    messageInterface.setMessage(errorLabel,language.getLabelErrorTokenNotEnough(),Color.RED);
                }
            }
            catch (Exception e){ System.out.println("Problème dans exchange de shopMenuController token"); }
        }

        titleShopTokenLabel.setText(language.getShopMenuControllerTitleShopTokenLabel()+user.getToken());
        titleShopMoneyLabel.setText(language.getShopMenuControllerTitleShopMoneyLabel()+user.getMoney());
    }

    /**
     * Méthode pour supprimer un échange
     * Accessible seulement par un ADMIN
     **/
    private void deleteInformation(List<Label> listOfLabel, List<Button> listOfButtonExchange, List<Button> listOfButtonDelete, List<String> listOfInformation, int indexList, String type, Button addExchangeButton){
        cancelAddInformation();

        double positionY = listOfLabel.get(indexList).getLayoutY();

        listOfLabel.get(indexList).setVisible(false);
        listOfLabel.remove(indexList);
        listOfButtonDelete.get(indexList).setVisible(false);
        listOfButtonDelete.remove(indexList);
        listOfButtonExchange.get(indexList).setVisible(false);
        listOfButtonExchange.remove(indexList);
        String information = listOfInformation.remove(indexList);

        for(int index = indexList; index < listOfInformation.size(); index ++){
            int finalIndex = index;
            listOfButtonDelete.get(index).setLayoutY(positionY);
            listOfButtonDelete.get(index).setOnMouseClicked((event)-> deleteInformation(listOfLabel,listOfButtonExchange,listOfButtonDelete,listOfInformation,finalIndex,type,addExchangeButton));
            listOfButtonExchange.get(index).setLayoutY(positionY);
            listOfLabel.get(index).setLayoutY(positionY);

            if(positionY % 4 == 0){
                positionY = positionOriginY;
            }
            else {
                positionY += 80;
            }
        }

        addExchangeButton.setLayoutY(positionY);

        if(type.equals("token")){
            indexToken  --;
            database.delete(databaseName.getTableExchangeToken(),databaseName.getTableExchangeTokenColumnPriceToken()+" = "+information.split(" ")[0]+" && "+databaseName.getTableExchangeTokenColumnMoneyGain()+" = "+information.split(" ")[3]);
            printShopTokenInformation();
        }
        else if(type.equals("money"))
        {
            indexMoney --;
            database.delete(databaseName.getTableExchangeMoney(),databaseName.getTableExchangeMoneyColumnPriceMoney()+" = "+information.split(" ")[0]+" && "+databaseName.getTableExchangeMoneyColumnTokenGain()+" = "+information.split(" ")[3]);
            printShopMoneyInformation();
        }
    }

    /**
     * Méthode pour ajouter un échange de jeton
     * Accessible seulement par un ADMIN
     **/
    private void addTokenInformation(double xMoney, double xToken, double xLabel, double xButton, Button addInformationButton){
        cancelAddInformation();
        TextField textMoney = new TextField();
        TextField textToken = new TextField();
        Label label = new Label();
        Button validButton = new Button();

        xMoney = addInformationButton.getLayoutX() - xMoney;
        xToken = addInformationButton.getLayoutX() - xToken;
        xLabel = addInformationButton.getLayoutX() - xLabel;
        xButton = addInformationButton.getLayoutX() - xButton;

        addTokenInformation = true;

        setupScene.setTextField(textMoney, "", Pos.CENTER, xMoney, addInformationButton.getLayoutY(), 20, 70, new Font(15), true, anchorPane);
        setupScene.setTextField(textToken,"",Pos.CENTER,xToken,addInformationButton.getLayoutY(),20,70,new Font(15),true,anchorPane);
        setupScene.setLabel(label, " ---> ", Pos.CENTER, xLabel, addInformationButton.getLayoutY(), 20, 50, new Font(15), Paint.valueOf("BLACK"), true, anchorPane);
        setupScene.setButton(validButton,language.getAddButton(),Pos.CENTER,xButton,addInformationButton.getLayoutY(),20,100,new Font(15),true,anchorPane);
        validButton.setOnMouseClicked((event) -> validAddTokenInformation(textMoney,textToken,addInformationButton,addInformationButton.getLayoutY()));
    }

    /**
     * Méthode pour ajouter un échange d'argent
     * Accessible seulement par un ADMIN
     **/
    private void addMoneyInformation(double xMoney, double xToken, double xLabel, double xButton, Button addInformationButton){
        cancelAddInformation();
        TextField textMoney = new TextField();
        TextField textToken = new TextField();
        Label label = new Label();
        Button validButton = new Button();

        xMoney = addInformationButton.getLayoutX() - xMoney;
        xToken = addInformationButton.getLayoutX() - xToken;
        xLabel = addInformationButton.getLayoutX() - xLabel;
        xButton = addInformationButton.getLayoutX() - xButton;

        addMoneyInformation = true;

        setupScene.setTextField(textMoney, "", Pos.CENTER, xMoney, addInformationButton.getLayoutY(), 20, 70, new Font(15), true, anchorPane);
        setupScene.setTextField(textToken,"",Pos.CENTER,xToken,addInformationButton.getLayoutY(),20,70,new Font(15),true,anchorPane);
        setupScene.setLabel(label, " ---> ", Pos.CENTER, xLabel, addInformationButton.getLayoutY(), 20, 50, new Font(15), Paint.valueOf("BLACK"), true, anchorPane);
        setupScene.setButton(validButton,language.getAddButton(),Pos.CENTER,xButton,addInformationButton.getLayoutY(),20,100,new Font(15),true,anchorPane);
        validButton.setOnMouseClicked((event)-> validAddMoneyInformation(textMoney,textToken,addInformationButton,addInformationButton.getLayoutY()));
    }

    /**
     * Méthode pour valider l'ajout d'un échange de jeton
     * Accessible seulement par un ADMIN
     **/
    private void validAddTokenInformation(TextField textMoney, TextField textToken, Button addInformationButton, double positionY){
        if(!textToken.getText().isEmpty() && !textMoney.getText().isEmpty()) {
            Button exchangeButton = new Button();
            Button deleteInformationButton = new Button();
            Label labelInformation = new Label();

            for(int index = 0; index < 4; index ++) {
                anchorPane.getChildren().get(anchorPane.getChildren().size() - 1).setVisible(false);
                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);
            }

            listOfShopToken.add(textToken.getText() + " jetons" +" ---> " + textMoney.getText()+" $");
            setupScene.setButton(exchangeButton, language.getShopMenuControllerExchangeButton(), Pos.CENTER, positionOriginXToken, positionY, 20, 100, new Font(15), false, anchorPane);
            exchangeButton.setOnMouseClicked((event) -> exchange(listOfShopToken.get(indexToken - 1)));
            listOfButtonShopToken.add(exchangeButton);

            indexToken ++;

            setupScene.setLabel(labelInformation, textToken.getText() + " " + language.getToken() +" ---> " + textMoney.getText() + " $", Pos.CENTER, 14, positionY, 20, 200, new Font(20), Paint.valueOf("BLACK"), false, anchorPane);
            listOfLabelShopToken.add(labelInformation);

            setupScene.setButton(deleteInformationButton, "X", Pos.CENTER, positionOriginXToken + 110, positionY, 20, 20, new Font(15), false, anchorPane);
            deleteInformationButton.setOnMouseClicked((event) -> deleteInformation(listOfLabelShopToken, listOfButtonShopToken, listOfButtonShopTokenDelete, listOfShopToken, listOfShopToken.size() - 1, "token",addExchangeTokenButton));
            listOfButtonShopTokenDelete.add(deleteInformationButton);

            database.insert(databaseName.getTableExchangeToken(),textToken.getText()+","+textMoney.getText(),databaseName.getTableExchangeTokenColumnPriceToken()+","+databaseName.getTableExchangeTokenColumnMoneyGain());

            if (positionY >= 685) {
                positionY = positionOriginY;
            } else {
                positionY += 80;
            }

            addInformationButton.setLayoutY(positionY);

            addTokenInformation = false;

            printShopTokenInformation();
        }
    }

    /**
     * Méthode pour valider l'ajout d'un échange d'argent
     * Accessible seulement par un ADMIN
     **/
    private void validAddMoneyInformation(TextField textMoney, TextField textToken,Button addInformationButton, double positionY){
        if(!textMoney.getText().isEmpty() && !textToken.getText().isEmpty()) {
            Button exchangeButton = new Button();
            Button deleteInformationButton = new Button();
            Label labelInformation = new Label();

            for(int index = 0; index < 4; index ++) {
                anchorPane.getChildren().get(anchorPane.getChildren().size() - 1).setVisible(false);
                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);
            }

            listOfShopMoney.add(textMoney.getText() + " $ ---> " + textToken.getText() + " jetons");

            setupScene.setButton(exchangeButton, language.getShopMenuControllerExchangeButton(), Pos.CENTER, positionOriginXMoney, positionY, 20, 100, new Font(15), false, anchorPane);
            exchangeButton.setOnMouseClicked((event) -> exchange(listOfShopMoney.get(indexMoney - 1)));
            listOfButtonShopMoney.add(exchangeButton);

            indexMoney ++;

            setupScene.setLabel(labelInformation, textMoney.getText() + " $ ---> " + textToken.getText() + " " + language.getToken(), Pos.CENTER, 414, positionY, 20, 200, new Font(20), Paint.valueOf("BLACK"), false, anchorPane);
            listOfLabelShopMoney.add(labelInformation);

            setupScene.setButton(deleteInformationButton, "X", Pos.CENTER, positionOriginXMoney + 110, positionY, 20, 20, new Font(15), false, anchorPane);
            deleteInformationButton.setOnMouseClicked((event) -> deleteInformation(listOfLabelShopMoney, listOfButtonShopMoney, listOfButtonShopMoneyDelete, listOfShopMoney, listOfShopMoney.size() - 1, "money",addExchangeMoneyButton));
            listOfButtonShopMoneyDelete.add(deleteInformationButton);

            database.insert(databaseName.getTableExchangeMoney(),textMoney.getText()+","+textToken.getText(),databaseName.getTableExchangeMoneyColumnPriceMoney()+","+databaseName.getTableExchangeMoneyColumnTokenGain());

            if (positionY >= 685) {
                positionY = positionOriginY;
            } else {
                positionY += 80;
            }

            addInformationButton.setLayoutY(positionY);

            addMoneyInformation = false;

            printShopMoneyInformation();
        }
    }

    /**
     * Méthode pour annuler l'ajout d'un échange de jeton ou d'argent avant de valider
     * Accessible seulement par un ADMIN
     **/
    private void cancelAddInformation(){
        if(addTokenInformation){
            addTokenInformation = false;
            for(int index = 0; index < 4; index ++) {
                anchorPane.getChildren().get(anchorPane.getChildren().size() - 1).setVisible(false);
                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);
            }
        }
        else if(addMoneyInformation){
            addMoneyInformation = false;
            for(int index = 0; index < 4; index ++) {
                anchorPane.getChildren().get(anchorPane.getChildren().size() - 1).setVisible(false);
                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);
            }
        }
    }

    /**
     * Méthode pour aller dans le menu d'historique des échanges
     **/
    private void goToHistoryShoppingMenu(){
        settingMenuController.exitSettingMenu();
        HistoryShoppingMenuController historyShoppingMenuController = new HistoryShoppingMenuController(user,stage,database,language,soundVolume,backgroundAnimation);
        historyShoppingMenuController.setting();
    }

    /**
     * Méthode pour aller dans le menu d'achat d'argent
     * Utilisable pour ajouter de l'argent pour simuler l'achat avec de l'argent réel
     **/
    private void goToBuyingMenu(){
        settingMenuController.exitSettingMenu();
        BuyingMoneyMenuController buyingMoneyMenuController = new BuyingMoneyMenuController(user,language,titleShopMoneyLabel);
        buyingMoneyMenuController.setting();
    }

    /**
     * Méthode pour quitter ce menu et retourner dans le menu principale
     **/
    private void goToMainMenu(){
        settingMenuController.exitSettingMenu();
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,language,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    /**
     * Méthode qui permet de modifier le volume sonore
     **/
    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
        }
    }

    /**
     * Méthode qui d'afficher ou non les animations en arrière plan
     **/
    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    /**
     * Méthode qui permet d'accéder au menu des paramètres
     **/
    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }

    private String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public void setLanguage(Language language){ this.language = language; }

    public void refresh(){
        setting();
        settingMenuController.exitSettingMenu();
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        settingMenuController.setting();
    }
}
