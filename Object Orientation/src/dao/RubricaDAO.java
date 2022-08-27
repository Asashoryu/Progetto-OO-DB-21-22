package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Contatto;
import model.Gruppo;

// TODO: Auto-generated Javadoc
/**
 * The Interface RubricaDAO.
 */
public interface RubricaDAO {
	
	/**
	 * Load contatti.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @param contatti the contatti
	 */
	public void loadContatti(String nomeRubrica, ArrayList<Contatto> contatti);
	
	/**
	 * Load gruppi.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @param contatti the contatti
	 * @param gruppi the gruppi
	 */
	public void loadGruppi(String nomeRubrica, ArrayList<Contatto> contatti, ArrayList<Gruppo> gruppi);
	
	/**
	 * Apri connessione.
	 *
	 * @return the connection
	 */
	public Connection apriConnessione();
	
	/**
	 * Gets the connessione.
	 *
	 * @return the connessione
	 */
	public Connection getConnessione();
	
	/**
	 * Genera contatto ID.
	 *
	 * @param connTransazione the conn transazione
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int generaContattoID(Connection connTransazione) throws SQLException;
	
	/**
	 * Adds the info contatto.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @param nome the nome
	 * @param secondonome the secondonome
	 * @param cognome the cognome
	 * @param numMobile the num mobile
	 * @param numFisso the num fisso
	 * @param via the via
	 * @param citta the citta
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param id the id
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void addInfoContatto(String nomeRubrica, String nome, String secondonome, String cognome, String numMobile,
			String numFisso, String via, String citta, String nazione, String cap, int id, Connection connessione) throws SQLException;
	
	/**
	 * Change info contatto.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @param nome the nome
	 * @param secondonome the secondonome
	 * @param cognome the cognome
	 * @param numMobile the num mobile
	 * @param numFisso the num fisso
	 * @param via the via
	 * @param citta the citta
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param vecchioContattoId the vecchio contatto id
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void changeInfoContatto(String nomeRubrica, String nome, String secondonome, String cognome,
			String numMobile, String numFisso, String via, String citta, String nazione, String cap,
			int vecchioContattoId, Connection connessione) throws SQLException;
	
	/**
	 * Adds the immagine.
	 *
	 * @param pathImmagine the path immagine
	 * @param id the id
	 * @param connTransazione the conn transazione
	 * @throws SQLException the SQL exception
	 */
	public void addImmagine(String pathImmagine, int id, Connection connTransazione) throws SQLException;
	
	/**
	 * Adds the indirizzo.
	 *
	 * @param via the via
	 * @param città the città
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param descrizione the descrizione
	 * @param id the id
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void addIndirizzo(String via, String città, String nazione, String cap, String descrizione, int id, Connection connessione) throws SQLException;
	
	/**
	 * Adds the telefono.
	 *
	 * @param numero the numero
	 * @param descrizione the descrizione
	 * @param id_contatto the id contatto
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void addTelefono(String numero, String descrizione, int id_contatto, Connection connessione) throws SQLException;
	
	/**
	 * Adds the email.
	 *
	 * @param indirizzoEmail the indirizzo email
	 * @param descrizione the descrizione
	 * @param id_contatto the id contatto
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto, Connection connessione)
			throws SQLException;
	
	/**
	 * Load account contatto.
	 *
	 * @param contatto the contatto
	 * @param connTransazione the conn transazione
	 * @throws SQLException the SQL exception
	 */
	public void loadAccountContatto(Contatto contatto, Connection connTransazione) throws SQLException;
	
	/**
	 * Delete contatto.
	 *
	 * @param codiceContatto the codice contatto
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void deleteContatto(int codiceContatto, Connection connessione) throws SQLException;
	
	/**
	 * Adds the info gruppo.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @param nuovoGruppo the nuovo gruppo
	 * @param connessione the connessione
	 * @throws Exception the exception
	 */
	public void addInfoGruppo(String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws Exception;
	
	/**
	 * Delete gruppo.
	 *
	 * @param codiceGruppo the codice gruppo
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void deleteGruppo(int codiceGruppo, Connection connessione) throws SQLException;
	
	/**
	 * Change info gruppo.
	 *
	 * @param nomeRubrica the nome rubrica
	 * @param nuovoGruppo the nuovo gruppo
	 * @param connessione the connessione
	 * @throws SQLException the SQL exception
	 */
	public void changeInfoGruppo (String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws SQLException;
	
}
