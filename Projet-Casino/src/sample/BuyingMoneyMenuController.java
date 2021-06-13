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
    private Language language;
    private Scene scene;

    private final Label titleLabel = new Label();
    private final Label userNameLabel = new Label();
    private final Label moneyLabel = new Label();
    private final Label errorLabel = new Label();
    private final Label labelTitleMoney;

    private final TextField textMoney = new TextField();

    private final Button validButton = new Button();

    private double sizeX;
    private double sizeY;

    public BuyingMoneyMenuController(User user, Language language, Label labelTitleMoney){
        this.user = user;
        this.language = language;
        this.labelTitleMoney = labelTitleMoney;
    }

    /**
     * Méthode qui initialise l'interface
     **/
    public void setting(){
        stage = new Stage();
        stage.setResizable(false);
        scene = new Scene(root, 400, 400);
        stage.setScene(scene);

        setupScene.setLabel(titleLabel,language.getLine("shopMoneyTitleLabel"), Pos.CENTER,0,20,20,400,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(userNameLabel,language.getLine("userNameLabel") + " " + user.getUserName(),Pos.CENTER,20,150,20,150,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(moneyLabel,language.getLine("moneyLabel") + " " + user.getMoney(),Pos.CENTER,200,150,20,150,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(errorLabel,"",Pos.CENTER_LEFT,100,300,20,300,new Font(15),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setTextField(textMoney,"",Pos.CENTER,100,200,20,200,new Font(15),true,anchorPane);

        setupScene.setButton(validButton,language.getLine("addButton"),Pos.CENTER,150,250,20,100,new Font(15),true,anchorPane);

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
                    moneyLabel.setText(language.getLine("moneyLabel") + ": " + user.getMoney());
                    labelTitleMoney.setText(language.getLine("shopTokenTitleMoney") + ": " + user.getMoney());
                    textMoney.setText("");
                    messageInterface.setMessage(errorLabel,value + " " + language.getLine("buyingMoneyErrorLabelMoneyAdd"),Color.GREEN);
                }
                else {
                    messageInterface.setMessage(errorLabel,language.getLine("buyingMoneyErrorLabelWrongValue"),Color.RED);
                }
            } catch (Exception e) {
                System.out.println("Erreur type de addMoney dans BuyingMoneyMenuController");
            }
        }
        else {
            messageInterface.setMessage(errorLabel,language.getLine("buyingMoneyErrorLabelFieldEmpty"),Color.RED);
        }
    }

    public void setLanguage(Language language){ this.language = language; }

    public void refresh(){
        titleLabel.setText(language.getLine("shopMoneyTitleLabel"));
        userNameLabel.setText(language.getLine("userNameLabel") + " " + user.getUserName());
        moneyLabel.setText(language.getLine("moneyLabel") + " " + user.getMoney());
        validButton.setText(language.getLine("addButton"));

        refreshPosition();
    }

    private void refreshPosition(){
        scene.setRoot(new BorderPane());
        scene = new Scene(root, sizeX * 400, sizeY * 400);
        stage.setScene(scene);

        titleLabel.setLayoutY(sizeY * 20);
        titleLabel.setPrefHeight(sizeY * 20);
        titleLabel.setPrefWidth(sizeX * 400);
        titleLabel.setFont(new Font(sizeX * 30));

        userNameLabel.setLayoutX(sizeX * 20);
        userNameLabel.setLayoutY(sizeY * 150);
        userNameLabel.setPrefHeight(sizeY * 20);
        userNameLabel.setPrefWidth(sizeX * 150);
        userNameLabel.setFont(new Font(sizeX * 15));

        moneyLabel.setLayoutX(sizeX * 200);
        moneyLabel.setLayoutY(sizeY * 150);
        moneyLabel.setPrefHeight(sizeY * 20);
        moneyLabel.setPrefWidth(sizeX * 150);
        moneyLabel.setFont(new Font(sizeX * 15));

        errorLabel.setLayoutX(sizeX * 100);
        errorLabel.setLayoutY(sizeY * 300);
        errorLabel.setPrefHeight(sizeY * 20);
        errorLabel.setPrefWidth(sizeX * 300);
        errorLabel.setFont(new Font(sizeX * 15));

        textMoney.setLayoutX(sizeX * 100);
        textMoney.setLayoutY(sizeY * 200);
        textMoney.setPrefHeight(sizeY * 20);
        textMoney.setPrefWidth(sizeX * 200);
        textMoney.setFont(new Font(sizeX * 15));

        validButton.setLayoutX(sizeX * 150);
        validButton.setLayoutY(sizeY * 250);
        validButton.setPrefHeight(sizeY * 20);
        validButton.setPrefWidth(sizeX * 100);
        validButton.setFont(new Font(sizeX * 15));
    }

    public void exitBuyingMenu(){
        if(stage != null){
            stage.close();
        }
    }
}
