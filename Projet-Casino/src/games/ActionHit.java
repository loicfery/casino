package games;

import java.util.List;

public class ActionHit implements ActionBlackJack{

    int currentHand;

    public ActionHit(int currentHand){
        this.currentHand = currentHand;
    }
    @Override
    public void action(List<UserHand> ListOfUserHand, Cards_Package cards_package, Bet bet) {
        ListOfUserHand.get(currentHand).addCard(cards_package);
    }
}
