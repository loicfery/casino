package games;

import sample.CellRoulette;

import java.util.ArrayList;
import java.util.List;

public class BetRoulette {

    List<CellRoulette> cells = new ArrayList<>();
    List<Bet> bets= new ArrayList<>();
    int valueOfBet;
    double multiply;

    public BetRoulette(List<CellRoulette> cells, int valueOfBet){
        this.cells = cells;
        this.valueOfBet = valueOfBet;
        multiply = chooseMultiply(cells);
    }


    public double chooseMultiply(List<CellRoulette> cells){
        switch (cells.size()) {
            case 1:
                return 36;
            case 2:
                return 17;
            case 3:
                return 11;
            case 4:
                return 8;
            case 6:
                return 5;
            case 12:
                return 2;
            case 18:
                return 1;
            case 24:
                return 0.5;
            default:
                return 0;
        }
    }

    public boolean containCell(String cell){
        for(CellRoulette cellRoulette : cells){
            if(cellRoulette.getValueCase().equals(cell)){
                return true;
            }
        }
        return false;
    }

    public List<CellRoulette> getCells(){return cells;}
    public int getValueOfBet(){return valueOfBet;}
    public double getMultiply(){return multiply;}

    public void setCells(List<CellRoulette> cells){
        this.cells = cells;
    }
    public void setValueOfBet(int valueOfBet){ this.valueOfBet = valueOfBet; }
}
