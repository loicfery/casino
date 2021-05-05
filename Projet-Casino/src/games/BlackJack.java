package games;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    private List<UserHand> listOfUserHand; //Liste des joueurs présent dans la partie
    private Cards_Package cards_package; //Paquet de carte
    private Bet bet; //Mise
    private ActionBlackJack action; //Action du joueur
    private User user;


    public BlackJack(User user) {
        cards_package = new Cards_Package();
        listOfUserHand = new ArrayList<>();
        bet = new Bet();
    }

    public int countValueOfUserHand(UserHand userHand){ //Calcule la valeur total des cartes dans la main d'un joueur
        int somme=0;
        for (int i=0; i<userHand.getHand().size(); i++){
            if(userHand.getHand().get(i).getNumber() == 0 && somme<11){
                somme = somme+11;
            }
            else {
                int somme1 = userHand.getHand().get(i).getNumber();
                if(somme1>=10){
                    somme1 = 10;
                }
                somme = somme + somme1;
            }
        }
        return somme;
    }

    public boolean verifyBlackJack(UserHand userHand){ //Vérifie si le joueur à fait un blackJack (si la valeur de ses cartes est 21)
        int s;
        s = countValueOfUserHand(userHand);
        if(s == 21){
            return true;
        }
        return false;
    }

    public void giveCardToUser(){ //Distribue les 2 premières cartes à tout les joueurs
        listOfUserHand.get(1).addCard(cards_package);
        listOfUserHand.get(0).addCard(cards_package);
        listOfUserHand.get(1).addCard(cards_package);

        /*for(int i = 0; i< listOfUserHand.size(); i++){
            listOfUserHand.get(i).addCard(cards_package);
            listOfUserHand.get(i).addCard(cards_package);
        }*/
    }

    public void userBet(int valueOfBet, User user){ //Augmente la mise du joueur user
        for(int i = 1; i< listOfUserHand.size(); i++){
            if(user == listOfUserHand.get(i).getUser()) {
                bet.addBet(valueOfBet, listOfUserHand.get(i).getUser());
            }
        }
    }

    public void addUserBet(User user){
        //Créer une mise pour user
        bet.addUser(user);
    }

    public void gameBegin(){ //Méthode créant une partie de blackJack
        cards_package.initCardPackage();
        cards_package.mixCardPackage();
        User croupier = new User("croupier", "null", "null");
        UserHand croupierhand = new UserHand(croupier);
        UserHand userHand = new UserHand(user);
        User split = new User("split", "null", "null");
        UserHand splitHand = new UserHand(split);
        User insurance = new User("insurance", "null","null");
        UserHand insuranceBet = new UserHand(insurance);

        listOfUserHand.add(croupierhand);
        listOfUserHand.add(userHand);
        listOfUserHand.add(splitHand);
        listOfUserHand.add(insuranceBet);

        //listOfUserHand.set(0, croupierhand);
        //listOfUserHand.set(1,userHand);
        //listOfUserHand.set(2, splitHand);
        //listOfUserHand.set(3, insuranceBet);

        giveCardToUser();
    }

    public void gameEnd() {
        while (countValueOfUserHand(listOfUserHand.get(0)) <= 17) { //Pioche du croupier jusqu'à avoir 17 ou +
            listOfUserHand.get(0).addCard(cards_package);
        }
    }

    public int betDistribute(){
        int token = 0;
        if (listOfUserHand.get(2).getHand().get(0) != null) {  //Remise ou perte des gains si split
            if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(1))) {
                token = token + bet.getBet(listOfUserHand.get(1).getUser());
                listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(1).getUser()));
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(1))) {
                listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()));
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(2))) {
                token = token + bet.getBet(listOfUserHand.get(1).getUser());
                listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(2).getUser()));
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(2))) {
                listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(2).getUser()));
            }
        }
        else {
            if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(1))) {
                if (verifyBlackJack(listOfUserHand.get(1))) {
                    token = (int) (bet.getBet(listOfUserHand.get(1).getUser()) * 1.5);
                    listOfUserHand.get(1).getUser().addToken(token);
                } else {
                    token = bet.getBet(listOfUserHand.get(1).getUser());
                    listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(1).getUser()));
                }
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(1))) {
                if (!verifyBlackJack(listOfUserHand.get(0))) {
                    listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()));
                    listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(3).getUser()));
                }
            }
        }
        reset();
        return token;
    }

    public void reset(){
        bet.removeBet(bet.getBet(listOfUserHand.get(1).getUser()), listOfUserHand.get(1).getUser());
        bet.removeBet(bet.getBet(listOfUserHand.get(2).getUser()), listOfUserHand.get(1).getUser());
        bet.removeBet(bet.getBet(listOfUserHand.get(3).getUser()), listOfUserHand.get(1).getUser());
        bet.removeUser(listOfUserHand.get(1).getUser());
        bet.removeUser(listOfUserHand.get(2).getUser());
        bet.removeUser(listOfUserHand.get(3).getUser());
        for(int i = 0; i < listOfUserHand.size(); i++){
            listOfUserHand.remove(i);
        }
        Cards_Package cards_package = new Cards_Package();
    }

    public void actionSplit(UserHand userHand){
        if(listOfUserHand.get(0).getHand().get(0) == listOfUserHand.get(0).getHand().get(1)){
            action = new ActionInsurance();
            action.action(listOfUserHand, cards_package, bet);
        }
    }

    public void actionDouble(){
        action = new ActionDouble();
        action.action(listOfUserHand, cards_package, bet);
        gameEnd();
    }

    public void actionHit(){
        action = new ActionHit();
        action.action(listOfUserHand, cards_package, bet);
    }

    public void actionInsurance(){
        action = new ActionInsurance();
        action.action(listOfUserHand, cards_package, bet);
    }

    public void actionStand(){
        action = new ActionStand();
        action.action(listOfUserHand, cards_package, bet);
        gameEnd();
    }

    public void actionSurrender(){
        action = new ActionSurrender();
        action.action(listOfUserHand, cards_package, bet);
        reset();
        gameBegin();
    }

    public void setAction(ActionBlackJack action){
        this.action = action;
    }

    public List<UserHand> getListOfUserHand(){
        return listOfUserHand;
    }

}
