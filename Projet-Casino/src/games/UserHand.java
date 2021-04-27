package games;

import java.util.List;

public class UserHand  {
    private List<Card> hand;
    private User user;


    public UserHand(List<Card> hand, User user) {
        this.hand = hand;
        this.user = user;
    }

    public void addCard(Cards_Package cards_package){
        hand.add(cards_package.drawCard());
    }

    public void removeCard(Card card){
        hand.remove(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public User getUser() {
        return user;
    }
}
