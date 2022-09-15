--Crea il datatype per il CAP--Al fine di evitare la distruzione e poi la ricreazione del database, si suggerisce di usare la seguente sequenza di comandi (togliendo i commenti)
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
 CHECK (VALUE ~ '^[0-9]*$');
 
--Creazione dominio per numero telefonico
CREATE DOMAIN NUMType AS VARCHAR(10)
 CHECK (VALUE ~ '^[0-9]*$' AND
	    (length(VALUE)) > 2
       );
 

--Crea la tabella Rubrica
CREATE TABLE Rubrica(
Utente_ID VARCHAR(30)
);
 
ALTER TABLE Rubrica
 ADD CONSTRAINT rubrica_pk PRIMARY KEY(Utente_ID); 


--Crea la tabella Gruppo
CREATE TABLE Gruppo(
Gruppo_ID SERIAL,
Nome VARCHAR(20) NOT NULL,
Rubrica_FK VARCHAR(30) NOT NULL
);

ALTER TABLE Gruppo
 ADD CONSTRAINT gruppo_pk PRIMARY KEY(Gruppo_ID),
 ADD CONSTRAINT gruppo_rubrica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;
 
 
--Crea la tabella Contatto
CREATE TABLE Contatto(
Contatto_ID SERIAL,
Nome VARCHAR(20) NOT NULL,
SecondoNome VARCHAR(20),
Cognome VARCHAR(20) NOT NULL,
Foto VARCHAR(1000),
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
IndirizzoEmail EmailType NOT NULL,
Descrizione VARCHAR(30) NOT NULL,
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
Città VARCHAR(30) NOT NULL,
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
Descrizione VARCHAR(20) NOT NULL,
Contatto_FK SERIAL
);

ALTER TABLE Telefono
 ADD CONSTRAINT telefono_pk PRIMARY KEY(Telefono_ID),
 ADD CONSTRAINT telefono_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT not_redundant_telephone_number UNIQUE(Contatto_FK,Numero);


--Crea la tabella Associa 
CREATE TABLE Associa (
Email_FK SERIAL,
Account_FK SERIAL
);

ALTER TABLE Associa
 ADD CONSTRAINT associa_email_fk FOREIGN KEY(Email_FK) REFERENCES Email(Email_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT associa_account_fk FOREIGN KEY(Account_FK) REFERENCES Account(Account_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;


--Crea la tabella Composizione
CREATE TABLE Composizione(
Contatto_FK SERIAL,
Gruppo_FK   SERIAL
);

ALTER TABLE Composizione
 ADD CONSTRAINT composizione_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 ADD CONSTRAINT composizione_gruppo_fk   FOREIGN KEY(Gruppo_FK)   REFERENCES Gruppo(Gruppo_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;


--Definizione dei trigger per implementare i vincoli
--I membri di un gruppo devono appartenere alla stessa rubrica
CREATE OR REPLACE FUNCTION group_coherency_membership_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		r_contatto Rubrica.Utente_ID%TYPE;
		r_gruppo   Rubrica.Utente_ID%TYPE;
	BEGIN
		SELECT Rubrica_FK INTO r_contatto
		FROM Contatto
		WHERE Contatto_ID = NEW.Contatto_FK;
		
		SELECT Rubrica_FK into r_gruppo
		FROM   Gruppo
		WHERE  Gruppo_ID = NEW.Gruppo_FK;
		
		IF (r_contatto <> r_gruppo) THEN
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

--Bloccare l'inserimento di gruppi con lo stesso nome nella stessa rubrica
CREATE OR REPLACE FUNCTION block_homonymus_groups_in_same_rubric_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		-- controllo se esistono gruppi vuoti, cioè senza contatti
		if (select count(*) from gruppo where nome = NEW.nome AND rubrica_fk = NEW.rubrica_fk) > 1
		THEN
			RAISE EXCEPTION 'Si sta provando a inserire gruppo già presente in questa rubica';
			--Al primo errore, viene fatto un rollback della transazione in uso che rimuove ogni cambiamento
			ROLLBACK;
			return NULL;
		ELSE
			return NEW;
		END IF;
	END; $$;
	
CREATE CONSTRAINT TRIGGER block_homonymus_groups_in_same_rubric
	AFTER INSERT ON Gruppo
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_homonymus_groups_in_same_rubric_f();

--Un gruppo deve avere sempre almeno un contatto
CREATE OR REPLACE FUNCTION block_void_groups_insertion_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		-- controllo se esistono gruppi vuoti, cioè senza contatti
		if (select count(*) from gruppo as g where (select count(*) from composizione as c where c.gruppo_fk = g.gruppo_id) < 1) > 0
		THEN
			RAISE EXCEPTION 'Si sta provando a inserire un gruppo vuoto, ma si richiede che abbia almeno un contatto';
			--Al primo errore, viene fatto un rollback della transazione in uso che rimuove ogni cambiamento
			ROLLBACK;
			return NULL;
		ELSE
			return NEW;
		END IF;
	END; $$;
	
CREATE CONSTRAINT TRIGGER block_void_groups_insertion
	AFTER INSERT ON Gruppo
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_void_groups_insertion_f();
	
--Un gruppo che rimane senza contatti viene cancellato automaticamente
CREATE OR REPLACE FUNCTION delete_void_groups_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cur_groups CURSOR FOR
			   	   SELECT *
			       FROM gruppo
			       WHERE (SELECT count(*)
				          FROM composizione
				          WHERE Gruppo_FK = Gruppo_ID) < 1;
	BEGIN
		FOR cur_var IN cur_groups LOOP
			DELETE FROM Gruppo WHERE Gruppo_ID = cur_var.Gruppo_ID;
		END LOOP;
		RETURN NEW;
	END; $$;

CREATE CONSTRAINT TRIGGER delete_void_groups
	AFTER DELETE ON Composizione 
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE delete_void_groups_f();
	

--Vincolo per l'unicità	dell'email per rubrica
CREATE OR REPLACE FUNCTION unique_email_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cur_seaker CURSOR FOR(
			SELECT *
			FROM Contatto AS CNEW, Contatto as C1
			WHERE CNEW.Contatto_ID = NEW.Contatto_FK AND
				  CNEW.Contatto_ID <> C1.Contatto_ID AND
				  CNEW.Rubrica_FK  = C1.Rubrica_FK AND
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
			RAISE EXCEPTION  'L''email che si intende aggiungere è già in uso da qualche altro contatto della stessa rubrica' USING ERRCODE ='CIAO1';
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
			WHERE IndirizzoEmail = NEW.IndirizzoEmail;
	BEGIN
		FOR cur_var IN cur_account LOOP
			--Si inserisce in associa il codice dell'account a cui appartiene
			--la data email e l'account associato ai sistemi di messaging
			--con la stessa email
			INSERT INTO Associa VALUES
			(NEW.Email_ID, cur_var.Account_ID);
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
			WHERE IndirizzoEmail = NEW.IndirizzoEmail;
	BEGIN
		FOR cur_var IN cur_account LOOP
			--Si inserisce in associa il codice dell'account a cui appartiene
			--la data email e l'account associato ai sistemi di messaging
			--con la stessa email
			INSERT INTO Associa VALUES
			(cur_var.Email_ID,NEW.Account_ID);
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
		-- Se l'indirizzo è principale e corrisponde a un contatto esistente allora blocca la cancellazione dell'indirizzo
		IF (OLD.Descrizione = 'Principale') AND ((Select count(*) From Contatto where Contatto_ID = OLD.Contatto_FK ) <> 0) THEN
			RAISE EXCEPTION 'Questo indirizzo è principale e non può essere eliminato';
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
			RAISE EXCEPTION 'Tentativo di modifica un indirizzo principale in uno secondario abortito';
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
	
--Regola attiva che verifica se alla fine di una transazione un contatto
--abbia almeno un numero mobile e uno fisso
CREATE OR REPLACE FUNCTION block_contact_without_both_numbers_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		if (Select count(*) From telefono where Contatto_FK = NEW.Contatto_ID AND descrizione = 'Fisso') < 1
	    OR (Select count(*) From telefono where Contatto_FK = NEW.Contatto_ID AND descrizione = 'Mobile') < 1
		THEN
			RAISE EXCEPTION 'Si prova ad aggiungere un contatto senza indicare un numero mobile e uno fisso a cui reindirizzare le chiamate';
			--Al primo errore, viene fatto un rollback della transazione in uso che rimuove ogni cambiamento
			ROLLBACK;
			return NULL;
		ELSE
			return NEW;
		END IF;
	END; $$;

CREATE CONSTRAINT TRIGGER block_contact_without_both_numbers
	AFTER INSERT ON Contatto
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_contact_without_both_numbers_f();
	
--Regola attiva che verifica se alla fine di una transazione un contatto
--abbia associato un indirizzo principale
CREATE OR REPLACE FUNCTION block_contact_without_main_address_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF (SELECT COUNT(*) FROM Indirizzo WHERE Contatto_FK = NEW.Contatto_ID AND descrizione = 'Principale') < 1
		THEN
			RAISE EXCEPTION 'Si prova ad aggiungere un contatto senza indicare un indirizzo principale';
			--Al primo errore, viene fatto un rollback della transazione in uso che rimuove ogni cambiamento
			ROLLBACK;
			return NULL;
		ELSE
			return NEW;
		END IF;
	END; $$;

CREATE CONSTRAINT TRIGGER block_contact_without_main_address
	AFTER INSERT ON Contatto
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_contact_without_main_address_f();


--Trigger che assicura che per ogni contatto esistano sempre 
--un numero fisso e uno mobile
CREATE OR REPLACE FUNCTION check_mobile_landline_numbers_existence_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		-- Se il telefono da eliminare è l'ultimo rimasto, fisso o mobile, del contatto e il contatto esiste allora la cancellazione è bloccata
		IF ((SELECT count(*) FROM Telefono WHERE  Contatto_fk = OLD.Contatto_fk 
			                                 AND  Descrizione = OLD.Descrizione 
			 	                             AND (OLD.Descrizione = 'Mobile' OR OLD.Descrizione = 'Fisso')) = 1)
		    AND ((Select count(*) From Contatto where Contatto_ID = OLD.Contatto_FK ) <> 0) THEN
			--tg_op è una metavariabile che indica l'operazione che innesca il trigger
			RAISE EXCEPTION 'operazione di % del numero % del contatto % non consentita',tg_op, OLD.Numero, OLD.Contatto_fk using hint = 'FUck off';
			rollback;
			RETURN NULL;
		ELSE 
			IF tg_op = 'UPDATE' THEN
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


CREATE OR REPLACE FUNCTION generate_new_contatto_id()
	RETURNS INTEGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		--Seleziono e conservo un nuovo identificativo per il contatto
		codice_contatto     INTEGER; 
		nuovo_codice_valido INTEGER := (SELECT max(contatto_id) FROM Contatto) + 1;
	BEGIN
		SET CONSTRAINTS ALL DEFERRED;
		IF (nuovo_codice_valido <> -1) THEN
			codice_contatto := nuovo_codice_valido;
			raise notice 'Si inserisce il nuovo max id';
		ELSE
			codice_contatto := 1;
			raise notice 'Si inserisce 1';
		END IF;
		RETURN codice_contatto;
	END; $$;
	
CREATE OR REPLACE FUNCTION generate_new_gruppo_id()
	RETURNS INTEGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		--Seleziono e conservo un nuovo identificativo per il conttato
		codice_gruppo     INTEGER; 
		nuovo_codice_valido INTEGER := (SELECT max(gruppo_id) FROM Gruppo) + 1;
	BEGIN
		SET CONSTRAINTS ALL DEFERRED;
		IF (nuovo_codice_valido <> -1) THEN
			codice_gruppo := nuovo_codice_valido;
			raise notice 'Si inserisce il nuovo max id';
		ELSE
			codice_gruppo := 1;
			raise notice 'Si inserisce 1';
		END IF;
		RETURN codice_gruppo;
	END; $$;
	
--Funzione che garantisce il corretto inserimento di un contatto con un numero 
--fisso e uno mobile e un indirizzo fisico
--coherent_insertion_f(utente_rubrica, nome_contatto, secondonome_contatto, cognome_contatto,
--                     num_mobile, num_fisso, via, citta, nazione, cap, indirizzo_email, descrizione_email, contatto_id)
CREATE OR REPLACE PROCEDURE coherent_insertion_f(rubrica_par Rubrica.utente_id%TYPE,     nome_par Contatto.nome%TYPE,
												sec_no_par Contatto.secondonome%TYPE,   cognome_par Contatto.cognome%TYPE,
												numero_mobile_par Telefono.numero%TYPE, numero_fisso_par Telefono.numero%TYPE,
												via_par Indirizzo.via%TYPE,             citta_par Indirizzo.città%TYPE,
												nazione_par Indirizzo.nazione%TYPE,     cap_par Indirizzo.cap%TYPE,
												codice_contatto INTEGER)
	--RETURNS INTEGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		SET CONSTRAINTS ALL DEFERRED;
		--Disattivo il trigger che impedisce l'inserimento diretto in Contatto
		--ALTER TABLE Contatto DISABLE TRIGGER blocca_contatti_senza_numero;
		INSERT INTO Contatto (contatto_id, nome, secondonome, cognome, rubrica_fk)
		              VALUES (codice_contatto, nome_par, NULLIF(sec_no_par,''), cognome_par, rubrica_par);
		INSERT INTO Telefono (numero, descrizione, contatto_fk)
		              VALUES (numero_mobile_par, 'Mobile', codice_contatto);
		INSERT INTO Telefono (numero, descrizione, contatto_fk) 
		              VALUES (numero_fisso_par, 'Fisso', codice_contatto);
		INSERT INTO Indirizzo (via, descrizione, città, nazione, cap, contatto_fk)
		  			  VALUES (via_par, 'Principale', citta_par, nazione_par, cap_par, codice_contatto);
		--Riattivo il trigger
		--ALTER TABLE Contatto ENABLE TRIGGER blocca_contatti_senza_numero;
		--Viene ritornato il codice del contatto creato
		--RETURN codice_contatto;
	END; $$;