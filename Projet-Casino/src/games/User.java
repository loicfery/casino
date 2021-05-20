package games;

public class User {

    private String pseudo;
    private String email;
    private int numberOfToken;
    private int amountOfMoney;
    private String rank;
    private Database database;
    private DatabaseName databaseName = new DatabaseName();

    public User(String pseudo, String email, String rank, int numberOfToken, int amountOfMoney, Database database) {
        this.pseudo = pseudo;
        this.email = email;
        this.rank = rank;
        this.numberOfToken = numberOfToken;
        this.amountOfMoney = amountOfMoney;
        this.database = database;
    }

    public User(){}

    public String getPseudo(){return pseudo;}
    public String getEmail(){return email;}
    public int getToken(){return numberOfToken;}
    public int getMoney(){return amountOfMoney;}
    public String getRank(){return rank;}


    /** Ajoute des jetons **/
    public void addToken(int numberOfToken){
        this.numberOfToken = this.numberOfToken + numberOfToken;
        database.update(databaseName.getTableUser(),databaseName.getTableUserColumnToken(),this.numberOfToken+"",databaseName.getTableUserColumnMailUser()+" = \""+email+"\"");
    }

    /** Ajoute une somme d'argent **/
    public void addMoney(int numberOfMoney){
        this.amountOfMoney = this.amountOfMoney + numberOfMoney;
        database.update(databaseName.getTableUser(),databaseName.getTableUserColumnMoney(),this.amountOfMoney+"",databaseName.getTableUserColumnMailUser()+" = \""+email+"\"");
    }

    /** Enlève des jetons **/
    public void removeToken(int numberOfToken){
        this.numberOfToken = this.numberOfToken - numberOfToken;
        database.update(databaseName.getTableUser(),databaseName.getTableUserColumnToken(),this.numberOfToken+"",databaseName.getTableUserColumnMailUser()+" = \""+email+"\"");
    }

    /** Enlève une somme d'argent **/
    public void removeMoney(int numberOfMoney){
        this.amountOfMoney = this.amountOfMoney - numberOfMoney;
        database.update(databaseName.getTableUser(),databaseName.getTableUserColumnMoney(),this.amountOfMoney+"",databaseName.getTableUserColumnMailUser()+" = \""+email+"\"");
    }

    public void setPseudo(String newPseudo){ pseudo = newPseudo; }

    public void setEmail(String newEmail){ email = newEmail; }
}

