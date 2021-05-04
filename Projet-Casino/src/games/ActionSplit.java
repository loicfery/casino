package games;

import java.util.List;

public class ActionSplit implements ActionBlackJack {
    @Override
    public void action(List<UserHand> ListOfUserHand, Cards_Package cards_package, Bet bet) {
        ListOfUserHand.get(2).getHand().set(0,ListOfUserHand.get(1).getHand().get(1));
        ListOfUserHand.get(1).removeCard(ListOfUserHand.get(1).getHand().get(1));
        bet.addUser(ListOfUserHand.get(2).getUser());
        bet.addBet(bet.getBet(ListOfUserHand.get(1).getUser()), ListOfUserHand.get(2).getUser());

    }
}
