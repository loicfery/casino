package games;

public class ActionHit implements ActionBlackJack{
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet) {
        userHand.addCard(cards_package);
    }
}
