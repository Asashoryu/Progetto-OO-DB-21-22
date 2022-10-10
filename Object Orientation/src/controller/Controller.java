package controller;

import model.Rubrica;
import model.Sistema;
import model.Contatto;
import model.Gruppo;
import model.Indirizzo.tipoIndirizzo;

import dao.RubricaDAO;
import dao.SistemaDAO;
import implementazionepostgresdao.RubricaImplementazionePostgresDAO;
import implementazionepostgresdao.SistemaImplementazionePostgresDAO;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;


/** Gestisce l'interazione dell'interfaccia GUI col model e col DB. */
public class Controller {
	
	/** Oggetto contenitore delle rubriche. */
	private Sistema sistema;
	
	/** Rubrica selezionata dalla GUI e accedibile a tempo costante dal controller, soggetta a operazioni di modifica,
	 *  cancellazione o selezione. */
	private Rubrica rubricaSelezionata;
	
	/** Contatto selezionato dalla GUI e accedibile a tempo costante dal controller, soggetto alle operazioni di modifica,
	 *  cancellazione o selezione. */
	private Contatto contattoSelezionato;
	
	/** Gruppo selezionato dalla GUI e accedibile a tempo costante dal controller, soggetto alle operazioni di modifica,
	 *  cancellazione o selezione. */
	private Gruppo gruppoSelezionato;
	
	/** Oggetto connessione che consente di conservare il collegamento col DB in modo da gestire una transazione composta
	 * da più query atomiche. */
	private Connection connTransazione;
	
	/** Costruttore del Controller. Istanzia il {@link Sistema} e ne carica le rubriche all'avvio della GUI */
	public Controller() 
	{
		sistema = new Sistema();
		loadRubriche();
	}
	
	/**
	 * Carica le rubriche dal database e le inserisce in memoria.
	 */
	public void loadRubriche() 
	{
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		sistema.setRubriche(sistemaPosgr.loadRubriche());
	}
	
	/**
	 * Ritorna un array di nomi delle rubriche cui si riferiscono
	 *
	 * @return Array dei nomi delle rubriche
	 */
	public String[] getNomiRubriche()
	{
		String[] nomiRubriche = new String[sistema.getRubriche().size()];
		for(Rubrica r: sistema.getRubriche()) {
			nomiRubriche[sistema.getRubriche().indexOf(r)]=r.getNome();
		}
		return nomiRubriche;
	}
	
	/**
	 * Ritorna le rubriche presenti in memoria.
	 * 
	 * @return ArrayList delle rubriche caricate in memoria.
	 * 
	 */
	public ArrayList<Rubrica> getRubriche()
	{
		return sistema.getRubriche();
	}
	
	/**
	 * Fissa localmente la Rubrica selezionata.
	 *
	 * @param indice Indice della Rubrica nell'ArrayList delle Rubriche nel {@link Sistema}
	 * 
	 * @see rubricaSelezionata
	 */
	public void setRubricaSelezionata(int indice) 
	{
		rubricaSelezionata = sistema.getRubriche().get(indice);
	}
	
	/**
	 * Restituisce la rubrica selezionata.
	 *
	 * @return Rubrica selezionata
	 * 
	 * @see rubricaSelezionata
	 */
	public Rubrica getRubricaSelezionata() 
	{
		return rubricaSelezionata;
	}
	
	/**
	 * Modifica il nome della rubrica selezionata.
	 *
	 * @param nuovoNome Nuovo nome della rubrica
	 * 
	 * @throws SQLException SQL exception
	 */
	public void updateRubrica(String nuovoNome) throws SQLException 
	{
		
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			//update nel DB
			sistemaPosgr.updateRubrica(rubricaSelezionata.getNome(), nuovoNome);
			//update in memoria
			rubricaSelezionata.setNome(nuovoNome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	/**
	 * Crea e aggiunge una rubrica nel DB e in memoria.
	 *
	 * @param nomeRubrica Nome della rubrica da aggiungere
	 * 
	 * @throws SQLException the SQL exception
	 */
	public void addRubrica(String nomeRubrica) throws SQLException 
	{
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try {
			//add nel DB
			sistemaPosgr.addRubrica(nomeRubrica);
			//add in memoria
			Rubrica rubrica = new Rubrica(nomeRubrica);
			getRubriche().add(rubrica);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	/**
	 * Cancella la rubrica selezionata dal DB e dalla memoria.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void deleteRubricaSelezionata() throws SQLException 
	{
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		try
		{
			//remove dal DB, per nome
			sistemaPosgr.deleteRubrica(rubricaSelezionata.getNome());
			//remove dalla memoria, per rubrica selezionata nella UI
			getRubriche().remove(rubricaSelezionata);
		}
		catch (SQLException e) 
		{
			throw e;
		}
	}
	
	/**
	 * Fissa localmente il Contatto selezionato.
	 *
	 * @param indice Indice del Contatto nell'ArrayList della Rubrica selezionata o del Gruppo, se è stato selezionato
	 * 
	 * @see rubricaSelezionata
	 * @see gruppoSelezionato
	 * @see contattoSelezionato
	 */
	public void setContattoSelezionato(int indice) 
	{
		// Se non è selezionato un gruppo allora il contatto viene selezionato secondo l'indicizzazione dell'intera rubrica
		// altrimenti è selezionato secondo l'ordine dei contatti nel gruppo selezionato
		if (gruppoSelezionato == null)
		{
			contattoSelezionato = rubricaSelezionata.getContatti().get(indice);
		}
		else {
			contattoSelezionato = gruppoSelezionato.getContatti().get(indice);
		}
	}
	
	/**
	 * Restituisce il contatto selezionato.
	 *
	 * @return Contatto selezionato
	 * 
	 * @see contattoSelezionato
	 */
	public Contatto getContattoSelezionato() 
	{
		return contattoSelezionato;
	}
	
	/**
	 * Fissa localmente il Gruppo selezionato.
	 *
	 * @param indice Indice del Gruppo nell'ArrayList della Rubrica selezionata
	 * 
	 * @see gruppoSelezionato
	 * @see rubricaSelezionata
	 */
	public void setGruppoSelezionato(int indice) 
	{
		gruppoSelezionato = rubricaSelezionata.getGruppi().get(indice);
	}
	
	/**
	 * Mette a null il Gruppo selezionato
	 */
	public void setNullGruppoSelezionato() 
	{
		gruppoSelezionato = null;
	}
	
	/**
	 * Ritorna il Gruppo selezionato.
	 *
	 * @return Gruppo selezionato
	 * 
	 * @see gruppoSelezionato
	 */
	public Gruppo getGruppoSelezionato() 
	{
		return gruppoSelezionato;
	}
	
	/**
	 * Carica i Contatti dal DB in memoria.
	 *
	 * @throws Exception the exception
	 */
	public void loadContatti() throws Exception 
	{
		ArrayList<Contatto> contatti;
		// Se la rubrica non è inizializzata
		// allora i contatti sono caricati dal DB
		if(rubricaSelezionata.getContatti() == null) 
		{
			contatti = new ArrayList<>();
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			rubricaPosgr.apriConnessione();
			rubricaPosgr.loadContatti(rubricaSelezionata.getNome(), contatti);
			rubricaSelezionata.setContatti(contatti);
		}
	}
	
	/**
	 * Carica i Gruppi dal DB in memoria
	 */
	public void loadGruppi()
	{
		ArrayList<Gruppo> gruppi;
		// Se la rubrica non è inizializzata
		// allora i contatti sono caricati dal DB
		if(rubricaSelezionata.getGruppi() == null)
		{
			gruppi = new ArrayList<>();
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			rubricaPosgr.apriConnessione();
			rubricaPosgr.loadGruppi(rubricaSelezionata.getNome(), rubricaSelezionata.getContatti(), gruppi);
			// TODO : associazione dei contatti ai rispettivi gruppi
			
			rubricaSelezionata.setGruppi(gruppi);
		}
	}
	
	/**
	 * Restituisce i nomi di tutti i contatti della rubrica selezionata.
	 * 
	 * @return	Array di nomi delle Rubriche
	 */
	public String[] getNomiContattiRubrica()
	{
		String[] nomiContattiRubriche = new String[rubricaSelezionata.getContatti().size()];
		for(Contatto c : rubricaSelezionata.getContatti()) 
		{
			// sono riunite tutte le parti di un nome di un contatto in una stringa
			String nomeCompleto;
			if(c.getSecondoNome() != null && c.getSecondoNome().isBlank() == false) 
			{
				nomeCompleto = c.getNome()+" "+ c.getSecondoNome()+" "+ c.getCognome();
			} 
			else 
			{
				nomeCompleto = c.getNome() +" "+ c.getCognome();
			}
			nomiContattiRubriche[rubricaSelezionata.getContatti().indexOf(c)] = nomeCompleto;
		}
		return nomiContattiRubriche;
	}
	
	/**
	 * Restituisce i nomi di tutti i gruppi della rubrica selezionata.
	 *
	 * @return Array di nomi dei Gruppi
	 */
	public String[] getNomiGruppiRubrica()
	{
		String[] nomiGruppiRubrica = new String[rubricaSelezionata.getGruppi().size()];
		int indice;
		for(Gruppo g : rubricaSelezionata.getGruppi())
		{
			// sono riunite tutte le parti di un nome di un contatto in una stringa
			indice = rubricaSelezionata.getGruppi().indexOf(g);
			nomiGruppiRubrica[indice] = rubricaSelezionata.getGruppi().get(indice).getNome();
		}
		return nomiGruppiRubrica;
	}
	
	/**
	 * Ritorna i nomi di tutti i Contatti del Gruppo selezionato.
	 *
	 * @return Nomi Contatti Gruppo selezionato
	 */
	public String[] getNomiContattiGruppoSelezionato()
	{
		String[] nomiContattiGruppo = new String[gruppoSelezionato.getContatti().size()];
		for(Contatto c : gruppoSelezionato.getContatti())
		{
			// sono riunite tutte le parti di un nome di un contatto in una stringa
			String nomeCompleto;
			if (c.getSecondoNome() != null && c.getSecondoNome().isBlank() == false)
			{
				nomeCompleto = c.getNome()+" "+ c.getSecondoNome()+" "+ c.getCognome();
			}
			else 
			{
				nomeCompleto = c.getNome() +" "+ c.getCognome();
			}
			nomiContattiGruppo[gruppoSelezionato.getContatti().indexOf(c)] = nomeCompleto;
		}
		return nomiContattiGruppo;
	}
	
	/**
	 * Inizializza e avvia la transazione che inserisce il Contatto con tutte le sue informazioni
	 * (compresi numeri di telefono, indirizzi ed email) nel DB e in memoria.
	 *
	 * @return Id del contatto aggiunto nel DB
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see Contatto
	 */
	private int inizializzaInserimento() throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		connTransazione = rubricaPosgr.apriConnessione();
		connTransazione.setAutoCommit(false);
		return rubricaPosgr.generaContattoID(connTransazione);
	}
	
	/**
	 * Finalizza e chiude la transazione che inserisce il Contatto con tutte le sue informazioni.
	 *
	 * @param contatto Contatto da aggiungere alla rubrica Selezionata
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see Contatto
	 */
	private void finalizzaInserimento(Contatto contatto) throws SQLException
	{
		connTransazione.commit();
		connTransazione.close();
		System.out.println("COMMIT RIUSCITO");
		rubricaSelezionata.getContatti().add(contatto);
		connTransazione = null;
	}
	
	/**
	  * Inizializza e avvia la transazione che modifica il Contatto con tutte le sue informazioni
	 * (compresi numeri di telefono, indirizzi ed email) nel DB e in memoria.
	 *
	 * @throws SQLException the SQL exception
	 * 
	 * @see Contatto
	 */
	private void inizializzaModifica() throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		connTransazione = rubricaPosgr.apriConnessione();
		connTransazione.setAutoCommit(false);
	}
	
	/**
	 * Finalizza e chiude la transazione che modifica il Contatto con tutte le sue informazioni.
	 *
	 * @param nuovoContatto Contatto modificato da sostituire nella Rubrica selezionata col precedente
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see Contatto
	 */
	private void finalizzaModifica(Contatto nuovoContatto) throws SQLException
	{
		int indiceVecchioRubrica;
		int indiceVecchioGruppo;
		Boolean modifica;
		connTransazione.commit();
		connTransazione.close();
		System.out.println("COMMIT RIUSCITO");
		// sostituisco il nuovo contatto col vecchio in rubrica
		indiceVecchioRubrica = rubricaSelezionata.getContatti().indexOf(contattoSelezionato);
		rubricaSelezionata.getContatti().remove(contattoSelezionato);
		rubricaSelezionata.getContatti().add(indiceVecchioRubrica, nuovoContatto);
		// sostituisco il nuovo contatto col vecchio nei rispettivi gruppi
		for (Gruppo gruppo : rubricaSelezionata.getGruppi())
		{
			modifica = false;
			for (Contatto contatto : gruppo.getContatti())
			{
				if (contatto == contattoSelezionato)
				{
					modifica = true;
				}
			}
			if (modifica == true)
			{
				indiceVecchioGruppo = gruppo.getContatti().indexOf(contattoSelezionato);
				gruppo.getContatti().remove(contattoSelezionato);
				gruppo.getContatti().add(indiceVecchioGruppo, nuovoContatto);
			}
		}

		connTransazione = null;
	}
	
	/**
	 * Aggiunge le informazioni fondamentali del Contatto (informazioni sul nome, indirizzo principale e
	 * un numero mobile e uno fisso).
	 *
	 * @param nome Nome
	 * @param secondonome Secondo nome 
	 * @param cognome Cognome
	 * @param numMobile Numero mobile 
	 * @param numFisso Numero fisso
	 * @param via Via
	 * @param citta Città
	 * @param nazione Nazione
	 * @param cap Cap
	 * @param id Id del Contatto nel DB
	 * 
	 * @return Contatto aggiunto
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see Contatto
	 * @see model.Indirizzo
	 * @see model.Telefono
	 */
	private Contatto addInfoContatto(String nome, String secondonome, String cognome,
                            		 String numMobile, String numFisso, String via, String citta, String nazione, String cap,
                            		 int id) throws SQLException
	 {
		Contatto contatto; 
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO(); 
		try
		{
			rubricaPosgr.addInfoContatto(rubricaSelezionata.getNome(), nome, secondonome, cognome, numMobile, numFisso, via, citta,
					                      nazione, cap, id, connTransazione);
			contatto = new Contatto(nome, secondonome, cognome, numMobile, numFisso, via, citta, nazione, cap, id);
		}
		catch (SQLException e) 
		{
			contatto = null;
			throw e;
		}
		return contatto;
	}
	
	/**
	 * Riaggiunge le informazioni fondamentali del Contatto (informazioni sul nome, indirizzo principale e
	 * un numero mobile e uno fisso).
	 *
	 * @param nome Nome
	 * @param secondonome Secondo nome 
	 * @param cognome Cognome
	 * @param numMobile Numero mobile 
	 * @param numFisso Numero fisso
	 * @param via Via
	 * @param citta Città
	 * @param nazione Nazione
	 * @param cap Cap
	 * 
	 * @return Contatto aggiunto
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see Contatto
	 * @see model.Indirizzo
	 * @see model.Telefono
	 */
	private Contatto changeInfoContatto(String nome, String secondonome, String cognome,
            					   String numMobile, String numFisso, String via, String citta, String nazione, String cap) throws SQLException
	{
		Contatto contatto;
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try
		{
			rubricaPosgr.changeInfoContatto(rubricaSelezionata.getNome(), nome, secondonome, cognome, numMobile, numFisso, via, citta,
				                        nazione, cap, contattoSelezionato.getId(), connTransazione);
			contatto = new Contatto(nome, secondonome, cognome, numMobile, numFisso, via, citta, nazione, cap, contattoSelezionato.getId());
			
		}
		catch (SQLException e) 
		{
			contatto = null;
			throw e;
		}
		return contatto;
	}
	
	/**
	 * Aggiunge un'immagine nel DB e in memoria.
	 *
	 * @param contatto Contatto a cui aggiungere l'immagine
	 * @param pathImmagine Percorso sul disco da cui riperire l'immagine
	 * 
	 * @throws SQLException the SQL exception
	 */
	private void addImmagine(Contatto contatto, String pathImmagine) throws SQLException {
		// TODO Auto-generated method stub
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try
		{
			rubricaPosgr.addImmagine(pathImmagine, contatto.getId(), connTransazione);
			contatto.setPathImmagine(pathImmagine);
		}
		catch (SQLException e)
		{
			contatto = null;
			throw e;
		}
	}
	
	/**
	 * Aggiunge un Indirizzo secondario nel DB e in memoria.
	 *
	 * @param contatto Contatto a cui aggiungere l'indirizzo secondario
	 * @param via Via
	 * @param città Città
	 * @param nazione Nazione
	 * @param cap Cap
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see model.Indirizzo
	 */
	private void addIndirizzoSec(Contatto contatto, String via, String città, String nazione, String cap) throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try 
		{
			rubricaPosgr.addIndirizzo(via, città, nazione, cap, "Secondario", contatto.getId(), connTransazione);
			contatto.addIndirizzo(via, città, nazione, cap, tipoIndirizzo.Secondario);
		}
		catch (SQLException e)
		{
			contatto = null;
			throw e;
		}
	}
	
	/**
	 * Aggiunge un Telefono secondario nel DB e in memoria.
	 *
	 * @param contatto Contatto a cui aggiungere il telefono
	 * @param numero Numero
	 * @param descrizione Tipo di numero (es. Fisso, Mobile, ecc.)
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see model.Telefono
	 */
	private void addTelefonoSec(Contatto contatto, String numero, String descrizione) throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try
		{
			rubricaPosgr.addTelefono(numero, descrizione, contatto.getId(), connTransazione);
			contatto.addTelefono(numero, descrizione);
		}
		catch (SQLException e)
		{
			contatto = null;
			throw e;
		}
	}
	
	/**
	 * Aggiunge una Email secondaria nel DB e in memoria.
	 *
	 * @param contatto Contatto a cui aggiungere l'Email
	 * @param indirizzoEmail the indirizzo email
	 * @param descrizione Tipo di Email (es. Personale, Ufficio, ecc.)
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see model.Email
	 */
	private void addEmailSec(Contatto contatto, String indirizzoEmail, String descrizione) throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try
		{
			rubricaPosgr.addEmail(indirizzoEmail, descrizione, contatto.getId(), connTransazione);
			contatto.addEmail(indirizzoEmail, descrizione);
		}
		catch (SQLException e)
		{
			contatto = null;
			throw e;
		}
	}
	
	/**
	 * Carica dal DB in memoria tutti gli Account associati a un Contatto
	 *
	 * @param contatto Contatto di cui caricare gli account
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @see model.Account
	 */
	public void loadAccountContatto(Contatto contatto) throws SQLException {
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try
		{
			rubricaPosgr.loadAccountContatto(contatto, connTransazione);
		}
		catch (SQLException e)
		{
			throw e;
		}
	}
	
	/**
	 * Cancella dal DB e dalla memoria il contatto selezionato.
	 *
	 * @throws SQLException the SQL exception
	 * 
	 * @see contattoSelezionato
	 */
	public void deleteContattoSelezionato() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		Boolean modifica;
		try {
			// cancellazione dal DB
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			conn = rubricaPosgr.apriConnessione();
			rubricaPosgr.deleteContatto(contattoSelezionato.getId(), conn);
			conn = null;
			// cancellazione dalla memoria
			rubricaSelezionata.getContatti().remove(contattoSelezionato);
			for (Gruppo gruppo : rubricaSelezionata.getGruppi())
			{
				modifica = false;
				for (Contatto contatto : gruppo.getContatti())
				{
					if (contatto == contattoSelezionato)
					{
						modifica = true;
					}
				}
				if (modifica == true)
				{
					gruppo.getContatti().remove(contattoSelezionato);
				}
				// se il gruppo è vuoto viene eliminato
			}
			//eliminazione gruppi senza contatti
			for (int i = 0; i < rubricaSelezionata.getGruppi().size(); i++)
			{
				if(rubricaSelezionata.getGruppi().get(i).getContatti().size() == 0)
				{
					rubricaSelezionata.getGruppi().remove(i);
					i--;
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	/**
	 * Aggiunge il Gruppo con i Contatti nel DB e in memoria.
	 *
	 * @param nuovoGruppo Gruppo da aggiungere
	 * 
	 * @throws Exception the exception
	 * 
	 * @see Gruppo
	 */
	private void addInfoGruppo(Gruppo nuovoGruppo) throws Exception {
		Connection conn;
		try {
			// cancellazione dal DB
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			conn = rubricaPosgr.apriConnessione();
			rubricaPosgr.addInfoGruppo(rubricaSelezionata.getNome(), nuovoGruppo, conn);
			conn = null;
			rubricaSelezionata.getGruppi().add(nuovoGruppo);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	/**
	 * Elimina il Gruppo selezionato dalla memoria e dal DB.
	 *
	 * @throws SQLException the SQL exception
	 * 
	 * @see gruppoSelezionato
	 */
	public void deleteGruppoSelezionato() throws SQLException
	{
		Connection conn;
		try {
			// cancellazione dal DB
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			conn = rubricaPosgr.apriConnessione();
			rubricaPosgr.deleteGruppo(gruppoSelezionato.getId(), conn);
			conn = null;
			// cancellazione dalla memoria
			rubricaSelezionata.getGruppi().remove(gruppoSelezionato);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	/**
	 * Riaggiunge il Gruppo con i Contatti nel DB e in memoria.
	 *
	 * @param nuovoGruppo Gruppo da aggiungere
	 * 
	 * @throws Exception the exception
	 * 
	 * @see Gruppo
	 */
	private void changeInfoGruppo(Gruppo nuovoGruppo) throws Exception 
	{
		Connection conn;
		int indiceVecchio;
		try {
			// cancellazione dal DB
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			conn = rubricaPosgr.apriConnessione();
			nuovoGruppo.setId(gruppoSelezionato.getId());
			rubricaPosgr.changeInfoGruppo(rubricaSelezionata.getNome(), nuovoGruppo, conn);
			conn = null;
			// TODO : modifica in memoria
			indiceVecchio = rubricaSelezionata.getGruppi().indexOf(gruppoSelezionato);
			rubricaSelezionata.getGruppi().remove(gruppoSelezionato);
			rubricaSelezionata.getGruppi().add(indiceVecchio, nuovoGruppo);
			setGruppoSelezionato(indiceVecchio);
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Svolge una ricerca per sottostringa confrontando i nomi dei Contatti della Rubrica selezionata, dopodiché crea un
	 * Gruppo temporaneo costituito dai Contatti trovati e lo imposta come Gruppo selezionato
	 *
	 * @param text Stringa rispetto a cui avviene la ricerca
	 * 
	 * @see Gruppo
	 * @see Contatto
	 */
	public void cercaPerNome(String text) 
	{
		Gruppo gruppoRicerca = new Gruppo();
		gruppoRicerca.setContatti(rubricaSelezionata.cercaPerNome(text));
		gruppoSelezionato = gruppoRicerca;
	}
	
	/**
	 * Svolge una ricerca per sottostringa confrontando le Email dei Contatti della Rubrica selezionata, dopodiché crea un
	 * Gruppo temporaneo costituito dai Contatti trovati e lo imposta come Gruppo selezionato
	 *
	 * @param text Stringa rispetto a cui avviene la ricerca
	 * 
	 * @see Gruppo
	 * @see Contatto
	 */
	public void cercaPerEmail(String text) {
		Gruppo gruppoRicerca = new Gruppo();
		gruppoRicerca.setContatti(rubricaSelezionata.cercaPerEmail(text));
		gruppoSelezionato = gruppoRicerca;
	}

	/**
	 * Svolge una ricerca per sottostringa confrontando i nickname degli Account dei Contatti della Rubrica selezionata,
	 * dopodiché crea un Gruppo temporaneo costituito dai Contatti trovati e lo imposta come Gruppo selezionato
	 *
	 * @param text Stringa rispetto a cui avviene la ricerca
	 * 
	 * @see Gruppo
	 * @see Contatto
	 */
	public void cercaPerAccount(String text) {
		Gruppo gruppoRicerca = new Gruppo();
		gruppoRicerca.setContatti(rubricaSelezionata.cercaPerAccount(text));
		gruppoSelezionato = gruppoRicerca;
	}

	/**
	 * Svolge una ricerca per sottostringa confrontando i numeri dei Contatti della Rubrica selezionata, dopodiché crea un
	 * Gruppo temporaneo costituito dai Contatti trovati e lo imposta come Gruppo selezionato
	 *
	 * @param text Stringa rispetto a cui avviene la ricerca
	 * 
	 * @see Gruppo
	 * @see Contatto
	 */
	public void cercaPerNumero(String text) {
		Gruppo gruppoRicerca = new Gruppo();
		gruppoRicerca.setContatti(rubricaSelezionata.cercaPerNumero(text));
		gruppoSelezionato = gruppoRicerca;
	}
	
	/**
	 * Controlla se la descrizione dell'Indirizzo indica che è principale.
	 *
	 * @param tipo True se l'indirizzo è principale
	 * @return the boolean
	 * 
	 * @see model.Indirizzo
	 */
	public Boolean checkSeTipoIndirizzoPrincipale(tipoIndirizzo tipo)
	{
		return tipo == tipoIndirizzo.Principale;
	}

	/**
	 * Svolge un parsing degli opportuni Component della GUI ed estrae le informazioni aggiunte dinamicamente.
	 * Dopodiché aggiunge il Contatto con tutte sue informazioni principali e secondarie inserite
	 * nel DB e in memoria
	 *
	 * @param textFieldNome Component da cui estrarre il nome
	 * @param textFieldSecondoNome Component da cui estrarre il secondo nome
	 * @param textFieldCognome Component da cui estrarre il cognome
	 * @param textFieldVia Component da cui estrarre la via
	 * @param textFieldCittà Component da cui estrarre la città
	 * @param textFieldNazione Component da cui estrarre la nazione
	 * @param textFieldCap Component da cui estrarre il CAP
	 * @param textFieldNumMobile Component da cui estrarre il numero mobile
	 * @param textFieldNumFisso Component da cui estrarre il numero fisso
	 * @param percorsoImmagine Percorso sul disco da cui reperire l'immagine del Contatto
	 * @param pannelloScrollIndirizziSec Component da cui estrarre gli indirizzi secondari
	 * @param pannelloScrolNumTel Component da cui estrarre i telefoni secondari
	 * @param pannelloScrollMail Component da cui estrarre le email
	 * 
	 * @throws Exception the exception
	 */
	public void addContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
							JTextField textFieldCittà, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
							JTextField textFieldNumFisso, String percorsoImmagine,
							JPanel pannelloScrollIndirizziSec, JPanel pannelloScrolNumTel, JPanel pannelloScrollMail) throws Exception
	{
		int id = -1;
		// INSERIMENTI PRINCIPALI
		// contatto creato
		Contatto nuovoContatto;
		// inserimento in database
		id = inizializzaInserimento();
		nuovoContatto = addInfoContatto(textFieldNome.getText(),      textFieldSecondoNome.getText(), textFieldCognome.getText(),
							   			textFieldNumMobile.getText(), textFieldNumFisso.getText(),    textFieldVia.getText(),
							   		    textFieldCittà.getText(),     textFieldNazione.getText(),     textFieldCap.getText(),
							   		    id);
		// INSERIMENTI SECONDARI
		// inserimento immagine
		if (percorsoImmagine != null)
		{
			// TODO: gestire inserimento immagine
			addImmagine(nuovoContatto, percorsoImmagine);
		}
		// Inserimento indirizzi secondari
		for (Component compIndirizzoSec : pannelloScrollIndirizziSec.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(compIndirizzoSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				String viaSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[1]).getText();
				String cittàSec   = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[3]).getText();
				String nazioneSec = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[5]).getText();
				String capSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[7]).getText();
				addIndirizzoSec(nuovoContatto, viaSec, cittàSec, nazioneSec, capSec);
			}
		}
		// Inserimento numeri secondari
		for (Component compNumeroSec : pannelloScrolNumTel.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(compNumeroSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				String descrizioneSec = ((JTextField)((JPanel) compNumeroSec).getComponents()[0]).getText();
				String numeroSec      = ((JTextField)((JPanel) compNumeroSec).getComponents()[1]).getText();
				addTelefonoSec(nuovoContatto, numeroSec, descrizioneSec);
			}
		}
		// Inserimento email secondarie
		for (Component compEmailSec : pannelloScrollMail.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(compEmailSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				String descrizioneSec = ((JTextField)((JPanel) compEmailSec).getComponents()[0]).getText();
				String emailSec       = ((JTextField)((JPanel) compEmailSec).getComponents()[1]).getText();
				addEmailSec(nuovoContatto, emailSec, descrizioneSec);
			}
		}
		// vengono associati gli account alle email
		loadAccountContatto(nuovoContatto);
		//commit delle informazioni in DB e inserimento del contatto i memoria
		finalizzaInserimento(nuovoContatto);
	}
	
	/**
	 * Svolge un parsing degli opportuni Component della GUI ed estrae le informazioni aggiunte dinamicamente.
	 * Dopodiché riaggiunge il Contatto con tutte le nuove informazioni principali e secondarie indicate
	 * nel DB e in memoria
	 *
	 * @param textFieldNome Component da cui estrarre il nome
	 * @param textFieldSecondoNome Component da cui estrarre il secondo nome
	 * @param textFieldCognome Component da cui estrarre il cognome
	 * @param textFieldVia Component da cui estrarre la via
	 * @param textFieldCittà Component da cui estrarre la città
	 * @param textFieldNazione Component da cui estrarre la nazione
	 * @param textFieldCap Component da cui estrarre il CAP
	 * @param textFieldNumMobile Component da cui estrarre il numero mobile
	 * @param textFieldNumFisso Component da cui estrarre il numero fisso
	 * @param percorsoImmagine Percorso sul disco da cui reperire l'immagine del Contatto
	 * @param pannelloScrollIndirizziSec Component da cui estrarre gli indirizzi secondari
	 * @param pannelloScrolNumTel Component da cui estrarre i telefoni secondari
	 * @param pannelloScrollMail Component da cui estrarre le email
	 * 
	 * @throws Exception the exception
	 */
	public void changeContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
							   JTextField textFieldCittà, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
							   JTextField textFieldNumFisso, String percorsoImmagine,
							   JPanel pannelloScrollIndirizziSec, JPanel pannelloScrolNumTel, JPanel pannelloScrollMail) throws Exception
	{
		// INSERIMENTI PRINCIPALI
		// contatto creato
		Contatto nuovoContatto;
		// inserimento in database
		inizializzaModifica();
		// ricreo il contatto con le nuove informazioni
		nuovoContatto = changeInfoContatto(textFieldNome.getText(),      textFieldSecondoNome.getText(), textFieldCognome.getText(),
										   textFieldNumMobile.getText(), textFieldNumFisso.getText(),    textFieldVia.getText(),
										   textFieldCittà.getText(),     textFieldNazione.getText(),     textFieldCap.getText());
		// INSERIMENTI SECONDARIdfdasfda
		// inserimento immagine
		if (percorsoImmagine != null)
		{
			// gestisce inserimento immagine
			addImmagine(nuovoContatto, percorsoImmagine);
		}
		// Inserimento indirizzi secondari
		for (Component compIndirizzoSec : pannelloScrollIndirizziSec.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(compIndirizzoSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				String viaSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[1]).getText();
				String cittàSec   = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[3]).getText();
				String nazioneSec = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[5]).getText();
				String capSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[7]).getText();
				addIndirizzoSec(nuovoContatto, viaSec, cittàSec, nazioneSec, capSec);
			}
		}
		// Inserimento numeri secondari
		for (Component compNumeroSec : pannelloScrolNumTel.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(compNumeroSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				String descrizioneSec = ((JTextField)((JPanel) compNumeroSec).getComponents()[0]).getText();
				String numeroSec      = ((JTextField)((JPanel) compNumeroSec).getComponents()[1]).getText();
				addTelefonoSec(nuovoContatto, numeroSec, descrizioneSec);
			}
		}
		// Inserimento email secondarie
		for (Component compEmailSec : pannelloScrollMail.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(compEmailSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				String descrizioneSec = ((JTextField)((JPanel) compEmailSec).getComponents()[0]).getText();
				String emailSec       = ((JTextField)((JPanel) compEmailSec).getComponents()[1]).getText();
				addEmailSec(nuovoContatto, emailSec, descrizioneSec);
			}
		}
		
		// vengono associati gli account alle email
		loadAccountContatto(nuovoContatto);
		//commit delle informazioni in DB e inserimento del contatto i memoria
		finalizzaModifica(nuovoContatto);
	}
	
	/**
	 * Svolge un parsing degli opportuni Component della GUI ed estrae le informazioni aggiunte dinamicamente.
	 * Dopodiché aggiunge il Gruppo con i Contatti indicati nel DB e in memoria
	 * 
	 * @param textFieldNome Component da cui estrarre il nome del Gruppo
	 * @param pannelloContatti Component da cui estrarre i Contatti aggiunti al Gruppo
	 * 
	 * @throws Exception the exception
	 */
	public void addGruppo(JTextField textFieldNome, JPanel pannelloContatti) throws Exception
	{
		ArrayList<Contatto> contatti = new ArrayList<>();
		int indice = 0;
		
		for (Component scrollComponent : pannelloContatti.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(scrollComponent instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
				// controller.addIndirizzoSec(nuovoContatto, viaSec, cittàSec, nazioneSec, capSec);
				if (checkbox.isSelected())
				{
					contatti.add(getRubricaSelezionata().getContatti().get(indice));
				}
			}
			indice++;
		}
		Gruppo nuovoGruppo = new Gruppo(textFieldNome.getText(), contatti);
		addInfoGruppo(nuovoGruppo);
	}
	
	/**
	 * Svolge un parsing degli opportuni Component della GUI ed estrae le informazioni aggiunte dinamicamente.
	 * Dopodiché riaggiunge il Gruppo con i Contatti indicati nel DB e in memoria
	 * 
	 * @param textFieldNome Component da cui estrarre il nome del Gruppo
	 * @param pannelloContatti Component da cui estrarre i Contatti aggiunti al Gruppo
	 * 
	 * @throws Exception the exception
	 */
	public void changeGruppo(JTextField textFieldNome, JPanel pannelloContatti) throws Exception
	{
		ArrayList<Contatto> contatti = new ArrayList<>();
		int indice = 0;
		
		for (Component scrollComponent : pannelloContatti.getComponents())
		{
			// se non è un button allora è il pannello con gli indirizzi
			if(scrollComponent instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
				// controller.addIndirizzoSec(nuovoContatto, viaSec, cittàSec, nazioneSec, capSec);
				if (checkbox.isSelected())
				{
					contatti.add(getRubricaSelezionata().getContatti().get(indice));
				}
			}
			indice++;
		}
		Gruppo nuovoGruppo = new Gruppo(textFieldNome.getText(), contatti);
		changeInfoGruppo(nuovoGruppo);
	}
	
	/**
	 * Restituisce un nuovo numero da chiamare nel caso in cui la chiamata al numero passato non ha avuto successo
	 * 
	 * @param numeroDaReindirizzare numero rispetto al quale svolgere il reindirizzamento
	 * 
	 * @return numero reindirizzato verso cui riprovare la chiamata
	 * @see Contatto
	 */
	public String reindirizza(String numeroDaReindirizzare) 
	{
		return getContattoSelezionato().reindirizza(numeroDaReindirizzare);
	}
}
	
