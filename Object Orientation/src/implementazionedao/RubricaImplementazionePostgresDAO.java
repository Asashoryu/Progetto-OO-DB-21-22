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
	
	/** La costruzione di un oggetto comporta la creazione di una connessione col DB */
	public RubricaImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Contatto> loadContatti(String nomeRubrica) {
		System.out.println("SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'");
		PreparedStatement recuperaContatti;
		try {
			recuperaContatti = connection.prepareStatement(
				"SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'");
			ResultSet rs = recuperaContatti.executeQuery();
			contatti = new ArrayList<Contatto>();
			while (rs.next()) {
				Contatto nuovoContatto = new Contatto(rs.getString("nome"), rs.getString("secondonome"), rs.getString("cognome"), rs.getInt("contatto_id"));
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
	/* @returns id del contatto creato*/
	@Override
	public int addContatto(String nomeRubrica, String nome, String secondonome, String cognome,
			                String numMobile, String numFisso, String via, String citta, String nazione, String cap) throws SQLException {
		int id;
		System.out.println("SELECT coherent_insertion_f('" + nomeRubrica + "','" + nome +       "','" + secondonome + "',"
									                 +" '" + cognome +     "', '" + numMobile + "', '" + numFisso   + "',"
									                 +" '" + via +         "', '" + citta +     "', '" + nazione  +   "',"
									                 +" '" + cap +         "')");
		try 
		{
			PreparedStatement aggiungiContatto = connection.prepareStatement
					(
					"SELECT coherent_insertion_f('" + nomeRubrica + "','" + nome +       "','" + secondonome + "',"
							                  +" '" + cognome +     "', '" + numMobile + "', '" + numFisso   + "',"
							                  +" '" + via +         "', '" + citta +     "', '" + nazione  +   "',"
							                  +" '" + cap +         "')"
					);
			ResultSet rs = aggiungiContatto.executeQuery();
			id = rs.getInt("coherent_insertion_f");
			connection.close();
		}
		catch (SQLException e) 
		{
			throw e;
		}
		return id;
	}
		
}
