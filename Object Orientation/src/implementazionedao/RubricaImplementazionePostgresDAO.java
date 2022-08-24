package implementazionedao;

import model.Contatto;
import model.Email;
import model.Gruppo;
import model.Indirizzo.tipoIndirizzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RubricaDAO;
import database.ConnessioneDatabase;

public class RubricaImplementazionePostgresDAO implements RubricaDAO{
	
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
		System.out.println(" SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Contatto_ID; ");
		PreparedStatement recuperaContatti;
		PreparedStatement recuperaIndirizzi;
		PreparedStatement recuperaNumTelefono;
		PreparedStatement recuperaEmail;
		PreparedStatement recuperaAccount;
		try {
			// aggiunge le informazioni sul contatto
			recuperaContatti = connection.prepareStatement(
				" SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Contatto_ID; "
				);
			ResultSet rsc = recuperaContatti.executeQuery();
			while (rsc.next())
			{
				Contatto nuovoContatto = new Contatto(rsc.getString("nome"), rsc.getString("secondonome"), rsc.getString("cognome"),
						                              rsc.getString("foto"), rsc.getInt("contatto_id"));
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
					System.out.println(" SELECT * FROM Associa, Email, Account "
							         + " WHERE email_id = email_fk AND account_id = account_fk AND email_id = "+rse.getInt("email_id")+ "; ");
					recuperaAccount = connection.prepareStatement(
							" SELECT * FROM Associa, Email, Account "
					      + " WHERE email_id = email_fk AND account_id = account_fk AND email_id = "+rse.getInt("email_id")+ "; "
							);
					ResultSet rsea = recuperaAccount.executeQuery();
					while (rsea.next())
					{
						int indice = nuovoContatto.getEmail().size() - 1;
						nuovoContatto.getEmail().get(indice).addAccount(rsea.getString("fornitore"), rsea.getString("frasestato"), rsea.getString("nickname"));
					}
					rsea.close();
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
		System.out.println(" SELECT * FROM Gruppo WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Gruppo_ID; ");
		PreparedStatement recuperaGruppi;
		try {
			// aggiunge le informazioni sul contatto
			recuperaGruppi = connection.prepareStatement(
				" SELECT * FROM Gruppo WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Gruppo_ID; "
				);
			ResultSet rsg = recuperaGruppi.executeQuery();
			while(rsg.next())
			{
				Gruppo nuovoGruppo = new Gruppo(rsg.getString("nome"), rsg.getInt("gruppo_id"));
				PreparedStatement recuperaContattiGruppo;
				System.out.println(" SELECT * FROM Composizione WHERE gruppo_fk = " + rsg.getInt("gruppo_id") + " ORDER BY contatto_fk;");
				recuperaContattiGruppo = connection.prepareStatement(
						" SELECT * FROM Composizione WHERE gruppo_fk = " + rsg.getInt("gruppo_id") + " ORDER BY contatto_fk;"
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
	
	public int generaContattoID(Connection connessione) throws SQLException
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
		}
		catch (SQLException e) 
		{
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			throw e;
		}
	}
	
	public void changeContatto(String nomeRubrica, String nome, String secondonome, String cognome,
				               String numMobile, String numFisso, String via, String citta, String nazione, String cap,
				               int vecchioContattoId, Connection connessione) throws SQLException {
		
		try {
				// Memorizzo i gruppi in cui il contatto è presente
				System.out.println(" SELECT * FROM Composizione WHERE Contatto_FK = " + vecchioContattoId + "; ");
				PreparedStatement selezionaGruppi = connessione.prepareStatement(
						" SELECT * FROM Composizione WHERE Contatto_FK = " + vecchioContattoId + "; ");
				ResultSet rsg = selezionaGruppi.executeQuery();
			
				System.out.println(" DELETE FROM Contatto WHERE Contatto_ID = " + vecchioContattoId + "; ");
				PreparedStatement cancellaContatto = connessione.prepareStatement(
						" DELETE FROM Contatto WHERE Contatto_ID = " + vecchioContattoId + "; ");
				cancellaContatto.executeUpdate();
		
				System.out.println("CALL coherent_insertion_f('"   + nomeRubrica + "', '" + nome +          "', '" + secondonome + "',"
								                 +" '" + cognome +     "', '" + numMobile +     "', '" + numFisso   + "',"
								                 +" '" + via +         "', '" + citta +         "', '" + nazione  +   "',"
								                 +" '" + cap +         "',  " + vecchioContattoId+"); ");
				PreparedStatement aggiungiContatto = connessione.prepareStatement
					(
					"CALL coherent_insertion_f('"   + nomeRubrica + "','" + nome +       "','" + secondonome + "',"
							                  +" '" + cognome +     "', '" + numMobile + "', '" + numFisso   + "',"
							                  +" '" + via +         "', '" + citta +     "', '" + nazione  +   "',"
							                  +" '" + cap +         "',  " + vecchioContattoId+"); "
					);
				aggiungiContatto.execute();
				// reinserisco il contatto nei gruppi a cui appartiene.
				while (rsg.next())
				{
					System.out.println(" INSERT INTO Composizione VALUES (" + rsg.getInt("contatto_fk") + ", " + rsg.getInt("gruppo_fk") + "); ");
					PreparedStatement reinserisciContattoGruppo = connessione.prepareStatement(
							" INSERT INTO Composizione VALUES (" + rsg.getInt("contatto_fk") + ", " + rsg.getInt("gruppo_fk") + "); ");
					reinserisciContattoGruppo.executeUpdate();
				}
		}
		catch (SQLException e) 
		{
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			throw e;
		}
	}
	
	@Override
	public void addImmagine(String pathImmagine, int id, Connection connessione) throws SQLException {
		System.out.println(" UPDATE Contatto SET foto  = '" + pathImmagine + "' WHERE contatto_id = " + id + "; ");
		try {
			PreparedStatement aggiungiImmagine = connessione.prepareStatement(
					" UPDATE Contatto SET foto  = '" + pathImmagine + "' WHERE contatto_id = " + id + "; ");
			aggiungiImmagine.executeUpdate();
		} catch (SQLException e) {
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query di inserimento immagine");
			e.printStackTrace();
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
	
	@Override
	public void loadAccountContatto(Contatto contatto, Connection connessione) throws SQLException
	{
		try {
			for (Email email : contatto.getEmail())
			{
				System.out.println(" SELECT * FROM Associa, Email, Account "
						         + " WHERE email_id = email_fk AND account_id = account_fk AND Email.indirizzoemail = '" + email.getStringaEmail() + "'; "
						);
				PreparedStatement recuperaAccount = connessione.prepareStatement(
						" SELECT * FROM Associa, Email, Account "
					  + " WHERE email_id = email_fk AND account_id = account_fk AND Email.indirizzoemail = '" + email.getStringaEmail() + "'; "
						);
				ResultSet rsa = recuperaAccount.executeQuery();
				while (rsa.next())
				{
					email.addAccount(rsa.getString("fornitore"), rsa.getString("frasestato"), rsa.getString("nickname"));
				}
				rsa.close();
			}
		} catch (SQLException e) {
			connessione.rollback();
			System.out.println("ROLLBACK: è stato rilevanto un errore nella query");
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
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
	
	public void addGruppo(String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws Exception
	{
		
		try {
			connessione.setAutoCommit(false);
			System.out.println(" SELECT generate_new_gruppo_id(); ");
			PreparedStatement generaId = connessione.prepareStatement(
					" SELECT generate_new_gruppo_id(); "
					);
			rs = generaId.executeQuery();
			rs.next();
			System.out.println(" INSERT INTO Gruppo VALUES ("+ rs.getInt("generate_new_gruppo_id")+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); ");
			nuovoGruppo.setId(rs.getInt("generate_new_gruppo_id"));
			PreparedStatement inserisciGruppo = connessione.prepareStatement(
					" INSERT INTO Gruppo VALUES ("+ rs.getInt("generate_new_gruppo_id")+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); "
					);
			inserisciGruppo.executeUpdate();
			for (Contatto contatto : nuovoGruppo.getContatti())
			{
				System.out.println(" INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				PreparedStatement aggiungiContatto = connessione.prepareStatement(
						" INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				aggiungiContatto.executeUpdate();
			}
			connessione.commit();
		} catch (Exception e) {
			// TODO: handle exception
			connessione.rollback();
			throw e;
		}
		finally {
			connessione.close();
		}
	}
	
	public void deleteGruppo(int codiceGruppo, Connection connessione) throws SQLException
	{
		System.out.println(" DELETE FROM Gruppo WHERE Gruppo_ID = " + codiceGruppo + "; ");
		try {
				PreparedStatement cancellaGruppo = connessione.prepareStatement(
						" DELETE FROM Gruppo WHERE Gruppo_ID = " + codiceGruppo + "; ");
				cancellaGruppo.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
				throw e;
		}
	}
	
	public void changeGruppo (String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws SQLException
	{
		try {
			connessione.setAutoCommit(false);
			// eliminazione del gruppo corrente
			System.out.println(" DELETE FROM Gruppo WHERE Gruppo_ID = " + nuovoGruppo.getId() + "; ");
			PreparedStatement cancellaGruppo = connessione.prepareStatement(
					" DELETE FROM Gruppo WHERE Gruppo_ID = " + nuovoGruppo.getId() + "; ");
			cancellaGruppo.executeUpdate();
			
			System.out.println(" INSERT INTO Gruppo VALUES ("+ nuovoGruppo.getId()+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); ");
			PreparedStatement inserisciGruppo = connessione.prepareStatement(
					" INSERT INTO Gruppo VALUES ("+ nuovoGruppo.getId()+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); "
					);
			inserisciGruppo.executeUpdate();
			for (Contatto contatto : nuovoGruppo.getContatti())
			{
				System.out.println(" INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				PreparedStatement aggiungiContatto = connessione.prepareStatement(
						" INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				aggiungiContatto.executeUpdate();
			}
			connessione.commit();
		} catch (Exception e) {
			// TODO: handle exception
			connessione.rollback();
			throw e;
		}
		finally {
			connessione.close();
		}
	}
}
