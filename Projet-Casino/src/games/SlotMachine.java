package games;

import java.util.ArrayList;
import java.util.Random;

import static javax.swing.Action.DEFAULT;

public class SlotMachine {
    private String[] slot = {"LEMON","WATERMELON","CHERRY","WATERMELON","SEVEN","WATERMELON","LEMON","WATERMELON","CHERRY","LEMON"};
    //bobine de la machine
    private String results[] = new String[3]; //tableau des trois images tirées
    private Bet bet; //mise
    private ArrayList<String> image; //Les différentes images de la machine, tenter de faire un enum {7, CERISE, ROND...} (image au choix)
    private enum SlotImages {
        SEVEN,
        CHERRY,
        WATERMELON,
        LEMON;
    };


    public SlotMachine(){
        bet = new Bet();
        image = new ArrayList<>();
    };

    public void userBet(int valueOfBet, User user){
        // a completer
        bet.addBet(valueOfBet,user);
    }; //Mise du joueur, un seul jeton à la fois

    public void useSlotMachine(){
        //a completer
        Random random = new Random();
        int nb;
        for (int i =0; i < 3;i++ ){
            nb = random.nextInt(10);
            results[i] = slot[nb];
            }


    }; //utilisation de la machine à sous

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


    }; // vérification des resultats

    public void giveTokenBet(User user){
        int gain = verifySlot();
        user.addToken(gain);

    }; //gain de l'utilisateur, 160 pour 3 SEVEN, 25 pour 3 CHERRY, 8 pour 3 LEMON, 4 pour 3 WATERMELON, 0 sinon
}