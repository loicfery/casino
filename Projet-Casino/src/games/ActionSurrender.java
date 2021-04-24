package games;

public class ActionSurrender implements ActionBlackJack {
    private Bet bet;
    @Override
    public void action(UserHand userHand, Cards_Package cards_package) {
        bet.setValueOfBetTotal(bet.getValueOfBetTotal()/2);
    }
}
