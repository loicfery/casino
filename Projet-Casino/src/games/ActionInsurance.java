package games;

public class ActionInsurance implements ActionBlackJack {
    @Override
    public void action(UserHand userHand, Cards_Package cards_package, Bet bet, User user) {
        int insurrance = bet.getBet(user) / 2;


    }
}
