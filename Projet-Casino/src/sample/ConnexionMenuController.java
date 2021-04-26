package sample;

import games.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.imageio.IIOException;

public class ConnexionMenuController {
    public Label welcomeTitleLabel;
    public Label emailLabel;
    public TextField textEmail;
    public Label passwordLabel;
    public TextField textPassword;
    public Button loginButton;
    public Button newAccountButton;
    public Label newEmailLabel;
    public Label newPasswordlabel;
    public Label newPseudoLabel;
    public TextField textNewEmail;
    public TextField textNewPassword;
    public TextField textNewPseudo;
    public Button inscriptionButton;
    public Button loginButtonReturn;
    public Label errorLabel;

    public ConnexionMenuController() {
    }

    public void clickConnexion(ActionEvent actionEvent) throws Exception {
        if (!this.textEmail.getText().isEmpty() && !this.textPassword.getText().isEmpty()) {
            this.welcomeTitleLabel.setVisible(false);
            this.emailLabel.setVisible(false);
            this.textEmail.setVisible(false);
            this.passwordLabel.setVisible(false);
            this.textPassword.setVisible(false);
            this.loginButton.setVisible(false);
            this.newAccountButton.setVisible(false);
            this.errorLabel.setVisible(false);
            this.switchGameMenu(actionEvent);
        } else {
            this.setError("Erreur : un ou plusieurs champs sont vides");
        }

    }

    public void clickNewAccount(ActionEvent actionEvent) {
        this.emailLabel.setVisible(false);
        this.textEmail.setVisible(false);
        this.passwordLabel.setVisible(false);
        this.textPassword.setVisible(false);
        this.loginButton.setVisible(false);
        this.newAccountButton.setVisible(false);
        this.welcomeTitleLabel.setText("Nouveau Compte");
        this.newEmailLabel.setVisible(true);
        this.newPasswordlabel.setVisible(true);
        this.newPseudoLabel.setVisible(true);
        this.textNewEmail.setVisible(true);
        this.textNewPassword.setVisible(true);
        this.textNewPseudo.setVisible(true);
        this.inscriptionButton.setVisible(true);
        this.loginButtonReturn.setVisible(true);
    }

    public void clickInscription(ActionEvent actionEvent) throws Exception {
        if (!this.textNewEmail.getText().isEmpty() && !this.textNewPassword.getText().isEmpty() && !this.textNewPseudo.getText().isEmpty()) {
            this.newEmailLabel.setVisible(false);
            this.newPasswordlabel.setVisible(false);
            this.newPseudoLabel.setVisible(false);
            this.textNewEmail.setVisible(false);
            this.textNewPassword.setVisible(false);
            this.textNewPseudo.setVisible(false);
            this.inscriptionButton.setVisible(false);
            this.loginButtonReturn.setVisible(false);
            this.errorLabel.setVisible(false);
            this.switchGameMenu(actionEvent);
        } else {
            this.setError("Erreur : un ou plusieurs champs sont vides");
        }

    }

    public void clickConnexionReturn(ActionEvent actionEvent) {
        this.emailLabel.setVisible(true);
        this.textEmail.setVisible(true);
        this.passwordLabel.setVisible(true);
        this.textPassword.setVisible(true);
        this.loginButton.setVisible(true);
        this.newAccountButton.setVisible(true);
        this.welcomeTitleLabel.setText("Bienvenue au casino");
        this.newEmailLabel.setVisible(false);
        this.newPasswordlabel.setVisible(false);
        this.newPseudoLabel.setVisible(false);
        this.textNewEmail.setVisible(false);
        this.textNewPassword.setVisible(false);
        this.textNewPseudo.setVisible(false);
        this.inscriptionButton.setVisible(false);
        this.loginButtonReturn.setVisible(false);
    }

    public void setError(String errorName) {
        this.errorLabel.setText(errorName);
        this.errorLabel.setVisible(true);
    }

    public void switchGameMenu(ActionEvent actionEvent) throws Exception {
        User user = new User("Loic","loic-fery@orange.fr","ADMIN"); //pour tester
        user.addToken(100); // pour tester

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("mainMenuSample.fxml"));//"connexionMenuSample.fxml"
            loader.setControllerFactory(c -> new MainMenuController(user));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800.0D, 800.0D);
            scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IIOException var7) {
            var7.printStackTrace();
        }
    }
}
