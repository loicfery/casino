package games;

import java.util.List;

public class ActionHit implements ActionBlackJack{
    @Override
    public void action(List<UserHand> ListOfUserHand, Cards_Package cards_package, Bet bet) {
        ListOfUserHand.get(1).addCard(cards_package);
    }
}
