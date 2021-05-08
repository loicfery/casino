package games;

import java.util.ArrayList;
import java.util.Collections;

public class CardPackage {
    private ArrayList<Card> listOfCard;

    public CardPackage() {}

    public void initCardPackage(){ //Créer un packet trié (à supprimer si inutile)
        listOfCard = new ArrayList<>();

        for(int i =0; i<12; i++){
            Card heart = new Card(i, "HEART");
            listOfCard.add(heart);
            Card tile = new Card(i, "SQUARE");
            listOfCard.add(tile);
            Card spade = new Card(i, "SPADE");
            listOfCard.add(spade);
            Card clover = new Card(i, "CLOVER");
            listOfCard.add(clover);
        }
    }

    public void mixCardPackage(){ // "mélange" le packet (croupier avantageux) A compléter
        Collections.shuffle(listOfCard);
    }

    public void removeCard(int cardPosition){
        listOfCard.remove(cardPosition);
    }

    public Card drawCard(){
        Card c = listOfCard.get(0);
        removeCard(0);
        return c;
    }

    public ArrayList<Card> getCardPackage() {
        return listOfCard;
    }


}