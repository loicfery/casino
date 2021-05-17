package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bet {
    private List<Integer> listOfBetUser;
    private List<User> listOfUser;

    public Bet() {
        this.listOfBetUser = new ArrayList<>();
        this.listOfUser = new ArrayList<>();
    }

    public Bet(List<User> listOfUser){
        this.listOfUser = listOfUser;
        this.listOfBetUser = new ArrayList<>();
    }

    public void addBet(int valueOfBet, User user){
        for(int index = 0; index < listOfUser.size(); index ++){
            if(listOfUser.get(index) == user){
                listOfBetUser.set(index,listOfBetUser.get(index) + valueOfBet);
            }
        }
    }

    public void removeBet(int valueOfBet, User user){
        for(int index = 0; index < listOfUser.size(); index ++){
            if(listOfUser.get(index) == user){
                listOfBetUser.set(index,listOfBetUser.get(index) - valueOfBet);
            }
        }

    }

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

    public int getBet(User user){
        for(int i=0; i<listOfUser.size();i++){
            if(user == listOfUser.get(i)){
                return listOfBetUser.get(i);
            }
        }
        return 0;
    }
}
