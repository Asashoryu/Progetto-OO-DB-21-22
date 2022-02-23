package implementazionedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RubricaDAO;
import database.ConnessioneDatabase;
import model.Contatto;
import model.Rubrica;

public class RubricaImplementazionePostgresDAO implements RubricaDAO{
/*	
	private ArrayList<Contatto> contatti;
	
	private Connection connection;
	
	public RubricaImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contatti> loadContatti() {
		PreparedStatement recuperaRubriche;
		try {
			recuperaContatti = connection.prepareStatement(
				"SELECT * FROM Contatto, Rubrica WHERE ");
			ResultSet rs = recuperaRubriche.executeQuery();
			contatti = new ArrayList<Contatto>();
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
	}
	*/
}
