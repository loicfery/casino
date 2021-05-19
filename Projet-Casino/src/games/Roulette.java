package games;

import sample.CellRoulette;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette {

    private List<BetRoulette> listOfBet = new ArrayList<>();
    private User user;

    public Roulette(User user){
        this.user = user;
    }

    public void addBet(List<CellRoulette> cells, int valueOfBet){
        listOfBet.add(new BetRoulette(cells,valueOfBet));
        user.removeToken(valueOfBet);
    }

    public void modifyBet(List<CellRoulette> oldCells,List<CellRoulette> newCells, int newValueOfBet){
        boolean modifyBet = true;
        for(int firstIndex = 0; firstIndex < listOfBet.size(); firstIndex ++){
            if(listOfBet.get(firstIndex).getCells().size() == oldCells.size()) {
                for (int secondIndex = 0; secondIndex < oldCells.size(); secondIndex++) {
                    if (!oldCells.get(secondIndex).getValueCell().equals(listOfBet.get(firstIndex).getCells().get(secondIndex).getValueCell())) {
                        modifyBet = false;
                    }
                }
            }
            if(modifyBet){
                listOfBet.get(firstIndex).setCells(newCells);
                user.addToken(listOfBet.get(firstIndex).getValueOfBet());
                listOfBet.get(firstIndex).setValueOfBet(newValueOfBet);
                user.removeToken(listOfBet.get(firstIndex).getValueOfBet());
                return;
            }
        }
    }

    public void deleteBet(List<CellRoulette> cells){
        boolean deleteBet = true;
        for(BetRoulette bet : listOfBet) {
            for (int index = 0; index < cells.size(); index ++) {
                if(!cells.get(index).getValueCell().equals(bet.getCells().get(index).getValueCell())){
                    deleteBet = false;
                }
            }
            if(deleteBet){
                listOfBet.remove(bet);
                return;
            }
        }
    }

    public int RandomNumber(){
        Random random = new Random();
        int number;
        number = random.nextInt(37);
        return number;
    }

    public double gain(List<CellRoulette> listOfCells, String cellValue){
        for(CellRoulette cell : listOfCells) {
            if(cell.getValueCell().equals(cellValue)) {
                for (BetRoulette betRoulette : listOfBet) {
                    if (betRoulette.containCell(cellValue)) {
                        double multiple = betRoulette.getMultiply();
                        int bet = betRoulette.valueOfBet;
                        if(cellValue.equals("0")){
                            switch ((int)multiple){
                                case 1 :
                                    user.addToken(bet);
                                    return (bet + multiple) / 2;
                                case 2 :
                                    return 0;
                            }
                        }
                        user.addToken(bet);
                        return bet * multiple;
                    }
                }
            }
        }
        return 0;
    }

    public int finalGain(int number){
        int finalGain = 0;
        for(int i = 0; i < listOfBet.size(); i++){
            finalGain += gain(listOfBet.get(i).cells,number+"");
        }
        user.addToken(finalGain);
        return finalGain;
    }

    public int getBetTotal(){
        int valueBetTotal = 0;

        for(BetRoulette betRoulette : listOfBet){
            valueBetTotal = valueBetTotal + betRoulette.getValueOfBet();
        }
        return valueBetTotal;
    }

    public void resetBetList(){
        listOfBet.clear();
    }

    public List<BetRoulette> getListOfBet(){return listOfBet;}
}
