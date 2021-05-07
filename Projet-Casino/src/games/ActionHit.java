package games;

import java.util.List;

public class ActionHit implements ActionBlackJack{

    int currentHand;

    public ActionHit(int currentHand){
        this.currentHand = currentHand;
    }
    @Override
    public void action(List<UserHand> listOfUserHand, CardPackage card_package, Bet bet) {
        listOfUserHand.get(currentHand).addCard(card_package);
    }
}
