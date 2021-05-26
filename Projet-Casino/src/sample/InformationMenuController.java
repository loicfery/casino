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

    private double soundVolume;
    private boolean backgroundAnimation;

    private final Label labelTitle = new Label();
    private final Label labelUserName = new Label();
    private final Label labelEmail = new Label();
    private final Label labelToken = new Label();
    private final Label labelMoney = new Label();
    private final Label labelError = new Label();
    private final Label labelPassword = new Label();

    private final Circle circleSetting = new Circle();

    private final Button returnMainMenuButton = new Button();
    private final Button changeEmailButton = new Button();
    private final Button changeUserNameButton = new Button();
    private final Button changePasswordButton = new Button();

    private final TextField textEmail = new TextField();
    private final TextField textUserName = new TextField();
    private final TextField textPassword = new TextField();

    public InformationMenuController(User user, Stage stage, Database database, Language language, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        this.database = database;
        this.language = language;
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
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setLabel(labelTitle,language.getInformation(), Pos.CENTER,0,20,20,500,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelEmail,language.getLabelMail(),Pos.CENTER_LEFT,20,120,20,300,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelUserName,language.getLabelUserName(),Pos.CENTER_LEFT,20,180,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPassword,language.getLabelPassword(),Pos.CENTER_LEFT,20,240,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelToken,language.getLabelToken()+user.getToken(),Pos.CENTER_LEFT,20,300,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelMoney,language.getLabelMoney()+user.getMoney(),Pos.CENTER_LEFT,20,360,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelError,"",Pos.CENTER,140,400,20,300,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(textEmail,"",Pos.CENTER_LEFT,90,120,20,230,new Font(15),true,anchorPane);
        textEmail.setText(user.getEmail());
        setupScene.setTextField(textUserName,"",Pos.CENTER_LEFT,150,180,20,140,new Font(15),true,anchorPane);
        textUserName.setText(user.getUserName());
        setupScene.setTextField(textPassword,"",Pos.CENTER_LEFT,160,240,20,130,new Font(15),true,anchorPane);
        getPassword(textEmail.getText());

        setupScene.setButton(returnMainMenuButton,language.getQuitButton(),Pos.CENTER,20,440,20,100,new Font(15),true,anchorPane);
        setupScene.setButton(changeEmailButton,language.getInformationMenuControllerChangeEmailButton(),Pos.CENTER,330,120,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(changeUserNameButton,language.getInformationMenuControllerChangeUserNameButton(),Pos.CENTER,300,180,20,180,new Font(15),true,anchorPane);
        setupScene.setButton(changePasswordButton,language.getInformationMenuControllerChangePasswordButton(),Pos.CENTER,300,240,20,180,new Font(15),true,anchorPane);

        setupScene.setCircle(circleSetting,18,475,30,new ImagePattern(new Image(new File("Projet-Casino/image/pictureSetting.png").toURI().toString())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        returnMainMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        changeUserNameButton.setOnMouseClicked((event)-> changeUserName());
        changeEmailButton.setOnMouseClicked((event)-> changeEmail());
        changePasswordButton.setOnMouseClicked((event) -> changePassword());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode qui récupère le mot de passe dans la base de données **/
    private void getPassword(String email){
        try {
            ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser()+" = \"" + email + "\"");
            if(resultSet.next()) {
                textPassword.setText(resultSet.getString(4));
            }
        }
        catch (Exception e){}
    }

    /**
     * Méthode qui affiche le menu principale
     */
    private void goToMainMenu(){
        settingMenuController.exitSettingMenu();
      MainMenuController mainMenuController = new MainMenuController(stage,user, database,language,soundVolume,backgroundAnimation);
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
                        ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser() + " = \"" + textEmail.getText() + "\"");
                        if (resultSet.next()) {
                            messageInterface.setMessage(labelError, language.getLabelErrorMailAlreadyUsed(), Color.RED);
                        } else {
                            database.update(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser(), "\"" + textEmail.getText() + "\"", databaseName.getTableUserColumnMailUser() + " = \"" + user.getEmail() + "\"");
                            user.setEmail(textEmail.getText());
                            messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorEmailChangeValid(), Color.GREEN);
                        }
                    } catch (Exception e) {
                    }
                } else {
                    messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorEmailWrongFormat(), Color.RED);
                }
            }
            else {
                messageInterface.setMessage(labelError,language.getInformationMenuControllerLabelErrorEmailSame(),Color.RED);
            }
        }
        else {
            messageInterface.setMessage(labelError,language.getLabelErrorEmptyField(),Color.RED);
        }
    }


    /**
     * Méthode qui modifie le pseudonyme de l'utilisateur
     */
    private void changeUserName(){
        if(!textUserName.getText().isEmpty()){
            if(!textUserName.getText().equals(user.getUserName())) {
                if (InputControl.isUsername(textUserName.getText()) && textUserName.getText().length() > 5) {
                    database.update(databaseName.getTableUser(), databaseName.getTableUserColumnUserName(), "\"" + textUserName.getText() + "\"", databaseName.getTableUserColumnMailUser() + " = \"" + user.getEmail() + "\"");
                    user.setUserName(textUserName.getText());
                    messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorUserNameChangeValid(), Color.GREEN);
                } else {
                    messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorUserNameWrongFormat(), Color.RED);
                }
            }
            else {
                messageInterface.setMessage(labelError,language.getInformationMenuControllerLabelErrorUserNameSame(),Color.RED);
            }
        }
        else {
            messageInterface.setMessage(labelError,language.getLabelErrorEmptyField(),Color.RED);
        }
    }

    /**
     * Méthode qui modifie le mot de passe de l'utilisateur
     */
    private void changePassword(){
        try {
            if (!textPassword.getText().isEmpty()) {
                ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser() + " = \"" + user.getEmail() + "\"");
                if (!resultSet.getString(4).equals(textPassword.getText())) {
                    if (InputControl.validPassword(textPassword.getText()) && textPassword.getText().length() > 5) {
                        database.update(databaseName.getTableUser(), databaseName.getTableUserColumnPassword(), "\"" + textPassword.getText() + "\"", databaseName.getTableUserColumnMailUser() + " = \"" + user.getEmail() + "\"");
                        messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorPasswordChangeValid(), Color.GREEN);
                    } else {
                        messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorPasswordWrongFormat(), Color.RED);
                    }
                }
                else {
                    messageInterface.setMessage(labelError, language.getInformationMenuControllerLabelErrorPasswordSame(), Color.RED);
                }
            }
            else {
                messageInterface.setMessage(labelError, language.getLabelErrorEmptyField(), Color.RED);
            }
        }
        catch (Exception e){ System.out.println("Erreur : changePassword dans InformationMenuController"); }
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

    /** Méthode qui rafraichit ce menu **/
    public void refresh(){
        setting();
        settingMenuController.exitSettingMenu();
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        settingMenuController.setting();
    }
}
