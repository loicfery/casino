package games;

import java.util.List;

public class BlackJack {

    private List<UserHand> ListOfUserHand; //Liste des joueurs présent dans la partie
    private Cards_Package cards_package; //Paquet de carte
    private Bet bet; //Mise
    private ActionBlackJack action; //Action du joueur

    /** C'est pas logique d'avoir ces deux paramètre alors que tu dois les créer dans la classe
     * il te faut plutot mettre User user pour récupérer l'utilisateur **/
    public BlackJack(List<UserHand> listOfUserHand, ActionBlackJack action) {
        ListOfUserHand = listOfUserHand;
        this.action = action;
        cards_package = new Cards_Package();
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
        for(int i = 0; i<ListOfUserHand.size(); i++){
            ListOfUserHand.get(i).addCard(cards_package);
            ListOfUserHand.get(i).addCard(cards_package);
        }
    }

    public void userBet(int valueOfBet, User user){ //Augmente la mise du joueur user
        for(int i = 1; i< ListOfUserHand.size(); i++){
            if(user == ListOfUserHand.get(i).getUser()) {
                bet.addBet(valueOfBet, ListOfUserHand.get(i).getUser());
            }
        }
    }

    public void actionSplit(UserHand userHand){
        if(ListOfUserHand.get(0).getHand().get(0) == ListOfUserHand.get(0).getHand().get(1)){
            action = new ActionInsurance();
            action.action(ListOfUserHand, cards_package, bet);
        }
    }

    public void addUserBet(User user){
        bet.addUser(user);
    } //Créer une mise pour user

    public void gameBegin(){ //Méthode créant une partie de blackJack
        cards_package.mixCardPackage(cards_package.getCard_package());
        User croupier = new User("croupier", "null", "null");
        UserHand croupierhand = new UserHand(null, croupier);
        User split = new User("split", "null", "null");
        UserHand splitHand = new UserHand(null, split);
        User insurance = new User("insurance", "null","null");
        UserHand insuranceBet = new UserHand(null, insurance);
        ListOfUserHand.set(0, croupierhand);
        ListOfUserHand.set(2, splitHand);
        ListOfUserHand.set(3, insuranceBet);
        ListOfUserHand.get(0).addCard(cards_package);
        giveCardToUser();
    }

    public void gameEnd() {
        while (countValueOfUserHand(ListOfUserHand.get(0)) <= 17) { //Pioche du croupier jusqu'à avoir 17 ou +
            ListOfUserHand.get(0).addCard(cards_package);
        }
    }

    public int betDistribute(){
        int token = 0;
        if (ListOfUserHand.get(2).getHand().get(0) != null) {  //Remise ou perte des gains si split
            if (countValueOfUserHand(ListOfUserHand.get(0)) < countValueOfUserHand(ListOfUserHand.get(1))) {
                token = token + bet.getBet(ListOfUserHand.get(1).getUser());
                ListOfUserHand.get(1).getUser().addToken(bet.getBet(ListOfUserHand.get(1).getUser()));
            }
            if (countValueOfUserHand(ListOfUserHand.get(0)) > countValueOfUserHand(ListOfUserHand.get(1))) {
                ListOfUserHand.get(1).getUser().removeToken(bet.getBet(ListOfUserHand.get(1).getUser()));
            }
            if (countValueOfUserHand(ListOfUserHand.get(0)) < countValueOfUserHand(ListOfUserHand.get(2))) {
                token = token + bet.getBet(ListOfUserHand.get(1).getUser());
                ListOfUserHand.get(1).getUser().addToken(bet.getBet(ListOfUserHand.get(2).getUser()));
            }
            if (countValueOfUserHand(ListOfUserHand.get(0)) > countValueOfUserHand(ListOfUserHand.get(2))) {
                ListOfUserHand.get(1).getUser().removeToken(bet.getBet(ListOfUserHand.get(2).getUser()));
            }
        }
        else {
            if (countValueOfUserHand(ListOfUserHand.get(0)) < countValueOfUserHand(ListOfUserHand.get(1))) {
                if (verifyBlackJack(ListOfUserHand.get(1))) {
                    token = (int) (bet.getBet(ListOfUserHand.get(1).getUser()) * 1.5);
                    ListOfUserHand.get(1).getUser().addToken(token);
                } else {
                    token = bet.getBet(ListOfUserHand.get(1).getUser());
                    ListOfUserHand.get(1).getUser().addToken(bet.getBet(ListOfUserHand.get(1).getUser()));
                }
            }
            if (countValueOfUserHand(ListOfUserHand.get(0)) > countValueOfUserHand(ListOfUserHand.get(1))) {
                if (!verifyBlackJack(ListOfUserHand.get(0))) {
                    ListOfUserHand.get(1).getUser().removeToken(bet.getBet(ListOfUserHand.get(1).getUser()));
                    ListOfUserHand.get(1).getUser().removeToken(bet.getBet(ListOfUserHand.get(3).getUser()));
                }
            }
        }
        reset();
        return token;
    }

    public void reset(){
        bet.removeBet(bet.getBet(ListOfUserHand.get(1).getUser()), ListOfUserHand.get(1).getUser());
        bet.removeBet(bet.getBet(ListOfUserHand.get(2).getUser()), ListOfUserHand.get(1).getUser());
        bet.removeBet(bet.getBet(ListOfUserHand.get(3).getUser()), ListOfUserHand.get(1).getUser());
        bet.removeUser(ListOfUserHand.get(1).getUser());
        bet.removeUser(ListOfUserHand.get(2).getUser());
        bet.removeUser(ListOfUserHand.get(3).getUser());
        for(int i = 0; i < ListOfUserHand.size(); i++){
            ListOfUserHand.remove(i);
        }
        Cards_Package cards_package = new Cards_Package();
    }

    public void actionDouble(){
        action = new ActionDouble();
        action.action(ListOfUserHand, cards_package, bet);
        gameEnd();
    }

    public void actionHit(){
        action = new ActionHit();
        action.action(ListOfUserHand, cards_package, bet);
    }

    public void actionInsurance(){
        action = new ActionInsurance();
        action.action(ListOfUserHand, cards_package, bet);
    }

    public void actionStand(){
        action = new ActionStand();
        action.action(ListOfUserHand, cards_package, bet);
        gameEnd();
    }

    public void actionSurrender(){
        action = new ActionSurrender();
        action.action(ListOfUserHand, cards_package, bet);
        reset();
        gameBegin();
    }


}
