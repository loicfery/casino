package games;

import java.util.List;

public class ActionSurrender implements ActionBlackJack {
    @Override
    public void action(List<UserHand> ListOfUserHand, CardPackage card_package, Bet bet) {
        if(bet.getBet(ListOfUserHand.get(1).getUser()) != 0) {
            int surrender =  bet.getBet(ListOfUserHand.get(1).getUser()) / 2;
            ListOfUserHand.get(1).getUser().addToken(surrender);
            bet.removeBet(bet.getBet(ListOfUserHand.get(1).getUser()), ListOfUserHand.get(1).getUser());
        }
        else{
            int surrender =  bet.getBet(ListOfUserHand.get(2).getUser()) / 2;
            ListOfUserHand.get(1).getUser().addToken(surrender);
            bet.removeBet(bet.getBet(ListOfUserHand.get(2).getUser()), ListOfUserHand.get(2).getUser());
        }
    }
}
