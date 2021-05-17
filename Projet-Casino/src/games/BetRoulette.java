package games;

import java.util.ArrayList;
import java.util.List;

public class BetRoulette {
    List<Cell> cells = new ArrayList<>();
    List<Bet> bets= new ArrayList<>();
    int valueOfBet;
    Multiply multiply;

    public BetRoulette(List<Cell> cells, int valueOfBet){
        this.cells = cells;
        this.valueOfBet = valueOfBet;
        multiply = chooseMultiply(cells);
    }

    public Multiply chooseMultiply(List<Cell> cells){
        if(cells.size()==1)
            return Multiply.Plein;
        else if(cells.size()==2)
            return Multiply.Cheval;
        else if(cells.size()==3)
            return Multiply.Transversal;
        else if(cells.size()==4)
            return Multiply.Carre;
        else if(cells.size()==6)
            return Multiply.Douzaine;
        else if(cells.size()==12)
            return Multiply.Colonne;
        else
            return Multiply.Simple;
    }

    public List<Cell> getCells(){return cells;}
    public int getValueOfBet(){return valueOfBet;}
    public Multiply getMultiply(){return multiply;}
}
