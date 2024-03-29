package implementazionepostgresdao;

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

/** Gestisce l'oggetto Rubrica e la sua connessione al DB. */
public class RubricaImplementazionePostgresDAO implements RubricaDAO{
	
	/** Connessione al DB destinata a gestire una transazione composta da pi� operazioni SQL*/
	private Connection connection;
	
	/**
	 *  Costruttore di un oggetto RubricaImplementazionePostgresDAO. Per l'utilizzo � indispensabile 
	 *  aprire e chiudere una connessione.
	 */
	public RubricaImplementazionePostgresDAO() {
	}
	
	/**
	 * Instaura la connessione con il DB.
	 *
	 * @return connection
	 */
	public Connection apriConnessione()
	{
		try {
			connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Ritorna la connessione.
	 *
	 * @return connessione
	 */
	public Connection getConnessione()
	{
		return connection;
	}
	
	/**
	 * Carica i contatti della rubrica nel DB assieme a tutte le informazioni che possiedono.
	 *
	 * @param nomeRubrica il nome dell'utente della rubrica
	 * @param contatti ArrayList di contatti da caricare
	 */
	@Override
	public void loadContatti(String nomeRubrica, ArrayList<Contatto> contatti) {
		PreparedStatement recuperaContatti;
		PreparedStatement recuperaIndirizzi;
		PreparedStatement recuperaNumTelefono;
		PreparedStatement recuperaEmail;
		PreparedStatement recuperaAccount;
		try {
			System.out.println("SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Contatto_ID; ");
			// aggiunge le informazioni sul contatto
			recuperaContatti = connection.prepareStatement(
				"SELECT * FROM Contatto WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Contatto_ID;"
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
						nuovoContatto.addIndirizzo(rsi.getString("via"), rsi.getString("citt�"), rsi.getString("nazione"), rsi.getString("cap"), tipoIndirizzo.Principale);
					}
					else 
					{
						nuovoContatto.addIndirizzo(rsi.getString("via"), rsi.getString("citt�"), rsi.getString("nazione"), rsi.getString("cap"), tipoIndirizzo.Secondario);
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
					System.out.println("SELECT * FROM Associa, Email, Account "
							         + "WHERE email_id = email_fk AND fornitore = fornitoreAccount AND account.indirizzoemail = indirizzoemailaccount AND email_id = "+rse.getInt("email_id")+ "; ");
					recuperaAccount = connection.prepareStatement(
							"SELECT * FROM Associa, Email, Account "
					      + "WHERE email_id = email_fk AND fornitore = fornitoreAccount AND account.indirizzoemail = indirizzoemailaccount AND email_id = "+rse.getInt("email_id")+ "; "
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
			e.printStackTrace();
		}
	}
	
	/**
	 * Carica i gruppi della rubrica nel DB.
	 *
	 * @param nomeRubrica nome dell'utente della rubrica
	 * @param contatti ArrayList di contatti da inserire nei rispettivi gruppi
	 * @param gruppi ArrayList di gruppi da caricare
	 */
	@Override
	public void loadGruppi(String nomeRubrica, ArrayList<Contatto> contatti, ArrayList<Gruppo> gruppi) {
		System.out.println("SELECT * FROM Gruppo WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Gruppo_ID;");
		PreparedStatement recuperaGruppi;
		try {
			// aggiunge le informazioni sul contatto
			recuperaGruppi = connection.prepareStatement(
				"SELECT * FROM Gruppo WHERE rubrica_fk = "+"\'"+nomeRubrica+"\' ORDER BY Gruppo_ID;"
				);
			ResultSet rsg = recuperaGruppi.executeQuery();
			while(rsg.next())
			{
				Gruppo nuovoGruppo = new Gruppo(rsg.getString("nome"), rsg.getInt("gruppo_id"));
				PreparedStatement recuperaContattiGruppo;
				System.out.println("SELECT * FROM Composizione WHERE gruppo_fk = " + rsg.getInt("gruppo_id") + " ORDER BY contatto_fk;");
				recuperaContattiGruppo = connection.prepareStatement(
						"SELECT * FROM Composizione WHERE gruppo_fk = " + rsg.getInt("gruppo_id") + " ORDER BY contatto_fk;"
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
				gruppi.add(nuovoGruppo);
			}
			rsg.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Genera un nuovo valido idenfiticato (ID) per un contatto.
	 *
	 * @param connessione connessione al DB
	 * @return ID da associare a un nuovo contatto
	 * @throws SQLException SQL exception
	 */
	@Override
	public int generaContattoID(Connection connessione) throws SQLException
	{
		ResultSet rs;
		int id = -1;
		
		try
		{
			System.out.println("SET CONSTRAINTS ALL DEFERRED;");
			PreparedStatement deferTrigger = connessione.prepareStatement(
				"SET CONSTRAINTS ALL DEFERRED;");
			deferTrigger.execute();
			System.out.println("SELECT generate_new_contatto_id(); ");
			PreparedStatement initEGeneraContattoID = connessione.prepareStatement
					(
						"SELECT generate_new_contatto_id(); "
					);
			rs = initEGeneraContattoID.executeQuery();
			//Si sposta il cursore in avanti poich� inizialmente punta a prima della row iniziale
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
			throw e;
		}
		
	}
	
	/**
	 * Inserisci nel DB tutte le informazioni di un contatto.
	 *
	 * @param nomeRubrica nome dell'utente della rubrica
	 * @param nome nome del contatto
	 * @param secondonome secondo nome del contatto
	 * @param cognome cognome del contatto
	 * @param numMobile numero mobile del contatto
	 * @param numFisso numero fisso del contatto
	 * @param via via dell'indirizzo del contatto
	 * @param citta citt� dell'indirizzo del contatto
	 * @param nazione nazione dell'indirizzo del contatto
	 * @param cap CAP dell'indirizzo del contatto
	 * @param id identificativo del contatto
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	/* @returns id del contatto creato*/
	@Override
	public void addInfoContatto(String nomeRubrica, String nome, String secondonome, String cognome,
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
			throw e;
		}
	}
	
	/**
	 * Cambia le informazioni di un contatto nel DB.
	 *
	 * @param nomeRubrica nome dell'utente della rubrica
	 * @param nome nome del contatto
	 * @param secondonome secondo nome del contatto
	 * @param cognome cognome del contatto
	 * @param numMobile numero mobile del contatto
	 * @param numFisso numero fisso del contatto
	 * @param via via dell'indirizzo del contatto
	 * @param citta citt� dell'indirizzo del contatto
	 * @param nazione nazione dell'indirizzo del contatto
	 * @param cap CAP dell'indirizzo del contatto
	 * @param vecchioContattoId vecchio identificativo del contatto
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void changeInfoContatto(String nomeRubrica, String nome, String secondonome, String cognome,
				               String numMobile, String numFisso, String via, String citta, String nazione, String cap,
				               int vecchioContattoId, Connection connessione) throws SQLException {
		
		try {
				System.out.println("SET CONSTRAINTS ALL DEFERRED;");
				PreparedStatement deferTrigger = connessione.prepareStatement(
					"SET CONSTRAINTS ALL DEFERRED;");
				deferTrigger.execute();
				// Memorizzo i gruppi in cui il contatto � presente
				System.out.println("SELECT * FROM Composizione WHERE Contatto_FK = " + vecchioContattoId + ";");
				PreparedStatement selezionaGruppi = connessione.prepareStatement(
						"SELECT * FROM Composizione WHERE Contatto_FK = " + vecchioContattoId + ";");
				ResultSet rsg = selezionaGruppi.executeQuery();
			
				System.out.println("DELETE FROM Contatto WHERE Contatto_ID = " + vecchioContattoId + ";");
				PreparedStatement cancellaContatto = connessione.prepareStatement(
						"DELETE FROM Contatto WHERE Contatto_ID = " + vecchioContattoId + ";");
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
					System.out.println("INSERT INTO Composizione VALUES (" + rsg.getInt("contatto_fk") + ", " + rsg.getInt("gruppo_fk") + ");");
					PreparedStatement reinserisciContattoGruppo = connessione.prepareStatement(
							"INSERT INTO Composizione VALUES (" + rsg.getInt("contatto_fk") + ", " + rsg.getInt("gruppo_fk") + ");");
					reinserisciContattoGruppo.executeUpdate();
				}
		}
		catch (SQLException e) 
		{
			connessione.rollback();
			throw e;
		}
	}
	
	/**
	 * Inserisci l'immagine del contatto nel database.
	 *
	 * @param pathImmagine percorso dell'immagine
	 * @param id identificativo del contatto a cui appartiene l'immagine
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void addImmagine(String pathImmagine, int id, Connection connessione) throws SQLException {
		System.out.println("UPDATE Contatto SET foto  = NULLIF('" + pathImmagine + "','') WHERE contatto_id = " + id + ";");
		try {
			PreparedStatement aggiungiImmagine = connessione.prepareStatement(
					"UPDATE Contatto SET foto  = NULLIF('" + pathImmagine + "','') WHERE contatto_id = " + id + ";"
					);
			
			aggiungiImmagine.executeUpdate();
		} catch (SQLException e) {
			connessione.rollback();
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Inserisci un indirizzo nel database.
	 *
	 * @param via via dell'indirizzo
	 * @param citt� citt� dell'indirizzo
	 * @param nazione nazione dell'indirizzo
	 * @param cap CAP dell'indirizzo
	 * @param descrizione descrizione dell'indirizzo
	 * @param id identificativo del contatto a cui appartiene l'indirizzo
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void addIndirizzo(String via, String citt�, String nazione, String cap, String descrizione, int id, Connection connessione) throws SQLException
	{
		System.out.println("INSERT INTO Indirizzo(via, citt�, nazione, cap, descrizione, contatto_fk)"
				                + " VALUES "+"(NULLIF('" + via + "',''), NULLIF('" + citt� + "', ''), NULLIF('" + nazione + "', ''),"
									        + "NULLIF('" + cap + "', ''), NULLIF('" + descrizione + "', ''),  " + id     + "); ");
		try {
			PreparedStatement aggiungiIndirizzo = connessione.prepareStatement(
					"INSERT INTO Indirizzo(via, citt�, nazione, cap, descrizione, contatto_fk)"
			                + " VALUES "+"(NULLIF('" + via + "',''), NULLIF('" + citt� + "', ''), NULLIF('" + nazione + "', ''),"
								        + "NULLIF('" + cap + "', ''), \'" + descrizione + "\',  " + id     + "); "
								        );
			aggiungiIndirizzo.executeUpdate();
		} catch (SQLException e) {
			connessione.rollback();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Inserisci un numero di telefono nel database.
	 *
	 * @param numero numero di telefono 
	 * @param descrizione descrizione del numero di telefono
	 * @param id_contatto identificativo del contatto a cui appartiene il numero di telefono
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void addTelefono(String numero, String descrizione, int id_contatto, Connection connessione) throws SQLException
	{
		System.out.println("INSERT INTO Telefono(numero, descrizione, contatto_fk)"
                                 + " VALUES (NULLIF('" + numero + "',''), NULLIF('" + descrizione + "',''), " + id_contatto + "); ");
		try {
			PreparedStatement aggiungiTelefono = connessione.prepareStatement(
					"INSERT INTO Telefono(numero, descrizione, contatto_fk)"
                            + " VALUES (NULLIF('" + numero + "',''), NULLIF('" + descrizione + "',''), " + id_contatto + "); "
                            );
			aggiungiTelefono.executeUpdate();
		} catch (SQLException e) {
			connessione.rollback();
			e.printStackTrace();
			throw e;
		}
		
	}

	/**
	 * Inserisci un email nel DB con le sue relative informazioni.
	 *
	 * @param indirizzoEmail indirizzo email
	 * @param descrizione descrizione dell'email
	 * @param id_contatto identificativo del contatto a cui appartiene l'email
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void addEmail(String indirizzoEmail, String descrizione, int id_contatto, Connection connessione) throws SQLException
	{
		System.out.println("INSERT INTO Email(indirizzoemail, descrizione, contatto_fk) "
	                     + "VALUES ((NULLIF('" + indirizzoEmail + "',''), NULLIF('" + descrizione + "',''), " + id_contatto + "); ");
		try {
				PreparedStatement aggiungiEmail = connessione.prepareStatement(
						"INSERT INTO Email(indirizzoemail, descrizione, contatto_fk) "
			                   + " VALUES (NULLIF('" + indirizzoEmail + "',''), NULLIF('" + descrizione + "',''), " + id_contatto + "); "
			                     );
				aggiungiEmail.executeUpdate();
		} catch (SQLException e) {
				connessione.rollback();
				e.printStackTrace();
				throw e;
		}
	}
	
	/**
	 * Recupera tutti gli account di un contatto, associati a tutte le sue email, dal DB.
	 *
	 * @param contatto contatto di cui recuperare l'account
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void loadAccountContatto(Contatto contatto, Connection connessione) throws SQLException
	{
		try {
			for (Email email : contatto.getEmail())
			{
				System.out.println("SELECT * FROM Associa, Email, Account "
						         + " WHERE email_id = email_fk AND fornitore = fornitoreAccount AND account.indirizzoemail = indirizzoemailaccount AND Email.indirizzoemail = '" + email.getStringaEmail() + "'; "
						);
				PreparedStatement recuperaAccount = connessione.prepareStatement(
						"SELECT * FROM Associa, Email, Account "
					  + " WHERE email_id = email_fk AND fornitore = fornitoreAccount AND account.indirizzoemail = indirizzoemailaccount AND Email.indirizzoemail = '" + email.getStringaEmail() + "'; "
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
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Elimina contatto dal DB con eliminazione in cascata di tutte le sue ulteriori informazioni.
	 *
	 * @param codiceContatto identificativo del contatto da eliminare
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void deleteContatto(int codiceContatto, Connection connessione) throws SQLException
	{
		try {
				System.out.println("DELETE FROM Contatto WHERE Contatto_ID = " + codiceContatto + "; ");
				PreparedStatement cancellaContatto = connessione.prepareStatement(
						"DELETE FROM Contatto WHERE Contatto_ID = " + codiceContatto + "; ");
				cancellaContatto.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
				throw e;
		}
	}
	
	/**
	 * Inserisci un gruppo e tutti i suoi contatti nel DB.
	 *
	 * @param nomeRubrica nome utente della rubrica
	 * @param nuovoGruppo nome gruppo
	 * @param connessione connessione al DB
	 * @throws Exception exception
	 */
	@Override
	public void addInfoGruppo(String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws Exception
	{
		ResultSet rs;
		try {
			connessione.setAutoCommit(false);
			System.out.println("SET CONSTRAINTS ALL DEFERRED;");
			PreparedStatement deferTrigger = connessione.prepareStatement(
				"SET CONSTRAINTS ALL DEFERRED;");
			deferTrigger.execute();
			System.out.println("SELECT generate_new_gruppo_id();");
			PreparedStatement generaId = connessione.prepareStatement(
					"SELECT generate_new_gruppo_id();"
					);
			rs = generaId.executeQuery();
			rs.next();
			System.out.println("INSERT INTO Gruppo VALUES ("+ rs.getInt("generate_new_gruppo_id")+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); ");
			nuovoGruppo.setId(rs.getInt("generate_new_gruppo_id"));
			PreparedStatement inserisciGruppo = connessione.prepareStatement(
					"INSERT INTO Gruppo VALUES ("+ rs.getInt("generate_new_gruppo_id")+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); "
					);
			inserisciGruppo.executeUpdate();
			for (Contatto contatto : nuovoGruppo.getContatti())
			{
				System.out.println("INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				PreparedStatement aggiungiContatto = connessione.prepareStatement(
						"INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				aggiungiContatto.executeUpdate();
			}
			connessione.commit();
			connessione.close();
		} catch (Exception e) {
			connessione.rollback();
			throw e;
		}
		finally {
			connessione.close();
		}
	}
	
	/**
	 * Elimina un gruppo dal DB.
	 *
	 * @param codiceGruppo identificativo del gruppo da eliminare
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void deleteGruppo(int codiceGruppo, Connection connessione) throws SQLException
	{
		System.out.println("DELETE FROM Gruppo WHERE Gruppo_ID = " + codiceGruppo + "; ");
		try {
				PreparedStatement cancellaGruppo = connessione.prepareStatement(
						"DELETE FROM Gruppo WHERE Gruppo_ID = " + codiceGruppo + "; ");
				cancellaGruppo.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
				throw e;
		}
	}
	
	/**
	 * Cambia le informazioni di un gruppo nel DB.
	 *
	 * @param nomeRubrica nome dell'utente della rubrica
	 * @param nuovoGruppo gruppo da aggiornare
	 * @param connessione connessione al DB
	 * @throws SQLException SQL exception
	 */
	@Override
	public void changeInfoGruppo (String nomeRubrica, Gruppo nuovoGruppo, Connection connessione) throws SQLException
	{
		try {
			connessione.setAutoCommit(false);
			System.out.println("SET CONSTRAINTS ALL DEFERRED;");
			PreparedStatement deferTrigger = connessione.prepareStatement(
				"SET CONSTRAINTS ALL DEFERRED;");
			deferTrigger.execute();
			// eliminazione del gruppo corrente
			System.out.println("DELETE FROM Gruppo WHERE Gruppo_ID = " + nuovoGruppo.getId() + ";");
			PreparedStatement cancellaGruppo = connessione.prepareStatement(
					"DELETE FROM Gruppo WHERE Gruppo_ID = " + nuovoGruppo.getId() + ";");
			cancellaGruppo.executeUpdate();
			
			System.out.println("INSERT INTO Gruppo VALUES ("+ nuovoGruppo.getId()+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); ");
			PreparedStatement inserisciGruppo = connessione.prepareStatement(
					"INSERT INTO Gruppo VALUES ("+ nuovoGruppo.getId()+", '" +nuovoGruppo.getNome()+"', '"+ nomeRubrica +"'); "
					);
			inserisciGruppo.executeUpdate();
			for (Contatto contatto : nuovoGruppo.getContatti())
			{
				System.out.println("INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				PreparedStatement aggiungiContatto = connessione.prepareStatement(
						"INSERT INTO Composizione VALUES (" +contatto.getId()+", "+ nuovoGruppo.getId() +"); ");
				aggiungiContatto.executeUpdate();
			}
			connessione.commit();
			connessione.close();
		} catch (Exception e) {
			connessione.rollback();
			throw e;
		}
		finally {
			connessione.close();
		}
	}
}
