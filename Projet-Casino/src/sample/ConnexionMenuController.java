package sample;

import games.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.IIOException;

public class ConnexionMenuController {
    private Stage stage;
    private User user;

    @FXML
    private Label welcomeTitleLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField textEmail;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField textPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button newAccountButton;
    @FXML
    private Label newEmailLabel;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private Label newPseudoLabel;
    @FXML
    private TextField textNewEmail;
    @FXML
    private TextField textNewPassword;
    @FXML
    private TextField textNewPseudo;
    @FXML
    private Button inscriptionButton;
    @FXML
    private Button loginButtonReturn;
    @FXML
    private Label errorLabel;

    public ConnexionMenuController(Stage stage) {
        this.stage = stage;
    }

    /** Méthode pour se connecter avec un email et un mot de passe **/
    public void clickConnexion(MouseEvent mouseEvent) throws Exception {
        if (!this.textEmail.getText().isEmpty() && !this.textPassword.getText().isEmpty()) {
            this.welcomeTitleLabel.setVisible(false);
            this.emailLabel.setVisible(false);
            this.textEmail.setVisible(false);
            this.passwordLabel.setVisible(false);
            this.textPassword.setVisible(false);
            this.loginButton.setVisible(false);
            this.newAccountButton.setVisible(false);
            this.errorLabel.setVisible(false);

            //rechercher de user dans la base de donnée
            //setUser(...) --> récupération de speudo,email, rank
            //récupération nombre jeton et argent de user

            this.switchMainMenu(mouseEvent);
        } else {
            this.setError("Erreur : un ou plusieurs champs sont vides");
        }

    }

    /** Méthode pour changer le menu pour la création d'un nouveau compte **/
    public void clickNewAccount(MouseEvent mouseEvent) {
        this.emailLabel.setVisible(false);
        this.textEmail.setVisible(false);
        this.passwordLabel.setVisible(false);
        this.textPassword.setVisible(false);
        this.loginButton.setVisible(false);
        this.newAccountButton.setVisible(false);
        this.welcomeTitleLabel.setText("Nouveau Compte");
        this.newEmailLabel.setVisible(true);
        this.newPasswordLabel.setVisible(true);
        this.newPseudoLabel.setVisible(true);
        this.textNewEmail.setVisible(true);
        this.textNewPassword.setVisible(true);
        this.textNewPseudo.setVisible(true);
        this.inscriptionButton.setVisible(true);
        this.loginButtonReturn.setVisible(true);
    }

    /** Méthode pour créer un nouveau compte **/
    public void inscription(MouseEvent mouseEvent) throws Exception {
        if (!textNewEmail.getText().isEmpty() && !textNewPassword.getText().isEmpty() && !textNewPseudo.getText().isEmpty()) {
            newEmailLabel.setVisible(false);
            newPasswordLabel.setVisible(false);
            newPseudoLabel.setVisible(false);
            textNewEmail.setVisible(false);
            textNewPassword.setVisible(false);
            textNewPseudo.setVisible(false);
            inscriptionButton.setVisible(false);
            loginButtonReturn.setVisible(false);
            errorLabel.setVisible(false);

            //création d'un user dans la base de donnée
            //récupération nombre jeton et argent de user

            setUser(new User(textNewPseudo.getText(),textNewEmail.getText(),"USER"));
            switchMainMenu(mouseEvent);
        } else {
            this.setError("Erreur : un ou plusieurs champs sont vides");
        }

    }

    /** Méthode pour changer le menu pour se connecter **/
    public void connexionReturn(MouseEvent mouseEvent) {
        this.emailLabel.setVisible(true);
        this.textEmail.setVisible(true);
        this.passwordLabel.setVisible(true);
        this.textPassword.setVisible(true);
        this.loginButton.setVisible(true);
        this.newAccountButton.setVisible(true);
        this.welcomeTitleLabel.setText("Bienvenue au casino");
        this.newEmailLabel.setVisible(false);
        this.newPasswordLabel.setVisible(false);
        this.newPseudoLabel.setVisible(false);
        this.textNewEmail.setVisible(false);
        this.textNewPassword.setVisible(false);
        this.textNewPseudo.setVisible(false);
        this.inscriptionButton.setVisible(false);
        this.loginButtonReturn.setVisible(false);
    }

    /** Méthode pour changer le texte de l'erreur **/
    public void setError(String errorName) {
        this.errorLabel.setText(errorName);
        this.errorLabel.setVisible(true);
    }

    public void setUser(User user){
        this.user = user;
    }

    /** Méthode pour charger le menu principal **/
    public void switchMainMenu(MouseEvent mouseEvent) throws Exception {
        user = new User("Loic","loic-fery@orange.fr","ADMIN"); //pour tester
        user.addToken(50); // pour tester

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("mainMenuSample.fxml"));
            loader.setControllerFactory(c -> new MainMenuController(user,stage));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800.0D, 800.0D);
            scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }
    }
}
