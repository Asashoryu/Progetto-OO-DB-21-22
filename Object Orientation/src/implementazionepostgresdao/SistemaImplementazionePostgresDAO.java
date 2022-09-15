package implementazionepostgresdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.SistemaDAO;
import database.ConnessioneDatabase;
import model.Rubrica;

/** Gestisce l'oggetto Sistema e la sua connessione al DB. */
public class SistemaImplementazionePostgresDAO implements SistemaDAO{
	
	/** ArrayList di utenti della rubrica. */
	private ArrayList<Rubrica> rubriche;
	
	/** Connessione al DB. */
	private Connection connection;
	
	/** Costruttore di un oggetto SistemaImplementazionePostgresDAO e instaura la connessione con il DB. */
	public SistemaImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carica le rubriche dal DB.
	 *
	 * @return ArrayList di rubriche
	 */
	public ArrayList<Rubrica> loadRubriche() {
		PreparedStatement recuperaRubriche;
		try {
			System.out.println("SELECT * FROM Rubrica ORDER BY utente_id");
			recuperaRubriche = connection.prepareStatement(
				"SELECT * FROM Rubrica ORDER BY utente_id"
					);
			ResultSet rs = recuperaRubriche.executeQuery();
			rubriche= new ArrayList<Rubrica>();
			while (rs.next()) {
				Rubrica nuovaRubrica = new Rubrica(rs.getString("utente_id"));
				rubriche.add(nuovaRubrica);	
			}
			connection.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return rubriche;		
	}
	
	/**
	 * Modifica utente della rubrica nel database.
	 *
	 * @param vecchiaRubrica vecchio nome della rubrica da modificare
	 * @param nuovaRubrica nuovo nome della rubrica modificata
	 * @throws SQLException SQL exception
	 */
	public void updateRubrica(String vecchiaRubrica, String nuovaRubrica) throws SQLException {
		System.out.println("UPDATE Rubrica "+
				"SET utente_id = "+"\'"+nuovaRubrica+"\' " + 
				"WHERE utente_id = "+"\'"+vecchiaRubrica+"\'");
		try {
			PreparedStatement modificaRubrica = connection.prepareStatement(
					"UPDATE Rubrica "+
					"SET utente_id = "+"\'"+nuovaRubrica+"\' " + 
					"WHERE utente_id = "+"\'"+vecchiaRubrica+"\'");
				
			modificaRubrica.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Carica un nuovo utente della rubrica nel database.
	 *
	 * @param nomeRubrica nome della rubrica da inserire
	 * @throws SQLException SQL exception
	 */
	public void addRubrica(String nomeRubrica) throws SQLException {
		System.out.println("INSERT INTO Rubrica VALUES "+"(\'"+nomeRubrica+"\')");
		try {
			PreparedStatement aggiungiRubrica = connection.prepareStatement(
					"INSERT INTO Rubrica VALUES "+"(\'"+nomeRubrica+"\')");
			aggiungiRubrica.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Elimina un utente della rubrica dal database.
	 *
	 * @param nomeRubrica nome della rubrica da eliminare
	 * @throws SQLException SQL exception
	 */
	public void deleteRubrica(String nomeRubrica) throws SQLException {
		System.out.println("DELETE FROM Rubrica WHERE utente_id = "+"\'"+nomeRubrica+"\'");
		try {
			PreparedStatement cancellaRubrica = connection.prepareStatement(
					"DELETE FROM Rubrica WHERE utente_id = "+"\'"+nomeRubrica+"\'");
			cancellaRubrica.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
