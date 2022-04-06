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
	
	public ArrayList<Contatto> loadContatti(String nomeRubrica) {
		System.out.println("SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'");
		PreparedStatement recuperaContatti;
		try {
			recuperaContatti = connection.prepareStatement(
				"SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'");
			ResultSet rs = recuperaContatti.executeQuery();
			contatti = new ArrayList<Contatto>();
			while (rs.next()) {
				Contatto nuovoContatto = new Contatto(rs.getString("nome"), rs.getString("secondonome"), rs.getString("cognome"));
				contatti.add(nuovoContatto);
			}
			connection.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contatti;
	}
	
	public void addContatti(String nome, String secondonome, String cognome, String nomerubrica) {
		System.out.println("INSERT INTO Contatto (nome, secondonome, cognome) VALUES "+"(\'"+nomeRubrica+"\')");
		try {
			PreparedStatement aggiungiContatto = connection.prepareStatement(
					"INSERT INTO Rubrica VALUES "+"(\'"+nomeRubrica+"\')");
			aggiungiRubrica.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
		
}
