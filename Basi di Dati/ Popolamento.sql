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


INSERT INTO Indirizzo(Via,Città,Nazione,CAP,Descrizione) VALUES 
('Scarlatti','Napoli','Italia',80127,'Principale'),
('Terracina','Napoli','Italia',80125,'Secondario'),
('Corso Europa','Napoli','Italia',80127,'Principale'),
('Latina','Roma','Italia',00031,'Principale'),
('Bagnoli','Napoli','Italia',80124,'Principale'),
('Posillipo','Napoli','Italia',80123,'Principale'),
('Pisanelli','Napoli','Italia',80128,'Secondario'),
('Archimede','Napoli','Italia',80126,'Principale'),
('Carlo Alberto','Napoli','Italia',80045,'Principale');

INSERT INTO Telefono(Numero,Descrizione) VALUES
(3387889123,'Mobile'),
(3333838901,'Mobile'),
(0817885566,'Fisso'),
(0815552300,'Fisso'),
(3394989345,'Mobile'),
(3722935674,'Mobile'),
(0817258844,'Fisso'),
(3359018293,'Mobile'),
(3904657689,'Mobile'),
(3382112090,'Mobile');

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
