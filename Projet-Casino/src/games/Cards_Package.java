package games;

import java.util.ArrayList;
import java.util.Collections;

public class Cards_Package {
    private ArrayList<Card> listOfCard;
    private final int numberOfCard = 52;

    public Cards_Package() {}

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

        /*if(card_package.isEmpty()){
            for(int i =0; i<12; i++){
                Card heart = new Card(i, "HEART");
                card_package.add(heart);
                Card tile = new Card(i, "SQUARE");
                card_package.add(tile);
                Card spade = new Card(i, "SPADE");
                card_package.add(spade);
                Card clover = new Card(i, "CLOVER");
                card_package.add(clover);
            }
        }*/
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

    public ArrayList<Card> getCard_package() {
        return listOfCard;
    }


}
