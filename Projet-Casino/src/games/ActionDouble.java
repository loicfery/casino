package games;

public class ActionDouble implements ActionBlackJack {
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet, User user) {
        int actiondouble  = bet.getBet(user);
        bet.addBet(actiondouble, user);
        userHand.addCard(cards_package);
    }
}
