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

import java.util.ArrayList;
import java.util.List;

public class LogMenuController {

    private final SetupScene setupScene = new SetupScene();
    private Stage stageLog;

    private List<String> listOfLog = new ArrayList<>();

    private final Label labelTitle = new Label();
    private final TextArea textLog = new TextArea();

    public void setting(){
        stageLog = new Stage();
        stageLog.setResizable(false);
        BorderPane rootLog = new BorderPane();
        AnchorPane anchorPaneLog = new AnchorPane();
        Scene sceneLog = new Scene(rootLog, 360, 850);
        stageLog.setScene(sceneLog);

        setupScene.setLabel(labelTitle,"Historique", Pos.CENTER,0,25,20,360,new Font(30), Paint.valueOf("BLACK"),true,anchorPaneLog);
        setupScene.setTextArea(textLog,0,100,750,360,false,true,anchorPaneLog);

        rootLog.getChildren().add(anchorPaneLog);
        stageLog.show();
    }

    private void setTextLog(){
        textLog.setText("");
        for(String line : listOfLog){
            textLog.setText(textLog.getText() + line);
        }
    }

    public void getLog(String log){
        listOfLog.add(log + "\n\n");
        setTextLog();
    }

    public void exitLogMenu() {
        if (stageLog != null) {
            stageLog.close();
        }
    }

    public void resetLog(){
        listOfLog = new ArrayList<>();
    }
}
