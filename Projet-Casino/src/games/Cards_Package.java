package games;

import java.util.ArrayList;
import java.util.Collections;

public class Cards_Package {
    private ArrayList<Card> card_package;
    private int numberOfCard;

    public Cards_Package(ArrayList<Card> card_package, int numberOfCard) {
        this.card_package = card_package;
        this.numberOfCard = numberOfCard;
    }

    public void initCardPackage(ArrayList<Card> card_package){ //Créer un packet trié (à supprimer si inutile)
        if(card_package.isEmpty()){
            for(int i =0; i<12; i++){
                Card heart = new Card(i, "HEART", "RED");
                card_package.add(heart);
                Card tile = new Card(i, "TILE", "RED");
                card_package.add(tile);
                Card spade = new Card(i, "SPADE", "BLACK");
                card_package.add(spade);
                Card clover = new Card(i, "CLOVER", "BLACK");
                card_package.add(clover);
            }
        }
    }

    public void mixCardPackage(ArrayList<Card> card_package){ // "mélange" le packet (croupier avantageux) A compléter
        Collections.shuffle(card_package);
    }

    public void removeCard(int cardPosition){
        card_package.remove(cardPosition);
    }

    public Card drawCard(){
        Card c = card_package.get(0);
        removeCard(0);
        return c;
    }
}
