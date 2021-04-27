package games;

public class ActionSurrender implements ActionBlackJack {
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet) {
        bet.setValueOfBetTotal(bet.getValueOfBetTotal()/2);
    }
}
