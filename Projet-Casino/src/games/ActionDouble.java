package games;

import java.util.List;

public class ActionDouble implements ActionBlackJack {
    @Override
    public void action(List<UserHand> ListOfUserHand, CardPackage card_package, Bet bet) {
        int actiondouble  = bet.getBet(ListOfUserHand.get(1).getUser());
        bet.addBet(actiondouble, ListOfUserHand.get(1).getUser());
        ListOfUserHand.get(1).addCard(card_package);
    }
}