package games;

import java.util.ArrayList;

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

    public void sorted_package(ArrayList<Cards> card_package){ //Créer un packet trié (à supprimer si inutile)

    }

    public void mix_package(ArrayList<Cards> card_package){ // "mélange" le packet (croupier avantageux)
        return;
    }
}
