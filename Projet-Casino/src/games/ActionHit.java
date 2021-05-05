package games;

import java.util.List;

public class ActionHit implements ActionBlackJack{

    int currentHand;

    public ActionHit(int currentHand){
        this.currentHand = currentHand;
    }
    @Override
    public void action(List<UserHand> listOfUserHand, Cards_Package cards_package, Bet bet) {
        listOfUserHand.get(currentHand).addCard(cards_package);
        System.out.println("currentIndex = "+currentHand);
        System.out.println(listOfUserHand.get(currentHand).getHand().get(listOfUserHand.get(currentHand).getHand().size() - 1).getNumber());
        System.out.println();
    }
}
