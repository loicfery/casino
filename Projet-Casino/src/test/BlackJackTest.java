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

    }

    @org.junit.jupiter.api.Test
    void userBet() {
    }

    @org.junit.jupiter.api.Test
    void addUserBet() {
    }

    @org.junit.jupiter.api.Test
    void gameBegin() {
    }

    @org.junit.jupiter.api.Test
    void turnCroupier() {
    }

    @org.junit.jupiter.api.Test
    void betDistribute() {
    }

    @org.junit.jupiter.api.Test
    void reset() {
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
    void actionInsurance() {
    }

    @org.junit.jupiter.api.Test
    void actionStand() {
    }

    @org.junit.jupiter.api.Test
    void actionSurrender() {
    }

    @org.junit.jupiter.api.Test
    void getListOfUserHand() {
    }

    @org.junit.jupiter.api.Test
    void getBet() {
    }

    @org.junit.jupiter.api.Test
    void getInsuranceUser() {
    }
}