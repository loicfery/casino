package games;

import java.util.ArrayList;
import java.util.Collections;

public class Cards_Package {
    private ArrayList<Cards> card_package;
    private int numberOfCard;

    public Cards_Package(ArrayList<Cards> card_package, int numberOfCard) {
        this.card_package = card_package;
        this.numberOfCard = numberOfCard;
    }

    public void initCardPackage(ArrayList<Cards> card_package){ //Créer un packet trié (à supprimer si inutile)
        if(card_package.isEmpty()){
            for(int i =0; i<12; i++){
                Cards heart = new Cards(i, "HEART", "RED");
                card_package.add(heart);
                Cards tile = new Cards(i, "TILE", "RED");
                card_package.add(tile);
                Cards spade = new Cards(i, "SPADE", "BLACK");
                card_package.add(spade);
                Cards clover = new Cards(i, "CLOVER", "BLACK");
                card_package.add(clover);
            }
        }


    }

    public void mixCardPackage(ArrayList<Cards> card_package){ // "mélange" le packet (croupier avantageux) A compléter
        Collections.shuffle(card_package);
    }

    public void removeCard(int cardPosition){
        card_package.remove(cardPosition);
    }

    public Cards drawCard(){
        Cards c = card_package.get(0);
        removeCard(0);
        return c;
    }
}
