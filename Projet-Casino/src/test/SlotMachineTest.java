package test;

import games.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotMachineTest {

    @Test
    void userBet() {
        User user = new User();
        SlotMachine slotMachine = new SlotMachine(user);
        slotMachine.userBet(user);
        assertEquals(slotMachine.getBet().getBet(user), 1, "Le joueur a augmenter sa mise de 1");
        slotMachine.userBet(user);
        assertEquals(slotMachine.getBet().getBet(user), 2, "Le joueur a augmenter sa mise de 1");
    }

    @Test
    void useSlotMachine() {
    }

    @Test
    void verifySlot() {
        SlotMachine slotMachine = new SlotMachine();
        String[] results1 = {"SEVEN", "SEVEN", "SEVEN"};
        int res = 160;
        slotMachine.setResults(results1);
        assertEquals(slotMachine.verifySlot(),res, "le resultat est 160");
        String[] results2 = {"CHERRY", "CHERRY", "CHERRY"};
        res = 25;
        slotMachine.setResults(results2);
        assertEquals(slotMachine.verifySlot(),res, "le resultat est 25");
        String[] results3 = {"LEMON", "LEMON", "LEMON"};
        res = 8;
        slotMachine.setResults(results3);
        assertEquals(slotMachine.verifySlot(),res, "le resultat est 8");
        String[] results4 = {"WATERMELON", "WATERMELON", "WATERMELON"};
        res = 4;
        slotMachine.setResults(results4);
        assertEquals(slotMachine.verifySlot(),res, "le resultat est 4");
        String[] results5 = {"SEVEN", "LEMON", "SEVEN"};
        res = 0;
        slotMachine.setResults(results5);
        assertEquals(slotMachine.verifySlot(),res, "le resultat est 0");

    }

    @Test
    void giveTokenBet() {
    }

    @Test
    void getNbImage() {
    }

    @Test
    void reset() {
    }
}