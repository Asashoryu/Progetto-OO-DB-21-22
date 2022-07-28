package controller;

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
import model.Indirizzo.tipoIndirizzo;

/** Gestisce l'interazione dell'interfaccia col model e col DB. */
public class Controller {
	
	/** Oggetto contenitore delle rubriche. */
	private Sistema sistema;
	/** Rubrica soggetta a operazioni di modifica, cancellazione o selezione. */
	private Rubrica rubricaSelezionata;
	
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
	 * Carica i contatti dal DB in memoria.
	 */
	public void loadContatti() 
	{
		// Se la rubrica non è inizializzata
		// allora i contatti sono caricati dal DB
		if(rubricaSelezionata.getContatti() == null) 
		{
			RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
			System.out.println("Rubrica selezionata è: " + rubricaSelezionata.getNome());
			rubricaSelezionata.setContatti(rubricaPosgr.loadContatti(rubricaSelezionata.getNome()));
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
	
	public Contatto addContatto(String nome, String secondonome, String cognome,
                            String numMobile, String numFisso, String via, String citta, String nazione, String cap,
                            String indirizzoEmail, String descrEmail) throws SQLException
	 {
		Contatto contatto;
		int id;
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try 
		{
			id = rubricaPosgr.addContatto(rubricaSelezionata.getNome(), nome, secondonome, cognome, numMobile, numFisso, via, citta,
					                      nazione, cap, indirizzoEmail, descrEmail);
			contatto = rubricaSelezionata.aggiungiContatto(nome, secondonome, cognome, numMobile, numFisso, via, citta, nazione, cap, id);
		}
		catch (SQLException e) 
		{
			throw e;
		}
		return contatto;
	}
	
	public void addIndirizzoSec(Contatto contatto, String via, String città, String nazione, String cap) throws SQLException
	{
		ContattoDAO contattoPosgr =  new ContattoImplementazionePostgresDAO();
		try 
		{
			contattoPosgr.addIndirizzo(via, città, nazione, cap, "Secondario", contatto.getId());
			contatto.addIndirizzo(via, città, nazione, cap, tipoIndirizzo.Secondario);
		}
		catch (SQLException e)
		{
			throw e;
		}
	}
	
	public void addTelefonoSec(Contatto contatto, String numero, String descrizione) throws SQLException
	{
		ContattoDAO contattoPosgr =  new ContattoImplementazionePostgresDAO();
		try 
		{
			contattoPosgr.addTelefono(numero, descrizione, contatto.getId());
			contatto.addTelefono(numero, descrizione);
		}
		catch (SQLException e)
		{
			throw e;
		}
	}
	
	public void addEmailSec(Contatto contatto, String indirizzoEmail, String descrizione) throws SQLException
	{
		ContattoDAO contattoPosgr =  new ContattoImplementazionePostgresDAO();
		try
		{
			contattoPosgr.addEmail(indirizzoEmail, descrizione, contatto.getId());
			contatto.addEmail(indirizzoEmail, descrizione);
		}
		catch (SQLException e)
		{
			throw e;
		}
	}
	
	public void deleteContatto(int indiceContatto)
	{
		Contatto contattoEliminato = rubricaSelezionata.getContatti().get(indiceContatto);
	}
}
