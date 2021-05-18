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

    public void addCard(CardPackage cardPackage){
        hand.add(cardPackage.drawCard());
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
