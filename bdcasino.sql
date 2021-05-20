CREATE TABLE IF NOT EXISTS Users (
    IdUser_Users BIGINT AUTO_INCREMENT,
    NameUser_Users VARCHAR(50),
    MailUser_Users VARCHAR(50),
    Password_Users VARCHAR(20),
    Money_Users FLOAT,
    Token_HistoryPartyGamed BIGINT,
    PRIMARY KEY (IdUser_Users,MailUser_Users)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoryExchangeTokens(
    Id_HistoryExchangeTokens BIGINT AUTO_INCREMENT,
    MailUser_HistoryExchangeTokens VARCHAR(50),
    TokenPrice_HistoryExchangeTokens FLOAT,
    MoneyGame_HistoryExchangeTokens BIGINT,
    PRIMARY KEY (Id_HistoryExchangeTokens)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Game (
    IdGame_Game BIGINT AUTO_INCREMENT,
    GameName_Game VARCHAR(40),
    PRIMARY KEY (IdGame_Game)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoryExchangeMoney(
    IdHistoryExchangeMoney_HistoryExchangeMoney BIGINT AUTO_INCREMENT,
    MailUser_HistoryExchangeMoney VARCHAR(50),
    MoneyPrice_HistoryExchangeMoney BIGINT,
    TokenGain_HistoryExchangeMoney BIGINT,
    PRIMARY KEY (IdHistoryExchangeMoney_HistoryExchangeMoney)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoryPartyGamed(
    IdUser_HistoryPartyGamed BIGINT AUTO_INCREMENT,
    MailUser_HistoryPartyGamed VARCHAR(50),
    GameName_HistoryPartyGamed VARCHAR(50),
    TokenGain_HistoryPartyGamed FLOAT,
    PRIMARY KEY (IdUser_HistoryPartyGamed)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ActionPlay (
    IdUser_Users BIGINT,
    IdGame_Game BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Users,IdGame_Game)) ENGINE=InnoDB,
    CONSTRAINT FK_ActionPlay_IdUser_Users FOREIGN KEY (IdUser_Users) REFERENCES Users (IdUser_Users),
    CONSTRAINT FK_ActionPlay_IdGame_Game FOREIGN KEY (IdGame_Game) REFERENCES Game (IdGame_Game);

CREATE TABLE IF NOT EXISTS Complete (
    IdUser_Users BIGINT,
    IdUser_HistoryExchangeTokens BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Users,IdUser_HistoryExchangeTokens)) ENGINE=InnoDB,
    CONSTRAINT FK_Complete_IdUser_Users FOREIGN KEY (IdUser_Users) REFERENCES Users (IdUser_Users),
    CONSTRAINT FK_Complete_idUser_HistoryExchangeTokens FOREIGN KEY (idUser_HistoryExchangeTokens) REFERENCES HistoryExchangeTokens (idUser_HistoryExchangeTokens);

CREATE TABLE IF NOT EXISTS Echange (IdUser_Users BIGINT,
    IdHistoryExchangeMoney_HistoryExchangeMoney BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Users,IdHistoryExchangeMoney_HistoryExchangeMoney)) ENGINE=InnoDB,
     CONSTRAINT FK_Echange_IdUser_Users FOREIGN KEY (IdUser_Users) REFERENCES Users (IdUser_Users),
     CONSTRAINT FK_Echange_Id_HistoryExchangeTokens_HistoryExchangeTokens FOREIGN KEY (Id_HistoryExchangeTokens_HistoryExchangeTokens) REFERENCES HistoryExchangeTokens (Id_HistoryExchangeTokens_HistoryExchangeTokens);


CREATE TABLE IF NOT EXISTS Consulte (IdUser_Users  BIGINT,
    IdUser_HistoryPartyGamed BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Users,IdUser_HistoryPartyGamed)) ENGINE=InnoDB,
    CONSTRAINT FK_Consulte_IdUser_Users FOREIGN KEY (IdUser_Users) REFERENCES Users (IdUser_Users),
    CONSTRAINT FK_Consulte_IdUser_HistoryPartyGamed FOREIGN KEY (IdUser_HistoryPartyGamed) REFERENCES HistoryPartyGamed (IdUser_HistoryPartyGamed);

    CREATE TABLE IF NOT EXISTS ExchangeMoney(id_ExchangeMoney BIGINT,
    PRIMARY KEY (Id_ExchangeMoney BIGINT NOT NULL) ENGINE=InnoDB,
    MoneyPrice_ExchangeMoney BIGINT NOT NULL,
    TokenGame_ExchangeMoney FLOAT );

    CREATE TABLE IF NOT EXISTS ExchangeToken (Id_ExchangeToken BIGINT,
        TokenPrice_ExchangeToken BIGINT NOT NULL,
        MoneyGain_ExchangeToken BIGINT,
        PRIMARY KEY (Id_ExchangeToken)) ENGINE=InnoDB;


--ALTER TABLE Joue ADD CONSTRAINT FK_J_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);

--ALTER TABLE Joue ADD CONSTRAINT FK_Joue_IdJeux_Jeux FOREIGN KEY (IdJeux_Jeux) REFERENCES Jeux (IdJeux_Jeux);
--ALTER TABLE Complete ADD CONSTRAINT FK_Complete_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
--ALTER TABLE Complete ADD CONSTRAINT FK_Complete_idUser_HistoriqueAchatJetons FOREIGN KEY (idUser_HistoriqueAchatJetons) REFERENCES HistoriqueAchatJetons (idUser_HistoriqueAchatJetons);
--ALTER TABLE Echange ADD CONSTRAINT FK_Echange_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
--ALTER TABLE Echange ADD CONSTRAINT FK_Echange_idHistoEchange_HistoriqueEchangeArgents FOREIGN KEY (idHistoEchange_HistoriqueEchangeArgents) REFERENCES HistoriqueEchange (idHistoEchange_HistoriqueEchangeArgents);
--ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
--ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_HistoriquePartiesJouees FOREIGN KEY (IdUser_HistoriquePartiesJouees) REFERENCES HistoriquePartiesJouees (IdUser_HistoriquePartiesJouees);