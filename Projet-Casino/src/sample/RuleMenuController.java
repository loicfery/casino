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
    private Stage stageRule;
    private InterfaceMenu interfaceMenu;

    private final Label labelTitle = new Label();
    private final TextArea textRule = new TextArea();
    private int heightMax;
    private int widthMax;

    public RuleMenuController(InterfaceMenu interfaceMenu){
        this.interfaceMenu = interfaceMenu;
    }

    public void setting(){
        stageRule = new Stage();
        stageRule.setResizable(false);
        BorderPane rootLog = new BorderPane();
        AnchorPane anchorPaneLog = new AnchorPane();
        setRule();
        Scene sceneLog = new Scene(rootLog, widthMax, heightMax);
        //scene.getStylesheets().add(getClass().getResource("interfaceMenuSetting.css").toExternalForm());
        stageRule.setScene(sceneLog);

        setupScene.setLabel(labelTitle,labelTitle.getText(), Pos.CENTER,0,25,20,widthMax,new Font(30), Paint.valueOf("BLACK"),true,anchorPaneLog);
        setupScene.setTextArea(textRule,0,100,(heightMax - 100),widthMax,false,true,anchorPaneLog);


        rootLog.getChildren().add(anchorPaneLog);
        stageRule.show();
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
                fileRule = new File("rule_blackJack.txt");
                labelTitle.setText("Règles du Black Jack");
            }
            if(interfaceMenu instanceof RouletteMenuController){
                heightMax = 665;
                widthMax = 950;
                fileRule = new File("rule_roulette.txt");
                labelTitle.setText("Règles de la roulette");
            }
            if(interfaceMenu instanceof SlotMachineMenuController){
                heightMax = 450;
                widthMax = 580;
                fileRule = new File("rule_slotMachine.txt");
                labelTitle.setText("Règles des machines à sous");
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

    public void exitRuleMenu(){
        if(stageRule != null){
            stageRule.close();
        }
    }
}
