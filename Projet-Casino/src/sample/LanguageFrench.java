package sample;

public class LanguageFrench implements Language{

    /** ConnexionMenuController **/
    public String getConnexionMenuControllerLabelTitle1() {
        return "Bienvenue au casino";
    }

    public String getConnexionMenuControllerLabelTitle2(){
        return "Nouveau Compte";
    }

    public String getConnexionMenuControllerLabelEmail() {
        return "Email";
    }

    public String getConnexionMenuControllerLabelPassword() {
        return "Mot de passe";
    }

    public String getConnexionMenuControllerButtonLogin() {
        return "Connexion";
    }

    public String getConnexionMenuControllerButtonNewAccount() {
        return "Nouveau Compte";
    }

    public String getConnexionMenuControllerButtonLoginMenuReturn() {
        return "Se connecter";
    }

    public String getConnexionMenuControllerButtonInscription() {
        return "S'inscrire";
    }

    public String getConnexionMenuControllerLabelNewUserName() {
        return "Pseudonyme :";
    }

    public String getConnexionMenuControllerLabelErrorConnexion(){
        return "L'email ou le mot de passe est incorrect";
    }

    /** MainMenuController **/
    public String getMainMenuControllerLogoutButton(){
        return "Déconnexion";
    }

    public String getMainMenuControllerHistoryGamePlayedButton(){
        return "Historique jeux";
    }

    /** BlackJackMenuController **/
    public String getBlackJackMenuControllerTextBetUser(){
        return "Votre mise";
    }

    public String getBlackJackMenuControllerActionSurrenderButton(){
        return "Abandonner";
    }

    public String getBlackJackMenuControllerActionHitButton(){
        return "Tirer une carte";
    }

    public String getBlackJackMenuControllerActionDoubleButton(){
        return "Doubler la mise";
    }

    public String getBlackjackMenuControllerActionStandButton(){
        return "Rester";
    }

    public String getBlackJackMenuControllerActionInsuranceButton(){
        return "Assurance";
    }

    public String getBlackJackMenuControllerActionSplitButton(){
        return "Partager";
    }

    public String getBlackJackMenuControllerLabelErrorBetEmpty(){
        return "Il faut entrer une mise";
    }

    public String getBlackJackMenuControllerLabelErrorBetWrongValue(){
        return "Vous ne pouvez miser qu'entre 2 et 100 jetons";
    }

    public String getBlackJackMenuControllerLabelValueHand(){
        return "valeur de la main : ";
    }

    /** SlotMachineMenuController **/
    public String getSlotMachineMenuControllerSymbolLemon(){
        return "citron";
    }

    public String getSlotMachineMenuControllerSymbolWatermelon(){
        return "pastèque";
    }

    public String getSlotMachineMenuControllerSymbolCherry(){
        return "cerise";
    }

    public String getSlotMachineMenuControllerSymbolSeven(){
        return "sept";
    }

    /** RouletteMenuController **/
    public String getRouletteMenuControllerLabelErrorTokenPosition(){
        return "Vous ne pouvez pas placer de jeton ici";
    }

    public String getRouletteMenuControllerLabelInformationBetTokenPart1(){
        return "Mise d'un jeton  \nCases sélectionnées : \n";
    }

    public String getRouletteMenuControllerLabelInformationBetTokenPart2(){
        return "\n Cases misées : \n";
    }

    public String getRouletteMenuControllerGetCellsBetStringListEmpty(){
        return "aucune case sélectionnée";
    }

    public String getRouletteMenuControllerGetCellsBetStringSameCellBet(){
        return "Vous ne pouvez pas miser \nsur ces deux cases en même temps";
    }

    public String getRouletteMenuControllerGetCellsBetStringImpossibleCombination(){
        return "Cette combinaison de case est impossible";
    }

    /** ShopMenuController **/
    public String getShopMenuControllerAddMoneyButton(){
        return "Ajouter de l'argent";
    }

    public String getShopMenuControllerHistoryShoppingButton(){
        return "Historique achats";
    }

    public String getShopMenuControllerExchangeButton(){
        return "Echanger";
    }

    public String getShopMenuControllerLabelErrorExchangeValid(){
        return "Votre échange a bien été effectué";
    }

    public String getShopMenuControllerButtonExchangeToken(){ return "jetons"; }

    public String getShopMenuControllerTitleShopTokenLabel(){ return "Echange de jeton : "; }

    public String getShopMenuControllerTitleShopMoneyLabel(){ return "Echange d'argent : "; }

    /** HistoryShoppingMenuController **/
    public String getHistoryShoppingMenuControllerLabelTitle1(){
        return "Historique des échanges : Jeton";
    }

    public String getHistoryShoppingMenuControllerLabelTitle2(){
        return "Historique des échanges : Argent";
    }

    /** InformationMenuController **/
    public String getInformationMenuControllerChangeEmailButton(){ return "Modifier email"; }

    public String getInformationMenuControllerChangeUserNameButton(){ return "Modifier pseudonyme"; }

    public String getInformationMenuControllerChangePasswordButton(){ return "Modifier mot de passe"; }

    public String getInformationMenuControllerLabelErrorEmailChangeValid(){ return "L'email a été modifié"; }

    public String getInformationMenuControllerLabelErrorEmailWrongFormat(){ return "L'email ne correspond pas au format"; }

    public String getInformationMenuControllerLabelErrorEmailSame(){ return "L'email est le même"; }

    public String getInformationMenuControllerLabelErrorUserNameChangeValid(){ return "Le pseudonyme a été modifié"; }

    public String getInformationMenuControllerLabelErrorUserNameWrongFormat(){ return "Le pseudonyme ne correspond pas au format"; }

    public String getInformationMenuControllerLabelErrorUserNameSame(){ return "Le pseudonyme est le même"; }

    public String getInformationMenuControllerLabelErrorPasswordChangeValid(){ return "Le mot de passe a été modifié"; }

    public String getInformationMenuControllerLabelErrorPasswordWrongFormat(){ return "Le mot de passe ne correspond pas au format"; }

    public String getInformationMenuControllerLabelErrorPasswordSame(){ return "Le mot de passe est le même"; }

    /** HistoryGamePlayed **/
    public String getHistoryGamePlayedNoGameRegister(){ return "Il n'y a aucune partie enregistrée"; }

    public String getHistoryGamePlayedTitleLabelBlackJack(){ return "Historique de jeux : Black Jack"; }

    public String getHistoryGamePlayedTitleLabelSlotMachine(){ return "Historique de jeux : Machine à sous"; }

    public String getHistoryGamePlayedTitleLabelRoulette(){ return "Historique de jeux : Roulette"; }

    /** SettingMenuController **/
    public String getSettingMenuControllerTitleLabel(){ return "Paramètres"; }

    public String getSettingMenuControllerSoundVolumeTitleLabel(){ return "volume son : "; }

    public String getSettingMenuControllerBackgroundAnimation(){ return "Animation arrière plan :"; }

    public String getSettingMenuControllerLanguage(){ return "Langue :"; }

    /** RuleMenuController **/
    public String getRuleFileNameBlackJack(){ return "rule_blackJack_french.txt"; }

    public String getLabelTitleBlackJack(){ return "Règles du Black Jack"; }

    public String getRuleFileNameRoulette(){ return "rule_roulette_french.txt"; }

    public String getLabelTitleRoulette(){ return "Règles de la roulette"; }

    public String getRuleFileNameSlotMachine(){ return "rule_slotMachine_french.txt"; }

    public String getLabelTitleSlotMachine(){ return "Règles des machines à sous"; }

    /** BuyingMoneyMenuController **/
    public String getBuyingMoneyMenuControllerTitleLabel(){
        return "Ajouter de l'argent";
    }

    public String getBuyingMoneyMenuControllerLabelErrorWrongValue(){ return "Il faut une valeur strictement positive"; }

    public String getBuyingMoneyMenuControllerLabelErrorFieldEmpty(){ return "Le champ est vide"; }

    public String getBuyingMoneyMenuControllerLabelErrorMoneyAdd(){ return "d'argent ont été ajouté"; }

    /** **/
    public String getLabelToken(){
        return "Jetons : ";
    }

    public String getLabelMoney(){
        return "Argent :";
    }

    public String getLabelProfit(){
        return "Gain : ";
    }

    public String getLabelPlayer(){
        return "Joueur : ";
    }

    public String getLabelUserName(){ return "Pseudonyme :"; }

    public String getLabelMail() { return "Email :"; }

    public String getLabelPassword() {
        return "Mot de passe :";
    }

    public String getQuitButton(){
        return "Quitter";
    }

    public String getNewPartyButton(){
        return "Nouvelle partie";
    }

    public String getBetButton(){
        return "Miser";
    }

    public String getLabelLog(){
        return "Log";
    }

    public String getLabelErrorTokenNotEnough(){
        return "Vous n'avez pas assez de jeton";
    }

    public String getLabelErrorMoneyNotEnough(){
        return "Vous n'avez pas assez d'argent";
    }

    public String getInformation(){
        return "Informations";
    }

    public String getStartingGameButton(){
        return "Lancer";
    }

    public String getLabelErrorIntegerValue(){
        return "Il faut miser une valeur entière";
    }

    public String getLabelErrorNotEnoughTokenBet(){
        return "Il faut placer un jeton au minimum";
    }

    public String getToken(){
        return "jetons";
    }

    public String getTitleShopToken(){
        return "Echange de jeton : ";
    }

    public String getTitleShopMoney(){
        return "Echange d'argent : ";
    }

    public String getLabelErrorMailAlreadyUsed(){ return "Cette email est déjà utilisé"; }

    public String getLabelErrorEmptyField(){
        return "Un ou plusieurs champs sont vides";
    }

    public String getHistorySearchUserButton(){
        return "Rechercher";
    }

    public String getGameBlackJackButton(){ return "Black Jack"; }

    public String getGameSlotMachineButton(){return "Machine à sous"; }

    public String getGameRouletteButton(){ return "Roulette"; }

    public String getAddButton(){
        return "Ajouter";
    }
}
