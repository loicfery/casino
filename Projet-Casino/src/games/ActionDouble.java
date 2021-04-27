package games;

public class ActionDouble implements ActionBlackJack {
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet) {
        bet.setValueOfBetTotal(bet.getValueOfBetTotal()*2);
        userHand.addCard(cards_package);
    }
}
