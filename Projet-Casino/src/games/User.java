package games;

public class User {

    private String nomU;
    private String prenomU;
    private String pseudoU;
    private String adressmail;
    private String password;
    private int coins;

    public User(String nomU, String prenomU, String pseudoU, String adressmail, String password, int coins) {
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.pseudoU = pseudoU;
        this.adressmail = adressmail;
        this.password = password;
        this.coins = coins;
    }
}
