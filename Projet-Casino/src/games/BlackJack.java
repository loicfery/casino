package games;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    private List<UserHand> listOfUserHand; //Liste des joueurs présent dans la partie
    private Cards_Package cards_package; //Paquet de carte
    private Bet bet; //Mise
    private ActionBlackJack action; //Action du joueur
    private User user;
    private int insuranceFirstHandUser;
    private int insuranceSecondHandUser;

    public BlackJack(User user) {
        this.user = user;
        cards_package = new Cards_Package();
        listOfUserHand = new ArrayList<>();
        bet = new Bet();
        insuranceFirstHandUser = 0;
        insuranceSecondHandUser = 0;
    }

    public int countValueOfUserHand(UserHand userHand){ //Calcule la valeur total des cartes dans la main d'un joueur
        int somme=0;
        for (int i=0; i<userHand.getHand().size(); i++){
            if(userHand.getHand().get(i).getNumber() == 1 && somme<11){
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

    //Vérifie si le joueur à fait un blackJack (si la valeur de ses cartes est 21)
    public boolean verifyBlackJack(UserHand userHand){
        int valueOfHand;
        valueOfHand = countValueOfUserHand(userHand);
        if(valueOfHand == 21){
            return true;
        }
        return false;
    }

    //Distribue les 2 premières cartes à tout les joueurs
    public void giveCardToUser(){
        listOfUserHand.get(1).addCard(cards_package);
        listOfUserHand.get(0).addCard(cards_package);
        listOfUserHand.get(1).addCard(cards_package);

        /*for(int i = 0; i< listOfUserHand.size(); i++){
            listOfUserHand.get(i).addCard(cards_package);
            listOfUserHand.get(i).addCard(cards_package);
        }*/
    }

    //Augmente la mise du joueur user
    public void userBet(int valueOfBet, User user){
        bet.addBet(valueOfBet,user);
    }

    public void addUserBet(User user){
        //Créer une mise pour user
        bet.addUser(user);
    }

    //Méthode créant une partie de blackJack
    public void gameBegin(){
        cards_package.initCardPackage();
        cards_package.mixCardPackage();

        User croupier = new User("croupier", "null", "null");
        UserHand croupierHand = new UserHand(croupier);
        UserHand userHand = new UserHand(user);

        //User split = new User("split", "null", "null");
        //UserHand splitHand = new UserHand(split);
        //User insurance = new User("insurance", "null","null");
        //UserHand insuranceBet = new UserHand(insurance);

        listOfUserHand.add(croupierHand);
        listOfUserHand.add(userHand);

        //listOfUserHand.set(0, croupierhand);
        //listOfUserHand.set(1,userHand);
        //listOfUserHand.set(2, splitHand);
        //listOfUserHand.set(3, insuranceBet);

        giveCardToUser();
    }

    //Pioche du croupier jusqu'à avoir 17 ou +
    public void turnCroupier() {
        while (countValueOfUserHand(listOfUserHand.get(0)) < 17) {
            listOfUserHand.get(0).addCard(cards_package);
        }
    }

    public int betDistribute(){
        int tokenGain = 0;

        if(verifyBlackJack(listOfUserHand.get(0))){
            if(insuranceFirstHandUser > 0 || verifyBlackJack(listOfUserHand.get(1))){
                tokenGain = 0;
            }
            else {
                tokenGain = -1 * (bet.getBet(listOfUserHand.get(1).getUser()));
                listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()));
            }
            if(listOfUserHand.size() > 2){
                if(insuranceSecondHandUser > 0 || verifyBlackJack(listOfUserHand.get(2))){
                    tokenGain = 0;
                }
                else {
                    tokenGain = -1 * (bet.getBet(listOfUserHand.get(2).getUser()));
                    listOfUserHand.get(2).getUser().removeToken(bet.getBet(listOfUserHand.get(2).getUser()));
                }
            }
        }
        else {
            if(verifyBlackJack(listOfUserHand.get(1))){
                tokenGain = bet.getBet(listOfUserHand.get(1).getUser()) - insuranceFirstHandUser;
                listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(1).getUser()) - insuranceFirstHandUser);
            }
            else{
                if(countValueOfUserHand(listOfUserHand.get(1)) > 21){
                    tokenGain = -1 * (bet.getBet(listOfUserHand.get(1).getUser()) + insuranceFirstHandUser);
                    listOfUserHand.get(1).getUser().addToken(tokenGain);
                    return tokenGain;
                }
                if(countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(1))){
                    tokenGain = -1 * (bet.getBet(listOfUserHand.get(1).getUser()) + insuranceFirstHandUser);
                    listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()) + insuranceFirstHandUser);
                }
                if(countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(1))){
                    tokenGain = bet.getBet(listOfUserHand.get(1).getUser()) - insuranceFirstHandUser;
                    listOfUserHand.get(1).getUser().addToken(tokenGain);
                }
                if(countValueOfUserHand(listOfUserHand.get(0)) == countValueOfUserHand(listOfUserHand.get(1))){
                    tokenGain = - insuranceFirstHandUser;
                    listOfUserHand.get(1).getUser().removeToken(insuranceFirstHandUser);
                }
            }
            if(listOfUserHand.size() > 2){
                if(verifyBlackJack(listOfUserHand.get(2))){
                    tokenGain = bet.getBet(listOfUserHand.get(2).getUser()) - insuranceSecondHandUser;
                    listOfUserHand.get(2).getUser().addToken(bet.getBet(listOfUserHand.get(1).getUser()) - insuranceSecondHandUser);
                }
                else {
                    if(countValueOfUserHand(listOfUserHand.get(2)) > 21){
                        tokenGain = -1 * (bet.getBet(listOfUserHand.get(2).getUser()) + insuranceFirstHandUser);
                        listOfUserHand.get(2).getUser().addToken(tokenGain);
                        return tokenGain;
                    }
                    if(countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(2))){
                        tokenGain = -1 * (bet.getBet(listOfUserHand.get(2).getUser()) + insuranceSecondHandUser);
                        listOfUserHand.get(2).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()) + insuranceSecondHandUser);
                    }
                    if(countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(2))){
                        tokenGain = bet.getBet(listOfUserHand.get(2).getUser()) - insuranceSecondHandUser;
                        listOfUserHand.get(2).getUser().addToken(tokenGain);
                    }
                    if(countValueOfUserHand(listOfUserHand.get(0)) == countValueOfUserHand(listOfUserHand.get(2))){
                        tokenGain = - insuranceSecondHandUser;
                        listOfUserHand.get(2).getUser().removeToken(insuranceSecondHandUser);
                    }
                }
            }
        }

        /*if (listOfUserHand.get(2).getHand().get(0) != null) {  //Remise ou perte des gains si split
            if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(1))) {
                tokenGain = tokenGain + bet.getBet(listOfUserHand.get(1).getUser());
                listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(1).getUser()));
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(1))) {
                listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()));
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(2))) {
                tokenGain = tokenGain + bet.getBet(listOfUserHand.get(1).getUser());
                listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(2).getUser()));
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(2))) {
                listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(2).getUser()));
            }
        }
        else {
            if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(1))) {
                if (verifyBlackJack(listOfUserHand.get(1))) {
                    tokenGain = (int) (bet.getBet(listOfUserHand.get(1).getUser()) * 1.5);
                    listOfUserHand.get(1).getUser().addToken(tokenGain);
                } else {
                    tokenGain = bet.getBet(listOfUserHand.get(1).getUser());
                    listOfUserHand.get(1).getUser().addToken(bet.getBet(listOfUserHand.get(1).getUser()));
                }
            }
            if (countValueOfUserHand(listOfUserHand.get(0)) > countValueOfUserHand(listOfUserHand.get(1))) {
                if (!verifyBlackJack(listOfUserHand.get(0))) {
                    listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()));
                    listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(3).getUser()));
                }
            }
        }*/

        return tokenGain;
    }

    public void reset(){
        bet.removeBet(bet.getBet(listOfUserHand.get(1).getUser()), listOfUserHand.get(1).getUser());
        if(listOfUserHand.size() > 2) {
            bet.removeBet(bet.getBet(listOfUserHand.get(2).getUser()), listOfUserHand.get(1).getUser());
        }
        //bet.removeBet(bet.getBet(listOfUserHand.get(3).getUser()), listOfUserHand.get(1).getUser());

        bet.removeUser(listOfUserHand.get(1).getUser());
        if(listOfUserHand.size() > 2) {
            bet.removeUser(listOfUserHand.get(2).getUser());
        }
        //bet.removeUser(listOfUserHand.get(3).getUser());

        for(int i = 0; i < listOfUserHand.size(); i++){
            listOfUserHand.remove(i);
        }
        cards_package = new Cards_Package();
    }

    public void actionSplit(){
        action = new ActionInsurance();
        action.action(listOfUserHand, cards_package, bet);
    }

    public void actionDouble(){
        action = new ActionDouble();
        action.action(listOfUserHand, cards_package, bet);
        turnCroupier();
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
        turnCroupier();
    }

    public void actionSurrender(){
        action = new ActionSurrender();
        action.action(listOfUserHand, cards_package, bet);
        reset();
        gameBegin();
    }

    public List<UserHand> getListOfUserHand(){
        return listOfUserHand;
    }

}
