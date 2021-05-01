package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.List;

public class InformationTokenBet {

    private Circle circleToken;
    private Label labelToken;
    private String valueOfBet = "0";
    private List<Case> listOfCaseToken;
    private List<Case> listOfCaseBet;

    public InformationTokenBet(Circle circleToken, Label labelToken, List<Case> listOfCaseToken){
        this.circleToken = circleToken;
        this.labelToken = labelToken;
        this.listOfCaseToken = listOfCaseToken;
    }

    public String getCases(){
        String cases = " ";
        for(int index = 0; index < listOfCaseToken.size(); index ++){
            cases = cases + listOfCaseToken.get(index).getValueCase() + ";";
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

    public List<Case> getListOfCaseToken() {
        return listOfCaseToken;
    }

    public void setValueOfBet(String valueOfBet){
        this.valueOfBet = valueOfBet;
    }

    public void setListOfCaseBet(List<Case> listOfCaseBet){
        this.listOfCaseBet = listOfCaseBet;
    }
}
