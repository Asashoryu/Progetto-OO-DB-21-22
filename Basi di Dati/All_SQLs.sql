--Al fine di evitare la distruzione e poi la ricreazione del database, si suggerisce di usare la seguente sequenza di comandi (togliendo i commenti)
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

--Creazione datatype EmailType per indirizzi email
--L'indirizzo email deve essere strutturato in quest'ordine: testo,"@",testo,".",testo.
CREATE DOMAIN EmailType AS VARCHAR(256) 
CHECK (VALUE LIKE '_%@_%.__%');

--Crea il datatype per il CAP
 CREATE DOMAIN CAPType AS CHAR(5) 
 CHECK (VALUE NOT LIKE '%[^0-9]%');
 
--Creazione dominio per numero telefonico
CREATE DOMAIN NUMType AS VARCHAR(10) 
 CHECK (VALUE NOT LIKE '%[^0-9]%');
 
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
 ON UPDATE CASCADE ON DELETE CASCADE;
 
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
 ON UPDATE CASCADE ON DELETE CASCADE;



--Crea la tabella Account
CREATE TABLE Account(
Account_ID SERIAL ,
Fornitore VARCHAR(20) NOT NULL,
IndirizzoEmail EmailType NOT NULL,
FraseStato VARCHAR(256),
Nickname VARCHAR(30)
);

ALTER TABLE Account
 ADD CONSTRAINT account_pk PRIMARY KEY(Account_ID),
 ADD CONSTRAINT unique_provider_email UNIQUE (Fornitore,IndirizzoEmail);
 
 

--Crea la tabella Email
CREATE TABLE Email(
Email_ID SERIAL,
IndirizzoEmail EmailType,
Descrizione VARCHAR(30),
Contatto_FK SERIAL
);

ALTER TABLE Email
 ADD CONSTRAINT email_pk PRIMARY KEY(Email_ID),
 ADD CONSTRAINT email_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT not_redundant_email UNIQUE (Contatto_FK,indirizzoEmail);
 
 

 
--Crea la tabella Indirizzo
CREATE TABLE Indirizzo(
Indirizzo_ID SERIAL ,
Via VARCHAR(20) NOT NULL,
Città VARCHAR(30),
Nazione VARCHAR(30) NOT NULL,
CAP CAPType NOT NULL,
Descrizione VARCHAR(20) NOT NULL,
Contatto_FK SERIAL
);

ALTER TABLE Indirizzo
 ADD CONSTRAINT indirizzo_pk PRIMARY KEY(Indirizzo_ID),
 ADD CONSTRAINT indirizzo_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;


 
--Crea la tabella Telefono
CREATE TABLE Telefono (
Telefono_ID SERIAL,
Numero NUMType NOT NULL,
Descrizione VARCHAR(20),
Contatto_FK SERIAL
);

ALTER TABLE Telefono
 ADD CONSTRAINT telefono_pk PRIMARY KEY(Telefono_ID),
 ADD CONSTRAINT telefono_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT not_redundant_telephone_number UNIQUE(Contatto_FK,Numero);

--Crea la tabella Associa 
CREATE TABLE Associa (
Contatto_FK SERIAL,
Account_FK SERIAL
);

ALTER TABLE Associa
 ADD CONSTRAINT associa_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT associa_account_fk FOREIGN KEY(Account_FK) REFERENCES Account(Account_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;

--Crea la tabella Composizione
CREATE TABLE Composizione(
Contatto_FK SERIAL,
Gruppo_FK SERIAL
);
ALTER TABLE Composizione
 ADD CONSTRAINT composizione_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT composizione_gruppo_fk FOREIGN KEY(Gruppo_FK) REFERENCES Gruppo(Gruppo_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;


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
	
--Regola attiva che dopo ogni inserimento di una nuova email
--la associa a tutti i suoi account
CREATE OR REPLACE FUNCTION automatic_email_association_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cur_account CURSOR FOR
			SELECT *
			FROM Account
			WHERE IndirizzoEmail=NEW.IndirizzoEmail;
	BEGIN
		FOR cur_var IN cur_account LOOP
			--Si inserisce in associa il codice dell'account a cui appartiene
			--la data email e l'account associato ai sistemi di messaging
			--con la stessa email
			INSERT INTO Associa VALUES
			(NEW.Contatto_FK,cur_var.Account_ID);
		END LOOP;
		RETURN NEW;
	END; $$;
	
CREATE OR REPLACE TRIGGER automatic_email_association
	AFTER INSERT ON Email
	FOR EACH ROW
	EXECUTE PROCEDURE automatic_email_association_f();
	
--Regola attiva che dopo ogni inserimento di un nuovo account
--la sua email viene associata a quella dei contatti che la condividono
CREATE OR REPLACE FUNCTION automatic_account_association_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cur_account CURSOR FOR
			SELECT *
			FROM Email
			WHERE IndirizzoEmail=NEW.IndirizzoEmail;
	BEGIN
		FOR cur_var IN cur_account LOOP
			--Si inserisce in associa il codice dell'account a cui appartiene
			--la data email e l'account associato ai sistemi di messaging
			--con la stessa email
			INSERT INTO Associa VALUES
			(cur_var.Contatto_FK,NEW.Account_ID);
		END LOOP;
		RETURN NEW;
	END; $$;
	
CREATE OR REPLACE TRIGGER automatic_account_association
	AFTER INSERT ON Account
	FOR EACH ROW
	EXECUTE PROCEDURE automatic_account_association_f();
	
--Vincolo che impedisce l'eliminazione di indirizzi con
--descrizione 'principale'
CREATE OR REPLACE FUNCTION undeletable_main_address_f() 
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF (OLD.Descrizione = 'Principale') THEN
			RAISE NOTICE 'Questo indirizzo è principale e non può essere eliminato';
			RETURN NULL;
		ELSE 
			RETURN OLD;
		END IF;
	END;$$;

CREATE OR REPLACE TRIGGER undeletable_main_address
	BEFORE DELETE ON Indirizzo 
	FOR EACH ROW
	EXECUTE PROCEDURE undeletable_main_address_f();	

--Regola attiva che dopo ogni inserimento di un nuovo account
--la sua email viene associata a quella dei contatti che la condividono
CREATE OR REPLACE FUNCTION unchangeable_address_description_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF OLD.Descrizione='Principale' AND NEW.Descrizione<>'Principale' THEN
			RAISE NOTICE 'Tentativo di modifica un indirizzo principale in uno secondario abortito';
			RETURN NULL;
		ELSE
			RETURN NEW;
		END IF;
	END; $$;
	
CREATE OR REPLACE TRIGGER unchangeable_address_description
	--L'attivazione del vincolo interviene per aggiustare solamente
	--la modifica della descrizione principale, mentre le restanti 
	--modifiche sono mantenute
	BEFORE UPDATE ON Indirizzo
	FOR EACH ROW
	EXECUTE PROCEDURE unchangeable_address_description_f();
	
--blocca l'inserimento in Contatto
CREATE OR REPLACE FUNCTION block_direct_insertion_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		RAISE NOTICE 'Inserimento diretto in contatto bloccato';
		RETURN OLD;
	END; $$;
	
CREATE OR REPLACE TRIGGER block_direct_insertion
	BEFORE INSERT ON Contatto
	FOR EACH ROW
	EXECUTE PROCEDURE block_direct_insertion_f();

--Trigger che assicura che per ogni contatto esistano sempre 
--un numero fisso e uno mobile
CREATE OR REPLACE FUNCTION check_mobile_landline_numbers_existence_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		--tg_op è una metavariabile che indica l'operazione che innesca il trigger
		IF (SELECT count(*) FROM Telefono WHERE Contatto_fk=OLD.Contatto_fk AND Descrizione=OLD.Descrizione)<=1 THEN
			RAISE NOTICE 'operazione di % del numero % del contatto % non consentita',tg_op, OLD.Numero, OLD.Contatto_fk;
			RETURN NULL;
		ELSE 
			IF tg_op='UPDATE' THEN
				RETURN NEW;
			ELSE 
				RETURN OLD;
			END IF;
		END IF;
	END; $$;
	
CREATE OR REPLACE TRIGGER check_mobile_landline_numbers_existence
	BEFORE DELETE OR UPDATE ON Telefono
	FOR EACH ROW
	EXECUTE PROCEDURE check_mobile_landline_numbers_existence_f();

--Funzione che garantisce il corretto inserimento di un contatto con un numero 
--fisso e uno mobile e un indirizzo fisico
CREATE OR REPLACE FUNCTION coherent_insertion_f(rubrica_par Rubrica.utente_id%TYPE, nome_par Contatto.nome%TYPE, 
												cognome_par Contatto.cognome%TYPE, numero_mobile_par Telefono.numero%TYPE, 
												numero_fisso_par Telefono.numero%TYPE, indirizzo_principale_par Indirizzo.via%TYPE,
											    citta_par Indirizzo.città%TYPE, cap_par Indirizzo.cap%TYPE, nazione_par Indirizzo.nazione%TYPE)
	RETURNS INTEGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		codice_contatto INTEGER := (SELECT max(contatto_id) FROM Contatto)+1;
	BEGIN
		--Disattivo il trigger che impedisce l'inserimento diretto in Contatto
		ALTER TABLE Contatto DISABLE TRIGGER block_direct_insertion;
		INSERT INTO Contatto (contatto_id,nome, cognome, rubrica_fk) VALUES (codice_contatto, nome_par, cognome_par, rubrica_par);
		INSERT INTO Telefono (numero, descrizione, contatto_fk) VALUES (numero_mobile_par, 'Mobile', codice_contatto);
		INSERT INTO Telefono (numero, descrizione, contatto_fk) VALUES (numero_fisso_par, 'Fisso', codice_contatto);
		INSERT INTO Indirizzo (via, descrizione, città, cap, nazione, contatto_fk) 
							   VALUES (indirizzo_principale_par, 'Principale', citta_par, cap_par, nazione_par, codice_contatto);
		--Riattivo il trigger
		ALTER TABLE Contatto ENABLE TRIGGER block_direct_insertion;
		--Viene ritornato il codice del contatto creato
		RETURN codice_contatto;
		END; $$;
