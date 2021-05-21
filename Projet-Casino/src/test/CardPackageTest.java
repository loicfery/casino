package test;

import games.Card;
import games.CardPackage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardPackageTest {

    @Test
    void initCardPackage() {
        CardPackage cardPackage = new CardPackage();
        cardPackage.initCardPackage();

        assertEquals(cardPackage.getCardPackage().size(), 52, "Un paquet de carte est créer");
    }

    @Test
    void mixCardPackage() {
        CardPackage cardPackage = new CardPackage();
        CardPackage cardPackageMix = new CardPackage();
        cardPackage.initCardPackage();
        cardPackageMix.initCardPackage();
        cardPackageMix.mixCardPackage();

        assertNotSame(cardPackage, cardPackageMix);
    }

    @Test
    void removeCard() {
        CardPackage cardPackage = new CardPackage();
        cardPackage.initCardPackage();
        cardPackage.removeCard(5);

        assertEquals(cardPackage.getCardPackage().size(), 51);
    }

    @Test
    void drawCard() {
        CardPackage cardPackage = new CardPackage();

        cardPackage.initCardPackage();
        Card card = cardPackage.drawCard();

        assertEquals(cardPackage.getCardPackage().size(), 51);

        for(int i =0; i<cardPackage.getCardPackage().size();i++){
            assertNotSame(cardPackage.getCardPackage().get(i), card);
        }
    }

    @Test
    void getCardPackage() {
        CardPackage cardPackage = new CardPackage();

        cardPackage.initCardPackage();

        assertEquals(cardPackage.getCardPackage().size(), 52);
    }
}