package sample;

import games.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;


public class HistoryShoppingMenuController implements InterfaceMenu{

    private final BorderPane root = new BorderPane();
    private final Stage stage;
    private final AnchorPane anchorPane = new AnchorPane();
    private final SetupScene setupScene = new SetupScene();
    private final User user;
    private final SettingMenuController settingMenuController;

    private final Circle circleSetting = new Circle();

    private final Label labelTitle = new Label();

    private final TextArea textInformation = new TextArea();

    private final Button leftInformationButton = new Button();
    private final Button rightInformationButton = new Button();

    private double soundVolume;
    private boolean backgroundAnimation;
    private List<String> listOfInformation = new ArrayList<>();
    private int indexInformation = 15;

    public HistoryShoppingMenuController(User user, Stage stage, double soundVolume, boolean backgroundAnimation){
        this.user = user;
        this.stage = stage;
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
        Scene scene = new Scene(root, 400, 800);
        scene.getStylesheets().add(getClass().getResource("historyShoppingMenu.css").toExternalForm());
        stage.setScene(scene);

        setupScene.setLabel(labelTitle,"Historique des achats", Pos.CENTER,0,20,20,400,new Font(30),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setTextArea(textInformation,0,100,700,400,false,true,anchorPane);

        setupScene.setButton(leftInformationButton,"<-",Pos.CENTER,100,700,20,50,new Font(15),false,anchorPane);
        setupScene.setButton(rightInformationButton,"->",Pos.CENTER,250,700,20,50,new Font(15),true,anchorPane);

        setupScene.setCircle(circleSetting,30,750,40,new ImagePattern(new Image(getClass().getResource("image/pictureSetting.png").toExternalForm())), Paint.valueOf("BLACK"), StrokeType.INSIDE,1.0,true,anchorPane);

        circleSetting.setOnMouseClicked((event) -> goToMenuSetting());
        leftInformationButton.setOnMouseClicked((event) -> leftInformation());
        rightInformationButton.setOnMouseClicked((event) -> rightInformation());

        getAllInformation();

        root.getChildren().add(anchorPane);
        stage.show();
    }

    private void getAllInformation(){
        //recup historique achat
        for(int index = 0; index < 100; index ++){ //pour tester
            listOfInformation.add("Date : "+index+", "+index+" jetons -> "+index+" d'argent");
        }

        printInformation();
    }

    private void printInformation(){
        int indexPrint = indexInformation + 15;
        if((indexInformation + 15) > listOfInformation.size()){
            indexPrint = listOfInformation.size();
        }
        for(int index = indexInformation; index < indexPrint; index ++){
            setTextInformation(listOfInformation.get(index));
        }
    }

    private void leftInformation(){
        textInformation.setText("");
        indexInformation -= 15;
        printInformation();

        if(indexInformation <= 15){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if(indexInformation >= listOfInformation.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    private void rightInformation(){
        textInformation.setText("");
        indexInformation += 15;
        printInformation();

        if(indexInformation <= 15){
            leftInformationButton.setVisible(false);
        }
        else {
            leftInformationButton.setVisible(true);
        }
        if((indexInformation + 15) >= listOfInformation.size()){
            rightInformationButton.setVisible(false);
        }
        else {
            rightInformationButton.setVisible(true);
        }
    }

    private void setTextInformation(String information){
        textInformation.setText(textInformation.getText() + information + "\n");
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
