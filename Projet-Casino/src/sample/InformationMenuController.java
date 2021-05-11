package sample;

import games.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;


public class InformationMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;

    private double soundVolume;
    private boolean backgroundAnimation;

    private final Label labelTitle = new Label();
    private final Label labelPseudo = new Label();
    private final Label labelEmail = new Label();
    private final Label labelToken = new Label();
    private final Label labelMoney = new Label();
    private final Label labelError = new Label();
    private final Label labelPassword = new Label();

    private final Circle circleSetting = new Circle();

    private final Button returnMainMenuButton = new Button();
    private final Button changeEmailButton = new Button();
    private final Button changePseudonymButton = new Button();
    private final Button changePasswordButton = new Button();

    private final TextField textEmail = new TextField();
    private final TextField textPseudonym = new TextField();
    private final TextField textPassword = new TextField();

    public InformationMenuController(User user, Stage stage, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);
    }

    /**
     * Méthode qui charge le menu d'informations
     */
    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 500, 500);
        //scene.getStylesheets().add(getClass().getResource("connexionMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(labelTitle,"Informations", Pos.CENTER,0,20,20,500,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelEmail,"Email :",Pos.CENTER_LEFT,20,120,20,300,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPseudo,"Pseudonyme :",Pos.CENTER_LEFT,20,180,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelPassword,"Mot de passe :",Pos.CENTER_LEFT,20,240,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelToken,"Jetons : "+user.getNumberOfToken(),Pos.CENTER_LEFT,20,300,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelMoney,"Argents : "+user.getAmountOfMoney(),Pos.CENTER_LEFT,20,360,20,500,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(labelError,"Erreur :",Pos.CENTER,140,400,20,300,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(textEmail,"",Pos.CENTER_LEFT,90,120,20,230,new Font(15),true,anchorPane);
        textEmail.setText(user.getEmail());
        setupScene.setTextField(textPseudonym,"",Pos.CENTER_LEFT,150,180,20,140,new Font(15),true,anchorPane);
        textPseudonym.setText(user.getPseudo());
        setupScene.setTextField(textPassword,"",Pos.CENTER_LEFT,160,240,20,130,new Font(15),true,anchorPane);
        textPassword.setText("password"); //recup password dans BD

        setupScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,20,440,20,100,new Font(15),true,anchorPane);
        setupScene.setButton(changeEmailButton,"Modifier email",Pos.CENTER,330,120,20,150,new Font(15),true,anchorPane);
        setupScene.setButton(changePseudonymButton,"Modifier pseudonyme",Pos.CENTER,300,180,20,180,new Font(15),true,anchorPane);
        setupScene.setButton(changePasswordButton,"Modifier mot de passe",Pos.CENTER,300,240,20,180,new Font(15),true,anchorPane);

        setupScene.setCircle(circleSetting,18,475,30,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())),Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        returnMainMenuButton.setOnMouseClicked((event)-> goToMainMenu());
        changePseudonymButton.setOnMouseClicked((event)-> changePseudonym());
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
      MainMenuController mainMenuController = new MainMenuController(stage,user,soundVolume,backgroundAnimation);
      mainMenuController.setting();
    }

    /**
     * Mméthode qui modifie l'email de l'utilisateur
     */
    private void changeEmail(){
        if(!textEmail.getText().isEmpty()){
            if(ControleSaisie.validEmail(textEmail.getText())) { //vérification email pas déjà pris
                showStatusChanged("L'email a été modifié", Color.GREEN);
            }
            else {
                showStatusChanged("L'email ne correspond pas au format",Color.RED);
            }
        }
        else {
            showStatusChanged("Le champ ne peux pas être vide",Color.RED);
        }
    }

    /**
     * Méthode qui modifie le pseudonyme de l'utilisateur
     */
    private void changePseudonym(){
        if(!textPseudonym.getText().isEmpty()){
            if(ControleSaisie.isUsername(textPseudonym.getText()) && textPseudonym.getText().length() > 5) {
                showStatusChanged("Le pseudonyme a été modifié", Color.GREEN);
            }
            else {
                showStatusChanged("Le pseudonyme ne correspond pas au format",Color.RED);
            }
        }
        else {
            showStatusChanged("Le champ ne peux pas être vide",Color.RED);
        }
    }

    /**
     * Méthode qui modifie le mot de passe de l'utilisateur
     */
    private void changePassword(){
        if(!textPassword.getText().isEmpty()){
            if(ControleSaisie.validPassword(textPassword.getText()) && textPassword.getText().length() > 5){
                showStatusChanged("Le mot de passe a été modifié", Color.GREEN);
            }
            else {
                showStatusChanged("Le mot de passe ne correspond pas au format",Color.RED);
            }
        }
        else {
            showStatusChanged("Le champ ne peux pas être vide",Color.RED);
        }
    }

    /**
     * Méthode qui affiche l'erreur quand il y en a une
     */
    private void showStatusChanged(String message, Color colorMessage) {
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelError.setTextFill(colorMessage)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelError.setText(message)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint,e ->  labelError.setVisible(true)));
        timePoint = timePoint.add(Duration.seconds(3));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> labelError.setVisible(false)));

        timeline.play();
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
}
