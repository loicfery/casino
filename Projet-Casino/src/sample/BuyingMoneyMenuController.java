package sample;

import games.User;
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

public class BuyingMoneyMenuController {

    private final BorderPane root = new BorderPane();
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private  Stage stage;
    private final MessageInterface messageInterface = new MessageInterface();

    private final Label titleLabel = new Label();
    private final Label userNameLabel = new Label();
    private final Label userMoneyLabel = new Label();
    private final Label errorLabel = new Label();

    private final TextField textMoney = new TextField();

    private final Button validButton = new Button();

    public BuyingMoneyMenuController(User user){
        this.user = user;
    }

    /**
     * Méthode qui initialise l'interface
     **/
    public void setting(){
        stage = new Stage();
        stage.setResizable(false);
        Scene scene = new Scene(root, 400, 400);
        //scene.getStylesheets().add(getClass().getResource("shopMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(titleLabel,"Ajouter de l'argent", Pos.CENTER,0,20,20,400,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(userNameLabel,"Pseudo : "+user.getPseudo(),Pos.CENTER,20,150,20,150,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(userMoneyLabel,"Argent : "+user.getMoney(),Pos.CENTER,200,150,20,150,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(errorLabel,"",Pos.CENTER_LEFT,100,300,20,300,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(textMoney,"",Pos.CENTER,100,200,20,200,new Font(15),true,anchorPane);

        setupScene.setButton(validButton,"Ajouter",Pos.CENTER,150,250,20,100,new Font(15),true,anchorPane);

        validButton.setOnMouseClicked((event)-> addMoney());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    /**
     * Méthode qui ajoute de l'argent à l'utilisateur
     **/
    private void addMoney(){
        if(!textMoney.getText().isEmpty()) {
            int value = 0;

            try {
                value = Integer.parseInt(textMoney.getText());
                if(value > 0) {
                    user.addMoney(value);
                    userMoneyLabel.setText("Argent : "+user.getMoney());
                    textMoney.setText("");
                    messageInterface.setMessage(errorLabel,value+" d'argent ont été ajouté",Color.GREEN);
                }
                else {
                    messageInterface.setMessage(errorLabel,"Il faut une valeur strictement positive",Color.RED);
                }
            } catch (Exception e) {
                System.out.println("Erreur type de addMoney dans BuyingMoneyMenuController");
            }
        }
        else {
            messageInterface.setMessage(errorLabel,"Le champ est vide",Color.RED);
        }
    }
}
