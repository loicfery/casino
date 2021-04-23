package games;

import java.util.ArrayList;

public class Cards {

    private String sign;
    private int number; // 0 à 9 = 1 = 10, 10 = J, 11 = Q, 12 = K
    private ArrayList<Cards> card_package; // Type provisoire, à adapter au besoin

    public Cards(int number, String sign) { // A modifier quand l'enum sera fait
        this.number = number;
        this.sign = sign;

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
