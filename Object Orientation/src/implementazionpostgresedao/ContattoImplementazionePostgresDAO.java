package implementazionpostgresedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ContattoDAO;
import database.ConnessioneDatabase;

/** Gestisce l'oggetto contatto e la sua connessione con il database. */
public class ContattoImplementazionePostgresDAO implements ContattoDAO{
	
	/** Instanzia la connessione. */
	private Connection connection;
	
	/**  Instaura la connessione con il database. */
	public ContattoImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Aggiunge nel database un nuovo indirizzo con i suoi relativi campi.
	 * @param via la via dell'indirizzo
	 * @param città la città dell'indirizzo
	 * @param nazione la nazione dell'indirizzo
	 * @param cap il CAP dell'indirizzo
	 * @param descrizione la descrizione data all'indirizzo
	 * @param id l'identificativo del nuovo indirizzo
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
	 * Aggiunge nel database un nuovo numero di telefono.
	 *
	 * @param numero cifre del numero da inserire 
	 * @param descrizione la descrizione del numero
	 * @param id_contatto l'identificativo del contatto a cui appartiene il nuovo numero
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
	 * Aggiunge una nuova email nel database con le sue relative informazioni.
	 *
	 * @param indirizzoEmail l'indirizzo email da aggiungere
	 * @param descrizione la descrizione data alla nuova email
	 * @param id_contatto l'identificativo del contatto a cui appartiene la nuova email
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
