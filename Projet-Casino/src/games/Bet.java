package games;

import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        valueOfBet = scanner.nextInt();
        valueOfBetTotal = getValueOfBetTotal() + valueOfBet;

    }

    public void removeBet(int valueOfBet, User user){
        setValueOfBetTotal(0);
    }

    public void addUser(User user){
        listOfUser.add(user);
    }

    public int getValueOfBetTotal() {
        return valueOfBetTotal;
    }

    public void setValueOfBetTotal(int valueOfBetTotal) {
        this.valueOfBetTotal = valueOfBetTotal;
    }
}
