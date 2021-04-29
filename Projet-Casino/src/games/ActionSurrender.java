package games;

public class ActionSurrender implements ActionBlackJack {
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet, User user) {
        int surrender  = bet.getBet(user);
        bet.addBet(surrender, user);
    }
}
