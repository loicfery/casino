package test;

import games.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {

    @org.junit.jupiter.api.Test
    void countValueOfUserHand() {
        BlackJack blackJack = new BlackJack();
        UserHand userHand = new UserHand();
        userHand.getHand().add(0, new Card(5, "SQUARE"));
        userHand.getHand().add(1, new Card(7, "SPADE"));
        int res = 14;
        assertEquals(blackJack.countValueOfUserHand(userHand), res, "la somme des cartes est 14");
        userHand.getHand().clear();
        userHand.getHand().add(0, new Card(0, "SQUARE"));
        userHand.getHand().add(1, new Card(5, "SQUARE"));
        res = 17;
        assertEquals(blackJack.countValueOfUserHand(userHand), res, "la somme des cartes est 17");
        userHand.getHand().clear();
        userHand.getHand().add(0, new Card(11, "SQUARE"));
        res = 10;
        assertEquals(blackJack.countValueOfUserHand(userHand), res, "la somme des cartes est 10");
        userHand.getHand().clear();
        userHand.getHand().add(0, new Card(5, "SQUARE"));
        userHand.getHand().add(1, new Card(5, "SPADE"));
        userHand.getHand().add(2, new Card(0, "SQUARE"));
        res = 13;
        assertEquals(blackJack.countValueOfUserHand(userHand), res, "la somme des cartes est 13");

    }

    @org.junit.jupiter.api.Test
    void verifyBlackJack() {
        BlackJack blackJack = new BlackJack();
        UserHand userHand = new UserHand();
        userHand.getHand().add(0, new Card(0, "SQUARE"));
        userHand.getHand().add(1, new Card(11, "SQUARE"));
        assertTrue(blackJack.verifyBlackJack(userHand), "Le joueur a fait un BlackJack");
        userHand.getHand().clear();
        userHand.getHand().add(0, new Card(5, "SQUARE"));
        assertFalse(blackJack.verifyBlackJack(userHand), "Le joueur n'a pas fait de BlackJack");
    }

    @org.junit.jupiter.api.Test
    void giveCardToUser() {
        BlackJack blackJack = new BlackJack();
        User user0 = new User();
        User user1 = new User();
        UserHand croupier = new UserHand(user0);
        UserHand joueur = new UserHand(user1);
        blackJack.getListOfUserHand().add(0, croupier);
        blackJack.getListOfUserHand().add(1, joueur);
        blackJack.giveCardToUser();
        int res = 2;
        assertEquals(blackJack.getListOfUserHand().get(1).getHand().size(), res, "Le joueur a 2 cartes");
        res = 1;
        assertEquals(blackJack.getListOfUserHand().get(0).getHand().size(), res, "Le joueur a 1 cartes");
    }

    @org.junit.jupiter.api.Test
    void userBet() {
        BlackJack blackJack = new BlackJack();
        User user = new User();
        blackJack.addUserBet(user);
        int valueOfBet = 7;
        blackJack.userBet(valueOfBet, user);
        assertEquals(blackJack.getBet().getBet(user), 7, "Le joueur a augmenter sa mise de 7");
        blackJack.userBet(valueOfBet, user);
        assertEquals(blackJack.getBet().getBet(user), 14, "Le joueur a augmenter sa mise de 7");
    }

    @org.junit.jupiter.api.Test
    void addUserBet() {
        BlackJack blackJack = new BlackJack();
        User user = new User();
        blackJack.addUserBet(user);
        assertEquals(blackJack.getBet().getBet(user), 0, "Le joueur a été crée");
    }

    @org.junit.jupiter.api.Test
    void gameBegin() {  // TO DO
        BlackJack blackJack = new BlackJack();
        User user = new User();
        UserHand userHand = new UserHand();
        user.addToken(10);
        int valueOfBet = 10;
        blackJack.gameBegin();
        assertEquals(blackJack.getListOfUserHand().get(1).getUser().getToken(), 0, "Le joueur a misé");
    }

    @org.junit.jupiter.api.Test
    void turnCroupier() {
        BlackJack blackJack = new BlackJack();
        User user = new User();
        UserHand userHand = new UserHand(user);
        blackJack.getListOfUserHand().add(userHand);
        blackJack.turnCroupier();
        assertFalse(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0)) <17);
        assertTrue(blackJack.countValueOfUserHand(blackJack.getListOfUserHand().get(0)) >= 17);

    }

    @org.junit.jupiter.api.Test
    void betDistribute() { //TO DO
        BlackJack blackJack = new BlackJack();
    }

    @org.junit.jupiter.api.Test
    void reset() {
        BlackJack blackJack = new BlackJack();
        User user0 = new User();
        UserHand croupier = new UserHand(user0);
        User user1 = new User();
        UserHand joueur = new UserHand(user1);
        User user2 = new User();
        UserHand split = new UserHand(user2);
        CardPackage cardPackage = new CardPackage();
        cardPackage.initCardPackage();
        blackJack.getListOfUserHand().add(0, croupier);
        blackJack.getListOfUserHand().add(1, joueur);
        blackJack.getListOfUserHand().add(2, split);
        blackJack.reset();
        assertTrue(blackJack.getListOfUserHand().isEmpty());
        assertTrue(cardPackage.getCardPackage().isEmpty());
        // A FINIR

    }

    @org.junit.jupiter.api.Test
    void actionSplit() {
    }

    @org.junit.jupiter.api.Test
    void actionDouble() {
    }

    @org.junit.jupiter.api.Test
    void actionHit() {
    }

    @org.junit.jupiter.api.Test
    void actionInsurance() { //En attente de base de donnée
        BlackJack blackJack = new BlackJack();
        User croupier = new User();
        User user = new User();
        UserHand userHand0 = new UserHand(croupier);
        UserHand userHand = new UserHand(user);
        blackJack.addUserBet(user);
        blackJack.getListOfUserHand().add(0, userHand0);
        blackJack.getListOfUserHand().add(1, userHand);
        int mise = 10;
        blackJack.userBet(mise, user);
        blackJack.actionInsurance();
        assertEquals(blackJack.getInsuranceUser(), 5);
        // assert pour vérifer que le token a changé (BDD)
    }

    @org.junit.jupiter.api.Test
    void actionStand() {
    }

    @org.junit.jupiter.api.Test
    void actionSurrender() {
    }

    @org.junit.jupiter.api.Test
    void getListOfUserHand() {
        BlackJack blackJack = new BlackJack();
        User user = new User();
        User user1 = new User();
        UserHand userHand = new UserHand(user);
        UserHand userHand1 = new UserHand(user1);
        blackJack.getListOfUserHand().add(userHand);
        blackJack.getListOfUserHand().add(userHand1);
        assertEquals(blackJack.getListOfUserHand().size(), 2);
    }

    @org.junit.jupiter.api.Test
    void getBet() {
        BlackJack blackJack = new BlackJack();
        User user = new User();
        blackJack.addUserBet(user);
        int valueOfBet = 7;
        blackJack.userBet(valueOfBet, user);
        assertEquals(blackJack.getBet().getBet(user), 7);
    }

    @org.junit.jupiter.api.Test
    void getInsuranceUser() { //En attente de base de donnée
        BlackJack blackJack = new BlackJack();
        User croupier = new User();
        User user = new User();
        UserHand userHand0 = new UserHand(croupier);
        UserHand userHand = new UserHand(user);
        blackJack.addUserBet(user);
        blackJack.getListOfUserHand().add(0, userHand0);
        blackJack.getListOfUserHand().add(1, userHand);
        int mise = 10;
        blackJack.userBet(mise, user);
        blackJack.actionInsurance();
        assertEquals(blackJack.getInsuranceUser(), 5);

    }
}