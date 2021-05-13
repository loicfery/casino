package sample;

import games.Bet;
import games.Database;
import games.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BuyingMoneyMenuController {

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final Database database;
    private  Stage stageBuyingMoney;

    private final Label titleLabel = new Label();
    private final Label userPseudoLabel = new Label();
    private final Label userMoneyLabel = new Label();
    private final Label errorLabel = new Label();

    private final TextField textMoney = new TextField();

    private final Button validButton = new Button();

    private double soundVolume;
    private boolean backgroundAnimation;

    /** Base de données **/
    private final String tableUser = "utilisateur";
    private final String columnMMoney = "Money";
    private final String columnMailUser = "MailUser";

    public BuyingMoneyMenuController(User user,Stage stage,Database database, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.database = database;
        this.soundVolume = soundVolume;
        this.stage = stage;
        this.backgroundAnimation = backgroundAnimation;
    }

    public void setting(){
        stageBuyingMoney = new Stage();
        //stageBuyingMoney.setResizable(false);
        Scene scene = new Scene(root, 400, 400);
        //scene.getStylesheets().add(getClass().getResource("shopMenu.css").toExternalForm());
        stageBuyingMoney.setScene(scene);

        setupScene.setLabel(titleLabel,"Ajouter de l'argent", Pos.CENTER,0,20,20,400,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(userPseudoLabel,"Pseudo : "+user.getPseudo(),Pos.CENTER,20,150,20,150,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(userMoneyLabel,"Argent : "+user.getMoney(),Pos.CENTER,200,150,20,150,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(errorLabel,"",Pos.CENTER_LEFT,100,300,20,300,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(textMoney,"",Pos.CENTER,100,200,20,200,new Font(15),true,anchorPane);

        setupScene.setButton(validButton,"Ajouter",Pos.CENTER,150,250,20,100,new Font(15),true,anchorPane);

        validButton.setOnMouseClicked((event)-> addMoney());

        root.getChildren().add(anchorPane);
        stageBuyingMoney.show();
    }

    private void addMoney(){
        if(!textMoney.getText().isEmpty()) {
            int value = 0;

            try {
                value = Integer.parseInt(textMoney.getText());
                if(value > 0) {
                    user.addMoney(value);
                    userMoneyLabel.setText("Argent : "+user.getMoney());
                    textMoney.setText("");
                    showMessage(value+" d'argent ont été ajouté",Color.GREEN);
                }
                else {
                    showMessage("Il faut une valeur strictement positive",Color.RED);
                }
            } catch (Exception e) {
                System.out.println("Erreur type de addMoney dans BuyingMoneyMenuController");
            }
        }
        else {
            showMessage("Le champ est vide",Color.RED);
        }
    }

    private void showMessage(String message, Color color){
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setText(message)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setTextFill(color)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setVisible(true)));
        timePoint = timePoint.add(Duration.seconds(3));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setVisible(false)));

        timeline.play();
    }
}
