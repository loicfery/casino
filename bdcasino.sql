CREATE TABLE IF NOT EXISTS Utilisateur (IdUser_Utilisateur BIGINT AUTO_INCREMENT,
                                        NameUser_Utilisateur VARCHAR(50),
    MailUser_Utilisateur VARCHAR(50),
    Password_Utilisateur VARCHAR(20),
    Money_Utilisateurs FLOAT,
    Token_Utilisateurs BIGINT,
    PRIMARY KEY (IdUser_Utilisateur,MailUser_Utilisateur)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriqueAchatJetons (idAchat_HistoriqueAchatJetons BIGINT AUTO_INCREMENT,
                                                  MailUser_HistoriqueAchatJetons VARCHAR(50),
    PrixJetons_HistoriqueAchatJetons FLOAT,
    ArgentsGagnes_HistoriqueAchatJetons BIGINT,
    PRIMARY KEY (idAchat_HistoriqueAchatJetons)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Jeux (IdJeux_Jeux BIGINT AUTO_INCREMENT,
                                 NomJeu_Jeux VARCHAR(40),
    PRIMARY KEY (IdJeux_Jeux)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriqueEchangeArgents (idEchange_HistoriqueEchangeArgents BIGINT AUTO_INCREMENT,
                                                     MailUser_HistoriqueEchangeArgents VARCHAR(50),
    PrixArgents_HistoriqueEchangeArgents BIGINT,
    jetonsGagnes_HistoriqueEchangeArgents BIGINT,
    PRIMARY KEY (idHistoEchange_HistoriqueEchangeArgents)) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HistoriquePartiesJouees (IdPartie_HistoriquePartiesJouees BIGINT AUTO_INCREMENT,
                                                    MailUser_HistoriquePartiesJouees VARCHAR(50),
    NomJeu_HistoriquePartiesJouees VARCHAR(50),
    TokensGagnes_HistoriquePartiesJouees FLOAT,
    PRIMARY KEY (IdPartie_HistoriquePartiesJouees)) ENGINE=InnoDB;

ALTER TABLE HistoriqueAchatJetons ADD CONSTRAINT FK_HistoriqueAchatJetons_MailUser_HistoriqueAchatJetons FOREIGN KEY (MailUser_HistoriqueAchatJetons) REFERENCES Utilisateur (MailUser_Utilisateur);
ALTER TABLE HistoriqueEchangeArgents ADD CONSTRAINT FK_HistoriqueEchangeArgents_MailUser_HistoriqueAchatJetons FOREIGN KEY (MailUser_HistoriqueEchangeArgents) REFERENCES Utilisateur (MailUser_Utilisateur);
ALTER TABLE HistoriquePartiesJouees ADD CONSTRAINT FK_HistoriquePartiesJouees_MailUser_HistoriquePartiesJouees FOREIGN KEY (MailUser_HistoriquePartiesJouees) REFERENCES Utilisateur (MailUser_Utilisateur);
ALTER TABLE HistoriquePartiesJouees ADD CONSTRAINT FK_HistoriquePartiesJouees_NomJeu_HistoriquePartiesJouees FOREIGN KEY (NomJeu_HistoriquePartiesJouees) REFERENCES Jeux (NomJeu_Jeux);

ALTER TABLE Complete ADD CONSTRAINT FK_Complete_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Complete ADD CONSTRAINT FK_Complete_idUser_HistoriqueAchat FOREIGN KEY (idUser_HistoriqueAchat) REFERENCES HistoriqueAchat (idUser_HistoriqueAchat);
ALTER TABLE Echange ADD CONSTRAINT FK_Echange_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Echange ADD CONSTRAINT FK_Echange_idHistoEchange_HistoriqueEchange FOREIGN KEY (idHistoEchange_HistoriqueEchange) REFERENCES HistoriqueEchange (idHistoEchange_HistoriqueEchange);
ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_Utilisateurs FOREIGN KEY (IdUser_Utilisateurs) REFERENCES Utilisateurs (IdUser_Utilisateurs);
ALTER TABLE Consulte ADD CONSTRAINT FK_Consulte_IdUser_HistoriquePartiesJouees FOREIGN KEY (IdUser_HistoriquePartiesJouees) REFERENCES HistoriquePartiesJouees (IdUser_HistoriquePartiesJouees);
