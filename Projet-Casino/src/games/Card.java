package games;

public class Card {

    private String rank;
    private int number;
    private String color;

    public Card(int number, String rank) {
        this.number = number;
        this.rank = rank;

    }

    public int getNumber() {
        return number+1;
    }
    public String getRank(){return rank;}
}
