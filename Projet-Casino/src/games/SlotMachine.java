package games;

import java.util.ArrayList;

public class SlotMachine {
    private String[] slot1 = {"LEMON","WATERMELON","CHERRY","WATERMELON","SEVEN","WATERMELON","LEMON","WATERMELON","LEMON","WATERMELON","CHERRY","LEMON"};
    private String results[] = new String[3];
    private Bet bet;
    private ArrayList<String> image; //Les différentes images de la machine, tenter de faire un enum {7, CERISE, ROND...} (image au choix)
    private enum SlotImages {
        SEVEN,
        CHERRY,
        WATERMELON,
        LEMON;
    };


    public SlotMachine(){


    }; // A modifier quand l'enum sera fait

    public void userBet(int valueOfBet){
    };

    public void useSlotMachine(){};

    public void verifySlot(){};

    public void giveTokenBet(User user){

    };
}