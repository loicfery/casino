package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class RuleMenuController {

    private final SetupScene setupScene = new SetupScene();
    private Stage stage;
    private InterfaceMenu interfaceMenu;
    private final Language language;

    private final Label labelTitle = new Label();
    private final TextArea textRule = new TextArea();
    private int heightMax;
    private int widthMax;

    public RuleMenuController(InterfaceMenu interfaceMenu, Language language){
        this.interfaceMenu = interfaceMenu;
        this.language = language;
    }

    /**
     * Méthode qui initialise l'interface
     **/
    public void setting(){
        stage = new Stage();
        stage.setResizable(false);
        BorderPane rootLog = new BorderPane();
        AnchorPane anchorPaneLog = new AnchorPane();
        setRule();
        Scene sceneLog = new Scene(rootLog, widthMax, heightMax);
        //scene.getStylesheets().add(getClass().getResource("interfaceMenuSetting.css").toExternalForm());
        stage.setScene(sceneLog);

        setupScene.setLabel(labelTitle,labelTitle.getText(), Pos.CENTER,0,25,20,widthMax,new Font(30), Paint.valueOf("BLACK"),true,anchorPaneLog);
        setupScene.setTextArea(textRule,0,100,(heightMax - 100),widthMax,false,true,anchorPaneLog);


        rootLog.getChildren().add(anchorPaneLog);
        stage.show();
    }

    /**
     * Méthode pour écrire les règles du jeux dans une zone de texte
     **/
    private void setRule() {
        File fileRule = null;

        try {
            if(interfaceMenu instanceof BlackJackMenuController) {
                heightMax = 660;
                widthMax = 700;
                fileRule = new File(language.getRuleFileNameBlackJack());
                labelTitle.setText(language.getLabelTitleBlackJack());
            }
            if(interfaceMenu instanceof RouletteMenuController){
                heightMax = 665;
                widthMax = 950;
                fileRule = new File(language.getRuleFileNameRoulette());
                labelTitle.setText(language.getLabelTitleRoulette());
            }
            if(interfaceMenu instanceof SlotMachineMenuController){
                heightMax = 450;
                widthMax = 580;
                fileRule = new File(language.getRuleFileNameSlotMachine());
                labelTitle.setText(language.getLabelTitleSlotMachine());
            }

            if(fileRule != null) {
                BufferedReader buffer = new BufferedReader(new FileReader(fileRule));
                String line;

                while ((line = buffer.readLine()) != null) {
                    textRule.setText(textRule.getText() + "\n" + line);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("rule file not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour fermer le menu des règles
     **/
    public void exitRuleMenu(){
        if(stage != null){
            stage.close();
        }
    }
}
