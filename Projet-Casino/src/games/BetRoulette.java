package games;

import java.util.ArrayList;
import java.util.List;

public class BetRoulette {
    List<Cell> cells = new ArrayList<>();
    List<Bet> bets= new ArrayList<>();
    Bet bet;
    Multiply multiply;

    public BetRoulette(){}

    public Multiply ChooseMultiply(List<Cell> cells){
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

    public void addBet(List<Cell> cells, Bet bet){

    }
}
