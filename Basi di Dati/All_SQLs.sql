--Al fine di evitare la distruzione e poi la ricreazione del database, si suggerisce di usare la seguente sequenza di comandi (togliendo i commenti)
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

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
 ADD CONSTRAINT gruppo_rubrica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
 ON DELETE CASCADE;
 
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
 ADD CONSTRAINT contatto_rubica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
 ON DELETE CASCADE;

--Crea la tabella Account
CREATE TABLE Account(
Account_ID SERIAL ,
Fornitore VARCHAR(20) NOT NULL,
IndirizzoEmail VARCHAR(256) NOT NULL,
FraseStato VARCHAR(256),
Nickname VARCHAR(30)
);

ALTER TABLE Account
 ADD CONSTRAINT account_pk PRIMARY KEY(Account_ID),
 ADD CONSTRAINT unique_provider_email UNIQUE (Fornitore,IndirizzoEmail),
 --L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo utilizzando un CHECK per verificare tale condizione
 ADD CONSTRAINT correct_email_formatting CHECK ( IndirizzoEmail LIKE '_%@_%.__%');

--Crea la tabella Email
CREATE TABLE Email(
Email_ID SERIAL,
IndirizzoEmail VARCHAR(256) NOT NULL,
Descrizione VARCHAR(30),
Contatto_FK SERIAL
);

ALTER TABLE Email
 ADD CONSTRAINT email_pk PRIMARY KEY(Email_ID),
 ADD CONSTRAINT email_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON DELETE CASCADE,
 --L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo utilizzando un CHECK per verificare tale condizione
 ADD CONSTRAINT correct_email_formatting CHECK (IndirizzoEmail LIKE '_%@_%.__%'),
 ADD CONSTRAINT not_redundant_email UNIQUE (Contatto_FK,indirizzoEmail);
 
--Crea la tabella Indirizzo
CREATE TABLE Indirizzo(
Indirizzo_ID SERIAL ,
Via VARCHAR(20) NOT NULL,
Città VARCHAR(30),
Nazione VARCHAR(30) NOT NULL,
CAP INTEGER NOT NULL,
Descrizione VARCHAR(20) NOT NULL,
Contatto_FK SERIAL
);

ALTER TABLE Indirizzo
 ADD CONSTRAINT indirizzo_pk PRIMARY KEY(Indirizzo_ID),
 ADD CONSTRAINT indirizzo_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON DELETE CASCADE;

--Crea la tabella Telefono
CREATE TABLE Telefono (
Telefono_ID SERIAL,
Numero BIGINT NOT NULL,
Descrizione VARCHAR(20),
Contatto_FK SERIAL
);

ALTER TABLE Telefono
 ADD CONSTRAINT telefono_pk PRIMARY KEY(Telefono_ID),
 ADD CONSTRAINT telefono_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON DELETE CASCADE,
 ADD CONSTRAINT not_redundant_telephone_number UNIQUE(Contatto_FK,Numero);

--Crea la tabella Associa 
CREATE TABLE Associa (
Contatto_FK SERIAL,
Account_FK SERIAL
);

ALTER TABLE Associa
 ADD CONSTRAINT associa_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON DELETE CASCADE,
 ADD CONSTRAINT associa_account_fk FOREIGN KEY(Account_FK) REFERENCES Account(Account_ID)
 ON DELETE CASCADE;

--Crea la tabella Composizione
CREATE TABLE Composizione(
Contatto_FK SERIAL,
Gruppo_FK SERIAL
);
ALTER TABLE Composizione
 ADD CONSTRAINT composizione_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON DELETE CASCADE,
 ADD CONSTRAINT composizione_gruppo_fk FOREIGN KEY(Gruppo_FK) REFERENCES Gruppo(Gruppo_ID)
 ON DELETE CASCADE;


--Definizione dei trigger per implementare i vincoli
--I membri di un gruppo devono appartenere alla stessa rubrica
CREATE OR REPLACE FUNCTION group_coherency_membership_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		r_contatto Rubrica.Utente_ID%TYPE;
		r_gruppo Rubrica.Utente_ID%TYPE;
	BEGIN
		SELECT Rubrica_FK INTO r_contatto
		FROM Contatto
		WHERE Contatto_ID=NEW.Contatto_FK;
		
		SELECT Rubrica_FK INTO r_gruppo
		FROM Gruppo
		WHERE Gruppo_ID=NEW.Gruppo_FK;
		
		IF (r_contatto<>r_gruppo) THEN
			RAISE NOTICE 'Il contatto di ID % non appartiene alla stessa rubrica
			              del gruppo di ID %, pertanto l''inserimento è annullato ', NEW.Contatto_FK, NEW.Gruppo_FK;
			RETURN OLD;
		ELSE
			RETURN NEW;
		END IF;
	END; $$;
CREATE OR REPLACE TRIGGER group_coherency_membership
	BEFORE INSERT ON Composizione
	FOR EACH ROW
	EXECUTE PROCEDURE group_coherency_membership_f();
	
--Vincolo per l'unicità	dell'email per rubrica
CREATE OR REPLACE FUNCTION unique_email_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cur_seaker CURSOR FOR(
			SELECT *
			FROM Contatto AS CNEW, Contatto as C1
			WHERE CNEW.Contatto_ID=NEW.Contatto_FK AND
				  CNEW.Contatto_ID<>C1.Contatto_ID AND
				  CNEW.Rubrica_FK=C1.Rubrica_FK AND
				  C1.Contatto_ID IN
				  (SELECT Em.Contatto_FK
				   FROM Email AS Em
				   WHERE Em.IndirizzoEmail=NEW.IndirizzoEmail));
		cur_var cur_seaker%TYPE;
	BEGIN
		OPEN cur_seaker;
		FETCH cur_seaker INTO cur_var;
		--FOUND è una variabile di sistema che, in questo caso, verifica 
		--se la fetch ha restituito almeno una tupla, nel qual caso l'email già esiste
		IF FOUND THEN 
			RAISE NOTICE 'L''email che si intende aggiungere è già in uso da qualche altro contatto della stessa rubrica';
			CLOSE cur_seaker;
			RETURN OLD;
		ELSE
			CLOSE cur_seaker;
			RETURN NEW;
		END IF;
	END; $$;
	
CREATE OR REPLACE TRIGGER unique_email
	BEFORE INSERT ON Email
	FOR EACH ROW
	EXECUTE PROCEDURE unique_email_f();
