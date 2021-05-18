package games;

import java.util.ArrayList;
import java.util.Random;

import static javax.swing.Action.DEFAULT;

public class SlotMachine {

    private String[] slot = {"LEMON","WATERMELON","CHERRY","WATERMELON","SEVEN","WATERMELON","LEMON","WATERMELON","CHERRY","LEMON"};
    /**
     * tableau des trois images tirées
     * **/
    private String results[] = new String[3];



    /**
     * mise
     * **/
    private Bet bet;
    private User user;
    /**
     * Les différentes images de la machine
     * **/
    private ArrayList<Integer> nbImage;

    public SlotMachine(User user){
        this.user = user;
        bet = new Bet();
        nbImage = new ArrayList<>();
    };

    public SlotMachine(){
        bet = new Bet();
        nbImage = new ArrayList<>();
    };

    public void setResults(String[] results) {
        this.results = results;
    }

    /**
     * Mise du joueur, un seul jeton à la fois
     * **/
    public void userBet(User user){
        bet.addBet(1,user);
    }

    /**
     * utilisation de la machine à sous
     * **/
    public void useSlotMachine(){
        //a completer
        user.removeToken(1);
        Random random = new Random();
        int nb;
        for (int i =0; i < 3;i++ ){
            nb = random.nextInt(10);
            results[i] = slot[nb];
            nbImage.add(nb);
        }
        giveTokenBet(user);
    }

    /**
     * vérification des resultats
     * **/
    public int verifySlot(){
        if(results[0].equals(results[1]) && results[0].equals(results[2])) {
            switch (results[0]){
                case "SEVEN":
                    return 160;
                case "CHERRY":
                    return 25;
                case "LEMON":
                    return 8;
                case "WATERMELON":
                    return 4;
                default:
                    return 0;
            }
        }
        else{return 0;}
    }

    /**
     * gain de l'utilisateur
     * 160 pour 3 SEVEN, 25 pour 3 CHERRY, 8 pour 3 LEMON, 4 pour 3 WATERMELON, 0 sinon
     * **/
    public void giveTokenBet(User user){
        int gain = verifySlot();
        user.addToken(gain);
    }

    public ArrayList<Integer> getNbImage() {
        return nbImage;
    }

    public void reset(){
        bet.removeBet(bet.getBet(user), user);
        nbImage = new ArrayList<>();
    }

    public Bet getBet() {
        return bet;
    }
}