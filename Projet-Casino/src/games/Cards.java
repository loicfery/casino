package games;

import java.util.ArrayList;

public class Cards {

    private String rank;
    private int number; // 0 à 9 = 1 = 10, 10 = J, 11 = Q, 12 = K
    private String color;

    public Cards(int number, String rank, String color) { // A modifier quand l'enum sera fait
        this.number = number;
        this.rank = rank;
        this.color = color;

    }

}
