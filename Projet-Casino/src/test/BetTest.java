package test;

import games.Bet;
import games.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    void addBet() {
        Bet bet = new Bet();
        User user = new User();
        int valueOfBet = 10;

        bet.addUser(user);
        bet.addBet(valueOfBet, user);

        assertEquals(bet.getBet(user), valueOfBet);
    }

    @Test
    void removeBet() {
        Bet bet = new Bet();
        User user = new User();
        int valueOfBet = 10;

        bet.addUser(user);
        bet.addBet(valueOfBet, user);

        valueOfBet = 7;
        bet.removeBet(valueOfBet, user);

        assertEquals(bet.getBet(user), 3);
    }

    @Test
    void addUser() {
        List<User> listOfUser = new ArrayList<>();
        Bet bet = new Bet(listOfUser);
        User user = new User();

        bet.addUser(user);

        assertEquals(listOfUser.get(0), user);

    }

    @Test
    void removeUser() {
        List<User> listOfUser = new ArrayList<>();
        Bet bet = new Bet(listOfUser);
        User user = new User();
        User user1 = new User();

        bet.addUser(user);
        bet.addUser(user1);
        bet.removeUser(user);

        assertEquals(listOfUser.size(), 1);

        for(int i = 0; i<listOfUser.size(); i++){
            assertNotSame(listOfUser.get(i), user);
        }
    }

    @Test
    void getBet() {
        Bet bet = new Bet();
        User user = new User();
        int valueOfBet = 10;

        bet.addUser(user);
        bet.addBet(valueOfBet, user);

        assertEquals(bet.getBet(user), valueOfBet);
    }
}