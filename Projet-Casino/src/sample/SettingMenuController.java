package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingMenuController {

    private final InterfaceMenu interfaceMenu;
    private Stage stage;
    private final SetupScene setupScene = new SetupScene();
    private Language language;

    private double soundVolume;
    private boolean backgroundAnimation;

    private final Label titleLabel = new Label();
    private final Label soundVolumeTitleLabel = new Label();
    private final Label soundVolumeLabel = new Label();
    private final Label backgroundAnimationLabel = new Label();
    private final Label languageLabel = new Label();

    private final Slider soundVolumeSlider = new Slider();

    private final CheckBox backgroundAnimationCheckBox = new CheckBox();

    private final RadioButton languageFrenchRadioButton = new RadioButton();
    private final RadioButton languageEnglishRadioButton = new RadioButton();

    private final ToggleGroup languageGroup = new ToggleGroup();

    public SettingMenuController(InterfaceMenu interfaceMenu, Language language, double soundVolume, boolean backgroundAnimation){
        this.interfaceMenu = interfaceMenu;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        this.language = language;
    }

    /**
     * Méthode qui initialise l'interface
     **/
    public void setting(){
        AnchorPane anchorPane = new AnchorPane();
        BorderPane root = new BorderPane();
        stage = new Stage();
        stage.setResizable(false);
        Scene sceneSetting = new Scene(root, 500, 500);
        stage.setScene(sceneSetting);

        setupScene.setLabel(titleLabel,language.getSettingMenuControllerTitleLabel(), Pos.CENTER,150,15,30,200,new Font(30), Paint.valueOf("BLACK"),true, anchorPane);

        setupScene.setLabel(soundVolumeTitleLabel,language.getSettingMenuControllerSoundVolumeTitleLabel(),Pos.CENTER_LEFT,50,200,20,120,new Font(15),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setLabel(backgroundAnimationLabel,language.getSettingMenuControllerBackgroundAnimation(),Pos.CENTER_LEFT,50,250,20,200,new Font(15),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setLabel(soundVolumeLabel,""+(int)(soundVolume * 100),Pos.CENTER,370,200,23,80,new Font(20),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setLabel(languageLabel,language.getSettingMenuControllerLanguage(),Pos.CENTER_LEFT,50,300,20,100,new Font(15),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setSlider(soundVolumeSlider,160,200,23,200,0.0,1.0, soundVolume,true, anchorPane);

        setupScene.setCheckBox(backgroundAnimationCheckBox,210,250,20,20,backgroundAnimation,true, anchorPane);

        setupScene.setRadioButton(languageFrenchRadioButton,160,300,200,20,"Français",new Font(15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setRadioButton(languageEnglishRadioButton,280,300,200,20,"English",new Font(15),Paint.valueOf("BLACK"),true,anchorPane);

        languageEnglishRadioButton.setToggleGroup(languageGroup);
        languageFrenchRadioButton.setToggleGroup(languageGroup);

        checkLanguage();

        languageGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                if(languageGroup.getSelectedToggle() != null){
                    RadioButton button = (RadioButton) languageGroup.getSelectedToggle();
                    switchLanguage(button);
                }
            }
        });

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
        stage.show();
    }

    /**
     * Méthode pour quitter le menu des paramètres
     **/
    public void exitSettingMenu(){
        if(stage != null) {
            stage.close();
        }
    }

    /** Méthode qui affiche la langue actuelle dans les boutons de langues **/
    private void checkLanguage(){
        if(language instanceof LanguageFrench){
            languageFrenchRadioButton.setSelected(true);
            languageFrenchRadioButton.requestFocus();
        }
        if(language instanceof LanguageEnglish){
            languageEnglishRadioButton.setSelected(true);
            languageEnglishRadioButton.requestFocus();
        }
    }

    /** Méthode qui change de langue **/
    private void switchLanguage(RadioButton button){
        if(languageFrenchRadioButton.getText().equals(button.getText())){
            language = new LanguageFrench();
        }
        if(languageEnglishRadioButton.getText().equals(button.getText())){
            language = new LanguageEnglish();
        }

        interfaceMenu.setLanguage(language);
        interfaceMenu.refresh();
    }
}
