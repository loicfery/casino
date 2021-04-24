package games;

import java.util.List;

public class Bet {
    private int valueOfBetTotal;
    private List<Integer> listOfBetUser;
    private List<User> listOfUser;

    public Bet(int valueOfBetTotal, List<Integer> listOfBetUser, List<User> listOfUser) {
        this.valueOfBetTotal = valueOfBetTotal;
        this.listOfBetUser = listOfBetUser;
        this.listOfUser = listOfUser;
    }

    public void addBet(int valueOfBet, User user){

    }

    public void removeBet(int valueOfBet, User user){

    }

    public void addUser(User user){

    }
}
