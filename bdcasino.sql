CREATE TABLE IF NOT EXISTS Utilisateurs (IdUser_Utilisateurs BIGINT AUTO_INCREMENT,
NameUser_Utilisateurs VARCHAR(50),
MailUser_Utilisateurs VARCHAR(50),
Password_Utilisateurs VARCHAR(20),
Money_Utilisateurs FLOAT,
Token_HistoriquePartiesJouees BIGINT,
PRIMARY KEY (IdUser_Utilisateurs)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriqueAchat (idUser_HistoriqueAchat BIGINT AUTO_INCREMENT,
idAchat_HistoriqueAchat BIGINT,
prixAchat_HistoriqueAchat FLOAT,
nbAchat_HistoriqueAchat BIGINT,
PRIMARY KEY (idUser_HistoriqueAchat)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Jeux (IdJeux_Jeux BIGINT AUTO_INCREMENT,
NomJeu_Jeux VARCHAR(40),
PRIMARY KEY (IdJeux_Jeux)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriqueEchange (idHistoEchange_HistoriqueEchange BIGINT AUTO_INCREMENT,
idUser_HistoriqueEchange BIGINT,
nbEchange_HistoriqueEchange BIGINT,
sommeEchange_HistoriqueEchange BIGINT,
PRIMARY KEY (idHistoEchange_HistoriqueEchange)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriquePartiesJouees (IdUser_HistoriquePartiesJouees BIGINT AUTO_INCREMENT,
nbParties_HistoriquePartiesJouees BIGINT,
TokenWin_HistoriquePartiesJouees FLOAT,
PRIMARY KEY (IdUser_HistoriquePartiesJouees)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Joue (IdUser_Utilisateurs BIGINT,
IdJeux_Jeux BIGINT NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 IdJeux_Jeux)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Complete (IdUser_Utilisateurs BIGINT,
idUser_HistoriqueAchat BIGINT NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 idUser_HistoriqueAchat)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Echange (IdUser_Utilisateurs  BIGINT,
idHistoEchange_HistoriqueEchange BIGINT NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 idHistoEchange_HistoriqueEchange)) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS Consulte (IdUser_Utilisateurs  BIGINT,
IdUser_HistoriquePartiesJouees BIGINT NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 IdUser_HistoriquePartiesJouees)) ENGINE=InnoDB;

ALTER TABLE Joue ADD CONSTRAINT FK_Joue_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);

ALTER TABLE Joue ADD CONSTRAINT FK_Joue_IdJeux_Jeux FOREIGN KEY (IdJeux_Jeux) REFERENCES Jeux (IdJeux_Jeux);
ALTER TABLE Complete ADD CONSTRAINT FK_Complete_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Complete ADD CONSTRAINT FK_Complete_idUser_HistoriqueAchat FOREIGN KEY (idUser_HistoriqueAchat) REFERENCES HistoriqueAchat (idUser_HistoriqueAchat);
ALTER TABLE Echange ADD CONSTRAINT FK_Echange_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Echange ADD CONSTRAINT FK_Echange_idHistoEchange_HistoriqueEchange FOREIGN KEY (idHistoEchange_HistoriqueEchange) REFERENCES HistoriqueEchange (idHistoEchange_HistoriqueEchange);
ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_HistoriquePartiesJouees FOREIGN KEY (IdUser_HistoriquePartiesJouees) REFERENCES HistoriquePartiesJouees (IdUser_HistoriquePartiesJouees);
