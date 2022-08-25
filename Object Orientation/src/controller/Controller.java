package controller;

import model.Rubrica;
import model.Sistema;
import model.Contatto;
import model.Gruppo;
import model.Indirizzo.tipoIndirizzo;

import dao.RubricaDAO;
import dao.SistemaDAO;

import implementazionedao.RubricaImplementazionePostgresDAO;
import implementazionedao.SistemaImplementazionePostgresDAO;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;


/** Gestisce l'interazione dell'interfaccia col model e col DB. */
public class Controller {
	
	/** Oggetto contenitore delle rubriche. */
	private Sistema sistema;
	
	/** Rubrica soggetta a operazioni di modifica, cancellazione o selezione. */
	private Rubrica rubricaSelezionata;
	
	/** Contatto soggetto alle operazioni di modifica, cancellazione o selezione. */
	private Contatto contattoSelezionato;
	
	/** Contatto soggetto alle operazioni di modifica, cancellazione o selezione. */
	private Gruppo gruppoSelezionato;
	
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
		// Se non Ë selezionato un gruppo allora il contatto viene selezionato secondo l'indicizzazione della rubrica
		// Altrimenti Ë selezionato secondo l'ordine dei contatti 
		if (gruppoSelezionato == null)
		{
			contattoSelezionato = rubricaSelezionata.getContatti().get(indice);
		}
		else {
			contattoSelezionato = gruppoSelezionato.getContatti().get(indice);
		}
	}
	
	/**
	 * Restituisce la rubrica selezionata.
	 * @return
	 */
	public Contatto getContattoSelezionato() 
	{
		return contattoSelezionato;
	}
	
	public void setGruppoSelezionato(int indice) 
	{
		gruppoSelezionato = rubricaSelezionata.getGruppi().get(indice);
	}
	
	public void setNullGruppoSelezionato() 
	{
		gruppoSelezionato = null;
	}
	
	public Gruppo getGruppoSelezionato() 
	{
		return gruppoSelezionato;
	}
	
	/**
	 * Carica i contatti dal DB in memoria.
	 * @throws Exception 
	 */
	public void loadContatti() throws Exception 
	{
		ArrayList<Contatto> contatti;
		// Se la rubrica non Ë inizializzata
		// allora i contatti sono caricati dal DB
		if(rubricaSelezionata.getContatti() == null) 
		{
			System.out.println("Rubrica selezionata Ë: " + rubricaSelezionata.getNome());
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
		// Se la rubrica non Ë inizializzata
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
	
	public int inizializzaInserimento() throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		connTransazione = rubricaPosgr.apriConnessione();
		connTransazione.setAutoCommit(false);
		return rubricaPosgr.generaContattoID(connTransazione);
	}
	
	public void finalizzaInserimento(Contatto contatto) throws SQLException
	{
		connTransazione.commit();
		System.out.println("Commit riuscito");
		rubricaSelezionata.getContatti().add(contatto);
		connTransazione = null;
	}
	
	public void inizializzaModifica() throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		connTransazione = rubricaPosgr.apriConnessione();
		connTransazione.setAutoCommit(false);
	}
	
	public void finalizzaModifica(Contatto nuovoContatto) throws SQLException
	{
		int indiceVecchioRubrica;
		int indiceVecchioGruppo;
		Boolean modifica;
		connTransazione.commit();
		System.out.println("Commit riuscito");
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
	
	public void addImmagine(Contatto contatto, String pathImmagine) throws SQLException {
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
	 * 
	 * @param contatto
	 * @param via
	 * @param citt‡
	 * @param nazione
	 * @param cap
	 * @throws SQLException
	 */
	public void addIndirizzoSec(Contatto contatto, String via, String citt‡, String nazione, String cap) throws SQLException
	{
		RubricaDAO rubricaPosgr = new RubricaImplementazionePostgresDAO();
		try 
		{
			rubricaPosgr.addIndirizzo(via, citt‡, nazione, cap, "Secondario", contatto.getId(), connTransazione);
			contatto.addIndirizzo(via, citt‡, nazione, cap, tipoIndirizzo.Secondario);
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
	
	public void addInfoGruppo(Gruppo nuovoGruppo) throws Exception {
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
	
	public void changeInfoGruppo(Gruppo nuovoGruppo) throws Exception 
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
		} catch (SQLException e) {
			throw e;
		}
	}

	public void cercaPerNome(String text) 
	{
		Gruppo gruppoRicerca = rubricaSelezionata.cercaPerNome(text);
		gruppoSelezionato = gruppoRicerca;
	}
	// TODO: da rivedere
	public void cercaPerEmail(String text) {
		Gruppo gruppoRicerca = rubricaSelezionata.cercaPerEmail(text);
		gruppoSelezionato = gruppoRicerca;
	}

	public void cercaPerAccount(String text) {
		Gruppo gruppoRicerca = rubricaSelezionata.cercaPerAccount(text);
		gruppoSelezionato = gruppoRicerca;
	}

	public void cercaPerNumero(String text) {
		Gruppo gruppoRicerca = rubricaSelezionata.cercaPerNumero(text);
		gruppoSelezionato = gruppoRicerca;
	}
	
	public void addContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
							JTextField textFieldCitt‡, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
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
							   		    textFieldCitt‡.getText(),     textFieldNazione.getText(),     textFieldCap.getText(),
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
			System.out.println("Debug: Primo ciclo for");
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(compIndirizzoSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				System.out.println("\t Debug: Entrato nell'if del for");
				String viaSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[1]).getText();
				String citt‡Sec   = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[3]).getText();
				String nazioneSec = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[5]).getText();
				String capSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[7]).getText();
				addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
			}
		}
		// Inserimento numeri secondari
		for (Component compNumeroSec : pannelloScrolNumTel.getComponents())
		{
			System.out.println("Primo ciclo for");
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(compNumeroSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				System.out.println("\tEntrato nell'if del for");
				String descrizioneSec = ((JTextField)((JPanel) compNumeroSec).getComponents()[0]).getText();
				String numeroSec      = ((JTextField)((JPanel) compNumeroSec).getComponents()[1]).getText();
				addTelefonoSec(nuovoContatto, numeroSec, descrizioneSec);
			}
		}
		// Inserimento email secondarie
		for (Component compEmailSec : pannelloScrollMail.getComponents())
		{
			System.out.println("Primo ciclo for");
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(compEmailSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				System.out.println("\tEntrato nell'if del for");
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
	
	public void changeContatto(JTextField textFieldNome, JTextField textFieldSecondoNome, JTextField textFieldCognome, JTextField textFieldVia,
							   JTextField textFieldCitt‡, JTextField textFieldNazione, JTextField textFieldCap, JTextField textFieldNumMobile,
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
														textFieldCitt‡.getText(),     textFieldNazione.getText(),     textFieldCap.getText());
		// INSERIMENTI SECONDARI
		// inserimento immagine
		if (percorsoImmagine != null)
		{
			// gestisce inserimento immagine
			addImmagine(nuovoContatto, percorsoImmagine);
		}
		// Inserimento indirizzi secondari
		for (Component compIndirizzoSec : pannelloScrollIndirizziSec.getComponents())
		{
			System.out.println("Primo ciclo for");
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(compIndirizzoSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				System.out.println("\tEntrato nell'if del for");
				String viaSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[1]).getText();
				String citt‡Sec   = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[3]).getText();
				String nazioneSec = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[5]).getText();
				String capSec     = ((JTextField)((JPanel) compIndirizzoSec).getComponents()[7]).getText();
				addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
			}
		}
		// Inserimento numeri secondari
		for (Component compNumeroSec : pannelloScrolNumTel.getComponents())
		{
			System.out.println("Primo ciclo for");
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(compNumeroSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				System.out.println("\tEntrato nell'if del for");
				String descrizioneSec = ((JTextField)((JPanel) compNumeroSec).getComponents()[0]).getText();
				String numeroSec      = ((JTextField)((JPanel) compNumeroSec).getComponents()[1]).getText();
				addTelefonoSec(nuovoContatto, numeroSec, descrizioneSec);
			}
		}
		// Inserimento email secondarie
		for (Component compEmailSec : pannelloScrollMail.getComponents())
		{
			System.out.println("Primo ciclo for");
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(compEmailSec instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				System.out.println("\tEntrato nell'if del for");
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
	
	public Boolean checkSeTipoIndirizzoPrincipale(tipoIndirizzo tipo)
	{
		return tipo == tipoIndirizzo.Principale;
	}
	
	public void addGruppo(JTextField textFieldNome, JPanel pannelloContatti) throws Exception
	{
		ArrayList<Contatto> contatti = new ArrayList<>();
		int indice = 0;
		
		for (Component scrollComponent : pannelloContatti.getComponents())
		{
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(scrollComponent instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
				// controller.addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
				if (checkbox.isSelected())
				{
					contatti.add(getRubricaSelezionata().getContatti().get(indice));
					System.out.println(" Debug: "+checkbox.getText() + " e quello salvato Ë " + getRubricaSelezionata().getContatti().get(indice).getNome());
				}
			}
			indice++;
		}
		Gruppo nuovoGruppo = new Gruppo(textFieldNome.getText(), contatti);
		addInfoGruppo(nuovoGruppo);
	}
	
	public void changeGruppo(JTextField textFieldNome, JPanel pannelloContatti) throws Exception
	{
		ArrayList<Contatto> contatti = new ArrayList<>();
		int indice = 0;
		
		for (Component scrollComponent : pannelloContatti.getComponents())
		{
			// se non Ë un button allora Ë il pannello con gli indirizzi
			if(scrollComponent instanceof JPanel)
			{
				// estraggo le informazioni dal panel trovato
				JCheckBox checkbox = ((JCheckBox)((JPanel) scrollComponent).getComponents()[0]);
				// controller.addIndirizzoSec(nuovoContatto, viaSec, citt‡Sec, nazioneSec, capSec);
				if (checkbox.isSelected())
				{
					contatti.add(getRubricaSelezionata().getContatti().get(indice));
					System.out.println(" Debug: "+checkbox.getText() + " e quello salvato Ë " + getRubricaSelezionata().getContatti().get(indice).getNome());
				}
			}
			indice++;
		}
		Gruppo nuovoGruppo = new Gruppo(textFieldNome.getText(), contatti);
		changeInfoGruppo(nuovoGruppo);
	}
}
	
