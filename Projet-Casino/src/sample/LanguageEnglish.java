package sample;

public class LanguageEnglish implements Language{

    /** ConnexionMenuController **/
    public String getConnexionMenuControllerLabelTitle1() {
        return "Welcome to the casino";
    }

    public String getConnexionMenuControllerLabelTitle2(){
        return "New Account";
    }

    public String getConnexionMenuControllerLabelEmail() {
        return "Email";
    }

    public String getConnexionMenuControllerLabelPassword() {
        return "Password";
    }

    public String getConnexionMenuControllerButtonLogin() {
        return "Login";
    }

    public String getConnexionMenuControllerButtonNewAccount() {
        return "New Account";
    }

    public String getConnexionMenuControllerButtonLoginMenuReturn() {
        return "Login";
    }

    public String getConnexionMenuControllerButtonInscription() {
        return "Register";
    }

    public String getConnexionMenuControllerLabelNewUserName() {
        return "User name :";
    }

    public String getConnexionMenuControllerLabelErrorConnexion(){
        return "Incorrect email or password";
    }

    /** MainMenuController **/
    public String getMainMenuControllerLogoutButton(){
        return "Logout";
    }

    public String getMainMenuControllerHistoryGamePlayedButton(){
        return "Game history";
    }

    /** BlackJackMenuController **/
    public String getBlackJackMenuControllerTextBetUser(){
        return "Your bet";
    }

    public String getBlackJackMenuControllerActionSurrenderButton(){
        return "Surrender";
    }

    public String getBlackJackMenuControllerActionHitButton(){
        return "Draw a card";
    }

    public String getBlackJackMenuControllerActionDoubleButton(){
        return "Double the bet";
    }

    public String getBlackjackMenuControllerActionStandButton(){
        return "Stand";
    }

    public String getBlackJackMenuControllerActionInsuranceButton(){
        return "Insurance";
    }

    public String getBlackJackMenuControllerActionSplitButton(){
        return "Split";
    }

    public String getBlackJackMenuControllerLabelErrorBetEmpty(){
        return "You have to enter a bet";
    }

    public String getBlackJackMenuControllerLabelErrorBetWrongValue(){
        return "You can only bet between 2 and 100 tokens";
    }

    public String getBlackJackMenuControllerLabelValueHand(){
        return "value of the hand : ";
    }

    /** SlotMachineMenuController **/
    public String getSlotMachineMenuControllerSymbolLemon(){
        return "Lemon";
    }

    public String getSlotMachineMenuControllerSymbolWatermelon(){
        return "watermelon";
    }

    public String getSlotMachineMenuControllerSymbolCherry(){
        return "cherry";
    }

    public String getSlotMachineMenuControllerSymbolSeven(){
        return "seven";
    }

    /** RouletteMenuController **/
    public String getRouletteMenuControllerLabelErrorTokenPosition(){
        return "You cannot place a toke here";
    }

    public String getRouletteMenuControllerLabelInformationBetTokenPart1(){
        return "Placing a token  \nSelected cells : \n";
    }

    public String getRouletteMenuControllerLabelInformationBetTokenPart2(){
        return "\n Cells bets : \n";
    }

    public String getRouletteMenuControllerGetCellsBetStringListEmpty(){
        return "No cell selected";
    }

    public String getRouletteMenuControllerGetCellsBetStringSameCellBet(){
        return "You can't bet on both \non these two cells at the same time";
    }

    public String getRouletteMenuControllerGetCellsBetStringImpossibleCombination(){
        return "this combination of cells is impossible";
    }

    /** ShopMenuController **/
    public String getShopMenuControllerAddMoneyButton(){
        return "Add money";
    }

    public String getShopMenuControllerHistoryShoppingButton(){
        return "Purchase history";
    }

    public String getShopMenuControllerExchangeButton(){
        return "Exchange";
    }

    public String getShopMenuControllerLabelErrorExchangeValid(){
        return "Your exchange has been completed";
    }

    public String getShopMenuControllerButtonExchangeToken(){ return "tokens"; }

    public String getShopMenuControllerTitleShopTokenLabel(){ return "Token exchange : "; }

    public String getShopMenuControllerTitleShopMoneyLabel(){ return "Money exchange : "; }

    /** HistoryShoppingMenuController **/
    public String getHistoryShoppingMenuControllerLabelTitle1(){
        return "History of exchanges : Token";
    }

    public String getHistoryShoppingMenuControllerLabelTitle2(){
        return "History of exchanges : Money";
    }

    /** InformationMenuController **/
    public String getInformationMenuControllerChangeEmailButton(){ return "Edit email"; }

    public String getInformationMenuControllerChangeUserNameButton(){ return "Edit user name"; }

    public String getInformationMenuControllerChangePasswordButton(){ return "Edit password"; }

    public String getInformationMenuControllerLabelErrorEmailChangeValid(){ return "The email has been modified"; }

    public String getInformationMenuControllerLabelErrorEmailWrongFormat(){ return "The email does not match the format"; }

    public String getInformationMenuControllerLabelErrorEmailSame(){ return "The email is the same"; }

    public String getInformationMenuControllerLabelErrorUserNameChangeValid(){ return "The user name has been modified"; }

    public String getInformationMenuControllerLabelErrorUserNameWrongFormat(){ return "The user name does not math the format"; }

    public String getInformationMenuControllerLabelErrorUserNameSame(){ return "The user name is the same"; }

    public String getInformationMenuControllerLabelErrorPasswordChangeValid(){ return "The password has been modified"; }

    public String getInformationMenuControllerLabelErrorPasswordWrongFormat(){ return "The password does not math the format"; }

    public String getInformationMenuControllerLabelErrorPasswordSame(){ return "The password is the same"; }

    /** HistoryGamePlayed **/
    public String getHistoryGamePlayedNoGameRegister(){ return "There are no recorded game"; }

    public String getHistoryGamePlayedTitleLabelBlackJack(){ return "Game history : Black Jack"; }

    public String getHistoryGamePlayedTitleLabelSlotMachine(){ return "Game history : Slot machine"; }

    public String getHistoryGamePlayedTitleLabelRoulette(){ return "Game history : Roulette"; }

    /** SettingMenuController **/
    public String getSettingMenuControllerTitleLabel(){ return "Settings"; }

    public String getSettingMenuControllerSoundVolumeTitleLabel(){ return "Sound volume : "; }

    public String getSettingMenuControllerBackgroundAnimation(){ return "Background animation :"; }

    public String getSettingMenuControllerLanguage(){ return "Language :"; }

    /** RuleMenuController **/
    public String getRuleFileNameBlackJack(){ return "rule_blackJack_english.txt"; }

    public String getLabelTitleBlackJack(){ return "Black Jack rules"; }

    public String getRuleFileNameRoulette(){ return "rule_roulette_english.txt"; }

    public String getLabelTitleRoulette(){ return "Roulette rules"; }

    public String getRuleFileNameSlotMachine(){ return "rule_slotMachine_english.txt"; }

    public String getLabelTitleSlotMachine(){ return "Slot machine rules"; }

    /** BuyingMoneyMenuController **/
    public String getBuyingMoneyMenuControllerTitleLabel(){
        return "Add money";
    }

    public String getBuyingMoneyMenuControllerLabelErrorWrongValue(){ return "A strictly positive value is required"; }

    public String getBuyingMoneyMenuControllerLabelErrorFieldEmpty(){ return "The field is empty"; }

    public String getBuyingMoneyMenuControllerLabelErrorMoneyAdd(){ return "of money have been added"; }

    /** **/
    public String getLabelToken(){
        return "Tokens : ";
    }

    public String getLabelMoney(){
        return "Money :";
    }

    public String getLabelProfit(){
        return "Gain : ";
    }

    public String getLabelPlayer(){
        return "Player : ";
    }

    public String getLabelUserName(){ return "User name :"; }

    public String getLabelMail() {
        return "Email :";
    }

    public String getLabelPassword() {
        return "Password :";
    }

    public String getQuitButton(){
        return "Quit";
    }

    public String getNewPartyButton(){
        return "New party";
    }

    public String getBetButton(){
        return "Bet";
    }

    public String getLabelLog(){
        return "Log";
    }

    public String getLabelErrorTokenNotEnough(){
        return "You don't have enough tokens";
    }

    public String getLabelErrorMoneyNotEnough(){
        return "You don't have enough money";
    }

    public String getInformation(){
        return "Information";
    }

    public String getStartingGameButton(){
        return "Launch";
    }

    public String getLabelErrorIntegerValue(){
        return "You have to bet a integer value";
    }

    public String getLabelErrorNotEnoughTokenBet(){
        return "You must place at least one token";
    }

    public String getToken(){
        return "tokens";
    }

    public String getTitleShopToken(){
        return "Token exchange : ";
    }

    public String getTitleShopMoney(){
        return "Money exchange : ";
    }

    public String getLabelErrorMailAlreadyUsed(){ return "This email is already used"; }

    public String getLabelErrorEmptyField(){
        return "One or more fields are empty";
    }

    public String getHistorySearchUserButton(){
        return "Research";
    }

    public String getGameBlackJackButton(){ return "Black Jack"; }

    public String getGameSlotMachineButton(){return "Slot Machine"; }

    public String getGameRouletteButton(){ return "Roulette"; }

    public String getAddButton(){
        return "Add";
    }
}
