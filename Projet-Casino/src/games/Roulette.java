package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette {

    private List<BetRoulette> listOfBet = new ArrayList<>();

    public void addBet(List<Cell> cells, int valueOfBet){
        listOfBet.add(new BetRoulette(cells,valueOfBet));
    }


    public int RandomNumber(){
        Random random = new Random();
        int number;
        number = random.nextInt(37);
        return number;
    }

    public int Gain(List<Cell> listOfCells, int number){
        for(int j=0;j<listOfCells.size();j++) {
            if(listOfCells.get(j).getNumber()==number)
                return listOfBet.get(j).valueOfMultiply(listOfBet.get(j).chooseMultiply(listOfCells))*listOfBet.get(j).valueOfBet;
        }
        return 0;
    }

    public int FinalGain(int number){
        int finalGain=0;
        for(int i=0;i<listOfBet.size();i++){
            for(int j=0;j<listOfBet.get(i).cells.size();j++){
                finalGain+=Gain(listOfBet.get(i).cells,number);
            }
        }
        return finalGain;
    }

    public void ResetBetList(){
        listOfBet.clear();
    }

    /** une méthode qui calcule tous les gains et retourne le gain final **/


}
