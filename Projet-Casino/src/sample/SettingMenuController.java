package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingMenuController {

    private final InterfaceMenu interfaceMenu;
    private Stage stageSetting;
    private final SetupScene setupScene = new SetupScene();

    private double soundVolume;
    private boolean backgroundAnimation;

    private final Label titleLabel = new Label();
    private final Label soundVolumeTitleLabel = new Label();
    private final Label soundVolumeLabel = new Label();
    private final Label backgroundAnimationLabel = new Label();

    private final Slider soundVolumeSlider = new Slider();

    private final CheckBox backgroundAnimationCheckBox = new CheckBox();

    public SettingMenuController(InterfaceMenu interfaceMenu, double soundVolume, boolean backgroundAnimation){
        this.interfaceMenu = interfaceMenu;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
    }

    public void setting(){
        AnchorPane anchorPane = new AnchorPane();
        BorderPane root = new BorderPane();
        stageSetting = new Stage();
        Scene sceneSetting = new Scene(root, 500, 500);
        //scene.getStylesheets().add(getClass().getResource("interfaceMenuSetting.css").toExternalForm());
        stageSetting.setScene(sceneSetting);

        setupScene.setLabel(titleLabel,"Paramètres", Pos.CENTER,150,15,30,200,new Font(30), Paint.valueOf("BLACK"),true, anchorPane);

        setupScene.setLabel(soundVolumeTitleLabel,"volume son : ",Pos.CENTER_LEFT,50,200,20,100,new Font(15),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setSlider(soundVolumeSlider,160,200,23,200,0.0,1.0, soundVolume,true, anchorPane);
        setupScene.setLabel(soundVolumeLabel,""+(int)(soundVolume * 100),Pos.CENTER,370,200,23,80,new Font(20),Paint.valueOf("BLACK"),true, anchorPane);

        setupScene.setLabel(backgroundAnimationLabel,"Animation arrière plan :",Pos.CENTER_LEFT,50,250,20,200,new Font(15),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setCheckBox(backgroundAnimationCheckBox,210,250,20,20,backgroundAnimation,true, anchorPane);

        soundVolumeSlider.valueProperty().addListener(observable -> {
            soundVolume = soundVolumeSlider.getValue();
            interfaceMenu.setSoundVolume(soundVolume);
            soundVolumeLabel.setText(""+(int)(soundVolume * 100));
        });

        backgroundAnimationCheckBox.selectedProperty().addListener(observable -> {
            backgroundAnimation = backgroundAnimationCheckBox.isSelected();
            interfaceMenu.setBackgroundAnimation(backgroundAnimation);
        });

        root.getChildren().add(anchorPane);
        stageSetting.show();
    }

    public void exitSettingMenu(){
        if(stageSetting != null) {
            stageSetting.close();
        }
    }
}
