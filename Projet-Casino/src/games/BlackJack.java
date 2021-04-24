package games;

import java.util.List;

public class BlackJack {

    private List<UserHand> ListOfUserHand;
    private Cards_Package cards_package;
    private Bet bet;

    public int countValueOfUserHand(UserHand userHand){
        int s=0;
        for (int i=0; i<userHand.getHand().size(); i++){
            if(userHand.getHand().get(i).getNumber() == 0 && s<11){
                s = s+11;
            }
            else {
                s = s + userHand.getHand().get(i).getNumber();
            }
        }
        return s;
    }

    public boolean verifyBlackJack(UserHand userHand){
        int s;
        s = countValueOfUserHand(userHand);
        if(s == 21){
            return true;
        }
        return false;
    }

    public void giveCardToUser(){

    }


}
