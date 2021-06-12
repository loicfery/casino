package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Scene sceneSetting;
    private BorderPane root;

    private double soundVolume;
    private boolean backgroundAnimation;
    private double sizeX;
    private double sizeY;

    private final Label titleLabel = new Label();
    private final Label soundVolumeTitleLabel = new Label();
    private final Label soundVolumeLabel = new Label();
    private final Label backgroundAnimationLabel = new Label();
    private final Label languageLabel = new Label();
    private final Label sizeLabel = new Label();

    private final Slider soundVolumeSlider = new Slider();

    private final CheckBox backgroundAnimationCheckBox = new CheckBox();

    private final RadioButton languageFrenchRadioButton = new RadioButton();
    private final RadioButton languageEnglishRadioButton = new RadioButton();

    private final ToggleGroup languageGroup = new ToggleGroup();

    private MenuBar menuBarSize;
    private Menu menuSize;
    private MenuItem itemSize1;
    private MenuItem itemSize2;

    public SettingMenuController(InterfaceMenu interfaceMenu, Language language, double soundVolume, boolean backgroundAnimation, double sizeX, double sizeY){
        this.interfaceMenu = interfaceMenu;
        this.soundVolume = soundVolume;
        this.backgroundAnimation = backgroundAnimation;
        this.language = language;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Méthode qui initialise l'interface
     **/
    public void setting(){
        AnchorPane anchorPane = new AnchorPane();
        root = new BorderPane();
        stage = new Stage();
        stage.setResizable(false);
        sceneSetting = new Scene(root, sizeX * 500, sizeY * 500);
        stage.setScene(sceneSetting);

        menuBarSize = new MenuBar();
        menuSize = new Menu("taille");
        itemSize1 = new MenuItem("taille 1");
        itemSize2 = new MenuItem("taille 2");

        setupScene.setLabel(titleLabel,language.getLine("settingTitleLabel"), Pos.CENTER,sizeX * 150,sizeY * 15,sizeY * 30,sizeX * 200,new Font(sizeX * 30), Paint.valueOf("BLACK"),true, anchorPane);

        setupScene.setLabel(soundVolumeTitleLabel,language.getLine("settingSoundVolumeTitleLabel"),Pos.CENTER_LEFT,sizeX * 50,sizeY * 200,sizeY * 20,sizeX * 120,new Font(sizeX * 15),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setLabel(backgroundAnimationLabel,language.getLine("settingBackgroundAnimationTitleLabel"),Pos.CENTER_LEFT,sizeX * 50,sizeY * 250,sizeY * 20,sizeX * 200,new Font(sizeX * 15),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setLabel(soundVolumeLabel,""+(int)(soundVolume * 100),Pos.CENTER,sizeX * 370,sizeY * 200,sizeY * 23,sizeX * 80,new Font(sizeX * 20),Paint.valueOf("BLACK"),true, anchorPane);
        setupScene.setLabel(languageLabel,language.getLine("settingLanguageTitleLabel"),Pos.CENTER_LEFT,sizeX * 50,sizeY * 300,sizeY * 20,sizeX * 100,new Font(sizeX * 15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setLabel(sizeLabel,"Taille :",Pos.CENTER_LEFT,sizeX * 50,sizeY * 350,sizeY * 20,sizeX * 100,new Font(sizeX * 15),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setSlider(soundVolumeSlider,sizeX * 160,sizeY * 200,sizeY * 23,sizeX * 200,0.0,1.0, soundVolume,true, anchorPane);

        setupScene.setCheckBox(backgroundAnimationCheckBox,sizeX * 210,sizeY * 250,sizeY * 20,sizeX * 20,backgroundAnimation,true, anchorPane);

        setupScene.setRadioButton(languageFrenchRadioButton,sizeX * 160,sizeY * 300,sizeY * 200,sizeX * 20,"Français",new Font(sizeX * 15),Paint.valueOf("BLACK"),true,anchorPane);
        setupScene.setRadioButton(languageEnglishRadioButton,sizeX * 280,sizeY * 300,sizeY * 200,sizeX * 20,"English",new Font(sizeX * 15),Paint.valueOf("BLACK"),true,anchorPane);

        setupScene.setMenu(menuBarSize,sizeX * 150,sizeY * 350,sizeY * 20,sizeX * 100,true,anchorPane);

        languageEnglishRadioButton.setToggleGroup(languageGroup);
        languageFrenchRadioButton.setToggleGroup(languageGroup);
        menuSize.getItems().addAll(itemSize1, itemSize2);
        menuBarSize.getMenus().addAll(menuSize);

        checkLanguage();

        itemSize1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchSize(itemSize1);
                interfaceMenu.setSizeX(sizeX);
                interfaceMenu.setSizeY(sizeY);
                interfaceMenu.refresh();
                refresh();
            }
        });

        itemSize2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchSize(itemSize2);
                interfaceMenu.setSizeX(sizeX);
                interfaceMenu.setSizeY(sizeY);
                interfaceMenu.refresh();
                refresh();
            }
        });

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
        if(language.getLanguage().equals("language_french")){
            languageFrenchRadioButton.setSelected(true);
            languageFrenchRadioButton.requestFocus();
        }
        if(language.getLanguage().equals("language_english")){
            languageEnglishRadioButton.setSelected(true);
            languageEnglishRadioButton.requestFocus();
        }
    }

    /** Méthode qui change de langue **/
    private void switchLanguage(RadioButton button){
        if(languageFrenchRadioButton.getText().equals(button.getText())){
            language = new Language("language_french");
        }
        if(languageEnglishRadioButton.getText().equals(button.getText())){
            language = new Language("language_english");
        }

        interfaceMenu.setLanguage(language);
        interfaceMenu.refresh();
        refresh();
    }

    private void switchSize(MenuItem item){
        switch(item.getText()){
            case "taille 1" :
                sizeX = 1;
                sizeY = 1;
                break;
            case "taille 2" :
                sizeX = 1.5;
                sizeY = 1.5;
                break;
            default:
                System.out.println("error");
        }

        refresh();
    }

    private void refresh(){
        titleLabel.setText(language.getLine("settingTitleLabel"));
        soundVolumeTitleLabel.setText(language.getLine("settingSoundVolumeTitleLabel"));
        backgroundAnimationLabel.setText(language.getLine("settingBackgroundAnimationTitleLabel"));
        languageLabel.setText(language.getLine("settingLanguageTitleLabel"));

        refreshPosition();
    }

    private void refreshPosition(){
        sceneSetting.setRoot(new BorderPane());
        sceneSetting = new Scene(root, sizeX * 500, sizeY * 500);
        stage.setScene(sceneSetting);

        titleLabel.setLayoutX(sizeX * 150);
        titleLabel.setLayoutY(sizeY * 15);
        titleLabel.setPrefHeight(sizeY * 30);
        titleLabel.setPrefWidth(sizeX * 200);
        titleLabel.setFont(new Font(sizeX * 30));

        soundVolumeTitleLabel.setLayoutX(sizeX * 50);
        soundVolumeTitleLabel.setLayoutY(sizeY * 200);
        soundVolumeTitleLabel.setPrefHeight(sizeY * 20);
        soundVolumeTitleLabel.setPrefWidth(sizeX * 120);
        soundVolumeTitleLabel.setFont(new Font(sizeX * 15));

        backgroundAnimationLabel.setLayoutX(sizeX * 50);
        backgroundAnimationLabel.setLayoutY(sizeY * 250);
        backgroundAnimationLabel.setPrefHeight(sizeY * 20);
        backgroundAnimationLabel.setPrefWidth(sizeX * 200);
        backgroundAnimationLabel.setFont(new Font(sizeX * 15));

        soundVolumeLabel.setLayoutX(sizeX * 370);
        soundVolumeLabel.setLayoutY(sizeY * 200);
        soundVolumeLabel.setPrefHeight(sizeY * 23);
        soundVolumeLabel.setPrefWidth(sizeX * 80);
        soundVolumeLabel.setFont(new Font(sizeX * 20));

        languageLabel.setLayoutX(sizeX * 50);
        languageLabel.setLayoutY(sizeY * 300);
        languageLabel.setPrefHeight(sizeY * 20);
        languageLabel.setPrefWidth(sizeX * 100);
        languageLabel.setFont(new Font(sizeX * 15));

        sizeLabel.setLayoutX(sizeX * 50);
        sizeLabel.setLayoutY(sizeY * 350);
        sizeLabel.setPrefHeight(sizeY * 20);
        sizeLabel.setPrefWidth(sizeX * 100);
        sizeLabel.setFont(new Font(sizeX * 15));

        soundVolumeSlider.setLayoutX(sizeX * 160);
        soundVolumeSlider.setLayoutY(sizeY * 200);
        soundVolumeSlider.setPrefHeight(sizeY * 23);
        soundVolumeSlider.setPrefWidth(sizeX * 200);

        backgroundAnimationCheckBox.setLayoutX(sizeX * 210);
        backgroundAnimationCheckBox.setLayoutY(sizeY * 250);
        backgroundAnimationCheckBox.setPrefHeight(sizeY * 20);
        backgroundAnimationCheckBox.setPrefWidth(sizeX * 20);

        languageFrenchRadioButton.setLayoutX(sizeX * 160);
        languageFrenchRadioButton.setLayoutY(sizeY * 300);
        languageFrenchRadioButton.setFont(new Font(sizeX * 15));

        languageEnglishRadioButton.setLayoutX(sizeX * 280);
        languageEnglishRadioButton.setLayoutY(sizeY * 300);
        languageEnglishRadioButton.setFont(new Font(sizeX * 15));


        menuBarSize.setLayoutX(sizeX * 150);
        menuBarSize.setLayoutY(sizeY * 350);
        menuBarSize.setPrefHeight(sizeY * 20);
        menuBarSize.setPrefWidth(sizeX * 100);
    }
}
