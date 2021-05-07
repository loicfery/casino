package games;

import java.util.List;

public class ActionInsurance implements ActionBlackJack {
    @Override
    public void action(List<UserHand> ListOfUserHand, CardPackage card_package, Bet bet) {
        int insurrance = bet.getBet(ListOfUserHand.get(1).getUser()) / 2;
        bet.addUser(ListOfUserHand.get(3).getUser());
        bet.addBet(insurrance, ListOfUserHand.get(3).getUser());


    }
}
