--Crea la tabella Rubrica
CREATE TABLE Rubrica(
Utente_ID VARCHAR(30),
--Chiave primaria
PRIMARY KEY(Utente_ID) );

--Crea la tabella Gruppo
CREATE TABLE Gruppo(
 --Gruppo_ID è di tipo SERIAL
Gruppo_ID SERIAL ,
Nome VARCHAR(20) NOT NULL,
Rubrica_FK VARCHAR(30) NOT NULL,
 --Chiave primaria
PRIMARY KEY(Gruppo_ID),
 --Chiave esterna
FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
 );

--Crea la tabella Contatto
CREATE TABLE Contatto(
 --Contatto_ID è di tipo SERIAL
Contatto_ID SERIAL ,
Nome VARCHAR(20) NOT NULL,
SecondoNome VARCHAR(20),
Cognome VARCHAR(20) NOT NULL,
Foto VARCHAR(20),
Rubrica_FK VARCHAR(20) NOT NULL,
 --Chiave primaria
PRIMARY KEY(Contatto_ID),
 --Chiave esterna
FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
);

--Crea la tabella Account
CREATE TABLE Account(
 --Account_ID è di tipo SERIAL
Account_ID SERIAL ,
Fornitore VARCHAR(20) NOT NULL,
 --L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo utilizzando un CHECK per verificare tale condizione
IndirizzoEmail VARCHAR(256) NOT NULL UNIQUE CHECK ( IndirizzoEmail LIKE '_%@_%.__%'),
FraseStato VARCHAR(256),
Nickname VARCHAR(30),
 --Chiave primaria
PRIMARY KEY(Account_ID)
);

--Crea la tabella Email
CREATE TABLE Email(
 --Email_ID è di tipo SERIAL
Email_ID SERIAL ,
 --L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo utilizzando un CHECK per verificare tale condizione
IndirizzoEmail VARCHAR(256) NOT NULL UNIQUE CHECK (IndirizzoEmail LIKE '_%@_%.__%'),
Descrizione VARCHAR(30),
Contatto_ID SERIAL ,
 --Chiave primaria
PRIMARY KEY(Email_ID),
 --Chiave esterna
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID)
);

--Crea la tabella Indirizzo
CREATE TABLE Indirizzo(
 --Indirizzo_ID è di tipo SERIAL
Indirizzo_ID SERIAL ,
Via VARCHAR(20) NOT NULL,
Città VARCHAR(30),
Nazione VARCHAR(30) NOT NULL,
CAP INTEGER NOT NULL,
Descrizione VARCHAR(20) NOT NULL,
Contatto_ID SERIAL ,
 --Chiave primaria
PRIMARY KEY(Indirizzo_ID),
 --Chiave esterna
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID)
);

--Crea la tabella Telefono
CREATE TABLE Telefono (
 --Telefono_ID è di tipo SERIAL
Telefono_ID SERIAL,
Numero INTEGER NOT NULL,
Descrizione VARCHAR(20),
Contatto_ID SERIAL ,
 --Chiave primaria
PRIMARY KEY(Telefono_ID),
--Chiave esterna
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID) );

--Crea la tabella Associa 
CREATE TABLE Associa (
--Contatto_ID e Account_ID sono di tipo SERIAL
Contatto_ID SERIAL ,
Account_ID SERIAL,
--Chiavi esterne
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
FOREIGN KEY(Account_ID) REFERENCES Account(Account_ID) );

--Crea la tabella RecapitoEmail
CREATE TABLE RecapitoEmail(
 --Contatto_ID e Email_ID sono di tipo SERIAL
Contatto_ID SERIAL ,
Email_ID SERIAL ,
--Chiavi esterne
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
FOREIGN KEY(Email_ID) REFERENCES Email(Email_ID) );

--Crea la tabella RecapitoIndirizzo
CREATE TABLE RecapitoIndirizzo(
 --Contatto_ID e Indirizzo_ID sono di tipo SERIAL
Contatto_ID SERIAL ,
Indirizzo_ID SERIAL ,
 --Chiavi esterne
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
FOREIGN KEY(Indirizzo_ID) REFERENCES Indirizzo(Indirizzo_ID) );

--Crea la tabella RecapitoTelefono
CREATE TABLE RecapitoTelefono(
 --Contatto_ID e Telefono_ID sono di tipo SERIAL
Contatto_ID SERIAL,
Telefono_ID SERIAL ,
 --Chiavi esterne
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
FOREIGN KEY(Telefono_ID) REFERENCES Telefono(Telefono_ID) );

--Crea la tabella Composizione
CREATE TABLE Composizione(
 --Contatto_ID e Gruppo_ID sono di tipo SERIAL
Contatto_ID SERIAL,
Gruppo_ID SERIAL  ,
 --Chiavi esterne
FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
FOREIGN KEY(Gruppo_ID) REFERENCES Gruppo(Gruppo_ID) );
