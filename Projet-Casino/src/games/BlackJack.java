package games;
import java.util.ArrayList;
import java.util.List;
public class BlackJack {

    private List<UserHand> listOfUserHand; //Liste des joueurs présent dans la partie
    private CardPackage card_package; //Paquet de carte
    private Bet bet; //Mise
    private ActionBlackJack action; //Action du joueur
    private User user;
    private int insuranceUser;
    private int currentHand = 1;

    public BlackJack(User user) {
        this.user = user;
        card_package = new CardPackage();
        listOfUserHand = new ArrayList<>();
        bet = new Bet();
        insuranceUser = 0;
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
        listOfUserHand.get(1).addCard(card_package);
        listOfUserHand.get(0).addCard(card_package);
        listOfUserHand.get(1).addCard(card_package);
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
        card_package.initCardPackage();
        card_package.mixCardPackage();
        User croupier = new User("croupier", "null", "null");
        UserHand croupierHand = new UserHand(croupier);
        UserHand userHand = new UserHand(user);
        listOfUserHand.add(croupierHand);
        listOfUserHand.add(userHand);
        giveCardToUser();
        listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser()));
    }
    //Pioche du croupier jusqu'à avoir 17 ou +
    public void turnCroupier() {
        while (countValueOfUserHand(listOfUserHand.get(0)) < 17) {
            listOfUserHand.get(0).addCard(card_package);
        }
    }
    public void betDistribute() { //Distribution des gains au joueur
        int tokenGain = 0;
        if (verifyBlackJack(listOfUserHand.get(0))) {
            if (insuranceUser > 0) {
                tokenGain = bet.getBet(listOfUserHand.get(1).getUser()) + insuranceUser;
            } else if (verifyBlackJack(listOfUserHand.get(1))) {
                tokenGain = bet.getBet(listOfUserHand.get(1).getUser());
            }
            if (listOfUserHand.size() > 2) {
                if (insuranceUser <= 0 && !verifyBlackJack(listOfUserHand.get(2))) {
                    tokenGain = bet.getBet(listOfUserHand.get(2).getUser());
                }
            }
        }
        else {
            if (verifyBlackJack(listOfUserHand.get(1))) {
                if (listOfUserHand.size() > 2) {
                    tokenGain = bet.getBet(listOfUserHand.get(1).getUser());
                } else {
                    tokenGain = (int) (bet.getBet(listOfUserHand.get(1).getUser()) + (bet.getBet(listOfUserHand.get(1).getUser()) * 1.5));
                }
            } else {
                if (countValueOfUserHand(listOfUserHand.get(0)) > 21) {
                    tokenGain = bet.getBet(listOfUserHand.get(1).getUser()) * 2;
                    listOfUserHand.get(1).getUser().addToken(tokenGain);
                    return;
                }
                if (countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(1)) && (countValueOfUserHand(listOfUserHand.get(1)) < 21)) {
                    tokenGain = bet.getBet(listOfUserHand.get(1).getUser()) * 2;
                }
                if (countValueOfUserHand(listOfUserHand.get(0)) == countValueOfUserHand(listOfUserHand.get(1))) {
                    tokenGain = bet.getBet(listOfUserHand.get(1).getUser());
                }
            }
        }
        user.addToken(tokenGain);
        tokenGain = 0;
        if (listOfUserHand.size() > 2) {
            if (verifyBlackJack(listOfUserHand.get(2)) && !verifyBlackJack(listOfUserHand.get(0))) {
                tokenGain = bet.getBet(listOfUserHand.get(2).getUser()) * 2;
            }
            if(verifyBlackJack(listOfUserHand.get(2)) && verifyBlackJack(listOfUserHand.get(0))){
                tokenGain = bet.getBet(listOfUserHand.get(2).getUser());
            }
            else {
                if (countValueOfUserHand(listOfUserHand.get(0)) > 21) {
                    tokenGain = bet.getBet(listOfUserHand.get(2).getUser()) * 2;
                    listOfUserHand.get(2).getUser().addToken(tokenGain);
                    return;
                }
                if ((countValueOfUserHand(listOfUserHand.get(0)) < countValueOfUserHand(listOfUserHand.get(2))) && (countValueOfUserHand(listOfUserHand.get(2)) < 21)) {
                    tokenGain = bet.getBet(listOfUserHand.get(2).getUser()) * 2;
                }
                if (countValueOfUserHand(listOfUserHand.get(0)) == countValueOfUserHand(listOfUserHand.get(2))) {
                    tokenGain = bet.getBet(listOfUserHand.get(2).getUser());
                }
            }
        }
        user.addToken(tokenGain);
    }

        public void reset(){ //Suprimme les mains et les bets pour redémarrer une partie
            bet.removeBet(bet.getBet(listOfUserHand.get(1).getUser()), listOfUserHand.get(1).getUser());
            if(listOfUserHand.size() > 2) {
                bet.removeBet(bet.getBet(listOfUserHand.get(2).getUser()), listOfUserHand.get(1).getUser());
            }
            bet.removeUser(listOfUserHand.get(1).getUser());
            if(listOfUserHand.size() > 2) {
                bet.removeUser(listOfUserHand.get(2).getUser());
            }
            for(int i = 0; i < listOfUserHand.size(); i++){
                listOfUserHand.remove(i);
            }
            card_package = new CardPackage();
        }
        public void actionSplit(){ //Appelle l'action pour split la main en deux mains
            action = new ActionSplit();
            action.action(listOfUserHand, card_package, bet);
        }
        public void actionDouble(){ //Appelle l'action qui double la mise
            action = new ActionDouble();
            action.action(listOfUserHand, card_package, bet);
            listOfUserHand.get(1).getUser().removeToken(bet.getBet(listOfUserHand.get(1).getUser())/2);
            turnCroupier();
        }
        public void actionHit(){ //Appelle l'action qui fait piocher le joueur
            action = new ActionHit(currentHand);
            action.action(listOfUserHand, card_package, bet);
            if(countValueOfUserHand(listOfUserHand.get(currentHand)) >= 21 && currentHand == 1){
                currentHand = 2;
            }
        }
        public void actionInsurance(){ //Appelle l'action qui créé une assurance
            insuranceUser = bet.getBet(listOfUserHand.get(1).getUser()) / 2; //enlever classe ActionInsurance ?
            listOfUserHand.get(1).getUser().removeToken(insuranceUser);
        }
        public void actionStand(){ // Appelle l'action qui passe le tour du joueur
            action = new ActionStand();
            action.action(listOfUserHand, card_package, bet);
            if(currentHand == 1){
                currentHand = 2;
            }
            else {
                turnCroupier();
            }
        }
        public void actionSurrender(){ //Appelle l'action qui fait abandonner le joueur
            action = new ActionSurrender();
            action.action(listOfUserHand, card_package, bet);
            if(currentHand == 1){
                currentHand = 2;
            }
            else {
                turnCroupier();
            }
        }
        public List<UserHand> getListOfUserHand(){
            return listOfUserHand;
        }
        public Bet getBet(){return bet;}
}