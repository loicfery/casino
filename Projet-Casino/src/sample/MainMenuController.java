package sample;

import games.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;;


public class MainMenuController {

    private BorderPane root = new BorderPane();
    private Scene scene;
    private Stage stage;
    private AnchorPane anchorPane = new AnchorPane();
    private SetupScene setupScene = new SetupScene();
    private User user;

    private Button logoutButton = new Button();
    private Button informationMenuButton = new Button();
    private Button shopButton = new Button();

    private ImageView pictureBlackJackMenu = new ImageView();
    private ImageView pictureSlotMachine = new ImageView();
    private ImageView pictureRoulette = new ImageView();
    private ImageView pictureShop = new ImageView();


    public MainMenuController(Stage stage,User user){
        this.stage = stage;
        this.user = user;
    }

    public void setting(){
        stage.setTitle("Menu principale");
        scene = new Scene(root,800,800);
        scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setImageView(pictureBlackJackMenu,20.0,170.0,300.0,370.0,new Image(getClass().getResource("image/blackjack.png").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureSlotMachine,410.0,490.0,290.0,370.0,new Image(getClass().getResource("image/slot_machine.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureRoulette,20.0,490.0,290.0,370.0,new Image(getClass().getResource("image/roulette2.jpg").toExternalForm()),true,anchorPane);
        setupScene.setImageView(pictureShop,380,20,100,150,new Image(getClass().getResource("image/shop.jpg").toExternalForm()),true,anchorPane);

        setupScene.setButton(logoutButton,"Déconnexion", Pos.CENTER,20,20,100,150,new Font(10),true,anchorPane);
        setupScene.setButton(informationMenuButton,"Informations",Pos.CENTER,200,20,100,150,new Font(10),true,anchorPane);


        logoutButton.setOnMouseClicked((event)->{
            goToConnexionMenu();
        });

        informationMenuButton.setOnMouseClicked((event)->{
            goToInformationMenu();
        });

        pictureShop.setOnMouseClicked((event)->{
            goToShopMenu();
        });

        pictureBlackJackMenu.setOnMouseClicked((event)->{
            goToBlackJackMenu();
        });

        pictureSlotMachine.setOnMouseClicked((event)->{
            goToSlotMachineMenu();
        });

        pictureRoulette.setOnMouseClicked((event)->{
            goToRouletteMenu();
        });

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void goToConnexionMenu(){
        ConnexionMenuController controller = new ConnexionMenuController(stage);
        controller.setting();
    }

    private void goToInformationMenu(){
        //loadingInterface.loading("informationMenuSample.fxml","",500,500,new InformationMenuController(user,stage),mouseEvent);
    }

    private void goToShopMenu() {
    }

    private void goToBlackJackMenu(){
        BlackJackMenuController blackJackMenuController = new BlackJackMenuController(user,stage);
        blackJackMenuController.setting();
    }

    private void goToSlotMachineMenu(){
        SlotMachineMenuController slotMachineMenuController = new SlotMachineMenuController(user,stage);
        slotMachineMenuController.setting();
    }

    private void goToRouletteMenu(){
        RouletteMenuController rouletteMenuController = new RouletteMenuController(user,stage);
        rouletteMenuController.setting();
    }
}