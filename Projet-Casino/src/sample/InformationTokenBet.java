package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class InformationTokenBet {

    private Circle circleToken;
    private Label labelToken;
    private String valueOfBet = "0";
    private List<CellRoulette> listOfCellToken;
    private List<CellRoulette> listOfCellBet = new ArrayList<>();

    public InformationTokenBet(Circle circleToken, Label labelToken, List<CellRoulette> listOfCellToken){
        this.circleToken = circleToken;
        this.labelToken = labelToken;
        this.listOfCellToken = listOfCellToken;
    }

    /** Méthode qui retourne toutes les cases de la mise **/
    public String getCasesToString(){
        String cases = " ";
        for(int index = 0; index < listOfCellToken.size(); index ++){
            cases = cases + listOfCellToken.get(index).getValueCell() + ";";
        }
        if(cases.length() > 0) {
            return cases.substring(0,cases.length() - 1);
        }
        return "aucune case sélectionnée";
    }

    public Circle getCircleToken() {
        return circleToken;
    }
    public Label getLabelToken() {
        return labelToken;
    }
    public String getValueOfBet() {
        return valueOfBet;
    }
    public List<CellRoulette> getListOfCellToken() {
        return listOfCellToken;
    }
    public List<CellRoulette> getListOfCellBet(){return listOfCellBet;}

    public void setValueOfBet(String valueOfBet){
        this.valueOfBet = valueOfBet;
    }

    public void setListOfCellBetInterface(List<CellRoulette> listOfCellRouletteBet){
        this.listOfCellBet = listOfCellRouletteBet;
    }
}
