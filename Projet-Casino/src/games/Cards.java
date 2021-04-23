package games;

import java.util.ArrayList;

public class Cards {

    private String sign; // essayer de faire un enum pour {HEART, CLOVER, SPADES, TILE}
    private int number; // 1 à 10 normale, 11 J / 12 Q / 13 K
    private ArrayList<Cards> card_package; // Type provisoire, à adapter au besoin

    public Cards(String sign, int number) { // A modifier quand l'enum sera fait
        this.sign = sign;
        this.number = number;
    }

    public boolean empty_package(){ //Vérifie si le paquet est vide
        return true;
    }

    public void sorted_package(ArrayList<Cards> card_package){ //Créer un packet trié (à supprimer si inutile)
        return;
    }

    public void mix_package(ArrayList<Cards> card_package){ // "mélange" le packet (croupier avantageux)
        return;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
