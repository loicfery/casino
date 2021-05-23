CREATE TABLE IF NOT EXISTS Users (
    Id_Users BIGINT AUTO_INCREMENT,
    NameUser_Users VARCHAR(50) NOT NULL,
    MailUser_Users VARCHAR(50),
    Password_Users VARCHAR(20) NOT NULL,
    Rank_Users VARCHAR(20) NOT NULL,
    Money_Users FLOAT NOT NULL,
    Token_HistoryPartyGamed BIGINT NOT NULL,
    PRIMARY KEY (Id_Users,MailUser_Users)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoryExchangeTokens(
    Id_HistoryExchangeTokens BIGINT AUTO_INCREMENT,
    MailUser_HistoryExchangeTokens VARCHAR(50) NOT NULL,
    TokenPrice_HistoryExchangeTokens FLOAT NOT NULL,
    MoneyGain_HistoryExchangeTokens BIGINT NOT NULL,
    Date_HistoryExchangeTokens DATE NOT NULL,
    PRIMARY KEY (Id_HistoryExchangeTokens)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Game (
    Id_Game BIGINT AUTO_INCREMENT,
    GameName_Game VARCHAR(40) NOT NULL,
    PRIMARY KEY (Id_Game)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoryExchangeMoney(
    Id_HistoryExchangeMoney BIGINT AUTO_INCREMENT,
    MailUser_HistoryExchangeMoney VARCHAR(50) NOT NULL,
    MoneyPrice_HistoryExchangeMoney BIGINT NOT NULL,
    TokenGain_HistoryExchangeMoney BIGINT NOT NULL,
    Date_HistoryExchangeMoney DATE NOT NULL,
    PRIMARY KEY (Id_HistoryExchangeMoney)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoryPartyGamed(
    Id_HistoryPartyGamed BIGINT AUTO_INCREMENT,
    MailUser_HistoryPartyGamed VARCHAR(50) NOT NULL,
    GameName_HistoryPartyGamed VARCHAR(50) NOT NULL,
    TokenGain_HistoryPartyGamed FLOAT NOT NULL,
    Date_HistoryPartyGamed DATE NOT NULL,
    PRIMARY KEY (Id_HistoryPartyGamed)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ActionPlay (
    Id_Users BIGINT,
    Id_Game BIGINT NOT NULL,
    PRIMARY KEY (Id_Users,Id_Game),
    CONSTRAINT FK_ActionPlay_Id_Users FOREIGN KEY (Id_Users) REFERENCES Users (Id_Users),
    CONSTRAINT FK_ActionPlay_Id_Game FOREIGN KEY (Id_Game) REFERENCES Game (Id_Game)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Complete (
    Id_Users BIGINT,
    Id_HistoryExchangeTokens BIGINT NOT NULL,
    PRIMARY KEY (Id_Users,Id_HistoryExchangeTokens),
    CONSTRAINT FK_Complete_Id_Users FOREIGN KEY (Id_Users) REFERENCES Users (Id_Users),
    CONSTRAINT FK_Complete_id_HistoryExchangeTokens FOREIGN KEY (Id_HistoryExchangeTokens) REFERENCES HistoryExchangeTokens (Id_HistoryExchangeTokens)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Echange (
    Id_Users BIGINT,
    Id_HistoryExchangeMoney BIGINT NOT NULL,
    PRIMARY KEY (Id_Users,Id_HistoryExchangeMoney),
     CONSTRAINT FK_Echange_Id_Users FOREIGN KEY (Id_Users) REFERENCES Users (Id_Users),
     CONSTRAINT FK_Echange_Id_HistoryExchangeTokens FOREIGN KEY (Id_HistoryExchangeMoney) REFERENCES HistoryExchangeMoney (Id_HistoryExchangeMoney)) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS Consulte (
    Id_Users  BIGINT,
    Id_HistoryPartyGamed BIGINT NOT NULL,
    PRIMARY KEY (Id_Users,Id_HistoryPartyGamed),
    CONSTRAINT FK_Consulte_Id_Users FOREIGN KEY (Id_Users) REFERENCES Users (Id_Users),
    CONSTRAINT FK_Consulte_Id_HistoryPartyGamed FOREIGN KEY (Id_HistoryPartyGamed) REFERENCES HistoryPartyGamed (Id_HistoryPartyGamed)) ENGINE=InnoDB;

    CREATE TABLE IF NOT EXISTS ExchangeMoney(
    Id_ExchangeMoney BIGINT AUTO_INCREMENT,
    MoneyPrice_ExchangeMoney BIGINT NOT NULL,
    TokenGame_ExchangeMoney FLOAT,
    PRIMARY KEY (Id_ExchangeMoney)) ENGINE=InnoDB;

    CREATE TABLE IF NOT EXISTS ExchangeToken (
        Id_ExchangeToken BIGINT AUTO_INCREMENT,
        TokenPrice_ExchangeToken BIGINT NOT NULL,
        MoneyGain_ExchangeToken BIGINT,
        PRIMARY KEY (Id_ExchangeToken)) ENGINE=InnoDB;