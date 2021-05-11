package sample;

import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;;


public class MainMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;

    private final Button logoutButton = new Button();
    private final Button informationMenuButton = new Button();
    private final Button historyShoppingButton = new Button();

    private final ImageView pictureBlackJackMenu = new ImageView();
    private final ImageView pictureSlotMachine = new ImageView();
    private final ImageView pictureRoulette = new ImageView();
    private final ImageView pictureShop = new ImageView();

    private final Circle circleSetting = new Circle();

    private double soundVolume;
    private boolean backgroundAnimation;


    public MainMenuController(Stage stage,User user, double soundVolume, boolean backgroundAnimation){
        this.stage = stage;
        this.user = user;
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
        scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setImageView(pictureBlackJackMenu,20.0,170.0,300.0,370.0,new Image(getClass().getResource("image/blackjack.png").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureSlotMachine,410.0,490.0,290.0,370.0,new Image(getClass().getResource("image/slot_machine.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureRoulette,20.0,490.0,290.0,370.0,new Image(getClass().getResource("image/roulette2.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureShop,490,20,80,120,new Image(getClass().getResource("image/shop.jpg").toExternalForm()),true,anchorPane);

        setupScene.setButton(logoutButton,"Déconnexion", Pos.CENTER,20,20,80,120,new Font(15),true,anchorPane);
        setupScene.setButton(informationMenuButton,"Informations",Pos.CENTER,170,20,80,120,new Font(15),true,anchorPane);
        setupScene.setButton(historyShoppingButton,"Historique achats",Pos.CENTER,320,20,80,140,new Font(15),true,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("GREEN"), StrokeType.INSIDE,1.0,true,anchorPane);

        logoutButton.setOnMouseClicked((event)-> goToConnexionMenu());
        informationMenuButton.setOnMouseClicked((event)-> goToInformationMenu());
        pictureShop.setOnMouseClicked((event)-> goToShopMenu());
        pictureBlackJackMenu.setOnMouseClicked((event)-> goToBlackJackMenu());
        pictureSlotMachine.setOnMouseClicked((event)-> goToSlotMachineMenu());
        pictureRoulette.setOnMouseClicked((event)-> goToRouletteMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        historyShoppingButton.setOnMouseClicked((event) -> goToHistoryShoppingMenu());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void goToConnexionMenu(){
        settingMenuController.exitSettingMenu();
        ConnexionMenuController controller = new ConnexionMenuController(stage);
        controller.setting();
    }

    private void goToInformationMenu(){
        settingMenuController.exitSettingMenu();
        InformationMenuController informationMenuController = new InformationMenuController(user,stage,soundVolume,backgroundAnimation);
        informationMenuController.setting();
    }

    private void goToShopMenu() {
        settingMenuController.exitSettingMenu();
    }

    private void goToBlackJackMenu(){
        settingMenuController.exitSettingMenu();
        BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage,soundVolume,backgroundAnimation);
        blackJackMenuController.setting();
    }

    private void goToSlotMachineMenu(){
        settingMenuController.exitSettingMenu();
        SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage,soundVolume,backgroundAnimation);
        slotMachineMenuController.setting();
    }

    private void goToRouletteMenu(){
        settingMenuController.exitSettingMenu();
        RouletteMenuController rouletteMenuController = new RouletteMenuController(user,stage,soundVolume,backgroundAnimation);
        rouletteMenuController.setting();
    }

    private void goToHistoryShoppingMenu(){
        HistoryShoppingMenuController historyShoppingMenuController = new HistoryShoppingMenuController(user,stage,soundVolume,backgroundAnimation);
        historyShoppingMenuController.setting();
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