package games;

public class ActionHit implements ActionBlackJack{
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet, User user) {
        userHand.addCard(cards_package);
    }
}
