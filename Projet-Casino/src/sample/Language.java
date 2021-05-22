package sample;

public interface Language {

    /** ConnexionMenuController **/
    String getConnexionMenuControllerLabelTitle1();
    String getConnexionMenuControllerLabelTitle2();
    String getConnexionMenuControllerLabelEmail();
    String getConnexionMenuControllerLabelPassword();
    String getConnexionMenuControllerButtonLogin();
    String getConnexionMenuControllerButtonNewAccount();
    String getConnexionMenuControllerButtonLoginMenuReturn();
    String getConnexionMenuControllerButtonInscription();
    String getConnexionMenuControllerLabelNewPassword();
    String getConnexionMenuControllerLabelNewUserName();
    String getConnexionMenuControllerLabelErrorConnexion();

    /** MainMenuController **/
    String getMainMenuControllerLogoutButton();
    String getMainMenuControllerHistoryGamePlayedButton();

    /** BlackJackMenuController **/
    String getBlackJackMenuControllerTextBetUser();
    String getBlackJackMenuControllerActionSurrenderButton();
    String getBlackJackMenuControllerActionHitButton();
    String getBlackJackMenuControllerActionDoubleButton();
    String getBlackjackMenuControllerActionStandButton();
    String getBlackJackMenuControllerActionInsuranceButton();
    String getBlackJackMenuControllerActionSplitButton();
    String getBlackJackMenuControllerLabelErrorBetEmpty();
    String getBlackJackMenuControllerLabelErrorBetWrongValue();
    String getBlackJackMenuControllerLabelValueHand();

    /** SlotMachineMenuController **/
    String getSlotMachineMenuControllerSymbolLemon();
    String getSlotMachineMenuControllerSymbolWatermelon();
    String getSlotMachineMenuControllerSymbolCherry();
    String getSlotMachineMenuControllerSymbolSeven();

    /** RouletteMenuController **/
    String getRouletteMenuControllerLabelErrorTokenPosition();
    String getRouletteMenuControllerLabelInformationBetTokenPart1();
    String getRouletteMenuControllerLabelInformationBetTokenPart2();
    String getRouletteMenuControllerGetCellsBetStringListEmpty();
    String getRouletteMenuControllerGetCellsBetStringSameCellBet();
    String getRouletteMenuControllerGetCellsBetStringImpossibleCombination();

    /** ShopMenuController **/
    String getShopMenuControllerAddMoneyButton();
    String getShopMenuControllerHistoryShoppingButton();
    String getShopMenuControllerExchangeButton();
    String getShopMenuControllerLabelErrorExchangeValid();

    /** HistoryShoppingMenuController **/
    String getHistoryShoppingMenuControllerLabelTitle1();
    String getHistoryShoppingMenuControllerLabelTitle2();

    /** InformationMenuController **/
    String getInformationMenuControllerChangeEmailButton();
    String getInformationMenuControllerChangeUserNameButton();
    String getInformationMenuControllerLabelErrorEmailChangeValid();
    String getInformationMenuControllerLabelErrorEmailWrongFormat();
    String getInformationMenuControllerLabelErrorEmailSame();
    String getInformationMenuControllerLabelErrorUserNameChangeValid();
    String getInformationMenuControllerLabelErrorUserNameWrongFormat();
    String getInformationMenuControllerLabelErrorUserNameSame();
    String getInformationMenuControllerLabelErrorPasswordChangeValid();
    String getInformationMenuControllerLabelErrorPasswordWrongFormat();
    String getInformationMenuControllerLabelErrorPasswordSame();

    /** HistoryGamePlayed **/
    String getHistoryGamePlayedNoGameRegister();
    String getHistoryGamePlayedTitleLabelBlackJack();
    String getHistoryGamePlayedTitleLabelSlotMachine();
    String getHistoryGamePlayedTitleLabelRoulette();

    /** SettingMenuController **/
    String getSettingMenuControllerTitleLabel();
    String getSettingMenuControllerSoundVolumeTitleLabel();
    String getSettingMenuControllerBackgroundAnimation();
    String getSettingMenuControllerLanguage();

    /** RuleMenuController **/
    String getRuleFileNameBlackJack();
    String getLabelTitleBlackJack();
    String getRuleFileNameRoulette();
    String getLabelTitleRoulette();
    String getRuleFileNameSlotMachine();
    String getLabelTitleSlotMachine();

    /** BuyingMoneyMenuController **/
    String getBuyingMoneyMenuControllerTitleLabel();
    String getBuyingMoneyMenuControllerLabelErrorWrongValue();
    String getBuyingMoneyMenuControllerLabelErrorFieldEmpty();
    String getBuyingMoneyMenuControllerLabelErrorMoneyAdd();

    /** **/
    String getLabelToken();
    String getLabelMoney();
    String getLabelProfit();
    String getLabelPlayer();
    String getLabelUserName();
    String getLabelMail();
    String getQuitButton();
    String getNewPartyButton();
    String getBetButton();
    String getLabelLog();
    String getLabelErrorTokenNotEnough();
    String getLabelErrorMoneyNotEnough();
    String getInformation();
    String getStartingGameButton();
    String getLabelErrorIntegerValue();
    String getLabelErrorNotEnoughTokenBet();
    String getToken();
    String getTitleShopToken();
    String getTitleShopMoney();
    String getLabelErrorMailAlreadyUsed();
    String getLabelErrorEmptyField();
    String getHistorySearchUserButton();
    String getGameBlackJackButton();
    String getGameSlotMachineButton();
    String getGameRouletteButton();
    String getAddButton();
}
