package test;

import games.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getNumber() {
        Card card = new Card(7, "");
        int value = 7+1;

        assertEquals(card.getNumber(), value, "La valeur de la carte est 8");
    }

    @Test
    void getRank() {
        Card card = new Card(7, "SQUARE");
        String string = "SQUARE";

        assertEquals(card.getRank(),  string, "Le signe de la carte est SQUARE");
    }
}