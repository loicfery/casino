package games;

import java.util.List;

public class BlackJack {

    private List<UserHand> ListOfUserHand; //Liste des joueurs présent dans la partie
    private Cards_Package cards_package; //Paquet de carte
    private Bet bet; //Mise
    private ActionBlackJack action; //Action du joueur
    private int insurance; //Temporaire : mise d'assurance du joueur

    public BlackJack(List<UserHand> listOfUserHand, ActionBlackJack action) {
        ListOfUserHand = listOfUserHand;
        this.action = action;
        cards_package = new Cards_Package();
        bet = new Bet();
    }

    public int countValueOfUserHand(UserHand userHand){ //Calcule la valeur total des cartes dans la main d'un joueur
        int s=0;
        for (int i=0; i<userHand.getHand().size(); i++){
            if(userHand.getHand().get(i).getNumber() == 0 && s<11){
                s = s+11;
            }
            else {
                int s1 = userHand.getHand().get(i).getNumber();
                if(s1>=10){
                    s1 = 10;
                }
                s = s + s1;
            }
        }
        return s;
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

    public void UserBet(int valueOfBet, User user){ //Augment la mise du joueur user
        for(int i = 1; i< ListOfUserHand.size(); i++){
            if(user == ListOfUserHand.get(i).getUser()) {
                bet.addBet(valueOfBet, ListOfUserHand.get(i).getUser());
            }
        }
    }

    public void addUserBet(User user){
        bet.addUser(user);
    } //Créer une mise pour user

    public void game(){ //Méthode créant une partie de blackJack
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

    public void setaction(ActionBlackJack action){ //méthode permettant de récupéré l'action pour l'interface
        this.action = action;
    }
}
