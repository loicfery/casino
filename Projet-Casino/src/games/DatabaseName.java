package games;

public class DatabaseName {
    //show tables from casino; pour recup les noms des tables (pas sûre de l'utiliser)

    private final String tableUser = "Utilisateurs";
    private final String tableUserColumnMailUser = "MailUser_Utilisateurs";
    private final String tableUserColumnPseudo = "NameUser_Utilisateurs";
    private final String tableUserColumnToken = "Token_HistoriquePartiesJouees";
    private final String tableUserColumnMoney = "Money_Utilisateurs";
    private final String tableUserPassword = "Password_Utilisateurs";

    private final String tableHistoryExchangeToken = "HistoriqueAchatJetons";
    private final String tableHistoryExchangeTokenColumnMailUser = "MailUser_HistoriqueAchatJetons";
    private final String tableHistoryExchangeTokenColumnPriceToken = "PrixJeton_HistoriqueAchatJetons";
    private final String tableHistoryExchangeTokenColumnMoneyGain = "ArgentGagne_HistoriqueAchatJetons";

    private final String tableHistoryExchangeMoney = "HistoriqueEchangeArgent";
    private final String tableHistoryExchangeMoneyColumnMailUser = "MailUser_HistoriqueEchangeArgent";
    private final String tableHistoryExchangeMoneyColumnPriceMoney = "PrixArgent_HistoriqueEchangeArgent";
    private final String tableHistoryExchangeMoneyColumnMoneyGain = "JetonGagne_HistoriqueEchangeArgent";

    private final String tableHistoryPartyGamed = "HistoriquePartiesJouees";
    private final String tableHistoryPartyGamedColumnMailUser = "MailUser_HistoriquePartiesJouees";
    private final String tableHistoryPartyGamedColumnGameName = "NomJeux_HistoriquePartiesJouees";
    private final String tableHistoryPartyGamedColumnTokenGain = "TokenGagne_HistoriquePartiesJouees";

    //tempo
    private final String tableExchangeToken = "EchangeArgents";
    private final String tableExchangeTokenColumnPriceToken = "PrixArgent_EchangeArgents";
    private final String tableExchangeTokenColumnMoneyGain = "JetonGagne_EchangeArgents";

    //tempo
    private final String tableExchangeMoney = "EchangeJetons";
    private final String tableExchangeMoneyColumnPriceMoney = "PrixJeton_EchangeJetons";
    private final String tableExchangeMoneyColumnTokenGain = "ArgentGagne_EchangeJetons";

    private final String tableGame = "Jeux";
    private final String tableGameColumnNameGame = "NomJeux_Jeux";
    private final String gameBlackJack = "BlackJack";
    private final String gameSlotMachine = "SlotMachine";
    private final String gameRoulette = "Roulette";

    public String getTableUser() {
        return tableUser;
    }

    public String getTableUserColumnMailUser() {
        return tableUserColumnMailUser;
    }

    public String getTableUserColumnPseudo() {
        return tableUserColumnPseudo;
    }

    public String getTableUserColumnToken() {
        return tableUserColumnToken;
    }

    public String getTableUserColumnMoney() {
        return tableUserColumnMoney;
    }

    public String getTableUserPassword(){
        return tableUserPassword;
    }

    public String getTableHistoryExchangeToken() {
        return tableHistoryExchangeToken;
    }

    public String getTableHistoryExchangeTokenColumnMailUser() {
        return tableHistoryExchangeTokenColumnMailUser;
    }

    public String getTableHistoryExchangeTokenColumnPriceToken() {
        return tableHistoryExchangeTokenColumnPriceToken;
    }

    public String getTableHistoryExchangeTokenColumnMoneyGain() {
        return tableHistoryExchangeTokenColumnMoneyGain;
    }

    public String getTableHistoryExchangeMoney() {
        return tableHistoryExchangeMoney;
    }

    public String getTableHistoryExchangeMoneyColumnMailUser() {
        return tableHistoryExchangeMoneyColumnMailUser;
    }

    public String getTableHistoryExchangeMoneyColumnPriceMoney() {
        return tableHistoryExchangeMoneyColumnPriceMoney;
    }

    public String getTableHistoryExchangeMoneyColumnMoneyGain() {
        return tableHistoryExchangeMoneyColumnMoneyGain;
    }

    public String getTableHistoryPartyGamed() {
        return tableHistoryPartyGamed;
    }

    public String getTableHistoryPartyGamedColumnMailUser() {
        return tableHistoryPartyGamedColumnMailUser;
    }

    public String getTableHistoryPartyGamedColumnGameName() {
        return tableHistoryPartyGamedColumnGameName;
    }

    public String getTableHistoryPartyGamedColumnTokenGain() {
        return tableHistoryPartyGamedColumnTokenGain;
    }

    public String getTableGame() {
        return tableGame;
    }

    public String getTableGameColumnNameGame() {
        return tableGameColumnNameGame;
    }

    public String getGameBlackJack(){
        return gameBlackJack;
    }

    public String getGameSlotMachine(){
        return gameSlotMachine;
    }

    public String getGameRoulette(){
        return gameRoulette;
    }

    public String getTableExchangeToken(){
        return tableExchangeToken;
    }

    public String getTableExchangeTokenColumnPriceToken(){
        return tableExchangeTokenColumnPriceToken;
    }

    public String getTableExchangeTokenColumnMoneyGain(){
        return tableExchangeTokenColumnMoneyGain;
    }

    public String getTableExchangeMoney(){
        return tableExchangeMoney;
    }

    public String getTableExchangeMoneyColumnPriceMoney(){
        return tableExchangeMoneyColumnPriceMoney;
    }

    public String getTableExchangeMoneyColumnTokenGain(){
        return tableExchangeMoneyColumnTokenGain;
    }
}
