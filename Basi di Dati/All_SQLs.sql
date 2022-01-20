--Al fine di evitare la distruzione e poi la ricreazione del database, si suggerisce di usare la seguente sequenza di comandi (togliendo i commenti)
--DROP SCHEMA public CASCADE;
--CREATE SCHEMA public;
--GRANT ALL ON SCHEMA public TO postgres;
--GRANT ALL ON SCHEMA public TO public;

--Crea la tabella Rubrica
CREATE TABLE Rubrica(
Utente_ID VARCHAR(30)
);

ALTER TABLE Rubrica
 ADD CONSTRAINT rubrica_pk PRIMARY KEY(Utente_ID); 

--Crea la tabella Gruppo
CREATE TABLE Gruppo(
Gruppo_ID SERIAL ,
Nome VARCHAR(20) NOT NULL,
Rubrica_FK VARCHAR(30) NOT NULL
);

ALTER TABLE Gruppo
 ADD CONSTRAINT gruppo_pk PRIMARY KEY(Gruppo_ID),
 ADD CONSTRAINT gruppo_rubrica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID);
 
--Crea la tabella Contatto
CREATE TABLE Contatto(
Contatto_ID SERIAL ,
Nome VARCHAR(20) NOT NULL,
SecondoNome VARCHAR(20),
Cognome VARCHAR(20) NOT NULL,
Foto VARCHAR(20),
Rubrica_FK VARCHAR(20) NOT NULL
);

ALTER TABLE Contatto
 ADD CONSTRAINT contatto_pk PRIMARY KEY(Contatto_ID),
 ADD CONSTRAINT contatto_rubica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID);

--Crea la tabella Account
CREATE TABLE Account(
Account_ID SERIAL ,
Fornitore VARCHAR(20) NOT NULL,
 --L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo utilizzando un CHECK per verificare tale condizione
IndirizzoEmail VARCHAR(256) NOT NULL UNIQUE CHECK ( IndirizzoEmail LIKE '_%@_%.__%'),
FraseStato VARCHAR(256),
Nickname VARCHAR(30)
);

ALTER TABLE Account
 ADD CONSTRAINT account_pk PRIMARY KEY(Account_ID);

--Crea la tabella Email
CREATE TABLE Email(
Email_ID SERIAL,
 --L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo utilizzando un CHECK per verificare tale condizione
IndirizzoEmail VARCHAR(256) NOT NULL UNIQUE CHECK (IndirizzoEmail LIKE '_%@_%.__%'),
Descrizione VARCHAR(30),
Contatto_ID SERIAL
);

ALTER TABLE Email
 ADD CONSTRAINT email_pk PRIMARY KEY(Email_ID),
 ADD CONSTRAINT email_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID);

--Crea la tabella Indirizzo
CREATE TABLE Indirizzo(
Indirizzo_ID SERIAL ,
Via VARCHAR(20) NOT NULL,
Città VARCHAR(30),
Nazione VARCHAR(30) NOT NULL,
CAP INTEGER NOT NULL,
Descrizione VARCHAR(20) NOT NULL,
Contatto_ID SERIAL
);

ALTER TABLE Indirizzo
 ADD CONSTRAINT indirizzo_pk PRIMARY KEY(Indirizzo_ID),
 ADD CONSTRAINT indirizzo_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID);

--Crea la tabella Telefono
CREATE TABLE Telefono (
Telefono_ID SERIAL,
Numero INTEGER NOT NULL,
Descrizione VARCHAR(20),
Contatto_ID SERIAL
);

ALTER TABLE Telefono
 ADD CONSTRAINT telefono_pk PRIMARY KEY(Telefono_ID),
 ADD CONSTRAINT telefono_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID);

--Crea la tabella Associa 
CREATE TABLE Associa (
Contatto_ID SERIAL,
Account_ID SERIAL
);

ALTER TABLE Associa
 ADD CONSTRAINT associa_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
 ADD CONSTRAINT associa_account_fk FOREIGN KEY(Account_ID) REFERENCES Account(Account_ID);

--Crea la tabella RecapitoEmail
CREATE TABLE RecapitoEmail(
Contatto_ID SERIAL,
Email_ID SERIAL
);

ALTER TABLE RecapitoEmail
 ADD CONSTRAINT recapitoemail_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
 ADD CONSTRAINT recapitoemail_email_fk FOREIGN KEY(Email_ID) REFERENCES Email(Email_ID);

--Crea la tabella RecapitoIndirizzo
CREATE TABLE RecapitoIndirizzo(
Contatto_ID SERIAL,
Indirizzo_ID SERIAL
);

ALTER TABLE RecapitoIndirizzo
 ADD CONSTRAINT recapitoindirizzo_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
 ADD CONSTRAINT recapitoindirizzo_indirizzo_fk FOREIGN KEY(Indirizzo_ID) REFERENCES Indirizzo(Indirizzo_ID);

--Crea la tabella RecapitoTelefono
CREATE TABLE RecapitoTelefono(
Contatto_ID SERIAL,
Telefono_ID SERIAL
);
ALTER TABLE RecapitoTelefono
 ADD CONSTRAINT recapitotelefono_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
 ADD CONSTRAINT recapitotelefono_telefono_fk FOREIGN KEY(Telefono_ID) REFERENCES Telefono(Telefono_ID);

--Crea la tabella Composizione
CREATE TABLE Composizione(
Contatto_ID SERIAL,
Gruppo_ID SERIAL
);
ALTER TABLE Composizione
 ADD CONSTRAINT composizione_contatto_fk FOREIGN KEY(Contatto_ID) REFERENCES Contatto(Contatto_ID),
 ADD CONSTRAINT composizione_gruppo_fk FOREIGN KEY(Gruppo_ID) REFERENCES Gruppo(Gruppo_ID);
