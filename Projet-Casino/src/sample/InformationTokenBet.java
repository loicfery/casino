package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.List;

public class InformationTokenBet {

    private Circle circleToken;
    private Label labelToken;
    private String valueOfBet = "0";
    private List<Case> listOfCaseToken;

    public InformationTokenBet(Circle circleToken, Label labelToken, List<Case> listOfCaseToken){
        this.circleToken = circleToken;
        this.labelToken = labelToken;
        this.listOfCaseToken = listOfCaseToken;
    }

    public String getCases(){
        String cases = " ";
        for(int index = 0; index < (listOfCaseToken.size() - 1); index ++){
            cases = cases + listOfCaseToken.get(index).getValueCase() + " ; ";
        }
        if(listOfCaseToken.size() > 0) {
            cases = cases + listOfCaseToken.get(listOfCaseToken.size() - 1).getValueCase();
        }
        return cases;
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
}
