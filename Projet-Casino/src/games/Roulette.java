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

    public int Gain(List<BetRoulette> listOfBet, int number){
        for(int i=0;i<listOfBet.size();i++){
            for(int j=0;j<listOfBet.get(i).cells.size();j++) {
                if(listOfBet.get(i).cells.get(j).number==number)
                    return listOfBet.get(i).valueOfMultiply(listOfBet.get(i).chooseMultiply(listOfBet.get(i).cells));
            }
        }
        return 0;
    }

    /*public int FinalGain(){
    }*/

    public void ResetBetList(){
        listOfBet.clear();
    }

    /** une méthode qui prend en paramètre une liste de case d'une mise, et la case choisit aléatoireemnt
     * et retourne le gain de cette mise (0 si aucun gain)  **/

    /** une méthode qui calcule tous les gains et retourne le gain final **/


}
