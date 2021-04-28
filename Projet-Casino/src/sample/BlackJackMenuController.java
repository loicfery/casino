package sample;

import games.BlackJack;
import games.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class BlackJackMenuController {

    private final int BET_MIN = 2;
    private final int BET_MAX = 100;
    private final int ORIGIN_X_Croupier = 300;
    private final int ORIGIN_X_FIRST_HAND = 300;
    private final int ORIGIN_X_SECOND_HAND = 523;
    private final int ORIGIN_Y_USER = 400;
    private final int ORIGIN_Y_CROUPIER = 100;

    private Stage stage;
    private User user;
    private BlackJack blackJack;
    private List<ImageView> listCardUserFirstHand = new ArrayList<>();
    private List<ImageView> listCardUserSecondHand = new ArrayList<>();

    @FXML
    private Rectangle zoneBetUser1;
    @FXML
    private Rectangle zoneBetUser2;

    @FXML
    private Label labelToken;
    @FXML
    private Label labelPseudo;
    @FXML
    private Label labelError;
    @FXML
    private Label labelProfit;

    @FXML
    private TextField textBetUser;

    @FXML
    private Button buttonValidBet;

    /** premier Jeton **/
    @FXML
    private Circle token1Circle1;
    @FXML
    private Circle token1Circle2;
    @FXML
    private Rectangle token1Rectangle1;
    @FXML
    private Rectangle token1Rectangle2;
    @FXML
    private Rectangle token1Rectangle3;
    @FXML
    private Rectangle token1Rectangle4;
    @FXML
    private Polyline token1Polyline1;
    @FXML
    private Polyline token1Polyline2;
    @FXML
    private Polyline token1Polyline3;
    @FXML
    private Polyline token1Polyline4;
    @FXML
    private Circle token1Circle3;
    @FXML
    private Line token1Line1;
    @FXML
    private Line token1Line2;
    @FXML
    private Line token1Line3;
    @FXML
    private Label token1LabelBetUser;

    /** deuxième jeton **/
    @FXML
    private Circle token2Circle1;
    @FXML
    private Circle token2Circle2;
    @FXML
    private Rectangle token2Rectangle1;
    @FXML
    private Rectangle token2Rectangle2;
    @FXML
    private Rectangle token2Rectangle3;
    @FXML
    private Rectangle token2Rectangle4;
    @FXML
    private Polyline token2Polyline1;
    @FXML
    private Polyline token2Polyline2;
    @FXML
    private Polyline token2Polyline3;
    @FXML
    private Polyline token2Polyline4;
    @FXML
    private Circle token2Circle3;
    @FXML
    private Line token2Line1;
    @FXML
    private Line token2Line2;
    @FXML
    private Line token2Line3;
    @FXML
    private Label token2LabelBetUser;

    /** Toutes les cartes ***********************************************************/

    /** Tous les as **/
    @FXML
    private ImageView pictureAsOfHeart;
    @FXML
    private ImageView pictureAsOfClover;
    @FXML
    private ImageView pictureAsOfSpade;
    @FXML
    private ImageView pictureAsOfSquare;

    /** Tous les deux **/
    @FXML
    private ImageView pictureTwoOfHeart;
    @FXML
    private ImageView pictureTwoOfClover;
    @FXML
    private ImageView pictureTwoOfSpade;
    @FXML
    private ImageView pictureTwoOfSquare;

    /** Tous les trois **/
    @FXML
    private ImageView pictureThreeOfHeart;
    @FXML
    private ImageView pictureThreeOfSpade;
    @FXML
    private ImageView pictureThreeOfClover;
    @FXML
    private ImageView pictureThreeOfSquare;

    /** Tous les quatre **/
    @FXML
    private ImageView pictureFourOfHeart;
    @FXML
    private ImageView pictureFourOfSpade;
    @FXML
    private ImageView pictureFourOfClover;
    @FXML
    private ImageView pictureFourOfSquare;

    /** Tous les cinq **/
    @FXML
    private ImageView pictureFiveOfHeart;
    @FXML
    private ImageView pictureFiveOfSpade;
    @FXML
    private ImageView pictureFiveOfClover;
    @FXML
    private ImageView pictureFiveOfSquare;

    /** Tous les six **/
    @FXML
    private ImageView pictureSixOfHeart;
    @FXML
    private ImageView pictureSixOfSpade;
    @FXML
    private ImageView pictureSixOfClover;
    @FXML
    private ImageView pictureSixOfSquare;

    /** Tous les sept **/
    @FXML
    private ImageView pictureSevenOfHeart;
    @FXML
    private ImageView pictureSevenOfSpade;
    @FXML
    private ImageView pictureSevenOfClover;
    @FXML
    private ImageView pictureSevenOfSquare;

    /** Tous les huit **/
    @FXML
    private ImageView pictureEightOfHeart;
    @FXML
    private ImageView pictureEightOfSpade;
    @FXML
    private ImageView pictureEightOfClover;
    @FXML
    private ImageView pictureEightOfSquare;

    /** Tous les neuf **/
    @FXML
    private ImageView pictureNineOfHeart;
    @FXML
    private ImageView pictureNineOfSpade;
    @FXML
    private ImageView pictureNineOfClover;
    @FXML
    private ImageView pictureNineOfSquare;

    /** Tous les dix **/
    @FXML
    private ImageView pictureTenOfHeart;
    @FXML
    private ImageView pictureTenOfSpade;
    @FXML
    private ImageView pictureTenOfClover;
    @FXML
    private ImageView pictureTenOfSquare;

    /** Tous les valets **/
    @FXML
    private ImageView pictureJackOfHeart;
    @FXML
    private ImageView pictureJackOfSpade;
    @FXML
    private ImageView pictureJackOfClover;
    @FXML
    private ImageView pictureJackOfSquare;

    /** Toutes les reines **/
    @FXML
    private ImageView pictureQueenOfHeart;
    @FXML
    private ImageView pictureQueenOfSpade;
    @FXML
    private ImageView pictureQueenOfClover;
    @FXML
    private ImageView pictureQueenOfSquare;

    /** Tous les rois **/
    @FXML
    private ImageView pictureKingOfHeart;
    @FXML
    private ImageView pictureKingOfSpade;
    @FXML
    private ImageView pictureKingOfClover;
    @FXML
    private ImageView pictureKingOfSquare;

    public BlackJackMenuController(User user,Stage stage){
        this.user = user;
        this.stage = stage;
    }

    public void initialize(){
        labelToken.setText("Jetons : "+user.getNumberOfToken());
        labelPseudo.setText("Joueur : "+user.getPseudo());
    }

    public void clickValidBet(ActionEvent actionEvent) {
        if(textBetUser.getText().isEmpty()){
            labelError.setText("Il faut entrer une mise");
            labelError.setVisible(true);
        }
        else {
            try {
                int valueOfBet = Integer.parseInt(textBetUser.getText());

                if (valueOfBet < BET_MIN || valueOfBet > BET_MAX) {
                    labelError.setText("Vous ne pouvez miser qu'entre 2 et 100 jetons");
                    labelError.setVisible(true);
                } else {
                    int newTokenUser = user.getNumberOfToken() - valueOfBet;
                    if (newTokenUser < 0) {
                        labelError.setText("Vous n'avez pas assez de jeton");
                        labelError.setVisible(true);
                    } else {
                        labelError.setVisible(false);
                        // ajouter valueOfBet à bet dans la classe BlackJack
                        textBetUser.setVisible(false);
                        buttonValidBet.setVisible(false);

                        user.removeToken(valueOfBet);
                        token1LabelBetUser.setText("1"); // recup valeur de bet
                        labelToken.setText("Jetons : " + user.getNumberOfToken());

                        setToken1Visible(true);
                        startingGame();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ImageView chooseCard(int cardNumber, String cardRank){
        switch (cardNumber){
            case 1 :
                return getCardByRank(cardRank, pictureAsOfHeart, pictureAsOfSpade, pictureAsOfClover, pictureAsOfSquare);
            case 2 :
                return getCardByRank(cardRank, pictureTwoOfHeart, pictureTwoOfSpade, pictureTwoOfClover, pictureTwoOfSquare);
            case 3 :
                return getCardByRank(cardRank, pictureThreeOfHeart, pictureThreeOfSpade, pictureThreeOfClover, pictureThreeOfSquare);
            case 4 :
                return getCardByRank(cardRank, pictureFourOfHeart, pictureFourOfSpade, pictureFourOfClover, pictureFourOfSquare);
            case 5 :
                return getCardByRank(cardRank, pictureFiveOfHeart, pictureFiveOfSpade, pictureFiveOfClover, pictureFiveOfSquare);
            case 6 :
                return getCardByRank(cardRank, pictureSixOfHeart, pictureSixOfSpade, pictureSixOfClover, pictureSixOfSquare);
            case 7 :
                return getCardByRank(cardRank, pictureSevenOfHeart, pictureSevenOfSpade, pictureSevenOfClover, pictureSevenOfSquare);
            case 8 :
                return getCardByRank(cardRank, pictureEightOfHeart, pictureEightOfSpade, pictureEightOfClover, pictureEightOfSquare);
            case 9 :
                return getCardByRank(cardRank, pictureNineOfHeart, pictureNineOfSpade, pictureNineOfClover, pictureNineOfSquare);
            case 10 :
                return getCardByRank(cardRank, pictureTenOfHeart, pictureTenOfSpade, pictureTenOfClover, pictureTenOfSquare);
            case 11 :
                return getCardByRank(cardRank, pictureJackOfHeart, pictureJackOfSpade, pictureJackOfClover, pictureJackOfSquare);
            case 12 :
                return getCardByRank(cardRank, pictureQueenOfHeart, pictureQueenOfSpade, pictureQueenOfClover, pictureQueenOfSquare);
            case 13 :
                return getCardByRank(cardRank, pictureKingOfHeart, pictureKingOfSpade, pictureKingOfClover, pictureKingOfSquare);
            default: return null;
        }
    }

    private ImageView getCardByRank(String cardRank, ImageView cardOfHeart, ImageView cardOfSpade, ImageView cardOfClover, ImageView cardOfSquare) {
        switch (cardRank){
            case "HEART" :
                return cardOfHeart;
            case "SPADE" :
                return cardOfSpade;
            case "CLOVER" :
                return cardOfClover;
            case "SQUARE" :
                return cardOfSquare;
            default: return null;
        }
    }

    private void setUpCard(ImageView card, int positionX, int positionY){
        card.setLayoutX(positionX);
        card.setLayoutY(positionY);
        card.setVisible(true);
    }

    public void startingGame(){
        int positionXUser = ORIGIN_X_FIRST_HAND;
        int positionXCroupier = ORIGIN_X_Croupier;
        ImageView card;

        // test de fonctionnement
        card = chooseCard(1,"HEART");
        listCardUserFirstHand.add(card);
        setUpCard(card,positionXUser,ORIGIN_Y_USER);
        positionXUser += 50;

        setUpCard(chooseCard(8,"SPADE"),positionXCroupier,ORIGIN_Y_CROUPIER);
        positionXCroupier += 50;

        card = chooseCard(12,"HEART");
        listCardUserFirstHand.add(card);
        setUpCard(card,positionXUser,ORIGIN_Y_USER);
        positionXUser += 50;
    }

    public void setToken1Visible(boolean tokenState){
        token1Circle1.setVisible(tokenState);
        token1Circle2.setVisible(tokenState);
        token1Rectangle1.setVisible(tokenState);
        token1Rectangle2.setVisible(tokenState);
        token1Rectangle3.setVisible(tokenState);
        token1Rectangle4.setVisible(tokenState);
        token1Polyline1.setVisible(tokenState);
        token1Polyline2.setVisible(tokenState);
        token1Polyline3.setVisible(tokenState);
        token1Polyline4.setVisible(tokenState);
        token1Circle3.setVisible(tokenState);
        token1Line1.setVisible(tokenState);
        token1Line2.setVisible(tokenState);
        token1Line3.setVisible(tokenState);
        token1LabelBetUser.setVisible(true);
    }

    public void setToken2Visible(boolean tokenState){
        token2Circle1.setVisible(tokenState);
        token2Circle2.setVisible(tokenState);
        token2Rectangle1.setVisible(tokenState);
        token2Rectangle2.setVisible(tokenState);
        token2Rectangle3.setVisible(tokenState);
        token2Rectangle4.setVisible(tokenState);
        token2Polyline1.setVisible(tokenState);
        token2Polyline2.setVisible(tokenState);
        token2Polyline3.setVisible(tokenState);
        token2Polyline4.setVisible(tokenState);
        token2Circle3.setVisible(tokenState);
        token2Line1.setVisible(tokenState);
        token2Line2.setVisible(tokenState);
        token2Line3.setVisible(tokenState);
        token2LabelBetUser.setVisible(true);
    }

    public void actionSplit(ActionEvent actionEvent){
        zoneBetUser2.setVisible(true);
        setToken2Visible(true);
        token2LabelBetUser.setText(token1LabelBetUser.getText());

        listCardUserSecondHand.add(listCardUserFirstHand.get(listCardUserFirstHand.size() - 1));
        listCardUserFirstHand.remove(listCardUserFirstHand.size() - 1);
        setUpCard(listCardUserSecondHand.get(0),ORIGIN_X_SECOND_HAND,ORIGIN_Y_USER);
    }
}
