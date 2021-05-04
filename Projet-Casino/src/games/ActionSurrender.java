package games;

import java.util.List;

public class ActionSurrender implements ActionBlackJack {
    @Override
    public void action(List<UserHand> ListOfUserHand, Cards_Package cards_package, Bet bet) {
        int surrender = (int) bet.getBet(ListOfUserHand.get(1).getUser()) / 2;
        ListOfUserHand.get(1).getUser().removeToken(surrender);
    }
}
