package sample;

import games.User;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private final int POSITION_X_CASE_ROULETTE_21 = 984;
    private final int POSITION_Y_CASE_ROULETTE_21 = 475;
    private final int POSITION_X_CASE_ROULETTE_2 = 963;
    private final int POSITION_Y_CASE_ROULETTE_2 = 493;
    private final int POSITION_X_CASE_ROULETTE_25 = 972;
    private final int POSITION_Y_CASE_ROULETTE_25 = 513;
    private final int POSITION_X_CASE_ROULETTE_17 = 979;
    private final int POSITION_Y_CASE_ROULETTE_17 = 535;
    private final int POSITION_X_CASE_ROULETTE_34 = 982;
    private final int POSITION_Y_CASE_ROULETTE_34 = 557;
    private final int POSITION_X_CASE_ROULETTE_6 = 982;
    private final int POSITION_Y_CASE_ROULETTE_6 = 579;
    private final int POSITION_X_CASE_ROULETTE_27 = 976;
    private final int POSITION_Y_CASE_ROULETTE_27 = 600;
    private final int POSITION_X_CASE_ROULETTE_13 = 967;
    private final int POSITION_Y_CASE_ROULETTE_13 = 620;
    private final int POSITION_X_CASE_ROULETTE_36 = 957;
    private final int POSITION_Y_CASE_ROULETTE_36 = 641;
    private final int POSITION_X_CASE_ROULETTE_11 = 942;
    private final int POSITION_Y_CASE_ROULETTE_11 = 658;
    private final int POSITION_X_CASE_ROULETTE_30 = 925;
    private final int POSITION_Y_CASE_ROULETTE_30 = 670;
    private final int POSITION_X_CASE_ROULETTE_8 = 906;
    private final int POSITION_Y_CASE_ROULETTE_8 = 680;
    private final int POSITION_X_CASE_ROULETTE_23 = 885;
    private final int POSITION_Y_CASE_ROULETTE_23 = 691;
    private final int POSITION_X_CASE_ROULETTE_10 = 861;
    private final int POSITION_Y_CASE_ROULETTE_10 = 694;
    private final int POSITION_X_CASE_ROULETTE_5 = 841;
    private final int POSITION_Y_CASE_ROULETTE_5 = 693;
    private final int POSITION_X_CASE_ROULETTE_24 = 816;
    private final int POSITION_Y_CASE_ROULETTE_24 = 694;
    private final int POSITION_X_CASE_ROULETTE_16 = 794;
    private final int POSITION_Y_CASE_ROULETTE_16 = 686;
    private final int POSITION_X_CASE_ROULETTE_33 = 775;
    private final int POSITION_Y_CASE_ROULETTE_33 = 674;
    private final int POSITION_X_CASE_ROULETTE_1 = 757;
    private final int POSITION_Y_CASE_ROULETTE_1 = 660;
    private final int POSITION_X_CASE_ROULETTE_20 = 742;
    private final int POSITION_Y_CASE_ROULETTE_20 = 645;
    private final int POSITION_X_CASE_ROULETTE_14 = 729;
    private final int POSITION_Y_CASE_ROULETTE_14 = 624;
    private final int POSITION_X_CASE_ROULETTE_31 = 718;
    private final int POSITION_Y_CASE_ROULETTE_31 = 603;
    private final int POSITION_X_CASE_ROULETTE_9 = 714;
    private final int POSITION_Y_CASE_ROULETTE_9 = 580;
    private final int POSITION_X_CASE_ROULETTE_22 = 716;
    private final int POSITION_Y_CASE_ROULETTE_22 = 556;
    private final int POSITION_X_CASE_ROULETTE_18 = 715;
    private final int POSITION_Y_CASE_ROULETTE_18 = 533;
    private final int POSITION_X_CASE_ROULETTE_29 = 722;
    private final int POSITION_Y_CASE_ROULETTE_29 = 512;
    private final int POSITION_X_CASE_ROULETTE_7 = 734;
    private final int POSITION_Y_CASE_ROULETTE_7 = 492;
    private final int POSITION_X_CASE_ROULETTE_28 = 745;
    private final int POSITION_Y_CASE_ROULETTE_28 = 469;
    private final int POSITION_X_CASE_ROULETTE_12 = 765;
    private final int POSITION_Y_CASE_ROULETTE_12 = 454;
    private final int POSITION_X_CASE_ROULETTE_35 = 786;
    private final int POSITION_Y_CASE_ROULETTE_35 = 443;
    private final int POSITION_X_CASE_ROULETTE_3 = 807;
    private final int POSITION_Y_CASE_ROULETTE_3 = 433;
    private final int POSITION_X_CASE_ROULETTE_26 = 829;
    private final int POSITION_Y_CASE_ROULETTE_26 = 426;
    private final int POSITION_X_CASE_ROULETTE_0 = 850;
    private final int POSITION_Y_CASE_ROULETTE_0 = 425;
    private final int POSITION_X_CASE_ROULETTE_32 = 875;
    private final int POSITION_Y_CASE_ROULETTE_32 = 428;
    private final int POSITION_X_CASE_ROULETTE_15 = 898;
    private final int POSITION_Y_CASE_ROULETTE_15 = 433;
    private final int POSITION_X_CASE_ROULETTE_19 = 920;
    private final int POSITION_Y_CASE_ROULETTE_19 = 441;
    private final int POSITION_X_CASE_ROULETTE_4 = 938;
    private final int POSITION_Y_CASE_ROULETTE_4 = 455;

    private final int RADIUS_TOKEN = 16;

    private final int ORIGIN_X_TOKEN = 336;
    private final int ORIGIN_Y_TOKEN = 262;

    private User user;
    private Stage stage;

    private List<Circle> listOfCircleToken = new ArrayList<>();
    private List<Label> listLabelToken = new ArrayList<>();
    private List<Case> listOfCase = new ArrayList<>();
    private List<InformationTokenBet> listOfTokenUsed = new ArrayList<>();
    private List<Integer> listPositionXCaseRoulette = new ArrayList<>();
    private List<Integer> listPositionYCaseRoulette = new ArrayList<>();
    private int tokenUsed = 0;
    private int indexTokenRemove = 0;
    private boolean startingGame = false;

     @FXML
     private Label labelProfit;
     @FXML
     private Label labelTokenUser;
     @FXML
     private Label labelPseudo;
    @FXML
    private Label labelInformationBetToken;
    @FXML
    private Label labelError;
    @FXML
    private Button buttonModifyBetToken;
    @FXML
    private Button buttonValidBetToken;
    @FXML
    private Button buttonStartingGame;
    @FXML
    private TextField textBetToken;
    @FXML
    private Circle circleBallRoulette;

     /** cercle et label de 15 jetons pour miser **/
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

        setCasePosition();
        setCaseRoulette();
    }

    /** Création de la liste des cases pour parier **/
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

    /** Création de la liste des position des cases de la roulette **/
    public void setCaseRoulette(){
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_21);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_21);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_2);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_2);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_25);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_25);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_17);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_17);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_34);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_34);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_6);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_6);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_27);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_27);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_13);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_13);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_36);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_36);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_11);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_11);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_30);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_30);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_8);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_8);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_23);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_23);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_10);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_10);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_5);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_5);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_24);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_24);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_16);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_16);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_33);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_33);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_1);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_1);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_20);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_20);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_14);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_14);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_31);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_31);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_9);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_9);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_22);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_22);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_18);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_18);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_29);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_29);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_7);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_7);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_28);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_28);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_12);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_12);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_35);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_35);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_3);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_3);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_26);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_26);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_0);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_0);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_32);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_32);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_15);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_15);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_19);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_19);
        listPositionXCaseRoulette.add(POSITION_X_CASE_ROULETTE_4);
        listPositionYCaseRoulette.add(POSITION_Y_CASE_ROULETTE_4);

    }

    /** Action pour poser un jeton ou modifier sa position ou sa valeur **/
    public void choosePositionToken(MouseEvent mouseEvent) {
        if(!startingGame) {
            buttonStartingGame.setVisible(false);
            labelError.setVisible(false);
            int mousePositionX = (int) (mouseEvent.getX());
            int mousePositionY = (int) (mouseEvent.getY());

            if (mouseEvent.getButton() == MouseButton.PRIMARY) { //clique gauche
                if (isPositionMouseGood(mousePositionX, mousePositionY)) {
                    setPositionToken(mousePositionX, mousePositionY, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), true);
                    betToken(mousePositionX, mousePositionY, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed));
                }
            }
            if (mouseEvent.getButton() == MouseButton.SECONDARY) { //clique droit
                indexTokenRemove = getTokenToRemove(mousePositionX, mousePositionY);
                if (indexTokenRemove > -1) {
                    if (tokenUsed > 0) {
                        tokenUsed--;
                    }

                    labelInformationBetToken.setText("Mise d'un jeton  \n" + "Cases sélectionnées : \n" + listOfTokenUsed.get(indexTokenRemove).getCases() + "\n Cases misées : \n" + getCasesBet(listOfTokenUsed.get(indexTokenRemove).getListOfCaseToken(), listOfTokenUsed.get(indexTokenRemove).getCircleToken())); //recup combinaison avec methode
                    textBetToken.setText(listOfTokenUsed.get(indexTokenRemove).getValueOfBet());
                    textBetToken.setVisible(true);
                    labelInformationBetToken.setVisible(true);
                    buttonModifyBetToken.setVisible(true);
                }
            }
        }
    }

    /** Returne les cases de la combinaison à partir de la position du jeton **/
    public String getCasesBet(List<Case> listOfCaseToken, Circle circleToken){
        if(listOfCaseToken.size() == 0){
            return "aucune case sélectionnée";
        }
        if(listOfCaseToken.size() == 1){
            switch(listOfCaseToken.get(0).getValueCase()){
                case "1st" : return "1;4;7;10;13;16;19;22;25;28;31;34";
                case "2nd" : return "2;5;8;11;14;17;20;23;26;29;32;35";
                case "3rd" : return "3;6;9;12;15;18;21;24;27;30;33;36";
                case "red" : return "1;3;5;7;9;12;14;16;18;21;23;25;27;30;32;34;36";
                case "black" : return "2;4;6;8;10;11;13;15;17;20;22;24;26;28;29;31;33;35";
                case "1-12" : return "de 1 à 12";
                case "13-24" : return "de 13 à 24";
                case "25-36" : return "de 25 à 36";
                case "even" : return "2;4;6;8;10;12;14;16;19;20;22;24;26;28;30;32;34;36";
                case "odd" : return "1;3;5;7;9;11;13;15;17;19;21;23;25;27;29;31;33;35";
                case "1-18" : return "de 1 à 18";
                case "19-36" : return "de 19 à 36";
                default:
                    if(( circleToken.getLayoutY() - circleToken.getRadius()) <= ORIGIN_Y_1){
                        String listCase = "";
                        for(Case cases : getCombinaisonCase(listOfCaseToken.get(0))){
                            listCase = listCase + cases.getValueCase() + ";";
                        }
                        return listCase.substring(0,listCase.length() - 1);
                    }
                    return listOfCaseToken.get(0).getValueCase();
            }
        }
        if(listOfCaseToken.size() == 2){
            switch (listOfCaseToken.get(0).getValueCase()){
                case "1" :
                case "4" :
                case "7" :
                case "10" :
                case "13" :
                case "16" :
                case "19" :
                case "22" :
                case "25" :
                case "28" :
                case "31" :
                case "34" :
                    if((circleToken.getLayoutY() + circleToken.getRadius()) >= END_Y_1){
                        String listCase = "";
                        for(Case cases : getCombinaisonCase(listOfCaseToken.get(0))){
                            listCase = listCase + cases.getValueCase() + ";";
                        }
                        return listCase.substring(0,listCase.length() - 1);
                    }
                    else {
                        if(listOfCaseToken.get(1).getValueCase().equals("1st")){
                            return "1;4;7;10;13;16;19;22;25;28;31;34";
                        }
                        break;
                    }
                case "1-12" :
                    switch (listOfCaseToken.get(1).getValueCase()){
                        case "1-18" :
                        case "even" :
                            buttonValidBetToken.setVisible(false);
                            buttonModifyBetToken.setVisible(false);
                            textBetToken.setVisible(false);
                            return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
                        case "13-24" :
                            return "de 1 à 24";
                    }
                case "13-24" :
                    switch (listOfCaseToken.get(1).getValueCase()){
                        case "red" :
                        case "black" :
                            buttonValidBetToken.setVisible(false);
                            buttonModifyBetToken.setVisible(false);
                            textBetToken.setVisible(false);
                            return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
                        case "25-36" :
                            return "de 13 à 36";
                    }
                case "25-35" :
                    switch (listOfCaseToken.get(1).getValueCase()){
                        case "odd" :
                        case "19-36" :
                            buttonValidBetToken.setVisible(false);
                            buttonModifyBetToken.setVisible(false);
                            textBetToken.setVisible(false);
                            return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
                    }
                case "1st" :
                    if(listOfCaseToken.get(1).getValueCase().equals("2nd")){
                        return "1;4;7;10;13;16;19;22;25;28;31;34 \n" + "2;5;8;11;14;17;20;23;26;29;32;35";
                    }
                   break;
                case "2nd" :
                    if(listOfCaseToken.get(1).getValueCase().equals("3rd")){
                        return "2;5;8;11;14;17;20;23;26;29;32;35 \n" + "3;6;9;12;15;18;21;24;27;30;33;36";
                    }
                    break;
                case "36" :
                    if(listOfCaseToken.get(1).getValueCase().equals("3rd")) {
                        return "3;6;9;12;15;18;21;24;27;30;33;36";
                    }
                    break;
                case "35" :
                    if(listOfCaseToken.get(1).getValueCase().equals("2nd")) {
                        return "2;5;8;11;14;17;20;23;26;29;32;35";
                    }
                    break;
            }
            return listOfCaseToken.get(0).getValueCase() + ";" + listOfCaseToken.get(1).getValueCase();
        }
        if(listOfCaseToken.size() == 3){
            buttonValidBetToken.setVisible(false);
            buttonModifyBetToken.setVisible(false);
            textBetToken.setVisible(false);
            return "Cette combinaison de case est impossible";
        }

        if(listOfCaseToken.size() == 4){
            if(listOfCaseToken.get(2).getValueCase().equals("2nd") && listOfCaseToken.get(3).getValueCase().equals("3rd")){
                return "2;5;8;11;14;17;20;23;26;29;32;35 \n" + "3;6;9;12;15;18;21;24;27;30;33;36";
            }
            if(listOfCaseToken.get(2).getValueCase().equals("1st") && listOfCaseToken.get(3).getValueCase().equals("2nd")){
                return "1;4;7;10;13;16;19;22;25;28;31;34 \n" + "2;5;8;11;14;17;20;23;26;29;32;35";
            }
            return listOfCaseToken.get(0).getValueCase()+";"+listOfCaseToken.get(1).getValueCase()+";"+listOfCaseToken.get(2).getValueCase()+";"+listOfCaseToken.get(3).getValueCase();
        }
        return "aucune case sélectionnée";
    }

    /** Retourne l'indice d'un jeton à modifier **/
    public int getTokenToRemove(int positionX, int positionY){
        for(int index = 0; index < tokenUsed; index ++){
            int distance = (int) Math.sqrt(Math.pow(Math.abs((positionX - listOfTokenUsed.get(index).getCircleToken().getLayoutX())) ,2) + Math.pow(Math.abs((positionY - listOfTokenUsed.get(index).getCircleToken().getLayoutY())) ,2));
            if(distance <= listOfTokenUsed.get(index).getCircleToken().getRadius()){
                return index;
            }
        }
        return -1;
    }

    /** Modifie la position d'un jeton **/
    public void setPositionToken(int positionX, int positionY, Circle circleToken, Label labelToken, boolean state){
        circleToken.setLayoutX(positionX);
        circleToken.setLayoutY(positionY);
        circleToken.setVisible(state);
        labelToken.setLayoutX(positionX - 10);
        labelToken.setLayoutY(positionY - 10);
        labelToken.setVisible(state);
    }

    /** Vérifie la zone pour poser un jeton **/
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

    /** Récupère toutes les informations du jeton posé **/
    public void betToken(int positionXToken, int positionYToken, Circle circleToken, Label labelToken){
        List<Case> listOfCaseToken = new ArrayList<>();
        for(int index = 0; index < listOfCase.size(); index ++){
            if(tokenInTheCase(listOfCase.get(index),positionXToken,positionYToken)){
                listOfCaseToken.add(listOfCase.get(index));
            }
        }
        InformationTokenBet informationTokenBet = new InformationTokenBet(circleToken,labelToken,listOfCaseToken);
        if(listOfTokenUsed.size() >= (tokenUsed + 1)){
            if(listOfTokenUsed.size() == 0){
                listOfTokenUsed.add(informationTokenBet);
            }
            else {
                listOfTokenUsed.set(tokenUsed, informationTokenBet);
            }
        }
        else {
            listOfTokenUsed.add(informationTokenBet);
        }
        labelInformationBetToken.setVisible(true);
        buttonValidBetToken.setVisible(true);
        textBetToken.setText("0");
        textBetToken.setVisible(true);

        labelInformationBetToken.setText("Mise d'un jeton  \n"+ "Cases sélectionnées : \n" + informationTokenBet.getCases() + "\n Cases misées : \n"+getCasesBet(informationTokenBet.getListOfCaseToken(),circleToken)); //recup combinaison avec methode
    }

    /** Vérifie si un jeton est passé sur une case **/
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

    /** Bouton pour valider la modification d'un jeton **/
    public void modifyBetToken(MouseEvent mouseEvent) {
        if(!textBetToken.getText().isEmpty()) {
            try {
                int valueOfBet = Integer.parseInt(textBetToken.getText());
                if (valueOfBet <= 0) {
                    if (indexTokenRemove >= 0) {
                        setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(indexTokenRemove), listLabelToken.get(indexTokenRemove), false);
                        listOfTokenUsed.remove(indexTokenRemove);
                        listLabelToken.get(indexTokenRemove).setText("0");
                        Label labelToken = listLabelToken.get(indexTokenRemove);
                        Circle circleToken = listOfCircleToken.get(indexTokenRemove);
                        listLabelToken.remove(indexTokenRemove);
                        listOfCircleToken.remove(indexTokenRemove);
                        listLabelToken.add(labelToken);
                        listOfCircleToken.add(circleToken);
                    }
                } else {
                    listOfTokenUsed.get(indexTokenRemove).setValueOfBet(textBetToken.getText());
                    listLabelToken.get(indexTokenRemove).setText(textBetToken.getText());
                    tokenUsed++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            tokenUsed++;
        }

        buttonModifyBetToken.setVisible(false);
        textBetToken.setVisible(false);
        labelInformationBetToken.setVisible(false);
        buttonStartingGame.setVisible(true);
    }

    /** Boutton pour valider un nouveau jeton posé  **/
    public void validBetToken(MouseEvent mouseEvent){
        if(!textBetToken.getText().isEmpty()) {
            try {
                int valueOfBet = Integer.parseInt(textBetToken.getText());
                if (valueOfBet <= 0) {
                    setPositionToken(ORIGIN_X_TOKEN, ORIGIN_Y_TOKEN, listOfCircleToken.get(tokenUsed), listLabelToken.get(tokenUsed), false);
                    if (tokenUsed >= 0 && listOfTokenUsed.size() > 0) {
                        listOfTokenUsed.remove(tokenUsed);
                        listLabelToken.get(tokenUsed).setText("0");
                    }
                } else {
                    listOfTokenUsed.get(tokenUsed).setValueOfBet(textBetToken.getText());
                    listLabelToken.get(tokenUsed).setText(textBetToken.getText());
                    tokenUsed++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
            buttonValidBetToken.setVisible(false);
            textBetToken.setVisible(false);
            labelInformationBetToken.setVisible(false);
            buttonStartingGame.setVisible(true);
        }
        else {
            textBetToken.setText("0");
            validBetToken(mouseEvent);
        }
    }

    /** Retourne la case qui possède une certaine valeur **/
    public Case getCase(String valueOfCase){
        for(int index = 0; index < listOfCase.size(); index ++){
            if(listOfCase.get(index).getValueCase().equals(valueOfCase)){
                return listOfCase.get(index);
            }
        }
        return null;
    }

    /** Retourne les autres cases d'une combinaisons d'après une case **/
    public List<Case> getCombinaisonCase(Case cases){
        List<Case> listOfCase = new ArrayList<>();

        switch (cases.getValueCase()){
            case "1" :
                listOfCase.add(cases);
                listOfCase.add(getCase("2"));
                listOfCase.add(getCase("3"));
                break;
            case "3" :
                listOfCase.add(getCase("1"));
                listOfCase.add(getCase("2"));
                listOfCase.add(cases);
                break;
            case "4" :
                listOfCase.add(cases);
                listOfCase.add(getCase("5"));
                listOfCase.add(getCase("6"));
                break;
            case "6" :
                listOfCase.add(getCase("4"));
                listOfCase.add(getCase("5"));
                listOfCase.add(cases);
                break;
            case "7" :
                listOfCase.add(cases);
                listOfCase.add(getCase("8"));
                listOfCase.add(getCase("9"));
                break;
            case "9" :
                listOfCase.add(getCase("7"));
                listOfCase.add(getCase("8"));
                listOfCase.add(cases);
                break;
            case "10" :
                listOfCase.add(cases);
                listOfCase.add(getCase("11"));
                listOfCase.add(getCase("12"));
                break;
            case "12" :
                listOfCase.add(getCase("10"));
                listOfCase.add(getCase("11"));
                listOfCase.add(cases);
                break;
            case "13" :
                listOfCase.add(cases);
                listOfCase.add(getCase("14"));
                listOfCase.add(getCase("15"));
                break;
            case "15" :
                listOfCase.add(getCase("13"));
                listOfCase.add(getCase("14"));
                listOfCase.add(cases);
                break;
            case "16" :
                listOfCase.add(cases);
                listOfCase.add(getCase("17"));
                listOfCase.add(getCase("18"));
                break;
            case "18" :
                listOfCase.add(getCase("16"));
                listOfCase.add(getCase("17"));
                listOfCase.add(cases);
                break;
            case "19" :
                listOfCase.add(cases);
                listOfCase.add(getCase("20"));
                listOfCase.add(getCase("21"));
                break;
            case "21" :
                listOfCase.add(getCase("19"));
                listOfCase.add(getCase("20"));
                listOfCase.add(cases);
                break;
            case "22" :
                listOfCase.add(cases);
                listOfCase.add(getCase("23"));
                listOfCase.add(getCase("24"));
                break;
            case "24" :
                listOfCase.add(getCase("22"));
                listOfCase.add(getCase("23"));
                listOfCase.add(cases);
                break;
            case "25" :
                listOfCase.add(cases);
                listOfCase.add(getCase("26"));
                listOfCase.add(getCase("27"));
                break;
            case "27" :
                listOfCase.add(getCase("25"));
                listOfCase.add(getCase("26"));
                listOfCase.add(cases);
                break;
            case "28" :
                listOfCase.add(cases);
                listOfCase.add(getCase("29"));
                listOfCase.add(getCase("30"));
                break;
            case "30" :
                listOfCase.add(getCase("28"));
                listOfCase.add(getCase("29"));
                listOfCase.add(cases);
                break;
            case "31" :
                listOfCase.add(cases);
                listOfCase.add(getCase("32"));
                listOfCase.add(getCase("33"));
                break;
            case "33" :
                listOfCase.add(getCase("31"));
                listOfCase.add(getCase("32"));
                listOfCase.add(cases);
                break;
            case "34" :
                listOfCase.add(cases);
                listOfCase.add(getCase("35"));
                listOfCase.add(getCase("36"));
                break;
            case "36" :
                listOfCase.add(getCase("34"));
                listOfCase.add(getCase("35"));
                listOfCase.add(cases);
                break;
        }
        return listOfCase;
    }

    /** Boutton pour lancer la roulette et mettre fin aux mises **/
    public void startingGame(MouseEvent mouseEvent) {
        if(tokenUsed == 0){
            labelError.setText("Il faut placer un jeton au minimum");
            labelError.setVisible(true);
        }
        else{
            startingGame = true;
            labelError.setVisible(false);
            buttonStartingGame.setVisible(false);

            //TranslateTransition translateTransition = new TranslateTransition();
           // translateTransition.setDuration(Duration.seconds(1));
            //translateTransition.setNode(circleBallRoulette);

            Circle circle = new Circle(140);
            PathTransition transition = new PathTransition();
            transition.setNode(circleBallRoulette);
            transition.setDuration(Duration.seconds(2));
            transition.setPath(circle);
            transition.setCycleCount(PathTransition.INDEFINITE);
            circleBallRoulette.setVisible(true);
            transition.play();
        }
    }
}
