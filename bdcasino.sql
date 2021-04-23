DROP TABLE IF EXISTS Utilisateurs ; 
CREATE TABLE Utilisateurs (IdUser_Utilisateurs BIGINT AUTO_INCREMENT NOT NULL,
NameUser_Utilisateurs VARCHAR2(50),
MailUser_Utilisateurs VARCHAR2(50),
Password_Utilisateurs VARCHAR2(20),
Money_Utilisateurs FLOAT,
Token_HistoriquePartiesJouees BIGINT,
PRIMARY KEY (IdUser_Utilisateurs)) ENGINE=InnoDB;

DROP TABLE IF EXISTS HistoriqueAchat ;
CREATE TABLE HistoriqueAchat (idUser_HistoriqueAchat BIGINT AUTO_INCREMENT NOT NULL,
idAchat_HistoriqueAchat BIGINT,
prixAchat_HistoriqueAchat FLOAT,
nbAchat_HistoriqueAchat BIGINT,
PRIMARY KEY (idUser_HistoriqueAchat)) ENGINE=InnoDB;

DROP TABLE IF EXISTS Jeux ;
CREATE TABLE Jeux (IdJeux_Jeux BIGINT AUTO_INCREMENT NOT NULL,
NomJeu_Jeux VARCHAR2(40),
PRIMARY KEY (IdJeux_Jeux)) ENGINE=InnoDB;

DROP TABLE IF EXISTS HistoriqueEchange ;
CREATE TABLE HistoriqueEchange (idHistoEchange_HistoriqueEchange BIGINT AUTO_INCREMENT NOT NULL,
idUser_HistoriqueEchange BIGINT,
nbEchange_HistoriqueEchange BIGINT,
sommeEchange_HistoriqueEchange BIGINT,
PRIMARY KEY (idHistoEchange_HistoriqueEchange)) ENGINE=InnoDB;

DROP TABLE IF EXISTS HistoriquePartiesJouees ;
CREATE TABLE HistoriquePartiesJouees (IdUser_HistoriquePartiesJouees BIGINT AUTO_INCREMENT NOT NULL,
nbParties_HistoriquePartiesJouees BIGINT,
TokenWin_HistoriquePartiesJouees FLOAT,
PRIMARY KEY (IdUser_HistoriquePartiesJouees)) ENGINE=InnoDB;

DROP TABLE IF EXISTS Joue ;
CREATE TABLE Joue (IdUser_Utilisateurs **NOT FOUND** AUTO_INCREMENT NOT NULL,
IdJeux_Jeux **NOT FOUND** NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 IdJeux_Jeux)) ENGINE=InnoDB;

DROP TABLE IF EXISTS Complete ;
CREATE TABLE Complete (IdUser_Utilisateurs **NOT FOUND** AUTO_INCREMENT NOT NULL,
idUser_HistoriqueAchat **NOT FOUND** NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 idUser_HistoriqueAchat)) ENGINE=InnoDB;

DROP TABLE IF EXISTS Echange ;
CREATE TABLE Echange (IdUser_Utilisateurs **NOT FOUND** AUTO_INCREMENT NOT NULL,
idHistoEchange_HistoriqueEchange **NOT FOUND** NOT NULL,
PRIMARY KEY (IdUser_Utilisateurs,
 idHistoEchange_HistoriqueEchange)) ENGINE=InnoDB;

DROP TABLE IF EXISTS Consulte ;
CREATE TABLE Consulte (IdUser_Utilisateurs **NOT FOUND** AUTO_INCREMENT NOT NULL,
IdUser_HistoriquePartiesJouees **NOT FOUND** NOT NULL,
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
