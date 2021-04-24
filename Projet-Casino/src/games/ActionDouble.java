package games;

public class ActionDouble implements ActionBlackJack {
    private Bet bet;
    @Override
    public void action(UserHand userHand, Cards_Package cards_package) {
        bet.setValueOfBetTotal(bet.getValueOfBetTotal()*2);
        userHand.addCard(cards_package);
    }
}
