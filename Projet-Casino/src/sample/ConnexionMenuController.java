package sample;

import games.Database;
import games.DatabaseName;
import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

import java.sql.ResultSet;


public class ConnexionMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private User user;
    private final SettingMenuController settingMenuController;
    private final Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private final MessageInterface messageInterface = new MessageInterface();

    private double soundVolume;
    private boolean backgroundAnimation;

    private final Label labelTitle = new Label();
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

    public ConnexionMenuController(Stage stage, Database database){
        this.stage = stage;
        this.soundVolume = 0.5;
        this.backgroundAnimation = true;
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);
        this.database = database;
    }

    /** Méthode qui initialise l'interface de connexion **/
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("connexionMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(labelTitle,"Bienvenue au casino", Pos.CENTER,100,50,20,300,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelEmail,"Email",Pos.CENTER,60,200,20,150,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPassword,"Mot de passe",Pos.CENTER,260,200,20,200,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextField(textEmail,"",Pos.CENTER,40,250,10,200,new Font(15),true,anchorPane);
        setupScene.setTextField(textPassword,"",Pos.CENTER,270,250,10,200,new Font(15),true,anchorPane);
        setupScene.setButton(buttonLogin,"Connexion",Pos.CENTER,10,380,20,480,new Font(25),true,anchorPane);
        setupScene.setButton(buttonNewAccount,"Nouveau Compte",Pos.CENTER,10,440,20,480,new Font(25),true,anchorPane);
        setupScene.setButton(buttonLoginMenuReturn,"Se connecter",Pos.CENTER,10,380,20,480,new Font(25),false,anchorPane);
        setupScene.setButton(buttonInscription,"S'inscrire",Pos.CENTER,10,440,20,480,new Font(25),false,anchorPane);
        setupScene.setLabel(labelNewMail,"Email :",Pos.CENTER,100,150,20,150,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelNewPassword,"Mot de passe :",Pos.CENTER,30,200,20,200,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelNewUserName,"Pseudonyme :",Pos.CENTER,30,250,20,200,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setTextField(textNewEmail,"",Pos.CENTER,250,150,20,200,new Font(15),false,anchorPane);
        setupScene.setTextField(textNewPassword,"",Pos.CENTER,250,200,20,200,new Font(15),false,anchorPane);
        setupScene.setTextField(textNewUserName,"",Pos.CENTER,250,250,20,200,new Font(15),false,anchorPane);
        setupScene.setLabel(labelError,"Erreur : ",Pos.CENTER,50,330,20,400,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setCircle(circleSetting,18,475,30,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

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
                ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser()+" = '" + textEmail.getText() + "' and "+ databaseName.getTableUserColumnPassword() +" = '" + textPassword.getText() + "'");
                if (resultSet.next()) {
                    labelTitle.setVisible(false);
                    labelEmail.setVisible(false);
                    textEmail.setVisible(false);
                    labelPassword.setVisible(false);
                    textPassword.setVisible(false);
                    buttonLogin.setVisible(false);
                    buttonNewAccount.setVisible(false);
                    settingMenuController.exitSettingMenu();

                    user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), database);
                    switchMainMenu();
                }
                else {
                    messageInterface.setMessage(labelError,"L'email ou le mot de passe est incorrect",Color.RED);
                }
            }
            catch (Exception e){}

        } else {
            messageInterface.setMessage(labelError,"Un ou plusieurs champs sont vides",Color.RED);
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
        settingMenuController.exitSettingMenu();

        labelTitle.setText("Nouveau Compte");

        labelNewMail.setVisible(true);
        labelNewPassword.setVisible(true);
        labelNewUserName.setVisible(true);
        textNewEmail.setVisible(true);
        textNewPassword.setVisible(true);
        textNewUserName.setVisible(true);
        buttonInscription.setVisible(true);
        buttonLoginMenuReturn.setVisible(true);
    }

    /** Méthode pour créer un nouveau compte **/
    private void newAccountGoToMainMenu(){
        if (!textNewEmail.getText().isEmpty() && !textNewPassword.getText().isEmpty() && !textNewUserName.getText().isEmpty()) {
            labelNewMail.setVisible(false);
            labelNewPassword.setVisible(false);
            labelNewUserName.setVisible(false);
            textNewEmail.setVisible(false);
            textNewPassword.setVisible(false);
            textNewUserName.setVisible(false);
            buttonInscription.setVisible(false);
            buttonLoginMenuReturn.setVisible(false);
            settingMenuController.exitSettingMenu();

            database.insert(databaseName.getTableUser(),"\""+ textNewUserName.getText()+"\", \""+textEmail.getText()+"\",\""+textNewPassword.getText()+"\",\"USER\",100,0",databaseName.getTableUserColumnUserName()+","+databaseName.getTableUserColumnMailUser()+","+databaseName.getTableUserColumnPassword()+","+databaseName.getTableUserColumnMoney()+","+databaseName.getTableUserColumnToken());
            setUser(new User(textNewUserName.getText(),textNewEmail.getText(),"USER",0,100,database));
            switchMainMenu();
        } else {
            messageInterface.setMessage(labelError,"Un ou plusieurs champs sont vides", Color.RED);
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

        labelTitle.setText("Bienvenue au casino");

        labelNewMail.setVisible(false);
        labelNewPassword.setVisible(false);
        labelNewUserName.setVisible(false);
        textNewEmail.setVisible(false);
        textNewPassword.setVisible(false);
        textNewUserName.setVisible(false);
        buttonInscription.setVisible(false);
        buttonLoginMenuReturn.setVisible(false);
        settingMenuController.exitSettingMenu();
    }

    private void setUser(User user){
        this.user = user;
    }

    /** Méthode pour charger le menu principal **/
    private void switchMainMenu(){
        MainMenuController mainMenuController = new MainMenuController(stage,user, database, soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume <= 1.0 && newSoundVolume >= 0){
            soundVolume = newSoundVolume;
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
