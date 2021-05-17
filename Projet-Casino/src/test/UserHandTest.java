package test;

import games.Card;
import games.CardPackage;
import games.User;
import games.UserHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserHandTest {

    @Test
    void addCard() {
        UserHand userHand = new UserHand();
        CardPackage cardPackage = new CardPackage();
        userHand.addCard(cardPackage);
        assertEquals(userHand.getHand().size(), 1, "La main comporte 1 carte");
    }

    @Test
    void removeCard() {
        UserHand userHand = new UserHand();
        CardPackage cardPackage = new CardPackage();
        userHand.addCard(cardPackage);
        userHand.removeCard(userHand.getHand().get(0));
        assertTrue(userHand.getHand().isEmpty());
    }

    @Test
    void getHand() {
        UserHand userHand = new UserHand();
        CardPackage cardPackage = new CardPackage();
        Card card = new Card(7, "SQUARE");
        Card card2 = new Card(8, "CLOVER");
        userHand.getHand().add(card);
        userHand.getHand().add(card2);
        assertEquals(userHand.getHand().get(0), card, "La carte est le 8 de carreau");
        assertEquals(userHand.getHand().get(1), card2, "La carte est le 9 de trèfle");

    }

    @Test
    void getUser() {
        User user  = new User();
        UserHand userHand = new UserHand(user);
        assertEquals(userHand.getUser(), user);
    }
}