package sample;

import games.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.BlackJackMenuController;
import sample.RouletteMenuController;
import sample.SetupScene;
import sample.SlotMachineMenuController;

public class MainMenuController {

    private BorderPane root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setUpScene = new SetupScene();
    private User user;

    private Button logoutButton = new Button();
    private Button informationMenuButton = new Button();
    private Button shopButton = new Button();
    private Button blackJackMenuButton = new Button();
    private Button slotMachineMenuButton = new Button();
    private Button rouletteMenuButton = new Button();

    public MainMenuController(Stage stage,User user){
        this.stage = stage;
        this.user = user;
    }

    public void setting(){
        stage.setTitle("Menu principale");
        scene = new Scene(root,800,800);
        stage.setScene(scene);

        setUpScene.setButton(logoutButton,"Déconnexion", Pos.CENTER,20,20,10,200,new Font(10),true,anchorPane);
        setUpScene.setButton(informationMenuButton,"Informations",Pos.CENTER,20,80,10,200,new Font(10),true,anchorPane);
        setUpScene.setButton(shopButton,"Boutique",Pos.CENTER,20,140,10,200,new Font(10),true,anchorPane);
        setUpScene.setButton(blackJackMenuButton,"Black Jack",Pos.CENTER,20,200,10,200,new Font(10),true,anchorPane);
        setUpScene.setButton(slotMachineMenuButton,"Machine à sous",Pos.CENTER,20,240,10,200,new Font(10),true,anchorPane);
        setUpScene.setButton(rouletteMenuButton,"Roulette",Pos.CENTER,20,280,10,200,new Font(10),true,anchorPane);

        logoutButton.setOnMouseClicked((event)->{
            goToConnexionMenu();
        });

        informationMenuButton.setOnMouseClicked((event)->{
            goToInformationMenu();
        });

        shopButton.setOnMouseClicked((event)->{
            goToShopMenu();
        });

        blackJackMenuButton.setOnMouseClicked((event)->{
            goToBlackJackMenu();
        });

        slotMachineMenuButton.setOnMouseClicked((event)->{
            goToSlotMachineMenu();
        });

        rouletteMenuButton.setOnMouseClicked((event)->{
            goToRouletteMenu();
        });

        root.getChildren().add(anchorPane);
        stage.show();
    }

    public void goToConnexionMenu(){
        //loadingInterface.loading("connexionMenuSample.fxml","",500,500,new ConnexionMenuController(stage),mouseEvent);
    }

    public void goToInformationMenu(){
        //loadingInterface.loading("informationMenuSample.fxml","",500,500,new InformationMenuController(user,stage),mouseEvent);
    }

    public void goToShopMenu() {
    }

    public void goToBlackJackMenu(){
        BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage);
        blackJackMenuController.setting();
    }

    public void goToSlotMachineMenu(){
        SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage);
        slotMachineMenuController.setting();
    }

    public void goToRouletteMenu(){
        RouletteMenuController rouletteMenuController = new RouletteMenuController(user,stage);
        rouletteMenuController.setting();
    }
}