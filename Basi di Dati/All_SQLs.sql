--Eseguire questa query e il popolamento per rigenerare il Database
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;


--DOMINI


--Dominio per una Email:
--un indirizzo email deve essere strutturato in questo modo: testo+"@"+testo+"."+testo.
CREATE DOMAIN EmailType AS VARCHAR(256)
CHECK (VALUE LIKE '_%@_%.__%');

--Dominio per il CAP:
--il CAP deve essere una stringa di 5 cifre
 CREATE DOMAIN CAPType AS CHAR(5)
 CHECK (VALUE ~ '^[0-9]*$');
 
--Dominio per un Numero telefonico:
--un numero di telefono ha una lunghezza compresa tra 2 e 15 cifre.
CREATE DOMAIN NUMType AS VARCHAR(15)
 CHECK (VALUE ~ '^[0-9]*$' AND
	    (length(VALUE)) > 2
       );


--TABELLE


--Tabella Rubrica
CREATE TABLE Rubrica(
Utente_ID VARCHAR(30)
);
 
ALTER TABLE Rubrica
 --Chiave primaria
 ADD CONSTRAINT rubrica_pk PRIMARY KEY(Utente_ID); 


--Tabella Gruppo
CREATE TABLE Gruppo(
Gruppo_ID SERIAL,
Nome VARCHAR(20) NOT NULL,
Rubrica_FK VARCHAR(30) NOT NULL
);

ALTER TABLE Gruppo
 --Chiave primaria
 ADD CONSTRAINT gruppo_pk PRIMARY KEY(Gruppo_ID),
 --Chiave esterna
 ADD CONSTRAINT gruppo_rubrica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;
 
 
--Tabella Contatto
CREATE TABLE Contatto(
Contatto_ID SERIAL,
Nome VARCHAR(20) NOT NULL,
SecondoNome VARCHAR(20),
Cognome VARCHAR(20) NOT NULL,
Foto VARCHAR(1000),
Rubrica_FK VARCHAR(20) NOT NULL
);

ALTER TABLE Contatto
 --Chiave primaria
 ADD CONSTRAINT contatto_pk PRIMARY KEY(Contatto_ID),
 --Chiave esterna
 ADD CONSTRAINT contatto_rubica_fk FOREIGN KEY(Rubrica_FK) REFERENCES Rubrica(Utente_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;


--Tabella Account
CREATE TABLE Account(
Fornitore VARCHAR(20) NOT NULL,
IndirizzoEmail EmailType NOT NULL,
FraseStato VARCHAR(256),
Nickname VARCHAR(30)
);

ALTER TABLE Account
 --Chiave primaria
 ADD CONSTRAINT account_pk PRIMARY KEY(Fornitore, IndirizzoEmail);

--Tabella Email
CREATE TABLE Email(
Email_ID SERIAL,
IndirizzoEmail EmailType NOT NULL,
Descrizione VARCHAR(30) NOT NULL,
Contatto_FK SERIAL
);

ALTER TABLE Email
 --Chiave primaria
 ADD CONSTRAINT email_pk PRIMARY KEY(Email_ID),
 --Chiave esterna
 ADD CONSTRAINT email_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 --Un contatto non può avere associata due volte la stessa email
 ADD CONSTRAINT not_redundant_email UNIQUE (Contatto_FK,indirizzoEmail);


--Tabella Indirizzo
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
 --Chiave primaria
 ADD CONSTRAINT indirizzo_pk PRIMARY KEY(Indirizzo_ID),
 --Chiave esterna
 ADD CONSTRAINT indirizzo_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;
 
 
--Tabella Telefono
CREATE TABLE Telefono (
Telefono_ID SERIAL,
Numero NUMType NOT NULL,
Descrizione VARCHAR(20) NOT NULL,
Contatto_FK SERIAL
);

ALTER TABLE Telefono
 --Chiave primaria
 ADD CONSTRAINT telefono_pk PRIMARY KEY(Telefono_ID),
 --Chiave esterna
 ADD CONSTRAINT telefono_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 --Un contatto non può avere associato due volte lo stesso numero di telefono
 ADD CONSTRAINT not_redundant_telephone_number UNIQUE(Contatto_FK,Numero);


--Tabella Associa 
CREATE TABLE Associa (
Email_FK SERIAL,
FornitoreAccount VARCHAR(20),
IndirizzoEmailAccount EmailType
);

ALTER TABLE Associa
 --Chiave esterna sulla tabella Email
 ADD CONSTRAINT associa_email_fk FOREIGN KEY(Email_FK) REFERENCES Email(Email_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 --Chiave esterna sulla tabella Account
 ADD CONSTRAINT associa_account_fk FOREIGN KEY(FornitoreAccount, IndirizzoEmailAccount) REFERENCES Account(Fornitore, IndirizzoEmail)
 ON UPDATE CASCADE ON DELETE CASCADE;


--Tabella Composizione
CREATE TABLE Composizione(
Contatto_FK SERIAL,
Gruppo_FK   SERIAL
);

ALTER TABLE Composizione
 --Chiave esterna sulla tabella Contatto
 ADD CONSTRAINT composizione_contatto_fk FOREIGN KEY(Contatto_FK) REFERENCES Contatto(Contatto_ID)
 ON UPDATE CASCADE ON DELETE CASCADE,
 --Chiave esterna sulla tabella Gruppo
 ADD CONSTRAINT composizione_gruppo_fk   FOREIGN KEY(Gruppo_FK)   REFERENCES Gruppo(Gruppo_ID)
 ON UPDATE CASCADE ON DELETE CASCADE;


--TRIGGER PER VINCOLI


--I membri di un gruppo devono appartenere alla stessa rubrica
CREATE OR REPLACE FUNCTION group_membership_coherency_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		rubrica_c Rubrica.Utente_ID%TYPE;
		rubrica_g   Rubrica.Utente_ID%TYPE;
	BEGIN
		SELECT Rubrica_FK INTO rubrica_c
		FROM Contatto
		WHERE Contatto_ID = NEW.Contatto_FK;
		
		SELECT Rubrica_FK INTO rubrica_g
		FROM   Gruppo
		WHERE  Gruppo_ID = NEW.Gruppo_FK;
		--Se la rubrica del contatto è diversa da quella del gruppo, blocca l'inserimento
		IF (rubrica_c <> rubrica_g) THEN
			RAISE EXCEPTION USING ERRCODE = 'GMCOH';
		ELSE
			RETURN NEW;
		END IF;
	 EXCEPTION
	 	WHEN SQLSTATE 'GMCOH' THEN
		RAISE 'Il contatto di ID % non appartiene alla stessa rubrica
			   del gruppo di ID %, pertanto l''inserimento è annullato ', NEW.Contatto_FK, NEW.Gruppo_FK USING ERRCODE = 'GMCOH';
		RETURN OLD;		
	END; $$;
	
CREATE OR REPLACE TRIGGER group_membership_coherency
	BEFORE INSERT ON Composizione
	FOR EACH ROW
	EXECUTE PROCEDURE group_membership_coherency_f();

--Blocca l'inserimento di più gruppi aventi lo stesso nome, che stanno nella stessa rubrica
CREATE OR REPLACE FUNCTION block_homonymous_groups_in_same_rubric_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		-- si controlla se esistono gruppi vuoti, cioè senza contatti
		IF (SELECT COUNT(*) FROM gruppo WHERE nome = NEW.nome AND rubrica_fk = NEW.rubrica_fk) > 1
		THEN
			RAISE EXCEPTION USING ERRCODE = 'BLKHG';
		ELSE
			RETURN NEW;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'BLKHG' THEN
		RAISE 'Si sta provando a inserire gruppo già presente in questa rubrica' USING ERRCODE = 'BLKHG';
		RETURN NULL;
	END; $$;
	
CREATE CONSTRAINT TRIGGER block_homonymous_groups_in_same_rubric
	AFTER INSERT ON Gruppo
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_homonymous_groups_in_same_rubric_f();

--Un gruppo deve avere sempre almeno un contatto
CREATE OR REPLACE FUNCTION block_void_groups_insertion_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		-- controllo se esistono gruppi vuoti, cioè senza contatti
		IF (SELECT COUNT(*) FROM Gruppo AS Gr WHERE (SELECT COUNT(*) FROM Composizione AS Comp WHERE Comp.gruppo_fk = Gr.gruppo_id) < 1) > 0
		THEN
			RAISE EXCEPTION USING ERRCODE = 'BLKVG';
		ELSE
			return NEW;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'BLKVG' THEN
		RAISE 'Si sta provando a inserire un gruppo vuoto: si richiede che un gruppo abbia almeno un contatto' USING ERRCODE = 'BLKVG';
		RETURN NULL;
	END; $$;
	
CREATE CONSTRAINT TRIGGER block_void_groups_insertion
	AFTER INSERT ON Gruppo
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_void_groups_insertion_f();
	

--Vincolo per l'unicità	dell'email per rubrica
CREATE OR REPLACE FUNCTION unique_email_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cerca_email_uguali CURSOR FOR(
			SELECT *
			FROM Contatto AS CNEW, Contatto AS C1
			WHERE CNEW.Contatto_ID = NEW.Contatto_FK AND
				  CNEW.Contatto_ID <> C1.Contatto_ID AND
				  CNEW.Rubrica_FK  =  C1.Rubrica_FK  AND
				  C1.Contatto_ID IN
				  (		SELECT E1.Contatto_FK
				   		FROM   Email AS E1
				   		--Confronto CASE INSENSITIVE
				   		WHERE  lower(E1.IndirizzoEmail) = lower(NEW.IndirizzoEmail) )
				  );
		email_uguali cerca_email_uguali%TYPE;
	BEGIN
		OPEN cerca_email_uguali;
		FETCH cerca_email_uguali INTO email_uguali;
		--FOUND è una variabile di sistema che, in questo caso, verifica 
		--se la fetch ha restituito almeno una tupla, nel qual caso l'email già esiste
		IF FOUND THEN 
			RAISE EXCEPTION USING ERRCODE ='UNIQE';
		ELSE
			CLOSE cerca_email_uguali;
			RETURN NEW;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'UNIQE' THEN
		RAISE 'L''email che si intende aggiungere è già in uso da qualche altro contatto della stessa rubrica' USING ERRCODE = 'UNIQE';
		CLOSE cerca_email_uguali;
		RETURN OLD;
	END; $$;
	
CREATE OR REPLACE TRIGGER unique_email
	BEFORE INSERT ON Email
	FOR EACH ROW
	EXECUTE PROCEDURE unique_email_f();
	
	
--Vincolo che impedisce l'eliminazione di indirizzi con
--descrizione "Principale'
CREATE OR REPLACE FUNCTION undeletable_main_address_f() 
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		-- Se l'indirizzo è principale e corrisponde a un contatto esistente allora blocca la cancellazione dell'indirizzo
		IF (OLD.Descrizione = 'Principale') AND ((SELECT COUNT(*) FROM Contatto WHERE Contatto_ID = OLD.Contatto_FK ) <> 0) THEN
			RAISE EXCEPTION USING ERRCODE ='UDMAD';
		ELSE 
			RETURN OLD;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'UDMAD' THEN
		RAISE 'Questo indirizzo è principale e non può essere eliminato' USING ERRCODE = 'UDMAD';
		RETURN NULL;
	END;$$;

CREATE OR REPLACE TRIGGER undeletable_main_address
	BEFORE DELETE ON Indirizzo 
	FOR EACH ROW
	EXECUTE PROCEDURE undeletable_main_address_f();	

--Vincolo che impedisce di modificare la descrizione degli indirizzi principali
CREATE OR REPLACE FUNCTION unchangeable_main_address_description_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF (OLD.Descrizione='Principale' AND NEW.Descrizione<>'Principale') OR (OLD.Descrizione<>'Principale' AND NEW.Descrizione='Principale') THEN
			RAISE EXCEPTION USING ERRCODE ='UMADD';
		ELSE
			RETURN NEW;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'UMADD' THEN
		RAISE 'Tentativo di modificare un indirizzo principale in uno secondario, o viceversa, bloccato' USING ERRCODE = 'UMADD';
		RETURN NULL;
	END; $$;
	
CREATE OR REPLACE TRIGGER unchangeable_main_address_description
	BEFORE UPDATE ON Indirizzo
	FOR EACH ROW
	EXECUTE PROCEDURE unchangeable_main_address_description_f();
	
--Vincolo che verifica se alla fine di una transazione di inserimento di un contatto
--questi abbia sempre almeno un numero mobile e uno fisso
CREATE OR REPLACE FUNCTION block_contact_without_both_numbers_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF (SELECT COUNT(*) FROM telefono WHERE Contatto_FK = NEW.Contatto_ID AND descrizione = 'Fisso' ) < 1
	    OR (SELECT COUNT(*) FROM telefono WHERE Contatto_FK = NEW.Contatto_ID AND descrizione = 'Mobile') < 1
		THEN
			RAISE EXCEPTION USING ERRCODE ='BCWBN';
		ELSE
			RETURN NEW;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'BCWBN' THEN
		RAISE EXCEPTION 'Si blocca l''inserimento di un contatto di cui non è stato dichiarato
 						 almeno un numero mobile e uno fisso' USING ERRCODE = 'BCWBN';
		RETURN NULL;
	END; $$;

CREATE CONSTRAINT TRIGGER block_contact_without_both_numbers
	AFTER INSERT ON Contatto
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_contact_without_both_numbers_f();
	
--Vincolo che verifica se alla fine di una transazione di inserimento di un contatto
--questi abbia sempre un indirizzo principale associato
CREATE OR REPLACE FUNCTION block_contact_without_main_address_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF (SELECT COUNT(*) FROM Indirizzo WHERE Contatto_FK = NEW.Contatto_ID AND descrizione = 'Principale') < 1
		THEN
			RAISE EXCEPTION USING ERRCODE = 'BCWMA';
			ROLLBACK;
			RETURN NULL;
		ELSE
			RETURN NEW;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'BCWMA' THEN
		RAISE EXCEPTION 'Si blocca l''inserimento di un contatto di cui non è stato dichiarato
 						 un indirizzo principale' USING ERRCODE = 'BCWMA';
		RETURN NULL;
	END; $$;

CREATE CONSTRAINT TRIGGER block_contact_without_main_address
	AFTER INSERT ON Contatto
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE block_contact_without_main_address_f();


--Vincolo che assicura che nessuna modifica possa lasciare un contatto
--senza almeno un numero mobile e uno fisso
CREATE OR REPLACE FUNCTION check_mobile_landline_numbers_existence_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		--Se il numero di telefono interessato è l'ultimo con descrizione
		--"Fisso" o "Mobile" e il contatto esiste, allora l'operazione è bloccata
		IF ((SELECT COUNT(*) FROM Telefono WHERE  Contatto_fk = OLD.Contatto_fk 
			                                 AND  Descrizione = OLD.Descrizione 
			 	                             AND (OLD.Descrizione = 'Mobile' OR OLD.Descrizione = 'Fisso')) = 1)
		    AND ((SELECT COUNT(*) FROM Contatto WHERE Contatto_ID = OLD.Contatto_FK ) <> 0) THEN
			RAISE EXCEPTION USING ERRCODE = 'CMLNE';
		ELSE
			--Se l'operazione è UPDATE
			IF tg_op = 'UPDATE' THEN
				RETURN NEW;
			--Se l'operazione è DELETE
			ELSE
				RETURN OLD;
			END IF;
		END IF;
	EXCEPTION 
		WHEN SQLSTATE 'CMLNE' THEN
		--tg_op è una metavariabile che indica l'operazione che innesca il trigger
		RAISE EXCEPTION 'operazione di % del numero % del contatto di ID % non consentita',tg_op, OLD.Numero, OLD.Contatto_fk USING ERRCODE = 'CMLNE';
		RETURN NULL;
	END; $$;
	
CREATE OR REPLACE TRIGGER check_mobile_landline_numbers_existence
	BEFORE DELETE OR UPDATE ON Telefono
	FOR EACH ROW
	EXECUTE PROCEDURE check_mobile_landline_numbers_existence_f();


--AUTOMAZIONI


--Un gruppo che resta senza contatti viene cancellato automaticamente
CREATE OR REPLACE FUNCTION automatic_void_groups_deletion_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF (SELECT count(*) FROM composizione AS CP WHERE OLD.gruppo_fk = CP.gruppo_fk) = 0 THEN
			DELETE FROM Gruppo WHERE Gruppo_ID = OLD.gruppo_fk;
			RAISE NOTICE 'Gruppo di ID % è stato eliminato perché senza contatti', OLD.Gruppo_fk;
			RETURN NEW;
		END IF;
	END; $$;

CREATE CONSTRAINT TRIGGER automatic_void_groups_deletion
	AFTER DELETE ON Composizione 
	DEFERRABLE
	FOR EACH ROW
	EXECUTE PROCEDURE automatic_void_groups_deletion_f();


--Regola attiva che dopo ogni inserimento di una nuova email
--la associa a tutti i suoi account
CREATE OR REPLACE FUNCTION automatic_email_association_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cerca_account_da_associare CURSOR FOR
			SELECT *
			FROM Account
			WHERE IndirizzoEmail = NEW.IndirizzoEmail;
	BEGIN
		FOR account_da_associare IN cerca_account_da_associare LOOP
			INSERT INTO Associa VALUES
			(NEW.Email_ID, account_da_associare.Fornitore, account_da_associare.IndirizzoEmail);
			RAISE NOTICE 'La nuova email è stata associata all''account di indirizzo "%" con fornitore "%"', NEW.indirizzoEmail, account_da_associare.Fornitore;
		END LOOP;
		RETURN NEW;
	END; $$;
	
CREATE OR REPLACE TRIGGER automatic_email_association
	AFTER INSERT ON Email
	FOR EACH ROW
	EXECUTE PROCEDURE automatic_email_association_f();
	
	
--Regola attiva che dopo ogni inserimento di un nuovo account
--il suo indirizzo elettronico è associato alle email che lo condividono
CREATE OR REPLACE FUNCTION automatic_account_association_f()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		cerca_email_da_associare CURSOR FOR
			SELECT *
			FROM Email
			WHERE IndirizzoEmail = NEW.IndirizzoEmail;
	BEGIN
		FOR email_da_associare IN cerca_email_da_associare LOOP
			INSERT INTO Associa VALUES
			(email_da_associare.Email_ID, NEW.Fornitore, NEW.IndirizzoEmail);
			RAISE NOTICE 'Il nuovo account di fornitore "%" è stato associato all''email di indirizzo "%"', NEW.Fornitore, email_da_associare.indirizzoEmail;
		END LOOP;
		RETURN NEW;
	END; $$;
	
CREATE OR REPLACE TRIGGER automatic_account_association
	AFTER INSERT ON Account
	FOR EACH ROW
	EXECUTE PROCEDURE automatic_account_association_f();


--FUNZIONI E PROCEDURE


--Funzione che genera un id valido per un contatto
CREATE OR REPLACE FUNCTION generate_new_contatto_id()
	RETURNS INTEGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		--Seleziono e conservo un nuovo identificativo per il contatto
		codice_contatto     INTEGER; 
		nuovo_codice_valido INTEGER := (SELECT max(contatto_id) FROM Contatto) + 1;
	BEGIN
		IF (nuovo_codice_valido <> -1) THEN
			codice_contatto := nuovo_codice_valido;
			RAISE NOTICE 'Si inserisce il nuovo max id';
		ELSE
			codice_contatto := 1;
			RAISE NOTICE 'Si inserisce 1';
		END IF;
		RETURN codice_contatto;
	END; $$;

--Funzione che genera un id valido per un gruppo
CREATE OR REPLACE FUNCTION generate_new_gruppo_id()
	RETURNS INTEGER
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		--Seleziono e conservo un nuovo identificativo per il contato
		codice_gruppo     INTEGER; 
		nuovo_codice_valido INTEGER := (SELECT max(gruppo_id) FROM Gruppo) + 1;
	BEGIN
		IF (nuovo_codice_valido <> -1) THEN
			codice_gruppo := nuovo_codice_valido;
			RAISE NOTICE 'Si inserisce il nuovo max id';
		ELSE
			codice_gruppo := 1;
			RAISE NOTICE 'Si inserisce 1';
		END IF;
		RETURN codice_gruppo;
	END; $$;
	
--Funzione che garantisce il corretto inserimento di un contatto con le informazioni principali:
--un numero fisso, uno mobile e un indirizzo fisico.
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
		INSERT INTO Contatto (contatto_id, nome, secondonome, cognome, rubrica_fk)
		              VALUES (codice_contatto, nome_par, NULLIF(sec_no_par,''), cognome_par, rubrica_par);
		INSERT INTO Telefono (numero, descrizione, contatto_fk)
		              VALUES (numero_mobile_par, 'Mobile', codice_contatto);
		INSERT INTO Telefono (numero, descrizione, contatto_fk) 
		              VALUES (numero_fisso_par, 'Fisso', codice_contatto);
		INSERT INTO Indirizzo (via, descrizione, città, nazione, cap, contatto_fk)
		  			  VALUES (via_par, 'Principale', citta_par, nazione_par, cap_par, codice_contatto);
	END; $$;
	
--Funzione reindirizza
CREATE OR REPLACE FUNCTION reindirizza(contatto_chiamato Contatto.contatto_id%TYPE, numero_chiamato Telefono.numero%TYPE)
 	RETURNS Telefono.numero%TYPE
	LANGUAGE PLPGSQL
	AS $$
	DECLARE
		descrizione_num_chiamato Telefono.descrizione%TYPE;
		numero_reindirizzato Telefono.numero%TYPE;
	BEGIN
		--Si cerca la descrizione del numero chiamato
		SELECT descrizione INTO descrizione_num_chiamato
		FROM Telefono
		WHERE numero = trim(numero_chiamato) AND
		 	  contatto_chiamato = contatto_fk;
		--Se il numero chiamato non esiste tra quelli del contatto, si ritorna NULL
		IF descrizione_num_chiamato IS NULL THEN
			numero_reindirizzato := NULL;
			RAISE NOTICE 'Il numero chiamato non è presente tra quelli del contatto di ID %', contatto_chiamato;
		ELSE
			--Se il numero chiamato è fisso, ritorna il primo numero mobile del contatto
			IF     descrizione_num_chiamato = 'Fisso'  THEN
				SELECT numero INTO numero_reindirizzato
				FROM   Telefono
				WHERE  descrizione = 'Mobile' AND
					   contatto_fk = contatto_chiamato
				LIMIT 1;
			--Se il numero chiamato è mobile, ritorna il primo numero fisso del contatto
			ELSEIF descrizione_num_chiamato = 'Mobile' THEN
				SELECT numero INTO numero_reindirizzato
				FROM   Telefono
				WHERE  descrizione = 'Fisso' AND
					   contatto_fk = contatto_chiamato
				LIMIT 1;
			--Se il numero chiamato non è mobile o fisso, ritorna il primo numero di uno dei due tipi del contatto
			ELSE
				SELECT numero INTO numero_reindirizzato
				FROM   Telefono
				WHERE  (descrizione = 'Mobile' OR descrizione = 'Fisso') AND
					   contatto_fk = contatto_chiamato
				LIMIT 1;
			END IF;
		END IF;
		RETURN numero_reindirizzato;
	END; $$;

--Funzione che generalizza la ricerca dei contatti secondo un criterio indicato (nome, numero, email o account)
CREATE OR REPLACE FUNCTION cerca(criterio_ricerca VARCHAR, rubrica Rubrica.utente_id%TYPE, testo_ricerca VARCHAR)
	RETURNS TABLE(
				contatto_id Contatto.contatto_id%TYPE)
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		IF     criterio_ricerca = 'Nome'    THEN
			RETURN QUERY
				SELECT CNO.contatto_id
				FROM cerca_per_nome(rubrica, testo_ricerca) AS CNO;
		ELSEIF criterio_ricerca = 'Numero'  THEN
			RETURN QUERY
				SELECT CNU.contatto_id
				FROM cerca_per_numero(rubrica, testo_ricerca) AS CNU;
		ELSEIF criterio_ricerca = 'Email'   THEN
			RETURN QUERY
				SELECT CEM.contatto_id
				FROM cerca_per_email(rubrica, testo_ricerca) AS CEM;
		ELSEIF criterio_ricerca = 'Account' THEN
			RETURN QUERY
				SELECT CAC.contatto_id
				FROM cerca_per_account(rubrica, testo_ricerca) AS CAC;
		END IF;
	END; $$;

--Funzione che cerca i contatti per nome
CREATE OR REPLACE FUNCTION cerca_per_nome(rubrica Rubrica.utente_id%TYPE, nome_cercato Contatto.nome%TYPE)
	RETURNS TABLE(
				contatto_id Contatto.contatto_id%TYPE,
				nome Contatto.nome%TYPE,
				secondonome Contatto.secondonome%TYPE,
				cognome Contatto.cognome%TYPE,
				foto Contatto.foto%TYPE,
				rubrica_fk Contatto.rubrica_fk%TYPE)
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		RETURN QUERY
			SELECT C1.contatto_id, C1.nome, C1.secondonome, C1.cognome, C1.foto, C1.rubrica_fk
			FROM Contatto AS C1
			WHERE rubrica = C1.rubrica_fk AND (position(lower(nome_cercato) in lower(C1.nome)) <> 0);
	END; $$;
	
--Funzione che cerca i contatti per numero di telefono
CREATE OR REPLACE FUNCTION cerca_per_numero(rubrica Rubrica.utente_id%TYPE, numero_cercato Telefono.numero%TYPE)
	RETURNS TABLE(
				contatto_id Telefono.contatto_fk%TYPE,
				telefono_id Telefono.telefono_id%TYPE,
				numero Telefono.numero%TYPE,
				descrizione Telefono.descrizione%TYPE
				)
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		RETURN QUERY
			SELECT T1.contatto_fk, T1.telefono_id, T1.numero, T1.descrizione
			FROM Telefono AS T1, Contatto AS C1
			WHERE rubrica = C1.rubrica_fk AND (position(lower(numero_cercato) in lower(T1.numero)) <> 0) AND
				  C1.contatto_id = T1.contatto_fk;
	END; $$;
	
--Funzione che cerca i contatti per indirizzo email
CREATE OR REPLACE FUNCTION cerca_per_email(rubrica Rubrica.utente_id%TYPE, email_cercata VARCHAR)
	RETURNS TABLE(
				contatto_id Email.contatto_fk%TYPE,
				email_id Email.email_id%TYPE,
				indirizzoEmail Email.indirizzoEmail%TYPE,
				descrizione Email.descrizione%TYPE)
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		RETURN QUERY
			SELECT E1.contatto_fk, E1.email_id, E1.indirizzoEmail, E1.descrizione
			FROM Email AS E1, Contatto AS C1
			WHERE rubrica = C1.rubrica_fk AND (position(lower(email_cercata) in lower(E1.indirizzoEmail)) <> 0) AND
				  C1.contatto_id = E1.contatto_fk;
	END; $$;

--Funzione che cerca i contatti per nickname dell'account
CREATE OR REPLACE FUNCTION cerca_per_account(rubrica Rubrica.utente_id%TYPE, nickname_cercato Account.nickname%TYPE)
	RETURNS TABLE(
				contatto_id Contatto.contatto_id%TYPE,
				nickname Account.nickname%TYPE,
				fornitore Account.fornitore%TYPE,
				indirizzoEmail Account.indirizzoEmail%TYPE,
				frasestato Account.frasestato%TYPE)
	LANGUAGE PLPGSQL
	AS $$
	BEGIN
		RETURN QUERY
			SELECT E1.contatto_fk, A1.nickname, A1.fornitore, A1.indirizzoEmail, A1.frasestato
			FROM Account AS A1, Associa AS AS1, Email AS E1, Contatto AS C1
			WHERE rubrica = C1.rubrica_fk AND (position(lower(nickname_cercato) in lower(A1.nickname)) <> 0) AND
				  C1.contatto_id = E1.contatto_fk AND E1.email_id = AS1.email_fk AND AS1.FornitoreAccount = A1.Fornitore AND
				  AS1.indirizzoEmailAccount = A1.indirizzoEmail;
	END; $$;