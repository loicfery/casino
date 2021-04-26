package games;

public class User {

    private String pseudo;
    private String email;
    private int numberOfToken;
    private int numberOfMoney;
    private String rank;

    public User(String pseudo, String email, String rank) {
        this.pseudo = pseudo;
        this.email = email;
        this.rank = rank;
        this.numberOfMoney = 0;
        this.numberOfToken = 0;
    }

    public String getPseudo(){return pseudo;}
    public String getEmail(){return email;}
    public int getNumberOfToken(){return numberOfToken;}
    public int getNumberOfMoney(){return numberOfMoney;}
    public String getRank(){return rank;}


    /** Récupère le nombre total de jeton de l'utilisateur **/
    public void getToken(){
        //recupération nombre de token dans base de donnée
        //this.numberOfToken = ...;
    }

    /** Récupère la somme totale d'argent de l'utilisateur **/
    public void getMoney(){
        //recupération somme argent dans base de donnée
    }

    /** Ajoute des jetons **/
    public void addToken(int numberOfToken){
        this.numberOfToken = this.numberOfToken + numberOfToken;
    }

    /** Ajoute une somme d'argent **/
    public void addMoney(int numberOfMoney){
        this.numberOfMoney = this.numberOfMoney + numberOfMoney;
    }

    /** Enlève des jetons **/
    public void removeToken(int numberOfToken){
        this.numberOfToken = this.numberOfToken - numberOfToken;
    }

    /** Enlève une somme d'argent **/
    public void removeMoney(int numberOfMoney){
        this.numberOfMoney = this.numberOfMoney - numberOfMoney;
    }
}

