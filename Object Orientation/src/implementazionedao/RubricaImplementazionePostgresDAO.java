package implementazionedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RubricaDAO;
import database.ConnessioneDatabase;
import model.Contatto;
import model.Gruppo;
import model.Rubrica;
import model.Indirizzo.tipoIndirizzo;

public class RubricaImplementazionePostgresDAO implements RubricaDAO{
	
	private ArrayList<Contatto> contatti;
	
	private Connection connection;
	
	private ResultSet rs;
	
	/** La costruzione di un oggetto comporta la creazione di una connessione col DB */
	public RubricaImplementazionePostgresDAO() {
	}
	
	public Connection apriConnessione()
	{
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public Connection getConnessione()
	{
		return connection;
	}
	
	@Override
	public void loadContatti(String nomeRubrica, ArrayList<Contatto> contatti) {
		System.out.println("SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'");
		PreparedStatement recuperaContatti;
		PreparedStatement recuperaIndirizzi;
		PreparedStatement recuperaNumTelefono;
		PreparedStatement recuperaEmail;
		try {
			// aggiunge le informazioni sul contatto
			recuperaContatti = connection.prepareStatement(
				"SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'"
				);
			ResultSet rsc = recuperaContatti.executeQuery();
			while (rsc.next())
			{
				Contatto nuovoContatto = new Contatto(rsc.getString("nome"), rsc.getString("secondonome"), rsc.getString("cognome"), rsc.getInt("contatto_id"));
				contatti.add(nuovoContatto);
				
				// per ogni contatto vengono aggiunti i suoi indirizzi
				System.out.println("SELECT * FROM Indirizzo WHERE contatto_fk = " + nuovoContatto.getId());
				recuperaIndirizzi = connection.prepareStatement(
						"SELECT * FROM Indirizzo WHERE contatto_fk = " + nuovoContatto.getId()
						);
				ResultSet rsi = recuperaIndirizzi.executeQuery();
				while (rsi.next())
				{
					if (rsi.getString("descrizione").equals("Principale"))
					{
						nuovoContatto.addIndirizzo(rsi.getString("via"), rsi.getString("città"), rsi.getString("nazione"), rsi.getString("cap"), tipoIndirizzo.Principale);
					}
					else 
					{
						nuovoContatto.addIndirizzo(rsi.getString("via"), rsi.getString("città"), rsi.getString("nazione"), rsi.getString("cap"), tipoIndirizzo.Secondario);
					}
				}
				rsi.close();
				
				// per ogni contatto vengono aggiunti i suoi numeri di telefono
				System.out.println("SELECT * FROM Telefono WHERE contatto_fk = " + nuovoContatto.getId());
				recuperaNumTelefono = connection.prepareStatement(
						"SELECT * FROM Telefono WHERE contatto_fk = " + nuovoContatto.getId()
						);
				ResultSet rst = recuperaNumTelefono.executeQuery();
				while (rst.next())
				{
					nuovoContatto.addTelefono(rst.getString("numero"), rst.getString("descrizione"));
				}
				rst.close();
				
				// per ogni contatto vengono aggiunte le sue email
				System.out.println("SELECT * FROM Email WHERE contatto_fk = " + nuovoContatto.getId());
				recuperaEmail = connection.prepareStatement(
						"SELECT * FROM Email WHERE contatto_fk = " + nuovoContatto.getId()
						);
				ResultSet rse = recuperaEmail.executeQuery();
				while (rse.next())
				{
					nuovoContatto.addEmail(rse.getString("indirizzoemail"), rse.getString("descrizione"));
				}
				rse.close();
			}
			rsc.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadGruppi(String nomeRubrica, ArrayList<Contatto> contatti, ArrayList<Gruppo> gruppi) {
		System.out.println("SELECT * FROM Gruppo WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'");
		PreparedStatement recuperaGruppi;
		try {
			// aggiunge le informazioni sul contatto
			recuperaGruppi = connection.prepareStatement(
				"SELECT * FROM Gruppo WHERE rubrica_fk = "+"\'"+nomeRubrica+"\'"
				);
			ResultSet rsg = recuperaGruppi.executeQuery();
			while(rsg.next())
			{
				Gruppo nuovoGruppo = new Gruppo(rsg.getString("nome"), rsg.getInt("gruppo_id"));
				PreparedStatement recuperaContattiGruppo;
				System.out.println(" SELECT * FROM Composizione WHERE gruppo_fk = " + rsg.getInt("gruppo_id") + ";");
				recuperaContattiGruppo = connection.prepareStatement(
						" SELECT * FROM Composizione WHERE gruppo_fk = " + rsg.getInt("gruppo_id") + "; "
						);
				ResultSet rsc = recuperaContattiGruppo.executeQuery();
				// per ogni risultato si cerca il contatto con l'id trovato e lo si aggiunge al nuovo gruppo
				while (rsc.next())
				{
					for(Contatto contatto : contatti)
					{
						if (contatto.getId() == rsc.getInt("contatto_fk"))
						{
							nuovoGruppo.getContatti().add(contatto);
							break;
						}
					}
				}
				System.out.println("Debug : contatti del nuovo gruppo");
				for (Contatto contatto : nuovoGruppo.getContatti())
				{
					System.out.println("Debug : " + contatto.getNome());
				}
				gruppi.add(nuovoGruppo);
			}
			rsg.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int startTransazione(Connection connessione) throws SQLException
	{
		int id = -1;
		System.out.println(" BEGIN; SELECT generate_new_contatto_id(); ");
		
		try
		{
			PreparedStatement initEGeneraContattoID = connessione.prepareStatement
					(
						" SELECT generate_new_contatto_id(); "
					);
			rs = initEGeneraContattoID.executeQuery();
			System.out.println("Si supera la parte che esegue la query");
			//Si sposta il cursore in avanti poiché inizialmente punta a prima della row iniziale
			if(rs.next() == true)
			{
				id = rs.getInt("generate_new_contatto_id");				
			}
			rs.close();
			return id;
		}
		catch (SQLException e) 
		{
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			throw e;
		}
		
	}
	
	/* @returns id del contatto creato*/
	@Override
	public void addContatto(String nomeRubrica, String nome, String secondonome, String cognome,
			                String numMobile, String numFisso, String via, String citta, String nazione, String cap,
			                int id, Connection connessione) throws SQLException {
		System.out.println("CALL coherent_insertion_f('"   + nomeRubrica + "', '" + nome +          "', '" + secondonome + "',"
									                 +" '" + cognome +     "', '" + numMobile +     "', '" + numFisso   + "',"
									                 +" '" + via +         "', '" + citta +         "', '" + nazione  +   "',"
									                 +" '" + cap +         "',  " + id+"); ");
		try 
		{
			PreparedStatement aggiungiContatto = connessione.prepareStatement
					(
					"CALL coherent_insertion_f('"   + nomeRubrica + "','" + nome +       "','" + secondonome + "',"
							                  +" '" + cognome +     "', '" + numMobile + "', '" + numFisso   + "',"
							                  +" '" + via +         "', '" + citta +     "', '" + nazione  +   "',"
							                  +" '" + cap +         "',  " + id+"); "
					);
			aggiungiContatto.execute();
			System.out.println("Si supera la parte che genera un errore di inserimento");
		}
		catch (SQLException e) 
		{
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			throw e;
		}
	}
	
	@Override
	public void addIndirizzo(String via, String città, String nazione, String cap, String descrizione, int id, Connection connessione) throws SQLException
	{
		System.out.println(" INSERT INTO Indirizzo(via, città, nazione, cap, descrizione, contatto_fk)"
				                + " VALUES "+"(\'" + via + "\', \'" + città       + "\', \'"+ nazione + "\',"
									        + "\'" + cap + "\', \'" + descrizione + "\',  " + id     + "); ");
		try {
			PreparedStatement aggiungiIndirizzo = connessione.prepareStatement(
					" INSERT INTO Indirizzo(via, città, nazione, cap, descrizione, contatto_fk)"
							 + " VALUES "+"(\'" + via + "\', \'" + città       + "\', \'"+ nazione + "\',"
						                 + "\'" + cap + "\', \'" + descrizione + "\',  " + id     + "); ");
			aggiungiIndirizzo.executeUpdate();
		} catch (SQLException e) {
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void addTelefono(String numero, String descrizione, int id_contatto, Connection connessione) throws SQLException
	{
		System.out.println(" INSERT INTO Telefono(numero, descrizione, contatto_fk)"
                                 + " VALUES (\'" + numero + "\', \'" + descrizione + "\', " + id_contatto + "); ");
		try {
			PreparedStatement aggiungiTelefono = connessione.prepareStatement(
					" INSERT INTO Telefono(numero, descrizione, contatto_fk)"
                           + " VALUES (\'" + numero + "\', \'" + descrizione + "\', " + id_contatto + "); ");
			aggiungiTelefono.executeUpdate();
		} catch (SQLException e) {
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto, Connection connessione) throws SQLException
	{
		System.out.println(" INSERT INTO Email(indirizzoemail, descrizione, contatto_fk)"
	                     + " VALUES (\'" + indirizzoEmail + "\', \'" + descrizione + "\', " + id_contatto + "); ");
		try {
				PreparedStatement aggiungiEmail = connessione.prepareStatement(
						" INSERT INTO Email(indirizzoemail, descrizione, contatto_fk)"
	                             + " VALUES (\'" + indirizzoEmail + "\', \'" + descrizione + "\', " + id_contatto + "); ");
				aggiungiEmail.executeUpdate();
		} catch (SQLException e) {
				connessione.rollback();
				System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
				e.printStackTrace();
				throw e;
		}
	}
	
	public void deleteContatto(int codiceContatto, Connection connessione) throws SQLException
	{
		System.out.println(" DELETE FROM Contatto WHERE Contatto_ID = " + codiceContatto + "; ");
		try {
				PreparedStatement cancellaContatto = connessione.prepareStatement(
						" DELETE FROM Contatto WHERE Contatto_ID = " + codiceContatto + "; ");
				cancellaContatto.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
				throw e;
		}
	}
		
}
