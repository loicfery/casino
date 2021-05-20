package games;

public class DatabaseName {
    //show tables from casino; pour recup les noms des tables (pas sûre de l'utiliser)

    private final String tableUser = "Users";
    private final String tableUserColumnMailUser = "MailUser_Users";
    private final String tableUserColumnUserName = "NameUser_Users";
    private final String tableUserColumnToken = "Token_HistoryPartyGamed";
    private final String tableUserColumnMoney = "Money_Users";
    private final String tableUserPassword = "Password_Users";

    private final String tableHistoryExchangeToken = "HistoryExchangeTokens";
    private final String tableHistoryExchangeTokenColumnMailUser = "MailUser_HistoryExchangeTokens";
    private final String tableHistoryExchangeTokenColumnPriceToken = "TokenPrice_HistoryExchangeTokens";
    private final String tableHistoryExchangeTokenColumnMoneyGain = "MoneyGame_HistoryExchangeTokens";

    private final String tableHistoryExchangeMoney = "HistoryExchangeMoney";
    private final String tableHistoryExchangeMoneyColumnMailUser = "MailUser_HistoryExchangeMoney";
    private final String tableHistoryExchangeMoneyColumnPriceMoney = "MoneyPrice_HistoryExchangeMoney";
    private final String tableHistoryExchangeMoneyColumnMoneyGain = "TokenGain_HistoryExchangeMoney";

    private final String tableHistoryPartyGamed = "HistoryPartyGamed";
    private final String tableHistoryPartyGamedColumnMailUser = "MailUser_HistoryPartyGamed";
    private final String tableHistoryPartyGamedColumnGameName = "GameName_HistoryPartyGamed";
    private final String tableHistoryPartyGamedColumnTokenGain = "TokenGain_HistoryPartyGamed";

    //tempo
    private final String tableExchangeToken = "ExchangeMoney";
    private final String tableExchangeTokenColumnPriceToken = "MoneyPrice_ExchangeMoney";
    private final String tableExchangeTokenColumnMoneyGain = "TokenGame_ExchangeMoney";

    //tempo
    private final String tableExchangeMoney = "ExchangeToken";
    private final String tableExchangeMoneyColumnPriceMoney = "TokenPrice_ExchangeToken";
    private final String tableExchangeMoneyColumnTokenGain = "MoneyGain_ExchangeToken";

    private final String tableGame = "Game";
    private final String tableGameColumnNameGame = "GameName_Game";
    private final String gameBlackJack = "BlackJack";
    private final String gameSlotMachine = "SlotMachine";
    private final String gameRoulette = "Roulette";

    public String getTableUser() {
        return tableUser;
    }

    public String getTableUserColumnMailUser() {
        return tableUserColumnMailUser;
    }

    public String getTableUserColumnUserName() {
        return tableUserColumnUserName;
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
