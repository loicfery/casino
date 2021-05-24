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

import java.io.File;
import java.sql.ResultSet;


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

    public ConnexionMenuController(Stage stage, Database database, Language language){
        this.stage = stage;
        this.soundVolume = 0.5;
        this.backgroundAnimation = true;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        this.database = database;
        this.language = language;
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
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("connexionMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(labelTitle,language.getConnexionMenuControllerLabelTitle1(), Pos.CENTER,0,50,20,500,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelEmail,language.getConnexionMenuControllerLabelEmail(),Pos.CENTER,60,200,20,150,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPassword,language.getConnexionMenuControllerLabelPassword(),Pos.CENTER,260,200,20,200,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextField(textEmail,"",Pos.CENTER,40,250,10,200,new Font(15),true,anchorPane);
        setupScene.setTextField(textPassword,"",Pos.CENTER,270,250,10,200,new Font(15),true,anchorPane);
        setupScene.setButton(buttonLogin,language.getConnexionMenuControllerButtonLogin(),Pos.CENTER,10,380,20,480,new Font(25),true,anchorPane);
        setupScene.setButton(buttonNewAccount,language.getConnexionMenuControllerButtonNewAccount(),Pos.CENTER,10,440,20,480,new Font(25),true,anchorPane);
        setupScene.setButton(buttonLoginMenuReturn,language.getConnexionMenuControllerButtonLoginMenuReturn(),Pos.CENTER,10,380,20,480,new Font(25),false,anchorPane);
        setupScene.setButton(buttonInscription,language.getConnexionMenuControllerButtonInscription(),Pos.CENTER,10,440,20,480,new Font(25),false,anchorPane);
        setupScene.setLabel(labelNewMail,language.getLabelMail(),Pos.CENTER,100,150,20,150,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelNewPassword,language.getLabelPassword(),Pos.CENTER,30,200,20,200,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setLabel(labelNewUserName,language.getConnexionMenuControllerLabelNewUserName(),Pos.CENTER,30,250,20,200,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setupScene.setTextField(textNewEmail,"",Pos.CENTER,250,150,20,200,new Font(15),false,anchorPane);
        setupScene.setTextField(textNewPassword,"",Pos.CENTER,250,200,20,200,new Font(15),false,anchorPane);
        setupScene.setTextField(textNewUserName,"",Pos.CENTER,250,250,20,200,new Font(15),false,anchorPane);
        setupScene.setLabel(labelError,"",Pos.CENTER,50,330,20,400,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setCircle(circleSetting,18,475,30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

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

                    user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getInt(7), resultSet.getInt(6), database);
                    switchMainMenu();
                }
                else {
                    messageInterface.setMessage(labelError,language.getConnexionMenuControllerLabelErrorConnexion(),Color.RED);
                }
            }
            catch (Exception e){}

        } else {
            messageInterface.setMessage(labelError,language.getLabelErrorEmptyField(),Color.RED);
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

        labelTitle.setText(language.getConnexionMenuControllerLabelTitle2());

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
                ResultSet resultSet = database.select(databaseName.getTableUser(),databaseName.getTableUserColumnMailUser()+" = \""+textNewEmail.getText()+"\"");

                if(!resultSet.next()) {
                    labelNewMail.setVisible(false);
                    labelNewPassword.setVisible(false);
                    labelNewUserName.setVisible(false);
                    textNewEmail.setVisible(false);
                    textNewPassword.setVisible(false);
                    textNewUserName.setVisible(false);
                    buttonInscription.setVisible(false);
                    buttonLoginMenuReturn.setVisible(false);
                    settingMenuController.exitSettingMenu();

                    database.insert(databaseName.getTableUser(), "\"" + textNewUserName.getText() + "\", \"" + textNewEmail.getText() + "\",\"" + textNewPassword.getText() + "\",\"USER\",100,0", databaseName.getTableUserColumnUserName() + "," + databaseName.getTableUserColumnMailUser() + "," + databaseName.getTableUserColumnPassword() + "," + databaseName.getTableUserColumnRank() + "," + databaseName.getTableUserColumnMoney() + "," + databaseName.getTableUserColumnToken());
                    setUser(new User(textNewUserName.getText(), textNewEmail.getText(), "USER", 100, 0, database));
                    switchMainMenu();
                }
                else {
                    messageInterface.setMessage(labelError,language.getLabelErrorMailAlreadyUsed(), Color.RED);
                }
            }
            catch (Exception e){}
        } else {
            messageInterface.setMessage(labelError,language.getLabelErrorEmptyField(), Color.RED);
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

        labelTitle.setText(language.getConnexionMenuControllerLabelTitle1());

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

    /** Méthode qui charge le menu principal **/
    private void switchMainMenu(){
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,language, soundVolume,backgroundAnimation);
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

    /** Méthode qui rafraichit ce menu **/
    public void refresh(){
        setting();
        settingMenuController.exitSettingMenu();
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        settingMenuController.setting();
    }
}
