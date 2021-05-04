package sample;

import games.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class ConnexionMenuController {

    private BorderPane root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setUpScene = new SetupScene();
    private User user;

    private Label labelWelcomeTitle = new Label();
    private Label labelEmail = new Label();
    private Label labelPassword = new Label();
    private Label labelNewMail = new Label();
    private Label labelNewPassword = new Label();
    private Label labelNewPseudo = new Label();
    private Label labelError = new Label();

    private TextField textEmail = new TextField();
    private TextField textPassword = new TextField();
    private TextField textNewEmail = new TextField();
    private TextField textNewPassword = new TextField();
    private TextField textNewPseudo = new TextField();

    private Button buttonLogin = new Button();
    private Button buttonNewAccount = new Button();
    private Button buttonInscription = new Button();
    private Button buttonLoginMenuReturn = new Button();

    public ConnexionMenuController(Stage stage){
        this.stage = stage;
    }

    /** Méthode qui initialise l'interface de connexion **/
    public void setting(){
        stage.setTitle("Menu de connexion");
        scene = new Scene(root,500,500);
        stage.setScene(scene);

        setUpScene.setLabel(labelWelcomeTitle,"Bienvenue au casino", Pos.CENTER,100,50,20,300,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelEmail,"Email",Pos.CENTER,60,200,20,150,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setLabel(labelPassword,"Mot de passe",Pos.CENTER,260,200,20,200,new Font(25),Paint.valueOf("BLACK"),true,anchorPane);
        setUpScene.setTextField(textEmail,"",Pos.CENTER,40,250,10,200,new Font(15),true,anchorPane);
        setUpScene.setTextField(textPassword,"",Pos.CENTER,270,250,10,200,new Font(15),true,anchorPane);
        setUpScene.setButton(buttonLogin,"Connexion",Pos.CENTER,10,380,20,480,new Font(25),true,anchorPane);
        setUpScene.setButton(buttonNewAccount,"Nouveau Compte",Pos.CENTER,10,440,20,480,new Font(25),true,anchorPane);
        setUpScene.setButton(buttonLoginMenuReturn,"Se connecter",Pos.CENTER,10,380,20,480,new Font(25),true,anchorPane);
        setUpScene.setButton(buttonInscription,"S'inscrire",Pos.CENTER,10,440,20,480,new Font(25),false,anchorPane);
        setUpScene.setLabel(labelNewMail,"Email :",Pos.CENTER,100,150,20,150,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setUpScene.setLabel(labelNewPassword,"Mot de passe :",Pos.CENTER,30,200,20,200,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setUpScene.setLabel(labelNewPseudo,"Pseudonyme :",Pos.CENTER,30,250,20,200,new Font(25),Paint.valueOf("BLACK"),false,anchorPane);
        setUpScene.setTextField(textNewEmail,"",Pos.CENTER,250,150,20,200,new Font(15),false,anchorPane);
        setUpScene.setTextField(textNewPassword,"",Pos.CENTER,250,200,20,200,new Font(15),false,anchorPane);
        setUpScene.setTextField(textNewPseudo,"",Pos.CENTER,250,250,20,200,new Font(15),false,anchorPane);
        setUpScene.setLabel(labelError,"Erreur : ",Pos.CENTER,50,330,20,400,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        buttonLogin.setOnMouseClicked((event)->{
            goToMainMenu();
        });

        buttonNewAccount.setOnMouseClicked((event)->{
            goToNewAccountMenu();
        });

        buttonLoginMenuReturn.setOnMouseClicked((event)->{
            goToLoginMenu();
        });

        buttonInscription.setOnMouseClicked((event)->{
            newAccountGoToMainMenu();
        });

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /** Méthode pour se connecter avec un email et un mot de passe **/
    public void goToMainMenu(){
        if (!textEmail.getText().isEmpty() && !textPassword.getText().isEmpty()) {
            labelWelcomeTitle.setVisible(false);
            labelEmail.setVisible(false);
            textEmail.setVisible(false);
            labelPassword.setVisible(false);
            textPassword.setVisible(false);
            buttonLogin.setVisible(false);
            buttonNewAccount.setVisible(false);
            labelError.setVisible(false);

            //rechercher de user dans la base de donnée
            //setUser(...) --> récupération de speudo,email, rank
            //récupération nombre jeton et argent de user

            switchMainMenu();
        } else {
            setError("Erreur : un ou plusieurs champs sont vides");
        }

    }

    /** Méthode pour changer le menu pour la création d'un nouveau compte **/
    public void goToNewAccountMenu() {
        labelEmail.setVisible(false);
        textEmail.setVisible(false);
        labelPassword.setVisible(false);
        textPassword.setVisible(false);
        buttonLogin.setVisible(false);
        buttonNewAccount.setVisible(false);

        labelWelcomeTitle.setText("Nouveau Compte");

        labelNewMail.setVisible(true);
        labelNewPassword.setVisible(true);
        labelNewPseudo.setVisible(true);
        textNewEmail.setVisible(true);
        textNewPassword.setVisible(true);
        textNewPseudo.setVisible(true);
        buttonInscription.setVisible(true);
        buttonLoginMenuReturn.setVisible(true);
    }

    /** Méthode pour créer un nouveau compte **/
    public void newAccountGoToMainMenu(){
        if (!textNewEmail.getText().isEmpty() && !textNewPassword.getText().isEmpty() && !textNewPseudo.getText().isEmpty()) {
            labelNewMail.setVisible(false);
            labelNewPassword.setVisible(false);
            labelNewPseudo.setVisible(false);
            textNewEmail.setVisible(false);
            textNewPassword.setVisible(false);
            textNewPseudo.setVisible(false);
            buttonInscription.setVisible(false);
            buttonLoginMenuReturn.setVisible(false);
            labelError.setVisible(false);

            //création d'un user dans la base de donnée
            //récupération nombre jeton et argent de user

            setUser(new User(textNewPseudo.getText(),textNewEmail.getText(),"USER"));
            switchMainMenu();
        } else {
            setError("Erreur : un ou plusieurs champs sont vides");
        }

    }

    /** Méthode pour changer le menu pour se connecter **/
    public void goToLoginMenu() {
        labelEmail.setVisible(true);
        textEmail.setVisible(true);
        labelPassword.setVisible(true);
        textPassword.setVisible(true);
        buttonLogin.setVisible(true);
        buttonNewAccount.setVisible(true);

        labelWelcomeTitle.setText("Bienvenue au casino");

        labelNewMail.setVisible(false);
        labelNewPassword.setVisible(false);
        labelNewPseudo.setVisible(false);
        textNewEmail.setVisible(false);
        textNewPassword.setVisible(false);
        textNewPseudo.setVisible(false);
        buttonInscription.setVisible(false);
        buttonLoginMenuReturn.setVisible(false);
    }

    /** Méthode pour changer le texte de l'erreur **/
    public void setError(String errorName) {
        labelError.setText(errorName);
        labelError.setVisible(true);
    }

    public void setUser(User user){
        this.user = user;
    }

    /** Méthode pour charger le menu principal **/
    public void switchMainMenu(){
        user = new User("Loic","loic-fery@orange.fr","ADMIN"); //pour tester
        user.addToken(50); // pour tester

        MainMenuController mainMenuController = new MainMenuController(stage,user);
        mainMenuController.setting();
    }
}
