--Inizio transazione
BEGIN;
--questo comando indica di differire tutti
SET CONSTRAINTS ALL DEFERRED;

INSERT INTO Rubrica VALUES 
('Utente_1'),
('Utente_2');


INSERT INTO Contatto(Nome,SecondoNome,Cognome,Foto,Rubrica_FK) VALUES 
('Alfredo',NULL,'Esposito',NULL,'Utente_1'),
('Maria',NULL,'Rossi',NULL,'Utente_1'),
('Giuseppe','Franco','Esposito',NULL,'Utente_1'),
('Romana',NULL,'Lombardi',NULL,'Utente_1'),
('Angelo',NULL,'Castiglione',NULL,'Utente_1'),
('Silvio',NULL,'Luccio',NULL,'Utente_1'),
('Giacomo',NULL,'Botte',NULL,'Utente_1'),
('Valerio',NULL,'Mazza',NULL,'Utente_1'),
('Massimo',NULL,'Russo',NULL,'Utente_1'),
('Vincenzo',NULL,'Sosta',NULL,'Utente_1'),
('Francesco','Pio','Colombo',NULL,'Utente_1'),
('Emanuela',NULL,'Giordano',NULL,'Utente_1'),

('Pasquale','Enrico','Amabile',NULL,'Utente_2'),
('Dario',NULL,'Scolaro',NULL,'Utente_2'),
('Elia',NULl,'Tirato',NULL,'Utente_2'),
('Matteo',NULL,'Mavio',NULL,'Utente_2'),
('Antonella',NULL,'Falcone',NULL,'Utente_2'),
('Lucio',NULL,'Domo',NULL,'Utente_2'),
('Federico','Antonio','Piuma',NULL,'Utente_2');


INSERT INTO Gruppo(Nome,Rubrica_FK) VALUES
('Gruppo1','Utente_1'),
('Friends','Utente_1'),
('Gruppo2','Utente_2');

INSERT INTO Composizione VALUES 
(1,1),
(2,1),
(3,1),
(13,3),
(14,3);

INSERT INTO Account(Fornitore,Indirizzoemail,FraseStato,Nickname) VALUES 
('Whatsapp','Alfredo.Esposito@gmail.com','Hey there I am using whatsapp','Alfredo'),
('Telegram','AlfredoEspo33@gmail.com',NULL,'Alfre33'),
('Linkedin','AlfredoEspo33@gmail.com',NULL,'Alfre33'),
('Whatsapp','rossimaria@gmail.com',NULL,'Maria Rossi'),
('Whatsapp','Giuseppe789@gmail.com',NULL,'Esposito Giuseppe'),
('Instagram','RomanaLomb@gmail.com',NULL,'Lombardi_Romana'),
('Twitter','RomanaLomb@gmail.com',NULL,'Romana_Lomba_98'),
('Whatsapp','Silvio20@gmail.com','Hey there I am using whatsapp','Silvio Luccio'),
('Instagram','Silvio20@gmail.com',NULL,'Silvuccio'),
('Telegram','Silvio20@gmail.com',NULL,'Silvio_L'),
('Unina','S.Luccio@studenti.unina.it',NULL,'Luccio Silvio'),
('Whatsapp','Valerio@libero.it',NULL,'Valerio Mazza'),
('Unina','MazzaValerio@studenti.unina.it',NULL,'Valerio Mazza'),
('Whatsapp','Pasquale@gmail.com',NULL,'Pasquale Amabile'),
('Instagram','PasquiAma@gmail.com',NULL,'Pasquale_Amabile'),
('Telegram','PAmabile@libero.it',NULL,'Pasqua'),
('Unina','Pasquale.Amabile@studenti.unina.it',NULL,'Amabile Pasquale');


INSERT INTO Indirizzo(Via,Città,Nazione,CAP,Descrizione,Contatto_FK) VALUES 
('Scarlatti','Napoli','Italia','80127','Principale',1),
('Terracina','Napoli','Italia','80125','Secondario',1),
('Colosseo','Roma','Italia','00031','Secondario',1),
('Scarlatti','Napoli','Italia','80127','Principale',2),
('Corso Europa','Napoli','Italia','80127','Principale',3),
('Latina','Roma','Italia','00031','Principale',4),
('Bagnoli','Napoli','Italia','80124','Principale',5),
('Posillipo','Napoli','Italia','80123','Principale',6),
('Pisanelli','Napoli','Italia','80128','Secondario',6),
('Giacomo','Napoli','Italia','80120','Principale',7),
('Palinsesto','Catania','Italia','90345','Principale',8),
('Monte','Bergamo','Italia','46789','Principale',9),
('Archimede','Napoli','Italia','80126','Principale',10),
('Tolomeo','Napoli','Italia','80123','Principale',11),
('Carlo Alberto','Napoli','Italia','80045','Principale',12),
('Tempo','Palermo','Italia','90234','Principale',13),
('Bacone','Trieste','Italia','20900','Principale',14),
('Mella','Catania','Italia','30987','Principale',15),
('Banda','Milano','Italia','20390','Principale',16),
('Cintone','Napoli','Italia','80129','Principale',17),
('Toledo','Napoli','Italia','80128','Principale',18),
('Rossi','Roma','Italia','00031','Principale',19);

INSERT INTO Telefono(Numero,Descrizione,Contatto_FK) VALUES
('3387889123','Mobile',1),
('0817881100','Fisso',1),
('3333838901','Mobile',2),
('0817885566','Fisso',2),
('3387889123','Mobile',3),
('0815552300','Fisso',3),
('3394989345','Mobile',4),
('0815550000','Fisso',4),
('3722935674','Mobile',5),
('0817258844','Fisso',5),
('3842935674','Mobile',6),
('0817250044','Fisso',6),
('3359018293','Mobile',7),
('0815889090','Fisso',7),
('3904657689','Mobile',8),
('0817208090','Fisso',8),
('3382112090','Mobile',9),
('0812026060','Fisso',9),
('3381112090','Mobile',10),
('0812020001','Fisso',10),
('3382002090','Mobile',11),
('0815990202','Fisso',11),
('3394947467','Mobile',12),
('0813459009','Fisso',12),
('3389991212','Mobile',13),
('0815996070','Fisso',13),
('3339923465','Mobile',14),
('0817984920','Fisso',14),
('3380923711','Mobile',15),
('0815001001','Fisso',15),
('3332112090','Mobile',16),
('0815886060','Fisso',16),
('3381818900','Mobile',17),
('0815882900','Fisso',17),
('3359090127','Mobile',18),
('0812003456','Fisso',18),
('3320012090','Mobile',19),
('0815887060','Fisso',19);

INSERT INTO Email(IndirizzoEmail,Descrizione,Contatto_FK) VALUES 
('Alfredo.Esposito@gmail.com','Primaria',1),
('AlfredoEspo33@gmail.com','Secondaria',1),
('rossimaria@gmail.com','Primaria',2),
('Giuseppe789@gmail.com','Primaria',3),
('RomanaLomb@gmail.com','Primaria',4),
('Silvio20@gmail.com','Primaria',6),
('Valerio@libero.it','Lavoro',8),
('MazzaValerio@studenti.unina.it','Universitaria',8),
('Pasquale@gmail.com','Primaria',13),
('PasquiAma@gmail.com','Ausiliaria',13),
('PAmabile@libero.it','Secondaria',13),
('Pasquale.Amabile@studenti.unina.it','Universitaria',13);

commit;