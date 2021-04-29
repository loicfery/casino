package games;

import java.util.List;

public class BlackJack {

    private List<UserHand> ListOfUserHand;
    private Cards_Package cards_package;
    private Bet bet;
    private ActionBlackJack action;
    private int insurance;

    public BlackJack(List<UserHand> listOfUserHand, ActionBlackJack action) {
        ListOfUserHand = listOfUserHand;
        this.action = action;
        cards_package = new Cards_Package();
        bet = new Bet();
    }

    public int countValueOfUserHand(UserHand userHand){
        int s=0;
        for (int i=0; i<userHand.getHand().size(); i++){
            if(userHand.getHand().get(i).getNumber() == 0 && s<11){
                s = s+11;
            }
            else {
                s = s + userHand.getHand().get(i).getNumber();
            }
        }
        return s;
    }

    public boolean verifyBlackJack(UserHand userHand){
        int s;
        s = countValueOfUserHand(userHand);
        if(s == 21){
            return true;
        }
        return false;
    }

    public void giveCardToUser(){
        for(int i = 0; i<ListOfUserHand.size(); i++){
            ListOfUserHand.get(i).addCard(cards_package);
            ListOfUserHand.get(i).addCard(cards_package);
        }
    }

    public void UserBet(int valueOfBet, User user){
        for(int i = 1; i< ListOfUserHand.size(); i++){
            if(user == ListOfUserHand.get(i).getUser()) {
                bet.addBet(valueOfBet, ListOfUserHand.get(i).getUser());
            }
        }
    }

    public void addUserBet(User user){
        bet.addUser(user);
    }

    public void game(){
        int j = 1;
        User croupier = new User("croupier", "null", "null");
        UserHand croupierhand = new UserHand(null, croupier);
        ListOfUserHand.set(0, croupierhand);
        action = new ActionCroupier();
        action.action(ListOfUserHand.get(0), cards_package, bet, croupier);
        giveCardToUser();
        while(j!=0){

        }




    }

    public void setaction(ActionBlackJack action){
        this.action = action;
    }
}
