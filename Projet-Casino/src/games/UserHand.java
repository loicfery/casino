package games;

import java.util.ArrayList;
import java.util.List;

public class UserHand  {
    private List<Card> hand;
    private User user;

    public UserHand(){
        this.hand = new ArrayList<>();
    }

    public UserHand(User user) {
        this.hand = new ArrayList<>();
        this.user = user;
    }

    public void addCard(CardPackage card_package){
        hand.add(card_package.drawCard());
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
