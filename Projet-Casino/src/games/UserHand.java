package games;

import java.util.List;

public class UserHand  {
    private List<Cards> hand;
    private User user;


    public UserHand(List<Cards> hand, User user) {
        this.hand = hand;
        this.user = user;
    }

    public void addCard(Cards_Package cards_package){
        hand.add(cards_package.drawCard());
    }

    public void removeCard(Cards cards){
        hand.remove(cards);
    }

    public List<Cards> getHand() {
        return hand;
    }
}
