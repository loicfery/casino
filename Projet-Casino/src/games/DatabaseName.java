package games;

public class DatabaseName {

    public String getTableUser() {
        return "Users";
    }

    public String getTableUserColumnMailUser() {
        return "MailUser_Users";
    }

    public String getTableUserColumnUserName() {
        return "NameUser_Users";
    }

    public String getTableUserColumnToken() {
        return "Token_HistoryPartyGamed";
    }

    public String getTableUserColumnMoney() {
        return "Money_Users";
    }

    public String getTableUserColumnPassword(){
        return "Password_Users";
    }

    public String getTableUserColumnRank(){return "Rank_Users";}

    public String getTableHistoryExchangeToken() {
        return "HistoryExchangeTokens";
    }

    public String getTableHistoryExchangeTokenColumnMailUser() {
        return "MailUser_HistoryExchangeTokens";
    }

    public String getTableHistoryExchangeTokenColumnPriceToken() {
        return "TokenPrice_HistoryExchangeTokens";
    }

    public String getTableHistoryExchangeTokenColumnMoneyGain() {
        return "MoneyGain_HistoryExchangeTokens";
    }

    public String getTableHistoryExchangeMoney() {
        return "HistoryExchangeMoney";
    }

    public String getTableHistoryExchangeMoneyColumnMailUser() {
        return "MailUser_HistoryExchangeMoney";
    }

    public String getTableHistoryExchangeMoneyColumnPriceMoney() {
        return "MoneyPrice_HistoryExchangeMoney";
    }

    public String getTableHistoryExchangeMoneyColumnMoneyGain() {
        return "TokenGain_HistoryExchangeMoney";
    }

    public String getTableHistoryPartyGamed() {
        return "HistoryPartyGamed";
    }

    public String getTableHistoryPartyGamedColumnMailUser() {
        return "MailUser_HistoryPartyGamed";
    }

    public String getTableHistoryPartyGamedColumnGameName() {
        return "GameName_HistoryPartyGamed";
    }

    public String getTableHistoryPartyGamedColumnTokenGain() {
        return "TokenGain_HistoryPartyGamed";
    }

    public String getTableGame() {
        return "Game";
    }

    public String getTableGameColumnNameGame() {
        return "GameName_Game";
    }

    public String getGameBlackJack(){
        return "BlackJack";
    }

    public String getGameSlotMachine(){
        return "SlotMachine";
    }

    public String getGameRoulette(){
        return "Roulette";
    }

    public String getTableExchangeToken(){
        return "ExchangeToken";
    }

    public String getTableExchangeTokenColumnPriceToken(){
        return "TokenPrice_ExchangeToken";
    }

    public String getTableExchangeTokenColumnMoneyGain(){
        return "MoneyGain_ExchangeToken";
    }

    public String getTableExchangeMoney(){
        return "ExchangeMoney";
    }

    public String getTableExchangeMoneyColumnPriceMoney(){
        return "MoneyPrice_ExchangeMoney";
    }

    public String getTableExchangeMoneyColumnTokenGain(){
        return "TokenGain_ExchangeMoney";
    }

    public String getTableHistoryExchangeTokenColumnDate(){return "Date_HistoryExchangeTokens";}

    public String getTableHistoryExchangeMoneyColumnDate(){return "Date_HistoryExchangeMoney";}

    public String getTableHistoryPartyGamedColumnDate(){
        return "Date_HistoryPartyGamed";
    }
}
