package model;

import java.util.ArrayList;

/** Gestisce l'oggetto Rubrica e la sua interazione con il controller. */
public class Rubrica {
	
	/** nome della rubrica (inteso come il nome dell'utente della rubrica). */
	private String nome;
	
	/** contatti salvati nella rubrica in un ArrayList. */
	private ArrayList<Contatto> contatti;
	
	/** gruppi salvati nella rubrica in un ArrayList. */
	private ArrayList<Gruppo> gruppi;
	
	/**
	 *  Alla creazione di una rubrica vengono caricati tutti i suoi contatti.
	 *
	 * @param nome the nome
	 */
	public Rubrica(String nome) {
		setNome(nome);
	}
	
	/**
	 * Imposta il valore del nome localmente..
	 *
	 * @param nome nome della rubrica
	 */
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	/**
	 * Ritorna il nome impostato localmente.
	 *
	 * @return nome della rubrica
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Ritorna l'ArrayList di contatti salvati in rubrica.
	 *
	 * @return contatti
	 */
	public ArrayList<Contatto> getContatti() {
		return contatti;
	}
	
	/**
	 * Imposta il valore dell'ArrayList di contatti localmente.
	 *
	 * @param contatti contatti
	 */
	public void setContatti(ArrayList<Contatto> contatti) {
		this.contatti = contatti;
	}
	
	/**
	 * Ritorna l'ArrayList di gruppi.
	 *
	 * @return gruppi
	 */
	public ArrayList<Gruppo> getGruppi() {
		return gruppi;
	}

	/**
	 * Imposta il valore dell'ArrayList di gruppi localmente.
	 *
	 * @param gruppi gruppi
	 */
	public void setGruppi(ArrayList<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}
	
	/**
	 * Aggiungi un nuovo contatto nella rubrica.
	 *
	 * @param primonome nome del contatto
	 * @param secondonome secondo nome del contatto
	 * @param cognome cognome del contatto
	 * @param numMobile numero mobile del contatto
	 * @param numFisso numero fisso del contatto
	 * @param via via dell'indirizzo del contatto
	 * @param citta città dell'indirizzo del contatto
	 * @param nazione nazione dell'indirizzo del contatto
	 * @param cap cap dell'indirizzo del contatto
	 * @param id identificativo del contatto
	 * @return contatto
	 */
	public Contatto aggiungiContatto(String primonome, String secondonome, String cognome,
                                 String numMobile, String numFisso, String via, String citta, String nazione, String cap, int id) 
	{
		Contatto contatto;
		try 
		{
			/** creo il contatto */
			contatto = new Contatto(primonome, secondonome,cognome, numMobile, numFisso, via, citta, nazione, cap, id);
			/** aggiungo il contatto alla rubrica */
			contatti.add(contatto);
		} 
		catch (NullPointerException e) 
		{
			throw e;
		}
		
		return contatto;
	}
	
	/**
	 * Cerca per nome.
	 *
	 * @param text stringa o sottostringa da cercare come nome
	 * @return ArrayList dei contatti trovati
	 */
	public ArrayList<Contatto> cercaPerNome(String text) 
	{
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		for (Contatto contatto : contatti)
		{
			if (contatto.getNome().matches("(?i).*"+text+".*"))
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca per email.
	 *
	 * @param text stringa o sottostringa da cercare come email
	 * @return ArrayList dei contatti trovati 
	 */
	public ArrayList<Contatto> cercaPerEmail(String text) {
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Boolean match;
		for (Contatto contatto : contatti)
		{
			match = false;
			for(Email email : contatto.getEmail())
			{
				if (email.getStringaEmail().matches("(?i).*"+text+".*"))
				{
					match = true;
				}
			}
			if (match == true)
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca per account.
	 *
	 * @param text stringa o sottostringa da cercare come account
	 * @return ArrayList dei contatti trovati 
	 */
	public ArrayList<Contatto> cercaPerAccount(String text) {
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Boolean match;
		for (Contatto contatto : contatti)
		{
			match = false;
			for (Email email : contatto.getEmail())
			{
				for (Account account : email.getAccount())
				{
					if (account.getNickname().matches("(?i).*"+text+".*"))
					{
						match = true;
					}
				}
			}
			if (match == true)
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
	
	/**
	 * Cerca per numero.
	 *
	 * @param text stringa contenente il numero da cercare
	 * @return ArrayList dei contatti trovati 
	 */
	public ArrayList<Contatto> cercaPerNumero(String text) {
		ArrayList<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Boolean match;
		for (Contatto contatto : contatti)
		{
			match = false;
			for(Telefono telefono : contatto.getTelefoni())
			{
				if (telefono.getNumero().matches("(?i).*"+text+".*"))
				{
					match = true;
				}
			}
			if (match == true)
			{
				contattiTrovati.add(contatto);
			}
		}
		return contattiTrovati;
	}
}	
