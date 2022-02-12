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
			recuperaRubriche = connection.prepareStatement(
				"SELECT * FROM Rubrica");
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
	
	public void updateRubrica(String vecchiaRubrica, String nuovaRubrica) {
		System.out.println("UPDATE Rubrica "+
					"SET utente_id = "+" \'"+nuovaRubrica+"\'" + 
					"WHERE utente_id = "+" \'"+vecchiaRubrica+"\'");
		try {
			PreparedStatement modificaRubrica = connection.prepareStatement(
					"UPDATE Rubrica "+
					"SET utente_id = "+" \'"+nuovaRubrica+"\'" + 
					"WHERE utente_id = "+" \'"+vecchiaRubrica+"\'");
				
			modificaRubrica.executeUpdate();
			connection.close();
			} catch (SQLException e) {
		e.printStackTrace();
		}
	}
}
