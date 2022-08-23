package implementazionedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.SistemaDAO;
import database.ConnessioneDatabase;
import model.Rubrica;

public class SistemaImplementazionePostgresDAO implements SistemaDAO{
	
	private ArrayList<Rubrica> rubriche;
	
	private Connection connection;
	
	public SistemaImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
				System.out.println("valore trovato: " + rs.getString("utente_id") );
				Rubrica nuovaRubrica = new Rubrica(rs.getString("utente_id"));
				rubriche.add(nuovaRubrica);	
			}
			connection.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return rubriche;		
	}
	
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
