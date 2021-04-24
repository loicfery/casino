package games;

public class ActionDouble implements ActionBlackJack {
    @Override
    public void action(UserHand userHand, Cards_Package cards_package) {
        //BETx2...
        userHand.addCard(cards_package);
    }
}
