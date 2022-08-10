package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ContattoDAO;
import dao.RubricaDAO;
import dao.SistemaDAO;
import implementazionedao.ContattoImplementazionePostgresDAO;
import implementazionedao.RubricaImplementazionePostgresDAO;
import implementazionedao.SistemaImplementazionePostgresDAO;
import model.Rubrica;
import model.Sistema;
import model.Contatto;
import model.Gruppo;
import model.Indirizzo.tipoIndirizzo;

/** Gestisce l'interazione dell'interfaccia col model e col DB. */
public class Controller {
	
	/** Oggetto contenitore delle rubriche. */
	private Sistema sistema;
	
	/** Rubrica soggetta a operazioni di modifica, cancellazione o selezione. */
	private Rubrica rubricaSelezionata;
	
	/** Contatto soggetto alle operazioni di modifica, cancellazione o selezione. */
	private Contatto contattoSelezionato;
	
	/** Stringa di tutte le operazione che querry da eseguire atomicamente, in una transazione*/
	private Connection connTransazione;
	
	/**Costruttore del Controller. Carica le rubriche dal DB in memoria */
	public Controller() 
	{
		sistema = new Sistema();
		loadRubriche();
	}
	
	/**
	 * Questo metodo carica le rubriche dal database e le inserisce in memoria
	 */
	public void loadRubriche() 
	{
		SistemaDAO sistemaPosgr = new SistemaImplementazionePostgresDAO();
		sistema.setRubriche(sistemaPosgr.loadRubriche());
	}
	
	/**
	 * Metodo usato dalla ComboBox del main. Ritorna un array di nomi delle
	 * rubriche cui si riferiscono, enumerati nello stesso ordine degli oggetti,
	 * quindi l'indice di un nome consente di recuperare l'oggetto rubrica
	 * @return
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
	 * @return arrayList delle rubriche caricate in memoria.
	 */
	public ArrayList<Rubrica> getRubriche()
	{
		return sistema.getRubriche();
	}
	
	/**
	 * Imposta la rubrica selezionata dalla combobox attraverso il suo nome, univoco per ogni rubrica
	 * @param indice
	 */
	public void setRubricaSelezionata(int indice) 
	{
		rubricaSelezionata = sistema.getRubriche().get(indice);
	}
	
	/**
	 * Restituisce la rubrica selezionata.
	 * @return
	 */
	public Rubrica getRubricaSelezionata() 
	{
		return rubricaSelezionata;
	}
	
	/**
	 * Modifica il nome della rubrica selezionata
	 * @param nuovoNome
	 * @throws SQLException
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
	 * @param nomeRubrica
	 * @throws SQLException
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
	 * Cancella dal DB e dalla memoria la rubrica selezionata
	 * @throws SQLException
	 */
	public void deleteRubrica() throws SQLException 
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
	 * Imposta la rubrica selezionata dalla combobox attraverso il suo nome, univoco per ogni rubrica
	 * @param indice
	 */
	public void setContattoSelezionato(int indice) 
	{
		// TODO : impostare come viene selezionato il contatto selezionato
		contattoSelezionato = rubricaSelezionata.getContatti().get(indice);
	}
	
	/**
	 * Restituisce la rubrica selezionata.
	 * @return
	 */
	public Contatto getContattoSelezionato() 
	{
		return contattoSelezionato;
	}
	
	/**
	 * Carica i contatti dal DB in memoria.
	 * @throws Exception 
	 */
	public void loadContatti() throws Exception 
	{
		ArrayList<Contatto> contatti;
		// Se la rubrica non è inizializzata
		// allora i contatti sono caricati dal DB
		if(rubricaSelezionata.getContatti() == null) 
		{
			System.out.println("Rubrica selezionata è: " + rubricaSelezionata.getNome());
			contatti = new ArrayList<>();
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			rubricaPosgr.apriConnessione();
			rubricaPosgr.loadContatti(rubricaSelezionata.getNome(), contatti);
			rubricaSelezionata.setContatti(contatti);
		}
	}
	
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
			rubricaPosgr.loadGruppi(rubricaSelezionata.getNome(), gruppi);
			// associazione dei contatti ai rispettivi gruppi
			
			rubricaSelezionata.setGruppi(gruppi);
		}
	}
	
	/**
	 * Restituisce i nomi di tutti i contatti della rubrica selezionata.
	 * Necessario per visualizare i nomi nell'interfaccia UI.
	 * @return	Array di nomi in forma di strighe
	 */
	public String[] getNomiContattiRubrica()
	{
		String[] nomiContattiRubriche = new String[rubricaSelezionata.getContatti().size()];
		for(Contatto c : rubricaSelezionata.getContatti()) 
		{
			// sono riunite tutte le parti di un nome di un contatto in una stringa
			String nomeCompleto;
			if(c.getSecondoNome()!=null) 
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
	
	public int inizializzaInserimento() throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		connTransazione = rubricaPosgr.apriConnessione();
		connTransazione.setAutoCommit(false);
		return rubricaPosgr.startTransazione(connTransazione);
	}
	
	public void finalizzaInserimento(Contatto contatto) throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		connTransazione.commit();
		System.out.println("Commit riuscito");
		rubricaSelezionata.getContatti().add(contatto);
		connTransazione = null;
	}
	public Contatto addContatto(String nome, String secondonome, String cognome,
                            String numMobile, String numFisso, String via, String citta, String nazione, String cap,
                            int id) throws SQLException
	 {
		Contatto contatto;
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try
		{
			rubricaPosgr.addContatto(rubricaSelezionata.getNome(), nome, secondonome, cognome, numMobile, numFisso, via, citta,
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
	 * 
	 * @param contatto
	 * @param via
	 * @param città
	 * @param nazione
	 * @param cap
	 * @throws SQLException
	 */
	public void addIndirizzoSec(Contatto contatto, String via, String città, String nazione, String cap) throws SQLException
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
	 * 
	 * @param contatto
	 * @param numero
	 * @param descrizione
	 * @throws SQLException
	 */
	public void addTelefonoSec(Contatto contatto, String numero, String descrizione) throws SQLException
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
	 * 
	 * @param contatto
	 * @param indirizzoEmail
	 * @param descrizione
	 * @throws SQLException
	 */
	public void addEmailSec(Contatto contatto, String indirizzoEmail, String descrizione) throws SQLException
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
	
	public void deleteContattoSelezionato() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			// cancellazione dal DB
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			conn = rubricaPosgr.apriConnessione();
			rubricaPosgr.deleteContatto(contattoSelezionato.getId(), conn);
			conn = null;
			// cancellazione dalla memoria
			rubricaSelezionata.getContatti().remove(contattoSelezionato);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void deleteContattoSelezionatoTransazione(int indiceContatto) throws SQLException
	{
		try {
			// cancellazione dal DB
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			rubricaPosgr.deleteContatto(contattoSelezionato.getId(), connTransazione);
			// cancellazione dalla memoria
			rubricaSelezionata.getContatti().remove(contattoSelezionato);
		} catch (SQLException e) {
			throw e;
		}
	}

}
