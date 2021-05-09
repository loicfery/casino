package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InterfaceMenuSetting {

    private InterfaceMenu interfaceMenu;
    private Stage stageSetting;
    private Scene sceneSetting;
    private BorderPane root = new BorderPane();
    private final AnchorPane anchorPane = new AnchorPane();
    private SetupScene setupScene = new SetupScene();

    private double soundVolume;

    private Label titleLabel = new Label();
    private Label soundVolumeTitleLabel = new Label();
    private Label soundVolumeLabel = new Label();

    private Slider soundVolumeSlider = new Slider();

    public InterfaceMenuSetting(InterfaceMenu interfaceMenu, double soundVolume){
        this.interfaceMenu = interfaceMenu;
        this.soundVolume = soundVolume;
    }

    public void setting(){
        stageSetting = new Stage();
        sceneSetting = new Scene(root,500,500);
        //scene.getStylesheets().add(getClass().getResource("interfaceMenuSetting.css").toExternalForm());
        stageSetting.setScene(sceneSetting);

        setupScene.setLabel(titleLabel,"Paramètres", Pos.CENTER,150,15,30,200,new Font(30), Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(soundVolumeTitleLabel,"volume son : ",Pos.CENTER,50,200,20,100,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setSlider(soundVolumeSlider,160,200,23,200,0.0,1.0, soundVolume,true,anchorPane);
        setupScene.setLabel(soundVolumeLabel,""+(int)(soundVolume * 100),Pos.CENTER,370,200,23,80,new Font(20),Paint.valueOf("BLACK"),true,anchorPane);

        soundVolumeSlider.valueProperty().addListener(observable -> {
            soundVolume = soundVolumeSlider.getValue();
            interfaceMenu.setSoundVolume(soundVolume);
            soundVolumeLabel.setText(""+(int)(soundVolume * 100));
        });

        root.getChildren().add(anchorPane);
        stageSetting.show();
    }
}
