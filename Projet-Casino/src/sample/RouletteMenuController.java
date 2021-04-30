package sample;

import games.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RouletteMenuController {

    private final int ORIGIN_X_1 = 320;
    private final int ORIGIN_Y_1 = 78;
    private final int END_X_1 = 1046;
    private final int END_Y_1 = 222;
    private final int ORIGIN_X_2 = 383;
    private final int ORIGIN_Y_2 = 222;
    private final int END_X_2 = 992;
    private final int END_Y_2 = 318;

    private final int RADIUS_TOKEN = 16;

    private final int ORIGIN_X_TOKEN = 336;
    private final int ORIGIN_Y_TOKEN = 262;

    private User user;
    private Stage stage;

    private List<Circle> listOfCircleToken = new ArrayList<>();
    private List<Label> listLabelToken = new ArrayList<>();
    private List<Case> listOfCase = new ArrayList<>();
    private List<InformationTokenBet> listOfTokenUsed = new ArrayList<>();
    private int tokenUsed = 0;
    private int indexTokenRemove = -1;

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

    @FXML
    private Label labelInformationBetToken;
    @FXML
    private Button buttonModifyBetToken;
    @FXML
    private Button buttonValidBetToken;
    @FXML
    private TextField textBetToken;


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

        setCasePosition();
    }

    public void setCasePosition(){
        listOfCase.add(new Case(320,78,384,223,"green","0"));
        listOfCase.add(new Case(383,174,434,222,"red","1"));
        listOfCase.add(new Case(383,126,434,174,"black","2"));
        listOfCase.add(new Case(383,78,434,126,"red","3"));
        listOfCase.add(new Case(434,174,485,222,"black","4"));
        listOfCase.add(new Case(434,126,485,174,"red","5"));
        listOfCase.add(new Case(434,78,485,126,"black","6"));
        listOfCase.add(new Case(485,174,536,222,"red","7"));
        listOfCase.add(new Case(485,126,536,174,"black","8"));
        listOfCase.add(new Case(485,78,536,126,"red","9"));
        listOfCase.add(new Case(536,174,587,222,"black","10"));
        listOfCase.add(new Case(536,126,587,174,"black","11"));
        listOfCase.add(new Case(536,78,587,126,"red","12"));
        listOfCase.add(new Case(587,174,638,222,"black","13"));
        listOfCase.add(new Case(587,126,638,174,"red","14"));
        listOfCase.add(new Case(587,78,638,126,"black","15"));
        listOfCase.add(new Case(638,174,689,222,"red","16"));
        listOfCase.add(new Case(638,126,689,174,"black","17"));
        listOfCase.add(new Case(638,78,689,126,"red","18"));
        listOfCase.add(new Case(689,174,740,222,"red","19"));
        listOfCase.add(new Case(689,126,740,174,"black","20"));
        listOfCase.add(new Case(689,78,740,126,"red","21"));
        listOfCase.add(new Case(740,174,791,222,"black","22"));
        listOfCase.add(new Case(740,126,791,174,"red","23"));
        listOfCase.add(new Case(740,78,791,126,"black","24"));
        listOfCase.add(new Case(791,174,842,222,"red","25"));
        listOfCase.add(new Case(791,126,842,174,"black","26"));
        listOfCase.add(new Case(791,78,842,126,"red","27"));
        listOfCase.add(new Case(842,174,893,222,"black","28"));
        listOfCase.add(new Case(842,126,893,174,"black","29"));
        listOfCase.add(new Case(842,78,893,126,"red","30"));
        listOfCase.add(new Case(893,174,944,222,"black","31"));
        listOfCase.add(new Case(893,126,944,174,"red","32"));
        listOfCase.add(new Case(893,78,944,126,"black","33"));
        listOfCase.add(new Case(944,174,995,222,"red","34"));
        listOfCase.add(new Case(944,126,995,174,"black","35"));
        listOfCase.add(new Case(944,78,995,126,"red","36"));
        listOfCase.add(new Case(995,174,1046,222,"green","1st"));
        listOfCase.add(new Case(995,126,1046,174,"green","2nd"));
        listOfCase.add(new Case(995,78,1046,126,"green","3rd"));
        listOfCase.add(new Case(383,222,587,270,"green","1-12"));
        listOfCase.add(new Case(383,270,483,318,"green","1-18"));
        listOfCase.add(new Case(484,270,584,318,"green","even"));
        listOfCase.add(new Case(587,222,791,270,"green","13-24"));
        listOfCase.add(new Case(587,270,687,718,"green","red"));
        listOfCase.add(new Case(688,270,788,718,"green","black"));
        listOfCase.add(new Case(791,222,995,270,"green","25-36"));
        listOfCase.add(new Case(791,270,891,318,"green","odd"));
        listOfCase.add(new Case(892,270,992,318,"green","19-36"));
    }

    public void choosePositionToken(MouseEvent mouseEvent) {
        int mousePositionX = (int)(mouseEvent.getX());
        int mousePositionY = (int)(mouseEvent.getY());

        if(mouseEvent.getButton() == MouseButton.PRIMARY){ //clique gauche
            if(isPositionMouseGood(mousePositionX,mousePositionY)){
                setPositionToken(mousePositionX,mousePositionY,listOfCircleToken.get(tokenUsed),listLabelToken.get(tokenUsed),true);
                betToken(mousePositionX,mousePositionY,listOfCircleToken.get(tokenUsed),listLabelToken.get(tokenUsed));
            }
        }
        if(mouseEvent.getButton() == MouseButton.SECONDARY){ //clique droit
            indexTokenRemove = getTokenToRemove(mousePositionX,mousePositionY);
            System.out.println(indexTokenRemove+" indexTokenused = "+tokenUsed);
            if(indexTokenRemove > -1) {
                if (tokenUsed > 0) {
                    tokenUsed--;
                }

                labelInformationBetToken.setText("Mise d'un jeton  \n\n"+ "Cases sélectionnées : \n" + listOfTokenUsed.get(indexTokenRemove).getCases() + "\n Combinaison : "); //recup combinaison avec methode
                textBetToken.setText(listOfTokenUsed.get(indexTokenRemove).getValueOfBet());
                textBetToken.setVisible(true);
                labelInformationBetToken.setVisible(true);
                buttonModifyBetToken.setVisible(true);
            }
        }
    }

    public int getTokenToRemove(int positionX, int positionY){
        for(int index = 0; index < tokenUsed; index ++){
            int distance = (int) Math.sqrt(Math.pow(Math.abs((positionX - listOfTokenUsed.get(index).getCircleToken().getLayoutX())) ,2) + Math.pow(Math.abs((positionY - listOfTokenUsed.get(index).getCircleToken().getLayoutY())) ,2));
            if(distance <= listOfTokenUsed.get(index).getCircleToken().getRadius()){
                return index;
            }
        }
        return -1;
    }

    public void setPositionToken(int positionX, int positionY, Circle circleToken, Label labelToken, boolean state){
        circleToken.setLayoutX(positionX);
        circleToken.setLayoutY(positionY);
        circleToken.setVisible(state);
        labelToken.setLayoutX(positionX - 10);
        labelToken.setLayoutY(positionY - 10);
        labelToken.setVisible(state);
    }

    public boolean isPositionMouseGood(int positionX, int positionY){
        if((positionX >= ORIGIN_X_1 && positionX <= END_X_1) && (positionY >= ORIGIN_Y_1 && positionY <= END_Y_1)){
            return true;
        }
        else{
            if((positionX >= ORIGIN_X_2 && positionX <= END_X_2) && (positionY >= ORIGIN_Y_2 && positionY <= END_Y_2)){
                return true;
            }
            else {
                return false;
            }
        }
    }

    public void betToken(int positionXToken, int positionYToken, Circle circleToken, Label LabelToken){
        List<Case> listOfCaseToken = new ArrayList<>();
        for(int index = 0; index < listOfCase.size(); index ++){
            if(tokenInTheCase(listOfCase.get(index),positionXToken,positionYToken)){
                listOfCaseToken.add(listOfCase.get(index));
            }
        }
        InformationTokenBet informationTokenBet = new InformationTokenBet(circleToken,labelToken1,listOfCaseToken);
        listOfTokenUsed.add(informationTokenBet);
        labelInformationBetToken.setText("Mise d'un jeton  \n\n"+ "Cases sélectionner : \n" + informationTokenBet.getCases() + "\n Combinaison : "); //recup combinaison avec methode

        labelInformationBetToken.setVisible(true);
        buttonValidBetToken.setVisible(true);
        textBetToken.setText("");
        textBetToken.setVisible(true);
    }

    public boolean tokenInTheCase(Case cases, int positionXToken, int positionYToken){
        if(((positionXToken + RADIUS_TOKEN) >= cases.getOriginX() && (positionXToken + RADIUS_TOKEN) <= cases.getEndX()) && (positionYToken >= cases.getOriginY() && positionYToken <= cases.getEndY())){
            return true;
        }
        else {
            if(((positionXToken - RADIUS_TOKEN) >= cases.getOriginX() && (positionXToken - RADIUS_TOKEN) <= cases.getEndX()) && (positionYToken >= cases.getOriginY() && positionYToken <= cases.getEndY())){
                return true;
            }
            else {
                if(((positionYToken + RADIUS_TOKEN) >= cases.getOriginY() && (positionYToken + RADIUS_TOKEN) <= cases.getEndY()) && (positionXToken >= cases.getOriginX() && positionXToken <= cases.getEndX())){
                    return true;
                }
                else{
                    if(((positionYToken - RADIUS_TOKEN) >= cases.getOriginY() && (positionYToken - RADIUS_TOKEN) <= cases.getEndY()) && (positionXToken >= cases.getOriginX() && positionXToken <= cases.getOriginX())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
    }

    public void modifyBetToken(MouseEvent mouseEvent) {
        try {
            int valueOfBet = Integer.parseInt(textBetToken.getText());
            if(valueOfBet <= 0){
                setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(indexTokenRemove), listLabelToken.get(indexTokenRemove), false);
            }
            else{
                listOfTokenUsed.get(listOfTokenUsed.size() - 1).setValueOfBet(textBetToken.getText());
                tokenUsed++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        buttonModifyBetToken.setVisible(false);
        textBetToken.setVisible(false);
        labelInformationBetToken.setVisible(false);
    }

    public void validBetToken(MouseEvent mouseEvent){
        try {
            int valueOfBet = Integer.parseInt(textBetToken.getText());
            if(valueOfBet <= 0){
                setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), false);
            }
            else{
                listOfTokenUsed.get(listOfTokenUsed.size() - 1).setValueOfBet(textBetToken.getText());
                tokenUsed++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        buttonValidBetToken.setVisible(false);
        textBetToken.setVisible(false);
        labelInformationBetToken.setVisible(false);
    }
}
