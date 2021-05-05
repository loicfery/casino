package games;

import java.util.List;

public class ActionSplit implements ActionBlackJack {
    @Override
    public void action(List<UserHand> listOfUserHand, Cards_Package cards_package, Bet bet) {
        int splitBet = bet.getBet(listOfUserHand.get(1).getUser()) / 2;
        Card card = listOfUserHand.get(1).getHand().get(1);
        listOfUserHand.get(1).removeCard(card);

        User userSplit = new User(listOfUserHand.get(1).getUser().getPseudo(),listOfUserHand.get(1).getUser().getEmail(),listOfUserHand.get(1).getUser().getRank());
        listOfUserHand.add(new UserHand(userSplit));
        listOfUserHand.get(2).getHand().add(card);
        bet.addUser(userSplit);
        bet.removeBet(splitBet, listOfUserHand.get(1).getUser());
        bet.addBet(splitBet, listOfUserHand.get(2).getUser());

        //listOfUserHand.get(2).getHand().set(0,listOfUserHand.get(1).getHand().get(1));
        //listOfUserHand.get(1).removeCard(listOfUserHand.get(1).getHand().get(1));
        //bet.addUser(listOfUserHand.get(2).getUser());
        //bet.addBet(bet.getBet(listOfUserHand.get(1).getUser()), listOfUserHand.get(2).getUser());

    }
}
