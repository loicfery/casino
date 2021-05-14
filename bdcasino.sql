CREATE TABLE IF NOT EXISTS Utilisateurs (
    IdUser_Utilisateurs BIGINT AUTO_INCREMENT,
    NameUser_Utilisateurs VARCHAR(50),
    MailUser_Utilisateurs VARCHAR(50),
    Password_Utilisateurs VARCHAR(20),
    Money_Utilisateurs FLOAT,
    Token_HistoriquePartiesJouees BIGINT,
    PRIMARY KEY (IdUser_Utilisateurs,MailUser_Utilisateurs)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriqueAchatJetons (
    IdAchat_HistoriqueAchatJetons BIGINT AUTO_INCREMENT,
    MailUser_HistoriqueAchatJetons VARCHAR(50),
    PrixJeton_HistoriqueAchatJetons FLOAT,
    ArgentGagne_HistoriqueAchatJetons BIGINT,
    PRIMARY KEY (idAchat_HistoriqueAchatJetons)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Jeux (
    IdJeux_Jeux BIGINT AUTO_INCREMENT,
    NomJeux_Jeux VARCHAR(40),
    PRIMARY KEY (IdJeux_Jeux)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriqueEchangeArgent (
    IdHistoriqueEchange_HistoriqueEchangeArgent BIGINT AUTO_INCREMENT,
    MailUser_HistoriqueEchangeArgent VARCHAR(50),
    PrixArgent_HistoriqueEchangeArgent BIGINT,
    JetonGagne_HistoriqueEchangeArgent BIGINT,
    PRIMARY KEY (idHistoriqueEchange_HistoriqueEchangeArgent)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriquePartiesJouees (
    IdUser_HistoriquePartiesJouees BIGINT AUTO_INCREMENT,
    MailUser_HistoriquePartiesJouees VARCHAR(50),
    NomJeux_HistoriquePartiesJouees VARCHAR(50),
    TokenGagne_HistoriquePartiesJouees FLOAT,
    PRIMARY KEY (IdUser_HistoriquePartiesJouees)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Joue (
    IdUser_Utilisateurs BIGINT,
    IdJeux_Jeux BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Utilisateurs,IdJeux_Jeux)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Complete (
    IdUser_Utilisateurs BIGINT,
    IdUser_HistoriqueAchat BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Utilisateurs,IdUser_HistoriqueAchat)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Echange (IdUser_Utilisateurs  BIGINT,
    IdHistoEchange_HistoriqueEchange BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Utilisateurs,IdHistoEchange_HistoriqueEchange)) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS Consulte (IdUser_Utilisateurs  BIGINT,
    IdUser_HistoriquePartiesJouees BIGINT NOT NULL,
    PRIMARY KEY (IdUser_Utilisateurs,IdUser_HistoriquePartiesJouees)) ENGINE=InnoDB;

ALTER TABLE Joue ADD CONSTRAINT FK_Joue_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);

ALTER TABLE Joue ADD CONSTRAINT FK_Joue_IdJeux_Jeux FOREIGN KEY (IdJeux_Jeux) REFERENCES Jeux (IdJeux_Jeux);
ALTER TABLE Complete ADD CONSTRAINT FK_Complete_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Complete ADD CONSTRAINT FK_Complete_idUser_HistoriqueAchat FOREIGN KEY (idUser_HistoriqueAchat) REFERENCES HistoriqueAchat (idUser_HistoriqueAchat);
ALTER TABLE Echange ADD CONSTRAINT FK_Echange_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Echange ADD CONSTRAINT FK_Echange_idHistoEchange_HistoriqueEchange FOREIGN KEY (idHistoEchange_HistoriqueEchange) REFERENCES HistoriqueEchange (idHistoEchange_HistoriqueEchange);
ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_HistoriquePartiesJouees FOREIGN KEY (IdUser_HistoriquePartiesJouees) REFERENCES HistoriquePartiesJouees (IdUser_HistoriquePartiesJouees);