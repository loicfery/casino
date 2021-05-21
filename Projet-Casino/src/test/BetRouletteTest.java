package test;

import games.BetRoulette;
import games.ColorRoulette;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.CellRoulette;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BetRouletteTest {

    @Test
    void chooseMultiply() {
        List<CellRoulette> cells = new ArrayList<>();
        BetRoulette betroulette = new BetRoulette(cells,1);
        CellRoulette cellroulette = new CellRoulette(0,0,0,0,ColorRoulette.Red,"");

        assertEquals(betroulette.chooseMultiply(cells),0);

        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),36);

        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),17);

        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),11);

        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),8);

        cells.add(cellroulette);
        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),5);

        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),2);

        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),1);

        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        cells.add(cellroulette);
        assertEquals(betroulette.chooseMultiply(cells),0.5);
    }

    @Test
    void containCell() {
        List<CellRoulette> list = new ArrayList<>();
        list.add(new CellRoulette(1,2,3,4,ColorRoulette.Red,"2"));

        BetRoulette betroulette = new BetRoulette(list,5);

        assertTrue(betroulette.containCell("2"));
        assertFalse(betroulette.containCell("5"));
    }
}