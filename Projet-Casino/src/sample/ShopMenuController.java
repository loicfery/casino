package sample;

import games.Database;
import games.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;
    private final Database database;

    private List<String> listOfShopToken = new ArrayList<>();
    private List<String> listOfShopMoney = new ArrayList<>();
    private List<Button> listOfButtonShopToken = new ArrayList<>();
    private List<Button> listOfButtonShopMoney = new ArrayList<>();
    private List<Label> listOfLabelShopToken = new ArrayList<>();
    private List<Label> listoflabelShopMoney = new ArrayList<>();

    private final Circle circleSetting = new Circle();

    private final Button returnMainMenuButton = new Button();
    private final Button historyShoppingButton = new Button();

    private final Label titleShopTokenLabel = new Label();
    private final Label titleShopMoneyLabel = new Label();
    private final Label errorLabel = new Label();

    private final Line middleLine = new Line();

    private double soundVolume;
    private boolean backgroundAnimation;
    private int XToken = 250;
    private int XMoney = 650;
    private int Y = 285;
    private int indexPrint = 0;

    /** Base de données **/
    private final String tableUser = "utilisateur";
    private final String columnUserMail = "MailUser";
    private final String columnUserMoney = "Money";
    private final String tableExchangeToken = "lotsjetons";
    private final String tableExchangeMoney = "lotechanges";
    private final String tableHistoryExchangeMoney = "historiqueachat";
    private final String tableHistoryExchangeToken = "historiqueechange";

    public ShopMenuController(Stage stage,User user, Database database, double soundVolume, boolean backgroundAnimation){
        this.stage = stage;
        this.user = user;
        this.database = database;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this, soundVolume,backgroundAnimation);
    }

    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("shopMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(titleShopTokenLabel,"Echange de jeton : "+user.getToken(),Pos.CENTER,0,200,20,400,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(titleShopMoneyLabel,"Echange d'argent : "+user.getMoney(),Pos.CENTER,400,200,20,400,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(errorLabel,"",Pos.CENTER,250,750,20,300,new Font(20),Paint.valueOf("RED"),false,anchorPane);

        setupScene.setButton(historyShoppingButton,"Historique achats", Pos.CENTER,150,14,60,200,new Font(20),true,anchorPane);
        setupScene.setButton(returnMainMenuButton,"Quitter",Pos.CENTER,14.0,14.0,60,123.0,new Font(20.0),true,anchorPane);

        setupScene.setLine(middleLine,400,200,0,0,0,520,Paint.valueOf("BLACK"),1.0,true,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("WHITE"), StrokeType.INSIDE,1.0,true,anchorPane);

        historyShoppingButton.setOnMouseClicked((event) -> goToHistoryShoppingMenu());
        returnMainMenuButton.setOnMouseClicked((event)-> returnMainMenu());

        getShopList();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void getShopList(){
        try {
            ResultSet resultSet = database.select(tableExchangeToken, "");
            while (resultSet.next()) {
                listOfShopMoney.add(resultSet.getInt(1)+" $ ---> "+resultSet.getInt(2)+" jetons");
            }

            resultSet = database.select(tableExchangeMoney,"");
            while (resultSet.next()){
                listOfShopToken.add(resultSet.getInt(1)+" jetons ---> "+resultSet.getInt(2)+" $");
            }

            setupShopList();
            printShopInformation();
        }
        catch (Exception e){}
    }

    private void setupShopList(){
        int positionY = Y;
        for(String line : listOfShopToken){
            Label label = new Label();
            setupScene.setLabel(label,line,Pos.CENTER_LEFT,14,positionY,20,200,new Font(20),Paint.valueOf("BLACK"),false,anchorPane);
            listOfLabelShopToken.add(label);

            Button button = new Button();
            setupScene.setButton(button,"Echanger",Pos.CENTER,XToken,positionY,20,100,new Font(15),false,anchorPane);
            button.setOnMouseClicked((event)-> exchange(line));
            listOfButtonShopToken.add(button);

            positionY += 100;
        }

        positionY = Y;
        for(String line : listOfShopMoney){
            Label label = new Label();
            setupScene.setLabel(label,line,Pos.CENTER_LEFT,414,positionY,20,200,new Font(20),Paint.valueOf("BLACK"),false,anchorPane);
            listoflabelShopMoney.add(label);

            Button button = new Button();
            setupScene.setButton(button,"Echanger",Pos.CENTER,XMoney,positionY,20,100,new Font(15),false,anchorPane);
            button.setOnMouseClicked((event) -> exchange(line));
            listOfButtonShopMoney.add(button);

            positionY += 100;
        }
    }

    private void printShopInformation(){
        int indexMax = (indexPrint + 5);
        if(indexMax < listOfButtonShopMoney.size()){
            indexMax = listOfButtonShopMoney.size();
        }

        for(int index = indexPrint; index < indexMax; index ++){
            listoflabelShopMoney.get(index).setVisible(true);
            listOfLabelShopToken.get(index).setVisible(true);
            listOfButtonShopToken.get(index).setVisible(true);
            listOfButtonShopMoney.get(index).setVisible(true);
        }

        indexPrint += 5;
    }

    private void exchange(String line){
        String[] lines = line.split(" ");
        int money;
        int token;

        if(lines[1].equals("$")){
            System.out.println("$");
            try {
                money = Integer.parseInt(lines[0]);
                token = Integer.parseInt(lines[3]);

                if(user.getMoney() >= money){
                    user.removeMoney(money);
                    user.addToken(token);
                    database.insert(tableHistoryExchangeMoney,money+","+token);
                    titleShopMoneyLabel.setText("Echange d'argent : "+user.getMoney());
                    titleShopTokenLabel.setText("Echange de jeton : "+user.getToken());
                    setMessage("Votre échange a bien été effectué",Color.GREEN);
                }
                else {
                    setMessage("Vous n'avez pas assez d'argent",Color.RED);
                }
            }
            catch (Exception e){ System.out.println("Problème dans exchange de shopMenuController : argent"); }
        }
        if(lines[1].equals("jetons")){
            try{
                money = Integer.parseInt(lines[0]);
                token = Integer.parseInt(lines[3]);

                if(user.getToken() >= token){
                    user.addMoney(money);
                    user.removeToken(token);
                    database.insert(tableHistoryExchangeToken,token+","+money);
                    titleShopTokenLabel.setText("Echange de jeton : "+user.getToken());
                    titleShopMoneyLabel.setText("Echange d'argent : "+user.getMoney());
                    setMessage("Votre échange a bien été effectué",Color.GREEN);
                }
                else {
                    setMessage("Vous n'avez pas assez de jeton",Color.RED);
                }
            }
            catch (Exception e){ System.out.println("Problème dans exchange de shopMenuController token"); }
        }
    }

    private void setMessage(String message, Color color){
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setText(message)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setTextFill(color)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setVisible(true)));
        timePoint = timePoint.add(Duration.seconds(3));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> errorLabel.setVisible(false)));

        timeline.play();
    }

    private void goToHistoryShoppingMenu(){
        HistoryShoppingMenuController historyShoppingMenuController = new HistoryShoppingMenuController(user,stage,database,soundVolume,backgroundAnimation);
        historyShoppingMenuController.setting();
    }

    /**
     * Méthode pour quitter le jeu et retourner dans le menu principale
     **/
    private void returnMainMenu(){
        settingMenuController.exitSettingMenu();
        MainMenuController mainMenuController = new MainMenuController(stage,user, database,soundVolume,backgroundAnimation);
        mainMenuController.setting();
    }

    public void setSoundVolume(double newSoundVolume){
        if(newSoundVolume >= 0 && newSoundVolume <= 1.0){
            this.soundVolume = newSoundVolume;
        }
    }

    public void setBackgroundAnimation(boolean newBackgroundAnimation){
        backgroundAnimation = newBackgroundAnimation;
    }

    private void goToMenuSetting(){
        settingMenuController.exitSettingMenu();
        settingMenuController.setting();
    }
}
