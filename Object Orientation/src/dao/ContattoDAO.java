package dao;

import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContattoDAO.
 */
public interface ContattoDAO {
	
	/**
	 * Adds the indirizzo.
	 *
	 * @param via the via
	 * @param città the città
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param descrizione the descrizione
	 * @param ido the ido
	 * @throws SQLException the SQL exception
	 */
	public void addIndirizzo(String via, String città, String nazione, String cap, String descrizione, int ido) throws SQLException;
	
	/**
	 * Adds the telefono.
	 *
	 * @param numero the numero
	 * @param descrizione the descrizione
	 * @param id_contatto the id contatto
	 * @throws SQLException the SQL exception
	 */
	public void addTelefono(String numero, String descrizione, int id_contatto) throws SQLException;
	
	/**
	 * Adds the email.
	 *
	 * @param indirizzoEmail the indirizzo email
	 * @param descrizione the descrizione
	 * @param id_contatto the id contatto
	 * @throws SQLException the SQL exception
	 */
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto) throws SQLException;
}
