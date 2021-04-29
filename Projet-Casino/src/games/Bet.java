package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bet {
    private int valueOfBetTotal;
    private List<Integer> listOfBetUser;
    private List<User> listOfUser;

    public Bet() {
        this.valueOfBetTotal = 0;
        this.listOfBetUser = new ArrayList<>();
        this.listOfUser = new ArrayList<>();
    }

    public void addBet(int valueOfBet, User user){
        for(int index = 0; index < listOfUser.size(); index ++){
            if(listOfUser.get(index) == user){
                listOfBetUser.set(index,listOfBetUser.get(index) + valueOfBet);
            }
        }
        setValueOfBetTotal(getValueOfBetTotal() + valueOfBet);
    }

    public void removeBet(int valueOfBet, User user){
        for(int index = 0; index < listOfUser.size(); index ++){
            if(listOfUser.get(index) == user){
                listOfBetUser.set(index,listOfBetUser.get(index) - valueOfBet);
            }
        }
        setValueOfBetTotal(getValueOfBetTotal() - valueOfBet);
    }

    public void resetBet(){setValueOfBetTotal(0);}

    public void addUser(User user){
        listOfUser.add(user);
        listOfBetUser.add(0);
    }

    public void removeUser(User user){
        for(int index = 0; index < listOfUser.size(); index ++){
            if(listOfUser.get(index) == user){
                listOfUser.remove(index);
                listOfBetUser.remove(index);
            }
        }
    }

    public int getValueOfBetTotal() {
        return valueOfBetTotal;
    }

    public void setValueOfBetTotal(int valueOfBetTotal) {
        this.valueOfBetTotal = valueOfBetTotal;
    }

    public int getBet(User user){
        for(int i=0; i<listOfUser.size();i++){
            if(user == listOfUser.get(i)){
                return listOfBetUser.get(i);
            }
        }
        return 0;
    }


}
