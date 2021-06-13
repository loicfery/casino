package sample;

import games.Database;
import games.DatabaseName;
import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ConnexionMenuController implements InterfaceMenu{

    private BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private User user;
    private
    SettingMenuController settingMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private final MessageInterface messageInterface = new MessageInterface();
    private Language language;
    private Scene scene;

    private double soundVolume;
    private boolean backgroundAnimation;
    private double sizeX;
    private double sizeY;

    private final List<Integer> listOfLayoutX = new ArrayList<>();
    private final List<Integer> listOfLayoutY = new ArrayList<>();
    private final List<Integer> listOfPrefHeight = new ArrayList<>();
    private final List<Integer> listOfPrefWidth = new ArrayList<>();
    private final List<Integer> listOfRadius = new ArrayList<>();

    private final Label labelTitle1 = new Label();
    private final Label labelTitle2 = new Label();
    private final Label labelEmail = new Label();
    private final Label labelPassword = new Label();
    private final Label labelNewMail = new Label();
    private final Label labelNewPassword = new Label();
    private final Label labelNewUserName = new Label();
    private final Label labelError = new Label();

    private final TextField textEmail = new TextField();
    private final PasswordField textPassword = new PasswordField();
    private final TextField textNewEmail = new TextField();
    private final PasswordField textNewPassword = new PasswordField();
    private final TextField textNewUserName = new TextField();

    private final Button buttonLogin = new Button();
    private final Button buttonNewAccount = new Button();
    private final Button buttonInscription = new Button();
    private final Button buttonLoginMenuReturn = new Button();

    private final Circle circleSetting = new Circle();

    public ConnexionMenuController(Stage stage, Database database, Language language, double sizeX, double sizeY){
        this.stage = stage;
        this.soundVolume = 0.5;
        this.backgroundAnimation = true;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation,sizeX,sizeY);
        this.database = database;
        this.language = language;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        System.out.println(labelEmail.getClass());
        System.out.println(textEmail.getClass());
    }

    /** Méthode qui initialise l'interface du menu de connexion **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        scene = new Scene(root, sizeX * 500, sizeY * 500);
        scene.getStylesheets().add(getClass().getResource("connexionMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(labelTitle1,language.getLine("connexionLabelTitle1"), Pos.CENTER,0,50,20,sizeX * 500,new Font(sizeX * 30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelTitle2,language.getLine("connexionLabelTitle2"), Pos.CENTER,0,50,20,sizeX * 500,new Font(sizeX * 30),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelEmail,language.getLine("connexionLabelEmail"),Pos.CENTER,sizeX * 60,sizeY * 200,sizeY * 20,sizeX * 150,new Font(sizeX * 25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPassword,language.getLine("connexionLabelPassword"),Pos.CENTER,sizeX * 260,sizeY * 200,sizeY * 20,sizeX * 200,new Font(sizeX * 25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextField(textEmail,"",Pos.CENTER,sizeX * 40,sizeY * 250,sizeY * 10,sizeX * 200,new Font(sizeX * 15),true,anchorPane);
        setupScene.setTextField(textPassword,"",Pos.CENTER,sizeX * 270,sizeY * 250,sizeY * 10,sizeX * 200,new Font(sizeX * 15),true,anchorPane);
        setupScene.setButton(buttonLogin,language.getLine("connexionButtonLogin"),Pos.CENTER,sizeX * 10,sizeY * 380,sizeY * 20,sizeX * 480,new Font(sizeX * 25),true,anchorPane);
        setupScene.setButton(buttonNewAccount,language.getLine("connexionButtonNewAccount"),Pos.CENTER,sizeX * 10,sizeY * 440,sizeY * 20,sizeX * 480,new Font(sizeX * 25),true,anchorPane);
        setupScene.setButton(buttonLoginMenuReturn,language.getLine("connexionButtonLoginMenuReturn"),Pos.CENTER,sizeX * 10,sizeY * 380,sizeY * 20,sizeX * 480,new Font(sizeX * 25),false,anchorPane);
        setupScene.setButton(buttonInscription,language.getLine("connexionButtonInscription"),Pos.CENTER,sizeX * 10,sizeY * 440,sizeY * 20,sizeX * 480,new Font(sizeX * 25),false,anchorPane);
        setupScene.setLabel(labelNewMail,language.getLine("emailLabel"),Pos.CENTER,sizeX * 100,sizeY * 150,sizeY * 20,sizeX * 150,new Font(sizeX * 25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelNewPassword,language.getLine("passwordLabel"),Pos.CENTER,sizeX * 30,sizeY * 200,sizeY * 20,sizeX * 200,new Font(sizeX * 25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelNewUserName,language.getLine("connexionUserName"),Pos.CENTER,sizeX * 30,sizeY * 250,sizeY * 20,sizeX * 200,new Font(sizeX * 25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setTextField(textNewEmail,"",Pos.CENTER,sizeX * 250,sizeY * 150,sizeY * 20,sizeX * 200,new Font(sizeX * 15),false,anchorPane);
        setupScene.setTextField(textNewPassword,"",Pos.CENTER,sizeX * 250,sizeY * 200,sizeY * 20,sizeX * 200,new Font(sizeX * 15),false,anchorPane);
        setupScene.setTextField(textNewUserName,"",Pos.CENTER,sizeX * 250,sizeY * 250,sizeY * 20,sizeX * 200,new Font(sizeX * 15),false,anchorPane);
        setupScene.setLabel(labelError,"",Pos.CENTER,sizeX * 50,sizeY * 330,sizeY * 20,sizeX * 400,new Font(sizeX * 15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setCircle(circleSetting,Math.max(sizeX,sizeY) * 18,sizeX * 475,sizeY * 30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        buttonLogin.setOnMouseClicked((event)-> goToMainMenu());
        textEmail.setOnAction((event)-> goToMainMenu());
        textPassword.setOnAction((event)-> goToMainMenu());
        buttonNewAccount.setOnMouseClicked((event)-> goToNewAccountMenu());
        buttonLoginMenuReturn.setOnMouseClicked((event)-> goToLoginMenu());
        buttonInscription.setOnMouseClicked((event)-> newAccountGoToMainMenu());
        textNewEmail.setOnAction((event)-> newAccountGoToMainMenu());
        textNewPassword.setOnAction((event)-> newAccountGoToMainMenu());
        textNewUserName.setOnAction((event)-> newAccountGoToMainMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode pour se connecter avec un email et un mot de passe **/
    private void goToMainMenu(){
        if (!textEmail.getText().isEmpty() && !textPassword.getText().isEmpty()) {
            try {
                ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser()+" = '" + textEmail.getText() + "' and "+ databaseName.getTableUserColumnPassword() +" = MD5('" + textPassword.getText() + "')");
                if (resultSet.next()) {
                    labelTitle1.setVisible(false);
                    labelTitle2.setVisible(false);
                    labelEmail.setVisible(false);
                    textEmail.setVisible(false);
                    labelPassword.setVisible(false);
                    textPassword.setVisible(false);
                    buttonLogin.setVisible(false);
                    buttonNewAccount.setVisible(false);
                    settingMenuController.exitSettingMenu();

                    user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getInt(7), resultSet.getInt(6), database);
                    switchMainMenu();
                }
                else {
                    messageInterface.setMessage(labelError,language.getLine("connexionErrorConnexionLabel"),Color.RED);
                }
            }
            catch (Exception e){}

        } else {
            messageInterface.setMessage(labelError,language.getLine("ErrorLabelEmptyField"),Color.RED);
        }

    }

    /** Méthode pour changer le menu pour la création d'un nouveau compte **/
    private void goToNewAccountMenu() {
        labelEmail.setVisible(false);
        textEmail.setVisible(false);
        labelPassword.setVisible(false);
        textPassword.setVisible(false);
        buttonLogin.setVisible(false);
        buttonNewAccount.setVisible(false);
        labelTitle1.setVisible(false);

        labelTitle2.setVisible(true);
        labelNewMail.setVisible(true);
        labelNewPassword.setVisible(true);
        labelNewUserName.setVisible(true);
        textNewEmail.setVisible(true);
        textNewPassword.setVisible(true);
        textNewUserName.setVisible(true);
        buttonInscription.setVisible(true);
        buttonLoginMenuReturn.setVisible(true);
        textNewEmail.setText("");
        textNewPassword.setText("");
        textNewUserName.setText("");
    }

    /** Méthode pour créer un nouveau compte **/
    private void newAccountGoToMainMenu(){
        if (!textNewEmail.getText().isEmpty() && !textNewPassword.getText().isEmpty() && !textNewUserName.getText().isEmpty()) {
            try {
                ResultSet resultSet = database.select(databaseName.getTableUser(),databaseName.getTableUserColumnMailUser()+" = '"+textNewEmail.getText()+"'");

                if(!resultSet.next()) {
                    database.insert(databaseName.getTableUser(), "'" + textNewUserName.getText() + "', '" + textNewEmail.getText() + "',MD5('" + textNewPassword.getText() + "'),'USER',100,0", databaseName.getTableUserColumnUserName() + "," + databaseName.getTableUserColumnMailUser() + "," + databaseName.getTableUserColumnPassword() + "," + databaseName.getTableUserColumnRank() + "," + databaseName.getTableUserColumnMoney() + "," + databaseName.getTableUserColumnToken());
                    setUser(new User(textNewUserName.getText(), textNewEmail.getText(), "USER", 100, 0, database));
                    switchMainMenu();
                }
                else {
                    messageInterface.setMessage(labelError,language.getLine("ErrorLabelMailAlreadyUsed"), Color.RED);
                }
            }
            catch (Exception e){}
        } else {
            messageInterface.setMessage(labelError,language.getLine("ErrorLabelEmptyField"), Color.RED);
        }

    }

    /** Méthode pour changer le menu pour se connecter **/
    private void goToLoginMenu() {
        labelEmail.setVisible(true);
        textEmail.setVisible(true);
        labelPassword.setVisible(true);
        textPassword.setVisible(true);
        buttonLogin.setVisible(true);
        buttonNewAccount.setVisible(true);
        labelTitle1.setVisible(true);

        labelTitle2.setVisible(false);
        labelNewMail.setVisible(false);
        labelNewPassword.setVisible(false);
        labelNewUserName.setVisible(false);
        textNewEmail.setVisible(false);
        textNewPassword.setVisible(false);
        textNewUserName.setVisible(false);
        buttonInscription.setVisible(false);
        buttonLoginMenuReturn.setVisible(false);
    }

    private void setUser(User user){
        this.user = user;
    }

    /** Méthode qui charge le menu principal **/
    private void switchMainMenu(){
        settingMenuController.exitSettingMenu();
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,language, soundVolume,backgroundAnimation,sizeX,sizeY);
        mainMenuController.setting();
    }

    /** Méthode qui modifie le volume sonore des effets sonores **/
    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume <= 1.0 && newSoundVolume >= 0){
            soundVolume = newSoundVolume;
        }
    }

    /** Méthode qui active/désactive les animations en arrière plan **/
    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    /** Méthode qui ouvre l'interface du menu des paramètres **/
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
        labelTitle1.setText(language.getLine("connexionLabelTitle1"));
        labelTitle2.setText(language.getLine("connexionLabelTitle2"));
        labelEmail.setText(language.getLine("connexionLabelEmail"));
        labelPassword.setText(language.getLine("connexionLabelPassword"));
        buttonLogin.setText(language.getLine("connexionButtonLogin"));
        buttonNewAccount.setText(language.getLine("connexionButtonNewAccount"));
        buttonLoginMenuReturn.setText(language.getLine("connexionButtonLoginMenuReturn"));
        buttonInscription.setText(language.getLine("connexionButtonInscription"));
        labelNewMail.setText(language.getLine("emailLabel"));
        labelNewPassword.setText(language.getLine("passwordLabel"));
        labelNewUserName.setText(language.getLine("connexionUserName"));

        refreshTest();
    }

    private void refreshPosition(){
        scene.setRoot(new BorderPane());
        scene = new Scene(root, sizeX * 500, sizeY * 500);
        stage.setScene(scene);

        labelTitle1.setPrefWidth(sizeX * 500);
        labelTitle1.setFont(new Font(sizeX * 30));

        labelTitle2.setPrefWidth(sizeX * 500);
        labelTitle2.setFont(new Font(sizeX * 30));

        labelEmail.setLayoutX(sizeX * 60);
        labelEmail.setLayoutY(sizeY * 200);
        labelEmail.setPrefHeight(sizeY * 20);
        labelEmail.setPrefWidth(sizeX * 150);
        labelEmail.setFont(new Font(sizeX * 25));

        labelPassword.setLayoutX(sizeX * 260);
        labelPassword.setLayoutY(sizeY * 200);
        labelPassword.setPrefHeight(sizeY * 20);
        labelPassword.setPrefWidth(sizeX * 200);
        labelPassword.setFont(new Font(sizeX * 25));

        textEmail.setLayoutX(sizeX * 40);
        textEmail.setLayoutY(sizeY * 250);
        textEmail.setPrefHeight(sizeY * 10);
        textEmail.setPrefWidth(sizeX * 200);
        textEmail.setFont(new Font(sizeX * 15));

        textPassword.setLayoutX(sizeX * 270);
        textPassword.setLayoutY(sizeY * 250);
        textPassword.setPrefHeight(sizeY * 10);
        textPassword.setPrefWidth(sizeX * 200);
        textPassword.setFont(new Font(sizeX * 15));

        buttonLogin.setLayoutX(sizeX * 10);
        buttonLogin.setLayoutY(sizeY * 380);
        buttonLogin.setPrefHeight(sizeY * 20);
        buttonLogin.setPrefWidth(sizeX * 480);
        buttonLogin.setFont(new Font(sizeX * 25));

        buttonNewAccount.setLayoutX(sizeX * 10);
        buttonNewAccount.setLayoutY(sizeY * 440);
        buttonNewAccount.setPrefHeight(sizeY * 20);
        buttonNewAccount.setPrefWidth(sizeX * 480);
        buttonNewAccount.setFont(new Font(sizeX * 25));

        buttonLoginMenuReturn.setLayoutX(sizeX * 10);
        buttonLoginMenuReturn.setLayoutY(sizeY * 380);
        buttonLoginMenuReturn.setPrefHeight(sizeY * 20);
        buttonLoginMenuReturn.setPrefWidth(sizeX * 480);
        buttonLoginMenuReturn.setFont(new Font(sizeX * 25));

        buttonInscription.setLayoutX(sizeX * 10);
        buttonInscription.setLayoutY(sizeY * 440);
        buttonInscription.setPrefHeight(sizeY * 20);
        buttonInscription.setPrefWidth(sizeX * 480);
        buttonInscription.setFont(new Font(sizeX * 25));

        labelNewMail.setLayoutX(sizeX * 100);
        labelNewMail.setLayoutY(sizeY * 150);
        labelNewMail.setPrefHeight(sizeY * 20);
        labelNewMail.setPrefWidth(sizeX * 150);
        labelNewMail.setFont(new Font(sizeX * 25));

        labelNewPassword.setLayoutX(sizeX * 30);
        labelNewPassword.setLayoutY(sizeY * 200);
        labelNewPassword.setPrefHeight(sizeY * 20);
        labelNewPassword.setPrefWidth(sizeX * 200);
        labelNewPassword.setFont(new Font(sizeX * 25));

        labelNewUserName.setLayoutX(sizeX * 30);
        labelNewUserName.setLayoutY(sizeY * 250);
        labelNewUserName.setPrefHeight(sizeY * 20);
        labelNewUserName.setPrefWidth(sizeX * 200);
        labelNewUserName.setFont(new Font(sizeX * 25));

        textNewEmail.setLayoutX(sizeX * 250);
        textNewEmail.setLayoutY(sizeY * 150);
        textNewEmail.setPrefHeight(sizeY * 20);
        textNewEmail.setPrefWidth(sizeX * 200);
        textNewEmail.setFont(new Font(sizeX * 15));

        textNewPassword.setLayoutX(sizeX * 250);
        textNewPassword.setLayoutY(sizeY * 200);
        textNewPassword.setPrefHeight(sizeY * 20);
        textNewPassword.setPrefWidth(sizeX * 200);
        textNewPassword.setFont(new Font(sizeX * 15));

        textNewUserName.setLayoutX(sizeX * 250);
        textNewUserName.setLayoutY(sizeY * 250);
        textNewUserName.setPrefHeight(sizeY * 20);
        textNewUserName.setPrefWidth(sizeX * 200);
        textNewUserName.setFont(new Font(sizeX * 15));

        labelError.setLayoutX(sizeX * 50);
        labelError.setLayoutY(sizeY * 330);
        labelError.setPrefHeight(sizeY * 20);
        labelError.setPrefWidth(sizeX * 400);
        labelError.setFont(new Font(sizeX * 15));

        circleSetting.setLayoutX(sizeX * 475);
        circleSetting.setLayoutY(sizeY * 30);
        circleSetting.setRadius(Math.max(sizeX,sizeY) * 18);
    }

    private void refreshTest(){
        scene.setRoot(new BorderPane());
        scene = new Scene(root, sizeX * 500, sizeY * 500);
        stage.setScene(scene);

        for(int index = 0; index < anchorPane.getChildren().size(); index ++){
            if(anchorPane.getChildren().get(index) instanceof Label){
                refreshPositionLabel(anchorPane.getChildren().get(index));
            }
            if(anchorPane.getChildren().get(index) instanceof TextField){
                refreshPositionTextField(anchorPane.getChildren().get(index));
            }
            if(anchorPane.getChildren().get(index) instanceof Button){
                refreshPositionButton(anchorPane.getChildren().get(index));
            }
            if(anchorPane.getChildren().get(index) instanceof Circle){
                refreshPositionCircle(anchorPane.getChildren().get(index));
            }
        }
    }

    private void refreshPositionLabel(Node node){
        node.setLayoutX(sizeX * node.getLayoutX());
        node.setLayoutY(sizeY * node.getLayoutY());
        ((Label) node).setPrefHeight(sizeY * ((Label) node).getPrefHeight());
        ((Label) node).setPrefWidth(sizeX * ((Label) node).getPrefWidth());
        ((Label) node).setFont(new Font(sizeX * ((Label) node).getFont().getSize()));
    }

    private void refreshPositionTextField(Node node){
        node.setLayoutX(sizeX * node.getLayoutX());
        node.setLayoutY(sizeY * node.getLayoutY());
        ((TextField) node).setPrefHeight(sizeY * ((TextField) node).getPrefHeight());
        ((TextField) node).setPrefWidth(sizeX * ((TextField) node).getPrefWidth());
        ((TextField) node).setFont(new Font(sizeX * ((TextField) node).getFont().getSize()));
    }

    private void refreshPositionButton(Node node){
        node.setLayoutX(sizeX * node.getLayoutX());
        node.setLayoutY(sizeY * node.getLayoutY());
        ((Button) node).setPrefHeight(sizeY * ((Button) node).getPrefHeight());
        ((Button) node).setPrefWidth(sizeX * ((Button) node).getPrefWidth());
        ((Button) node).setFont(new Font(sizeX * ((Button) node).getFont().getSize()));
    }

    private void refreshPositionCircle(Node node){
        node.setLayoutX(sizeX * node.getLayoutX());
        node.setLayoutY(sizeY * node.getLayoutY());
        ((Circle) node).setRadius(Math.max(sizeX,sizeY) * ((Circle) node).getRadius());
    }
}
