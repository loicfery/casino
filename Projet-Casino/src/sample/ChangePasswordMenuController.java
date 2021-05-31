package sample;

import games.Database;
import games.DatabaseName;
import games.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class ChangePasswordMenuController {

    private Stage stage;
    private final SetupScene setupScene = new SetupScene();
    private Language language;
    private User user;
    private Database database;
    private final DatabaseName databaseName = new DatabaseName();
    private final MessageInterface messageInterface = new MessageInterface();

    private Label titleLabel = new Label();
    private Label oldPasswordLabel = new Label();
    private Label newPasswordLabel = new Label();
    private Label newPasswordConfirmationLabel = new Label();
    private Label errorLabel = new Label();

    private PasswordField oldPasswordText = new PasswordField();
    private PasswordField newPasswordText = new PasswordField();
    private PasswordField newPasswordConfirmationText = new PasswordField();

    private Button validButton = new Button();

    public ChangePasswordMenuController(User user, Database database, Language language){
        this.user = user;
        this.database = database;
        this.language = language;
    }

    public void setting(){
        AnchorPane anchorPane = new AnchorPane();
        BorderPane root = new BorderPane();
        stage = new Stage();
        stage.setResizable(false);
        Scene sceneSetting = new Scene(root, 400, 500);
        stage.setScene(sceneSetting);

        setupScene.setLabel(titleLabel,language.getLine("changePasswordTitleLabel"),Pos.CENTER,0,15,20,400,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(oldPasswordLabel,language.getLine("changePasswordOldPassword"), Pos.CENTER,0,100,20,400,new Font(15), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(newPasswordLabel,language.getLine("changePasswordNewPassword"),Pos.CENTER,0,200,20,400,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(newPasswordConfirmationLabel,language.getLine("changePasswordNewPasswordConfirmation"),Pos.CENTER,0,300,20,400,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(errorLabel,"",Pos.CENTER,0,400,20,400,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(oldPasswordText,"",Pos.CENTER,100,150,20,200,new Font(10),true,anchorPane);
        setupScene.setTextField(newPasswordText,"",Pos.CENTER,100,250,20,200,new Font(10),true,anchorPane);
        setupScene.setTextField(newPasswordConfirmationText,"",Pos.CENTER,100,350,20,200,new Font(10),true,anchorPane);

        setupScene.setButton(validButton,language.getLine("changePasswordValidButton"),Pos.CENTER,15,460,20,370,new Font(15),true,anchorPane);

        validButton.setOnMouseClicked((event)-> editPassword());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void editPassword(){
        if(!oldPasswordText.getText().isEmpty() && !newPasswordText.getText().isEmpty() && !newPasswordConfirmationText.getText().isEmpty()){
            try {
                ResultSet resultSet = database.select(databaseName.getTableUser(), databaseName.getTableUserColumnMailUser() + " = '" + user.getEmail() + "'," + databaseName.getTableUserColumnPassword() + " = MD5('" + oldPasswordText.getText() + "')");

                if(!resultSet.next()){
                    messageInterface.setMessage(errorLabel,language.getLine("changePasswordErrorLabelWrongPassword"),Color.RED);
                }
                else {
                    if (!newPasswordText.getText().equals(newPasswordConfirmationText.getText())) {
                        messageInterface.setMessage(errorLabel, language.getLine("changePasswordErrorLabelNotSamePassword"), Color.RED);
                    } else {
                        if (oldPasswordText.getText().equals(newPasswordText.getText())) {
                            messageInterface.setMessage(errorLabel, language.getLine("changePasswordErrorLabelSamePassword"), Color.RED);
                        }
                        else {
                            if (InputControl.validPassword(newPasswordText.getText())) {
                                database.update(databaseName.getTableUser(), databaseName.getTableUserColumnPassword(), "MD5('" + newPasswordText.getText() + "')", databaseName.getTableUserColumnMailUser() + " = '" + user.getEmail() + "'");
                                messageInterface.setMessage(errorLabel, language.getLine("changePasswordErrorLabelPasswordEdit"), Color.GREEN);
                            }
                            else {
                                messageInterface.setMessage(errorLabel, language.getLine("changePasswordErrorLabelWrongFormat"), Color.RED);
                            }
                        }
                    }
                }
            }
            catch (Exception exception){
                System.err.println("Erreur editPassword");
                exception.printStackTrace();
            }
        }
        else{
            messageInterface.setMessage(errorLabel,language.getLine("ErrorLabelEmptyField"), Color.RED);
        }
    }

    public void exitChangePasswordMenu(){
        if(stage != null) {
            stage.close();
        }
    }
}
