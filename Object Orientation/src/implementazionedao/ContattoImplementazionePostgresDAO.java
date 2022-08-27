package implementazionedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ContattoDAO;
import database.ConnessioneDatabase;

// TODO: Auto-generated Javadoc
/**
 * The Class ContattoImplementazionePostgresDAO.
 */
public class ContattoImplementazionePostgresDAO implements ContattoDAO{
	
	/** The connection. */
	private Connection connection;
	
	/**
	 * Instantiates a new contatto implementazione postgres DAO.
	 */
	public ContattoImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the indirizzo.
	 *
	 * @param via the via
	 * @param città the città
	 * @param nazione the nazione
	 * @param cap the cap
	 * @param descrizione the descrizione
	 * @param id the id
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void addIndirizzo(String via, String città, String nazione, String cap, String descrizione, int id) throws SQLException
	{
		System.out.println("INSERT INTO Indirizzo(via, città, nazione, cap, descrizione, contatto_fk)"
				                + " VALUES "+"(\'" + via + "\', \'" + città       + "\', \'"+ nazione + "\',"
									        + "\'" + cap + "\', \'" + descrizione + "\',  " + id     + ")");
		try {
			PreparedStatement aggiungiIndirizzo = connection.prepareStatement(
					"INSERT INTO Indirizzo(via, città, nazione, cap, descrizione, contatto_fk)"
							+ " VALUES "+"(\'" + via + "\', \'" + città       + "\', \'"+ nazione + "\',"
						                + "\'" + cap + "\', \'" + descrizione + "\',  " + id     + ")");
			aggiungiIndirizzo.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Adds the telefono.
	 *
	 * @param numero the numero
	 * @param descrizione the descrizione
	 * @param id_contatto the id contatto
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void addTelefono(String numero, String descrizione, int id_contatto) throws SQLException
	{
		System.out.println("INSERT INTO Telefono(numero, descrizione, contatto_fk)"
                                + " VALUES (\'" + numero + "\', \'" + descrizione + "\', " + id_contatto + ")");
		try {
			PreparedStatement aggiungTelefono = connection.prepareStatement(
					"INSERT INTO Telefono(numero, descrizione, contatto_fk)"
                          + " VALUES (\'" + numero + "\', \'" + descrizione + "\', " + id_contatto + ")");
			aggiungTelefono.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/**
	 * Adds the email.
	 *
	 * @param indirizzoEmail the indirizzo email
	 * @param descrizione the descrizione
	 * @param id_contatto the id contatto
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto) throws SQLException
	{
		System.out.println("INSERT INTO Email(indirizzoemail, descrizione, contatto_fk)"
                                + " VALUES (\'" + indirizzoEmail + "\', \'" + descrizione + "\', " + id_contatto + ")");
	try {
			PreparedStatement aggiungEmail = connection.prepareStatement(
					"INSERT INTO Email(indirizzoemail, descrizione, contatto_fk)"
                            + " VALUES (\'" + indirizzoEmail + "\', \'" + descrizione + "\', " + id_contatto + ")");
			aggiungEmail.executeUpdate();
			connection.close();
	} catch (SQLException e) {
			e.printStackTrace();
			throw e;
	}
		
	}
}
