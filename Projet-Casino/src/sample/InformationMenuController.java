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
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.sql.ResultSet;


public class InformationMenuController implements InterfaceMenu{

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
    private ChangePasswordMenuController changePasswordMenuController;
    private Scene scene;

    private double soundVolume;
    private boolean backgroundAnimation;
    private double sizeX;
    private double sizeY;

    private final Label labelTitle = new Label();
    private final Label labelUserName = new Label();
    private final Label labelEmail = new Label();
    private final Label labelToken = new Label();
    private final Label labelMoney = new Label();
    private final Label labelError = new Label();

    private final Circle circleSetting = new Circle();

    private final Button returnMainMenuButton = new Button();
    private final Button changeEmailButton = new Button();
    private final Button changeUserNameButton = new Button();
    private final Button changePasswordButton = new Button();

    private final TextField textEmail = new TextField();
    private final TextField textUserName = new TextField();

    public InformationMenuController(User user, Stage stage, Database database, Language language, double soundVolume, boolean backgroundAnimation, double sizeX, double sizeY){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation,sizeX,sizeY);
        this.database = database;
        this.language = language;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Méthode qui charge l'interface du menu d'informations
     */
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        scene = new Scene(root, sizeX * 500, sizeY * 500);
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(labelTitle,language.getLine("informationLabel"), Pos.CENTER,0,20,20,500,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelEmail,language.getLine("emailLabel"),Pos.CENTER_LEFT,20,120,20,300,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelUserName,language.getLine("userNameLabel"),Pos.CENTER_LEFT,20,180,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelToken,language.getLine("tokenLabel") + ": " + user.getToken(),Pos.CENTER_LEFT,20,240,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelMoney,language.getLine("moneyLabel") + " " + user.getMoney(),Pos.CENTER_LEFT,20,300,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelError,"",Pos.CENTER,140,360,20,300,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(textEmail,"",Pos.CENTER_LEFT,90,120,20,230,new Font(15),true,anchorPane);
        textEmail.setText(user.getEmail());
        setupScene.setTextField(textUserName,"",Pos.CENTER_LEFT,150,180,20,140,new Font(15),true,anchorPane);
        textUserName.setText(user.getUserName());

        setupScene.setButton(returnMainMenuButton,language.getLine("quitButton"),Pos.CENTER,20,440,20,100,new Font(15),true,anchorPane);
        setupScene.setButton(changeEmailButton,language.getLine("informationChangeEmailButton"),Pos.CENTER,330,120,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(changeUserNameButton,language.getLine("informationChangeUserNameButton"),Pos.CENTER,300,180,20,180,new Font(15),true,anchorPane);
        setupScene.setButton(changePasswordButton,language.getLine("informationChangePasswordButton"),Pos.CENTER,20,380,20,180,new Font(15),true,anchorPane);

        setupScene.setCircle(circleSetting,18,475,30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        returnMainMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        changeUserNameButton.setOnMouseClicked((event)-> changeUserName());
        changeEmailButton.setOnMouseClicked((event)-> changeEmail());
        changePasswordButton.setOnMouseClicked((event) -> changePassword());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /**
     * Méthode qui affiche le menu principale
     */
    private void goToMainMenu(){
        settingMenuController.exitSettingMenu();
      MainMenuController mainMenuController = new MainMenuController(stage,user, database,language,soundVolume,backgroundAnimation,sizeX,sizeY);
      mainMenuController.setting();
    }

    /**
     * Mméthode qui modifie l'email de l'utilisateur
     */
    private void changeEmail(){
        if(!textEmail.getText().isEmpty()){
            if(!textEmail.getText().equals(user.getEmail())) {
                if (InputControl.validEmail(textEmail.getText())) {
                    try {
                        ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser() + " = '" + textEmail.getText() + "'");
                        if (resultSet.next()) {
                            messageInterface.setMessage(labelError, language.getLine("ErrorLabelMailAlreadyUsed"), Color.RED);
                        } else {
                            database.update(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser(), "'" + textEmail.getText() + "'", databaseName.getTableUserColumnMailUser() + " = '" + user.getEmail() + "'");
                            user.setEmail(textEmail.getText());
                            messageInterface.setMessage(labelError, language.getLine("informationErrorLabelChangeEmailValid"), Color.GREEN);
                        }
                    } catch (Exception e) {
                    }
                } else {
                    messageInterface.setMessage(labelError, language.getLine("informationErrorLabelEmailWrongFormat"), Color.RED);
                }
            }
            else {
                messageInterface.setMessage(labelError,language.getLine("informationErrorLabelSameEmail"),Color.RED);
            }
        }
        else {
            messageInterface.setMessage(labelError,language.getLine("ErrorLabelEmptyField"),Color.RED);
        }
    }


    /**
     * Méthode qui modifie le pseudonyme de l'utilisateur
     */
    private void changeUserName(){
        if(!textUserName.getText().isEmpty()){
            if(!textUserName.getText().equals(user.getUserName())) {
                if (InputControl.isUsername(textUserName.getText()) && textUserName.getText().length() > 5) {
                    database.update(databaseName.getTableUser(), databaseName.getTableUserColumnUserName(), "'" + textUserName.getText() + "'", databaseName.getTableUserColumnMailUser() + " = '" + user.getEmail() + "'");
                    user.setUserName(textUserName.getText());
                    messageInterface.setMessage(labelError, language.getLine("informationErrorLabelChangeUserNameValid"), Color.GREEN);
                } else {
                    messageInterface.setMessage(labelError, language.getLine("informationErrorLabelUserNameWrongFormat"), Color.RED);
                }
            }
            else {
                messageInterface.setMessage(labelError,language.getLine("informationErrorLabelSameUserName"),Color.RED);
            }
        }
        else {
            messageInterface.setMessage(labelError,language.getLine("ErrorLabelEmptyField"),Color.RED);
        }
    }

    private void changePassword(){
        if(changePasswordMenuController != null) {
            changePasswordMenuController.exitChangePasswordMenu();
        }
        changePasswordMenuController = new ChangePasswordMenuController(user,database,language);
        changePasswordMenuController.setting();
    }


    /**
     * Méthode qui change le volume sonore
     */
    public void setSoundVolume(double newSoundVolume){
        soundVolume = newSoundVolume;
    }

    /**
     * Méthode qui lance ou arrête les animations en fond d'écran
     */
    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    /**
     * Méthode qui affiche le menu des paramètres
     */
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
        labelTitle.setText(language.getLine("informationLabel"));
        labelEmail.setText(language.getLine("emailLabel"));
        labelUserName.setText(language.getLine("userNameLabel"));
        labelToken.setText(language.getLine("tokenLabel") + ": " + user.getToken());
        labelMoney.setText(language.getLine("moneyLabel") + " " + user.getMoney());
        returnMainMenuButton.setText(language.getLine("quitButton"));
        changeEmailButton.setText(language.getLine("informationChangeEmailButton"));
        changeUserNameButton.setText(language.getLine("informationChangeUserNameButton"));
        changePasswordButton.setText(language.getLine("informationChangePasswordButton"));

        if(changePasswordMenuController != null){
            changePasswordMenuController.setLanguage(language);
            changePasswordMenuController.refresh();
        }

        refreshPosition();
    }

    private void refreshPosition(){
        scene.setRoot(new BorderPane());
        scene = new Scene(root, sizeX * 500, sizeY * 500);
        stage.setScene(scene);

        labelTitle.setPrefHeight(sizeY * 20);
        labelTitle.setPrefWidth(sizeX * 500);
        labelTitle.setFont(new Font(sizeX * 30));

        labelEmail.setLayoutX(sizeX * 20);
        labelEmail.setLayoutY(sizeY * 120);
        labelEmail.setPrefHeight(sizeY * 20);
        labelEmail.setPrefWidth(sizeX * 300);
        labelEmail.setFont(new Font(sizeX * 20));

        labelUserName.setLayoutX(sizeX * 20);
        labelUserName.setLayoutY(sizeY * 180);
        labelUserName.setPrefHeight(sizeY * 20);
        labelUserName.setPrefWidth(sizeX * 500);
        labelUserName.setFont(new Font(sizeX * 20));

        labelToken.setLayoutX(sizeX * 20);
        labelToken.setLayoutY(sizeY * 240);
        labelToken.setPrefHeight(sizeY * 20);
        labelToken.setPrefWidth(sizeX * 500);
        labelToken.setFont(new Font(sizeX * 20));

        labelMoney.setLayoutX(sizeX * 20);
        labelMoney.setLayoutY(sizeY * 300);
        labelMoney.setPrefHeight(sizeY * 20);
        labelMoney.setPrefWidth(sizeX * 500);
        labelMoney.setFont(new Font(sizeX * 20));

        labelError.setLayoutX(sizeX * 140);
        labelError.setLayoutY(sizeY * 360);
        labelError.setPrefHeight(sizeY * 20);
        labelError.setPrefWidth(sizeX * 300);
        labelError.setFont(new Font(sizeX * 15));

        textEmail.setLayoutX(sizeX * 90);
        textEmail.setLayoutY(sizeY * 120);
        textEmail.setPrefHeight(sizeY * 20);
        textEmail.setPrefWidth(sizeX * 230);
        textEmail.setFont(new Font(sizeX * 15));

        textUserName.setLayoutX(sizeX * 150);
        textUserName.setLayoutY(sizeY * 180);
        textUserName.setPrefHeight(sizeY * 20);
        textUserName.setPrefWidth(sizeX * 140);
        textUserName.setFont(new Font(sizeX * 15));

        returnMainMenuButton.setLayoutX(sizeX * 20);
        returnMainMenuButton.setLayoutY(sizeY * 440);
        returnMainMenuButton.setPrefHeight(sizeY * 20);
        returnMainMenuButton.setPrefWidth(sizeX * 100);
        returnMainMenuButton.setFont(new Font(sizeX * 15));

        changeEmailButton.setLayoutX(sizeX * 330);
        changeEmailButton.setLayoutY(sizeY * 120);
        changeEmailButton.setPrefHeight(sizeY * 20);
        changeEmailButton.setPrefWidth(sizeX * 150);
        changeEmailButton.setFont(new Font(sizeX * 15));

        changeUserNameButton.setLayoutX(sizeX * 300);
        changeUserNameButton.setLayoutY(sizeY * 180);
        changeUserNameButton.setPrefHeight(sizeY * 20);
        changeUserNameButton.setPrefWidth(sizeX * 180);
        changeUserNameButton.setFont(new Font(sizeX * 15));

        changePasswordButton.setLayoutX(sizeX * 20);
        changePasswordButton.setLayoutY(sizeY * 380);
        changePasswordButton.setPrefHeight(sizeY * 20);
        changePasswordButton.setPrefWidth(sizeX * 180);
        changePasswordButton.setFont(new Font(sizeX * 15));

        circleSetting.setLayoutX(sizeX * 475);
        circleSetting.setLayoutY(sizeY * 30);
        circleSetting.setRadius(Math.max(sizeX,sizeY) * 18);
    }
}
