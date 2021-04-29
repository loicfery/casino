package sample;

import games.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RouletteMenuController {

    private final int ORIGIN_X_TOKEN_CIRCLE = 336;
    private final int ORIGIN_Y_TOKEN_CIRCLE = 262;
    private final int ORIGIN_X_TOKEN_LABEL = 325;
    private final int ORIGIN_Y_TOKEN_LABEL = 252;

    private User user;
    private Stage stage;

    private List<Circle> listOfCircleToken = new ArrayList<>();
    private List<Label> listLabelToken = new ArrayList<>();

     @FXML
     private Label labelProfit;
     @FXML
     private Label labelTokenUser;
     @FXML
     private Label labelPseudo;

    @FXML
    private Circle circleToken1;
    @FXML
    private Label labelToken1;

    @FXML
    private Circle circleToken2;
    @FXML
    private Label labelToken2;

    @FXML
    private Circle circleToken3;
    @FXML
    private Label labelToken3;

    @FXML
    private Circle circleToken4;
    @FXML
    private Label labelToken4;

    @FXML
    private Circle circleToken5;
    @FXML
    private Label labelToken5;

    @FXML
    private Circle circleToken6;
    @FXML
    private Label labelToken6;

    @FXML
    private Circle circleToken7;
    @FXML
    private Label labelToken7;

    @FXML
    private Circle circleToken8;
    @FXML
    private Label labelToken8;

    @FXML
    private Circle circleToken9;
    @FXML
    private Label labelToken9;

    @FXML
    private Circle circleToken10;
    @FXML
    private Label labelToken10;

    @FXML
    private Circle circleToken11;
    @FXML
    private Label labelToken11;

    @FXML
    private Circle circleToken12;
    @FXML
    private Label labelToken12;

    @FXML
    private Circle circleToken13;
    @FXML
    private Label labelToken13;

    @FXML
    private Circle circleToken14;
    @FXML
    private Label labelToken14;

    @FXML
    private Circle circleToken15;
    @FXML
    private Label labelToken15;


    public RouletteMenuController(User user, Stage stage){
        this.user = user;
        this.stage = stage;
    }

    public void initialize(){
        labelPseudo.setText("Joueur : "+user.getPseudo());
        labelTokenUser.setText("Jetons : "+user.getNumberOfToken());

        listOfCircleToken.add(circleToken1);
        listOfCircleToken.add(circleToken2);
        listOfCircleToken.add(circleToken3);
        listOfCircleToken.add(circleToken4);
        listOfCircleToken.add(circleToken5);
        listOfCircleToken.add(circleToken6);
        listOfCircleToken.add(circleToken7);
        listOfCircleToken.add(circleToken8);
        listOfCircleToken.add(circleToken9);
        listOfCircleToken.add(circleToken10);
        listOfCircleToken.add(circleToken11);
        listOfCircleToken.add(circleToken12);
        listOfCircleToken.add(circleToken13);
        listOfCircleToken.add(circleToken14);
        listOfCircleToken.add(circleToken15);

        listLabelToken.add(labelToken1);
        listLabelToken.add(labelToken2);
        listLabelToken.add(labelToken3);
        listLabelToken.add(labelToken4);
        listLabelToken.add(labelToken5);
        listLabelToken.add(labelToken6);
        listLabelToken.add(labelToken7);
        listLabelToken.add(labelToken8);
        listLabelToken.add(labelToken9);
        listLabelToken.add(labelToken10);
        listLabelToken.add(labelToken11);
        listLabelToken.add(labelToken12);
        listLabelToken.add(labelToken13);
        listLabelToken.add(labelToken14);
        listLabelToken.add(labelToken15);
    }


    public void betToken(MouseEvent mouseEvent) {
        int mousePositionX = 0;
        int mousePositionY = 0;

        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            mousePositionX = (int)(mouseEvent.getX());
            mousePositionY = (int)(mouseEvent.getY() * 10);
            listOfCircleToken.get(0).setLayoutX(mousePositionX);
            listOfCircleToken.get(0).setLayoutY(mousePositionY);
            System.out.println(mousePositionX+", "+mousePositionY);
        }
    }
}
