package sample;

import games.Database;
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

    private BorderPane root;
    private final Stage stage;
    private AnchorPane anchorPane;
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private SettingMenuController settingMenuController;
    private final Database database;
    private Language language;

    private final Button logoutButton = new Button();
    private final Button informationMenuButton = new Button();
    private final Button historyGamePlayedButton = new Button();

    private final ImageView pictureBlackJackMenu = new ImageView();
    private final ImageView pictureSlotMachineMenu = new ImageView();
    private final ImageView pictureRouletteMenu = new ImageView();
    private final ImageView pictureShopMenu = new ImageView();

    private final Circle circleSetting = new Circle();

    private double soundVolume;
    private boolean backgroundAnimation;


    public MainMenuController(Stage stage,User user, Database database,Language language, double soundVolume, boolean backgroundAnimation){
        this.stage = stage;
        this.user = user;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        this.database = database;
        this.language = language;
    }

    public void setting(){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        });
        root = new BorderPane();
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
        stage.setScene(scene);
        anchorPane = new AnchorPane();

        setupScene.setImageView(pictureBlackJackMenu,20.0,170.0,300.0,370.0,new Image(getClass().getResource("image/blackjack.png").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureSlotMachineMenu,410.0,490.0,290.0,370.0,new Image(getClass().getResource("image/slot_machine.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureRouletteMenu,20.0,490.0,290.0,370.0,new Image(getClass().getResource("image/roulette2.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureShopMenu,315,20,80,120,new Image(getClass().getResource("image/shop.jpg").toExternalForm()),true,anchorPane);

        setupScene.setButton(logoutButton,language.getMainMenuControllerLogoutButton(), Pos.CENTER,20,20,80,120,new Font(15),true,anchorPane);
        setupScene.setButton(informationMenuButton,language.getInformation(),Pos.CENTER,170,20,80,120,new Font(15),true,anchorPane);
        setupScene.setButton(historyGamePlayedButton,language.getMainMenuControllerHistoryGamePlayedButton(),Pos.CENTER,470,20,80,150,new Font(15),true,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("GREEN"), StrokeType.INSIDE,1.0,true,anchorPane);

        logoutButton.setOnMouseClicked((event)-> goToConnexionMenu());
        informationMenuButton.setOnMouseClicked((event)-> goToInformationMenu());
        pictureShopMenu.setOnMouseClicked((event)-> goToShopMenu());
        pictureBlackJackMenu.setOnMouseClicked((event)-> goToBlackJackMenu());
        pictureSlotMachineMenu.setOnMouseClicked((event)-> goToSlotMachineMenu());
        pictureRouletteMenu.setOnMouseClicked((event)-> goToRouletteMenu());
        circleSetting.setOnMouseClicked((event)-> goToMenuSetting());
        historyGamePlayedButton.setOnMouseClicked((event)-> goToHistoryGamePlayedMenu());

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void goToConnexionMenu(){
        settingMenuController.exitSettingMenu();
        ConnexionMenuController controller = new ConnexionMenuController(stage,database,language);
        controller.setting();
    }

    private void goToInformationMenu(){
        settingMenuController.exitSettingMenu();
        InformationMenuController informationMenuController = new InformationMenuController(user,stage, database,language,soundVolume,backgroundAnimation);
        informationMenuController.setting();
    }

    private void goToShopMenu() {
        settingMenuController.exitSettingMenu();
        ShopMenuController shopMenuController = new ShopMenuController(stage,user,database,language,soundVolume,backgroundAnimation);
        shopMenuController.setting();
    }

    private void goToBlackJackMenu(){
        settingMenuController.exitSettingMenu();
        BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage, database,language,soundVolume,backgroundAnimation);
        blackJackMenuController.setting();
    }

    private void goToSlotMachineMenu(){
        settingMenuController.exitSettingMenu();
        SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage,database,language,soundVolume,backgroundAnimation);
        slotMachineMenuController.setting();
    }

    private void goToRouletteMenu(){
        settingMenuController.exitSettingMenu();
        RouletteMenuController rouletteMenuController = new RouletteMenuController(user,stage, database,language,soundVolume,backgroundAnimation);
        rouletteMenuController.setting();
    }

    private void goToHistoryGamePlayedMenu(){
        settingMenuController.exitSettingMenu();
        HistoryGamePlayedMenuController historyGamePlayedMenuController = new HistoryGamePlayedMenuController(user,stage,database,language,soundVolume,backgroundAnimation);
        historyGamePlayedMenuController.setting();
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

    public void setLanguage(Language language){ this.language = language; }

    public void refresh(){
        setting();
        settingMenuController.exitSettingMenu();
        settingMenuController = new SettingMenuController(this,language, soundVolume,backgroundAnimation);
        settingMenuController.setting();
    }
}