package games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Cards_Package {
    private ArrayList<Cards> card_package;

    public Cards_Package(ArrayList<Cards> card_package) {
        this.card_package = card_package;
    }

    public boolean empty_package(ArrayList<Cards> card_package){ //Vérifie si le paquet est vide
        if(card_package.isEmpty()){
            return true;
        }
        return false;
    }

    public void create_package(ArrayList<Cards> card_package){ //Créer un packet trié (à supprimer si inutile)
        if(empty_package(card_package)){
            for(int i =0; i<12; i++){
                Cards heart = new Cards(i, "HEART");
                card_package.add(heart);
                Cards tile = new Cards(i, "TILE");
                card_package.add(tile);
                Cards spade = new Cards(i, "SPADE");
                card_package.add(spade);
                Cards clover = new Cards(i, "CLOVER");
                card_package.add(clover);
            }
        }


    }

    public void mix_package(ArrayList<Cards> card_package){ // "mélange" le packet (croupier avantageux) A compléter
        Collections.shuffle(card_package);
    }
}
