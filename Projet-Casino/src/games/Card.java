package games;

public class Card {

    private String rank;
    private int number;
    private String color;

    public Card(int number, String rank, String color) {
        this.number = number;
        this.rank = rank;
        this.color = color;

    }

    public int getNumber() {
        return number+1;
    }
    public String getRank(){return rank;}
    public String getColor(){return color;}
}
